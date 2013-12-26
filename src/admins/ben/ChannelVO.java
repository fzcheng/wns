package admins.ben;

/**
 * 渠道channel
 */
public class ChannelVO {

	/* 渠道id */
	int channelId;
	/* 渠道名称  */
	String channelName;
	
	/*Setter and Getter*/
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
}
