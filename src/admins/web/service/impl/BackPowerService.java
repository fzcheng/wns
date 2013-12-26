package admins.web.service.impl;

import java.util.List;

import admins.PageI;
import admins.ben.BackPowerVO;
import admins.ben.BackUserVO;
import admins.ben.OptionBean;
import admins.dao.BackPowerDAO;
import admins.dao.BackRolePowerDAO;


public class BackPowerService {

	private BackPowerDAO backPowerDao;
	private BackRolePowerDAO backRolePowerDao;

	public void setBackPowerDao(BackPowerDAO backPowerDao) {
		this.backPowerDao = backPowerDao;
	}

	public void setBackRolePowerDao(BackRolePowerDAO backRolePowerDao) {
		this.backRolePowerDao = backRolePowerDao;
	}

	
	public int delBackPowerById(int id) {
		return backPowerDao.delBackPowerById(id);
	}

	
	public List<BackPowerVO> findBackPowerList() {
		return backPowerDao.findBackPowerList();
	}

	
	public int saveBackPower(BackPowerVO backPowerVO) {
		return backPowerDao.save(backPowerVO);
	}

	
	public int updateBackPower(BackPowerVO backPowerVO) {
		return backPowerDao.update(backPowerVO);
	}

	
	public List<BackPowerVO> findBackPowerListByRoleId(int roleId) {
		return backPowerDao.findBackPowerListByRoleId(roleId);
	}

	
	public BackPowerVO getBackPowerById(int powerId) {
		return backPowerDao.getBackPowerById(powerId);
	}

	
	public List<BackPowerVO> findBackPowerListByParentId(int parentId) {
		return backPowerDao.findBackPowerListByParentId(parentId);
	}

	
	public void findBackPowerByPage(PageI pageI,BackPowerVO backPowerVO) {
		int limitnumber = backPowerDao.getCountByParentId(backPowerVO.getParentId());
		backPowerVO.setPowerId(pageI.getLimit());
		List list = backPowerDao.findBackPowerByPage(backPowerVO);
		//当子类时赋值父类权限名
		if(0 != backPowerVO.getParentId()){
			BackPowerVO backPowerParent = backPowerDao.getBackPowerById(backPowerVO.getParentId());
			if(null != backPowerParent){
				int size = list.size();
				for(int i=0;i<size;i++){
					BackPowerVO backPowerChild = (BackPowerVO)list.get(i);
					backPowerChild.setParentName(backPowerParent.getPowerName());
				}
			}
		}
		
		pageI.build(list, limitnumber);
	}

	
	public List<BackPowerVO> searchModules(BackUserVO backUserVO) {
		
		List<BackPowerVO> backPowerVOList;
		int roleId = backUserVO.getRoleId();
		if(0 == roleId){
			backPowerVOList = this.findBackPowerList();
		}else{
			backPowerVOList = backPowerDao.findBackPowerListByRoleId(roleId);
		}
		return backPowerVOList;
	}

	
	public BackPowerVO getBackPowerByPowerSn(String powerSn) {
		return backPowerDao.getBackPowerByPowerSn(powerSn);
	}
	
 	public List<OptionBean> getFucListByParentId(int parentId, int type){
 		return backPowerDao.getFucListByParentId(parentId, type);
 	}
	
	public List<Integer> getBpVoByUrl(String url){
		return backPowerDao.getBpVoByUrl(url);
	}

}
