package cn.bill.base;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.bill.base.vo.BillChannelVO;
import cn.bill.base.vo.BillGoodsVO;
import cn.bill.base.vo.BillHaoduanVO;
import cn.bill.base.vo.BillLocationVO;
import cn.bill.base.vo.BillProvinceVO;
import cn.bill.migu.vo.basic.MiguCodeVO;
import cn.game.dao.basic.BasicDAO;
import cn.org.util.SpringUtils;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * bill 基础数据服务
 * 
 * @author fzcheng
 */
public class BillDataService {
	BasicDAO<BillChannelVO> billchanneldao;
	BasicDAO<MiguCodeVO> migucodedao;
	BasicDAO<BillProvinceVO> billprovincedao;
	BasicDAO<BillHaoduanVO> billhaoduandao;
	BasicDAO<BillLocationVO> billlocationdao;
	BasicDAO<BillGoodsVO> billgoodsdao;
	
	SqlMapClient MiguSqlMapClient;
	public void init()
	{
		MiguSqlMapClient = (SqlMapClient)SpringUtils.getBean("MiguSqlMapClient");
		
		billchanneldao = new BasicDAO<BillChannelVO>(MiguSqlMapClient, "BillChannel");
		migucodedao = new BasicDAO<MiguCodeVO>(MiguSqlMapClient, "MiguCode");
		billprovincedao = new BasicDAO<BillProvinceVO>(MiguSqlMapClient, "BillProvince");
		billhaoduandao = new BasicDAO<BillHaoduanVO>(MiguSqlMapClient, "BillHaoduan");
		billlocationdao = new BasicDAO<BillLocationVO>(MiguSqlMapClient, "BillLocation");
		billgoodsdao = new BasicDAO<BillGoodsVO>(MiguSqlMapClient, "BillGoods");
		
		loadAllBasicData();
		
		es.scheduleWithFixedDelay(new DataLoadRunnable(), 300,
				300, TimeUnit.SECONDS);
	}

	public void loadAllBasicData() {
		
		loadBillChannel();
		loadMiguCode();
		loadBillProvince();
		loadBillHaoduan();
		loadBillLocation();
		loadBillGoods();
	}
	
	
	////////////////// Bill basic
	public BillChannelVO getBillChannelById(String name)
	{
		return billchanneldao.getById(name);
	}
	public List<BillChannelVO> getBillChannelList()
	{
		return billchanneldao.getList();
	}
	public void loadBillChannel()
	{
		billchanneldao.loadList();
		
		//计算可用通道
		List<BillChannelVO> billchannellist = getBillChannelList();
		for(BillChannelVO channel : billchannellist)
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
	
	public BillProvinceVO getBillProvinceById(String name)
	{
		return billprovincedao.getById(name);
	}
	public List<BillProvinceVO> getBillProvinceList()
	{
		return billprovincedao.getList();
	}
	public void loadBillProvince()
	{
		billprovincedao.loadList();
	}
	public BillProvinceVO getBillProvinceByPcode(String pcode) {
		List<BillProvinceVO> list = getBillProvinceList();
		for(BillProvinceVO province : list)
		{
			if(province.getImsicode() != null && province.getImsicode().equals(pcode))
			{
				return province;
			}
		}
		return null;
	}
	
	
	public BillHaoduanVO getBillHaoduanById(String name)
	{
		return billhaoduandao.getByKey(name);
	}
	public void loadBillHaoduan()
	{
		//billhaoduandao.loadList();
	}
	
	public BillLocationVO getBillLocationById(String name)
	{
		return billlocationdao.getById(name);
	}
	public List<BillLocationVO> getBillLocationList()
	{
		return billlocationdao.getList();
	}
	public void loadBillLocation()
	{
		billlocationdao.loadList();
	}
	
	public void loadBillGoods()
	{
		billgoodsdao.loadList();
	}
	public BillGoodsVO getBillGoodsByKey(int channelid, String app_id, String fee_code)
	{
		return billgoodsdao.getById(channelid + "-" + app_id + "-" + fee_code);
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
