package cn.game.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.util.JacksonUtil;
import cn.org.util.ResponseEncoder;

/**
 * 上传积分响应
 * 
 * @author fzcheng
 */
public class UpScoreResponse extends ResponseEncoder{
	private final static Logger logger = Logger.getLogger(UpScoreResponse.class);
	
	public UpScoreResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	
	public UpScoreResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request,response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}
}
