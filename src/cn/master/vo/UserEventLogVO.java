package cn.master.vo;

import cn.game.vo.BaseVO;

/**
 * 用户操作日志
 * @author a
 *
 */
public class UserEventLogVO  extends BaseVO{

	int id;
	String UID;
	String event_time;
	int event_type;
	byte[] event_data;
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
	public String getEvent_time() {
		return event_time;
	}
	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}
	public int getEvent_type() {
		return event_type;
	}
	public void setEvent_type(int event_type) {
		this.event_type = event_type;
	}
	public byte[] getEvent_data() {
		return event_data;
	}
	public void setEvent_data(byte[] event_data) {
		this.event_data = event_data;
	}
}
