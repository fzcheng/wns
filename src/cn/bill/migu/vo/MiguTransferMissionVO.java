package cn.bill.migu.vo;

import cn.game.vo.BaseVO;


/**
 * 通知任务
 * @author fzc
 *
 */
public class MiguTransferMissionVO extends BaseVO{

	public final static int DEAL_FINISH = 1;
	public final static int DEAL_ING = 2;//通知中
	public final static int DEAL_TRANSFER_EXCEPTION = 3;//通知失败
	public final static int DEAL_TRANSFER_ERROR = 4;//通知失败
	
	public final static int DEAL_ERROR_NOCHANNEL = 101;//无渠道信息
	public final static int DEAL_ERROR_NOURL = 102;	//无通知地址
	public final static int DEAL_ERROR_NORECORD = 103;//无对应订单记录
	public final static int DEAL_ERROR_TRANSFERFAIL = 104;//通知失败
	public final static int DEAL_ERROR_TRANSFERED = 105;//已通知
	public final static int DEAL_ERROR_INVALIDCHANNEL = 106;//渠道无效
	public final static int DEAL_REBATE = 200;//自动扣点
	
	int id;
	String modify_time = "";
	String create_time = "";
	int orderid;
	String music_id = "";
	String msisdn = "";
	String msg = "";
	String rev_date = "";
	int deal;
	
	public MiguTransferMissionVO() {
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

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getMusic_id() {
		return music_id;
	}

	public void setMusic_id(String music_id) {
		this.music_id = music_id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRev_date() {
		return rev_date;
	}

	public void setRev_date(String rev_date) {
		this.rev_date = rev_date;
	}

	public int getDeal() {
		return deal;
	}

	public void setDeal(int deal) {
		this.deal = deal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
