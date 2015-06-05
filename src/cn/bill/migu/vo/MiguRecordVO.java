package cn.bill.migu.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.game.vo.BaseVO;


/**
 * 交易记录
 * @author fzc
 *
 */
public class MiguRecordVO extends BaseVO{

	int id;
	String orderid = "";
	int channelid;
	int codeid;
	String code = "";
	String port = "";
	int rmb;
	String phonenumber;
	String imsi;
	int status;//0-创建 1-成功
	String create_time;
	String modify_time;
	int transfer_status;//0-未通知 1-通知成功 2-通知失败 3-扣量不通知
	String transfer_url;
	
	public MiguRecordVO() {
	}

	@JsonIgnore
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public int getChannelid() {
		return channelid;
	}

	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}

	@JsonIgnore
	public int getCodeid() {
		return codeid;
	}

	public void setCodeid(int codeid) {
		this.codeid = codeid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@JsonIgnore
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
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
		this.transfer_url = transfer_url;
	}

	public int getRmb() {
		return rmb;
	}

	public void setRmb(int rmb) {
		this.rmb = rmb;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}
