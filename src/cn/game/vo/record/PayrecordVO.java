package cn.game.vo.record;

import cn.game.pay.mmarket.SyncAppOrderReq;
import cn.game.pay.mmarket.PayCodeVO;
import cn.game.pay.mmarket.Trusted2ServQueryResp;
import cn.game.pay.sina.SinaWEIPayVO;
import cn.game.util.DateUtil;
import cn.game.util.JacksonUtil;
import cn.game.vo.BaseVO;

public class PayrecordVO extends BaseVO{
	int id;
	String userId;
	String gameId;
	String orderId;
	String channelName;
	float rmb;
	int gameMoney;
	String status;//S:成功， F:失败  SF:付费成功，给游戏币失败
	String jsonData;
	String createTime;
	
	public PayrecordVO()
	{
		
	}
	
	public PayrecordVO(SinaWEIPayVO weipay)
	{
		userId = weipay.getOrder_uid();
		gameId = weipay.getGameId();
		orderId = weipay.getOrder_id();
		channelName = "sinawei";
		rmb = Float.valueOf(weipay.getActual_amount());
		gameMoney = weipay.getGameMoney();
		status = weipay.getStatus();
		jsonData = JacksonUtil.getJsonString4JavaPOJO(weipay);
		createTime = DateUtil.getCurrentTime();
	}
	
	public PayrecordVO(SyncAppOrderReq req, PayCodeVO paycode) {
		userId = req.getUid();
		gameId = paycode.getGameId();
		orderId = req.getOrderID();
		channelName = "mmarket";
		rmb = Float.valueOf(paycode.getRmb());
		gameMoney = paycode.getGamemoney();
		status = "S";
		jsonData = JacksonUtil.getJsonString4JavaPOJO(req);
		createTime = DateUtil.getCurrentTime();
	}

	public PayrecordVO(Trusted2ServQueryResp req, PayCodeVO paycode) {
		userId = req.getUid();
		gameId = paycode.getGameId();
		orderId = req.getOrderID();
		channelName = "mmarket";
		rmb = Float.valueOf(paycode.getRmb());
		gameMoney = paycode.getGamemoney();
		status = "S";
		jsonData = JacksonUtil.getJsonString4JavaPOJO(req);
		createTime = DateUtil.getCurrentTime();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public float getRmb() {
		return rmb;
	}
	public void setRmb(float rmb) {
		this.rmb = rmb;
	}
	public int getGameMoney() {
		return gameMoney;
	}
	public void setGameMoney(int gameMoney) {
		this.gameMoney = gameMoney;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
