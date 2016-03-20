package pack;

import java.util.List;

import pack.MsgBean.AddMsgListEntity;

import com.google.gson.Gson;

public class HeartBeatThread extends Thread{
	
	private HttpClient hc=HttpClient.getInstance();
	private StringSubClass ss=new StringSubClass();
	private Gson gson=new Gson();
	private boolean beat=true;
	private OnNewMsgListener mNewMsgListener;
	private WeChatClass wechat;
	public interface OnNewMsgListener{//��������Ϣ������
		void onNewMsg(String text);
		void startBeat();
	}
	public HeartBeatThread(WeChatClass wechat) {
		this.wechat=wechat;
	}
	
	public void setmNewMsgListener(OnNewMsgListener mNewMsgListener) {
		this.mNewMsgListener = mNewMsgListener;
	}
	@Override
	public void run() {
		if (mNewMsgListener!=null) {
			mNewMsgListener.startBeat();
		}
		while (beat) {
			//window.synccheck={retcode:"0",selector:"7"}
			String syncResult=hc.get("https://webpush2.weixin.qq.com/cgi-bin/mmwebwx-bin/synccheck?skey="+wechat.skey
					+"&sid="+wechat.wxsid
					+"&uin="+wechat.wxuin
					+"&deviceId=e982089146506998"
					+"&synckey="+wechat.keyString
					+"&r="+System.currentTimeMillis()
					+"&_="+System.currentTimeMillis()
					);
//			System.err.println(syncResult);
			String selector;
			try {
				 selector=ss.subStringOne(syncResult, "selector:\"", "\"}");
			} catch (Exception e) {
				continue;
			}
			
			if (!selector.equals("0")) {//����Ϣ
				if (mNewMsgListener!=null) {
					//��ȡ����Ϣ
					String data2="{\"BaseRequest\":{\"Uin\":\""+wechat.wxuin+"\",\"Sid\":\""+wechat.wxsid+"\",\"Skey\":\""+wechat.skey+"\",\"DeviceID\":\"e982189146506998\"},\"SyncKey\":"
							+wechat.gson.toJson(wechat.initbean.getSyncKey())+",\"rr\":"+System.currentTimeMillis()+"}";
					String newMsg=hc.post(wechat.baseUrl+"/webwxsync?sid="+wechat.wxsid+"&skey="+wechat.skey+"&pass_ticket="+wechat.pass_ticket,data2);//
					
					
					//ͬ�������
					wechat.syncKeys(newMsg);
					
					//��ȡ��Ϣ
					MsgBean msgBean=gson.fromJson(newMsg, MsgBean.class);
					List<AddMsgListEntity> msgList = msgBean.getAddMsgList();
					for (AddMsgListEntity addMsgListEntity : msgList) {
						if (addMsgListEntity.getFromUserName().startsWith("@@")) {//ֻ����Ⱥ��Ϣ
							String msg=addMsgListEntity.getContent();
							msg=msg.substring(msg.indexOf("<br/>")+5);
							mNewMsgListener.onNewMsg(msg);
						}
					}//for
				}
			}
			
		}//while
	}
}
