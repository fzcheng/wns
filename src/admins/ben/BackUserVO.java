/**
 * 
 */
package admins.ben;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admins.ben.ChannelVO;
import admins.dao.BackRoleDAO;
import admins.dao.BackRoleGameDAO;
import admins.dao.ChannelDAO;
import cn.org.util.SpringUtils;

/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackUserVO implements Serializable {
	
	private static final long serialVersionUID = 8688710414273438465L;
	/** 主键 */
	private int userId;
	/** 登陆用户名 */
	private String userName;
	/** 登陆密码 */
	private String userPwd;
	/** 用户状态 */
	private int userState;
	/** 角色id */
	private int roleId;
	/** 角色名称 */
	private String roleName;
	/** 游戏ID */
	private String gameId;
	/** 渠道ID */
	private int channelId;
	/** 登录次数 */
	private int loginCount;
	/** 最后登录时间 */
	private String lastLoginTime;
	/** 创建时间 */
	private String createTime;
	private String channelName;
	private String roleNameCur;
	
	public String getRoleNameCur(){
		if(roleId == 0) return "系统管理员";
		
		BackRoleDAO roleDao = (BackRoleDAO)SpringUtils.getBean("backRoleDao");
		BackRoleVO role = roleDao.getBackRoleById(roleId);
		if(role == null){
			return String.valueOf(roleId);
		}
		return role.getRoleName();
	}
	
	public String  getChannelName() {
	 
	/*	ChannelDAO dao = (ChannelDAO) SpringUtils.getBean("channeldao");	
		ChannelVO chl = dao.getChannelById(String.valueOf(channelId));
		if(chl == null){
			return " ";
		}*/
		System.out.println("用户角色："+roleId);
		System.out.println("用户游戏ID："+gameId);
		Map<String, String> params = new HashMap<String, String>();
		BackRoleGameDAO dao=(BackRoleGameDAO)SpringUtils.getBean("backRoleGameDao");
		params.put("roleId", String.valueOf(roleId));
		params.put("gameId", String.valueOf(gameId));
		List<BackRoleGameVO>  vo=dao.findBackRoleGameByRoleId(roleId);
			String st="";
			//ChannelDAO bpDao = (ChannelDAO) SpringUtils.getBean("channeldao");
			 
			for(BackRoleGameVO v : vo){
				System.out.println("vo：channelId "+v.getChannelId());
				System.out.println("vo：channelId "+v.getChannelId());
			//	ChannelVO bpVo = bpDao.getChannelById(String.valueOf(v.getChannelId()));
				if(v!=null){
					if("".equals(st))
					st +=v.getChannelName();
					else
					st +="、"+v.getChannelName();
				}
			}
		
		return st;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		if(roleId == 0) return "系统管理员";
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginTimeStr() {
		
		if(null == lastLoginTime || "".equals(lastLoginTime)){
			return "";
		}
		if(!"".equals(lastLoginTime) && 19 == lastLoginTime.length()){
			return lastLoginTime;
		}
		return lastLoginTime.substring(0,lastLoginTime.length()-2);
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
