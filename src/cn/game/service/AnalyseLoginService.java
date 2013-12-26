package cn.game.service;

import java.util.Date;

import cn.game.dao.analyse.DayLoginDAO;
import cn.game.dao.analyse.MonthLoginDAO;
import cn.game.dao.analyse.WeekLoginDAO;
import cn.game.util.DateUtil;
import cn.game.vo.analyse.DayLoginVO;
import cn.game.vo.analyse.MonthLoginVO;
import cn.game.vo.analyse.WeekLoginVO;
import cn.game.vo.record.RoleLoginRecordVO;

public class AnalyseLoginService {

	DayLoginDAO daylogindao;
	WeekLoginDAO weeklogindao;
	MonthLoginDAO monthlogindao;
	
	public void setDaylogindao(DayLoginDAO daylogindao) {
		this.daylogindao = daylogindao;
	}

	public void setWeeklogindao(WeekLoginDAO weeklogindao) {
		this.weeklogindao = weeklogindao;
	}

	public void setMonthlogindao(MonthLoginDAO monthlogindao) {
		this.monthlogindao = monthlogindao;
	}
	
	//每日
	public int saveDayLogin(DayLoginVO vo)
	{
		return daylogindao.save(vo);
	}
	
	public DayLoginVO getDayLoginByDate(String date)
	{
		DayLoginVO vo = daylogindao.getByDate(date);
		if(vo == null)
		{
			vo = new DayLoginVO();
			vo.setDate(date);
			vo.setId(saveDayLogin(vo));
		}
		
		return vo;
	}
	
	public void updateDayLogin(DayLoginVO vo)
	{
		daylogindao.update(vo);
	}
	
	//每周
	public int saveWeekLogin(WeekLoginVO vo)
	{
		return weeklogindao.save(vo);
	}
	
	public WeekLoginVO getWeekLoginByDate(String date)
	{
		WeekLoginVO vo = weeklogindao.getByDate(date);
		if(vo == null)
		{
			vo = new WeekLoginVO();
			vo.setDate(date);
			vo.setId(saveWeekLogin(vo));
		}
		
		return vo;
		
	}
	
	public void updateWeekLogin(WeekLoginVO vo)
	{
		weeklogindao.update(vo);
	}
	
	//每月
	public int saveMonthLogin(MonthLoginVO vo)
	{
		return monthlogindao.save(vo);
	}
	
	public MonthLoginVO getMonthLoginByDate(String date)
	{
		MonthLoginVO vo = monthlogindao.getByDate(date);
		if(vo == null)
		{
			vo = new MonthLoginVO();
			vo.setDate(date);
			vo.setId(saveMonthLogin(vo));
		}
		
		return vo;
	}
	
	public void updateMonthLogin(MonthLoginVO vo)
	{
		monthlogindao.update(vo);
	}
	
	/*
	 * 新注册
	 */
	public void addReg()
	{
		DayLoginVO dayvo = getDayLoginByDate(DateUtil.getCurrentDay());
		WeekLoginVO weekvo = getWeekLoginByDate(DateUtil.getCurrentWeekDay());
		MonthLoginVO monthvo = getMonthLoginByDate(DateUtil.getCurrentMonthDay());
		
		dayvo.setRegcount(dayvo.getRegcount() + 1);
		dayvo.setLogincount(dayvo.getLogincount() + 1);
		
		weekvo.setRegcount(weekvo.getRegcount() + 1);
		weekvo.setLogincount(weekvo.getLogincount() + 1);
		
		monthvo.setRegcount(monthvo.getRegcount() + 1);
		monthvo.setLogincount(monthvo.getLogincount() + 1);
		
		this.updateDayLogin(dayvo);
		this.updateWeekLogin(weekvo);
		this.updateMonthLogin(monthvo);
	}
	
