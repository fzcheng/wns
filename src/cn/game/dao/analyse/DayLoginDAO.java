package cn.game.dao.analyse;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.game.vo.analyse.DayLoginVO;

public class DayLoginDAO extends SqlMapClientDaoSupport {

	public int save(DayLoginVO vo)
	{
		return (Integer)getSqlMapClientTemplate().insert("DayLogin.save",vo);
	}
	
	public DayLoginVO getByDate(String date)
	{
		return (DayLoginVO)getSqlMapClientTemplate().queryForObject("DayLogin.getByDate", date);
	}
	
	public void update(DayLoginVO vo)
	{
		getSqlMapClientTemplate().update("DayLogin.update",vo);
	}
}
