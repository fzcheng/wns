package cn.game.dao.analyse;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.game.vo.analyse.MonthLoginVO;

public class MonthLoginDAO extends SqlMapClientDaoSupport {

	public int save(MonthLoginVO vo)
	{
		return (Integer)getSqlMapClientTemplate().insert("MonthLogin.save",vo);
	}
	
	public MonthLoginVO getByDate(String date)
	{
		return (MonthLoginVO)getSqlMapClientTemplate().queryForObject("MonthLogin.getByDate", date);
	}
	
	public void update(MonthLoginVO vo)
	{
		getSqlMapClientTemplate().update("MonthLogin.update",vo);
	}
}
