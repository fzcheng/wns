package cn.game.action.smspush;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.game.action.BaseAction;
import cn.game.request.ErrorResponse;
import cn.game.request.SmsPushResponse;

import com.sms.uniproud.ClientMain;

public class PushSms extends javax.servlet.http.HttpServlet{

	private final static Logger logger = Logger.getLogger(PushSms.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.warn("PushSms doget");
		
		try {
			String gameid = request.getParameter("gameid");
			String channelid = request.getParameter("channelid");
			String telnum = request.getParameter("telnum");
			
			int pushflag = ClientMain.getInstance().sendSms(telnum, "欢迎下载桃色恋人2：http://apk.mmarket.com/rs/publish/prepublish4/23/2013/11/13/a116/611/31611116/myheartmm1113.apk");
			
			ServletOutputStream out = response.getOutputStream();
			out.write(utf2Bytes("{\"result\":0, \"pushflag\":" + pushflag + "}"));
			out.flush();
		} catch (Exception e) {
			// 如果上述操作处理出错返回错误消息
			e.printStackTrace();
			logger.error(e.getMessage());
			
			ServletOutputStream out = response.getOutputStream();
			out.write(utf2Bytes("{\"result\":0, \"pushflag\":－10}"));
			out.flush();
		}
	}
	public static byte[] utf2Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new byte[0];
		}
	}
	/**
	 * 保存记录
	 */
//	public ActionForward smspush(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		SmsPushResponse rsp = new SmsPushResponse(request,response);
//		
//		try {
//			String gameid = request.getParameter("gameid");
//			String channelid = request.getParameter("channelid");
//			String telnum = request.getParameter("telnum");
//			
//			int pushflag = ClientMain.getInstance().sendSms(telnum, "欢迎下载桃色恋人2：http://apk.mmarket.com/rs/publish/prepublish4/23/2013/11/13/a116/611/31611116/myheartmm1113.apk");
//			
//			rsp.setPushflag(pushflag);
//			
//			rsp.write();
//		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
////			logger.error(req.getSkyId() + " enter arena error", e);
////			return new EnterArenaResponse(req.getGameState(), (byte) -1, StaticStr.str1);
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			ErrorResponse errrsp = new ErrorResponse(request,response, -1, "参数错误");
//			
//			errrsp.write();
//		}
//				
//		return null;
//	}
	
}
