package admins.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import admins.ben.Gamepay_DateVO;
import admins.ben.TotalGamepayrecordVO;

import cn.org.util.Paging;

public class PayTotalDAO extends SqlMapClientDaoSupport {

	/**
	 * 根据日期,分区号,渠道编号统计充值金额
	 * @param countDate 编号时间
	 * @param channelId 渠道编号
	 * @param sectionId 分区ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TotalGamepayrecordVO> loadGamepayrecordList(Map<String, String> params, Paging p){
		if(p != null){
			params.put("start", String.valueOf(p.getStart()));
			params.put("pageSize", String.valueOf(p.getPageSize()));
		}
		return getSqlMapClientTemplate().queryForList("GamePayRecord.loadGamepayrecordList", params);
	}
	
	/**
	 * 统计当天充值记录
	 * @param countDate 编号时间
	 * @param channelId 渠道编号
	 * @param sectionId 分区ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gamepay_DateVO> loadGamepayrecordByDayList(){
		return getSqlMapClientTemplate().queryForList("GamePayRecord.loadGamepayrecordByDayList");
	}
	
	/**
	 * 统计周充值记录
	 * @param countDate 编号时间
	 * @param channelId 渠道编号
	 * @param sectionId 分区ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gamepay_DateVO> loadGamepayrecordByWeekList(){
		return getSqlMapClientTemplate().queryForList("GamePayRecord.loadGamepayrecordByWeekList");
	}
	
	
	/**
	 * 统计当天充值记录
	 * @param countDate 编号时间
	 * @param channelId 渠道编号
	 * @param sectionId 分区ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gamepay_DateVO> loadGamepayrecordByMonthList(){
		return getSqlMapClientTemplate().queryForList("GamePayRecord.loadGamepayrecordByMonthList");
	}
	
}
