package cn.game.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.util.JacksonUtil;
import cn.game.vo.record.TopVO;
import cn.org.util.ResponseEncoder;

/**
 * 获取排行响应
 * 
 * @author fzcheng
 */
public class GetTopResponse extends ResponseEncoder{
	private final static Logger logger = Logger.getLogger(GetTopResponse.class);
	
	List<TopVO> topList;
	int keyIndex;
	
	public GetTopResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	
	public GetTopResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request,response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public void setTopList(List<TopVO> list) {
		this.topList = list;
	}

	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}

	public int getKeyIndex() {
		return keyIndex;
	}

	public List<TopVO> getTopList() {
		return topList;
	}
}
