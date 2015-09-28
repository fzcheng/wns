package cn.bill.migu.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import cn.bill.base.BillDataService;
import cn.bill.base.dao.BillBlockDAO;
import cn.bill.base.service.BillPhoneService;
import cn.bill.base.vo.BillBlockVO;
import cn.bill.base.vo.BillChannelVO;
import cn.bill.base.vo.BillHaoduanVO;
import cn.bill.base.vo.BillLocationVO;
import cn.bill.base.vo.BillPhoneVO;
import cn.bill.base.vo.BillProvinceVO;
import cn.bill.migu.dao.MiguRecordDAO;
import cn.bill.migu.dao.MiguTransferMissionDAO;
import cn.bill.migu.vo.MiguRecordVO;
import cn.bill.migu.vo.MiguTransferMissionVO;
import cn.bill.migu.vo.basic.MiguCodeVO;
import cn.game.lock.LockManager;
import cn.game.service.ReturnMessage;
import cn.game.util.DateUtil;
import cn.game.util.HttpUtils;
import cn.game.util.RandomUtil;

public class MiguService {

	MiguRecordDAO migurecorddao;
	BillBlockDAO billblockdao;
	BillPhoneService billphoneservice;
	
	MiguTransferMissionDAO migutransfermissiondao;
	BillDataService billdataservice;
	
	public void setMigurecorddao(MiguRecordDAO migurecorddao) {
		this.migurecorddao = migurecorddao;
	}
	
	public void setBillblockdao(BillBlockDAO billblockdao) {
		this.billblockdao = billblockdao;
	}
	
	public void setBillphoneservice(BillPhoneService billphoneservice) {
		this.billphoneservice = billphoneservice;
	}

	public void setMigutransfermissiondao(MiguTransferMissionDAO migutransfermissiondao) {
		this.migutransfermissiondao = migutransfermissiondao;
	}
	
	public void setBilldataservice(BillDataService billdataservice) {
		this.billdataservice = billdataservice;
	}
	
	public void init()
	{
		es.scheduleWithFixedDelay(new DealMissionRunnable(), 30,
				30, TimeUnit.SECONDS);
	}
	
	public ReturnMessage requestGetCode(HttpServletRequest request) {
		ReturnMessage rm = new ReturnMessage();
		String phone = (String)request.getParameter("phone");
		String imsi = (String)request.getParameter("imsi");
		if((phone == null || phone.length() != 11) && (imsi == null || imsi.length() != 20))
		{
			rm.setDetail("号码有误");
			return rm;
		}
		synchronized(LockManager.get(phone + imsi))
		{
			String channelid = (String)request.getParameter("channelid");
			String price = (String)request.getParameter("price");
			
			BillChannelVO miguchannel = billdataservice.getBillChannelById(channelid);
			
			if(!(phone == null || phone.length() != 11))
			{
				//判断出是哪个省
				String subphone = phone.substring(0, 7);
				BillHaoduanVO miguhaoduan = billdataservice.getBillHaoduanById(subphone);
				
				if(miguhaoduan != null)
				{
					BillProvinceVO miguprovince = billdataservice.getBillProvinceById(miguhaoduan.getProvince());
					
					if(miguprovince != null && miguprovince.getBlock() == 1)
					{
						rm.setDetail("该省关停");
						return rm;
					}
					
					BillLocationVO migublocklocation = billdataservice.getBillLocationById(miguhaoduan.getProvince() + miguhaoduan.getLocation());
					
					if(migublocklocation != null && miguprovince.getBlock() == 1)
					{
						rm.setDetail("该市关停");
						return rm;
					}
					
					BillPhoneVO miguphone = billphoneservice.getByPhone(phone);
					if(miguphone != null)
					{

					}
				}
				
				BillBlockVO migublock = billblockdao.getByPhone(phone);
				
				if(migublock != null && migublock.getOpened() == 0)
				{
					//黑名单
					rm.setDetail("用户被屏蔽");
					return rm;
				}
			}
			else if(!(imsi == null || imsi.length() != 20))
			{
				//判断出是哪个省  移动
				//00 移动  01－中国联通  03-电信
				String pcode = imsi.substring(8, 10);
				
				BillProvinceVO miguprovince = billdataservice.getBillProvinceByPcode(pcode);
				
				if(miguprovince != null && miguprovince.getBlock() == 1)
				{
					rm.setDetail("该省关停");
					return rm;
				}
				
				BillPhoneVO miguphone = billphoneservice.getByImsi(imsi);
				if(miguphone != null)
				{
					
				}
				
				BillBlockVO migublock = billblockdao.getByImsi(imsi);
				
				if(migublock != null && migublock.getOpened() == 0)
				{
					//黑名单
					rm.setDetail("用户被屏蔽");
					return rm;
				}
			}
			
			//dataservice.getMiguProvinceById();
			//获取用户数据
			
			//从配置中获取一个通道
			String codeidsStr = miguchannel.getCodeids(); 
			if(codeidsStr == null || "".equals(codeidsStr))
			{
				//无可用通道
				rm.setDetail("无可用通道");
				return rm;
			}
			
			//查找可用通道
			MiguCodeVO migucode = getValidMiguCode(miguchannel, Integer.valueOf(price));
			if(migucode == null)
			{
				//配置错误
				rm.setDetail("无可用通道,检查参数");
				return rm;
			}
			
			synchronized(LockManager.get("migucode_" + migucode.getKey()))
			{
				migucode.setCid(migucode.getCid() + 1);
				billdataservice.updateMiguCode(migucode);
			}
			MiguRecordVO migurecord = new MiguRecordVO();
			migurecord.setChannelid(miguchannel.getChannel_id());
			switch(migucode.getType())
			{
				case MiguCodeVO.TYPE_MIGU:
					//组装code
					String code = migucode.getSMS_CODE();
					String raplace = "0000" + migucode.getCid();
					raplace = raplace.substring(raplace.length()-4, raplace.length());
					code = code.replace("CCCC", raplace);
					migurecord.setCode(code.replace("CCCC", "" + migucode.getCid()));
					break;
				default:
					break;
			}
			migurecord.setOrderid(""+DateUtil.getCurrentTimeStrs() + "" + RandomUtil.getIntRandom(1000, 9000));
			migurecord.setPort(migucode.getSMS_PORT());
			migurecord.setCodeid(migucode.getCodeid());
			migurecord.setCreate_time(DateUtil.getCurrentTime());
			migurecord.setImsi(imsi);
			migurecord.setModify_time(DateUtil.getCurrentTime());
			migurecord.setPhonenumber(phone);
			migurecord.setRmb(migucode.getPrice());
			migurecord.setStatus(0);
			migurecord.setTransfer_status(0);
			migurecord.setTransfer_url(miguchannel.getTransfer_url());
			
			migurecord.setId(migurecorddao.save(migurecord));
			
			rm.setObject(migurecord);
			
			rm.setResult(true);
		}
		
		return rm;
	}

