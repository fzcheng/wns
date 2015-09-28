package cn.bill.bestpay.v_api.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.bill.base.response.BillResponseEncoder;
import cn.bill.bestpay.v_api.vo.BestpayRecordVO;
import cn.game.util.JacksonUtil;

/**
 * 获取验证码
 * @author fzcheng
 */
public class GetVerifyCodeResponse extends BillResponseEncoder{
	private final static Logger logger = Logger.getLogger(GetVerifyCodeResponse.class);
	
	BestpayRecordVO record;
	
	public GetVerifyCodeResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public GetVerifyCodeResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request, response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public BestpayRecordVO getRecord() {
		return record;
	}

	public void setRecord(BestpayRecordVO record) {
		this.record = record;
	}
}
