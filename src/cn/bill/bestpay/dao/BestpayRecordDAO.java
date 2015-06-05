package cn.bill.bestpay.dao;

import cn.bill.migu.vo.MiguRecordVO;
import cn.game.dao.record.BasicRoleDAO;


public class BestpayRecordDAO extends BasicRoleDAO<MiguRecordVO> {

	@Override
	public String getXmlname() {
		
		return "BestpayRecord";
	}

	public MiguRecordVO getByTid(String tid) {
		return (MiguRecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByTid", tid);
	}
	
	public MiguRecordVO getByOrderid(String orderid) {
		return (MiguRecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByOrderid", orderid);
	}
	
	public MiguRecordVO getByChannelOrderid(String channelorderid) {
		return (MiguRecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByChannelOrderid", channelorderid);
	}
	
	public void updateStatus(MiguRecordVO vo) {
		getSqlMapClientTemplate().update(getXmlname() + ".update_status", vo);
	}
}
