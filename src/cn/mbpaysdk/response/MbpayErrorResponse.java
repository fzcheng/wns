package cn.mbpaysdk.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.bill.base.ErrorCode;
import cn.game.util.JacksonUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 错误响应
 * 
 * @author fzcheng
 */
public class MbpayErrorResponse extends MbpayResponseEncoder {
	private final static Logger logger = Logger
			.getLogger(MbpayErrorResponse.class);
	String errorMsg = "";
	ErrorCode errorcode;
	
	public String getErrorMsg() {
		if(errorMsg != null && !"".equals(errorMsg))
			return errorMsg;
		else
			return errorcode.getMsg();
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setErrorcode(ErrorCode errorcode)
	{
		this.errorcode = errorcode;	
	}
	
	@Override
	public int getResult()
	{
		return getErrorcode();
	}
	
	@JsonIgnore
	public int getErrorcode()
	{
		return errorcode.getCode();
	}
	
	public MbpayErrorResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public MbpayErrorResponse(HttpServletRequest request, HttpServletResponse response, ErrorCode errorcode,
			String errMsg) {
		this(request, response);
		setErrorcode(errorcode);
		this.errorMsg = errMsg;
	}
	
	public MbpayErrorResponse(HttpServletRequest request, HttpServletResponse response, ErrorCode errorcode) {
		this(request, response);
		setErrorcode(errorcode);
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
