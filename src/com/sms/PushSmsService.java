package com.sms;

import java.util.List;

import cn.game.dao.sms.SmsRecordDAO;
import cn.game.lock.LockManager;
import cn.game.vo.sms.MessageVO;
import cn.game.vo.sms.SmsRecordVO;
import cn.org.util.DateUtil;
import cn.org.util.SpringUtils;

import com.sms.dxt.DxtSms;

public class PushSmsService {
	
	public static PushSmsService self;
	public static int lastId = 0;
	
	public static PushSmsService getInstance() {
		if(self == null)
			self = new PushSmsService();
		return self;
	}
	
	public int sendSms(String channelId, String gameId, String telnum, String content)
	{
		synchronized(LockManager.get("pushsms"))
		{
			if(telnum == null || telnum.length() != 11)
			{
				return -2;//短信号码错误
			}
			
			int result = -1;
			//int result = -1;
//			try{
//				IPushSms pushsms = new DxtSms();
//				result = pushsms.sendSms(telnum, content);
//			}
//			catch(Exception e)
//			{
//				result = -1;
//				e.printStackTrace();
//			}
			
			try{
				SmsRecordVO record = new SmsRecordVO();
				record.setChannelId(channelId);
				record.setContent(content);
				record.setGameId(gameId);
				record.setSendflag(""+result);
				record.setSendtime(DateUtil.getCurrentTime());
				record.setSmschannel("dxt");
				record.setTelnum(telnum);
				
				SmsRecordDAO smsrecorddao = (SmsRecordDAO)SpringUtils.getBean("smsrecorddao");
				smsrecorddao.save(record);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			result = 0;
			return result;
		}
		
	}

	public List<MessageVO> getQueue() {
		synchronized(LockManager.get("pushsms"))
		{
			SmsRecordDAO smsrecorddao = (SmsRecordDAO)SpringUtils.getBean("smsrecorddao");
			List<MessageVO> list = smsrecorddao.getUnCompleteRecord(lastId);
			
			if(list != null && list.size() > 0)
			{
				MessageVO msg = list.get(list.size() - 1);
				
				if(lastId < msg.getMissionId())
				{
					lastId = msg.getMissionId();
				}
			}
			
			return list;
		}
	}

	public void completePush(int missionIdI) {
		SmsRecordDAO smsrecorddao = (SmsRecordDAO)SpringUtils.getBean("smsrecorddao");
		smsrecorddao.completePush(missionIdI, "free");
	}
}
