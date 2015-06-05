package cn.game.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.game.request.ErrorResponse;
import cn.game.request.GetGameNoticeResponse;
import cn.game.request.GetServerTimeResponse;
import cn.game.service.DataService;
import cn.game.vo.basic.GameVO;
import cn.org.util.SpringUtils;

public class DataAction extends BaseAction {

	private final static Logger logger = Logger.getLogger(DataAction.class);

	/**
	 * 服务器时间
	 */
	public ActionForward servertime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GetServerTimeResponse rsp = new GetServerTimeResponse(request,response);
		
		try {
			String clienttime = (String)request.getAttribute("clienttime");
			
			rsp.setClienttime(clienttime);
			rsp.setServertime("" + System.currentTimeMillis() / 1000);
			rsp.write();

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
//			logger.error(req.getSkyId() + " enter arena error", e);
//			return new EnterArenaResponse(req.getGameState(), (byte) -1, StaticStr.str1);
			e.printStackTrace();
			logger.error(e.getMessage());
			ErrorResponse errrsp = new ErrorResponse(request,response, -1, "参数错误");
			
			errrsp.write();
		}
				
		return null;
	}
	
	/**
	 * 公告
	 */
	public ActionForward gamenotice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GetGameNoticeResponse rsp = new GetGameNoticeResponse(request,response);
		
		try {
			String gameId = (String)request.getAttribute("gameId");

			DataService dataservice = (DataService)SpringUtils.getBean("dataservice");
			GameVO game = dataservice.getGameById(gameId);
			if(game != null)
				rsp.setNotice(game.getGamenotice());
			else
				rsp.setNotice("无公告");
			rsp.write();

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
//			logger.error(req.getSkyId() + " enter arena error", e);
//			return new EnterArenaResponse(req.getGameState(), (byte) -1, StaticStr.str1);
			e.printStackTrace();
			logger.error(e.getMessage());
			ErrorResponse errrsp = new ErrorResponse(request,response, -1, "参数错误");
			
			errrsp.write();
		}
				
		return null;
	}
}
