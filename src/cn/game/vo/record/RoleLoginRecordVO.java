package cn.game.vo.record;

import cn.game.util.DateUtil;


public class RoleLoginRecordVO{

	int roleId;
	int lgtimes;
	int lgdays;
	int mlgcdays;
	int clgcdays;
	String createTime;
	String lastTime;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getLgtimes() {
		return lgtimes;
	}
	public void setLgtimes(int lgtimes) {
		this.lgtimes = lgtimes;
	}
	public int getLgdays() {
		return lgdays;
	}
	public void setLgdays(int lgdays) {
		this.lgdays = lgdays;
	}
	public int getMlgcdays() {
		return mlgcdays;
	}
	public void setMlgcdays(int mlgcdays) {
		this.mlgcdays = mlgcdays;
	}
	public int getClgcdays() {
		return clgcdays;
	}
	public void setClgcdays(int clgcdays) {
		this.clgcdays = clgcdays;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	
	/**
	 * 获取的注册后的第几天
	 * @return
	 */
	public int getCreatedays() {
		int days = DateUtil.getBettwenDays(getCreateTime(), DateUtil.getCurrentDay()) + 1;
		
		return days;
	}
}
