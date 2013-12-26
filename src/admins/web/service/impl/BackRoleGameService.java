package admins.web.service.impl;

import java.util.List;
import java.util.Map;

import admins.ben.BackRoleGameDetailVO;
import admins.ben.BackRoleGameVO;
import admins.dao.BackRoleGameDAO;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRoleGameService{

	private BackRoleGameDAO backRoleGameDao;

	public void setBackRoleGameDao(BackRoleGameDAO backRoleGameDao) {
		this.backRoleGameDao = backRoleGameDao;
	}
	
	public int delBackRolePowerById(int id) {
		return backRoleGameDao.delBackRoleById(id);
	}

	
	public List<BackRoleGameVO> findBackRoleGameByRoleId(int roleId) {
		return backRoleGameDao.findBackRoleGameByRoleId(roleId);
	}
	public List<BackRoleGameVO> findBackRoleGameByRoleIdOrState(int roleId) {
		return backRoleGameDao.findBackRoleGameByRoleIdOrState(roleId);
	}
	public List<BackRoleGameVO> findBackRoleGameByRoleIdOrState(Map<String, String> params) {
		return backRoleGameDao.findBackRoleGameByRoleIdOrState(params);
	}
	
	public List<BackRoleGameVO> findBackRoleGameList() {
		return backRoleGameDao.findBackRoleGameList();
	}

	
	public int saveBackRolePower(BackRoleGameVO backRoleGameVO) {
		return backRoleGameDao.save(backRoleGameVO);
	}

	
	public int updateBackRolePower(BackRoleGameVO backRoleGameVO) {
		return backRoleGameDao.update(backRoleGameVO);
	}

	
	public List<BackRoleGameVO> findBackRoleGameByGameId(int gameId) {
		return backRoleGameDao.findBackRoleGameByGameId(gameId);
	}

	
	public List<BackRoleGameDetailVO> getBackRoleGameDetailListByRoleId(
			int roleId) {
		return backRoleGameDao.getBackRoleGameDetailListByRoleId(roleId);
	}
}
