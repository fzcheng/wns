package cn.game.action.pay;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.game.action.BaseAction;
import cn.game.inter.service.IDataService;
import cn.game.pay.mmarket.MMarketQueryService;
import cn.game.pay.mmarket.PayCodeVO;
import cn.game.pay.mmarket.Trusted2ServQueryResp;
import cn.game.request.ErrorResponse;
import cn.game.request.QueryOrderResponse;
import cn.game.service.ReturnMessage;
import cn.game.service.record.PayRecordService;
import cn.game.vo.basic.GameVO;
import cn.game.vo.record.PayrecordVO;
import cn.org.util.SpringUtils;

/**
 * 处理充值订单查询
 * @author a
 *
 */
public class PayAction  extends BaseAction{

	IDataService dataservice;
	public void setDataservice(IDataService dataservice) {
		this.dataservice = dataservice;
	}
	
	/**
	 * 订单查询
	 * 先判断本地是不是有相应的记录
	 * 没有，再去服务器验证
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QueryOrderResponse rsp = new QueryOrderResponse(request,response);
		
		try {
			//gameId
			String gameId = (String)request.getAttribute("gameId");
			String channel = (String)request.getAttribute("channel");//mmarket sinawei
			String jsondata = URLDecoder.decode((String)request.getAttribute("jsondata"), "utf-8");
			
			System.out.println("jsondata:" + jsondata);
			
			if("sinawei".equals(channel))
			{
				
			}
			else if("mmarket".equals(channel))
			{
				JSONObject jsonObject = JSONObject.fromObject(jsondata);
				
				GameVO game = dataservice.getGameById(gameId);
				
				String appId = game.getMmappid();
				String orderId = jsonObject.getString("orderId");
				String tradeId = jsonObject.getString("tradeId");
				
				Trusted2ServQueryResp resp = MMarketQueryService.getInstance().query(appId, orderId, tradeId, game.getMmOrderType());
				PayCodeVO paycode = dataservice.getPaycodeById(resp.getPayCode());
				
				if(resp.getReturnCode() == 0)
				{
					PayrecordVO payrecord = new PayrecordVO(resp, paycode);
					PayRecordService payrecordservice = (PayRecordService)SpringUtils.getBean("payrecordservice");
					ReturnMessage rm = payrecordservice.savepayrecord(payrecord);
					//返回客户端数据
					rsp.setStatus(0);
					rsp.setHint(rm.getDetail());
					rsp.setBasecoin(rm.getResult1());
				}
				else
				{
					//返回客户端数据
					rsp.setStatus(1);
					rsp.setHint("查询失败，请稍后再试，或联系客服。");
					rsp.setBasecoin(0);
				}
				
				rsp.write();
			}
			else
			{
				ErrorResponse errrsp = new ErrorResponse(request,response, -1, "渠道类型错误");
				
				errrsp.write();;
			}

		} catch (Exception e) {// 如果上述操作处理出错返回错误消息
//			logger.error(req.getSkyId() + " enter arena error", e);
//			return new EnterArenaResponse(req.getGameState(), (byte) -1, StaticStr.str1);
			e.printStackTrace();
			ErrorResponse errrsp = new ErrorResponse(request,response, -1, "参数错误");
			
			errrsp.write();
		}
				
		return null;
	}
}
