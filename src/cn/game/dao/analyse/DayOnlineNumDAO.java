package cn.game.dao.analyse;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.game.vo.analyse.DayOnlineNumVO;

public class DayOnlineNumDAO extends SqlMapClientDaoSupport {

	public int save(DayOnlineNumVO vo)
	{
		return (Integer)getSqlMapClientTemplate().insert("DayOnlineNum.save",vo);
	}
	
	@SuppressWarnings("unchecked")
	public List<DayOnlineNumVO> getListByDate(DayOnlineNumVO vo)
	{
		return (List<DayOnlineNumVO>)getSqlMapClientTemplate().queryForList("DayOnlineNum.getListByDate", vo);
	}
}
