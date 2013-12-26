package admins.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import admins.ben.BackUserVO;
/**
 * @author 田建
 * 
 * 14:24:51 
 */

public class BackUserDAO extends SqlMapClientDaoSupport{
	
	/**
	 * 登入检验
	 * @param backUserVO
	 * @return
	 */
	public BackUserVO getAdminLogin(BackUserVO backUserVO){
		return (BackUserVO)getSqlMapClientTemplate().queryForObject("BackUser.getAdminLogin",backUserVO);
	}
	
	/**
	 * 增加后台用户
	 * @param backUserVO
	 * @return
	 */
	public int saveBackUser(BackUserVO backUserVO){
		return (Integer)getSqlMapClientTemplate().insert("BackUser.save",backUserVO);
	}
	
	/**
	 * 根据id删除后台用户
	 * @param backUserVO
	 * @return
	 */
	public int delBackUserById(int id){
		return (Integer)getSqlMapClientTemplate().delete("BackUser.delete",id);
	}
	
	/**
	 * 获取所有用户
	 * @return 
	 */
	public List<BackUserVO> findBackUserList(){
		return (List<BackUserVO>)getSqlMapClientTemplate().queryForList("BackUser.findBackUserList");
	}
	
	/**
	 * 更新用户
	 * @param backUserVO
	 * @return
	 */
	public int updateBackUser(BackUserVO backUserVO){
		return (Integer)getSqlMapClientTemplate().update("BackUser.update",backUserVO);
	}
	/**
	 * 根据id 查询用户
	 * @param id
	 * @return
	 */
	public BackUserVO getBackUserById(int id){
		return (BackUserVO)getSqlMapClientTemplate().queryForObject("BackUser.getBackUserById",id);
	}
	/**
	 * 检查用户名
	 * @param userName
	 * @return
	 */
	public BackUserVO checkUserName(String userName){
		return (BackUserVO)getSqlMapClientTemplate().queryForObject("BackUser.checkUserName",userName);
	}
	/**
	 * 根据roleId 修改角色名
	 * @param backUserVO
	 * @return
	 */
	public int updateBackUserByRoleId(BackUserVO backUserVO){
		return (Integer)getSqlMapClientTemplate().update("BackUser.updateBackUserByRoleId",backUserVO);
	}
	/**
	 * 根据roleId获取用户
	 * @param roleId 角色id
	 * @return  
	 */
	public List<BackUserVO> findBackUserByRoleId(int roleId){
		return (List<BackUserVO>)getSqlMapClientTemplate().queryForList("BackUser.findBackUserByRoleId",roleId);
	}
	
}
