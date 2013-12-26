/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:07:25
 */
package cn.game.pay.mmarket;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * MMIAP支付服务器通知支付结果信息bean
 * <p>
 * 对于需要校验的字段，可以在对应属性名称的set方法中进行
 * </p>
 * @author a
 * @dateTime 2013-4-28 下午4:07:25
 */
@XmlRootElement(name = "SyncAppOrderReq", namespace = "http://www.monternet.com/dsmp/schemas/")
public class SyncAppOrderReq implements Serializable {

//	<SyncAppOrderReq xmlns="http://www.monternet.com/dsmp/schemas/">
//	<TransactionID>CSSP12955889</TransactionID>
//	<MsgType>SyncAppOrderReq</MsgType>
//	<Version>1.0.0</Version>
//	<Send_Address>
//	<DeviceType>200</DeviceType>
//	<DeviceID>CSSP</DeviceID>
//	</Send_Address>
//	<Dest_Address>
//	<DeviceType>1002</DeviceType>
//	<DeviceID>s1_0</DeviceID>
//	</Dest_Address>
//	<OrderID>11131218171222487832</OrderID>
//	<CheckID>0</CheckID>
//	<ActionTime>20131218171222</ActionTime>
//	<ActionID>1</ActionID>
//	<MSISDN></MSISDN>
//	<FeeMSISDN>E6CE08A6B26B82C8</FeeMSISDN>
//	<AppID>300005292017</AppID>
//	<PayCode>30000529201701</PayCode>
//	<TradeID>4D897940DD62499BF3C8B02054730900</TradeID>
//	<Price>10</Price>
//	<TotalPrice>10</TotalPrice>
//	<SubsNumb>1</SubsNumb>
//	<SubsSeq>1</SubsSeq>
//	<ChannelID>0000000000</ChannelID>
//	<ExData>Hello_My_ExData</ExData>
//	<OrderType>0</OrderType>
//	<MD5Sign>21290E3ED59DC09A317C749BD3507997</MD5Sign>
//	<OrderPayment>1</OrderPayment>
//	</SyncAppOrderReq>
	/**
	 * @author a
	 * @dateTime 2013-4-28 上午11:47:08
	 */
	private static final long serialVersionUID = -5719870901076921628L;

	private String TransactionID;
	private String MsgType;//消息类型

	private String Version;//版本号

	private Address_Info_Schema Send_Address;//发送方地址

	private Address_Info_Schema Dest_Address;//接收方地址

	private String OrderID;//订单编号

	private Integer CheckID;//开放平台订购，鉴权接口中的CheckID

	private String TradeID;//外部交易ID

	private Integer Price;//业务资费(分)

	private String ActionTime;//操作时间

	private Integer ActionID;//操作代码

	private String MSISDN;//目标用户手机号码

	private String FeeMSISDN;//计费手机号码

	private String AppID;//应用ID

	private String ProgramID;//应用程序包ID

	private String PayCode;//应用计费点编码

	private Integer TotalPrice;//订购总价(分)

	private Integer SubsNumb;//订购关系个数

	private Integer SubsSeq;//档次同步的序号

	private String ChannelID;//渠道ID

	private String ExData;//应用自定义参数

	private String OrderType;
	
	private String MD5Sign;//验证 大写MD5(OrderID#ChannelID#PayCode#Appkey)
	
	private String OrderPayment;
	public String getMsgType() {
		return MsgType;
	}

