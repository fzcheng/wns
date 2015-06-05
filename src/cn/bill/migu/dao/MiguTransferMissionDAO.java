package cn.bill.migu.dao;

import java.util.List;

import cn.bill.migu.vo.MiguTransferMissionVO;
import cn.game.dao.record.BasicRoleDAO;


public class MiguTransferMissionDAO extends BasicRoleDAO<MiguTransferMissionVO> {

	@Override
	public String getXmlname() {
		
		return "MiguTransferMission";
	}
	
	@SuppressWarnings("unchecked")
	public List<MiguTransferMissionVO> getUnDealList() {
		return (List<MiguTransferMissionVO>)getSqlMapClientTemplate().queryForList(getXmlname() + ".getUnDeal");
	}
}