	private void dealTransferMission() {
		List<MiguTransferMissionVO> list = migutransfermissiondao.getUnDealList();
		System.out.println("undeal:" + list.size());
		
		for(MiguTransferMissionVO vo : list)
		{
			//根据msg 找到原来请求的订单
			String msg = vo.getMsg().substring(0, 36);
			System.out.println();
			MiguRecordVO migurecord = migurecorddao.getByCode(msg);
			//根据channelid 找到需要通知的地址
			if(migurecord == null)
			{
				//更新deal状态
				vo.setDeal(MiguTransferMissionVO.DEAL_ERROR_NORECORD);
				migutransfermissiondao.update(vo);
				continue;
				
			}
			BillChannelVO miguchannel = billdataservice.getBillChannelById(""+migurecord.getChannelid());
			if(miguchannel != null)
			{
				if(miguchannel.getStatus() != 1)
				{
					//更新状态 渠道未启用
					updateMissionAndRecord(vo, migurecord, MiguTransferMissionVO.DEAL_ERROR_INVALIDCHANNEL);
					continue;
				}
				//更新手机号码 和 imsi 分别的扣费记录
				
				//通知地址
				String url = miguchannel.getTransfer_url();
				
				if(url == null || url.length() < 20)
				{
					//更新状态 无url数据
					updateMissionAndRecord(vo, migurecord, MiguTransferMissionVO.DEAL_ERROR_NOURL);
					continue;
				}
				
				//自动扣点
				int rand = RandomUtil.getIntRandom(100);
				if(rand > miguchannel.getP())
				{
					//更新状态 自动扣点
					updateMissionAndRecord(vo, migurecord, MiguTransferMissionVO.DEAL_ING);
					
					//transfer 
					String jsonData = "{\"orderid\":" + migurecord.getOrderid() + ", \"status\":0}";
					try {
						String result = HttpUtils.URLPost_JSON(url, jsonData);
						
						if(result != null && "ok".equals(result))
						{
							//更新mission 通知成功
							updateMissionAndRecord(vo, migurecord, MiguTransferMissionVO.DEAL_FINISH);
						}
						else
						{
							//更新mission 通知失败
							updateMissionAndRecord(vo, migurecord, MiguTransferMissionVO.DEAL_TRANSFER_ERROR);
						}
					} catch (IOException e) {
						e.printStackTrace();
						
						//更新mission 通知异常
						updateMissionAndRecord(vo, migurecord, MiguTransferMissionVO.DEAL_TRANSFER_EXCEPTION);
					}
					
				}
				else
				{
					//更新状态 自动扣点
					updateMissionAndRecord(vo, migurecord, MiguTransferMissionVO.DEAL_REBATE);
				}
			}
			else
			{
				//更新状态 无渠道数据
				updateMissionAndRecord(vo, migurecord, MiguTransferMissionVO.DEAL_ERROR_NOCHANNEL);
			}
		}
	}
	
	public void updateMissionAndRecord(MiguTransferMissionVO missionvo, MiguRecordVO recordvo, int status)
	{
		//更新mission deal状态
		missionvo.setDeal(status);
		migutransfermissiondao.update(missionvo);
		
		//更新record状态 updateStatus
		recordvo.setTransfer_status(status);
		migurecorddao.updateStatus(recordvo);
	}

	private MiguCodeVO getValidMiguCode(BillChannelVO miguchannel, int price) {
		List<MiguCodeVO> list = miguchannel.getMiguCodeList();
		if(list == null || list.size() < 1)
			return null;
		
//		Collections.sort(list,new Comparator<MiguCodeVO>(){
//			public int compare(MiguCodeVO o1, MiguCodeVO o2) {
//				return o1.getCid()-o2.getCid();
//			}
//		});
		
		int rand = RandomUtil.getIntRandom(list.size());
		for(int i = 0; i < list.size(); i ++)
		{
			MiguCodeVO migucode = list.get((i + rand) % list.size());
			//价格
			if(migucode.getPrice() != price)
				continue;
			//判断分省设置
			
			
			return migucode;
		}
		return null;
	}
	
	
	private ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

	/**
	 * 定时加载数据线程
	 */
	class DealMissionRunnable implements Runnable {

		@Override
		public void run() {
			try {
				dealTransferMission();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
