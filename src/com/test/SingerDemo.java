package com.test;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * 数字签名生成与验证demo，包括生成DSA私钥/公钥，生成数字签名，验证数字签名. <li>
 * 订购接口WABP方会将WABP方的公钥，提前提供给各AP，供接口交互时校验WABP发送的数据及签名正确性.<li>
 * 退订接口AP提前将公钥提供给WABP侧，供接口交互时校验AP发送的数据及签名正确性.
 * 
 * @ClassName: SingerDemo
 * @author yanhuajian
 * @Email yanhuajian@aspirecn.com
 * @date:2013-8-20 上午10:11:15
 * @version V1.2
 * @modify 封装生成数字签名、验证数字签名方法，完善注释 yanhuajian 2013-9-6 11:24:15 v1.2
 * 
 */
public class SingerDemo {

	/**
	 * DSA密钥长度，DSA算法的默认密钥长度是1024 密钥长度必须是64的倍数，在512到1024位之间
	 */
	private static final int KEY_SIZE = 1024;
	// 公钥
	private static final String PUBLIC = "DSAPublicKey";
	// 私钥
	private static final String PRIVATE = "DSAPrivateKey";
	// 签名
	private static final String SIGN = "sign";
	// 签名方式
	private static final String SIGN_METHOD = "signMethod";
	// 默认字符编码
	private static final String DEFAULT_CHARSET = "GBK";
	// 默认加密算法
	private static final String DEFAULT_SIGN_METHOD = "DSA";

	public static void main(String[] args) throws Exception {
		// 数字签名
		// String signtest =
		// "MCwCFAcNdT/ii1fkWRbzeRH5yXZ/hd1QAhQwO3K4hdrbnTzK+phIQ7NeK93Ebw==";
		// 公钥
		// String publicKeyStr =
		// "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBANXuKL54pUJLAE2thFIudDtTVG+mKdw0qxDDVPeonrsTrx+3MrqkDNbUFUdgeQrW+KSYSydMgfUkNzvx4Pp1ETh7KZfrCYHJr+tMeC3BpzAHW34or4Ge8GDeBt58Yrx828Cc5pyUWASdruthnck5Ch2lzBE1nvd6qKbXhEtWvWt7";
		/*
		Map<String, String> keyMap = genkeys();
		
		// 取得公钥/私钥
		//Map<String, String> keyMap = genkeys();

		// 模拟数据
		Map<String, Object> map = getDataMap("1000000001", "10959", "31357",
				"31", "10960", "1", "1001057441", "0", "23875310451", "21",
				"2013-09-03 09:50:39", null);
		// 移除非签名参数
		map.remove(SIGN);
		map.remove(SIGN_METHOD);

		// 生成数字签名字符串(用户退订时，将数字签名传给WABP)
		String signStr = buildSign(keyMap.get(PRIVATE), map);

		System.out.println("signStr:"+signStr);
		
		// 验证数字签名(用于订购时，验证WABP的数字签名)
		boolean isSign = verifySign(signStr, keyMap.get(PUBLIC), map);
		System.out.println(isSign);
		*/
		test2();
		test1();
	}
	
