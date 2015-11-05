package cn.master.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 错误响应
 * 
 * @author fzcheng
 */
public class MasterResponse extends MasterResponseEncoder {
	private final static Logger logger = Logger
			.getLogger(MasterResponse.class);
	
	
	public MasterResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
}
