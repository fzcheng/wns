package cn.game.pay.sina;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.action.DispatchTool;
import cn.game.service.ReturnMessage;
import cn.game.service.record.PayRecordService;
import cn.game.vo.record.PayrecordVO;
import cn.org.util.HashHex;
import cn.org.util.SpringUtils;

public class SinaWEICallback extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6149692584002815596L;
	private final static Logger logger = Logger.getLogger(SinaWEICallback.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		DispatchTool tool = new DispatchTool();
        tool.changeMethod("comd", SinaWEICallback.class,request, response);
        
		System.err.println("SinaWEICallback : doGet");
		request.setCharacterEncoding("gb2312");
		int result = 0;
		String resultstr = "";
		
		SinaWEIPayVO v = new SinaWEIPayVO(request);
		//验证
		StringBuffer signData = new StringBuffer();
		Map m = request.getParameterMap();
		Iterator<String> iter = m.keySet().iterator();
		while(iter.hasNext())
		{
			String key = iter.next();
			
			if(key.equals("signature"))
			{
				continue;
			}
			
			String value = request.getParameter(key);
			
			System.out.println(""+key+"|"+value);
			signData.append(""+key+"|"+value+"|");
		}
		signData.append("appkey");
		//sha1
		String signature = HashHex.HashToSHA1(signData.toString());
		if(signature.toLowerCase().equals(v.getSignature()))
		{
			System.out.println("signature match");
			//签名匹配
			PayrecordVO payrecord = new PayrecordVO(v);
			PayRecordService payrecordservice = (PayRecordService)SpringUtils.getBean("payrecordservice");
			ReturnMessage rm = payrecordservice.savepayrecord(payrecord);
			
			if(rm.isResult())
			{
				result = 1;
			}
		}
		else
		{
			System.out.println("signature unmatch:" + signature + "|" + v.getSignature());
			//签名不匹配
		}
		
		if(result == 1)
		{
			resultstr = "OK";
		}
		else
		{
			resultstr = "failed";
		}
		System.err.println("resultstr =="+ resultstr);
		
		response.setStatus(200);
		ServletOutputStream out = response.getOutputStream();
		out.write(utf2Bytes(resultstr));
		out.flush();
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
		System.err.println("SinaWEICallback : doPost");
	}
	
	public void getrecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
//		response.setCharacterEncoding("GBK");
		System.out.println("come getrecord Method");
	}
}
