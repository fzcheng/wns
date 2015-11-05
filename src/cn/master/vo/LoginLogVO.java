package cn.master.vo;

import cn.game.vo.BaseVO;

/**
 * 用户登录日志
 * @author a
 *
 */
public class LoginLogVO extends BaseVO{

	int id;
	String UID;
	String login_time;
	String client_version;
	int agenttype;
	String useragent;
	String nettype;
	String machine_type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public String getClient_version() {
		return client_version;
	}
	public void setClient_version(String client_version) {
		this.client_version = client_version;
	}
	public int getAgenttype() {
		return agenttype;
	}
	public void setAgenttype(int agenttype) {
		this.agenttype = agenttype;
	}
	public String getUseragent() {
		return useragent;
	}
	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
	public String getNettype() {
		return nettype;
	}
	public void setNettype(String nettype) {
		this.nettype = nettype;
	}
	public String getMachine_type() {
		return machine_type;
	}
	public void setMachine_type(String machine_type) {
		this.machine_type = machine_type;
	}
}
