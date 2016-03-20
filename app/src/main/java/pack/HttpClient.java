package pack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
	CookieManager ca = new CookieManager();
	String sessionID = "";
	String contentType="";
	private static HttpClient hcClient;
	
	static HttpClient getInstance(){
		if (hcClient==null) {
			synchronized (HttpClient.class) {
				if (hcClient==null) {
					hcClient=new HttpClient();
				}
			}
		}
		return hcClient;
	}
	public String get(String url, String charset,String referer,boolean isRedirects) {
		try {
			String key = "";
			String cookieVal = "";

			URL httpURL = new URL(url);
			HttpURLConnection http = (HttpURLConnection) httpURL
					.openConnection();
			http.setInstanceFollowRedirects(isRedirects);//设置自动跳转
			if (referer!=null ) {
				http.setRequestProperty("Referer", referer);
			}
			if (contentType!=null ) {
				http.setRequestProperty("content-type", contentType);
			}
			http.setRequestProperty("User-agent","Mozilla/5.0 (Linux; Android 4.2.1; Nexus 7 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166  Safari/535.19");
			if (!sessionID.equals("")) {
				http.setRequestProperty("Cookie", sessionID);
			}
			for (int i = 1; (key = http.getHeaderFieldKey(i)) != null; i++) {
				if (key.equalsIgnoreCase("set-cookie")) {
					cookieVal = http.getHeaderField(i);
					cookieVal = cookieVal.substring(
							0,
							cookieVal.indexOf(";") > -1 ? cookieVal
									.indexOf(";") : cookieVal.length() - 1);
					sessionID = sessionID + cookieVal + ";";
				}
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					http.getInputStream(), charset));
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
				sb.append("\n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String get(String url) {
		return get(url, "utf-8", null, true);
	}
	public String post(String url, String data, String charset,String referer,boolean isRedirects) {
		try {
			URL httpURL = new URL(url);
			String key = null;
			String cookieVal = null;
			HttpURLConnection http = (HttpURLConnection) httpURL
					.openConnection();
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko LBBROWSER");
			http.setInstanceFollowRedirects(isRedirects);//设置自动跳转
			if (referer!=null ) {
				http.setRequestProperty("Referer", referer);
			}
			if (contentType!=null ) {
				http.setRequestProperty("content-type", contentType);
			}
			if (!sessionID.equals("") && sessionID!=null) {
				http.setRequestProperty("Cookie", sessionID);
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					http.getOutputStream(), charset));
			bw.write(data);
			bw.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					http.getInputStream(), charset));
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
				sb.append("\n");
			}
			br.close();
			for (int i = 1; (key = http.getHeaderFieldKey(i)) != null; i++) {
				if (key.equalsIgnoreCase("set-cookie")) {
					cookieVal = http.getHeaderField(i);
					cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
					sessionID = sessionID + cookieVal + ";";
				}
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String post(String url,String data) {
		return post(url, data, "utf-8", null, true);
		
	}
}