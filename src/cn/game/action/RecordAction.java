package cn.game.action;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.game.request.ErrorResponse;
import cn.game.request.GetRecordResponse;
import cn.game.request.MCoinResponse;
import cn.game.request.SaveRecordResponse;
import cn.game.request.SuggestResponse;
import cn.game.service.ReturnMessage;
import cn.game.service.record.RecordService;
import cn.game.service.record.SuggestService;
import cn.game.vo.record.RecordVO;
import cn.game.vo.record.SuggestVO;

public class RecordAction extends BaseAction {

	private final static Logger logger = Logger.getLogger(RecordAction.class);

	private RecordService recordservice;
	private SuggestService suggestservice;
	
	public void setRecordservice(RecordService recordservice) {
		this.recordservice = recordservice;
	}

	public void setSuggestservice(SuggestService suggestservice) {
		this.suggestservice = suggestservice;
	}
	
	/**
	 * 保存记录
	 */
	public ActionForward saverecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SaveRecordResponse rsp = new SaveRecordResponse(request,response);
		
		try {
			String mtId = (String)request.getAttribute("mtId");
			String plat = getAttribute(request, "plat", "sinawei");
			String username = (String)request.getAttribute("username");
			String gameId = (String)request.getAttribute("gameId");
			String keyValue = (String)request.getAttribute("keyValue");
			String recordData = (String)request.getAttribute("recordData");
			String sign = (String)request.getAttribute("sign");
			
			ReturnMessage rm = recordservice.requestSaverecord(mtId, plat, username, gameId, keyValue, recordData, sign);
			
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
	
	private String getAttribute(HttpServletRequest request, String name, String defaultv) {
		String r = (String)request.getAttribute(name);
		
		if(r == null || r == "")
		{
			r = defaultv;
		}
		return r;
	}

	/**
	 * 获取记录
	 */
	public ActionForward getrecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		GetRecordResponse rsp = new GetRecordResponse(request,response);
		try {
			String mtId = (String)request.getAttribute("mtId");
			String plat = getAttribute(request, "plat", "sinawei");
			String gameId = (String)request.getAttribute("gameId");
			String sign = (String)request.getAttribute("sign");
			
			ReturnMessage rm = recordservice.requestGetrecord(mtId, plat, gameId, sign);
			
			if(!rm.isResult())
			{
				ErrorResponse errrsp = new ErrorResponse(request,response, rm.getErrorCode(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				RecordVO r = (RecordVO)rm.getObject();
				rsp.setRecord(r);
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
	}
	
	/**
	 * 货币变化
	 */
	public ActionForward mcoin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MCoinResponse rsp = new MCoinResponse(request,response);
		
		try {
			String mtId = (String)request.getAttribute("mtId");
			String plat = getAttribute(request, "plat", "sinawei");
			String gameId = (String)request.getAttribute("gameId");
			String keyValue = (String)request.getAttribute("keyValue");
			String recordData = (String)request.getAttribute("recordData");
			String coindata = URLDecoder.decode((String)request.getAttribute("coindata"), "utf-8");
			
			String sign = (String)request.getAttribute("sign");
			
			ReturnMessage rm = recordservice.requestExchange(mtId, plat, gameId, keyValue, recordData, coindata, sign);
			
			if(!rm.isResult())
			{
				ErrorResponse errrsp = new ErrorResponse(request,response, rm.getErrorCode(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				RecordVO r = (RecordVO)rm.getObject();
				
				rsp.setRecord(r);
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
	}
	
	/**
	 * 上传意见
	 */
	public ActionForward suggest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SuggestResponse rsp = new SuggestResponse(request,response);
		
		try {
			String mtId = (String)request.getAttribute("mtId");
			String plat = getAttribute(request, "plat", "sinawei");
			String gameId = (String)request.getAttribute("gameId");
			String suggest = URLDecoder.decode((String)request.getAttribute("suggest"), "utf-8");
			
			SuggestVO s = new SuggestVO();
			s.setMtId(mtId);
			s.setPlat(plat);
			s.setSuggest(suggest);
			ReturnMessage rm = suggestservice.addSuggest(gameId, s);
			
			rsp.setFlag("S");
			rsp.write();

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
//			logger.error(req.getSkyId() + " enter arena error", e);
//			return new EnterArenaResponse(req.getGameState(), (byte) -1, StaticStr.str1);
			e.printStackTrace();
			logger.error(e.getMessage());

			rsp.setFlag("F");
			rsp.write();
		}
				
		return null;
	}
}
