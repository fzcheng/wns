package cn.bill.bestpay.v_sdk.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.game.vo.BaseVO;


/**
 * 请求短信内容 返回数据
 * @author fzc
 *
 */
public class GetCodeResultVO extends BaseVO{

	String create_time = "";
	String orderid = "";//下游交易号
	String tid = "";//我们的交易号
	String msg = "";
	String dst = "";
	
	public GetCodeResultVO() {
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
