package admins.web.service;

import java.util.List;

import admins.ben.BackRolePowerVO;

/**
 * @author 田建
 * 
 * 14:24:51 
 */
public interface IBackRolePowerService {
	
	 /**
	  * 新增角色权限记录
	  * @param backRolePowerVO
	  * @return
	  */
	public int saveBackRolePower(BackRolePowerVO backRolePowerVO);
	
	/**
	 * 查询所有的角色权限记录
	 * @return
	 */
	public List<BackRolePowerVO> findBackRolePowerList();
	/**
	 * 更新角色权限记录
	 * @param backRoleVO
	 * @return
	 */
	public int updateBackRolePower(BackRolePowerVO backRolePowerVO);
	/**
	 * 根据id删除角色权限记录
	 * @param id
	 * @return
	 */
	public int delBackRolePowerById(int id);
	/**
	 * 根据角色id查询角色权限记录
	 * @param roleId 角色id
	 * @return
	 */
	public List<BackRolePowerVO> findBackRolePowerByRoleId(int roleId);
	
	/**
	 * 根据权限id获取角色和权限关系记录
	 * @return 
	 */
	public List<BackRolePowerVO> findBackRolePowerByPowerId(int powerId);

	/**
	 * 根据 roleId,powerId,checked 增加或者删除角色的权限
	 * @param roleId
	 * @param powerId
	 * @param checked
	 */
	public void deleteOrSaveRolePower(int roleId,int powerId,String checked,String execute,int parentId);
	
	/**
	 * 即时认证，判断某个角色是否拥有对某个模块的某个操作的权限
	 * @param roleId 角色标识
	 * @param powerSn 模块标识
	 * @param permission 操作标识（C/R/U/D）
	 * @return 允许或不允许
	 */
	public boolean hasPermissiona(int roleId,String powerSn,int permission);
	
	/**
	 * 根据roleId,powerId,premission,yes 更新或者增加授权对象
	 * @param roleId	角色id
	 * @param powerId	权限id
	 * @param permission 更新或者增加授权状态	
	 * @param yes 取消或者增加
	 * @return
	 */
	public int addOrUpdatePermission(int roleId,int powerId, int permission, boolean yes,String executeParent,int parentId);
	/**
	 * 根据roleId,powerId 删除授权对象
	 * @param roleId	角色id
	 * @param powerId	权限id
	 * @return
	 */
	public int delPermission( int roleId,int powerId);
	
	public int delPermission(BackRolePowerVO backRolePowerVO);
	
	/**
	 * 根据权限编号查询方法列表
	 * @param roleId
	 * @return
	 * @author LuZhiYong
	 * @Date 2012-7-19
	 */
	public List<BackRolePowerVO> getBrpByRid(int roleId);
	
	public boolean hasPermission(int roleId, String functionName);
	
	public boolean hasPermission(int roleId, int powerId);
	
	public BackRolePowerVO getBackRolePowerByRoleIdAndPowerId(int roleId, int powerId);
	
	
}
