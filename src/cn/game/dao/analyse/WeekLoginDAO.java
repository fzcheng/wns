package cn.game.dao.analyse;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.game.vo.analyse.WeekLoginVO;

public class WeekLoginDAO extends SqlMapClientDaoSupport {

	public int save(WeekLoginVO vo)
	{
		return (Integer)getSqlMapClientTemplate().insert("WeekLogin.save",vo);
	}
	
	public WeekLoginVO getByDate(String date)
	{
		return (WeekLoginVO)getSqlMapClientTemplate().queryForObject("WeekLogin.getByDate", date);
	}
	
	public void update(WeekLoginVO vo)
	{
		getSqlMapClientTemplate().update("WeekLogin.update",vo);
	}
}
