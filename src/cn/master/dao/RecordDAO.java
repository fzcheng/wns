package cn.master.dao;

import cn.game.dao.record.BasicRoleDAO;
import cn.master.vo.RecordVO;


public class RecordDAO extends BasicRoleDAO<RecordVO> {

	@Override
	public String getXmlname() {
		
		return "Record";
	}

	public RecordVO getByKey(RecordVO vo) {
		return (RecordVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByKey", vo);
	}
}
