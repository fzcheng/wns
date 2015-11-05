package cn.master.service;

import javax.servlet.http.HttpServletRequest;

import cn.master.dao.LoginLogDAO;
import cn.master.dao.RecordDAO;
import cn.master.dao.UploadRecordLogDAO;
import cn.master.dao.UserEventLogDAO;
import cn.master.exception.VersionException;
import cn.master.proto.MasterData.SaveData_;
import cn.master.vo.RecordVO;
import cn.org.util.DateUtil;

import com.google.protobuf.InvalidProtocolBufferException;


public class MasterService {

	LoginLogDAO loginlogdao;
	RecordDAO recorddao;
	UploadRecordLogDAO uploadrecordlogdao;
	UserEventLogDAO usereventlogdao;
	
	public void setLoginlogdao(LoginLogDAO loginlogdao) {
		this.loginlogdao = loginlogdao;
	}
	public void setRecorddao(RecordDAO recorddao) {
		this.recorddao = recorddao;
	}
	public void setUploadrecordlogdao(UploadRecordLogDAO uploadrecordlogdao) {
		this.uploadrecordlogdao = uploadrecordlogdao;
	}
	public void setUsereventlogdao(UserEventLogDAO usereventlogdao) {
		this.usereventlogdao = usereventlogdao;
	}
	
	
	private RecordVO getRecord(String uid)
	{
		RecordVO record = new RecordVO();
		record.setUID(uid);
		
		record = recorddao.getByKey(record);
		return record;
	}
	
	private void saveRecord(RecordVO vo)
	{
		
	}
	
	private void updateRecord(RecordVO vo)
	{
		
	}
	
	public RecordVO getRecord(HttpServletRequest request) {
		return getRecord((String)request.getAttribute("user"));	
	}
	
	public void updateRecord(byte [] data, boolean isforce) throws InvalidProtocolBufferException, VersionException {
		
		SaveData_.Builder savedata = SaveData_.newBuilder();
		savedata.mergeFrom(data);
		
		String uid = savedata.getUserId();
		
		RecordVO record = getRecord(uid);
		if(record == null)
		{
			//创建
			record = new RecordVO();
			record.setUID(uid);
			record.setRecord_data(data);
			record.setVersion(savedata.getDataVersions());
			
			saveRecord(record);
		}
		else
		{
			if(isforce == false)
			{
				//非强制
				if(savedata.getDataVersions() < record.getVersion())
				{
					//客户端版本落后的情况 不处理数据 抛出异常
					VersionException e = new VersionException();
					throw e;
				}
				else
				{
					//更新
					record.setLast_time(DateUtil.getCurrentTime());
					record.setVersion(savedata.getDataVersions());
					record.setRecord_data(data);
					updateRecord(record);
				}
			}
			else
			{
				//强制
				if(savedata.getDataVersions() > record.getVersion())
				{
					//客户端版本落后的情况 不处理数据 抛出异常
					VersionException e = new VersionException();
					throw e;
				}
				else
				{
					//更新
					record.setLast_time(DateUtil.getCurrentTime());
					record.setVersion(savedata.getDataVersions());
					record.setRecord_data(data);
					updateRecord(record);
				}
			}
		}
	}
}
