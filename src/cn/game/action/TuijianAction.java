package cn.game.action;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class TuijianAction extends DispatchAction {
	private Connection conn;
	private String path;
	private String filename;

	

	// 如果Struts动作不加file请求参数，则通过execute方法将指定目录中文件列表输出到客户端
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		response.setStatus(302);
//		response.setHeader("Location", "http://feimiao.magicbirds.cn/tuijian/home_weix.html");
//		//User-Agent
//		//Mozilla/5.0 (Linux; U; Android 4.1.1; zh-cn; GT-N7100 Build/JRO03C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MicroMessenger/5.2.380
//		String ua = request.getHeader("User-Agent");
//		System.out.println("getStreamInfo:" + ua);
//
//		ServletOutputStream out = response.getOutputStream();
//		out.write(utf2Bytes("The actual URL is 'http://feimiao.magicbirds.cn/tuijian/home_weix.html'."));
//		out.flush();
		String ua = request.getHeader("User-Agent");
		if(ua.contains("MicroMessenger"))
			request.getRequestDispatcher("home_weix.html").forward(request, response);
		else
			request.getRequestDispatcher("home.html").forward(request, response);
		
		return null;
	}
	
	public static byte[] utf2Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new byte[0];
		}
	}
}
