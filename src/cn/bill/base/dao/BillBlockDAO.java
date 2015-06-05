package cn.bill.base.dao;

import cn.bill.base.vo.BillBlockVO;
import cn.game.dao.record.BasicRoleDAO;


public class BillBlockDAO extends BasicRoleDAO<BillBlockVO> {

	@Override
	public String getXmlname() {
		
		return "BillBlock";
	}
	
	public BillBlockVO getByPhone(String phone) {
		return (BillBlockVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByPhone", phone);
	}
	
	public BillBlockVO getByImsi(String imsi) {
		return (BillBlockVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByImsi", imsi);
	}
}
