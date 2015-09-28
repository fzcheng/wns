package cn.mbpaysdk.service;

import java.util.List;

import cn.mbpaysdk.vo.basic.MappbaseVO;
import cn.mbpaysdk.vo.basic.MentranceVO;

public interface IDataService {
	
	/**
	 * 加载所有基础数据  启动的时候调用
	 */
	public void loadAllBasicData();
	public MappbaseVO getAppbaseById(String id);
	public List<MappbaseVO> getAppbaseList();
	public void loadAppbase();
	
	public MentranceVO getEntranceById(String id);
	public List<MentranceVO> getEntranceList();
	public void loadEntrance();
	
	public List<MentranceVO> getEntranceByCategoryAndFee(int mobileType, int fee);
}
