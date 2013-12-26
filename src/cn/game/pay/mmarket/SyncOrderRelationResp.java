package cn.game.pay.mmarket;



/**
 * 
 * @author daikaifeng 2013-4-1
 *
 */
public class SyncOrderRelationResp {
	/**
	 * 消息类型;
		填写“SyncOrderRelationResp”
	 */
	private String MsgType;
	/**
	 * 请求事务编号:
		填写请求消息中的TransactionID值。
	 */
	private String TransactionID;
	/**
	 * 该接口消息的版本号，本次所有的接口消息的版本都为“1.5.0”
	 */
	private String Version;
	/**
	 * 返回值；
		0	成功
		1	其他错误,未知错误
		2	网络异常（该错误指内部子系统之间调用出错）
		9010	网元繁忙
		9015	拒绝消息，服务器无法完成请求的服务
		9018	外部网元的网络错误
		4000	无效的msgtype 
		4003	无效的SP ID 
		4004	无效的serviceID
	 */
	private String hRet;
	
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String gethRet() {
		return hRet;
	}
	public void sethRet(String hRet) {
		this.hRet = hRet;
	}

}
