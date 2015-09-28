package cn.bill.bestpay.v_api.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.game.vo.BaseVO;


/**
 * 交易记录
 * @author fzc
 *
 */
public class BestpayRecordVO extends BaseVO{

	public final static int STATUS_CREATE = 0;//创建
	public final static int STATUS_ING = 1;//允许
	public final static int STATUS_SENDCODE = 2;//发送验证码
	public final static int STATUS_REFUSE = 3;//拦截不允许
	public final static int STATUS_SUCC = 4;//成功
	public final static int STATUS_FAIL = 5;//失败
	public final static int STATUS_ERROR = 6;//出错
	
	int id;
	String create_time = "";
	String modify_time = "";
	int cp_channel_id;//cp id
	String orderid = "";//下游交易号
	String phone = "";
	int price;
	String tid = "";//我们的交易号
	String SMSID = "";//电信账单支付－sdk版本使用 短信流水号
	String TRANDATE = "";//电信账单支付－sdk版本使用
	String uid = "";
	String bu = "";
	String extra = "";
	int status;
	int strategy;
	int transfer_status;//0-未通知 1-通知成功 2-通知失败 3-扣量不通知
	int transfer_count;
	int ip;
	String transfer_url = "";
	int code_channelid;
	String channel_order_id = "";//计费通道交易号
	String app_id = "";
	String fee_code = "";
	String dev_name = "";
	String resultcode = "";
	int province;
	int location;
	String msg;
	
	public BestpayRecordVO() {
	}

	@JsonIgnore
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@JsonIgnore
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@JsonIgnore
	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	@JsonIgnore
	public int getTransfer_status() {
		return transfer_status;
	}

	public void setTransfer_status(int transfer_status) {
		this.transfer_status = transfer_status;
	}

	@JsonIgnore
	public String getTransfer_url() {
		return transfer_url;
	}

	public void setTransfer_url(String transfer_url) {
		if(transfer_url != null)
			this.transfer_url = transfer_url;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@JsonIgnore
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	@JsonIgnore
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@JsonIgnore
	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	@JsonIgnore
	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@JsonIgnore
	public int getStrategy() {
		return strategy;
	}

	public void setStrategy(int strategy) {
		this.strategy = strategy;
	}

	@JsonIgnore
	public int getTransfer_count() {
		return transfer_count;
	}

	public void setTransfer_count(int transfer_count) {
		this.transfer_count = transfer_count;
	}

	@JsonIgnore
	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	@JsonIgnore
	public String getChannel_order_id() {
		return channel_order_id;
	}

	public void setChannel_order_id(String channel_order_id) {
		this.channel_order_id = channel_order_id;
	}

	@JsonIgnore
	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		if(app_id != null)
			this.app_id = app_id;
	}

	@JsonIgnore
	public String getFee_code() {
		return fee_code;
	}

	public void setFee_code(String fee_code) {
		if(fee_code != null)
			this.fee_code = fee_code;
	}

	@JsonIgnore
	public String getDev_name() {
		return dev_name;
	}

	public void setDev_name(String dev_name) {
		if(dev_name != null)
			this.dev_name = dev_name;
	}

	@JsonIgnore
	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	@JsonIgnore
	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	@JsonIgnore
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@JsonIgnore
	public int getCp_channel_id() {
		return cp_channel_id;
	}

	public void setCp_channel_id(int cp_channel_id) {
		this.cp_channel_id = cp_channel_id;
	}

	@JsonIgnore
	public int getCode_channelid() {
		return code_channelid;
	}

	public void setCode_channelid(int code_channelid) {
		this.code_channelid = code_channelid;
	}
	
	@JsonIgnore
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toString()
	{
		return id+","+modify_time+","+create_time+","+cp_channel_id+","+orderid+","+phone+","+price+","+tid+","+uid+","+bu+","+extra+","+
				status+","+strategy+","+transfer_status+","+transfer_count+","+ip+","+transfer_url+","+code_channelid+","+
				channel_order_id+","+app_id+","+fee_code+","+dev_name+","+resultcode+","+province+","+location;
	}

	public String getSMSID() {
		return SMSID;
	}

	public void setSMSID(String sMSID) {
		SMSID = sMSID;
	}

	public String getTRANDATE() {
		return TRANDATE;
	}

	public void setTRANDATE(String tRANDATE) {
		TRANDATE = tRANDATE;
	}
}
