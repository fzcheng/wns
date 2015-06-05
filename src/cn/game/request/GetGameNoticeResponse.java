package cn.game.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.util.JacksonUtil;
import cn.game.vo.record.RecordVO;
import cn.org.util.ResponseEncoder;

/**
 * 获取公告
 * 
 * @author fzcheng
 */
public class GetGameNoticeResponse extends ResponseEncoder{
	private final static Logger logger = Logger.getLogger(GetGameNoticeResponse.class);
	
	String notice;
	
	public GetGameNoticeResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	
	public GetGameNoticeResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request,response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
}
