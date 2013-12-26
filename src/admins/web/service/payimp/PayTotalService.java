package admins.web.service.payimp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.org.util.DateFormatTool;
import cn.org.util.Paging;
import cn.org.util.WeekTool;

import admins.ben.Gamepay_DateVO;
import admins.ben.TotalGamepayrecordVO;
import admins.dao.Gamepay_DateDAO;
import admins.dao.PayTotalDAO;
import admins.web.service.IPayTotalService;
/**
 * @author luoyaoren
 * 
 * 14:24:51 
 */
public class PayTotalService implements IPayTotalService {
	
	private PayTotalDAO payTotalDAO;
	public void setGamepay_DateDAO(Gamepay_DateDAO gamepayDateDAO) {
		gamepay_DateDAO = gamepayDateDAO;
	}
	private Gamepay_DateDAO gamepay_DateDAO;
	
	public void setPayTotalDAO(PayTotalDAO payTotalDAO) {
		this.payTotalDAO = payTotalDAO;
	}

	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public List<TotalGamepayrecordVO> loadGamepayrecordList(
			Map<String, String> params, Paging p) {
		 
		return payTotalDAO.loadGamepayrecordList(params, p);
	}

	/**
	 * 日运营统计
	 * @param countDate 编号时间
	 * @param channelId 渠道编号
	 * @param sectionId 分区ID
	 * @return
	 */
	
