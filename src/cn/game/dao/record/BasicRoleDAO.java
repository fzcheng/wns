package cn.game.dao.record;

import java.util.List;

import cn.game.dao.base.BaseDao;
import cn.game.vo.BaseVO;

public abstract class BasicRoleDAO<T extends BaseVO> extends BaseDao{

	String xmlname;
	
	public BasicRoleDAO() {
	}
	
	public abstract String getXmlname();
		
	@SuppressWarnings("unchecked")
	public T getById(T vo) {
		return (T)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getById", vo);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListById(T vo) {
		return (List<T>)getSqlMapClientTemplate().queryForList(getXmlname() + ".getListById", vo);
	}

	public int save(T vo) {
		Object obj = (Object)getSqlMapClientTemplate().insert(getXmlname() + ".save", vo);
		if(obj != null)
		{
			return (Integer)(obj);
		}
		return 0;
	}
	
	public void update(T vo) {
		getSqlMapClientTemplate().update(getXmlname() + ".update", vo);
	}
}
