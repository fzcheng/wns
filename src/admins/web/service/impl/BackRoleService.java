package admins.web.service.impl;

import java.util.List;

import admins.ben.BackRoleVO;
import admins.ben.BackUserVO;
import admins.dao.BackRoleDAO;
import admins.dao.BackUserDAO;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRoleService{
	
	private BackRoleDAO backRoleDao;
	private BackUserDAO backUserDao;
	
	public void setBackRoleDao(BackRoleDAO backRoleDao) {
		this.backRoleDao = backRoleDao;
	}

	public void setBackUserDao(BackUserDAO backUserDao) {
		this.backUserDao = backUserDao;
	}


	public int delBackRoleById(int id) {
		return backRoleDao.delBackRoleById(id);
	}

	
	public List<BackRoleVO> findBackRoleList() {
		return backRoleDao.findBackRoleList();
	}

	
	public int saveBackRole(BackRoleVO backRoleVO) {
		return backRoleDao.save(backRoleVO);
	}
	public boolean  checkRoleName(String roleName){
		BackRoleVO vo=  (BackRoleVO)backRoleDao.checkRoleName(roleName);
		if(vo==null)
			return true;
		else
			return false;
	}
	
	public int updateBackRole(BackRoleVO backRoleVO) {
		//修改角色名同时更新用户表里角色名称
		BackUserVO backUserVO = new BackUserVO();
		backUserVO.setRoleId(backRoleVO.getRoleId());
		backUserVO.setRoleName(backRoleVO.getRoleName());
		backUserDao.updateBackUserByRoleId(backUserVO);
		
		return backRoleDao.update(backRoleVO);
	}

	
	public BackRoleVO getBackRoleById(int id) {
		return backRoleDao.getBackRoleById(id);
	}

}
