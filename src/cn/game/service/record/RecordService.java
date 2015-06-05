package cn.game.service.record;

import java.util.List;

import net.sf.json.JSONObject;
import cn.game.dao.record.RecordDAO;
import cn.game.inter.service.IDataService;
import cn.game.service.ReturnMessage;
import cn.game.util.DateUtil;
import cn.game.util.JacksonUtil;
import cn.game.vo.basic.GameVO;
import cn.game.vo.record.KeyValue;
import cn.game.vo.record.RecordVO;
import cn.game.vo.record.TopVO;
import cn.org.util.StringUtil;

/**
 * 基础数据服务
 * 
 * @author fzcheng
 */
public class RecordService{
	RecordDAO recorddao;
	IDataService dataservice;
	
	public void setRecorddao(RecordDAO recorddao) {
		this.recorddao = recorddao;
	}

	public void setDataservice(IDataService dataservice) {
		this.dataservice = dataservice;
	}
	
	public ReturnMessage requestSaverecord(String mtId, String plat, String username, String gameId,
			String keyValue, String recordData, String sign) {
		ReturnMessage rm = new ReturnMessage();
		
		GameVO game = dataservice.getGameById(gameId);
		KeyValue kv = new KeyValue();
		kv = JacksonUtil.fromJson(keyValue, kv.getClass());
		RecordVO record = new RecordVO();
		
		record.setMtId(mtId);
		record.setPlat(plat);
		record.setUsername(username);
		record.setRecordData(recordData);
		record.setTableName(game.getShortName() + "_record");
		
		RecordVO oldr = recorddao.getById(record);
		if(oldr == null)
		{
			if(kv != null)
			{
				record.setKeyValue(kv);
			}
			
			recorddao.save(record);
		}
		else
		{
			oldr.setRecordData(recordData);
			oldr.setUsername(username);
			oldr.setLastTime(DateUtil.getCurrentTime());
			if(kv == null)
			{
				recorddao.update2(oldr);
			}
			else
			{
				if(kv != null)
				{
					oldr.setKeyValue(kv);
				}
				recorddao.update(oldr);
			}
		}
		
		rm.setResult(true);
		return rm;
	}

	public ReturnMessage requestGetrecord(String mtId, String plat, String gameId, String sign) {
		ReturnMessage rm = new ReturnMessage();
		
		GameVO game = dataservice.getGameById(gameId);
		
		RecordVO record = new RecordVO();
		record.setMtId(mtId);
		record.setPlat(plat);
		record.setTableName(game.getShortName() + "_record");
		
		RecordVO rvo = recorddao.getById(record);
		rm.setResult(true);
		rm.setObject(rvo);
		
		return rm;
	}
	
	public RecordVO getrecord(String mtId, String plat, String gameId) {
		GameVO game = dataservice.getGameById(gameId);
		if(game == null || game.getFlag() == 0)
		{
			return null;
		}
		
		RecordVO record = new RecordVO();
		record.setMtId(mtId);
		record.setPlat(plat);
		record.setTableName(game.getShortName() + "_record");
		
		RecordVO rvo = recorddao.getById(record);
		return rvo;
	}
	
	public ReturnMessage requestExchange(String mtId, String plat, String gameId,
			String keyValue, String recordData, String coindata, String sign) {
		ReturnMessage rm = new ReturnMessage();
		
		GameVO game = dataservice.getGameById(gameId);
		
		RecordVO record = new RecordVO();
		record.setMtId(mtId);
		record.setPlat(plat);
		record.setTableName(game.getShortName() + "_record");
		
		JSONObject coinjson = JSONObject.fromObject(coindata);
		String mBasecoin = String.valueOf(coinjson.get("mBasecoin"));
		String mSecondcoin = String.valueOf(coinjson.get("mSecondcoin"));
		String mThirdcoin = String.valueOf(coinjson.get("mThirdcoin"));
		
		RecordVO rvo = recorddao.getById(record);
		int imBasecoin = StringUtil.parseInt(mBasecoin);
		int imSecondcoin = StringUtil.parseInt(mSecondcoin);
		int imThirdcoin = StringUtil.parseInt(mThirdcoin);
		
		if(imBasecoin == 0 && imSecondcoin == 0 && imThirdcoin == 0)
		{
			rm.setDetail("无效操作，数据无变化。");
			return rm;
		}
		
		if(rvo.getBaseCoin() + imBasecoin < 0)
		{
			rm.setDetail("操作失败:货币1不足");
			return rm;
		}
		
		if(rvo.getSecondCoin() + imSecondcoin < 0)
		{
			rm.setDetail("操作失败:货币2不足");
			return rm;
		}
		
		if(rvo.getThirdCoin() + imThirdcoin < 0)
		{
			rm.setDetail("操作失败:货币3不足");
			return rm;
		}
		
		rvo.addBaseCoin(imBasecoin);
		rvo.addSecondCoin(imSecondcoin);
		rvo.addThirdCoin(imThirdcoin);
		rvo.setRecordData(recordData);
		rvo.setLastTime(DateUtil.getCurrentTime());
		KeyValue kv = new KeyValue();
		kv = JacksonUtil.fromJson(keyValue, kv.getClass());
		if(kv != null)
			rvo.setKeyValue(kv);
		this.updateCoin(rvo);
		
		rm.setResult(true);
		rm.setObject(rvo);
		
		return rm;
	}
	
