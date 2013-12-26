package cn.game.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.util.JacksonUtil;
import cn.org.util.ResponseEncoder;

/**
 * 登陆检查响应
 * 
 * @author fzcheng
 */
public class SmsPushResponse extends ResponseEncoder{
	private final static Logger logger = Logger.getLogger(SmsPushResponse.class);
	
	int pushflag;
	
	public SmsPushResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	
	public SmsPushResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request,response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public int getPushflag() {
		return pushflag;
	}

	public void setPushflag(int pushflag) {
		this.pushflag = pushflag;
	}

}
