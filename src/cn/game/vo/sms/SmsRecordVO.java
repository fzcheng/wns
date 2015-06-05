package cn.game.vo.sms;

import cn.game.vo.BaseVO;

public class SmsRecordVO extends BaseVO{
	int id;
	String telnum;
	String gameId;
	String channelId;
	String content;
	String sendflag;
	String smschannel;
	String sendtime;
	public SmsRecordVO()
	{
		setTableName("smsrecord");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendflag() {
		return sendflag;
	}
	public void setSendflag(String sendflag) {
		this.sendflag = sendflag;
	}
	public String getSmschannel() {
		return smschannel;
	}
	public void setSmschannel(String smschannel) {
		this.smschannel = smschannel;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
}
