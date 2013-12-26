package admins.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.org.util.Paging;
import admins.ben.Gamepay_DateVO;

public class Gamepay_DateDAO extends SqlMapClientDaoSupport {

	/**
	 * 日运营统计
	 * 
	 * @param countDate
	 *            编号时间
	 * @param channelId
	 *            渠道编号
	 * @param sectionId
	 *            分区ID
	 * @return
	 */
 
	/**
	 * 日运营统计
	 * @param countDate 编号时间
	 * @param channelId 渠道编号
	 * @param sectionId 分区ID
	 * @return
	 */
	public void save_day(Gamepay_DateVO vo) {
		getSqlMapClientTemplate().insert("Gamepay_date.saveDay", vo);
	}

	public void update_day(Gamepay_DateVO vo) {
		getSqlMapClientTemplate().update("Gamepay_date.updateDay", vo);
	}

	/**
	 * 周运营统计
	 * 
	 * @param countDate
	 *            编号时间
	 * @param channelId
	 *            渠道编号
	 * @param sectionId
	 *            分区ID
	 * @return
	 */
	public void save_week(Gamepay_DateVO vo) {
		getSqlMapClientTemplate().insert("Gamepay_date.saveWeek", vo);
	}

	public void update_week(Gamepay_DateVO vo) {
		getSqlMapClientTemplate().update("Gamepay_date.updateWeek", vo);
	}

	/**
	 * 月运营统计
	 * 
	 * @param countDate
	 *            编号时间
	 * @param channelId
	 *            渠道编号
	 * @param sectionId
	 *            分区ID
	 * @return
	 */
	public void save_month(Gamepay_DateVO vo) {
		getSqlMapClientTemplate().insert("Gamepay_date.saveMonth", vo);
	}

	public void update_month(Gamepay_DateVO vo) {
		getSqlMapClientTemplate().update("Gamepay_date.updateMonth", vo);
	}

	/**
	 * 获取日营统计数据
	 * 
	 * @param countDate
	 *            编号时间
	 * @param channelId
	 *            渠道编号
	 * @param sectionId
	 *            分区ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gamepay_DateVO> loadGamepayDayList(Map<String, String> params,
			Paging p) {
		if (p != null) {
			params.put("start", String.valueOf(p.getStart()));
			params.put("pageSize", String.valueOf(p.getPageSize()));
		}
		return getSqlMapClientTemplate().queryForList(
				"Gamepay_date.loadGamepayDayList", params);
	}

	/**
	 * 获取周营统计数据
	 * 
	 * @param countDate
	 *            编号时间
	 * @param channelId
	 *            渠道编号
	 * @param sectionId
	 *            分区ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gamepay_DateVO> loadGamepayWeekList(Map<String, String> params,
			Paging p) {
		if (p != null) {
			params.put("start", String.valueOf(p.getStart()));
			params.put("pageSize", String.valueOf(p.getPageSize()));
		}
		return getSqlMapClientTemplate().queryForList(
				"Gamepay_date.loadGamepayWeekList", params);
	}
	/**
	 * 检测上周是否做过统计记录
	 * @param countDate 编号时间
	 * @param channelId 渠道编号
	 * @param sectionId 分区ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gamepay_DateVO> CheckGamepayWeekList(){
		return getSqlMapClientTemplate().queryForList("GamePayRecord.CheckGamepayrecordByWeekList");
	}
	/**
	 * 获取月营统计数据
	 * 
	 * @param countDate
	 *            编号时间
	 * @param channelId
	 *            渠道编号
	 * @param sectionId
	 *            分区ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gamepay_DateVO> loadGamepayMonthList(
			Map<String, String> params, Paging p) {
		if (p != null) {
			params.put("start", String.valueOf(p.getStart()));
			params.put("pageSize", String.valueOf(p.getPageSize()));
		}
		return getSqlMapClientTemplate().queryForList(
				"Gamepay_date.loadGamepayMonthList", params);
	}

}
