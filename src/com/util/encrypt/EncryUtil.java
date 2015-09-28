package com.util.encrypt;

import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;  

public class EncryUtil {

	/**
	 * 生成RSA签名
	 */
	public static String handleRSA(TreeMap<String, Object> map,
			String privateKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String signTemp = sbuffer.toString();

		String sign = "";
		if (StringUtils.isNotEmpty(privateKey)) {
			sign = RSA.sign(signTemp, privateKey);
		}
		return sign;
	}
	
	/**
	 * 生成RSA签名
	 */
	public static String handleRSA(Map<String, Object> map,
			String privateKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String signTemp = sbuffer.toString();

		String sign = "";
		if (StringUtils.isNotEmpty(privateKey)) {
			sign = RSA.sign(signTemp, privateKey);
		}
		return sign;
	}
	
	/**
	 * 生成RSA签名
	 */
	public static String handleRSA(String info,
			String privateKey) {
		
		String signTemp = info;

		String sign = "";
		if (StringUtils.isNotEmpty(privateKey)) {
			sign = RSA.sign(signTemp, privateKey);
		}
		return sign;
	}

	public static boolean checkHandleRSA(String content, String sign, String publicKey) {
		return RSA.checkSign(content, sign, publicKey);
	}
	
//	/**
//	 * 对易宝支付返回的结果进行验签
//	 * 
//	 * @param data
//	 *            易宝支付返回的业务数据密文
//	 * @param encrypt_key
//	 *            易宝支付返回的对ybAesKey加密后的密文
//	 * @param yibaoPublickKey
//	 *            易宝支付提供的公钥
//	 * @param merchantPrivateKey
//	 *            商户自己的私钥
//	 * @return 验签是否通过
//	 * @throws Exception
//	 */
//	public static boolean checkDecryptAndSign(String data, String encrypt_key,
//			String yibaoPublickKey, String merchantPrivateKey) throws Exception {
//
//		/** 1.使用YBprivatekey解开aesEncrypt。 */
//		String AESKey = "";
//		try {
//			AESKey = RSA.decrypt(encrypt_key, merchantPrivateKey);
//		} catch (Exception e) {
//			/** AES密钥解密失败 */
//			e.printStackTrace();
//			return false;
//		}
//
//		/** 2.用aeskey解开data。取得data明文 */
//		String realData = AES.decryptFromBase64(data, AESKey);
//
//		TreeMap<String, String> map = JSON.parseObject(realData,
//				new TypeReference<TreeMap<String, String>>() {
//				});
//
//		/** 3.取得data明文sign。 */
//		String sign = StringUtils.trimToEmpty(map.get("sign"));
//
//		/** 4.对map中的值进行验证 */
//		StringBuffer signData = new StringBuffer();
//		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
//		while (iter.hasNext()) {
//			Entry<String, String> entry = iter.next();
//
//			/** 把sign参数隔过去 */
//			if (StringUtils.equals((String) entry.getKey(), "sign")) {
//				continue;
//			}
//			signData.append(entry.getValue() == null ? "" : entry.getValue());
//		}
//		
//		/** 5. result为true时表明验签通过 */
//		boolean result = RSA.checkSign(signData.toString(), sign,
//				yibaoPublickKey);
//
//		return result;
//	}

	/**
	 * 生成hmac
	 */
	public static String handleHmac(TreeMap<String, String> map, String hmacKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String hmacTemp = sbuffer.toString();

		String hmac = "";
		if (StringUtils.isNotEmpty(hmacKey)) {
			hmac = Digest.hmacSHASign(hmacTemp, hmacKey, Digest.ENCODE);
		}
		return hmac;
	}
	
	public static String Decrypt3DES(String value, String key) throws Exception {  
		  
		byte[] a = Base64.decode(value);
        byte[] b = decryptMode(GetKeyBytes(key), Base64.decode(value));  
  
        return new String(b);  
  
    }  
	
	public static String Encrypt3DES(String value, String key) throws Exception {  
		  
        String str = byte2Base64(encryptMode(GetKeyBytes(key), value.getBytes()));  
  
        return str;  
  
    } 
	
	//计算24位长的密码byte值,首先对原始密钥做MD5算hash值，再用前8位数据对应补全后8位  
	  
