package cn.mbpaysdk.vo;

import javax.servlet.http.HttpServletRequest;

import cn.game.vo.BaseVO;

/**
 * mbirdpaysdk支付记录
 * @author a
 *
 */
public class PayrecordVO extends BaseVO{

	String create_time;
	String modify_time;
	String order_id;
	String uniorder_id;
	int app_id;
	String imsi;
	String phone;
	String imei;
	int entrance_id;
	double fee;
	int channel;
	String extra;
	int status;
	String ip;
	String sign;
	String port;
	String code;
	
	public PayrecordVO()
	{
		
	}
	
	public PayrecordVO(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
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
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getUniorder_id() {
		return uniorder_id;
	}
	public void setUniorder_id(String uniorder_id) {
		this.uniorder_id = uniorder_id;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public int getEntrance_id() {
		return entrance_id;
	}
	public void setEntrance_id(int entrance_id) {
		this.entrance_id = entrance_id;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
