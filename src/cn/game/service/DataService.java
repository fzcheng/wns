package cn.game.service;

import java.util.List;

import cn.game.dao.basic.BasicDAO;
import cn.game.inter.service.IDataService;
import cn.game.pay.mmarket.PayCodeVO;
import cn.game.vo.basic.ChannelVO;
import cn.game.vo.basic.GameVO;
import cn.game.vo.basic.HzDayTaskVO;
import cn.game.vo.basic.HzTaskVO;
import cn.game.vo.sms.SmsChannelVO;
import cn.game.vo.sms.SmsGameVO;
import cn.org.util.SpringUtils;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 基础数据服务
 * 
 * @author fzcheng
 */
public class DataService implements IDataService {
	BasicDAO<GameVO> gamedao;
	BasicDAO<PayCodeVO> paycodedao;
	BasicDAO<HzTaskVO> hztaskdao;
	BasicDAO<HzDayTaskVO> hzdaytaskdao;
	
	BasicDAO<ChannelVO> channeldao;
	BasicDAO<SmsGameVO> smsgamedao;
	BasicDAO<SmsChannelVO> smschanneldao;
	
	SqlMapClient MlhSqlMapClient;
	public void init()
	{
		MlhSqlMapClient = (SqlMapClient)SpringUtils.getBean("WnsSqlMapClient");
		//gamedao = new BasicDAO<GameVO>(MlhSqlMapClient, "Game", 300);
		//paycodedao = new BasicDAO<PayCodeVO>(MlhSqlMapClient, "PayCode", 300);
		
		//channeldao = new BasicDAO<ChannelVO>(MlhSqlMapClient, "Channel", 300);
		
		////smsgamedao = new BasicDAO<SmsGameVO>(MlhSqlMapClient, "SmsGame", 300);
		////smschanneldao = new BasicDAO<SmsChannelVO>(MlhSqlMapClient, "SmsChannel", 300);
		////hztaskdao = new BasicDAO<HzTaskVO>(MlhSqlMapClient, "Hz_task", 60);
		////hzdaytaskdao = new BasicDAO<HzDayTaskVO>(MlhSqlMapClient, "Hz_daytask", 60);
		
		//loadAllBasicData();
	}

	@Override
	public void loadAllBasicData() {
		loadGame();
		loadPaycode();
		loadChannel();
		//loadSmsGame();
		//loadSmsChannel();
		//loadHzTask();
		//loadHzDayTask();

	}
	
	@Override
	public GameVO getGameById(String id) {
		return gamedao.getById(id);
	}

	@Override
	public GameVO getGameByMMAppId(String appID) {
		if(appID == null)
			return null;
		List<GameVO> list = gamedao.getList();
		for(GameVO vo : list)
		{
			if(appID.equals(vo.getMmappid()))
				return vo;
		}
		return null;
	}
	
	@Override
	public List<GameVO> getGameList() {
		return gamedao.getList();
	}

	@Override
	public void loadGame() {
		gamedao.loadList();
	}
	
	@Override
	public PayCodeVO getPaycodeById(String id) {
		return paycodedao.getById(id);
	}
	
	@Override
	public List<PayCodeVO> getPaycodeList() {
		return paycodedao.getList();
	}

	@Override
	public void loadPaycode() {
		paycodedao.loadList();
	}
	
	@Override
	public HzTaskVO getHzTaskById(String id) {
		return hztaskdao.getById(id);
	}

	@Override
	public List<HzTaskVO> getHzTaskList() {
		return hztaskdao.getList();
	}

	@Override
	public void loadHzTask() {
		hztaskdao.loadList();
	}
	
	@Override
	public HzDayTaskVO getHzDayTaskById(String id) {
		return hzdaytaskdao.getById(id);
	}
	
	@Override
	public List<HzDayTaskVO> getHzDayTaskList() {
		return hzdaytaskdao.getList();
	}

	@Override
	public void loadHzDayTask() {
		hzdaytaskdao.loadList();
	}
	
	@Override
	public ChannelVO getChannelById(String id) {
		return channeldao.getById(id);
	}

	@Override
	public List<ChannelVO> getChannelList() {
		return channeldao.getList();
	}

	@Override
	public void loadChannel() {
		channeldao.loadList();
	}
	
	@Override
	public SmsGameVO getSmsGameById(String gameid, String channelid) {
		return smsgamedao.getById(gameid + "-" + channelid);
	}
	
	@Override
	public List<SmsGameVO> getSmsGameList() {
		return smsgamedao.getList();
	}

	@Override
	public void loadSmsGame() {
		smsgamedao.loadList();
	}
	
	@Override
	public SmsChannelVO getSmsChannelById(String name) {
		return smschanneldao.getById(name);
	}
	
	@Override
	public List<SmsChannelVO> getSmsChannelList() {
		return smschanneldao.getList();
	}

	@Override
	public void loadSmsChannel() {
		smschanneldao.loadList();
	}

}
