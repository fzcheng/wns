package cn.game.inter.service;

import java.util.List;

import cn.game.pay.mmarket.PayCodeVO;
import cn.game.vo.basic.GameVO;
import cn.game.vo.basic.HzDayTaskVO;
import cn.game.vo.basic.HzTaskVO;

public interface IDataService {
	
	/**
	 * 加载所有基础数据  启动的时候调用
	 */
	public void loadAllBasicData();
	
	public void loadGame();
	
	public List<GameVO> getGameList();

	public GameVO getGameById(String id);
	
	public GameVO getGameByMMAppId(String appID);
	
	public PayCodeVO getPaycodeById(String id);
	public List<PayCodeVO> getPaycodeList();
	public void loadPaycode();
	
	public HzTaskVO getHzTaskById(String id);

	public List<HzTaskVO> getHzTaskList();

	public void loadHzTask();
	
	public HzDayTaskVO getHzDayTaskById(String id);

	public List<HzDayTaskVO> getHzDayTaskList();

	public void loadHzDayTask();
}
