package cn.master.vo;

import cn.game.vo.BaseVO;

/**
 * 上传记录日志
 * @author a
 *
 */
public class UploadRecordLogVO  extends BaseVO{

	int id;
	String UID;
	String upload_time;
	int record_version;
	byte[] record_data;
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
	public String getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}
	public int getRecord_version() {
		return record_version;
	}
	public void setRecord_version(int record_version) {
		this.record_version = record_version;
	}
	public byte[] getRecord_data() {
		return record_data;
	}
	public void setRecord_data(byte[] record_data) {
		this.record_data = record_data;
	}
	
	
}
