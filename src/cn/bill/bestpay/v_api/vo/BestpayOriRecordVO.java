package cn.bill.bestpay.v_api.vo;

import cn.game.vo.BaseVO;

public class BestpayOriRecordVO extends BaseVO{
	
	long id;
	String create_time = "";
	String modify_time = "";
	
	String orderNo = "";
	String goodsCode = "";
	String phoneNum = "";
	int orderAmount = 0;
	String attach = "";
	String ORDERSEQ = "";
	String ORDERREQTRANSEQ = "";
	String UPTRANSEQ = "";
	int TRANDATE = 0;
	String ORDERAMOUNT2 = "0";
	String RETNCODE = "";
	String RETNINFO = "";
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getORDERSEQ() {
		return ORDERSEQ;
	}
	public void setORDERSEQ(String oRDERSEQ) {
		ORDERSEQ = oRDERSEQ;
	}
	public String getORDERREQTRANSEQ() {
		return ORDERREQTRANSEQ;
	}
	public void setORDERREQTRANSEQ(String oRDERREQTRANSEQ) {
		ORDERREQTRANSEQ = oRDERREQTRANSEQ;
	}
	public String getUPTRANSEQ() {
		return UPTRANSEQ;
	}
	public void setUPTRANSEQ(String uPTRANSEQ) {
		UPTRANSEQ = uPTRANSEQ;
	}
	public int getTRANDATE() {
		return TRANDATE;
	}
	public void setTRANDATE(int tRANDATE) {
		TRANDATE = tRANDATE;
	}
	public String getRETNCODE() {
		return RETNCODE;
	}
	public void setRETNCODE(String rETNCODE) {
		RETNCODE = rETNCODE;
	}
	public String getRETNINFO() {
		return RETNINFO;
	}
	public void setRETNINFO(String rETNINFO) {
		RETNINFO = rETNINFO;
	}
	public String getORDERAMOUNT2() {
		return ORDERAMOUNT2;
	}
	public void setORDERAMOUNT2(String oRDERAMOUNT2) {
		ORDERAMOUNT2 = oRDERAMOUNT2;
	}
}
