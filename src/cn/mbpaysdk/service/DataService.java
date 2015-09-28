package cn.mbpaysdk.service;

import java.util.List;

import cn.game.dao.basic.BasicDAO;
import cn.mbpaysdk.vo.basic.MappbaseVO;
import cn.mbpaysdk.vo.basic.MentranceVO;
import cn.org.util.SpringUtils;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 基础数据服务
 * 
 * @author fzcheng
 */
public class DataService implements IDataService {

	BasicDAO<MappbaseVO> appbasedao;
	BasicDAO<MentranceVO> entrancedao;

	
	SqlMapClient mbirdtechSqlMapClient;
	public void init()
	{
		mbirdtechSqlMapClient = (SqlMapClient)SpringUtils.getBean("MbirdtechSqlMapClient");
		appbasedao = new BasicDAO<MappbaseVO>(mbirdtechSqlMapClient, "AppBase", 300);
		entrancedao = new BasicDAO<MentranceVO>(mbirdtechSqlMapClient, "Entrance", 300);
		
		loadAllBasicData();
	}

	@Override
	public void loadAllBasicData() {
		loadAppbase();
		loadEntrance();
	}
	
	@Override
	public MappbaseVO getAppbaseById(String id) {
		return appbasedao.getById(id);
	}

	@Override
	public List<MappbaseVO> getAppbaseList() {
		return appbasedao.getList();
	}

	@Override
	public void loadAppbase() {
		appbasedao.loadList();
	}
	
	@Override
	public MentranceVO getEntranceById(String id) {
		return entrancedao.getById(id);
	}

	@Override
	public List<MentranceVO> getEntranceList() {
		return entrancedao.getList();
	}

	@Override
	public void loadEntrance() {
		entrancedao.loadList();
	}

	/**
	 * 根据Category和Fee 查找可用的代码
	 */
	@Override
	public List<MentranceVO> getEntranceByCategoryAndFee(int mobileType, int fee) {
		
		return null;
	}
	
}
