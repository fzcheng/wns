package cn.bill.migu.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.bill.base.response.BillResponseEncoder;
import cn.bill.migu.vo.MiguRecordVO;
import cn.game.util.JacksonUtil;
import cn.game.vo.record.RecordVO;

/**
 * 
 * @author fzcheng
 */
public class GetMiguCodeResponse extends BillResponseEncoder{
	private final static Logger logger = Logger.getLogger(GetMiguCodeResponse.class);
	
	MiguRecordVO record;
	
	public GetMiguCodeResponse(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public GetMiguCodeResponse(HttpServletRequest request, HttpServletResponse response, byte result, String errMsg) {
		this(request, response);
		setResult(result);
	}
	
	@Override
	public String getJsonStr()
	{
		return JacksonUtil.getJsonString4JavaPOJO(this);
	}

	public MiguRecordVO getRecord() {
		return record;
	}

	public void setRecord(MiguRecordVO record) {
		this.record = record;
	}
}
