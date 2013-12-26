package admins.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import admins.ben.BackPowerVO;
import admins.ben.OptionBean;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackPowerDAO extends SqlMapClientDaoSupport{
	private static Map<String,BackPowerVO> funcMap = null;
	
	private void loadFuncMap(){
		if(funcMap == null) funcMap = new HashMap<String, BackPowerVO>();
		
		List<BackPowerVO> funcList = getFucList();
		
		for (BackPowerVO vo : funcList) {
			funcMap.put(vo.getLinkUrl(), vo);
		}
	}
	
	public List<Integer> getBpVoByUrl(String url){
		if(funcMap == null) loadFuncMap();
		List<Integer> result = new ArrayList<Integer>();
		for (String str : funcMap.keySet()) {
			if(str.indexOf(url+",") != -1){
				result.add(funcMap.get(str).getPowerId());
			}
		}
		return result;
	}
	
	/**
	 * 增加一个模块
	 * @param backPowerVO
	 */
	public int save(BackPowerVO backPowerVO){
		return (Integer)getSqlMapClientTemplate().insert("BackPower.save",backPowerVO);
	}
	/**
	 * 更新模块
	 * @param backPowerVO
	 */
	public int update(BackPowerVO backPowerVO){
		return getSqlMapClientTemplate().update("BackPower.update",backPowerVO);
	}
	
	/**
	 * 根据id 获取模块
	 * @param id
	 */
	public BackPowerVO getBackPowerById(int id){
		return (BackPowerVO)getSqlMapClientTemplate().queryForObject("BackPower.getBackPowerById",id);
	}
	
	/**
	 * 根据id删除模块
	 * @param id
	 */
	public int delBackPowerById(int id){
		return getSqlMapClientTemplate().delete("BackPower.delete",id);
	}
	/**
	 * 获取所有模块
	 * @param id
	 */
	public List<BackPowerVO> findBackPowerList(){
		return (List<BackPowerVO>)getSqlMapClientTemplate().queryForList("BackPower.findBackPowerList");
	}
	/**
	 * 根据roleId 查询权限信息
	 * @param roleId	角色id
	 * @return
	 */
	public List<BackPowerVO> findBackPowerListByRoleId(int roleId){
		return (List<BackPowerVO>)getSqlMapClientTemplate().queryForList("BackPower.findBackPowerListByRoleId",roleId);
	}
	
	/**
	 * 根据parentId 查询权限信息
	 * @param parentId	父类id
	 * @return
	 */
	public List<BackPowerVO> findBackPowerListByParentId(int parentId){
		return (List<BackPowerVO>)getSqlMapClientTemplate().queryForList("BackPower.findBackPowerListByParentId",parentId);
	}
	/**
	 * 分页获取所有模块
	 * @param startIndex 起始数
	 * @return
	 */
	public List<BackPowerVO> findBackPowerByPage(BackPowerVO backPowerVO){
		return (List<BackPowerVO>)getSqlMapClientTemplate().queryForList("BackPower.findBackPowerByPage",backPowerVO);
	}
	/**
	 * 获取所有数量
	 * @return
	 */
	public int getCountByParentId(int parentId){
		return (Integer)getSqlMapClientTemplate().queryForObject("BackPower.getCountByParentId",parentId);
	}
	
	public List<BackPowerVO> findBackPowerByPowerId(List powerIdList){
		return (List<BackPowerVO>)getSqlMapClientTemplate().queryForList("BackPower.findBackPowerByPowerId",powerIdList);
	}
	/**
	 * 根据模块标示获取模块信息
	 * @param powerSn
	 * @return
	 */
	public BackPowerVO getBackPowerByPowerSn(String powerSn){
		return (BackPowerVO)getSqlMapClientTemplate().queryForObject("BackPower.getBackPowerByPowerSn",powerSn);
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionBean> getFucListByParentId(int parentId, int type){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("type", type);
		return getSqlMapClientTemplate().queryForList("BackPower.getFucListByParentId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BackPowerVO> getFucList(){
		return getSqlMapClientTemplate().queryForList("BackPower.getFucList");
	}
}
