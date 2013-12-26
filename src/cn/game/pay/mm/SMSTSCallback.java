package cn.game.pay.mm;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;

import cn.game.util.XmlUtil;

public class SMSTSCallback extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6149692584002815596L;
	private final static Logger logger = Logger.getLogger(SMSTSCallback.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.warn("SMSTSCallback doget");
	}
		
	
	public static byte[] utf2Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new byte[0];
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.warn("SMSTSCallback dopost");
		
		MM_PayResultVO payresultvo = new MM_PayResultVO();
		int result = 0;
		
		try {
			InputStream in = request.getInputStream();
			
			Document document = XmlUtil.parserXml(in);
			in.close();
			System.out.println(document.asXML());
			
			MM_PayVO payvo = new MM_PayVO(document);
			
			payresultvo.setTransIDO(payvo.getTransIDO());
			
//			System.out.println(resultstr);
		}catch(Exception e){
			e.printStackTrace();

		}
		
		if(result == 1)
		{
			payresultvo.setHRet("0");
			payresultvo.setMessage("Success");
		}
		else
		{
			payresultvo.setHRet("" + result);
			payresultvo.setMessage("Fail");
		}
		
		System.out.println(payresultvo.getXmlData());
		ServletOutputStream out = response.getOutputStream();
		out.write(utf2Bytes(payresultvo.getXmlData()));
		out.flush();
	}
}
