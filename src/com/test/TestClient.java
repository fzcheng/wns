package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import cn.game.util.JacksonUtil;
import cn.game.util.JsonUtil;
import cn.game.util.RandomUtil;
import cn.org.util.StringUtil;

import com.util.encrypt.AES;
import com.util.encrypt.EncryUtil;
import com.util.encrypt.RSA;


public class TestClient {

	//publickey:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDdl9iQJ8UdPpR4Q+HFJMMQJYAw9skQfAYk0ch4L4DVp9OzN2QjwneLql9c5sINGKePdwLfb+prvy0T1Km5iDYyzHnxdQgHUOur+pfLAo5drTBm0zDIzyURlWOYaPlZQ6kpHFNWHGDuYloNiTCQ1taRVRN0sBkbDg6Uy2iyyY6IoQIDAQAB
	//privatekey:MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN2X2JAnxR0+lHhD4cUkwxAlgDD2yRB8BiTRyHgvgNWn07M3ZCPCd4uqX1zmwg0Yp493At9v6mu/LRPUqbmINjLMefF1CAdQ66v6l8sCjl2tMGbTMMjPJRGVY5ho+VlDqSkcU1YcYO5iWg2JMJDW1pFVE3SwGRsODpTLaLLJjoihAgMBAAECgYAYCJQQbn/OgC8SLCqcezQ82BRjOyDrjMgNzkLz5hMPGTi4w1U1G65jk4LsTIrXaMkjokDJgxXlwcJd2D7gRIm8HQsk0+mRxvdw2kQ1cRe9YirKUN1j2nHpQUH4QWKypwju6vn4jJNoc7GSGETWh0fedJZADIRcH0bb+Llz3t/KwQJBAPQPu1Sstak3Fx6IEEpNAkDGvWTWzeUf7n0xDvUAXTgKJeGFgTbgg5Vk48Z3d5+bqFPM2xiVFJ4+sQsRrFwz6a0CQQDobsEJgie2pOKXl+EjQGdP4csCmlm2q4pq6uITMy9E+SAwiyXEqCQ9+Wus3ShWjKzqDmR6HLV8hruSnuQg3mFFAkB67SWMi4TgGvhRD3UtB0aYO8GO9cY0nnWvprMC4dER9cKIqYagjkqYR32WK/wbbvpEYw0Df6sn3SmtCpTGx/rtAkEAq+ygZMQdVAAKi08QfN66jF/bpgU9rtyb+hHbP5Bwunf702xZd2lXxW+ksay0lbBghpA2ygxKQd33CLD21UkocQJARCcFkfsz7xPHi91rAcUYJbOyxpS6r+oYXvDzjR+n3vtuyf2EioWuEwXp7Lkl1C2Cjxq+v4mapt/lazSPJqWYTg==
	public static String clientPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN2X2JAnxR0+lHhD4cUkwxAlgDD2yRB8BiTRyHgvgNWn07M3ZCPCd4uqX1zmwg0Yp493At9v6mu/LRPUqbmINjLMefF1CAdQ66v6l8sCjl2tMGbTMMjPJRGVY5ho+VlDqSkcU1YcYO5iWg2JMJDW1pFVE3SwGRsODpTLaLLJjoihAgMBAAECgYAYCJQQbn/OgC8SLCqcezQ82BRjOyDrjMgNzkLz5hMPGTi4w1U1G65jk4LsTIrXaMkjokDJgxXlwcJd2D7gRIm8HQsk0+mRxvdw2kQ1cRe9YirKUN1j2nHpQUH4QWKypwju6vn4jJNoc7GSGETWh0fedJZADIRcH0bb+Llz3t/KwQJBAPQPu1Sstak3Fx6IEEpNAkDGvWTWzeUf7n0xDvUAXTgKJeGFgTbgg5Vk48Z3d5+bqFPM2xiVFJ4+sQsRrFwz6a0CQQDobsEJgie2pOKXl+EjQGdP4csCmlm2q4pq6uITMy9E+SAwiyXEqCQ9+Wus3ShWjKzqDmR6HLV8hruSnuQg3mFFAkB67SWMi4TgGvhRD3UtB0aYO8GO9cY0nnWvprMC4dER9cKIqYagjkqYR32WK/wbbvpEYw0Df6sn3SmtCpTGx/rtAkEAq+ygZMQdVAAKi08QfN66jF/bpgU9rtyb+hHbP5Bwunf702xZd2lXxW+ksay0lbBghpA2ygxKQd33CLD21UkocQJARCcFkfsz7xPHi91rAcUYJbOyxpS6r+oYXvDzjR+n3vtuyf2EioWuEwXp7Lkl1C2Cjxq+v4mapt/lazSPJqWYTg==";
	public static String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDdl9iQJ8UdPpR4Q+HFJMMQJYAw9skQfAYk0ch4L4DVp9OzN2QjwneLql9c5sINGKePdwLfb+prvy0T1Km5iDYyzHnxdQgHUOur+pfLAo5drTBm0zDIzyURlWOYaPlZQ6kpHFNWHGDuYloNiTCQ1taRVRN0sBkbDg6Uy2iyyY6IoQIDAQAB";
	
