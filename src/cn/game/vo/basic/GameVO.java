package cn.game.vo.basic;


/**
 * 用户信息
 * @author fzc
 *
 */
public class GameVO extends BasicVO{

	String gameId;
	String gameName = "";
	String shortName = "";
	String appkey = "";
	String publicKey = "";
	int flag;
	String mmappid;
	String mmappkey;
	int mmOrderType;
	
	public GameVO() {
	}
	
	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}


	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String getKey() {
		return gameId;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getMmappid() {
		return mmappid;
	}

	public void setMmappid(String mmappid) {
		this.mmappid = mmappid;
	}

	public String getMmappkey() {
		return mmappkey;
	}

	public void setMmappkey(String mmappkey) {
		this.mmappkey = mmappkey;
	}

	public int getMmOrderType() {
		return mmOrderType;
	}

	public void setMmOrderType(int mmOrderType) {
		this.mmOrderType = mmOrderType;
	}
}
