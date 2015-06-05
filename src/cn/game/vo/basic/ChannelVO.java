package cn.game.vo.basic;

public class ChannelVO extends BasicVO{

	String channelid;
	String channelname;
	int flag;
	String signkey;
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public String getChannelname() {
		return channelname;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getSignkey() {
		return signkey;
	}
	public void setSignkey(String signkey) {
		this.signkey = signkey;
	}
	@Override
	public String getKey()
	{
		return channelid;
	}
}
