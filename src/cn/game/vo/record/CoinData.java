package cn.game.vo.record;

public class CoinData {

	int baseCoin;//基础货币 － 一般由充值获得
	int secondCoin;//游戏货币 － 游戏中获得，也可由基础货币兑换得到
	int thirdCoin;//第三货币 － 
	public int getBaseCoin() {
		return baseCoin;
	}
	public void setBaseCoin(int baseCoin) {
		this.baseCoin = baseCoin;
	}
	public int getSecondCoin() {
		return secondCoin;
	}
	public void setSecondCoin(int secondCoin) {
		this.secondCoin = secondCoin;
	}
	public int getThirdCoin() {
		return thirdCoin;
	}
	public void setThirdCoin(int thirdCoin) {
		this.thirdCoin = thirdCoin;
	}
	public void addBaseCoin(int add) {
		baseCoin += add;
	}
	public void addSecondCoin(int add) {
		secondCoin += add;
	}
	public void addThirdCoin(int add) {
		thirdCoin += add;
	}
}
