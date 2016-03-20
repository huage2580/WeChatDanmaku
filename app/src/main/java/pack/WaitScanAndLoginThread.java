package pack;

public class WaitScanAndLoginThread extends Thread{
	private int tip=1;//0��ʾ�Ѿ�ɨ��
	private String uuid;
	private boolean running=true;
	private HttpClient hc=HttpClient.getInstance();
	private StringSubClass ss=new StringSubClass();
	private OnScanListener mScanListener;
	private WeChatClass wechat;

	public interface OnScanListener{//��½�ļ�����
		void onScan();//ɨ��ɹ�
		void onSure();//ȷ����½
	}
	
	public void setmScanListener(OnScanListener mScanListener) {
		this.mScanListener = mScanListener;
	}
	
	WaitScanAndLoginThread(String uuid,WeChatClass wechat){
		this.uuid=uuid;
		this.wechat=wechat;
		
	}
	@Override
	public void run() {
		System.out.println("�����ȴ��߳�");
		while(running){
			String result = hc.get(
					"https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/login?tip="
							+ tip + "&uuid=" + uuid + "&_="
							+ System.currentTimeMillis(), "utf-8", null, false);
			String code=ss.subStringOne(result, ".code=", ";");
			if (mScanListener!=null) {
				if(code.equals("201")){
					tip=0;
					mScanListener.onScan();
				}else if (code.equals("200")) {
					
					String redirect_uri=ss.subStringOne(result, "window.redirect_uri=\"", "\";");
					running=false;
					String loginResult=hc.get(redirect_uri+"&fun=new");
					wechat.skey=ss.subStringOne(loginResult, "<skey>", "</skey>");
					wechat.wxsid=ss.subStringOne(loginResult, "<wxsid>", "</wxsid>");
					wechat.pass_ticket=ss.subStringOne(loginResult, "<pass_ticket>", "</pass_ticket>");
					wechat.wxuin=ss.subStringOne(loginResult, "<wxuin>", "</wxuin>");
					wechat.baseUrl=redirect_uri.substring(0, redirect_uri.lastIndexOf("/"));
					mScanListener.onSure();
					wechat.init();
					
				}
			}
			
		}//while
		
	}

}
