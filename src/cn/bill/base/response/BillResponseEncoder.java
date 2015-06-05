package cn.bill.base.response;

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
import cn.org.util.DataBuffer;
import cn.org.util.StringUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.util.encrypt.AES;
import com.util.encrypt.EncryUtil;
import com.util.encrypt.RSA;

/**
 * 响应客户端编码
 * @author thomas.lei
 * 12:49:38 PM
 */
public abstract class BillResponseEncoder {
	
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	
	protected DataBuffer buffer;
	
	int result;
	
	public BillResponseEncoder(HttpServletRequest request, HttpServletResponse response)
	{
		result = 0;
		
		//response.setContentType("application/octet-stream");
		response.setContentType("text/xml;charset=UTF-8");
		this.response=response;
		this.request=request;
		
		this.response.addHeader("servertime", "" + System.currentTimeMillis() / 1000);
		this.buffer=DataBuffer.allocate(100);
		
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
	
//	public String getSign()
//	{
//		return "fEs6tfmaLF7hprXDmst3sDkX7sjf9EScYY/lboOke/YFZFU9ZcxX+mTV00eRXGh5fvX+9TklDpUWCk/HJMsCB8Lz9rucyVhxc401XGX5wkNlc69o9Avq3rUM+vaXs33O87qGBj+gOEEVW4mqadF3c9Jgx/92tVNUhhIsapZiw9E=";
//	}
	
	@JsonIgnore
	public abstract String getJsonStr();
	
	public void write()
	{
		try 
		{
			//response.setCharacterEncoding("utf-8");
			ServletOutputStream out = response.getOutputStream();
			//System.out.println("resp encode:" + response.getCharacterEncoding());
			response.setCharacterEncoding("utf-8");
			String jsonstr = getJsonStr();
			System.out.println("jsonstr:" + jsonstr);			
			
			out.write(utf2Bytes(jsonstr));
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