	//加密
	public static void test2() throws Exception 
	{
		//String privateKey = "MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoEFgIUakJjPqIb6yYgQWSEEnm20cafCzc=";
		String privateKey = "MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoEFgIUSsNlZLYUGcEy5LKUdrbWMlZHiSs=";
		
		//String stringToSign = "apco=20140911161823104521&aptid=20140911161823104521&aptrid=20140911161823104521&bu=aHR0cDovL3dhYnAubWFnaWNiaXJkcy5jbi93YWJwYXkvY29tcGxldGU/YXB0cmlkPTIwMTQwOTExMTYxODIzMTA0NTIx&ch=10960&ex=10329&mid=IBFICKAFEBGEHZ&sin=fddoz&xid=";
		String stringToSign = "apco=703106&aptid=26466389&aptrid=20150416101602907578&bu=aHR0cDovL3dhYnAubWFnaWNiaXJkcy5jbi93YWJwYXkvY29tcGxldGU/dGlkPTIwMTUwNDE2MTAxNjAyOTA3NTc4&ch=21649&ex=44000&mid=IGFFIKAFIIOFIH&sin=fkiyo&xid=";
		
		// 转换成二进制
		byte[] bytesToSign = stringToSign.getBytes(DEFAULT_CHARSET);
				
		// 初始化DSA签名工具
		Signature sg = Signature.getInstance("DSA");
		// 初始化DSA私钥
		sg.initSign((PrivateKey) getPrivateKey(privateKey));
		sg.update(bytesToSign);

		// 得到二进制形式的签名
		byte[] signBytes = sg.sign();
		// 进行标准Base64编码
		byte[] sign = Base64.encodeBase64(signBytes);
		// 转换成签名字符串
		String signContent = new String(sign);

		System.out.println("the sign content is: " + signContent);
				
	}
	//解密
	public static void test1() throws Exception 
	{
		//params="apco=703106&aptid=23056468&aptrid=20150420164946181462&bu=aHR0cDovL3dhYnAubWFnaWNiaXJkcy5jbi93YWJwYXkvY29tcGxldGU%2FdGlkPTIwMTUwNDIwMTY0OTQ2MTgxNDYy&ch=21649&ex=44000&mid=IGZFBKAFCGCEOB&sin=fkiyd&xid=" sign="MCwCFDecCd2XSD11BoVFtxp8t7OA4jO4AhQbyFNsMNcxhC4RyRAZtzVzaFJfMA=="
		
		String publickey = "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBANbPTDZ/fgUfEQZq8ZbKa/m4smswp4K1w9Dd1QkWCvDt9BF3OKeC+NK6f3QlaWefPfoYinCfPxiKS1vEta6lBuhzKMR1S+Khl72uCceXfSwOMcwsfkx5AUxJ55H+kjZdEhhGNWLmajUj75eTGfeMu+97AbAzBCNPxs4vz9Zq8S3+";
		
		//String singStr = "MCwCFDdhy/HAuSUnpnqZm6ioFR8lKrdlAhQwwDo2HBFoXE/ud/7VwouUJCyqxg==";
		//String stringToSign = "apco=703106&aptid=18392109&aptrid=20150416101615837648&bu=aHR0cDovL3dhYnAubWFnaWNiaXJkcy5jbi93YWJwYXkvY29tcGxldGU/dGlkPTIwMTUwNDE2MTAxNjE1ODM3NjQ4&ch=21649&ex=44000&mid=IGIIEKAFBHIGEC&sin=fkiyo&xid=";
		
		String singStr = "MC4CFQCUmMdZh7Nl+niwMlDwIa0hQcee1AIVAJFfWt8ff8Sgt9Qfv/QiIVdZ43U7";
		String stringToSign = "apco=703106&aptid=25629080&aptrid=20150420171416209896&bu=aHR0cDovL3dhYnAubWFnaWNiaXJkcy5jbi93YWJwYXkvY29tcGxldGU/dGlkPTIwMTUwNDIwMTcxNDE2MjA5ODk2&ch=21649&ex=44000&mid=IBHIOKAFBIHOII&sin=fkiyz&xid=";
				
		/*
		String publickey = "MIIBtzCCASsGByqGSM44BAEwggEeAoGBAJlxxmLUvrtXBBjMscYryR1Rwrke5xNNZJQp9/ynCX2UTjwqFIi+v/2hOu2u2M0b5cNdjKz0R4qE5uwLpkLpIbnt2ySQHx1b0yGSe9n0r5WNwsFmIC64NXGOez9ISpnv/OYZz/SoKPyyVp1P8t6HkfKYzoOO6/DXNmjryHYL06DDAhUAnICKL2SvfXqju9nHJ3+8fpyrh6cCgYAmqizaLNtEYybxhZDwYspTV9tlM9aZdBAgtuOV7TdW+sLgpOr1dKrJdkmMeFnUonKktj+eETCk55OiEVNJvPazyC7qCwBJnXLLKeiqKrsgHlcF0PVqa47CKbM1xpRhgcZAms4J64iq/oPw53K/MyqiPYNi5Q9JwMApIwpoTUFOMQOBhQACgYEAiqlsV3uUmOIvbrkTM5KyOzM4LvfR6gF0DyXQpKsPq8BvzMpDkv3aYWggaPzlrEAcT5LGILuVGcUmWGqvubWBxLJKjKADmViGSBfK03dakqmAZxdpwU43scuu2WXEx27DOyuJcYEWlMaW1FhlKVMazXvJ9FmKGQp5/HDdUoCSYhs=";
		String singStr = "MCwCFCajFAbcVLx7zgr/ULltFaMPSjpeAhRmeJRbiQrf8JpUws58VIPE0ZD3sg==";
		// 将map转换为url参数形式
		String stringToSign = "apco=20140910172953896063&aptid=20140910172953896063&aptrid=20140910172953896063&bu=aHR0cDovL3dhYnAubWFnaWNiaXJkcy5jbi93YWJwYXkvY29tcGxldGU/YXB0cmlkPTIwMTQwOTEwMTcyOTUzODk2MDYz&ch=10960&ex=10329&mid=IBFICKAFEBGEHZ&sin=fddoz&xid=";
		*/
		/*
		String publickey = "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAImMdwBp0912WKCTKKiN1W+GmmRU4iW8Y9INeSYW/HmnVjI8qzNML7Ztl1YdB6e03mnDzDKnMeM8nhCjTW2Wgob09tY8ZI3OOMlLXqrLTavbDomokhD18GKUIk7Wn1t6SGWX5SOISMgjZcmelqQvPFYhgNIEjUCkfuikNsPlNnN+";
		String singStr = "MCwCFCPd/FSF2tVHsf0uTSVuH+gh/cbHAhQUOLFVHtCnN0nYj59XDJbVlHnHsQ==";
		// 将map转换为url参数形式
		String stringToSign = "APContentId=1&APId=10959&APTransactionID=1000000001&APUserId=1001057441&Actiontime=2013-09-03 09:50:39&Backup1=null&Backup2=null&ChannelId=10960&Msisdn=23875310451&OrderType=0&Province=21&ServiceId=31357&ServiceType=31&method=null";
		*/
		System.out.println("stringToSign:"+stringToSign);
		// 将参数字符串转换成二进制
		byte[] bytesToSign = stringToSign.getBytes(DEFAULT_CHARSET);
		System.out.println(bytesToSign);
		// 将数字签名符串转换成二进制
		byte[] signBytes = Base64.decodeBase64(singStr.getBytes(DEFAULT_CHARSET));

		// 初始化DSA签名工具
		Signature sg = Signature.getInstance(DEFAULT_SIGN_METHOD);
		// 初始化DSA私钥
		sg.initVerify(getPublicKey(publickey));
		sg.update(bytesToSign);
		// 验证签名
		boolean status = sg.verify(signBytes);
		System.out.println("verify result：" + status);
	}
	
