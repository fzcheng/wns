package cn.bill.bestpay.v_sdk.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.bill.base.response.BillResponseEncoder;
import cn.bill.bestpay.v_sdk.vo.GetCodeResultVO;
import cn.game.util.JacksonUtil;

/**
 * 获取 短信内容
 * @author fzcheng
 */
public class GetMsgResponse extends BillResponseEncoder{
	private final static Logger logger = Logger.getLogger(GetMsgResponse.class);
	
	GetCodeResultVO record;
	
	public GetMsgResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public GetMsgResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request, response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public GetCodeResultVO getRecord() {
		return record;
	}

	public void setRecord(GetCodeResultVO record) {
		this.record = record;
	}
}
