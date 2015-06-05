package cn.game.pay.mmarket;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Trusted2ServQueryResp {

	private String MsgType;//消息类型 填写“Trusted2ServQueryResp”
	private String Version;//该接口消息的版本号,本次所有的 接口消息的版本都为“1.0.0”
	private String TradeID;//外部交易 ID(和请求消息一致)
	private Integer ReturnCode;//返回值。 0:用户已订购,存在对应订单 2:用户未订购,不存在对应订单 11:没有授权调用该能力 12:能力服务忙 13:其他,平台未知错误 17:数字签名不正确 18:请求能力不存在 19:应用不存在
	private String OrderID;//订单 ID,ReturnCode=0 时需要
	private String PayCode;//计费点代码,ReturnCode=0 时需 要
	private String StartDate;//订购的时间,格式为 YYYYMMDDHHMISS, 并且 是 24 小时制。ReturnCode=0 时需要
	private String TotalPrice;//总价(单位为元)
	private String ChannelID;//渠道 ID
	private String EXDATA;//应用自定义参数,可以是应用自定 义的用户 ID,商品 ID 等信息,由 应用在调用计费时传入,透传到应 用服务器
	private String SrcIP;//查询来源的 IP 地址,帮助开发者判 断自己的查询发起 IP 是否在白名单 内
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
	public String getTradeID() {
		return TradeID;
	}
	public void setTradeID(String tradeID) {
		TradeID = tradeID;
	}
	public Integer getReturnCode() {
		return ReturnCode;
	}
	public void setReturnCode(Integer returnCode) {
		ReturnCode = returnCode;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getPayCode() {
		return PayCode;
	}
	public void setPayCode(String payCode) {
		PayCode = payCode;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}
	public String getChannelID() {
		return ChannelID;
	}
	public void setChannelID(String channelID) {
		ChannelID = channelID;
	}
	public String getEXDATA() {
		return EXDATA;
	}
	public void setEXDATA(String exData) {
		EXDATA = exData;
	}
	public String getSrcIP() {
		return SrcIP;
	}
	public void setSrcIP(String srcIP) {
		SrcIP = srcIP;
	}
	//从ExData中获取uid
	@JsonIgnore
	public String getUid() {
		String s[] = EXDATA.split("\\|");
		if(s != null && s.length >= 1)
			return s[0];
		else
			return EXDATA;
	}
	//从ExData中获取uid
	@JsonIgnore
	public String getPlat() {
		String s[] = EXDATA.split("\\|");
		if(s != null && s.length > 1)
			return s[1];
		else
		{
			return "free";
		}
	}
}
