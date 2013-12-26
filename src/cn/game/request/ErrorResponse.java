package cn.game.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.util.JacksonUtil;
import cn.org.util.ResponseEncoder;

/**
 * 注册响应
 * 
 * @author fzcheng
 */
public class ErrorResponse extends ResponseEncoder {
	private final static Logger logger = Logger
			.getLogger(ErrorResponse.class);
	String errorMsg = "s";

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ErrorResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public ErrorResponse(HttpServletRequest request, HttpServletResponse response, int result,
			String errMsg) {
		this(request, response);
		setResult(result);
		this.errorMsg = errMsg;
	}

	@Override
	public String getJsonStr() {
		String resultstr = "";
		try {
			resultstr = JacksonUtil.getJsonString4JavaPOJO(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultstr;
	}
}
