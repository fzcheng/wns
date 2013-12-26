package admins.web.service.impl;

import java.util.List;

import admins.ben.BackUserVO;
import admins.dao.BackUserDAO;
import admins.web.service.IBackUserService;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackUserService implements IBackUserService {
	
	private BackUserDAO backUserDao;
	
	public void setBackUserDao(BackUserDAO backUserDao) {
		this.backUserDao = backUserDao;
	}
		
	/**
	 * 判断是否有这个玩家登陆
	 * 
	 * @param name
	 * @param paw
	 * @return
	 */
	public BackUserVO isAdminLogin(String name, String pwd) {
		BackUserVO backUserVO = new BackUserVO();
		backUserVO.setUserName(name);
		backUserVO.setUserPwd(pwd);
		BackUserVO vo = backUserDao.getAdminLogin(backUserVO);
		return vo;
	}

	
	public int saveBackUser(BackUserVO backUserVO) {
		return backUserDao.saveBackUser(backUserVO);
	}


	
	public List<BackUserVO> findBackUserList() {
		return backUserDao.findBackUserList();
	}


	
	public int updateBackUser(BackUserVO backUserVO) {
		return backUserDao.updateBackUser(backUserVO);
	}

	
	public BackUserVO getBackUserById(int id) {
		return backUserDao.getBackUserById(id);
	}

	
	public int delBackUserById(int id) {
		return backUserDao.delBackUserById(id);
	}

	
	public boolean checkUserName(String userName) {
		if(null == backUserDao.checkUserName(userName)){
			return false;
		}
		return true;
	}

	
	public List<BackUserVO> findBackUserByRoleId(int roleId) {
		return backUserDao.findBackUserByRoleId(roleId);
	}
	
	
}
