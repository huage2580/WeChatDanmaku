package pack;

import java.util.List;

import pack.DownLoadQrCodeThread.OnloadQrCodeFinnishListener;
import pack.HeartBeatThread.OnNewMsgListener;
import pack.WaitScanAndLoginThread.OnScanListener;

import com.google.gson.Gson;



public class WeChatClass {
	boolean isBeat=false;
	//************** һЩ����
	public String uuid;
	public String baseUrl;
	public String skey;
	public String wxsid;
	public String pass_ticket;
	public String wxuin;
	public String keyString;
	public BaseResponeBean initbean;
	//***************����ӿ�
	
	Gson gson=new Gson();
	private HttpClient hc=HttpClient.getInstance();
	private StringSubClass ss=new StringSubClass();
	private OnScanListener mScanListener;
	private OnNewMsgListener mNewMsgListener;
	private OnLoadQrCodeListener mQrCodeListener;
	
	
	//��ȡͼƬ��byte���飬��Ҫ���ڰ�׿��bitmapչʾ��pc�ɲ���
	public interface OnLoadQrCodeListener {
		void onLoadSuccess(byte[] imageBytes);
	}
	
	/**
	 * ����������������������Ȼ������������
	 */
	public void gogogo(){
		System.setProperty ("jsse.enableSNIExtension", "false");//����ssl�쳣
		String result=hc.post("https://login.weixin.qq.com/jslogin",
				"appid=wx782c26e4c19acffb&fun=new&lang=zh_CN&_="+System.currentTimeMillis());
		uuid=ss.subStringOne(result, ".uuid = \"", "\";");//�õ�uuid
		//�������ض�ά����߳�,��׿����Ҫ����������Ϊfalse
		DownLoadQrCodeThread qrCodeThread=new DownLoadQrCodeThread("https://login.weixin.qq.com/qrcode/"+uuid+"?t=webwx&_=", false);
		qrCodeThread.setListener(new OnloadQrCodeFinnishListener() {
			
			@Override
			public void onLoadSuccess(byte[] imageBytes) {
				if (mQrCodeListener!=null) {
					mQrCodeListener.onLoadSuccess(imageBytes);
				}
				//��ά��������ɣ�������ѯ�̵߳ȴ�ɨ���ά��͵�½
				WaitScanAndLoginThread loginThread=new WaitScanAndLoginThread(uuid,WeChatClass.this);
				loginThread.setmScanListener(mScanListener);
				loginThread.start();
			}
		});
		qrCodeThread.start();
		
	}//uuid
	
	
	
	/**
	 * �ڳɹ���½���ʼ��΢����ز���
	 */
	 void init(){
		 for (int i = 0; i < 5; i++) {//��5���߳�ȥ��ʼ��
			 new InitThread().start();
		}
		
	}
	
	
	
	/**
	 * ��ʼ�����ѡ���Ի�ȡ���Ѻ�Ⱥ
	 */
	public void getFriendAndGroup(){
		String groupResult=hc.post(baseUrl+"/webwxgetcontact?r="+System.currentTimeMillis()+"&pass_ticket="+pass_ticket+"&skey="+skey,"{}");
		System.err.println(groupResult);
	}
	/**
	 * ͬ��syncKeys��ÿ�λ�ȡ������Ϣ��Ҫͬ��
	 */
	public void syncKeys(String reslut) {
		initbean=gson.fromJson(reslut, BaseResponeBean.class);
		keyString="";
		List<BaseResponeBean.SyncKeyEntity.ListEntity> keyList = initbean.getSyncKey().getList();
		for (BaseResponeBean.SyncKeyEntity.ListEntity listEntity : keyList) {
			keyString+=listEntity.getKey()+"_"+listEntity.getVal()+"|";
		}
		keyString=keyString.substring(0, keyString.length()-1);
	}
	
	//���ø��ּ�����
	public void setmScanListener(OnScanListener mScanListener) {
		this.mScanListener = mScanListener;
	}
	public void setmNewMsgListener(OnNewMsgListener mNewMsgListener) {
		this.mNewMsgListener = mNewMsgListener;
	}
	public void setmQrCodeListener(OnLoadQrCodeListener mQrCodeListener) {
		this.mQrCodeListener = mQrCodeListener;
	}
	class InitThread extends Thread{
		
			@Override
			public void run() {
				String data="{\"BaseRequest\":{\"Uin\":\""+wxuin+"\",\"Sid\":\""+wxsid+"\",\"Skey\":\""+skey+"\",\"DeviceID\":\"e982189146506998\"}}";
				hc.contentType="application/json";
				String initResult = hc.post(baseUrl+"/webwxinit?r="+System.currentTimeMillis(),
						data);				
				
				System.out.println("�Ƿ��ѿ��������߳�");
				if(!isBeat){
					//ͬ��keys
					syncKeys(initResult);
					//���������߳�
					HeartBeatThread heartBeatThread=new HeartBeatThread(WeChatClass.this);
					heartBeatThread.setmNewMsgListener(mNewMsgListener);
					heartBeatThread.start();
					isBeat=true;
				}
			}
	}
	
}
