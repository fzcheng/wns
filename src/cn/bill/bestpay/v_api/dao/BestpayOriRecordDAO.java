package cn.bill.bestpay.v_api.dao;

import cn.bill.bestpay.v_api.vo.BestpayOriRecordVO;
import cn.game.dao.record.BasicRoleDAO;


public class BestpayOriRecordDAO extends BasicRoleDAO<BestpayOriRecordVO> {

	@Override
	public String getXmlname() {
		
		return "BestpayOriRecord";
	}

	public BestpayOriRecordVO getByOrderNo(String orderNo) {
		return (BestpayOriRecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByOrderNo", orderNo);
	}
}
