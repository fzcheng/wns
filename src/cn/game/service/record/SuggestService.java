package cn.game.service.record;

import cn.game.dao.record.SuggestDAO;
import cn.game.inter.service.IDataService;
import cn.game.service.ReturnMessage;
import cn.game.vo.basic.GameVO;
import cn.game.vo.record.SuggestVO;
import cn.org.util.DateUtil;

/**
 * 玩家意见
 * 
 * @author fzcheng
 */
public class SuggestService{
	SuggestDAO suggestdao;
	IDataService dataservice;
	
	public void setSuggestdao(SuggestDAO suggestdao) {
		this.suggestdao = suggestdao;
	}

	public void setDataservice(IDataService dataservice) {
		this.dataservice = dataservice;
	}
	
	public ReturnMessage addSuggest(String gameId, SuggestVO suggest) {
		ReturnMessage rm = new ReturnMessage();
		GameVO game = dataservice.getGameById(gameId);
		if(game == null)
			return rm;
		
		suggest.setTime(DateUtil.getCurrentTime());
		suggest.setTableName(game.getShortName() + "_suggest");
		
		suggestdao.save(suggest);

		rm.setResult(true);
		return rm;
	}
}
