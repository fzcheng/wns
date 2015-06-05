package cn.bill.migu.dao;

import cn.bill.migu.vo.MiguRecordVO;
import cn.game.dao.record.BasicRoleDAO;


public class MiguRecordDAO extends BasicRoleDAO<MiguRecordVO> {

	@Override
	public String getXmlname() {
		
		return "MiguRecord";
	}

	public MiguRecordVO getByCode(String msg) {
		return (MiguRecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByCode", msg);
	}
	
	public void updateStatus(MiguRecordVO vo) {
		getSqlMapClientTemplate().update(getXmlname() + ".update_status", vo);
	}
}
