package cn.bill.bestpay.v_api.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.bill.base.response.BillResponseEncoder;
import cn.game.util.JacksonUtil;

/**
 * 发送验证码
 * 
 * @author fzcheng
 */
public class SendVerifyCodeResponse extends BillResponseEncoder{
	private final static Logger logger = Logger.getLogger(SendVerifyCodeResponse.class);
	
	public SendVerifyCodeResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public SendVerifyCodeResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request, response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}
}
