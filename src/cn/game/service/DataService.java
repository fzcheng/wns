package cn.game.service;

import java.util.List;

import cn.game.dao.basic.BasicDAO;
import cn.game.inter.service.IDataService;
import cn.game.pay.mmarket.PayCodeVO;
import cn.game.vo.basic.GameVO;
import cn.game.vo.basic.HzDayTaskVO;
import cn.game.vo.basic.HzTaskVO;
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
	
	SqlMapClient MlhSqlMapClient;
	public void init()
	{
		MlhSqlMapClient = (SqlMapClient)SpringUtils.getBean("WnsSqlMapClient");
		gamedao = new BasicDAO<GameVO>(MlhSqlMapClient, "Game", 300);
		paycodedao = new BasicDAO<PayCodeVO>(MlhSqlMapClient, "PayCode", 300);
		//hztaskdao = new BasicDAO<HzTaskVO>(MlhSqlMapClient, "Hz_task", 60);
		//hzdaytaskdao = new BasicDAO<HzDayTaskVO>(MlhSqlMapClient, "Hz_daytask", 60);
		
		loadAllBasicData();
	}

	@Override
	public void loadAllBasicData() {
		loadGame();
		loadPaycode();
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
}