	/**
	 * 
	 * 生成数字签名字符串
	 * 
	 * @Title: buildSign
	 * @param privateKey
	 *            私钥
	 * @param data
	 *            待校验数据
	 * @return
	 * @throws Exception
	 * @author: yanhuajian 2013-9-6上午10:37:30
	 */
	public static String buildSign(String privateKey, Map<String, Object> data)
			throws Exception {

		// 按照标准url参数的形式组装签名源字符串
		String stringToSign = map2String(data);
		
		// 转换成二进制
		byte[] bytesToSign = stringToSign.getBytes(DEFAULT_CHARSET);

		// 初始化DSA签名工具
		Signature sg = Signature.getInstance("DSA");
		// 初始化DSA私钥
		sg.initSign((PrivateKey) getPrivateKey(privateKey));
		sg.update(bytesToSign);

		// 得到二进制形式的签名
		byte[] signBytes = sg.sign();
		// 进行标准Base64编码
		byte[] sign = Base64.encodeBase64(signBytes);
		// 转换成签名字符串
		String signContent = new String(sign);

		System.out.println("the sign content is: " + signContent);

		return signContent;
	}

	/**
	 * 
	 * 根据数字签名和公钥验证签名是否正确
	 * 
	 * @Title: verifySign
	 * 
	 * @param sign
	 *            数字签名
	 * @param publicKey
	 *            公钥
	 * @param data
	 *            待校验数据
	 * @return
	 * @throws Exception
	 * @author: yanhuajian 2013-9-6上午10:37:55
	 */
	public static boolean verifySign(String sign, String publicKey,
			Map<String, Object> data) throws Exception {
		// 将map转换为url参数形式
		String stringToSign = map2String(data);
		System.out.println("stringToSign:"+stringToSign);
		// 将参数字符串转换成二进制
		byte[] bytesToSign = stringToSign.getBytes(DEFAULT_CHARSET);
		System.out.println(bytesToSign);
		// 将数字签名符串转换成二进制
		byte[] signBytes = Base64.decodeBase64(sign.getBytes(DEFAULT_CHARSET));

		// 初始化DSA签名工具
		Signature sg = Signature.getInstance(DEFAULT_SIGN_METHOD);
		// 初始化DSA私钥
		sg.initVerify(getPublicKey(publicKey));
		sg.update(bytesToSign);
		// 验证签名
		boolean status = sg.verify(signBytes);
		System.out.println("verify result：" + status);

		return status;
	}

