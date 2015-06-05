package cn.bill.base.vo;

import cn.game.vo.BaseVO;


/**
 * 用户-手机号 信息
 * @author fzc
 *
 */
public class BillPhoneVO extends BaseVO{

	String phonenum;
	String imsi = "";
	int provinceid;
	String create_time = "";
	String last_time = "";
	int totalcount;
	int totalprice;
	int isblock;
	int monthcount;
	
	public BillPhoneVO() {
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


	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	public String getLast_time() {
		return last_time;
	}


	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}


	public int getTotalcount() {
		return totalcount;
	}


	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}


	public int getTotalprice() {
		return totalprice;
	}


	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}


	public int getIsblock() {
		return isblock;
	}


	public void setIsblock(int isblock) {
		this.isblock = isblock;
	}


	public int getMonthcount() {
		return monthcount;
	}


	public void setMonthcount(int monthcount) {
		this.monthcount = monthcount;
	}

	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

}
