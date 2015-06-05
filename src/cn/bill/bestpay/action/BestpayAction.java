package cn.bill.bestpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.bill.base.BaseBillAction;
import cn.bill.base.response.BillErrorResponse;
import cn.bill.migu.response.GetMiguCodeResponse;
import cn.bill.migu.service.MiguService;
import cn.bill.migu.vo.MiguRecordVO;
import cn.game.service.ReturnMessage;

public class BestpayAction extends BaseBillAction {

	private final static Logger logger = Logger.getLogger(BestpayAction.class);

	private MiguService miguservice;
	
	public void setMiguservice(MiguService miguservice) {
		this.miguservice = miguservice;
	}
	
	/**
	 * 根据用户手机号 或者 imsi获取可用的 migu代码
	 */
	public ActionForward getcode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GetMiguCodeResponse rsp = new GetMiguCodeResponse(request,response);
		
		try {
			ReturnMessage rm = miguservice.requestGetCode(request);
			
			if(!rm.isResult())
			{
				BillErrorResponse errrsp = new BillErrorResponse(request,response, rm.getErrorCode(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				rsp.setRecord((MiguRecordVO)rm.getObject());
				rsp.write();
			}
			

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
			e.printStackTrace();
			logger.error(e.getMessage());
			BillErrorResponse errrsp = new BillErrorResponse(request,response, -1, "处理异常");
			
			errrsp.write();
		}
				
		return null;
	}
	
	/**
	 * 通知完成订单
	 */
	public ActionForward complete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GetMiguCodeResponse rsp = new GetMiguCodeResponse(request,response);
		
		try {
			ReturnMessage rm = miguservice.requestGetCode(request);
			
			if(!rm.isResult())
			{
				BillErrorResponse errrsp = new BillErrorResponse(request,response, rm.getErrorCode(), rm.getDetail());
				
				errrsp.write();
			}
			else
			{
				rsp.setRecord((MiguRecordVO)rm.getObject());
				rsp.write();
			}
			

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
			e.printStackTrace();
			logger.error(e.getMessage());
			BillErrorResponse errrsp = new BillErrorResponse(request,response, -1, "处理异常");
			
			errrsp.write();
		}
				
		return null;
	}
}
