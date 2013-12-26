package cn.game.service.record;

import net.sf.json.JSONObject;
import cn.game.dao.record.RecordDAO;
import cn.game.inter.service.IDataService;
import cn.game.service.ReturnMessage;
import cn.game.util.JacksonUtil;
import cn.game.vo.basic.GameVO;
import cn.game.vo.record.KeyValue;
import cn.game.vo.record.RecordVO;
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
	
	public ReturnMessage requestSaverecord(String mtId, String gameId,
			String keyValue, String recordData, String sign) {
		ReturnMessage rm = new ReturnMessage();
		
		GameVO game = dataservice.getGameById(gameId);
		KeyValue kv = new KeyValue();
		kv = JacksonUtil.fromJson(keyValue, kv.getClass());
		RecordVO record;
		if(kv == null)
		{
			record = new RecordVO();
		}
		else
		{
			record = new RecordVO(kv);
		}
		
		record.setMtId(mtId);
		record.setRecordData(recordData);
		record.setTableName(game.getShortName() + "_record");
		
		RecordVO oldr = recorddao.getById(record);
		if(oldr == null)
		{
			recorddao.save(record);
		}
		else
		{
			record.setCreateTime(oldr.getCreateTime());
			if(kv == null)
			{
				recorddao.update2(record);
			}
			else
			{
				recorddao.update(record);
			}
		}
		
		rm.setResult(true);
		return rm;
	}

	public ReturnMessage requestGetrecord(String mtId, String gameId, String sign) {
		ReturnMessage rm = new ReturnMessage();
		
		GameVO game = dataservice.getGameById(gameId);
		
		RecordVO record = new RecordVO();
		record.setMtId(mtId);
		record.setTableName(game.getShortName() + "_record");
		
		RecordVO rvo = recorddao.getById(record);
		rm.setResult(true);
		rm.setObject(rvo);
		
		return rm;
	}
	
	public RecordVO getrecord(String mtId, String gameId) {
		GameVO game = dataservice.getGameById(gameId);
		if(game == null || game.getFlag() == 0)
		{
			return null;
		}
		
		RecordVO record = new RecordVO();
		record.setMtId(mtId);
		record.setTableName(game.getShortName() + "_record");
		
		RecordVO rvo = recorddao.getById(record);
		return rvo;
	}
	
	public ReturnMessage requestExchange(String mtId, String gameId,
			String keyValue, String recordData, String coindata, String sign) {
		ReturnMessage rm = new ReturnMessage();
		
		GameVO game = dataservice.getGameById(gameId);
		
		RecordVO record = new RecordVO();
		record.setMtId(mtId);
		record.setTableName(game.getShortName() + "_record");
		
		JSONObject coinjson = JSONObject.fromObject(coindata);
		String mBasecoin = (String)coinjson.get("mBasecoin");
		String mSecondcoin = (String)coinjson.get("mSecondcoin");
		String mThirdcoin = (String)coinjson.get("mThirdcoin");
		
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
		KeyValue kv = new KeyValue();
		kv = JacksonUtil.fromJson(keyValue, kv.getClass());
		if(kv != null)
			rvo.setKeyValue(kv);
		this.updateCoin(rvo);
		
		rm.setResult(true);
		rm.setObject(rvo);
		
		return rm;
	}
	
	public void update2(RecordVO record) {
		recorddao.update2(record);
	}
	
	public void updateCoin(RecordVO record) {
		recorddao.updateCoin(record);
	}
}
