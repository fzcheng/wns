package cn.org.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class HashHex {

	public static String HashToMD5Hex(String sourceStr) {
		String signStr = ""; 
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if (md5Byte != null) {
				signStr = HexBin.encode(md5Byte);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signStr;
	}
	
	public static String HashToSHA1(String sourceStr) {
		String signStr = ""; 
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("SHA-1");
			md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if (md5Byte != null) {
				signStr = HexBin.encode(md5Byte);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signStr;
	}
	
	public static void main(String[] args) throws Exception {
		String result = HashToMD5Hex("最新的测试结果表明，IE9的预览版本已经完全支持W3C Web Standards HTML5和CSS3。");
		System.out.println(result);
		
		String result1 = HashToSHA1("可以加密汉字.Oh,and english");
		System.out.println(result1);
	}
}
