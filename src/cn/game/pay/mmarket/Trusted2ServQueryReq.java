package cn.game.pay.mmarket;

public class Trusted2ServQueryReq {
	private String MsgType;//消息类型,填写 “Trusted2ServQueryReq”
	private String Version;//该接口消息的版本号,本次所有 的接口消息的版本都为“1.0.0”
	private String AppID;//能力应用 ID
	private String OrderID;//订单 ID
	private String TradeID;//外部交易 ID
	private Integer OrderType;//订单类型(默认为 0) 0:测试订单 1:正式订单
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getAppID() {
		return AppID;
	}
	public void setAppID(String appID) {
		AppID = appID;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getTradeID() {
		return TradeID;
	}
	public void setTradeID(String tradeID) {
		TradeID = tradeID;
	}
	public Integer getOrderType() {
		return OrderType;
	}
	public void setOrderType(Integer orderType) {
		OrderType = orderType;
	}
}
