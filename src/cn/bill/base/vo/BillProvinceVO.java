package cn.bill.base.vo;

import cn.game.vo.basic.BasicVO;


/**
 * 省份信息
 * @author fzc
 *
 */
public class BillProvinceVO extends BasicVO{

	int id;
	String imsicode = "";
	String modify_time = "";
	String create_time = "";
	String name = "";
	String desc = "";
	int block;
	
	public BillProvinceVO() {
	}
	
	@Override
	public String getKey() {
		return "" + name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public String getImsicode() {
		return imsicode;
	}

	public void setImsicode(String imsicode) {
		this.imsicode = imsicode;
	}
}
