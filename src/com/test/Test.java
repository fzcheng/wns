package com.test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import com.util.encrypt.AES;
import com.util.encrypt.EncryUtil;
import com.util.encrypt.RSA;

import cn.game.util.JsonUtil;
import cn.game.util.RandomUtil;
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
		
		String o = "A+B";
		String oo = URLEncoder.encode(o, "utf-8");
		String ooo = URLDecoder.decode("lLfQAxUC3jzykgqL6Ekit5Z4B1ALpDEvIaa2cHIQzuaFuvGrEaO78Md7757O2H6ZvICAXxPipXfpfpPLyc%2BXFIJ%2FUPLBixp%2B6jkWfOmMx1owe%2FD7JJLKLw%2BxeUKSyycdeqQb0WILT2okuaqNncfoOlIt4i%2F3uZ2rG28vxBFheRpEYSCaJfTRdNAUTrrar4hAndcMxjYbMWxRT6AgKnN8nCpHqEAEgiF4SGqLqdBWrgJLaR0MGBM9g0qurk%2B8W1KwKd00NfSPu03KgKP15dA2Ss3zuDVEz5FlB1Gziv%2BMmAQV1Jh5i6gOyXkTo3%2BlFTyf7ATFC0uZ2Z%2FFBzRDPtXczDgCPkgKmtLZKiU7GgNoeqE%3D", "utf-8");
		
		System.out.println(o);
		System.out.println(oo);
		System.out.println(ooo);
	}
}