	@XmlElement(name = "MsgType", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setMsgType(String msgType) {
		this.MsgType = msgType;
	}

	public String getVersion() {
		return Version;
	}

	@XmlElement(name = "Version", namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setVersion(String version) {
		this.Version = version;
	}

	public Address_Info_Schema getSend_Address() {
		return Send_Address;
	}

	@XmlElement(name = "Send_Address", type = Address_Info_Schema.class, namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setSend_Address(Address_Info_Schema send_Address) {
		this.Send_Address = send_Address;
	}

	public Address_Info_Schema getDest_Address() {
		return Dest_Address;
	}

	@XmlElement(name = "Dest_Address", type = Address_Info_Schema.class, namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setDest_Address(Address_Info_Schema dest_Address) {
		this.Dest_Address = dest_Address;
	}

	public String getOrderID() {
		return OrderID;
	}

	@XmlElement(name = "OrderID", namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setOrderID(String orderID) {
		this.OrderID = orderID;
	}

	public Integer getCheckID() {
		return CheckID;
	}

	@XmlElement(name = "CheckID", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setCheckID(Integer checkID) {
		this.CheckID = checkID;
	}

	public String getTradeID() {
		return TradeID;
	}

	@XmlElement(name = "TradeID", namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setTradeID(String tradeID) {
		this.TradeID = tradeID;
	}

	public Integer getPrice() {
		return Price;
	}

	@XmlElement(name = "Price", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setPrice(Integer price) {
		this.Price = price;
	}

	public String getActionTime() {
		return ActionTime;
	}

	@XmlElement(name = "ActionTime", namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setActionTime(String actionTime) {
		this.ActionTime = actionTime;
	}

	public Integer getActionID() {
		return ActionID;
	}

	@XmlElement(name = "ActionID", namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setActionID(Integer actionID) {
		this.ActionID = actionID;
	}

	public String getMSISDN() {
		return MSISDN;
	}

	@XmlElement(name = "MSISDN", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}

	public String getFeeMSISDN() {
		return FeeMSISDN;
	}

	@XmlElement(name = "FeeMSISDN", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setFeeMSISDN(String feeMSISDN) {
		this.FeeMSISDN = feeMSISDN;
	}

	public String getAppID() {
		return AppID;
	}

	@XmlElement(name = "AppID", namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setAppID(String appID) {
		this.AppID = appID;
	}

	public String getProgramID() {
		return ProgramID;
	}

	@XmlElement(name = "ProgramID", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setProgramID(String programID) {
		this.ProgramID = programID;
	}

	public String getPayCode() {
		return PayCode;
	}

	@XmlElement(name = "PayCode", namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setPayCode(String payCode) {
		this.PayCode = payCode;
	}

	public Integer getTotalPrice() {
		return TotalPrice;
	}

	@XmlElement(name = "TotalPrice", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setTotalPrice(Integer totalPrice) {
		this.TotalPrice = totalPrice;
	}

	public Integer getSubsNumb() {
		return SubsNumb;
	}

	@XmlElement(name = "SubsNumb", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setSubsNumb(Integer subsNumb) {
		this.SubsNumb = subsNumb;
	}

	public Integer getSubsSeq() {
		return SubsSeq;
	}

	@XmlElement(name = "SubsSeq", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setSubsSeq(Integer subsSeq) {
		this.SubsSeq = subsSeq;
	}

	public String getChannelID() {
		return ChannelID;
	}

	@XmlElement(name = "ChannelID", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setChannelID(String channelID) {
		this.ChannelID = channelID;
	}

	public String getExData() {
		return ExData;
	}

	@XmlElement(name = "ExData", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setExData(String exData) {
		this.ExData = exData;
	}
	
	//从ExData中获取uid
	@JsonIgnore
	public String getUid() {
		//ExData.split("");
		return ExData;
	}

	@XmlElement(name = "MD5Sign", namespace = "http://www.monternet.com/dsmp/schemas/")
	public void setMD5Sign(String MD5Sign)
	{
		this.MD5Sign = MD5Sign;
	}
	
	public String getMD5Sign() {
		return MD5Sign;
	}

	public String getTransactionID() {
		return TransactionID;
	}

	@XmlElement(name = "TransactionID", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setTransactionID(String transactionID) {
		this.TransactionID = transactionID;
	}

	public String getOrderType() {
		return OrderType;
	}

	@XmlElement(name = "OrderType", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setOrderType(String orderType) {
		this.OrderType = orderType;
	}

	public String getOrderPayment() {
		return OrderPayment;
	}

	@XmlElement(name = "OrderPayment", namespace = "http://www.monternet.com/dsmp/schemas/", required = false)
	public void setOrderPayment(String orderPayment) {
		OrderPayment = orderPayment;
	}
}
