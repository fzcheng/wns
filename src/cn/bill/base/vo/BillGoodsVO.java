package cn.bill.base.vo;

import cn.game.vo.basic.BasicVO;


/**
 * 商品
 * @author fzc
 *
 */
public class BillGoodsVO extends BasicVO{

	int id;
	int channelid;
	String app_id;
	String fee_code;
	String app_name;
	String fee_name;
	String dev_name;
	String GOODSCODE;
	String GOODSNAME;
	int ORDERAMOUNT;
	
	@Override
	public String getKey() {
		return channelid + "-" + app_id + "-" + fee_code;
	}
	
	public BillGoodsVO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChannelid() {
		return channelid;
	}

	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getFee_code() {
		return fee_code;
	}

	public void setFee_code(String fee_code) {
		this.fee_code = fee_code;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getFee_name() {
		return fee_name;
	}

	public void setFee_name(String fee_name) {
		this.fee_name = fee_name;
	}

	public String getDev_name() {
		return dev_name;
	}

	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}

	public String getGOODSCODE() {
		return GOODSCODE;
	}

	public void setGOODSCODE(String gOODSCODE) {
		GOODSCODE = gOODSCODE;
	}

	public String getGOODSNAME() {
		return GOODSNAME;
	}

	public void setGOODSNAME(String gOODSNAME) {
		GOODSNAME = gOODSNAME;
	}

	public int getORDERAMOUNT() {
		return ORDERAMOUNT;
	}

	public void setORDERAMOUNT(int oRDERAMOUNT) {
		ORDERAMOUNT = oRDERAMOUNT;
	}

}