	//publickey:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrEok1qecRPyuGBmGRA12eVdSjDasP59nbQuy71XiwBQDUbZlAzedsQv+IsTKbAdv2zH+iXBUPKlOIXm0RkPwx/rZ5jymUKgR2PahNKRX/r1nd1cFmATpo+J92Ib63G9cC0GIW4tS0ioJuL6Jj8fWHxj+72N8FcuTtUmgh02kSWQIDAQAB
	//privatekey:MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKsSiTWp5xE/K4YGYZEDXZ5V1KMNqw/n2dtC7LvVeLAFANRtmUDN52xC/4ixMpsB2/bMf6JcFQ8qU4hebRGQ/DH+tnmPKZQqBHY9qE0pFf+vWd3VwWYBOmj4n3Yhvrcb1wLQYhbi1LSKgm4vomPx9YfGP7vY3wVy5O1SaCHTaRJZAgMBAAECgYEAhVcbT5KwokbEm+CZZ7hh3io0ZSsgLHOg4UBW2UlYmoorzWfnzhUvzriiCF9lfGCEnOdkjJ6NY5awKVjAVrLVfq9QTwLggY36VGhIIKncROsLUXhOXH7fTaAdVwtlcXCcISTzpjivdZEt5dpK3dA4nPU7ClDE3y9mWPF30iAafiECQQD424wst1yXCBf2tBCLTyjCVWmwySEhpglOzS9fij6FSBqeQo70ul2BqOW5mjHkmycAuFGhk3ciqB4gGpBqYlkdAkEAr/t4fT2GAzR8VVgz03xfwS9o/ZcCyO2qv9PIuXZeZcIGq63py0ERZqDoSnzxrakNrk4x/1SJbkoEoWzfR7TVbQJAYdXGbQoFT6cx7J5r5qjPSF9V9teLu/PPsvg9yJzL6ZAoTtGx7Aqcmk3xSp+RP+OzWLgd95zG+IwJk+t9nSVfDQJBAJOR4BvFd5dAb/JxICR76x/oTbY455DuG3z4bx12cPoLleRo1eAnFBxnWeL8mvzxAhPkZnChxwzA7NkgnqtWrJECQEbuA781CqbkjcSAupIM09yCw4LZI9lI2PdGlGuo6MsU2c+c3yZvnqQBA5xMLAFwapybGVSm9/t9sfACSlAyizs=
	public static String serverPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKsSiTWp5xE/K4YGYZEDXZ5V1KMNqw/n2dtC7LvVeLAFANRtmUDN52xC/4ixMpsB2/bMf6JcFQ8qU4hebRGQ/DH+tnmPKZQqBHY9qE0pFf+vWd3VwWYBOmj4n3Yhvrcb1wLQYhbi1LSKgm4vomPx9YfGP7vY3wVy5O1SaCHTaRJZAgMBAAECgYEAhVcbT5KwokbEm+CZZ7hh3io0ZSsgLHOg4UBW2UlYmoorzWfnzhUvzriiCF9lfGCEnOdkjJ6NY5awKVjAVrLVfq9QTwLggY36VGhIIKncROsLUXhOXH7fTaAdVwtlcXCcISTzpjivdZEt5dpK3dA4nPU7ClDE3y9mWPF30iAafiECQQD424wst1yXCBf2tBCLTyjCVWmwySEhpglOzS9fij6FSBqeQo70ul2BqOW5mjHkmycAuFGhk3ciqB4gGpBqYlkdAkEAr/t4fT2GAzR8VVgz03xfwS9o/ZcCyO2qv9PIuXZeZcIGq63py0ERZqDoSnzxrakNrk4x/1SJbkoEoWzfR7TVbQJAYdXGbQoFT6cx7J5r5qjPSF9V9teLu/PPsvg9yJzL6ZAoTtGx7Aqcmk3xSp+RP+OzWLgd95zG+IwJk+t9nSVfDQJBAJOR4BvFd5dAb/JxICR76x/oTbY455DuG3z4bx12cPoLleRo1eAnFBxnWeL8mvzxAhPkZnChxwzA7NkgnqtWrJECQEbuA781CqbkjcSAupIM09yCw4LZI9lI2PdGlGuo6MsU2c+c3yZvnqQBA5xMLAFwapybGVSm9/t9sfACSlAyizs=";
	public static String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrEok1qecRPyuGBmGRA12eVdSjDasP59nbQuy71XiwBQDUbZlAzedsQv+IsTKbAdv2zH+iXBUPKlOIXm0RkPwx/rZ5jymUKgR2PahNKRX/r1nd1cFmATpo+J92Ib63G9cC0GIW4tS0ioJuL6Jj8fWHxj+72N8FcuTtUmgh02kSWQIDAQAB";
	
