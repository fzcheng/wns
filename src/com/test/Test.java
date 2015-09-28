package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.DocumentException;

import com.util.encrypt.AES;
import com.util.encrypt.EncryUtil;
import com.util.encrypt.RSA;

import cn.game.util.JsonUtil;
import cn.game.util.RandomUtil;
import cn.org.util.HashHex;
import cn.org.util.StringUtil;


public class Test {

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
//		Test t = new Test();
//		t.createKey();
//		t.createKey();
		
//		Map<String, Object> map = new TreeMap<String, Object>();
//		map.put("gameId", "10001");
//		map.put("comd", "getrecord");
//		map.put("mtId", "324123214");
//		map.put("recorddata", "recorddata");
//		
//		String oinfo = StringUtil.getFromMap(map, "sign");
//		// 生成RSA签名
//		String sign = EncryUtil.handleRSA(oinfo, clientPrivateKey);
//
//		map.put("sign", sign);
//		
//		//String info = JsonUtil.getJsonStringFromMap(map);
//		String info = JsonUtil.getJsonStringFromMap(map);
//		// 商户自己随机生成的AESkey
//		String merchantAesKey = RandomUtil.getRandom(16);
//	
//		// 生成data
//		String data = AES.encryptToBase64(info, merchantAesKey);
//
//		// 使用RSA算法将商户自己随机生成的AESkey加密
//		String encryptkey = RSA.encrypt(merchantAesKey, serverPublicKey);
//		
//		System.out.println("oinfo:"+oinfo);
//		System.out.println("info:"+info);
//		System.out.println("data:"+data);
//		System.out.println("merchantAesKey:"+merchantAesKey);
//		System.out.println("encryptkey:"+encryptkey);
//		
//		
//		String dmerchantAesKey = RSA.decrypt(encryptkey, serverPrivateKey);
//		String dinfo = AES.decryptFromBase64(data, dmerchantAesKey);
//		TreeMap<String, Object> dmap = JsonUtil.getMap4Json(dinfo);
//		String doinfo = StringUtil.getFromMap(dmap, "sign");
//		String dsign = sign;
//		boolean matched = EncryUtil.checkHandleRSA(doinfo, dsign, clientPublicKey);
//		
//		System.out.println("DmerchantAesKey:"+dmerchantAesKey);
//		System.out.println("Dinfo:"+dinfo);
//		System.out.println("Doinfo:"+doinfo);
//		System.out.println("matched:"+matched);
		
//		String o = "A+B";
//		String oo = URLEncoder.encode(o, "-8");
//		String ooo = URLDecoder.decode("lLfQAxUC3jzykgqL6Ekit5Z4B1ALpDEvIaa2cHIQzuaFuvGrEaO78Md7757O2H6ZvICAXxPipXfpfpPLyc%2BXFIJ%2FUPLBixp%2B6jkWfOmMx1owe%2FD7JJLKLw%2BxeUKSyycdeqQb0WILT2okuaqNncfoOlIt4i%2F3uZ2rG28vxBFheRpEYSCaJfTRdNAUTrrar4hAndcMxjYbMWxRT6AgKnN8nCpHqEAEgiF4SGqLqdBWrgJLaR0MGBM9g0qurk%2B8W1KwKd00NfSPu03KgKP15dA2Ss3zuDVEz5FlB1Gziv%2BMmAQV1Jh5i6gOyXkTo3%2BlFTyf7ATFC0uZ2Z%2FFBzRDPtXczDgCPkgKmtLZKiU7GgNoeqE%3D", "utf-8");
//		
//		System.out.println(o);
//		System.out.println(oo);
//		System.out.println(ooo);
		
//		String t = "usercode=13073369125&serviceid=150122084633&cporderid=20150123085245647358&operationtype=3&channelid=00023381&version=1.0&time=20150126055717";
//		String tt = CryptUtil.encryptBy3DesAndBase64(t, "2h1U2oW1dXnEarGHKrnWfNFk", "utf-8");
//		System.out.println(tt);
//		
////		byte[] ttt = CryptUtil.encryptBy3Des(t.getBytes(), "2h1U2oW1dXnEarGHKrnWfNFk");
////		String ggg = Base64.encode(ttt);
////		System.out.println(ggg);
//		
//		System.out.println(CryptUtil.decryptBy3DesAndBase64(tt,
//				"2h1U2oW1dXnEarGHKrnWfNFk", "utf-8"));
////		System.out.println(CryptUtil.decryptBy3DesAndBase64("0SPyfexP/QDCxU/JkB7LKaLlV9qqgs94IyNL+WJ0U6ImW7n7MtDWJk61yCI+9xKDiEpJI/VEWVUrqGLpd2bccFyGGecTBZATh/PQGZZhgBz+Qccp3L+aIIMAOEcqw95iUhJIWDi+iehuPIFmFel9apn1po7Kjp4lwX8hCvA5MrVZhrl2k71OWgf2W/lrPPIW",
////				"Subs123", "utf-8"));
////		
////		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><request><body><Payid>11795134702</Payid><Price>400</Price><PutChannelID>2094</PutChannelID><Version>1.0.0</Version><Appid>117951347</Appid><UA>h60</UA><imsi>460008333182755</imsi></body></request>";
////		String ss = sendHttp(xml, "http://umpay.vspace.net.cn:9180/umpay/fmmcsspchanneloff");
////		System.out.println("ss:" + ss);
//		
//		
//		String f = URLEncoder.encode("渠道运营日统计", "utf-8");
//		System.out.println("f:" + f);
//		//testUmpaychannel();
//		
//		System.out.println(URLDecoder.decode("mbs%40admin", "utf-8"));
		
