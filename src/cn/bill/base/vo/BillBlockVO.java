package cn.bill.base.vo;

import cn.game.vo.BaseVO;


/**
 * 黑名单
 * @author fzc
 *
 */
public class BillBlockVO extends BaseVO{

	String phonenum;
	String imsi = "";
	String reason = "";
	int opened;//1-解封
	
	public BillBlockVO() {
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getOpened() {
		return opened;
	}

	public void setOpened(int opened) {
		this.opened = opened;
	}

}
