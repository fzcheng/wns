package admins.ben;

import java.io.Serializable;
import java.util.List;

import admins.dao.BackRoleGameDAO;
import admins.dao.ChannelDAO;
import cn.org.util.SpringUtils;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRoleVO implements Serializable {
	
	private static final long serialVersionUID = -3725402099882526012L;
	
	/** 主键 */
	private int roleId;
	/** 角色名 */
	private String roleName;
	/** 角色描叙 */
	private String roleDsc;
	/** 创建时间 */
	private String createTime;
	private String channelName;
	
	
	public String getChannelName() {
		BackRoleGameDAO dao=(BackRoleGameDAO)SpringUtils.getBean("backRoleGameDao");
		 
		 String st="";
		List<BackRoleGameVO>  vo=dao.findBackRoleGameByRoleId(roleId);
		ChannelDAO bpDao = (ChannelDAO) SpringUtils.getBean("channeldao");
		 
		
		for(BackRoleGameVO v : vo){
			
			ChannelVO bpVo = bpDao.getChannelById(String.valueOf(v.getChannelId()));
			if(bpVo!=null){
				if("".equals(st))
				st +=bpVo.getChannelName();
				else
				st +="、"+bpVo.getChannelName();
			}
		}
		
		return st;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDsc() {
		return roleDsc;
	}
	public void setRoleDsc(String roleDsc) {
		this.roleDsc = roleDsc;
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
	
	

}