	/*
	 * 新登陆
	 */
	public void addLogin(String regdate, String lastlogindate, RoleLoginRecordVO vo)
	{
		if(!DateUtil.isSameDay(lastlogindate))
		{
			DayLoginVO dayvo = getDayLoginByDate(DateUtil.getCurrentDay());
			dayvo.setLogincount(dayvo.getLogincount() + 1);
			
			//判断是否是连续3天登陆
			if(DateUtil.getBettwenDays(lastlogindate, DateUtil.DateTostr(new Date())) == 1)
			{
				if(vo != null && vo.getClgcdays() + 1 >= 3)
				{
					dayvo.setClogincount(dayvo.getClogincount() + 1);
				}
			}
			this.updateDayLogin(dayvo);
			
			//计算留存量
			int dd = DateUtil.getBettwenDays(regdate, DateUtil.getCurrentDay());
			if(dd > 0 && dd < 7)
			{
				DayLoginVO regdayvo = getDayLoginByDate(DateUtil.formatDateToStr(DateUtil.strToDate(regdate), "yyyy-MM-dd"));
				switch(dd)
				{
				case 1:
					regdayvo.setLcount2(regdayvo.getLcount2() + 1);
					break;
				case 2:
					regdayvo.setLcount3(regdayvo.getLcount3() + 1);
					break;
				case 3:
					regdayvo.setLcount4(regdayvo.getLcount4() + 1);
					break;
				case 4:
					regdayvo.setLcount5(regdayvo.getLcount5() + 1);
					break;
				case 5:
					regdayvo.setLcount6(regdayvo.getLcount6() + 1);
					break;
				case 6:
					regdayvo.setLcount7(regdayvo.getLcount7() + 1);
					break;
				}
				this.updateDayLogin(regdayvo);
			}			
		}
		
		if(!DateUtil.isSameWeek(lastlogindate))
		{
			WeekLoginVO weekvo = getWeekLoginByDate(DateUtil.getCurrentWeekDay());
			weekvo.setLogincount(weekvo.getLogincount() + 1);
			this.updateWeekLogin(weekvo);
			
			int dd = DateUtil.getBettwenWeeks(regdate, DateUtil.getCurrentDay());
			if(dd > 0 && dd < 4)
			{
				WeekLoginVO regweekvo = getWeekLoginByDate(DateUtil.getWeekDay(regdate));
				switch(dd)
				{
				case 1:
					regweekvo.setLcount2(regweekvo.getLcount2() + 1);
					break;
				case 2:
					regweekvo.setLcount3(regweekvo.getLcount3() + 1);
					break;
				case 3:
					regweekvo.setLcount4(regweekvo.getLcount4() + 1);
					break;
				}
				this.updateWeekLogin(regweekvo);
			}
		}
		
		if(!DateUtil.isSameMonth(lastlogindate))
		{
			MonthLoginVO monthvo = getMonthLoginByDate(DateUtil.getCurrentMonthDay());
			monthvo.setLogincount(monthvo.getLogincount() + 1);
			this.updateMonthLogin(monthvo);
			
			int dd = DateUtil.getBettwenMonths(regdate, DateUtil.getCurrentDay());
			if(dd > 0 && dd < 4)
			{
				MonthLoginVO regmonthvo = getMonthLoginByDate(DateUtil.getMonthDay(regdate));
				switch(dd)
				{
				case 1:
					regmonthvo.setLcount2(regmonthvo.getLcount2() + 1);
					break;
				case 2:
					regmonthvo.setLcount3(regmonthvo.getLcount3() + 1);
					break;
				case 3:
					regmonthvo.setLcount4(regmonthvo.getLcount4() + 1);
					break;
				}
				this.updateMonthLogin(regmonthvo);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

		String cd = DateUtil.getCurrentDay();
		String wd = DateUtil.getCurrentWeekDay();
		String md = DateUtil.getCurrentMonthDay();
		
		System.out.println(cd);
		System.out.println(wd);
		System.out.println(md);
		
		int dd = DateUtil.getBettwenDays("2012-05-15", "2012-05-15");
		System.out.println(dd);
		
		String wd2 = DateUtil.getWeekDay("2012-04-10");
		String md2 = DateUtil.getMonthDay("2012-04-19");
		
		System.out.println(wd2);
		System.out.println(md2);
	}
}
