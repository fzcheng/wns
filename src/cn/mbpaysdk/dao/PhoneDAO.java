package cn.mbpaysdk.dao;

import cn.game.dao.record.BasicRoleDAO;
import cn.mbpaysdk.vo.PhoneVO;


public class PhoneDAO extends BasicRoleDAO<PhoneVO> {

	@Override
	public String getXmlname() {
		
		return "Phone";
	}

	public PhoneVO getByKey(PhoneVO vo) {
		return (PhoneVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByKey", vo);
	}
}
