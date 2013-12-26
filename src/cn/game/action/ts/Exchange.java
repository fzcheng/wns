package cn.game.action.ts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.game.dao.ts.TsCodeDAO;
import cn.game.vo.ts.TsCode;
import cn.org.util.SpringUtils;

/**
 * 兑换码兑换金币
 * @author a
 *
 */
public class Exchange extends javax.servlet.http.HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2942001754222721676L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.err.println("Exchange : doGet");
		request.setCharacterEncoding("gb2312");

		String resultstr = "";
		
		String code = request.getParameter("code");
		TsCodeDAO tscodedao = (TsCodeDAO)SpringUtils.getBean("tscodedao");
		
		TsCode tscode = tscodedao.getByCode(code);
		
		if(tscode == null || tscode.getFlag() == 1)
		{
			resultstr = "兑换码错误，或者已被使用。";
		}
		else
		{
			resultstr = "OK";
			tscode.setFlag(1);
			tscodedao.update(tscode);
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

}
