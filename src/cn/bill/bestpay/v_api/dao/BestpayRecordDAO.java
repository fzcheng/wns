package cn.bill.bestpay.v_api.dao;

import cn.bill.bestpay.v_api.vo.BestpayRecordVO;
import cn.game.dao.record.BasicRoleDAO;


public class BestpayRecordDAO extends BasicRoleDAO<BestpayRecordVO> {

	@Override
	public String getXmlname() {
		
		return "BestpayRecord";
	}

	public BestpayRecordVO getByTid(String tid) {
		return (BestpayRecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByTid", tid);
	}
	
	public BestpayRecordVO getByOrderid(String orderid) {
		return (BestpayRecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByOrderid", orderid);
	}
	
	public BestpayRecordVO getByChannelOrderid(String channelorderid) {
		return (BestpayRecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByChannelOrderid", channelorderid);
	}
	
	public void updateStatus(BestpayRecordVO vo) {
		getSqlMapClientTemplate().update(getXmlname() + ".update_status", vo);
	}

	public BestpayRecordVO getBySMSID(String smsid) {
		return (BestpayRecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getBySMSID", smsid);
	}
}