	public void createKey() throws Exception
	{
		// 生成RSA密钥对
		Map<String, String> m = RSA.generateKeyPair();
		String publickey = m.get("publicKey");
		String privatekey = m.get("privateKey");

		System.out.println("publickey:" + publickey);
		System.out.println("privatekey:" + privatekey);
	}
	
	public static void main(String[] args) throws Exception
	{
//		test();
//		Test t = new Test();
//		t.createKey();
//		t.createKey();
		
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		map.put("gameId", "10001");
		map.put("comd", "getalltop");
		map.put("mtId", "8078503bbd454ed4dc2acfd5b855");
		map.put("plat", "free");
		map.put("keyIndex", "1");
		
//		map.put("gameId", "10001");
//		map.put("comd", "getrecord");
//		map.put("mtId", "8078503bbd454ed4dc2acfd5b855");
//		map.put("plat", "free");
		
//		comd:getalltop
//		comd : getalltop
//		gameId : 10001
//		keyIndex : 1
//		mtId : 8078503bbd454ed4dc2acfd5b855
//		plat : free
//		sign : n4u7Xs0mfY/T15HCyTsewccHX61zbOdr/kAevjpolkzzQml54ONM2tGZAlBYimUfhcDm4YfzLHEA4jQXSaSHOsK6uU61eYIO6jrQgyHs7wPDUx8BMhjD9m4A2GKI8QuaJ9yXASiv6U3hR06WTEw4o65FeO1VXSPUR4qzW65aGek=

		
		String oinfo = StringUtil.getFromMap(map, "sign");
		// 生成RSA签名
		String sign = EncryUtil.handleRSA(oinfo, clientPrivateKey);

		map.put("sign", sign);
		
		//String info = JsonUtil.getJsonStringFromMap(map);
		String info = JsonUtil.getJsonStringFromMap(map);
		// 商户自己随机生成的AESkey
		String merchantAesKey = RandomUtil.getRandom(16);
		
		// 生成data
		String data = AES.encryptToBase64(info, merchantAesKey);

		// 使用RSA算法将商户自己随机生成的AESkey加密
		String encryptkey = RSA.encrypt(merchantAesKey, serverPublicKey);
		
		System.out.println("oinfo:"+oinfo);
		System.out.println("info:"+info);
		System.out.println("data:"+data);
		System.out.println("merchantAesKey:"+merchantAesKey);
		System.out.println("encryptkey:"+encryptkey);

		TestClient t = new TestClient();
		String gameId = "10001";
		//String url = "record.do?";
		String url = "top.do?";
		String result = t.sendCommond(gameId, data, encryptkey, url);
		decodeResult(result);
	}

	private static void decodeResult(String result) throws Exception {
		TreeMap<String, Object> map = JsonUtil.getMap4Json(result);
		String encryptkey = (String)map.get("key");
		String data = (String)map.get("data");
		
		String dmerchantAesKey = RSA.decrypt(encryptkey, clientPrivateKey);
		String dinfo = AES.decryptFromBase64(data, dmerchantAesKey);
		
		//JSONObject j = JSONObject.fromObject(dinfo);
		TreeMap<String, Object> dmap = JsonUtil.getMap4Json(dinfo);
		//j.toString();
		String doinfo = StringUtil.getFromMap(dmap, "sign");
		String dsign = (String)dmap.get("sign");
		//dmap.remove("sign");
		//String doinfo = JsonUtil.getJsonStringFromMap(dmap);
		boolean matched = EncryUtil.checkHandleRSA(doinfo, dsign, serverPublicKey);
		
		System.out.println("DmerchantAesKey:"+dmerchantAesKey);
		System.out.println("Dinfo:"+dinfo);
		System.out.println("Doinfo:"+doinfo);
		System.out.println("matched:"+matched);
	}

	private String sendCommond(String gameId, String cdata, String encryptkey, String url) {
		HttpURLConnection connection = null;
		try {
			String line;

				String orderurl = "http://127.0.0.1:8080/wns/"+url;
				//String data = "gameId="+gameId+"&data="+cdata+"&key="+encryptkey;
				String data = "gameId="+gameId+"&data="+URLEncoder.encode(cdata, "utf-8")+"&key="+URLEncoder.encode(encryptkey,"utf-8");
				//String data = "gameId="+gameId+"&key="+URLEncoder.encode(encryptkey,"utf-8");
				//connection = (HttpURLConnection) new URL(orderurl + data).openConnection();
				connection = (HttpURLConnection) new URL(orderurl).openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST"); 
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
				System.out.println("result:"+result);
				
				return result;

		} catch (Exception e) {
			e.printStackTrace();
//			logger.error(String.format("validator token error %s \n %s",
//					new Object[] { e.getMessage(), e.getCause() }));
		}
		
		return "";
	}
	
	public static void test()
	{
		// 生成data
		String data = AES.encryptToBase64("hello", "7Si1APV97dwV24C5");
		System.out.println(data);
		
		data = AES.encryptToBase64("hello", "7Si1APV97dwV24C5");
		System.out.println(data);
	}
}
