package admins.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import admins.ben.BackRoleGameDetailVO;
import admins.ben.BackRoleGameVO;


/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRoleGameDAO extends SqlMapClientDaoSupport{
	
	private String getKey(int roleId,String powerId){
		return roleId + "-" + powerId;
	}
	
	
	/**
	 * 增加一个角色的授权
	 * @param backRolePowerVO
	 */
	public int save(BackRoleGameVO backRoleGameVO){
		return (Integer)getSqlMapClientTemplate().insert("BackRoleGame.save",backRoleGameVO);
	}
	
	/**
	 * 更新角色的权限
	 * @param backRolePowerVO
	 */
	public int update(BackRoleGameVO backRoleGameVO){
		return getSqlMapClientTemplate().update("BackRoleGame.update",backRoleGameVO);
	}
	
	/**
	 * 根据id 获取角色授权
	 * @param id
	 */
	public BackRoleGameVO getBackRoleGameById(int id){
		return (BackRoleGameVO)getSqlMapClientTemplate().queryForObject("BackRoleGame.getBackRoleGameById",id);
	}
	
	/**
	 * 根据id删除角色和权限记录
	 * @param id
	 */
	public int delBackRoleById(int id){
		return getSqlMapClientTemplate().delete("BackRoleGame.delete",id);
	}
	/**
	 * 获取所有角色
	 * @return 
	 */
	public List<BackRoleGameVO> findBackRoleGameList(){
		return (List<BackRoleGameVO>)getSqlMapClientTemplate().queryForList("BackRoleGame.findBackRoleGameList");
	}
	/**
	 * 根据roleId获取角色和权限关系记录
	 * @param roleId 角色id
	 * @return 
	 */
	public List<BackRoleGameVO> findBackRoleGameByRoleId(int roleId){
		return (List<BackRoleGameVO>)getSqlMapClientTemplate().queryForList("BackRoleGame.findBackRoleGameByRoleId",roleId);
	}
	public List<BackRoleGameVO> findBackRoleGameByRoleIdOrState(int roleId){
		return (List<BackRoleGameVO>)getSqlMapClientTemplate().queryForList("BackRoleGame.findBackRoleGameByRoleIdorState",roleId);
	}
	public List<BackRoleGameVO> findBackRoleGameByRoleIdOrState(Map<String, String> params){
		return (List<BackRoleGameVO>)getSqlMapClientTemplate().queryForList("BackRoleGame.findBackRoleGameByRoleIdGameIdorState",params);
	}
	/**
	 * 根据powerId获取角色和权限关系记录
	 * @param powerId 角色id
	 * @return 
	 */
	public List<BackRoleGameVO> findBackRoleGameByGameId(int powerId){
		return (List<BackRoleGameVO>)getSqlMapClientTemplate().queryForList("BackRoleGame.findBackRoleGameByPowerId",powerId);
	}
	/**
	 * 根据roleId,powerId 删除角色和权限关系记录
	 * @param roleId 角色id
	 * @param powerId	权限id
	 * @return
	 */
	public int deleteRolePowerByRoleIdAndGameId(BackRoleGameVO backRoleGameVO){
		return getSqlMapClientTemplate().delete("BackRoleGame.deleteRolePowerByRoleIdAndGameId",backRoleGameVO);
	}
	/**
	 * 根据roleId ,powerId 获取角色授权
	 * @param roleId 角色id
	 * @param powerId	权限id
	 */
	public BackRoleGameVO getBackRoleGameByRoleIdAndGameId(BackRoleGameVO backRoleGameVO){
		return (BackRoleGameVO)getSqlMapClientTemplate().queryForObject("BackRoleGame.getBackRoleGameByRoleIdAndGameId",backRoleGameVO);
	}


	public List<BackRoleGameDetailVO> getBackRoleGameDetailListByRoleId(
			int roleId) {
		return (List<BackRoleGameDetailVO>)getSqlMapClientTemplate().queryForList("BackRoleGame.getBackRoleGameDetailListByRoleId",roleId);
	}
}
