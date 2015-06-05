package cn.game.vo.sms;

public class MessageVO {

	public static final int S_NORMAL = 0;
	public static final int S_SENDING = 1;
	public static final int S_SENDS = 2;
	public static final int S_RECEIVES = 3;
	public static final int S_SENDF = -1;
	
	
	int missionId;
	int status;//客户端记录状态  0-初始 1-发送中 2-发送成功 3-接收成功 －1-发送失败
	String mobile;
	String content;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMissionId() {
		return missionId;
	}
	public void setMissionId(int missionId) {
		this.missionId = missionId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
