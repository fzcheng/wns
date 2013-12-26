package cn.game.pay.mmarket;

import cn.game.vo.basic.BasicVO;

public class PayCodeVO extends BasicVO {

	String paycode;
	String appid;
	String gameId;
	String name;
	int rmb;
	int gamemoney;
	public String getPaycode() {
		return paycode;
	}
	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRmb() {
		return rmb;
	}
	public void setRmb(int rmb) {
		this.rmb = rmb;
	}
	public int getGamemoney() {
		return gamemoney;
	}
	public void setGamemoney(int gamemoney) {
		this.gamemoney = gamemoney;
	}
	@Override
	public String getKey() {
		
		return paycode;
	}
}
