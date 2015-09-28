package cn.mbpaysdk.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.util.JacksonUtil;
import cn.mbpaysdk.vo.GetCodeResult;

/**
 * 
 * @author fzcheng
 */
public class RequestCodeResponse extends MbpayResponseEncoder{
	private final static Logger logger = Logger.getLogger(RequestCodeResponse.class);
	
	GetCodeResult getcoderesult;
	
	public RequestCodeResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public RequestCodeResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request, response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public GetCodeResult getGetcoderesult() {
		return getcoderesult;
	}

	public void setGetcoderesult(GetCodeResult getcoderesult) {
		this.getcoderesult = getcoderesult;
	}

}
