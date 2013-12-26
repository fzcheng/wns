package admins.ben;

import java.io.Serializable;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRoleGameDetailVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -718635375750559160L;
	/** 主键 */
	private int id;
	/** 角色id */
	private int roleId;
	
	private String gameId;
	private String gameName;
	private int channelId;
	private String channelName;
	
	private int state;
	/** 创建时间 */
	private String createTime;
	
	
	/**  授权允许 */
	public static final int YES = 1;
	/**  授权不允许 */
	public static final int NO = 0;
	/**  授权不确定 */
	public static final int NEUTRAL = -1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreateTime() {
		if(!"".equals(createTime) && 19 == createTime.length()){
			return createTime;
		}
		return createTime.substring(0,createTime.length()-2);
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	

	public void setPermission(int permission,boolean yes){
		int temp = 1;
		temp = temp << permission;
		if(yes){
			state |= temp;
		}else{
			state &= ~temp;
		}
	}
	
	public int getPermission(int permission){
		
		int temp = 1;
		temp = temp << permission;
		temp &= state;
		if(temp != 0){
			return YES;
		}
		return NO;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	

}
