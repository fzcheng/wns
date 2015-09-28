package cn.mbpaysdk.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.util.JacksonUtil;
import cn.mbpaysdk.vo.InitResult;

/**
 * 
 * @author fzcheng
 */
public class UploadPayresultResponse extends MbpayResponseEncoder{
	private final static Logger logger = Logger.getLogger(UploadPayresultResponse.class);
	
	InitResult initresult;
	
	public UploadPayresultResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public UploadPayresultResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request, response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public InitResult getInitresult() {
		return initresult;
	}

	public void setInitresult(InitResult initresult) {
		this.initresult = initresult;
	}
}
