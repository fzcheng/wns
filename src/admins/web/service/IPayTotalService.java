package admins.web.service;

import java.util.List;
import java.util.Map;

import cn.org.util.Paging;

import admins.ben.Gamepay_DateVO;
import admins.ben.TotalGamepayrecordVO;

/**
 * @author luoyaoren
 * @version 处理充值统计
 * 14:24:51 
 */
public interface IPayTotalService {
 
 
	/**
	 * 根据 渠道、分区 统计充值金额
	 * @param roleId 角色id
	 */
	public List<TotalGamepayrecordVO> loadGamepayrecordList(Map<String, String> params, Paging p);
	
	 public void Gamepay_day(String initTime);
	 public void Gamepay_week(String initTime);
	 public void Gamepay_month(String initTime);
	 
	 List<Gamepay_DateVO> loadGamepayMonthList(
				Map<String, String> params, Paging p); 
	 
	 List<Gamepay_DateVO> loadGamepayWeekList(
				Map<String, String> params, Paging p); 
	 List<Gamepay_DateVO> CheckGamepayWeekList(
				); 
	 
	 List<Gamepay_DateVO> loadGamepayDayList(
				Map<String, String> params, Paging p); 
	 
}
