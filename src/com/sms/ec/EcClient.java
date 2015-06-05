package com.sms.ec;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sms.IPushSms;

public class EcClient implements IPushSms{

	final public static String EC_USER_ID = "user";
	final public static String EC_PASSWORD = "password";
	final public static String EC_CLIENT_IP = "";

	public int sendSms(String phoneNum, String content)
	{
		HttpURLConnection connection = null;
		try {
			String line;

				//String orderurl = "http://210.22.155.10:8089/mlh/login.do?comd=login&username=test测试1&password=testpass&nickname=test测试1&sex=1&sign=";
				String orderurl = "http://service.1681860.com:8080/sms/api";
				connection = (HttpURLConnection) new URL(orderurl).openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST"); 
				
				String data = "ec_method=ec.sms.send&ec_app_key=4006681860&ec_client_ip="+EC_CLIENT_IP+
						"&encoding=utf-8&ec_user_id="+EC_USER_ID+
						"&ec_password="+EC_PASSWORD+
						"&link_id=1&phone="+phoneNum+
						"&content="+content+"&product_id=1046&channelChange=2";
				OutputStream out = connection.getOutputStream();
//				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter (out));
//				writer.append(jsondata);
				out.write(data.getBytes());
				out.flush();
				
				InputStream in = connection.getInputStream();
				StringBuilder sb = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				while ((line = reader.readLine()) != null)
					sb.append(line);

				in.close();
				connection.disconnect();
				String result = new String(sb);
				System.out.println(result);
		} catch (Exception e) {
//			logger.error(String.format("validator token error %s \n %s",
//					new Object[] { e.getMessage(), e.getCause() }));
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		EcClient client = new EcClient();

		//System.out.println(System.getProperty("http.nonProxyHosts"));

		//System.setProperty("http.nonProxyHosts", "169.254/16");
		// 发送传真接口
		int result = client.sendSms("13818365949", "测试内容");
		System.out.println(result);
	}
}
