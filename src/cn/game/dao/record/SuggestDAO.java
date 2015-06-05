package cn.game.dao.record;

import cn.game.vo.record.SuggestVO;


public class SuggestDAO extends BasicRoleDAO<SuggestVO> {

	@Override
	public String getXmlname() {
		
		return "Suggest";
	}
}
