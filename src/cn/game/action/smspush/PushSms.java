package cn.game.action.smspush;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.service.DataService;
import cn.game.util.JsonUtil;
import cn.game.vo.basic.ChannelVO;
import cn.game.vo.sms.MessageVO;
import cn.game.vo.sms.SmsGameVO;
import cn.org.util.HashHex;
import cn.org.util.SpringUtils;

import com.sms.PushSmsService;

public class PushSms extends javax.servlet.http.HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 228100532899261133L;
	private final static Logger logger = Logger.getLogger(PushSms.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String comd = request.getParameter("comd");
		logger.warn("PushSms doget " + comd);
		
		if(comd.equals("getqueue"))
		{
			List<MessageVO> messages = PushSmsService.getInstance().getQueue();
			
			System.out.println("messages:"+JsonUtil.getJsonString4JavaPOJO(messages));
			ServletOutputStream out = response.getOutputStream();
			out.write(utf2Bytes("{\"messages\":" + JsonUtil.getJsonString4JavaPOJO(messages) + "}"));
			out.flush();
		}
		else if(comd.equals("completepush"))
		{
			String missionId = request.getParameter("missionId");
			int missionIdI = Integer.valueOf(missionId);
			PushSmsService.getInstance().completePush(missionIdI);
			
		}
		else
		{
			try {
				String gameid = request.getParameter("gameid");
				String channelid = request.getParameter("channelid");
				String telnum = request.getParameter("telnum");
				String sign = request.getParameter("sign");
				
				DataService dataservice = (DataService)SpringUtils.getBean("dataservice");
				ChannelVO channel = dataservice.getChannelById(channelid);
				
				System.out.println("PushSms:"+comd+"-"+channelid+"-"+gameid+"-"+telnum+"-"+sign);
				
				int pushflag = 0;//-1:发送错误 -2:短信号码无效 -3:未启用 -4:验证失败 -5:无效渠道或为启用
				if(channel == null || channel.getFlag() == 0)
				{
					pushflag = -5;
				}
				else
				{
					String sourceStr = comd+channelid+gameid+telnum+channel.getSignkey();
					String checksign = HashHex.HashToMD5Hex(sourceStr);
					
					if(!checksign.toLowerCase().equals(sign.toLowerCase()))
					{
						System.out.println(checksign);
						pushflag = -4;
					}
					else
					{
						SmsGameVO smsgame = dataservice.getSmsGameById(gameid, channelid);
						
						if(smsgame == null || smsgame.getIsuse() == 0)
						{
							pushflag = -3;
						}
						else{
							pushflag = PushSmsService.getInstance().sendSms(channelid, gameid, telnum, smsgame.getContent()+smsgame.getDownurl());
						}
					}
				}
				
				System.out.println("pushflag:"+pushflag);
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
