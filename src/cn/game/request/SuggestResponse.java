package cn.game.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.util.JacksonUtil;
import cn.game.vo.record.RecordVO;
import cn.org.util.ResponseEncoder;

/**
 * 登陆检查响应
 * 
 * @author fzcheng
 */
public class SuggestResponse extends ResponseEncoder{
	private final static Logger logger = Logger.getLogger(SuggestResponse.class);
	
	String flag;
	
	public SuggestResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	
	public SuggestResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request,response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
