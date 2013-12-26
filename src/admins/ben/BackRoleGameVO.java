package admins.ben;

import java.io.Serializable;

import admins.dao.ChannelDAO;
import cn.org.util.SpringUtils;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRoleGameVO implements Serializable {

	private static final long serialVersionUID = -5454883435309744583L;
	
	/** 主键 */
	private int id;
	/** 角色id */
	private int roleId;
	
	private String gameId;
	private int channelId;

	private int state;
	/** 创建时间 */
	private String createTime;
	private String channelName;
	 
	
	public String getChannelName() {
		ChannelDAO bpDao = (ChannelDAO) SpringUtils.getBean("channeldao");
		ChannelVO vo=bpDao.getChannelById(String.valueOf(channelId));
		if(vo!=null)
			return  vo.getChannelName();
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
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
	

}
