package cn.game.dao.record;

import java.util.List;

import cn.game.vo.record.RecordVO;
import cn.game.vo.record.TopVO;


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

	@SuppressWarnings("unchecked")
	public List<TopVO> getTop(RecordVO vo) {
		return getSqlMapClientTemplate().queryForList(getXmlname() + ".getTop", vo);
	}

	public List<TopVO> getAllTop(RecordVO vo) {
		return getSqlMapClientTemplate().queryForList(getXmlname() + ".getAllTop", vo);
	}

	public int getMyTop(RecordVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getMyTop", vo);
	}
}