	@Override
	public void Gamepay_day(String initTime) {
		// TODO Auto-generated method stub
		
		
		try {
			 
			 
			int sumPay=0;
			int sumCountPay=0;	
			List<Gamepay_DateVO> dayOpVo = payTotalDAO.loadGamepayrecordByDayList();
				if(dayOpVo.size()>0){
					
					for(Gamepay_DateVO vo : dayOpVo){
						
						Gamepay_DateVO v=new Gamepay_DateVO();
						 v.setChannelId(vo.getChannelId());
						 v.setSectionId(vo.getSectionId());
						 v.setMoneyType(vo.getMoneyType());
						 v.setPayCount(vo.getPayCount());
						 v.setRechargeCount(vo.getRechargeCount());
						 v.setCountDate(f.format(DateFormatTool.StringToDate(DateFormatTool.getBeforeDay("-", 1, new Date()))));
						 sumPay +=vo.getRechargeCount();
						 sumCountPay +=vo.getPayCount();
						 System.out.println("...."+vo.getChannelId());
						 System.out.println("...."+vo.getSectionId());
						 System.out.println("...."+vo.getMoneyType());
						 System.out.println("...."+vo.getPayCount());
						 System.out.println("...."+vo.getRechargeCount());
						 System.out.println("...."+vo.getCountDate());
						 gamepay_DateDAO.save_day(v);
					}
					
					System.out.println("（日）所有渠道总额"+sumPay);
					Gamepay_DateVO v2=new Gamepay_DateVO();
					 v2.setChannelId("0");
					 v2.setSectionId(0);
					 v2.setMoneyType(1);
					 v2.setPayCount(sumCountPay);
					 v2.setRechargeCount(sumPay);
					 //统计时间，当前时间的前一天
					 v2.setCountDate(f.format(DateFormatTool.StringToDate(DateFormatTool.getBeforeDay("-", 1, new Date()))));
					 gamepay_DateDAO.save_day(v2);
				}	
 
			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	/**
	 * 周  运营统计
	 * @param countDate 编号时间
	 * @param channelId 渠道编号
	 * @param sectionId 分区ID
	 * @return
	 */
	@Override
	public void Gamepay_week(String initTime) {
		// TODO Auto-generated method stub
		try {
			 
			 
			int sumPay=0;
			int sumCountPay=0;
			List<Gamepay_DateVO> dayOpVo = payTotalDAO.loadGamepayrecordByWeekList();
				if(dayOpVo.size()>0){
					
					for(Gamepay_DateVO vo : dayOpVo){
						
						Gamepay_DateVO v=new Gamepay_DateVO();
						 v.setChannelId(vo.getChannelId());
						 v.setSectionId(vo.getSectionId());
						 v.setMoneyType(vo.getMoneyType());
						 v.setPayCount(vo.getPayCount());
						 v.setRechargeCount(vo.getRechargeCount());
						 v.setCountDate(f.format(new Date()));
						 sumPay +=vo.getRechargeCount();
						 sumCountPay +=vo.getPayCount();
						 System.out.println("...."+vo.getChannelId());
						 System.out.println("...."+vo.getSectionId());
						 System.out.println("...."+vo.getMoneyType());
						 System.out.println("...."+vo.getPayCount());
						 System.out.println("...."+vo.getRechargeCount());
						 System.out.println("...."+new WeekTool().getPreviousWeekSunday());
						 gamepay_DateDAO.save_week(v);
					}
					System.out.println("（周）所有渠道总额"+sumPay);
					Gamepay_DateVO v2=new Gamepay_DateVO();
					 v2.setChannelId("0");
					 v2.setSectionId(0);
					 v2.setMoneyType(1);
					 v2.setPayCount(sumCountPay);
					 v2.setRechargeCount(sumPay);
					 v2.setCountDate(new WeekTool().getPreviousWeekSunday());
					 gamepay_DateDAO.save_week(v2);
				}	
				
			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	/**
	 * 月运营统计
	 * @param countDate 编号时间
	 * @param channelId 渠道编号
	 * @param sectionId 分区ID
	 * @return
	 */
	@Override
	public void Gamepay_month(String initTime) {
		// TODO Auto-generated method stub
		try {
			 
		/*	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
			//制定日期格式
			Calendar sc=Calendar.getInstance();
			Date date=new Date();
			sc.setTime(date);
			sc.add(Calendar.MONTH,-1); 
			//将当前日期加一个月
			String validityDate=df.format(sc.getTime()); */
			int sumPay=0;
			int sumCountPay=0;
			Map<String, String> map = DateFormatTool.getDateFormatTool().getFirstday_Lastday_Month(new Date());
			List<Gamepay_DateVO> dayOpVo = payTotalDAO.loadGamepayrecordByMonthList();
				if(dayOpVo.size()>0){
					
					for(Gamepay_DateVO vo : dayOpVo){
						
						Gamepay_DateVO v=new Gamepay_DateVO();
						 v.setChannelId(vo.getChannelId());
						 v.setSectionId(vo.getSectionId());
						 v.setMoneyType(vo.getMoneyType());
						 v.setPayCount(vo.getPayCount());
						 v.setRechargeCount(vo.getRechargeCount());
						 v.setCountDate(map.get("last"));
						 System.out.println("...."+vo.getChannelId());
						 System.out.println("...."+vo.getSectionId());
						 System.out.println("...."+vo.getMoneyType());
						 System.out.println("...."+vo.getPayCount());
						 System.out.println("...."+vo.getRechargeCount());
						 System.out.println("...."+vo.getCountDate());
						 sumPay +=vo.getRechargeCount();
						 sumCountPay +=vo.getPayCount();
						 gamepay_DateDAO.save_month(v);
					}
					System.out.println("（月）所有渠道总额"+sumPay);
					Gamepay_DateVO v2=new Gamepay_DateVO();
					 v2.setChannelId("0");
					 v2.setSectionId(0);
					 v2.setMoneyType(1);
					 v2.setPayCount(sumCountPay);
					 v2.setRechargeCount(sumPay);
					 v2.setCountDate(map.get("last"));
					 gamepay_DateDAO.save_month(v2);
				}	
 
			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}



	@Override
	public List<Gamepay_DateVO> loadGamepayDayList(Map<String, String> params,
			Paging p) {
		// TODO Auto-generated method stub
		return gamepay_DateDAO.loadGamepayDayList(params, p);
	}



	@Override
	public List<Gamepay_DateVO> loadGamepayMonthList(
			Map<String, String> params, Paging p) {
		// TODO Auto-generated method stub
		return gamepay_DateDAO.loadGamepayMonthList(params, p);
	}



	@Override
	public List<Gamepay_DateVO> loadGamepayWeekList(Map<String, String> params,
			Paging p) {
		// TODO Auto-generated method stub
		return gamepay_DateDAO.loadGamepayWeekList(params, p);
	}
	@Override
	public List<Gamepay_DateVO> CheckGamepayWeekList() {
		// TODO Auto-generated method stub
		return gamepay_DateDAO.CheckGamepayWeekList();
	}
			
	
}
