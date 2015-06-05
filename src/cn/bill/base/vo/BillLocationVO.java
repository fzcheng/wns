package cn.bill.base.vo;

import cn.game.vo.basic.BasicVO;

public class BillLocationVO extends BasicVO{

	int id;
	String province = "";
	String location = "";
	int block;
	String remark = "";
	
	@Override
	public String getKey() {
		return province + location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
