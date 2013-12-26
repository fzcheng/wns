package admins.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import admins.ben.SectionVO;

public class SectionDAO extends SqlMapClientDaoSupport{


	/**
	 * 获取Section
	 */
	@SuppressWarnings("unchecked")
	public List<SectionVO> getList() {
		return (List<SectionVO>)getSqlMapClientTemplate().queryForList("Section.getList");
	}
	
/*	public SectionVO getBySectionId(String gameId, int SectionId){
		SectionVO Sectionvo = new SectionVO();
		Sectionvo.setId(SectionId);
		return (SectionVO) getSqlMapClientTemplate().queryForObject("Section.getBySectionId", Sectionvo);
	}
	
	public int saveSection(SectionVO vo){
		return (Integer) getSqlMapClientTemplate().insert("Section.save", vo);
	}
	
	public int updateSection(SectionVO vo){
		return (Integer) getSqlMapClientTemplate().update("Section.update", vo);
	}
	
	public int delSection(String gameId, int SectionId){
		SectionVO Sectionvo = new SectionVO();
		Sectionvo.setId(SectionId);
		return (Integer) getSqlMapClientTemplate().delete("Section.del", Sectionvo);
	}*/
	public SectionVO getSectionById(int id){
		return (SectionVO) getSqlMapClientTemplate().queryForObject("Section.getSectionById", id);
	}
}