	/**
	 * 通过公钥字符串初始化DSA的公钥
	 * 
	 * @return
	 * @throws Exception
	 */
	private static PublicKey getPublicKey(String publicKeyStr) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_SIGN_METHOD);
		EncodedKeySpec keySpec = new X509EncodedKeySpec(
				Base64.decodeBase64(publicKeyStr.getBytes(DEFAULT_CHARSET)));
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * 通过私钥字符串初始化DSA的私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey getPrivateKey(String privateKeyStr)
			throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_SIGN_METHOD);
		EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
				Base64.decodeBase64(privateKeyStr.getBytes(DEFAULT_CHARSET)));
		return keyFactory.generatePrivate(keySpec);
	}

	/**
	 * 初始化密钥对
	 * 
	 * @return Map 甲方密钥的Map
	 * */
	public static Map<String, Key> initKey() throws Exception {
		// 实例化密钥生成器
		KeyPairGenerator keyPairGenerator = KeyPairGenerator
				.getInstance(DEFAULT_SIGN_METHOD);
		// 初始化密钥生成器
		keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
		// 生成密钥对
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 甲方公钥
		DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();
		// 甲方私钥
		DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();

		// 将密钥存储在map中
		Map<String, Key> keyMap = new HashMap<String, Key>();
		keyMap.put(PUBLIC, publicKey);
		keyMap.put(PRIVATE, privateKey);
		return keyMap;

	}

	/**
	 * 
	 * 通过公钥/私钥(KEY)生成对应的字符串
	 * 
	 * @Title: genkeys
	 * @return
	 * @throws Exception
	 * @author: yanhuajian 2013-9-6上午10:33:31
	 */
	public static Map<String, String> genkeys() throws Exception {
		// 初始化密钥
		// 生成密钥对
		Map<String, Key> keyMap = initKey();
		// 公钥
		Key publickey = (Key) keyMap.get(PUBLIC);
		Key privatekey = (Key) keyMap.get(PRIVATE);

		String publicStr = new String(Base64.encodeBase64(publickey
				.getEncoded()));
		String privateStr = new String(Base64.encodeBase64(privatekey
				.getEncoded()));
		System.out.println("公钥：" + publicStr);
		System.out.println("私钥：" + privateStr);

		Map<String, String> map = new HashMap<String, String>();
		map.put(PUBLIC, publicStr);
		map.put(PRIVATE, privateStr);
		return map;
	}

	/**
	 * 
	 * 将map转换为url格式字符串
	 * 
	 * @Title: map2String
	 * @param map
	 * @return
	 * @author: yanhuajian 2013-7-21下午7:25:08
	 */
	public static String map2String(Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(entry.getKey()).append("=").append(entry.getValue());
		}

		return sb.toString();
	}

	/**
	 * 简单组装map(未做封装)
	 */
	public static Map<String, Object> getDataMap(String apTransactionID,
			String apId, String serviceId, String serviceType,
			String channelId, String apContentId, String apUserId,
			String orderType, String msisdn, String province,
			String actiontime, String method) {

		Map<String, Object> map = new TreeMap<String, Object>(); // 用treeMap按照key做排序
		map.put("APTransactionID", apTransactionID);
		map.put("APId", apId);
		map.put("ServiceId", serviceId);
		map.put("ServiceType", serviceType);
		map.put("ChannelId", channelId);
		map.put("APContentId", apContentId);
		map.put("APUserId", apUserId);
		map.put("OrderType", orderType);
		map.put("Msisdn", msisdn);
		map.put("Province", province);
		map.put("Actiontime", actiontime);
		map.put("method", method);
		map.put("Backup1", null);
		map.put("Backup2", null);

		return map;
	}

}
