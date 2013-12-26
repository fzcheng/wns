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
public class GetServerTimeResponse extends ResponseEncoder{
	private final static Logger logger = Logger.getLogger(GetServerTimeResponse.class);
	
	String servertime;
	String clienttime;
	
	public GetServerTimeResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	
	public GetServerTimeResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request,response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public void setServertime(String servertime) {
		this.servertime = servertime;
	}

	public String getServertime() {
		return servertime;
	}

	public void setClienttime(String clienttime) {
		this.clienttime = clienttime;
	}

	public String getClienttime() {
		return clienttime;
	}
}
