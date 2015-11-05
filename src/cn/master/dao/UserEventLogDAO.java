package cn.master.dao;

import cn.game.dao.record.BasicRoleDAO;
import cn.master.vo.RecordVO;
import cn.master.vo.UserEventLogVO;


public class UserEventLogDAO extends BasicRoleDAO<UserEventLogVO> {

	@Override
	public String getXmlname() {
		
		return "UserEventLog";
	}

	public UserEventLogVO getByKey(RecordVO vo) {
		return (UserEventLogVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByKey", vo);
	}
}
