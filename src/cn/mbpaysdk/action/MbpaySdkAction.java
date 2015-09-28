package cn.mbpaysdk.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.bill.base.ErrorCode;
import cn.game.service.ReturnMessage;
import cn.mbpaysdk.response.InitResponse;
import cn.mbpaysdk.response.MbpayErrorResponse;
import cn.mbpaysdk.response.RequestCodeResponse;
import cn.mbpaysdk.response.UploadPayresultResponse;
import cn.mbpaysdk.service.MbpaySdkService;
import cn.mbpaysdk.vo.GetCodeResult;
import cn.mbpaysdk.vo.InitResult;

public class MbpaySdkAction extends BaseAction {

	private final static Logger logger = Logger.getLogger(MbpaySdkAction.class);

	private MbpaySdkService mbpaysdkservice;
	
	public void setMbpaysdkservice(MbpaySdkService mbpaysdkservice) {
		this.mbpaysdkservice = mbpaysdkservice;
	}
	
	/**
	 * init
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		InitResponse rsp = new InitResponse(request,response);
		
		try {
			ReturnMessage rm = mbpaysdkservice.requestInit(request);
			
			if(!rm.isResult())
			{
				MbpayErrorResponse errrsp = new MbpayErrorResponse(request,response, (ErrorCode)rm.getObject(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				rsp.setInitresult((InitResult)rm.getObject());
				rsp.write();
			}
			

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
			e.printStackTrace();
			logger.error(e.getMessage());
			MbpayErrorResponse errrsp = new MbpayErrorResponse(request,response, ErrorCode.Error_Exception, "处理异常");
			
			errrsp.write();
		}
				
		return null;
	}
	
	/**
	 * requestCode
	 */
	public ActionForward requestCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RequestCodeResponse rsp = new RequestCodeResponse(request,response);
		
		try {
			ReturnMessage rm = mbpaysdkservice.requestCode(request);
			
			if(!rm.isResult())
			{
				MbpayErrorResponse errrsp = new MbpayErrorResponse(request,response, (ErrorCode)rm.getObject(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				rsp.setGetcoderesult((GetCodeResult)rm.getObject());
				rsp.write();
			}
		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
			e.printStackTrace();
			logger.error(e.getMessage());
			MbpayErrorResponse errrsp = new MbpayErrorResponse(request,response, ErrorCode.Error_Exception, "处理异常");
			
			errrsp.write();
		}
				
		return null;
	}
	
	/**
	 * 短信发送结果
	 */
	public ActionForward uploadPayresult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UploadPayresultResponse rsp = new UploadPayresultResponse(request,response);
		
		try {
			ReturnMessage rm = mbpaysdkservice.uploadPayresult(request);
			
			if(!rm.isResult())
			{
				MbpayErrorResponse errrsp = new MbpayErrorResponse(request,response, (ErrorCode)rm.getObject(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				//rsp.setRecord((GetCodeResultVO)rm.getObject());
				rsp.write();
			}
			

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
			e.printStackTrace();
			logger.error(e.getMessage());
			MbpayErrorResponse errrsp = new MbpayErrorResponse(request,response, ErrorCode.Error_Exception, "处理异常");
			
			errrsp.write();
		}
				
		return null;
	}
}
