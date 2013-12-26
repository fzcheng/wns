package admins.web.service.impl;

import java.util.List;

import admins.ben.BackPowerVO;
import admins.ben.BackRolePowerVO;
import admins.dao.BackPowerDAO;
import admins.dao.BackRolePowerDAO;
import admins.web.service.IBackRolePowerService;
import cn.org.util.DateUtil;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRolePowerService implements IBackRolePowerService {

	private BackRolePowerDAO backRolePowerDao;
	private BackPowerDAO backPowerDao;

	public void setBackRolePowerDao(BackRolePowerDAO backRolePowerDao) {
		this.backRolePowerDao = backRolePowerDao;
	}

	public void setBackPowerDao(BackPowerDAO backPowerDao) {
		this.backPowerDao = backPowerDao;
	}

	
	public int delBackRolePowerById(int id) {
		return backRolePowerDao.delBackRoleById(id);
	}

	
	public List<BackRolePowerVO> findBackRolePowerByRoleId(int roleId) {
		return backRolePowerDao.findBackRolePowerByRoleId(roleId);
	}

	
	public List<BackRolePowerVO> findBackRolePowerList() {
		return backRolePowerDao.findBackRolePowerList();
	}

	
	public int saveBackRolePower(BackRolePowerVO backRolePowerVO) {
		return backRolePowerDao.save(backRolePowerVO);
	}

	
	public int updateBackRolePower(BackRolePowerVO backRolePowerVO) {
		return backRolePowerDao.update(backRolePowerVO);
	}

	
	public List<BackRolePowerVO> findBackRolePowerByPowerId(int powerId) {
		return backRolePowerDao.findBackRolePowerByPowerId(powerId);
	}


	
	public void deleteOrSaveRolePower(int roleId, int powerId,
			String checked,String execute,int parentId) {
		
		BackRolePowerVO backRolePowerVO = new BackRolePowerVO();
		backRolePowerVO.setPowerId(powerId);
		backRolePowerVO.setRoleId(roleId);
		if("true".equals(checked)){
			backRolePowerVO.setCreateTime(DateUtil.getCurrentTime());
			backRolePowerDao.save(backRolePowerVO);
			
			if("true".equals(execute)){
				BackRolePowerVO otherBackRolePowerVO = new BackRolePowerVO();
				otherBackRolePowerVO.setRoleId(roleId);
				otherBackRolePowerVO.setPowerId(parentId);
				otherBackRolePowerVO.setPermission(1, true);
				otherBackRolePowerVO.setCreateTime(DateUtil.getCurrentTime());
				backRolePowerDao.save(otherBackRolePowerVO);
			}
			
		}else{
			
			backRolePowerDao.deleteRolePowerByRoleIdAndPowerId(backRolePowerVO);
			
			if("true".equals(execute)){
				BackRolePowerVO otherBackRolePowerVO = new BackRolePowerVO();
				otherBackRolePowerVO.setRoleId(roleId);
				otherBackRolePowerVO.setPowerId(parentId);
				backRolePowerDao.deleteRolePowerByRoleIdAndPowerId(otherBackRolePowerVO);
			}
		}
	}

	
	public boolean hasPermissiona(int roleId, String powerSn, int permission) {
		
		BackPowerVO backPowerVO = backPowerDao.getBackPowerByPowerSn(powerSn);
		
		BackRolePowerVO backRolePowerVO = new BackRolePowerVO();
		backRolePowerVO.setRoleId(roleId);
		backRolePowerVO.setPowerId(backPowerVO.getPowerId());
		//查找角色的授权
		BackRolePowerVO backRolePower = backRolePowerDao.getBackRolePowerByRoleIdAndPowerId(backRolePowerVO);
		if(backRolePower != null){
			return backRolePower.getPermission(permission) == BackRolePowerVO.YES ? true : false;
		}
		
		return false;
	}
	
	
	public int addOrUpdatePermission(int roleId,
			int powerId, int permission, boolean yes,String executeParent,int parentId) {
		
		//根据子类更新父类权限
		if("true".equals(executeParent)){
			BackRolePowerVO parentBackRolePowerVO = new BackRolePowerVO();
			parentBackRolePowerVO.setRoleId(roleId);
			parentBackRolePowerVO.setPowerId(parentId);
			//查找授权对象
			BackRolePowerVO parentBackRolePowerVO2 = backRolePowerDao.getBackRolePowerByRoleIdAndPowerId(parentBackRolePowerVO);
			
			if(true == yes){
				//能够找到授权对象，更新permission
				if(parentBackRolePowerVO2 != null){
					//父类授权为读取，读取权限值为1
					parentBackRolePowerVO2.setPermission(1, yes);
					backRolePowerDao.update(parentBackRolePowerVO2);
				}else{
					//找不到授权对象，则创建授权对象，并更新permission
					parentBackRolePowerVO2 = new BackRolePowerVO();
					parentBackRolePowerVO2.setRoleId(roleId);
					parentBackRolePowerVO2.setPowerId(parentId);
					parentBackRolePowerVO2.setPermission(1, yes);
					parentBackRolePowerVO2.setCreateTime(DateUtil.getCurrentTime());
					backRolePowerDao.save(parentBackRolePowerVO2);
				}
			}else{
				//能够找到授权对象，更新permission
				if(parentBackRolePowerVO2 != null){
					//父类授权为读取，读取权限值为1
					parentBackRolePowerVO2.setPermission(1, yes);
					backRolePowerDao.update(parentBackRolePowerVO2);
				}
			}
		}
		
		/***子类权限操作代码**/
		
		BackRolePowerVO backRolePowerVO = new BackRolePowerVO();
		backRolePowerVO.setRoleId(roleId);
		backRolePowerVO.setPowerId(powerId);
		
		//查找授权对象
		BackRolePowerVO backRolePower = backRolePowerDao.getBackRolePowerByRoleIdAndPowerId(backRolePowerVO);
		
		//能够找到授权对象，更新permission
		if(backRolePower != null){
			backRolePower.setPermission(permission, yes);
			return backRolePowerDao.update(backRolePower);
		}
		
		//找不到授权对象，则创建授权对象，并更新permission
		backRolePowerVO = new BackRolePowerVO();
		backRolePowerVO.setRoleId(roleId);
		backRolePowerVO.setPowerId(powerId);
		backRolePowerVO.setPermission(permission, yes);
		backRolePowerVO.setCreateTime(DateUtil.getCurrentTime());
		
		return backRolePowerDao.save(backRolePowerVO);
	}
	
	public int delPermission( int roleId,int powerId) {
		
		BackRolePowerVO	backRolePowerVO = new BackRolePowerVO();
		backRolePowerVO.setRoleId(roleId);
		backRolePowerVO.setPowerId(powerId);
		return backRolePowerDao.deleteRolePowerByRoleIdAndPowerId(backRolePowerVO);
		
	}
	
	public int delPermission(BackRolePowerVO backRolePowerVO){
		return backRolePowerDao.deleteRolePowerByRoleIdAndPowerId(backRolePowerVO);
	}
	
	public List<BackRolePowerVO> getBrpByRid(int roleId){
		return backRolePowerDao.getBrpByRid(roleId);
	}
	
	public BackRolePowerVO getBackRolePowerByRoleIdAndPowerId(int roleId, int powerId){
		BackRolePowerVO vo = new BackRolePowerVO();
		vo.setRoleId(roleId);
		vo.setPowerId(powerId);
		return backRolePowerDao.getBackRolePowerByRoleIdAndPowerId(vo);
	}

	public boolean hasPermission(int roleId, String functionName){
		List<Integer> powerIds = backPowerDao.getBpVoByUrl(functionName);
		if(powerIds == null || powerIds.size() == 0) return false;
		for (Integer powerId : powerIds) {
			BackRolePowerVO rolePower = backRolePowerDao.getBrpByRidAndPid(roleId, powerId);
			if(rolePower != null){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasPermission(int roleId, int powerId){
		BackRolePowerVO rolePower = backRolePowerDao.getBrpByRidAndPid(roleId, powerId);
		return rolePower != null?true:false;
	}

 

}
