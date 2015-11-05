package cn.master.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 错误响应
 * 
 * @author fzcheng
 */
public class ErrorResponse extends MasterResponseEncoder {
	private final static Logger logger = Logger
			.getLogger(ErrorResponse.class);
	
	
	public ErrorResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}


	public ErrorResponse(HttpServletRequest request,
			HttpServletResponse response, String errorCode, String errorMsg) {
		super(request, response);
		
		response.setHeader("errorCode", errorCode);
		response.setHeader("errorMsg", errorMsg);
	}
}
