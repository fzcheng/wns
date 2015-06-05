package cn.game.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.game.request.ErrorResponse;
import cn.game.request.GetAllTopResponse;
import cn.game.request.GetTopResponse;
import cn.game.request.UpScoreResponse;
import cn.game.service.ReturnMessage;
import cn.game.service.record.RecordService;
import cn.game.vo.record.TopVO;

public class TopAction extends BaseAction {

	private final static Logger logger = Logger.getLogger(TopAction.class);

	private RecordService recordservice;
	
	public void setRecordservice(RecordService recordservice) {
		this.recordservice = recordservice;
	}

	public void init()
	{
		//recordservice.getfriendtop("1414708180", "sinawei", "10001", 2, "");
		System.out.println("time:" + System.currentTimeMillis() / 1000);
		//recordservice.getalltop("1414708180", "sinawei", "10001", 2);
	}
	
	/**
	 * 上传积分
	 */
	public ActionForward upscore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UpScoreResponse rsp = new UpScoreResponse(request,response);
		
		try {
			String mtId = (String)request.getAttribute("mtId");
			String plat = (String)request.getAttribute("plat");
			String gameId = (String)request.getAttribute("gameId");
			String keyIndex = (String)request.getAttribute("keyIndex");
			String keyValue = (String)request.getAttribute("keyValue");
			
			ReturnMessage rm = recordservice.upscore(mtId, plat, gameId, parseInt(keyIndex), parseInt(keyValue));
			
			if(!rm.isResult())
			{
				ErrorResponse errrsp = new ErrorResponse(request,response, rm.getErrorCode(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				rsp.write();
			}

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
//			logger.error(req.getSkyId() + " enter arena error", e);
//			return new EnterArenaResponse(req.getGameState(), (byte) -1, StaticStr.str1);
			e.printStackTrace();
			logger.error(e.getMessage());
			ErrorResponse errrsp = new ErrorResponse(request,response, -1, "参数错误");
			
			errrsp.write();
		}
				
		return null;
		//return mapping.findForward("activity");
	}
	
	/**
	 * 获取排行 keyValueId 1-6个数据排行
	 */
	public ActionForward getalltop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		GetAllTopResponse rsp = new GetAllTopResponse(request,response);
		try {
			String mtId = (String)request.getAttribute("mtId");
			String plat = (String)request.getAttribute("plat");
			String gameId = (String)request.getAttribute("gameId");
			String keyIndex = (String)request.getAttribute("keyIndex");
			
			rsp.setKeyIndex(parseInt(keyIndex));
			List<TopVO> list = recordservice.getalltop(mtId, plat, gameId, parseInt(keyIndex));
			int myTop = recordservice.getMyTop(mtId, plat, gameId, parseInt(keyIndex));
			rsp.setMyTop(myTop);
			
			rsp.setTopList(list);
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
	 * 获取排行 keyValueId 1-6个数据排行
	 */
	public ActionForward gettop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		GetTopResponse rsp = new GetTopResponse(request,response);
		try {
			String mtId = (String)request.getAttribute("mtId");
			String plat = (String)request.getAttribute("plat");
			String gameId = (String)request.getAttribute("gameId");
			String keyIndex = (String)request.getAttribute("keyIndex");
			
			//到第三方平台同步好友列表所需要数据
			String jsonstr = (String)request.getAttribute("jsonstr");
			System.out.println("gettop jsonstr:" + jsonstr);
			
			rsp.setKeyIndex(parseInt(keyIndex));
			List<TopVO> list = recordservice.getfriendtop(mtId, plat, gameId, parseInt(keyIndex), jsonstr);
			
			rsp.setTopList(list);
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
