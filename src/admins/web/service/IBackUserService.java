package admins.web.service;

import java.util.List;

import admins.ben.BackUserVO;

/**
 * @author 田建
 * 
 * 14:24:51 
 */
public interface IBackUserService {
	
	/**
	 * 登入检验
	 * @param name	用户名
	 * @param pwd	密码
	 * @return
	 */
	public BackUserVO isAdminLogin(String name, String pwd);
	 /**
	  * 新增后台用户
	  * @param backUserVO
	  * @return
	  */
	public int saveBackUser(BackUserVO backUserVO);
	
	/**
	 * 查询所有的用户
	 * @return
	 */
	public List<BackUserVO> findBackUserList();
	/**
	 * 更新用户
	 * @param backUserVO
	 * @return
	 */
	public int updateBackUser(BackUserVO backUserVO);
	/**
	 * 根据id 查询用户
	 * @param id
	 * @return
	 */
	public BackUserVO getBackUserById(int id);
	/**
	 * 根据id 删除用户
	 * @param id
	 * @return
	 */
	public int delBackUserById(int id);
	/**
	 * 检查用户名
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName);
	/**
	 * 根据roleId获取用户
	 * @param roleId 角色id
	 */
	public List<BackUserVO> findBackUserByRoleId(int roleId);
}
