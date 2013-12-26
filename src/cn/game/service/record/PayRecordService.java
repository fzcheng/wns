package cn.game.service.record;

import cn.game.dao.record.PayrecordDAO;
import cn.game.inter.service.IDataService;
import cn.game.service.ReturnMessage;
import cn.game.util.DateUtil;
import cn.game.vo.basic.GameVO;
import cn.game.vo.record.PayrecordVO;
import cn.game.vo.record.RecordVO;

/**
 * 基础数据服务
 * 
 * @author fzcheng
 */
public class PayRecordService{
	PayrecordDAO payrecorddao;
	IDataService dataservice;
	RecordService recordservice;
	
	public void setPayrecorddao(PayrecordDAO payrecorddao) {
		this.payrecorddao = payrecorddao;
	}

	public void setDataservice(IDataService dataservice) {
		this.dataservice = dataservice;
	}
	
	public void setRecordservice(RecordService recordservice) {
		this.recordservice = recordservice;
	}
	
	private int getCountByUserId(PayrecordVO vo) {
		return payrecorddao.getCountByUserId(vo);
	}
	
	public ReturnMessage savepayrecord(PayrecordVO pay) {
		ReturnMessage rm = new ReturnMessage();
		
		GameVO game = dataservice.getGameById(pay.getGameId());
		
		pay.setTableName(game.getShortName() + "_payrecord");
		PayrecordVO oldpay = payrecorddao.getByOrderId(pay);
		if(oldpay == null)
		{
			//判断是成功还是失败的记录
			if("S".equals(pay.getStatus()))
			{
				//需要判断是否是首充
				int count = getCountByUserId(pay);
				int add = pay.getGameMoney();
				if(count == 0)
					add = pay.getGameMoney() * 2;
				
				RecordVO record = recordservice.getrecord(pay.getUserId(), pay.getGameId());
				if(record != null)
				{
					//增加货币
					record.addBaseCoin(add);
					record.setLastTime(DateUtil.getCurrentTime());
					recordservice.updateCoin(record);
					
					rm.setDetail("操作成功");
					rm.setResult1(record.getBaseCoin());
				}
				else
				{
					pay.setStatus("SF");
					rm.setDetail("操作失败，未找到对应用户记录。");
					rm.setResult1(0);
				}
				payrecorddao.save(pay);
			}
			else
			{
				//只做记录
				payrecorddao.save(pay);
				
				rm.setDetail("此充值失败。");
				rm.setResult1(0);
			}
		}
		else
		{
			//已处理
			if("S".equals(oldpay.getStatus()))
			{
				RecordVO record = recordservice.getrecord(pay.getUserId(), pay.getGameId());
				if(record != null)
				{
					rm.setDetail("操作成功");
					rm.setResult1(record.getBaseCoin());
				}
				else
				{
					pay.setStatus("SF");
					rm.setDetail("操作失败，未找到对应用户记录。");
					rm.setResult1(0);
				}
			}
			else
			{
				//只做记录
				rm.setDetail("此充值失败。");
				rm.setResult1(0);
			}
		}

		rm.setResult(true);
		return rm;
	}
}
