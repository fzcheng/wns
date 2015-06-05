package cn.bill.migu.service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.bill.base.vo.BillChannelVO;
import cn.bill.base.vo.BillHaoduanVO;
import cn.bill.base.vo.BillLocationVO;
import cn.bill.base.vo.BillProvinceVO;
import cn.bill.migu.vo.basic.MiguCodeVO;
import cn.game.dao.basic.BasicDAO;
import cn.org.util.SpringUtils;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * migu igop基础数据服务
 * 
 * @author fzcheng
 */
public class MiguDataService_back {
	BasicDAO<BillChannelVO> miguchanneldao;
	BasicDAO<MiguCodeVO> migucodedao;
	BasicDAO<BillProvinceVO> miguprovincedao;
	BasicDAO<BillHaoduanVO> miguhaoduandao;
	BasicDAO<BillLocationVO> migulocationdao;
	
	SqlMapClient MiguSqlMapClient;
	public void init()
	{
		MiguSqlMapClient = (SqlMapClient)SpringUtils.getBean("MiguSqlMapClient");
		
		miguchanneldao = new BasicDAO<BillChannelVO>(MiguSqlMapClient, "MiguChannel");
		migucodedao = new BasicDAO<MiguCodeVO>(MiguSqlMapClient, "MiguCode");
		miguprovincedao = new BasicDAO<BillProvinceVO>(MiguSqlMapClient, "MiguProvince");
		miguhaoduandao = new BasicDAO<BillHaoduanVO>(MiguSqlMapClient, "MiguHaoduan");
		migulocationdao = new BasicDAO<BillLocationVO>(MiguSqlMapClient, "MiguLocation");
		
		loadAllBasicData();
		
		es.scheduleWithFixedDelay(new DataLoadRunnable(), 300,
				300, TimeUnit.SECONDS);
	}

	public void loadAllBasicData() {
		
		loadMiguChannel();
		loadMiguCode();
		loadMiguProvince();
		loadMiguHaoduan();
		loadMiguLocation();
	}
	
	
	////////////////// migu basic
	public BillChannelVO getMiguChannelById(String name)
	{
		return miguchanneldao.getById(name);
	}
	public List<BillChannelVO> getMiguChannelList()
	{
		return miguchanneldao.getList();
	}
	public void loadMiguChannel()
	{
		miguchanneldao.loadList();
		
		//计算可用通道
		List<BillChannelVO> miguchannellist = getMiguChannelList();
		for(BillChannelVO channel : miguchannellist)
		{
			String codestrs[] = channel.getCodeids().split(",");
			if(codestrs == null || codestrs.length < 1)
				continue;
			for(String codestr: codestrs)
			{
				MiguCodeVO migucode = getMiguCodeById(codestr);
				if(migucode != null && migucode.getIsclose() == 0)
					channel.addMiguCode(migucode);
			}
		}
	}
	
	public MiguCodeVO getMiguCodeById(String name)
	{
		return migucodedao.getByKey(name);
	}
	public List<MiguCodeVO> getMiguCodeList()
	{
		return migucodedao.getList();
	}
	public void loadMiguCode()
	{
		migucodedao.loadList();
	}
	public void updateMiguCode(MiguCodeVO migucode)
	{
		migucodedao.update(migucode);
	}
	
	public BillProvinceVO getMiguProvinceById(String name)
	{
		return miguprovincedao.getById(name);
	}
	public List<BillProvinceVO> getMiguProvinceList()
	{
		return miguprovincedao.getList();
	}
	public void loadMiguProvince()
	{
		miguprovincedao.loadList();
	}
	public BillProvinceVO getMiguProvinceByPcode(String pcode) {
		List<BillProvinceVO> list = getMiguProvinceList();
		for(BillProvinceVO province : list)
		{
			if(province.getImsicode() != null && province.getImsicode().equals(pcode))
			{
				return province;
			}
		}
		return null;
	}
	
	
	public BillHaoduanVO getMiguHaoduanById(String name)
	{
		return miguhaoduandao.getByKey(name);
	}
	public void loadMiguHaoduan()
	{
		//miguhaoduandao.loadList();
	}
	
	public BillLocationVO getMiguLocationById(String name)
	{
		return migulocationdao.getById(name);
	}
	public List<BillLocationVO> getMiguLocationList()
	{
		return migulocationdao.getList();
	}
	public void loadMiguLocation()
	{
		migulocationdao.loadList();
	}
	
	private ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

	/**
	 * 定时加载数据线程
	 */
	class DataLoadRunnable implements Runnable {

		@Override
		public void run() {
			try {
				loadAllBasicData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
