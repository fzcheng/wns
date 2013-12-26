package cn.game.vo.record;

import cn.game.vo.BaseVO;
import cn.org.util.DateUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RecordVO extends BaseVO{

	String mtId;    
	int keyValue1;
	int keyValue2;
	int keyValue3;
	int keyValue4;
	int keyValue5;
	int keyValue6;
	KeyValue keyValue;
	String recordData;
	int baseCoin;//基础货币 － 一般由充值获得
	int secondCoin;//游戏货币 － 游戏中获得，也可由基础货币兑换得到
	int thirdCoin;//第三货币 － 
	String createTime;
	String lastTime;
	CoinData coin;
	
	public RecordVO(KeyValue kv) {
		keyValue1 = kv.getKeyValue1();
		keyValue2 = kv.getKeyValue2();
		keyValue3 = kv.getKeyValue3();
		keyValue4 = kv.getKeyValue4();
		keyValue5 = kv.getKeyValue5();
		keyValue6 = kv.getKeyValue6();
		
		this.keyValue = kv;
		coin = new CoinData();
		
		createTime = DateUtil.getCurrentTime();
		lastTime = DateUtil.getCurrentTime();
	}
	
	public RecordVO() {
		keyValue = new KeyValue();
		coin = new CoinData();
		
		createTime = DateUtil.getCurrentTime();
		lastTime = DateUtil.getCurrentTime();
	}
	
	public String getMtId() {
		return mtId;
	}
	public void setMtId(String mtId) {
		this.mtId = mtId;
	}
	@JsonIgnore
	public int getKeyValue1() {
		return keyValue1;
	}
	public void setKeyValue1(int keyValue1) {
		this.keyValue1 = keyValue1;
		keyValue.setKeyValue1(keyValue1);
	}
	@JsonIgnore
	public int getKeyValue2() {
		return keyValue2;
	}
	public void setKeyValue2(int keyValue2) {
		this.keyValue2 = keyValue2;
		keyValue.setKeyValue2(keyValue2);
	}
	@JsonIgnore
	public int getKeyValue3() {
		return keyValue3;
	}
	public void setKeyValue3(int keyValue3) {
		this.keyValue3 = keyValue3;
		keyValue.setKeyValue3(keyValue3);
	}
	@JsonIgnore
	public int getKeyValue4() {
		return keyValue4;
	}
	public void setKeyValue4(int keyValue4) {
		this.keyValue4 = keyValue4;
		keyValue.setKeyValue4(keyValue4);
	}
	@JsonIgnore
	public int getKeyValue5() {
		return keyValue5;
	}
	public void setKeyValue5(int keyValue5) {
		this.keyValue5 = keyValue5;
		keyValue.setKeyValue5(keyValue5);
	}
	@JsonIgnore
	public int getKeyValue6() {
		return keyValue6;
	}
	public void setKeyValue6(int keyValue6) {
		this.keyValue6 = keyValue6;
		keyValue.setKeyValue6(keyValue6);
	}
	public KeyValue getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(KeyValue kv) {
		this.keyValue = kv;
	}
	public String getRecordData() {
		return recordData;
	}
	public void setRecordData(String recordData) {
		this.recordData = recordData;
	}
	@JsonIgnore
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@JsonIgnore
	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	@JsonIgnore
	public int getBaseCoin() {
		return baseCoin;
	}

	public void setBaseCoin(int baseCoin) {
		this.baseCoin = baseCoin;
		coin.setBaseCoin(baseCoin);
	}
	@JsonIgnore
	public int getSecondCoin() {
		return secondCoin;
	}

	public void setSecondCoin(int secondCoin) {
		this.secondCoin = secondCoin;
		coin.setSecondCoin(secondCoin);
	}
	@JsonIgnore
	public int getThirdCoin() {
		return thirdCoin;
	}

	public void setThirdCoin(int thirdCoin) {
		this.thirdCoin = thirdCoin;
		coin.setThirdCoin(thirdCoin);
	}

	public void addBaseCoin(int add) {
		baseCoin += add;
		coin.addBaseCoin(add);
	}
	
	public void addSecondCoin(int add) {
		secondCoin += add;
		coin.addSecondCoin(add);
	}
	
	public void addThirdCoin(int add) {
		thirdCoin += add;
		coin.addThirdCoin(add);
	}

	public CoinData getCoin() {
		return coin;
	}

	public void setCoin(CoinData coin) {
		this.coin = coin;
	}
}
