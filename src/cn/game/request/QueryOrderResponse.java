package cn.game.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.game.util.JacksonUtil;
import cn.org.util.ResponseEncoder;

/**
 * 订单查询
 * 返回成功失败标志和新的数据,以及说明
 * @author fzcheng
 */
public class QueryOrderResponse extends ResponseEncoder{
	
	int status;
	String hint;
	int basecoin;
	
	public QueryOrderResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	
	public QueryOrderResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request,response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public int getBasecoin() {
		return basecoin;
	}

	public void setBasecoin(int basecoin) {
		this.basecoin = basecoin;
	}

	
}
