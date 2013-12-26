package admins.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import admins.ben.BackRoleVO;
import admins.ben.BackUserVO;

/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRoleDAO extends SqlMapClientDaoSupport{
	
	/**
	 * 增加一个角色
	 * @param backRoleVO
	 */
	public int save(BackRoleVO backRoleVO){
		return (Integer)getSqlMapClientTemplate().insert("BackRole.save",backRoleVO);
	}
	
	/**
	 * 更新角色
	 * @param backRoleVO
	 */
	public int update(BackRoleVO backRoleVO){
		return getSqlMapClientTemplate().update("BackRole.update",backRoleVO);
	}
	
	/**
	 * 根据id 获取角色
	 * @param id
	 */
	public BackRoleVO getBackRoleById(int id){
		return (BackRoleVO)getSqlMapClientTemplate().queryForObject("BackRole.getBackRoleById",id);
	}
	
	/**
	 * 检查角色名
	 * @param userName
	 * @return
	 */
	public BackRoleVO checkRoleName(String roleName){
		return (BackRoleVO)getSqlMapClientTemplate().queryForObject("BackRole.checkRoleName",roleName);
	}
	
	/**
	 * 根据id删除角色
	 * @param id
	 */
	public int delBackRoleById(int id){
		return getSqlMapClientTemplate().delete("BackRole.delete",id);
	}
	/**
	 * 获取所有角色
	 * @return 
	 */
	public List<BackRoleVO> findBackRoleList(){
		return (List<BackRoleVO>)getSqlMapClientTemplate().queryForList("BackRole.findBackRoleList");
	}
}
