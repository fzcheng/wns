package cn.game.pay.mmarket;



/**
 * 
 * @author daikaifeng 2013-4-1
 *
 */
public class SyncOrderRelationReq 
{
	/**
	 * 消息类型;
		填写“SyncOrderRelationReq”
	 */
	private String MsgType;
	/**
	 * 请求事务编号;
		每次的定购关系同步请求交互都需要分配一个唯一的事务编号，唯一性由MM平台保证.
	 */
	private String TransactionID;
	/**
	 * 该接口消息的版本号，本次所有的接口消息的版本都为“1.5.0”
	 */
	private String Version;
	/**
	 * 发送方的地址；DeviceType填写0：M-MARKET
	 */
	private SyncOrderAddressInfoSchema Send_Address;
	/**
	 * 接收方的地址；DeviceType填写400：SP
	 */
	private SyncOrderAddressInfoSchema Dest_Address;
	/**
	 * 计费用户标识
	 */
	private SyncOrderUserIdSchema FeeUser_ID;
	/**
	 * 目标用户标识
	 */
	private SyncOrderUserIdSchema DestUser_ID;
	/**
	 * 订单推送号;
		MM给TAAC同步订购关系时，此字段值填充为ContentID，其他情况参考 3.1.3 服务访问/点播接口 应答中的PushID
	 */
	private String LinkID;
	/**
	 * 服务状态管理动作代码，具体值如下：
		1： 定购服务；
		2： 暂停服务；(包月业务有效)
		3： 停止服务；(包月业务有效)
		4： 激活服务  (包月业务有效)
	 */
	private String ActionID;
	/**
	 * 产生服务状态管理动作原因的代码，具体值如下：
		1：用户发起行为(默认) 
		2：系统发起行为
		3：扣费失败导致的服务取消
		4：其他
	 */
	private String ActionReasonID;
	/**
	 * SP的企业代码
		(应用所属AP代码)
	 */
	private String SPID;
	/**
	 * 能力应用ID（当订购关系向能力服务平台同步时，此字段必选）
	 */
	private String AppID;
	/**
	 * SP中该服务的服务代码
	(应用所属业务代码)
	 */
	private String SPServiceID;
	/**
	 * 定购发生地：
		0: M-Market
		1: WWW门户
		2: WAP门户
		3: 短信网关
		7: BOSS
		8: MISC
		10:终端门户
		17: PC门户
	 */
	private String AccessMode;
	/**
	 * 服务订购参数（保留，暂不使用）
	 */
	private String FeatureStr;

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
	public SyncOrderAddressInfoSchema getSend_Address() {
		return Send_Address;
	}
	public void setSend_Address(SyncOrderAddressInfoSchema sendAddress) {
		Send_Address = sendAddress;
	}
	public SyncOrderAddressInfoSchema getDest_Address() {
		return Dest_Address;
	}
	public void setDest_Address(SyncOrderAddressInfoSchema destAddress) {
		Dest_Address = destAddress;
	}
	public SyncOrderUserIdSchema getFeeUser_ID() {
		return FeeUser_ID;
	}
	public void setFeeUser_ID(SyncOrderUserIdSchema feeUserID) {
		FeeUser_ID = feeUserID;
	}
	public SyncOrderUserIdSchema getDestUser_ID() {
		return DestUser_ID;
	}
	public void setDestUser_ID(SyncOrderUserIdSchema destUserID) {
		DestUser_ID = destUserID;
	}
	public String getLinkID() {
		return LinkID;
	}
	public void setLinkID(String linkID) {
		LinkID = linkID;
	}
	public String getActionID() {
		return ActionID;
	}
	public void setActionID(String actionID) {
		ActionID = actionID;
	}
	public String getActionReasonID() {
		return ActionReasonID;
	}
	public void setActionReasonID(String actionReasonID) {
		ActionReasonID = actionReasonID;
	}
	public String getSPID() {
		return SPID;
	}
	public void setSPID(String sPID) {
		SPID = sPID;
	}
	public String getAppID() {
		return AppID;
	}
	public void setAppID(String appID) {
		AppID = appID;
	}
	public String getSPServiceID() {
		return SPServiceID;
	}
	public void setSPServiceID(String sPServiceID) {
		SPServiceID = sPServiceID;
	}
	public String getAccessMode() {
		return AccessMode;
	}
	public void setAccessMode(String accessMode) {
		AccessMode = accessMode;
	}
	public String getFeatureStr() {
		return FeatureStr;
	}
	public void setFeatureStr(String featureStr) {
		FeatureStr = featureStr;
	}

}
