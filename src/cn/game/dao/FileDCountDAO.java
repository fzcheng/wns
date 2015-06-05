package cn.game.dao;

import cn.game.dao.record.BasicRoleDAO;
import cn.game.vo.FileDCountVO;
import cn.game.vo.ts.TsCode;


public class FileDCountDAO extends BasicRoleDAO<FileDCountVO> {

	@Override
	public String getXmlname() {
		
		return "FileDCount";
	}
	
	public FileDCountVO getById(int id) {
		return (FileDCountVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getById", id);
	}

	public int getDownloadCount(int id) {
		FileDCountVO fvo = getById(id);
		if(fvo != null)
			return fvo.getCount();
		return 0;
	}

	public void incDownloadCount(int id, String filename) {
		FileDCountVO fvo = getById(id);
		if(fvo != null)
		{
			fvo.setCount(fvo.getCount() + 1);
			this.update(fvo);
		}
		else
		{
			fvo = new FileDCountVO();
			fvo.setId(id);
			fvo.setCount(1);
			fvo.setFilename(filename);
			this.save(fvo);
		}
	}
	
	
}
