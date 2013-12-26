package admins.ben;

import admins.dao.BackPowerDAO;
import admins.dao.ChannelDAO;
import cn.org.util.SpringUtils;

/**
 * 渠道channel
 */
public class TotalGamepayrecordVO {

	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlatId() {
		return platId;
	}
	public void setPlatId(int platId) {
		this.platId = platId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoneytype() {
		return moneytype;
	}
	public void setMoneytype(int moneytype) {
		this.moneytype = moneytype;
	}
	public int getRechargeTime() {
		return rechargeTime;
	}
	public void setRechargeTime(int rechargeTime) {
		this.rechargeTime = rechargeTime;
	}
	public int getPaycount() {
		return paycount;
	}
	public void setPaycount(int paycount) {
		this.paycount = paycount;
	}
	public int getPaySumMoney() {
		return paySumMoney;
	}
	public void setPaySumMoney(int paySumMoney) {
		this.paySumMoney = paySumMoney;
	}
	int id;
	/* 渠道名称  */
	int platId;
	int channelId;
	int sectionId;
	int userId;
	int money;
	int moneytype;
	int rechargeTime;
	int paycount;//充值次数
	int paySumMoney;
	String channelName;
	
	public String getChannelName() {
		ChannelDAO bpDao = (ChannelDAO) SpringUtils.getBean("channeldao");
		ChannelVO bpVo = bpDao.getChannelById(String.valueOf(channelId));
		if(bpVo==null){
			return "未知渠道";
		}
		return bpVo.getChannelName();
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	
/*	BackPowerDAO bpDao = (BackPowerDAO) SpringUtils.getBean("backPowerDao");
	BackPowerVO bpVo = bpDao.getBackPowerById(parentId);*/
	
}
