package cn.bill.base.dao;

import cn.bill.base.vo.BillPhoneVO;
import cn.game.dao.record.BasicRoleDAO;


public class BillPhoneDAO extends BasicRoleDAO<BillPhoneVO> {

	@Override
	public String getXmlname() {
		
		return "BillPhone";
	}

	public BillPhoneVO getByPhone(String phone) {
		return (BillPhoneVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByPhone", phone);
	}
	
	public BillPhoneVO getByImsi(String imsi) {
		return (BillPhoneVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByImsi", imsi);
	}
}
