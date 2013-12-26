package cn.game.dao.record;

import cn.game.vo.record.RecordVO;


public class RecordDAO extends BasicRoleDAO<RecordVO> {

	@Override
	public String getXmlname() {
		
		return "Record";
	}
	
	public void update2(RecordVO vo) {
		getSqlMapClientTemplate().update(getXmlname() + ".update2", vo);
	}

	public void updateCoin(RecordVO vo) {
		getSqlMapClientTemplate().update(getXmlname() + ".updateCoin", vo);
	}
}
