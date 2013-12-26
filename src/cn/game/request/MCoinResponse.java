package cn.game.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.game.util.JacksonUtil;
import cn.game.vo.record.RecordVO;
import cn.org.util.ResponseEncoder;

/**
 * 注册响应
 * 
 * @author fzcheng
 */
public class MCoinResponse extends ResponseEncoder {
	private final static Logger logger = Logger
			.getLogger(MCoinResponse.class);

	RecordVO record;

	public MCoinResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
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

	public void setRecord(RecordVO r) {
		record = r;
	}
	
	public RecordVO getRecord()
	{
		return record;
	}
}
