package cn.bill.base.vo;

import cn.game.vo.basic.BasicVO;


/**
 * 渠道
 * @author fzc
 *
 */
public class BillHaoduanVO extends BasicVO{

	int id;
	String haoduan = "";
	String flag = "";
	String province = "";
	String location = "";
	String type = "";
	
	public BillHaoduanVO() {
	}


	@Override
	public String getKey() {
		return "" + haoduan;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getHaoduan() {
		return haoduan;
	}


	public void setHaoduan(String haoduan) {
		this.haoduan = haoduan;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
}
