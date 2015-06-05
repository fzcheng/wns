package cn.game.inter.service;

import java.util.List;

import cn.game.pay.mmarket.PayCodeVO;
import cn.game.vo.basic.ChannelVO;
import cn.game.vo.basic.GameVO;
import cn.game.vo.basic.HzDayTaskVO;
import cn.game.vo.basic.HzTaskVO;
import cn.game.vo.sms.SmsChannelVO;
import cn.game.vo.sms.SmsGameVO;

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
	
	public ChannelVO getChannelById(String id);
	public List<ChannelVO> getChannelList();
	public void loadChannel();
	public SmsGameVO getSmsGameById(String gameid, String channelid);
	public List<SmsGameVO> getSmsGameList();
	public void loadSmsGame();


	public SmsChannelVO getSmsChannelById(String name);
	public List<SmsChannelVO> getSmsChannelList();
	public void loadSmsChannel();
	
}
