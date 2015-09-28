package cn.mbpaysdk.dao;

import cn.game.dao.record.BasicRoleDAO;
import cn.mbpaysdk.vo.PayrecordVO;


public class PayrecordDAO extends BasicRoleDAO<PayrecordVO> {

	@Override
	public String getXmlname() {
		
		return "Payrecord";
	}

	public PayrecordVO getByTid(String tid) {
		return (PayrecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByTid", tid);
	}
	
	public PayrecordVO getByChannelOrderid(String channelorderid) {
		return (PayrecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByChannelOrderid", channelorderid);
	}
	
	public void updateStatus(PayrecordVO vo) {
		getSqlMapClientTemplate().update(getXmlname() + ".update_status", vo);
	}

	public PayrecordVO getByOrderId(String order_id) {
		return (PayrecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByOrderid", order_id);
	}
}
