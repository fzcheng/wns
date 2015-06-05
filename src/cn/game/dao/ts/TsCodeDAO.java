package cn.game.dao.ts;

import cn.game.dao.record.BasicRoleDAO;
import cn.game.util.RandomUtil;
import cn.game.vo.ts.TsCode;


public class TsCodeDAO extends BasicRoleDAO<TsCode> {

	@Override
	public String getXmlname() {
		
		return "TsCode";
	}
	
	public TsCode getById(int id) {
		return (TsCode)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getById", id);
	}
	
	public TsCode getByCode(String code) {
		return (TsCode)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByCode", code);
	}
	
	public void init()
	{
//		int i = 0;
//		for(i = 0; i < 10005; i ++)
//		{
//			TsCode tscode = new TsCode();
//			tscode.setFlag(0);
//			tscode.setCode(""+RandomUtil.getIntRandom(10000000,99999999)+""+RandomUtil.getIntRandom(10,99));
//			this.save(tscode);
//		}
	}
}
