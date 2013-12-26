package admins.ben;

import jxl.write.DateTime;
import admins.dao.BackPowerDAO;
import admins.dao.ChannelDAO;
import admins.dao.SectionDAO;
import cn.org.util.SpringUtils;

/**
 * 渠道channel
 */
public class Gamepay_DateVO {

 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountDate() {
		return countDate;
	}
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
	public int getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
	}
	public int getPayCount() {
		return payCount;
	}
	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}
	public int getRechargeCount() {
		return rechargeCount;
	}
	public void setRechargeCount(int rechargeCount) {
		this.rechargeCount = rechargeCount;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		SectionDAO bpDao = (SectionDAO) SpringUtils.getBean("sectiondao");
		SectionVO bpVo = bpDao.getSectionById(sectionId);
		if(bpVo==null){
			return "0";
		}
		return bpVo.getSectionName();
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	
	int id;
	String countDate;
	int moneyType;
	int payCount;
	int rechargeCount;
	String channelId;
	int sectionId;
	String channelName;
	String sectionName;
	
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
