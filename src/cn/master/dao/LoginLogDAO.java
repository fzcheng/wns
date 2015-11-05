package cn.master.dao;

import cn.game.dao.record.BasicRoleDAO;
import cn.master.vo.LoginLogVO;
import cn.master.vo.RecordVO;


public class LoginLogDAO extends BasicRoleDAO<LoginLogVO> {

	@Override
	public String getXmlname() {
		
		return "LoginLog";
	}

	public LoginLogVO getByKey(RecordVO vo) {
		return (LoginLogVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByKey", vo);
	}
}
