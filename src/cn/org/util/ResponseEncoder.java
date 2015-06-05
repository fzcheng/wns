package cn.org.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.TreeMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.game.config.ServerKey;
import cn.game.util.JacksonUtil;
import cn.game.util.JsonUtil;
import cn.game.util.RandomUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.util.encrypt.AES;
import com.util.encrypt.EncryUtil;
import com.util.encrypt.RSA;

/**
 * 响应客户端编码
 * @author thomas.lei
 * 12:49:38 PM
 */
public abstract class ResponseEncoder {
	
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	
	protected DataBuffer buffer;
	
	int result;
	String clientPublicKey = "";
	
	public ResponseEncoder(HttpServletRequest request, HttpServletResponse response)
	{
		result = 0;
		
		//response.setContentType("application/octet-stream");
		response.setContentType("text/xml;charset=UTF-8");
		this.response=response;
		this.request=request;
		
		this.response.addHeader("servertime", "" + System.currentTimeMillis() / 1000);
		this.buffer=DataBuffer.allocate(100);
		
		clientPublicKey = (String) request.getAttribute("clientPublicKey");
	}

	public void setResult(int result)
	{
		this.result = result;
	}
	
	public int getResult()
	{
		return this.result;
	}
	
	public void setInt(int digital)
	{
		buffer.putInt(digital);
	}
	
	public void setFloat(float digital)
	{
		buffer.putFloat(digital);
	}
	
	public void setDouble(double digital)
	{
		buffer.putDouble(digital);
	}
	
	public void setString(String message)
	{
		buffer.putPrefixedString(message);
	}
	
	public void setClientPublicKey(String key)
	{
		clientPublicKey = key;
	}
	
//	public String getSign()
//	{
//		return "fEs6tfmaLF7hprXDmst3sDkX7sjf9EScYY/lboOke/YFZFU9ZcxX+mTV00eRXGh5fvX+9TklDpUWCk/HJMsCB8Lz9rucyVhxc401XGX5wkNlc69o9Avq3rUM+vaXs33O87qGBj+gOEEVW4mqadF3c9Jgx/92tVNUhhIsapZiw9E=";
//	}
	
	@JsonIgnore
	public abstract String getJsonStr();
	
//	public void write()
//	{
//
//		try 
//		{
////			buffer.clear();
////			setString(getJsonStr());
////			
////			buffer.flip();
////			byte[] b=new byte[buffer.limit()];
////			buffer.get(b);
////			response.getOutputStream().write(b);
//			
//			//response.setCharacterEncoding("utf-8");
//			ServletOutputStream out = response.getOutputStream();
//			//System.out.println("resp encode:" + response.getCharacterEncoding());
//			response.setCharacterEncoding("utf-8");
//			String jsonstr = getJsonStr();
//			System.out.println("jsonstr:" + jsonstr);
////			byte[] b = utf2Bytes(jsonstr);
////			String bb = new String(b, "utf-8");
////			System.out.println("jsonstr_2:" + bb);
//			
//			//做加密  以及签名
//			TreeMap<String,Object> map = JsonUtil.getMap4Json(jsonstr);
//			String oinfo = StringUtil.getFromMap(map);
//			// 生成RSA签名
//			String osign = EncryUtil.handleRSA(oinfo, ServerKey.serverPrivateKey);
//			map.put("sign", osign);
//			System.out.println("sign:" + osign);
//			
////			String tinfo = JsonUtil.getJsonStringFromMap(map);
////			
////			//System.out.println("tinfo:" + tinfo);
////			TreeMap<String,Object> mmmap = JsonUtil.getMap4Json(tinfo);
////			String mminfo = StringUtil.getFromMap(mmmap, "sign");
////			//System.out.println("mminfo:" + mminfo);
////			String sign = EncryUtil.handleRSA(mminfo, ServerKey.serverPrivateKey);
////			map.put("sign", sign);
//			
//			//String info = JsonUtil.getJsonStringFromMap(map);
//			String info = jsonstr;
//			// 商户自己随机生成的AESkey
//			String merchantAesKey = RandomUtil.getRandom(16);
//		
//			// 生成data
//			String data = AES.encryptToBase64(info, merchantAesKey);
//						
//			// 使用RSA算法将商户自己随机生成的AESkey加密
//			String encryptkey = RSA.encrypt(merchantAesKey, clientPublicKey);
//			
//			//System.out.println("oinfo:" + oinfo);
//			//System.out.println("sign:" + sign);
//			System.out.println("info:" + info);
//			String resultStr = "{\"data\":\""+data+"\",\"key\":\""+encryptkey+"\"}";
//			//System.out.println("resultStr:" + resultStr);
//			
//			out.write(utf2Bytes(resultStr));
//			out.flush();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
	
	public void write()
	{
		try 
		{
//			buffer.clear();
//			setString(getJsonStr());
//			
//			buffer.flip();
//			byte[] b=new byte[buffer.limit()];
//			buffer.get(b);
//			response.getOutputStream().write(b);
			
			//response.setCharacterEncoding("utf-8");
			ServletOutputStream out = response.getOutputStream();
			//System.out.println("resp encode:" + response.getCharacterEncoding());
			response.setCharacterEncoding("utf-8");
			String jsonstr = getJsonStr();
			System.out.println("jsonstr:" + jsonstr);
//			byte[] b = utf2Bytes(jsonstr);
//			String bb = new String(b, "utf-8");
//			System.out.println("jsonstr_2:" + bb);
			
			//做加密  以及签名
			TreeMap<String,Object> map = JsonUtil.getMap4Json(jsonstr);
			String ooinfo = StringUtil.getFromMap(map);
//			System.out.println("ooinfo:" + ooinfo);
			
			// 生成RSA签名
			String osign = EncryUtil.handleRSA(ooinfo, ServerKey.serverPrivateKey);
			map.put("sign", osign);
			String tinfo = JsonUtil.getJsonStringFromMap(map);
//			System.out.println("tinfo:" + tinfo);
			
			TreeMap<String,Object> mmmap = JsonUtil.getMap4Json(tinfo);
			String mminfo = StringUtil.getFromMap(mmmap, "sign");
			//System.out.println("mminfo:" + mminfo);
			String sign = EncryUtil.handleRSA(mminfo, ServerKey.serverPrivateKey);
			map.put("sign", sign);
			mmmap.put("sign", sign);
			
			String ttinfo = JsonUtil.getJsonStringFromMap(mmmap);
//			System.out.println("ttinfo:" + ttinfo);
//			//做加密  以及签名
//			TreeMap<String,Object> map = JacksonUtil.getMap4Json(jsonstr);
//			String oinfo = StringUtil.getFromMap(map, "sign");
//			String sign = EncryUtil.handleRSA(oinfo, ServerKey.serverPrivateKey);
//			map.put("sign", sign);
			
			String info = JsonUtil.getJsonStringFromMap(map);
			//String info = ttinfo;
			// 商户自己随机生成的AESkey
			String merchantAesKey = RandomUtil.getRandom(16);
		
			// 生成data
			String data = AES.encryptToBase64(info, merchantAesKey);
						
			// 使用RSA算法将商户自己随机生成的AESkey加密
			String encryptkey = RSA.encrypt(merchantAesKey, clientPublicKey);
			
			//System.out.println("oinfo:" + oinfo);
			//System.out.println("sign:" + sign);
			System.out.println("info:" + info);
			String resultStr = "{\"data\":\""+data+"\",\"key\":\""+encryptkey+"\"}";
			//System.out.println("resultStr:" + resultStr);
			
			out.write(utf2Bytes(resultStr));
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static byte[] utf2Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new byte[0];
		}
	}
}
