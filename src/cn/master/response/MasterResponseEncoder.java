package cn.master.response;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;

import cn.org.util.HashHex;

/**
 * 响应客户端编码
 * @author thomas.lei
 * 12:49:38 PM
 */
public abstract class MasterResponseEncoder {
	
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	
	byte[] byteArray;
	public static final String SIGN_KEY = "e759385a9e3d42b18748abd58fa56c06";
	
	public MasterResponseEncoder(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("application/octet-stream");
		this.response=response;
		this.request=request;
		
		this.response.setHeader("servertime", "" + System.currentTimeMillis() / 1000);
		this.response.setHeader("errorCode", "0");
	}

//	public String getSign()
//	{
//		return "fEs6tfmaLF7hprXDmst3sDkX7sjf9EScYY/lboOke/YFZFU9ZcxX+mTV00eRXGh5fvX+9TklDpUWCk/HJMsCB8Lz9rucyVhxc401XGX5wkNlc69o9Avq3rUM+vaXs33O87qGBj+gOEEVW4mqadF3c9Jgx/92tVNUhhIsapZiw9E=";
//	}
	
	public void write()
	{
		try 
		{
			//把sign写在header中
			String sign = HashHex.HashToMD5Hex(getSignByteArray());
			response.setHeader("sign", sign);
			
			ServletOutputStream out = response.getOutputStream();
			out.write(getByteArray());
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] getSignByteArray() {

		String respcmd = (String)request.getAttribute("respcmd");
		byte[] b = ArrayUtils.addAll(byteArray, (respcmd + SIGN_KEY).getBytes());
		return b;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}
}
