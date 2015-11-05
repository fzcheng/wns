package cn.master.vo;

import cn.game.vo.BaseVO;
import cn.org.util.DateUtil;

/**
 * 玩家存档记录
 * @author a
 *
 */
public class RecordVO extends BaseVO{

	int id;
	String UID;
	String create_time;
	String last_time;
	int version;
	byte[] record_data;//存档数据
	
	public RecordVO()
	{
		create_time = DateUtil.getCurrentTime();
		last_time = DateUtil.getCurrentTime();
	}
	
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
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_time() {
		return last_time;
	}
	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}
	public byte[] getRecord_data() {
		return record_data;
	}
	public void setRecord_data(byte[] record_data) {
		this.record_data = record_data;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
