package cn.game.dao.record;

import cn.game.vo.record.PayrecordVO;


public class PayrecordDAO extends BasicRoleDAO<PayrecordVO> {

	@Override
	public String getXmlname() {
		
		return "PayRecord";
	}

	public PayrecordVO getByOrderId(PayrecordVO vo) {
		return (PayrecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByOrderId", vo);
	}

	public int getCountByUserId(PayrecordVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getCountByUserId", vo);
	}
}
