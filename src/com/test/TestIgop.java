package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.game.util.JsonUtil;
import cn.org.util.HashHex;

public class TestIgop {

	public static String app = "dpsg";
	public static String URL = "http://g.10086.cn/pay/open/index?";
	public static String method = "getsessionkey";
	public static String key = "617c5586d63600b2bfa0309c94fcbf4e";
	
	public static void main(String[] args) throws Exception
	{
		
		SessionResultVO v = getSessionKey();
		
		String result = startOrder(app, "applyforpurchase" , "13818365949", "006071525001", "41403000", v.getSessionkey());
		StartOrderResultVO sorv = (StartOrderResultVO)JsonUtil.getObject4JsonString(result, StartOrderResultVO.class);
		
		//confirmpurchase 
		//重新获取sessionkey
		v = getSessionKey();
		java.util.Scanner sc=new java.util.Scanner(System.in);
		String verifycode = sc.next();
		comfirmOrder(app, "confirmpurchase", verifycode, sorv.getOrderid(), v.getSessionkey());
		
		//querypurchase
		//重新获取sessionkey
		v = getSessionKey();
		queryOrder(app, "querypurchase", sorv.getOrderid(), v.getSessionkey());
		//queryOrder(app, "querypurchase", "0000000f1f0130e4004e8d9a", v.getSessionkey());
		
	}
	
	private static SessionResultVO getSessionKey()
	{
		String time = "" + System.currentTimeMillis() / 1000;
		System.out.println(time);		
		String signstr = "app=" + app + "&method=" + method + "&time=" + time + "&key=" + key;
		
		String sign = HashHex.HashToMD5Hex(signstr).toLowerCase();
		//System.out.println("signstr:" + signstr);		
		//System.out.println("sign:" + sign);
		String url = URL + "app=" + app + "&method=" + method + "&time=" + time + "&hash=" + sign + "&format=json";
		String result = httpGet(url);
		
		SessionResultVO v = (SessionResultVO)JsonUtil.getObject4JsonString(result, SessionResultVO.class);
		
		return v;
	}
	
	private static String startOrder(String app, String method, String tel, String consumecode, String salechannelid, String sessionkey) {
		String time = "" + System.currentTimeMillis() / 1000;
		String signstr = "app=" + app + "&method=" + method + "&tel=" + tel + "&consumecode=" + consumecode + "&time=" + time + "&sessionkey=" + sessionkey + "&key=" + key;
		
		String sign = HashHex.HashToMD5Hex(signstr).toLowerCase();
		String url = URL + "app=" + app + "&method=" + method + "&tel=" + tel + "&consumecode=" + consumecode + "&time=" + time + "&sessionkey=" + sessionkey + "&salechannelid=" + salechannelid + "&hash=" + sign + "&format=json";
		String result = httpGet(url);
		return result;
	}
	
	private static String comfirmOrder(String app, String method, String verifycode, String orderid, String sessionkey) {
		String time = "" + System.currentTimeMillis() / 1000;
		String signstr = "app=" + app + "&method=" + method + "&verifycode=" + verifycode + "&orderid=" + orderid + "&time=" + time + "&sessionkey=" + sessionkey + "&key=" + key;
		String sign = HashHex.HashToMD5Hex(signstr).toLowerCase();
		System.out.println("signstr:" + signstr);		
		System.out.println("sign:" + sign);
		String url = URL + "app=" + app + "&method=" + method + "&verifycode=" + verifycode + "&orderid=" + orderid + "&time=" + time + "&sessionkey=" + sessionkey + "&hash=" + sign + "&format=json";
		//System.out.println("url:" + url);
		String result = httpGet(url);
		return result;
	}
	
	private static String queryOrder(String app, String method, String orderid, String sessionkey) {
		String time = "" + System.currentTimeMillis() / 1000;
		String signstr = "app=" + app + "&method=" + method + "&orderid=" + orderid + "&time=" + time + "&sessionkey=" + sessionkey + "&key=" + key;
		String sign = HashHex.HashToMD5Hex(signstr).toLowerCase();
		System.out.println("signstr:" + signstr)	;		
		System.out.println("sign:" + sign);
		String url = URL + "app=" + app + "&method=" + method + "&orderid=" + orderid + "&time=" + time + "&sessionkey=" + sessionkey + "&hash=" + sign + "&format=json";
		//System.out.println("url:" + url);
		String result = httpGet(url);
		return result;
	}

	public static String httpGet(String url) {
		HttpURLConnection connection = null;
		try {
			String line;

				connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("GET"); 
				
				InputStream in = connection.getInputStream();
				StringBuilder sb = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				while ((line = reader.readLine()) != null)
					sb.append(line);

				in.close();
				connection.disconnect();
				String result = new String(sb);
				System.out.println("result:"+result);
				
				return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