	public ReturnMessage upscore(String mtId, String plat, String gameId,
			int keyIndex, int keyValue) {
		ReturnMessage rm = new ReturnMessage();
		
		GameVO game = dataservice.getGameById(gameId);
		RecordVO record = new RecordVO();

		record.setMtId(mtId);
		record.setPlat(plat);
		record.setTableName(game.getShortName() + "_record");
		
		RecordVO oldr = recorddao.getById(record);
		if(oldr == null)
		{
			rm.setDetail("无记录");
			return rm;
		}
		else
		{
			oldr.setKeyValue(keyIndex, keyValue);

			recorddao.update(oldr);
		}
		
		rm.setResult(true);
		return rm;
	}
	
	public List<TopVO> getalltop(String mtId, String plat, String gameId,
			int keyIndex) {
		GameVO game = dataservice.getGameById(gameId);
		RecordVO record = new RecordVO();
		record.setMtId(mtId);
		record.setPlat(plat);
		record.setTableName(game.getShortName() + "_record");
		
		switch(keyIndex)
		{
		case 1:
			record.setPara("keyValue1");
			break;
		case 2:
			record.setPara("keyValue2");
			break;
		case 3:
			record.setPara("keyValue3");
			break;
		case 4:
			record.setPara("keyValue4");
			break;
		case 5:
			record.setPara("keyValue5");
			break;
		case 6:
			record.setPara("keyValue6");
			break;
		default:
			record.setPara("keyValue1");
			break;
		}
		List<TopVO> list = recorddao.getAllTop(record);
		return list;
	}
	
	public List<TopVO> getfriendtop(String mtId, String plat, String gameId,
			int keyIndex, String jsonstr) {
		GameVO game = dataservice.getGameById(gameId);
		RecordVO record = new RecordVO();
		record.setMtId(mtId);
		record.setPlat(plat);
		record.setTableName(game.getShortName() + "_record");
		
		//同步好友列表
		if(DateUtil.isSameDay(record.getSyncTime())){
			//JacksonUtil.fromJson(keyValue, kv.getClass());
			record.setSyncTime(DateUtil.getCurrentTime());
			recorddao.update(record);
		}
		
		switch(keyIndex)
		{
		case 1:
			record.setPara("keyValue1");
			break;
		case 2:
			record.setPara("keyValue2");
			break;
		case 3:
			record.setPara("keyValue3");
			break;
		case 4:
			record.setPara("keyValue4");
			break;
		case 5:
			record.setPara("keyValue5");
			break;
		case 6:
			record.setPara("keyValue6");
			break;
		default:
			record.setPara("keyValue1");
			break;
		}
		List<TopVO> list = recorddao.getTop(record);
		return list;
	}
	
	public void update2(RecordVO record) {
		recorddao.update2(record);
	}
	
	public void updateCoin(RecordVO record) {
		recorddao.updateCoin(record);
	}

	public int getMyTop(String mtId, String plat, String gameId, int keyIndex) {
		RecordVO record = new RecordVO();
		GameVO game = dataservice.getGameById(gameId);
		if(game == null)
			return -1;
		record.setMtId(mtId);
		record.setPlat(plat);
		record.setTableName(game.getShortName() + "_record");
		
		RecordVO myRecord = recorddao.getById(record);
		
		if(myRecord == null)
			return -1;
		
		switch(keyIndex)
		{
		case 1:
			record.setPara("keyValue1 > " + myRecord.getKeyValue1());
			break;
		case 2:
			record.setPara("keyValue2 > " + myRecord.getKeyValue2());
			break;
		case 3:
			record.setPara("keyValue3 > " + myRecord.getKeyValue3());
			break;
		case 4:
			record.setPara("keyValue4 > " + myRecord.getKeyValue4());
			break;
		case 5:
			record.setPara("keyValue5 > " + myRecord.getKeyValue5());
			break;
		case 6:
			record.setPara("keyValue6 > " + myRecord.getKeyValue6());
			break;
		default:
			record.setPara("keyValue1 > " + myRecord.getKeyValue1());
			break;
		}
		int curtop = recorddao.getMyTop(record);
		return curtop;
	}
}
