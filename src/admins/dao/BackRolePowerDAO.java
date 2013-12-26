package admins.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import admins.ben.BackRolePowerVO;

/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRolePowerDAO extends SqlMapClientDaoSupport{
	private static Map<String, BackRolePowerVO> roleFuncMap = null;
	
	public BackRolePowerVO getBrpByRidAndPid(int roleId,int powerId){
		if(roleFuncMap == null) loadRoleFuncMap();
		
		return roleFuncMap.get(getKey(roleId, powerId));
	}
	
	public List<BackRolePowerVO> getBrpByRid(int roleId){
		if(roleFuncMap == null) loadRoleFuncMap();
		List<BackRolePowerVO> resultList = new ArrayList<BackRolePowerVO>();
		for(String key : roleFuncMap.keySet()){  
			if(key.startsWith(roleId+"-")){
				BackRolePowerVO vo = roleFuncMap.get(key);
				resultList.add(vo);
			}
		} 
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	private void loadRoleFuncMap(){
		if(roleFuncMap == null) roleFuncMap = new HashMap<String, BackRolePowerVO>();
		
		List<BackRolePowerVO> roleFuncList = getSqlMapClientTemplate().queryForList("BackRolePower.getRoleFuncList");
		
		for (BackRolePowerVO brpVo : roleFuncList) {
			int roleId = brpVo.getRoleId();
			int powerId = brpVo.getPowerId();
			String key = getKey(roleId, powerId);
			roleFuncMap.put(key, brpVo);
		}
	}
	
	private String getKey(int roleId,int powerId){
		return roleId + "-" + powerId;
	}
	
	
	/**
	 * 增加一个角色的授权
	 * @param backRolePowerVO
	 */
	public int save(BackRolePowerVO backRolePowerVO){
		if(backRolePowerVO.getState() == 1){
			if(roleFuncMap == null) loadRoleFuncMap();
			
			int roleId = backRolePowerVO.getRoleId();
			int powerId = backRolePowerVO.getPowerId();
			
			roleFuncMap.put(getKey(roleId, powerId), backRolePowerVO);
		}
		return (Integer)getSqlMapClientTemplate().insert("BackRolePower.save",backRolePowerVO);
	}
	
	/**
	 * 更新角色的权限
	 * @param backRolePowerVO
	 */
	public int update(BackRolePowerVO backRolePowerVO){
		return getSqlMapClientTemplate().update("BackRolePower.update",backRolePowerVO);
	}
	
	/**
	 * 根据id 获取角色授权
	 * @param id
	 */
	public BackRolePowerVO getBackRolePowerById(int id){
		return (BackRolePowerVO)getSqlMapClientTemplate().queryForObject("BackRolePower.getBackRolePowerById",id);
	}
	
	/**
	 * 根据id删除角色和权限记录
	 * @param id
	 */
	public int delBackRoleById(int id){
		return getSqlMapClientTemplate().delete("BackRolePower.delete",id);
	}
	/**
	 * 获取所有角色
	 * @return 
	 */
	public List<BackRolePowerVO> findBackRolePowerList(){
		return (List<BackRolePowerVO>)getSqlMapClientTemplate().queryForList("BackRolePower.findBackRolePowerList");
	}
	/**
	 * 根据roleId获取角色和权限关系记录
	 * @param roleId 角色id
	 * @return 
	 */
	public List<BackRolePowerVO> findBackRolePowerByRoleId(int roleId){
		return (List<BackRolePowerVO>)getSqlMapClientTemplate().queryForList("BackRolePower.findBackRolePowerByRoleId",roleId);
	}
	
	/**
	 * 根据powerId获取角色和权限关系记录
	 * @param powerId 角色id
	 * @return 
	 */
	public List<BackRolePowerVO> findBackRolePowerByPowerId(int powerId){
		return (List<BackRolePowerVO>)getSqlMapClientTemplate().queryForList("BackRolePower.findBackRolePowerByPowerId",powerId);
	}
	/**
	 * 根据roleId,powerId 删除角色和权限关系记录
	 * @param roleId 角色id
	 * @param powerId	权限id
	 * @return
	 */
	public int deleteRolePowerByRoleIdAndPowerId(BackRolePowerVO backRolePowerVO){
		if(backRolePowerVO.getState() == 1){
			if(roleFuncMap == null) loadRoleFuncMap();
			
			int roleId = backRolePowerVO.getRoleId();
			int powerId = backRolePowerVO.getPowerId();
			
			roleFuncMap.remove(getKey(roleId, powerId));
		}
	
		return getSqlMapClientTemplate().delete("BackRolePower.deleteRolePowerByRoleIdAndPowerId",backRolePowerVO);
	}
	/**
	 * 根据roleId ,powerId 获取角色授权
	 * @param roleId 角色id
	 * @param powerId	权限id
	 */
	public BackRolePowerVO getBackRolePowerByRoleIdAndPowerId(BackRolePowerVO backRolePowerVO){
		return (BackRolePowerVO)getSqlMapClientTemplate().queryForObject("BackRolePower.getBackRolePowerByRoleIdAndPowerId",backRolePowerVO);
	}
}
