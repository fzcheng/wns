package cn.bill.bestpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.bill.base.BaseBillAction;
import cn.bill.base.ErrorCode;
import cn.bill.bestpay.v_api.response.BestpayErrorResponse;
import cn.bill.bestpay.v_api.response.GetVerifyCodeResponse;
import cn.bill.bestpay.v_api.response.SendVerifyCodeResponse;
import cn.bill.bestpay.v_api.vo.BestpayRecordVO;
import cn.bill.bestpay.v_sdk.response.GetMsgResponse;
import cn.bill.bestpay.v_sdk.vo.GetCodeResultVO;
import cn.game.service.ReturnMessage;

public class BestpayAction extends BaseBillAction {

	private final static Logger logger = Logger.getLogger(BestpayAction.class);

	private cn.bill.bestpay.v_api.service.BestpayService bestpay_api_service;
	private cn.bill.bestpay.v_sdk.service.BestpayService bestpay_sdk_service;
	
	public void setBestpay_api_service( cn.bill.bestpay.v_api.service.BestpayService bestpay_api_service) {
		this.bestpay_api_service = bestpay_api_service;
	}
	
	public void setBestpay_sdk_service( cn.bill.bestpay.v_sdk.service.BestpayService bestpay_sdk_service) {
		this.bestpay_sdk_service = bestpay_sdk_service;
	}
	
	/**
	 * 根据用户手机号获取验证码 -- api版本
	 */
	public ActionForward getcode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GetVerifyCodeResponse rsp = new GetVerifyCodeResponse(request,response);
		
		try {
			ReturnMessage rm = bestpay_api_service.requestGetVerifyCode(request);
			
			if(!rm.isResult())
			{
				BestpayErrorResponse errrsp = new BestpayErrorResponse(request,response, (ErrorCode)rm.getObject(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				rsp.setRecord((BestpayRecordVO)rm.getObject());
				rsp.write();
			}
			

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
			e.printStackTrace();
			logger.error(e.getMessage());
			BestpayErrorResponse errrsp = new BestpayErrorResponse(request,response, ErrorCode.Error_Exception, "处理异常");
			
			errrsp.write();
		}
				
		return null;
	}
	
	/**
	 * 发送验证码 -- api版本
	 */
	public ActionForward sendcode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SendVerifyCodeResponse rsp = new SendVerifyCodeResponse(request,response);
		
		try {
			ReturnMessage rm = bestpay_api_service.requestSendVerifyCode(request);
			
			if(!rm.isResult())
			{
				BestpayErrorResponse errrsp = new BestpayErrorResponse(request,response, (ErrorCode)rm.getObject(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				rsp.write();
			}
		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
			e.printStackTrace();
			logger.error(e.getMessage());
			BestpayErrorResponse errrsp = new BestpayErrorResponse(request,response, ErrorCode.Error_Exception, "处理异常");
			
			errrsp.write();
		}
				
		return null;
	}
	
	/**
	 * 根据用户手机号获取验证码 -- sdk版本
	 */
	public ActionForward getmsg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GetMsgResponse rsp = new GetMsgResponse(request,response);
		
		try {
			ReturnMessage rm = bestpay_sdk_service.requestGetMsg(request);
			
			if(!rm.isResult())
			{
				BestpayErrorResponse errrsp = new BestpayErrorResponse(request,response, (ErrorCode)rm.getObject(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				rsp.setRecord((GetCodeResultVO)rm.getObject());
				rsp.write();
			}
			

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
			e.printStackTrace();
			logger.error(e.getMessage());
			BestpayErrorResponse errrsp = new BestpayErrorResponse(request,response, ErrorCode.Error_Exception, "处理异常");
			
			errrsp.write();
		}
				
		return null;
	}
}
