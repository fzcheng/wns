package cn.game.dao.sms;

import java.util.List;

import cn.game.dao.record.BasicRoleDAO;
import cn.game.vo.sms.MessageVO;
import cn.game.vo.sms.SmsRecordVO;


public class SmsRecordDAO extends BasicRoleDAO<SmsRecordVO> {

	@Override
	public String getXmlname() {
		
		return "SmsRecord";
	}

	public List<MessageVO> getUnCompleteRecord(int lastId) {
		SmsRecordVO vo = new SmsRecordVO();
		
		vo.setId(lastId);
		return (List<MessageVO>)getSqlMapClientTemplate().queryForList(getXmlname() + ".getUnCompleteRecord", vo);
	}

	public void completePush(int missionIdI, String smsChannel) {
		SmsRecordVO vo = new SmsRecordVO();
		
		vo.setId(missionIdI);
		vo.setSmschannel(smsChannel);
		getSqlMapClientTemplate().update(getXmlname() + ".completePush", vo);
	}
}
