package cn.game.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
public class GetAllTopResponse extends ResponseEncoder{
	private final static Logger logger = Logger.getLogger(GetAllTopResponse.class);
	
	int myTop;//我的排名
	List<TopVO> topList;
	int keyIndex;
	
	public GetAllTopResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	
	public GetAllTopResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
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

	public String getTopList() {
		String s = JacksonUtil.getJsonString4JavaPOJO(topList);
		s = s.replace("\r\n", "");
		s = s.replace("\n", "");
		s = s.replace("\r", "");
		s = s.replace(" ", "");
		String ss = "[]";
		try {
			ss = URLEncoder.encode(s, "utf-8");
			//System.out.println(ss);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ss;
	}

	public int getMyTop() {
		return myTop;
	}

	public void setMyTop(int myTop) {
		this.myTop = myTop;
	}
}
