package cn.bill.migu.vo.basic;

import cn.game.vo.basic.BasicVO;


/**
 * 咪咕代码
 * @author fzc
 *
 */
public class MiguCodeVO extends BasicVO{

	final public static int TYPE_MIGU = 1;
	
	int codeid;
	int type;
	int SMS_TYPE;
	String SMS_CODE = "";
	String SMS_PORT = "";
	int price;
	String remark = "";
	int isclose;//1-关闭
	int cid;//用于订单中
	
	public MiguCodeVO() {
	}
	
	@Override
	public String getKey() {
		return "" + codeid;
	}

	public int getCodeid() {
		return codeid;
	}

	public void setCodeid(int codeid) {
		this.codeid = codeid;
	}

	public String getSMS_CODE() {
		return SMS_CODE;
	}

	public void setSMS_CODE(String sMS_CODE) {
		SMS_CODE = sMS_CODE;
	}

	public String getSMS_PORT() {
		return SMS_PORT;
	}

	public void setSMS_PORT(String sMS_PORT) {
		SMS_PORT = sMS_PORT;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIsclose() {
		return isclose;
	}

	public void setIsclose(int isclose) {
		this.isclose = isclose;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSMS_TYPE() {
		return SMS_TYPE;
	}

	public void setSMS_TYPE(int sMS_TYPE) {
		SMS_TYPE = sMS_TYPE;
	}

}
