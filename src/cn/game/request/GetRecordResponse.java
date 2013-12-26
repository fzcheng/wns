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
public class GetRecordResponse extends ResponseEncoder{
	private final static Logger logger = Logger.getLogger(GetRecordResponse.class);
	
	RecordVO record;
	
	public GetRecordResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public GetRecordResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request, response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public RecordVO getRecord() {
		return record;
	}

	public void setRecord(RecordVO record) {
		this.record = record;
	}
}