		System.out.println(URLDecoder.decode("%E6%88%90%E5%8A%9F", "utf-8"));
		
		//String signData = "app_id=246917aeab8411e4b345c6a10b512583&app_orderid=20150630104137391617&ch_type=37&is_monthly=0&merc_id=2000029&merc_key=5598531e2dbc664be50d88fc56e4a840&orderid=20150630104137391617&pay_amount=200&pay_time=20150630104202&phone=13818365949&rec_amount=200&status=1&time=1435632122&userid=14679060";
		
		String signData = "app_id=101&channelid=1000&comd=getmsg&extra=2015081000000203&fee_code=101001&orderid=2015081000000203&url=http://114.113.159.171:8088/statsynd/interface/zhangmu/ctcc.jsp&key=d4df4f41b8924964b8785ea35e117d6d";
		String sign = "3263845f0db5fdf926eb2d0da9406566";
		String MAC = HashHex.HashToMD5Hex(signData);
		
		System.out.println("signData:"+signData);
		System.out.println("MAC:"+MAC);
	}
	
	public static void testUmpaychannel() throws DocumentException {

		StringBuffer sb = new StringBuffer();
		//sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><request><body><Payid>11795134702</Payid><Price>400</Price><PutChannelID>2094</PutChannelID><Version>1.0.0</Version><Appid>117951347</Appid><UA>h60</UA><imsi>460008333182755</imsi></body></request>");
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><request><body><Payid>11795134702</Payid><Price>400</Price><PutChannelID>2094</PutChannelID><Version>1.0.0</Version><Appid>117951347</Appid><UA>h60</UA><imsi>460008333182755</imsi></body></request>");
		System.out.println("请求地址：" + "http://umpay.vspace.net.cn:9180/umpay/fmmcsspchanneloff ");
		String xml = sb.toString();
		System.out.println("请求XML信息：" + xml);
		long startTime = System.currentTimeMillis();

		String resultXml = sendHttp2(xml, "http://umpay.vspace.net.cn:9180/umpay/fmmcsspchanneloff ");
		System.out.println("返回的信息resultXml:" + resultXml);
		long endtime = System.currentTimeMillis();
		System.out.println("startTime:" + startTime + "  endtime：" + endtime + "  一起执行时间:" + (endtime - startTime));

	}
	
	public static String sendHttp2(String xml, String httpurl) {
		URL url;
		StringBuffer returnxml = new StringBuffer();

		try {
			url = new URL(httpurl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			//conn.setRequestProperty("remote-host-c", "211.138.100.178");
			conn.setRequestProperty("remote-host-c", "116.237.57.138");
			//String timeout = XmlTool.getInstance().getValue("timeout");
			String timeout = "3000";
			conn.setConnectTimeout(Integer.parseInt(timeout));
			conn.setReadTimeout(Integer.parseInt(timeout));
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.getOutputStream().write(xml.getBytes("UTF-8"));
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
			InputStream in = conn.getInputStream();
			InputStreamReader ireader = new InputStreamReader(in, "UTF-8");
			java.io.BufferedReader reader = new java.io.BufferedReader(ireader);
			String s = "";
			while ((s = reader.readLine()) != null) {
				returnxml.append(s + "\n");
			}
			reader.close();
		} catch (MalformedURLException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
		return returnxml.toString().trim();
	}
	
	public static String sendHttp(String xml, String httpurl) {
		URL url;
		StringBuffer returnxml = new StringBuffer();

		try {
			url = new URL(httpurl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(0);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
                //如果是服务器请求必须添加remote-host-c，如果用手机请求不需要添加这个参数
			//conn.setRequestProperty("remote-host-c", "218.80.198.203");
			conn.setRequestProperty("remote-host-c", "116.237.57.138");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.getOutputStream().write(xml.getBytes("UTF-8"));
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
			InputStream in = conn.getInputStream();
			InputStreamReader ireader = new InputStreamReader(in, "UTF-8");
			java.io.BufferedReader reader = new java.io.BufferedReader(ireader);
			String s = "";
			while ((s = reader.readLine()) != null) {
				returnxml.append(s + "\n");
			}
			reader.close();
		} catch (MalformedURLException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
		return returnxml.toString();
	}
}