    public static byte[] GetKeyBytes(String strKey) throws Exception {  
  
        if (null == strKey || strKey.length() < 1)  
  
            throw new Exception("key is null or empty!");  
  
   
  
        java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5");  
  
        alg.update(strKey.getBytes());  
  
        byte[] bkey = alg.digest();  
  
        System.out.println("md5key.length=" + bkey.length);  
  
        System.out.println("md5key=" + byte2hex(bkey));  
  
        int start = bkey.length;  
  
        byte[] bkey24 = new byte[24];  
  
        for (int i = 0; i < start; i++) {  
  
            bkey24[i] = bkey[i];  
  
        }  
  
        for (int i = start; i < 24; i++) {//为了与.net16位key兼容  
  
            bkey24[i] = bkey[i - start];  
  
        }  
  
   
  
        System.out.println("byte24key.length=" + bkey24.length);  
  
        System.out.println("byte24key=" + byte2hex(bkey24));  
  
        return bkey24;  
  
    }  
  
   
  
    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish         
  
   
  
    //keybyte为加密密钥，长度为24字节  
  
    //src为被加密的数据缓冲区（源）    
  
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {  
  
        try {  
  
            //生成密钥  
  
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); //加密   
  
            Cipher c1 = Cipher.getInstance(Algorithm);  
  
            c1.init(Cipher.ENCRYPT_MODE, deskey);  
  
            return c1.doFinal(src);  
  
       } catch (java.security.NoSuchAlgorithmException e1) {  
  
            e1.printStackTrace();  
  
        } catch (javax.crypto.NoSuchPaddingException e2) {  
  
            e2.printStackTrace();  
  
        } catch (java.lang.Exception e3) {  
  
            e3.printStackTrace();  
  
        }  
  
        return null;  
  
    }  
  
   
  
    //keybyte为加密密钥，长度为24字节    
  
    //src为加密后的缓冲区  
  
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {  
  
        try { //生成密钥     
  
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);  
  
            //解密       
  
            Cipher c1 = Cipher.getInstance(Algorithm);  
  
            c1.init(Cipher.DECRYPT_MODE, deskey);  
  
            return c1.doFinal(src);  
  
        } catch (java.security.NoSuchAlgorithmException e1) {  
  
            e1.printStackTrace();  
  
        } catch (javax.crypto.NoSuchPaddingException e2) {  
  
            e2.printStackTrace();  
  
        } catch (java.lang.Exception e3) {  
  
            e3.printStackTrace();  
  
        }  
  
        return null;  
  
    }  
  
   
  
    //转换成base64编码  
  
    public static String byte2Base64(byte[] b) {  
  
        return Base64.encode(b);  
  
    }  
  
   
  
    //转换成十六进制字符串    
  
    public static String byte2hex(byte[] b) {  
  
        String hs = "";  
  
        String stmp = "";  
  
        for (int n = 0; n < b.length; n++) {  
  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
  
            if (stmp.length() == 1)  
  
                hs = hs + "0" + stmp;  
  
            else  
  
                hs = hs + stmp;  
  
            if (n < b.length - 1)  
  
                hs = hs + ":";  
  
        }  
  
        return hs.toUpperCase();  
  
    }
    
    public static void main(String[] args) {  
    	  
        String key = "abcd1234";  
  
        String password = "password";  
  
        System.out.println("key=" + key + ",password=" + password);  
  
        System.out.println();  
  
        System.out.println("----------示例开始：使用java写的算法加密解密-----------");  
  
       try {  
  
            String encrypt = "";  
  
            String decrypt = "";  
  
           byte[] bkey = EncryUtil.GetKeyBytes(key);  
  
            encrypt = EncryUtil.byte2Base64(EncryUtil.encryptMode(bkey, password.getBytes()));  
  
            System.out.println("用预转换密钥算加密结果=" + encrypt);  
  
            System.out.println("加密后base64表示=" + EncryUtil.byte2hex(Base64.decode(encrypt)));  
  
            System.out.println("调用原始密钥算加密结果=" + EncryUtil.Encrypt3DES(password, key));  
  
   
  
           try {  
  
                decrypt = new String(EncryUtil.decryptMode(bkey, Base64.decode(encrypt)));  
  
                System.out.println("用预转换密钥算解密结果=" + decrypt);  
  
                System.out.println("调用原始密钥算解密结果=" + EncryUtil.Decrypt3DES(encrypt, key));  
  
            } catch (Exception ex) {  
  
                System.out.println("Exception:" + ex.getMessage());  
  
            }  
  
        } catch (Exception ex) {  
  
            System.out.println("Exception:" + ex.getMessage());  
  
        }  
  
        System.out.println("----------示例结束：使用java写的算法加密解密-----------");  
  
    }  
}
