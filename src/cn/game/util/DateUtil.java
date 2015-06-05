package cn.game.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import cn.game.vo.record.KeyValue;
import cn.org.util.HashHex;


/**
 * 功能:时间处理工具
 * 
 * @author hhj
 * 
 * 10:35:18 AM
 */
public class DateUtil {

	/** ***********以秒为基数的常量**************** */
	public static int MINUTE = 60;// 分钟
	public static int HOUR = 60 * MINUTE;// 小时
	public static int DAY = 24 * HOUR;// 天
	
	public static String firstDayOfMonth(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(c.getTime());
	}
	public static String lastDayOfMonth(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(c.getTime());
	}

	/** ******************************** */

	

	/**
	 * 字符串转换成date
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date strToDate(String dateString) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = df.parse(dateString);
		} catch (Exception ex) {
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df2.parse(dateString);
			} catch (Exception ex2) {
				date = new Date();
			}
		}
		return date;
	}
	
	/**
	 * 比较两个时间大小
	 * @param beginstr1
	 * @param endstr2
	 * @return
	 */
	public static boolean moreTimeSize(String beginstr1,String endstr2){
		Date str1 = strToDate(beginstr1);
		Date str2 = strToDate(endstr2);
		if(str1.getTime() > str2.getTime()){
			return true;
		}
		return false;
	}
	
	
	/**
	 *  data 转 字符
	 * @param date
	 * @return
	 */
	public static String DateTostr(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_new="";
		try 
		{
			date_new = df.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return date_new;
	}
	
	/**
	 *  data 转 字符
	 * @param date
	 * @return
	 */
	public static String TimeTostr(Date date){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		String date_new="";
		try 
		{
			date_new = df.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return date_new;
	}

	/**
	 *  data 转 字符
	 *  格式为yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String DateTostr(Date date,String format){
		String date_new = "";
		try{
			SimpleDateFormat df = new SimpleDateFormat(format);
			date_new = df.format(date);
		}catch (Exception e) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date_new = df.format(date);
		}
		
		return date_new;
	}
	
	/***************************************************************************
	 * 将日期字符转换成日期类型
	 * 
	 * @param 日期字符串
	 **************************************************************************/
	public static Date strToShortDate(String str) {
		Date de = null;
		try {
			if (str.length() >= 10) {
				str = str.substring(0, 10);
			}
			String[] dateStr = str.split("-");
			int year = Integer.parseInt(dateStr[0]);
			int month = Integer.parseInt(dateStr[1]);
			int day = Integer.parseInt(dateStr[2]);
			de = new Date(year - 1900, month - 1, day);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return de;
	}

	/**
	 * 判断当前时间是否在date_ks和date_js之间,date_ks和date_js的表示形式为2008-9-5 5:18:53
	 * 
	 * @param date_ks
	 * @param date_js
	 * @return
	 */
	public static boolean isShortDatenowBetweenBeginEnd(String date_ks,
			String date_js) {
		boolean result = false;

		if (date_ks == null || date_ks.equals("") || date_js == null
				|| date_js.equals("")) {
			return result;
		}
		Date nowTime = new Date();
		Date begin = DateUtil.strToShortDate(date_ks);
		Date end = DateUtil.strToShortDate(date_js);

		if (nowTime.after(begin) && nowTime.before(end)) {
			result = true;
		}

		return result;
	}

	/**
	 * 字符串转换成time
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date strToTime(String timeString) {
		Date date = null;
		date = DateUtil.strToDate(DateUtil.getTodayStr() + " " + timeString);
		return date;
	}

	/**
	 * 返回今天的字符串 格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getTodayStr() {
		String todayStr = null;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			todayStr = df.format(date.getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return todayStr;
	}

	/**
	 * 判断当前时间是否在time_ks和time_js之间,time_ks和time_js的表示形式为5:18:53
	 * 
	 * @param time_ks
	 * @param time_js
	 * @return
	 */
	public static boolean isTimenowBetweenBeginEnd(String time_ks,
			String time_js) {
		boolean result = false;

		if (time_ks == null || time_ks.equals("") || time_js == null
				|| time_js.equals("")) {
			return result;
		}
		Date nowTime = new Date();
		Date begin = DateUtil.strToTime(time_ks);
		Date end = DateUtil.strToTime(time_js);

		if (nowTime.after(begin) && nowTime.before(end)) {
			result = true;
		}

		return result;
	}

	/**
	 * 判断当前时间是否在date_ks和date_js之间,date_ks和date_js的表示形式为2008-9-5 5:18:53
	 * 
	 * @param date_ks
	 * @param date_js
	 * @return
	 */
	public static boolean isDatenowBetweenBeginEnd(String date_ks,String date_js) {
		boolean result = false;
		if (date_ks == null || date_ks.equals("") || date_js == null
				|| date_js.equals("")) {
			return result;
		}
		Date nowTime = new Date();
		Date begin = DateUtil.strToDate(date_ks);
		Date end = DateUtil.strToDate(date_js);

		if (nowTime.after(begin) && nowTime.before(end)) {
			result = true;
		}

		return result;
	}

	/**
	 * 时间从begin_time开始算起，有效时间是seconds秒，判断当前时间是否超过有效时间
	 * 
	 * @param begin_time
	 *            形式 如:2008-9-11 23:58:00
	 * @param seconds
	 *            秒
	 * @return 返回true表示超过有效时间，false表示没有超过有效时间
	 */
	public static boolean isOverdue(Date begin_time, int seconds) {
		boolean result = true;
		if (begin_time == null) {
			return true;
		}
		if (seconds == 0) {
			return true;
		}
		// 当前时间
		Calendar current_time = Calendar.getInstance();
		// 当前时间之前的seconds秒的有效时间
		current_time.add(Calendar.SECOND, -seconds);
		Date effect_time = current_time.getTime();
		// 判断开始时间是否在有效时间之前，如果在有效时间之前则表示已当前时间已超过开始时间seconds秒返回false
		if (begin_time.before(effect_time)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	/**
	 * 时间从begin_time开始算起，有效时间是seconds秒，判断当前时间是否超过有效时间
	 * 
	 * @param begin_time
	 *            形式 如:2008-9-11 23:58:00
	 * @param seconds
	 *            秒
	 * @return 返回true表示超过有效时间，false表示没有超过有效时间
	 */
	public static boolean isOverdue(String begin_timestr, int seconds) {
		Date begin_time = strToDate(begin_timestr);
		boolean result = true;
		if (begin_time == null) {
			return true;
		}
		if (seconds == 0) {
			return true;
		}
		// 当前时间
		Calendar current_time = Calendar.getInstance();
		// 当前时间之前的seconds秒的有效时间
		current_time.add(Calendar.SECOND, -seconds);
		Date effect_time = current_time.getTime();
		// 判断开始时间是否在有效时间之前，如果在有效时间之前则表示已当前时间已超过开始时间seconds秒返回false
		if (begin_time.before(effect_time)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 比较日期返回天数
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferDays(Date begin_time, Date end_time) {
		if (begin_time == null || end_time == null) {
			return 0;
		}
		int days = 0;
		days = (int) ((end_time.getTime() - begin_time.getTime()) / (DAY * 1000));
		return days;
	}

	/**
	 * 比较日期返回天数
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferDays(String begin_time, String end_time) {
		if (begin_time == null || end_time == null) {
			return 0;
		}
		Date begin_times = formatDate(strToDate(begin_time),
				"yyyy-MM-dd HH:mm:ss");
		Date end_timea = formatDate(strToDate(end_time), "yyyy-MM-dd HH:mm:ss");
		int days = 0;
		days = (int) ((end_timea.getTime() - begin_times.getTime()) / (DAY * 1000));
		return days;
	}

	/**
	 * 比较日期返回天数比较形式"yyyy-MM-dd"
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferDay(String begin_time, String end_time) {
		if (begin_time == null || end_time == null) {
			return 0;
		}

		Date begin_times = formatDate(strToDate(begin_time), "yyyy-MM-dd");
		Date end_timea = formatDate(strToDate(end_time), "yyyy-MM-dd");

		int days = 0;
		days = (int) ((end_timea.getTime() - begin_times.getTime()) / (DAY * 1000));
		return days;
	}
	
	/**
	 * 比较日期返回天数
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDaysBetween(String beginDate, String endDate)
    {
		Date bDate = strToDate(beginDate);
		Date eDate = strToDate(endDate);
		
		Calendar d1 = new GregorianCalendar();
		d1.setTime(bDate);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(eDate);

		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2)
		{
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 比较日期返回分钟
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimes(Date begin_time, Date end_time) {
		if (begin_time == null || end_time == null) {
			return 0;
		}

		begin_time = formatDate(begin_time, "yyyy-MM-dd HH:mm:ss");
		end_time = formatDate(end_time, "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		days = (int) ((end_time.getTime() - begin_time.getTime()) / (MINUTE * 1000));
		return days;
	}
	
	/**
	 * 比较日期返回秒
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimes_seconds(Date begin_time, Date end_time) {
		if (begin_time == null || end_time == null) {
			return 0;
		}

		begin_time = formatDate(begin_time, "yyyy-MM-dd HH:mm:ss");
		end_time = formatDate(end_time, "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		days = (int) ((end_time.getTime() - begin_time.getTime()) / (1000));
		return days;
	}
	
	/**
	 * 比较日期返回秒
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimes_seconds(String beginTimestr, Date end_time) {
		
		if (beginTimestr == null || end_time == null) {
			return 0;
		}

		Date begin_time = strToDate(beginTimestr);
		begin_time = formatDate(begin_time, "yyyy-MM-dd HH:mm:ss");
		end_time = formatDate(end_time, "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		days = (int) ((end_time.getTime() - begin_time.getTime()) / (1000));
		return days;
	}
	
	/**
	 * 比较日期返回秒
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimes_seconds(String beginTimestr, String end_timeStr) {
		
		if (beginTimestr == null || end_timeStr == null) {
			return 0;
		}

		Date begin_time = strToDate(beginTimestr);
		Date end_time = strToDate(end_timeStr);
		begin_time = formatDate(begin_time, "yyyy-MM-dd HH:mm:ss");
		end_time = formatDate(end_time, "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		days = (int) ((end_time.getTime() - begin_time.getTime()) / (1000));
		return days;
	}
	
	/**
	 * 比较日期返回秒
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimes_seconds(Date begin_time, String endTimeStr) {
		if (begin_time == null || endTimeStr == null) {
			return 0;
		}

		Date end_time = strToDate(endTimeStr);
		begin_time = formatDate(begin_time, "yyyy-MM-dd HH:mm:ss");
		end_time = formatDate(end_time, "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		days = (int) ((end_time.getTime() - begin_time.getTime()) / (1000));
		return days;
	}
	
	
	/**
	 * 比较日期返回秒
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimes_seconds2(Date begin_time, String endTimeStr) {
		if (begin_time == null || endTimeStr == null) {
			return 0;
		}

		Date end_time = strToTime(endTimeStr);
		begin_time = formatDate(begin_time, "yyyy-MM-dd HH:mm:ss");
		end_time = formatDate(end_time, "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		days = (int) ((end_time.getTime() - begin_time.getTime()) / (1000));
		return days;
	}
	
	/**
	 * 比较日期返回分钟
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimes(String begin_timestr, Date end_time) {
		Date begin_time = strToDate(begin_timestr);
		if (begin_time == null || end_time == null) {
			return 0;
		}

		begin_time = formatDate(begin_time, "yyyy-MM-dd HH:mm:ss");
		end_time = formatDate(end_time, "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		days = (int) ((end_time.getTime() - begin_time.getTime()) / (MINUTE * 1000));
		return days;
	}
	
	/**
	 * 比较日期返回秒
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimesByss(String begin_time, String end_time) {
		if (begin_time == null || end_time == null) {
			return 0;
		}

		Date begin_times = formatDate(strToDate(begin_time),
		"yyyy-MM-dd HH:mm:ss");
		Date end_timea = formatDate(strToDate(end_time), "yyyy-MM-dd HH:mm:ss");
		
		int days = 0;
		long a = end_timea.getTime() - begin_times.getTime();
		days = (int) ((end_timea.getTime() - begin_times.getTime()) /  1000);
		return days;
	}
	
	/**
	 * 比较日期返回秒
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimesByss(Date begin_time, Date end_time) {
		if (begin_time == null || end_time == null) {
			return 0;
		}

		begin_time = formatDate(begin_time, "yyyy-MM-dd HH:mm:ss");
		end_time = formatDate(end_time, "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		days = (int) ((end_time.getTime() - begin_time.getTime()) / 1000);
		return days;
	}
	
	
	
	public static int getBettwen(Date early,Date laster,int addMin){
		if(early==null||laster==null){
			return 0;
		}
		int min = (int)(laster.getTime()-early.getTime())/60000;
		if(min > addMin){
			return 0;
		}else{
			return addMin - min;
		}
	} 

	public static int getBettwen(String earlystr,Date laster,int addMin){
		Date early = strToDate(earlystr);
		if(early==null||laster==null){
			return 0;
		}
		int min = (int)(laster.getTime()-early.getTime())/60000;
		if(min > addMin){
			return 0;
		}else{
			return addMin - min;
		}
	}
	
	public static int getBettwenSeconds(String earlystr, Date laster, int addsecond) {
		Date early = strToDate(earlystr);
		if(early==null||laster==null){
			return 0;
		}
		int min = (int)(laster.getTime()-early.getTime())/1000;
		if(min > addsecond){
			return 0;
		}else{
			return addsecond - min;
		}
	}
	
	/**
	 * 比较日期返回分钟
	 * 
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static int getDifferTimes(String begin_time, String end_time) {
		if (begin_time == null || end_time == null) {
			return 0;
		}

		Date begin_times = formatDate(strToDate(begin_time),"yyyy-MM-dd HH:mm:ss");
		Date end_timea = formatDate(strToDate(end_time), "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		long a = end_timea.getTime() - begin_times.getTime();
		days = (int) ((end_timea.getTime() - begin_times.getTime()) / (MINUTE * 1000));
		return days;
	}
	

	public static int getDifferTimesMiao(String begin_time, String end_time) {
		if (begin_time == null || end_time == null) {
			return 0;
		}

		Date begin_times = formatDate(strToDate(begin_time),"yyyy-MM-dd HH:mm:ss");
		Date end_timea = formatDate(strToDate(end_time), "yyyy-MM-dd HH:mm:ss");

		int days = 0;
		long a = end_timea.getTime() - begin_times.getTime();
		days = (int) ((end_timea.getTime() - begin_times.getTime()) / (1000));
		return days;
	}

	/**
	 * 根据pattern格式化date
	 * 
	 * @param date
	 * @param pattern
	 *            形式如"yyyy-MM-dd"
	 * @return
	 */
	public static Date formatDate(Date date, String pattern) {
		if (date == null || pattern == null) {
			return date;
		}
		Date new_date = null;

		SimpleDateFormat f = new SimpleDateFormat(pattern);

		String date_str = f.format(date);

		try {
			new_date = f.parse(date_str);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		return new_date;
	}

	/**
	 * 根据pattern格式化date
	 * 
	 * @param date
	 * @param pattern
	 *            形式如"yyyy-MM-dd"
	 * @return
	 */
	public static String formatDateToStr(Date date, String pattern) {
		if (date == null || pattern == null) {
			return "";
		}

		SimpleDateFormat f = new SimpleDateFormat(pattern);

		String date_str = f.format(date);

		return date_str;
	}

	/**
	 * 得到当前时间与compare_time所差天数
	 * 
	 * @param compare_time
	 * @return
	 */
	public static int getDifferDaysToToday(Date compare_time) {
		if (compare_time == null) {
			return 0;
		}
		// 当前时间
		Date today = new Date();
		return getDifferDays(compare_time, today);
	}

	public static int getDifferDaysToToday(String compare_time) {
		if (compare_time == null) {
			return 0;
		}
		// 当前时间
		Date today = new Date();
		return getDifferDays(compare_time, DateTostr(today));
	}
	
	
	/**
	 * 判断当前时间是否与compare_time在同一天
	 * 
	 * @param compare_time
	 * @return
	 */
	public static boolean isSameDay(Date compare_time) {
		if (compare_time == null) {
			return false;
		}
		boolean isSameDay = false;
		// 当前时间
		Calendar current_time = Calendar.getInstance();
		int current_year = current_time.get(Calendar.YEAR);
		int current_day_of_year = current_time.get(Calendar.DAY_OF_YEAR);

		Calendar compare_datetime = Calendar.getInstance();
		compare_datetime.setTime(compare_time);
		int compare_year = compare_datetime.get(Calendar.YEAR);
		int compare__day_of_year = compare_datetime.get(Calendar.DAY_OF_YEAR);

		if (current_year == compare_year
				&& current_day_of_year == compare__day_of_year) {
			isSameDay = true;
		}
		return isSameDay;
	}
	
	/**
	 * 判断当前时间是否与compare_time在同一天
	 * 
	 * @param compare_time
	 * @return
	 */
	public static boolean isSameDay(Date compare_time1, Date compare_time2) {
		if (compare_time1 == null || compare_time2 == null) {
			return false;
		}
		boolean isSameDay = false;
		// 当前时间
		Calendar current_time = Calendar.getInstance();
		current_time.setTime(compare_time1);
		int current_year = current_time.get(Calendar.YEAR);
		int current_day_of_year = current_time.get(Calendar.DAY_OF_YEAR);

		Calendar compare_datetime = Calendar.getInstance();
		compare_datetime.setTime(compare_time2);
		int compare_year = compare_datetime.get(Calendar.YEAR);
		int compare__day_of_year = compare_datetime.get(Calendar.DAY_OF_YEAR);

		if (current_year == compare_year
				&& current_day_of_year == compare__day_of_year) {
			isSameDay = true;
		}
		return isSameDay;
	}
	
	public static boolean isSameDay(String compare_time1, String compare_time2) {
		if (compare_time1 == null || compare_time2 == null) {
			return false;
		}
		return isSameDay(strToDate(compare_time1), strToDate(compare_time2));
	}
	
	public static boolean isSameDay(String compare_time)
	{
		if(compare_time==null||"".equals(compare_time))
			return false;
		return isSameDay(strToDate(compare_time));
	}
	
	
	/**
	 * 判断当前时间是否与compare_time在同一天
	 * 
	 * @param compare_time
	 * @return
	 */
	public static boolean isSameWeek(Date compare_time1, Date compare_time2) {
		if (compare_time1 == null || compare_time2 == null) {
			return false;
		}
		boolean isSameDay = false;
		// 当前时间
		Calendar current_time = Calendar.getInstance();
		current_time.setTime(compare_time1);
		int current_year = current_time.get(Calendar.YEAR);
		int current_week_of_year = current_time.get(Calendar.WEEK_OF_YEAR);

		Calendar compare_datetime = Calendar.getInstance();
		compare_datetime.setTime(compare_time2);
		int compare_year = compare_datetime.get(Calendar.YEAR);
		int compare_week_of_year = compare_datetime.get(Calendar.WEEK_OF_YEAR);

		if (current_year == compare_year
				&& current_week_of_year == compare_week_of_year) {
			isSameDay = true;
		}
		return isSameDay;
	}
	
	public static boolean isSameWeek(String compare_time1, String compare_time2) {
		if (compare_time1 == null || compare_time2 == null) {
			return false;
		}
		return isSameWeek(strToDate(compare_time1), strToDate(compare_time2));
	}
	
	public static boolean isSameWeek(String compare_time)
	{
		if(compare_time==null||"".equals(compare_time))
			return false;
		return isSameWeek(strToDate(compare_time), new Date());
	}
	
	/**
	 * 判断当前时间是否与compare_time在同一天
	 * 
	 * @param compare_time
	 * @return
	 */
	public static boolean isSameMonth(Date compare_time1, Date compare_time2) {
		if (compare_time1 == null || compare_time2 == null) {
			return false;
		}
		boolean isSameDay = false;
		// 当前时间
		Calendar current_time = Calendar.getInstance();
		current_time.setTime(compare_time1);
		int current_year = current_time.get(Calendar.YEAR);
		int current_month_of_year = current_time.get(Calendar.MONTH);

		Calendar compare_datetime = Calendar.getInstance();
		compare_datetime.setTime(compare_time2);
		int compare_year = compare_datetime.get(Calendar.YEAR);
		int compare_month_of_year = compare_datetime.get(Calendar.MONTH);

		if (current_year == compare_year
				&& current_month_of_year == compare_month_of_year) {
			isSameDay = true;
		}
		return isSameDay;
	}
	
	public static boolean isSameMonth(String compare_time1, String compare_time2) {
		if (compare_time1 == null || compare_time2 == null) {
			return false;
		}
		return isSameDay(strToDate(compare_time1), strToDate(compare_time2));
	}
	
	public static boolean isSameMonth(String compare_time)
	{
		if(compare_time==null||"".equals(compare_time))
			return false;
		return isSameDay(strToDate(compare_time));
	}
	
	/**
	 * 判断当前时间是否与compare_time在同一小时
	 * 
	 * @param compare_time
	 * @return
	 */
	public static boolean isSameHour(Date compare_time) {
		if (compare_time == null) {
			return false;
		}
		boolean isSameDay = false;
		// 当前时间
		Calendar current_time = Calendar.getInstance();
		int current_year = current_time.get(Calendar.YEAR);
		int current_day_of_year = current_time.get(Calendar.DAY_OF_YEAR);
		int current_hour_of_day = current_time.get(Calendar.HOUR_OF_DAY);

		Calendar compare_datetime = Calendar.getInstance();
		compare_datetime.setTime(compare_time);
		int compare_year = compare_datetime.get(Calendar.YEAR);
		int compare__day_of_year = compare_datetime.get(Calendar.DAY_OF_YEAR);
		int compare__hour_of_day = compare_datetime.get(Calendar.HOUR_OF_DAY);
		
		if (current_year == compare_year
				&& current_day_of_year == compare__day_of_year
				&& current_hour_of_day == compare__hour_of_day){
			isSameDay = true;
		}
		return isSameDay;
	}

	public static boolean isSameHour(String compare_time)
	{
		if(compare_time==null||"".equals(compare_time))
			return false;
		return isSameHour(strToDate(compare_time));
	}
	
	/**
	 * 当前时间与时间compare_time做差值
	 * 
	 * @param compare_time
	 * @param time_difference
	 *            时间
	 * @return 返回差值（分钟）
	 */
	public static int getTimeDifference(Date compare_time, long time_difference) {
		if (compare_time == null) {
			return 0;
		}
		// 当前时间
		Date now_time = new Date();
		long result = time_difference
				- (now_time.getTime() - compare_time.getTime());

		long temp = result % 60000;

		if (temp == 0) {
			return (int) (result / 60000);
		} else {
			return (int) (result / 60000) + 1;
		}

	}

	/**
	 * 得到当请时间字符串
	 * 
	 * @param args
	 */
	public static String getCurrentTimeStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
		return formatter.format(new Date());
	}

	/**
	 * 得到当前时间字符串
	 * 
	 * @param args
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}
	/**
	 * 得到当前时间字符串
	 * 
	 * @param args
	 */
	public static String getCurrentTimeStrs() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(new Date());
	}

	/**
	 * 得到当前时间字符串
	 * 
	 * @param args
	 */
	public static String getCurrentTimeStrsS() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSSSSSSS");
		return formatter.format(new Date());
	}
	
	/**
	 * 得到当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}

	/**
	 * 今天礼拜几
	 * @return
	 */
	public static int getWeekDay()
	{
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int w = c.get(Calendar.DAY_OF_WEEK) - 1;
		return w;
	}
	
	public static String getCurrentMonthDay(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(c.getTime());
	}
	public static String getCurrentWeekDay(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(c.getTime());
	}
	
	//获取给定日期的周开始日期
	public static String getWeekDay(String regdate) {
		Calendar c = Calendar.getInstance();
		c.setTime(strToDate(regdate));
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(c.getTime());
	}
	
	//获取给定日期的月开始日期
	public static String getMonthDay(String regdate) {
		Calendar c = Calendar.getInstance();
		c.setTime(strToDate(regdate));
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(c.getTime());
	}
	
	/**
	 * 返回年月日
	 * 
	 * @return
	 */
	public static String getCurrentTimeNumber() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}

	/**
	 * 得到当前时间的分秒
	 * 
	 * @return
	 */
	public static String getCurrentTimeMMSS() {
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
		return formatter.format(new Date());
	}
	
	/**
	 * 得到当前时分
	 */
	public static String getCurrentTimeHHMM()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(new Date());
	}

	/**
	 * 得到游戏主页面的当请时间字符串
	 * 
	 * @param args
	 */
	public static String getMainPageCurTimeStr() {
		Date cur_date_time = new Date();

		String time_str = "";
		String date_str = "";

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");// 对时间进行格式化

		time_str = formatter.format(cur_date_time);

		formatter.applyPattern("yyyy-MM-dd");// 对日期进行格式化

		date_str = formatter.format(cur_date_time);

		return time_str;// +"<br/>"+date_str;
	}

	/**
	 * 判断当前时间是否是有效时间,同时满足以下条件
	 * 1.判断当前时间是否在time_ks和time_js之间,time_ks和time_js的表示形式为5:18:53
	 * 2.判断当前时间是否在date_ks和date_js之间,date_ks和date_js的表示形式为2008-09-05 05:18:53
	 * 
	 * @param time_begin
	 * @param time_end
	 * @param day_begin
	 * @param day_end
	 * @return
	 */
	public static boolean isEffectTime(String time_begin, String time_end,
			String day_begin, String day_end) {
		boolean result = false;

		if (time_begin == null) {
			time_begin = "";
		}
		if (time_end == null) {
			time_end = "";
		}
		if (day_begin == null) {
			day_begin = "";
		}
		if (day_end == null) {
			day_end = "";
		}

		if (time_begin.equals("") && time_end.equals("")
				&& day_begin.equals("") && day_end.equals("")) {
			// 没有时间限制
			return true;
		} else if (isTimenowBetweenBeginEnd(day_begin, day_end)) {
			// 判断当前时间是否在time_ks和time_js之间,time_ks和time_js的表示形式为5:18:53
			return true;
		} else if (isDatenowBetweenBeginEnd(time_begin, time_end)) {
			// 判断当前时间是否在date_ks和date_js之间,date_ks和date_js的表示形式为2008-9-5 5:18:53
			return true;
		}

		return result;
	}

	/**
	 * 传入分钟返回天数
	 * 
	 * @param time
	 *            分钟
	 * @return
	 */
	public static String returnDay(int times) {
		int time = times * 60;
		int day, hour, minute, second;
		day = time / 86400;
		time = time - 86400 * day;
		hour = time / 3600; // int型整除
		time = time - 3600 * hour;// 除去整小时数后剩下的时间
		minute = time / 60;
		time = time - 60 * minute;
		second = time;
		String dayhint = "";
		if (day > 0) {
			dayhint = day + "天";
		}
		String hourhint = "";
		if (hour > 0) {
			hourhint = hour + "小时";
		}
		String minutehint = "";
		if (minute > 0) {
			minutehint = minute + "分钟";
		}
		String secondhint = "";
		if (second > 0) {
			secondhint = second + "秒";
		}
		return dayhint + hourhint + minutehint + secondhint;
	}

	/**
	 * 传入秒返回天数
	 * 
	 * @param time
	 *            秒
	 * @return
	 */
	public static String returnStr(double times) {
		String result = "";
		int time = (int) times;
		int day, hour, minute, second;
		day = time / 86400;
		time = time - 86400 * day;
		hour = time / 3600; // int型整除
		time = time - 3600 * hour;// 除去整小时数后剩下的时间
		minute = time / 60;
		time = time - 60 * minute;
		second = time;
		String dayhint = "";
		if (day > 0) {
			dayhint = day + "天";
		}
		String hourhint = "";
		if (hour > 0) {
			hourhint = hour + "小时";
		}
		String minutehint = "";
		if (minute > 0) {
			minutehint = minute + "分钟";
		}

		result = dayhint + hourhint + minutehint;

		if (result.equals("") && second != 0) {
			return second + "秒";
		}

		return result;
	}

	/**
	 * 获取到当前时间N分钟后的时间 返回格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return
	 */
	public static String endTime(int time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, time);
		Date d = new Date();
		d.setTime(cal.getTimeInMillis());
		// 创建时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 对时间进行格式化
		String endTime = formatter.format(d);// 从页面得到当前时间,并且赋给一个变量
		return endTime;
	}

	/**
	 * 某个时间 N 分钟后的时间
	 * 
	 * @param q
	 * @param time
	 * @return
	 */
	public static String endsTime(String q, int time) {
		Date c = DateUtil.strToDate(q);
		Calendar cal = Calendar.getInstance();
		cal.setTime(c);
		cal.add(Calendar.MINUTE, time);
		Date d = new Date();
		d.setTime(cal.getTimeInMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 对时间进行格式化
		String endTime = formatter.format(d);// 从页面得到当前时间,并且赋给一个变量
		return endTime;
	}

	/**
	 * 得到当天的前几天或者后几天
	 * 
	 * @param number -
	 *            代表当前天数的前几天数 + 代表当前天数的后几天数
	 * @return
	 */
	public static String getyesterday(int number) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, number);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(cal.getTime());
		return yesterday;
	}

	/**
	 * 得到当天的前几天或者后几天
	 * 
	 * @param number -
	 *            代表当前天数的前几天数 + 代表当前天数的后几天数
	 * @return
	 */
	public static String getyesterdaYYYYYMMDD(int number) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, number);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		return yesterday;
	}
	
	public static String getyesterday_day(int number) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, number);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		return yesterday;
	}

	/**
	 * 得到当天的前几天或者后几天
	 * 
	 * @param number -
	 *            代表当前天数的前几天数 + 代表当前天数的后几天数
	 * @return
	 */
	public static String getyesterdaynumber(int number) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, number);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		return yesterday;
	}

	/**
	 * 得到当前时间的前几个小时或者后几个小时
	 * 
	 * @param number -
	 * @return
	 */
	
	public static String getyesterHOURnumber(int number) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, number);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
		return yesterday;
	}
	

	public static Date getAddTime(int time, int type) {
		Date date = new Date();
		switch (type) {
		case 0:
			date.setSeconds(date.getSeconds() + time);
			break;
		case 1:
			date.setMinutes(date.getMinutes() + time);
			break;
		case 2:
			date.setHours(date.getHours() + time);
			break;
		case 3:
			date.setDate(date.getDate() + time);
			break;
		default:
			date.setSeconds(date.getSeconds() + time);
			break;
		}
		return date;
	}

	public static int S = 0;// 秒
	public static int M = 1;// 分
	public static int H = 2;// 时
	public static int D = 3;// 天

	public static String leftTimeS(long l) {
		Long time = System.currentTimeMillis();
		long left = l - time;
		if (left < 0) {
			return "时间到";
		} else {
			StringBuffer sb = new StringBuffer();
			if (left / 3600000 > 0) {
				sb.append(left / 3600000).append("小时");
			}
			long leftMin = left % 3600000;
			if (leftMin / 60000 > 0) {
				sb.append(leftMin / 60000).append("分钟");
			}
			long leftSec = leftMin % 60000;
			if (leftSec / 1000 > 0) {
				sb.append(leftSec / 1000).append("秒");
			}
			return sb.toString().trim();
		}
	}
	
	public static String leftTimeFromBeginDate(Date beginDate,long totalSecond) {
		Long time = System.currentTimeMillis();
		long dtime=beginDate.getTime();
		long left = totalSecond*1000 - (time - dtime);
		if (left < 0) {
			return "时间到";
		} else {
			StringBuffer sb = new StringBuffer();
			if (left / (24*60*60*1000) > 0) {
				sb.append(left / (24*60*60*1000)).append("天");
			}
			long lefthonour = left % (24*60*60*1000);
			if (lefthonour / 3600000 > 0) {
				sb.append(lefthonour / 3600000).append("小时");
			}
			long leftMin = lefthonour % 3600000;
			if (leftMin / 60000 > 0) {
				sb.append(leftMin / 60000).append("分钟");
			}
			return sb.toString().trim();
		}
	}
	
	//交易用到（this method are very fuck）
	public static String leftTimeFromBeginTime(Date beginDate,long totalSecond) {
		Long time = System.currentTimeMillis();
		long dtime=beginDate.getTime();
		long left = totalSecond*1000 - (time - dtime);
		long temp=left/(1000*60);
		if(temp>=0&&temp<=3)
		{
			return "即将结算";
		}
		else if (left < 0)
		{
			return "交易结束";
		} else {
			StringBuffer sb = new StringBuffer();
			if (left / (24*60*60*1000) > 0) {
				sb.append(left / (24*60*60*1000)).append("天");
			}
			long lefthonour = left % (24*60*60*1000);
			if (lefthonour / 3600000 > 0) {
				sb.append(lefthonour / 3600000).append("小时");
			}
			long leftMin = lefthonour % 3600000;
			if (leftMin / 60000 > 0) {
				sb.append(leftMin / 60000).append("分钟");
			}
			return sb.toString().trim();
		}
	}
    /**
     * 
     *TODO:返回减去指定分钟数的日期..<br>
     *工程名:l<br>
     *包名:com.lottery.utils<br>
     *方法名:subtractMinute方法.<br>
     *
     *@author:jeff
     *@since :1.0:Jan 5, 2011
     *@param date 将运算日期.
     *@param minute 减去的分钟数
     *@return
     */
    public static Date subtractMinute(final Date date, final int minute){    
        try{
            java.util.Calendar calendar=java.util.Calendar.getInstance();   
            calendar.setTime(date);
            int currMinute = java.util.Calendar.MINUTE;
            calendar.set(currMinute, calendar.get(currMinute) - minute);    //让分钟减 minute 数 
    
            return calendar.getTime();
        }catch(Exception ex) {
            return null;
        }
    }
    
    /**
     * 判断时间是否已经开始(时间已到)
     * @param beginTime
     * @return
     */
    public static boolean isBegin(String beginTime)
	{
    	boolean isBegin = false;
    	if(null != beginTime)
    	{
    		Date now = new Date();
    		Date beginDate = DateUtil.strToDate(beginTime);
    		isBegin = beginDate.before(now);//开始时间在当前时间之前
    	}
		return isBegin;
	}
    
    /**
     *  判断时间是否已经开始(时间已到)
     * @param beginTime
     * @return
     */
    public static boolean isBegin(Date beginTime)
	{
    	boolean isBegin = false;
    	if(null != beginTime)
    	{
    		Date now = new Date();
    		return beginTime.before(now);//开始时间在当前时间之前
    	}
		return isBegin;
	}
	
    /**
     * 判断时间是否结束(时间已过)
     * @param endTime
     * @return
     */
	public static boolean isEnd(String endTime)
	{
		boolean isEnd = false;
		if(null != endTime)
		{
			Date now = new Date();
			Date endDate = DateUtil.strToDate(endTime);
			isEnd = endDate.before(now);//结束时间在当前时间之前
		}
		return isEnd;
	}
	
	/**
	 * 判断时间是否结束(时间已过)
	 * @param endTime
	 * @return
	 */
	public static boolean isEnd(Date endTime)
	{
		boolean isEnd = false;
		if(null != endTime){
			Date now = new Date();
			isEnd = endTime.before(now);//结束时间在当前时间之前
		}
		return isEnd;
	}
	/**
	 * 获取当前月的最后一天
	 * @return
	 */
	public static String getLastDayByMonth(){
		Date sDate1 = new Date();
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(sDate1);
		final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = cDay1.getTime();
		lastDate.setDate(lastDay);
		return formatDateToStr(lastDate,"yyyy-MM-dd");
	}
	/**
	 * 获取两个时间段每天日期集合
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getBetweenDay(Date startDate,Date endDate){
		
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		
		List<String> dayList = new ArrayList<String>();
		while (start.compareTo(end) <= 0) {
			dayList.add(DateTostr(start.getTime(), "yyyy-MM-dd"));
			// 循环，每次天数加1
			start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
		}
		return dayList;
	}
	
	//获取相隔天数
	public static int getBettwenDays(String startDateStr, String endDateStr) {

		Date startDate = DateUtil.strToDate(startDateStr);
		Date endDate = DateUtil.strToDate(endDateStr);
		
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		

		int start_year = start.get(Calendar.YEAR);
		int start_day_of_year = start.get(Calendar.DAY_OF_YEAR);


		int end_year = end.get(Calendar.YEAR);
		int end_day_of_year = end.get(Calendar.DAY_OF_YEAR);
		
		return (end_year - start_year) * 365 + end_day_of_year - start_day_of_year;
	}
	
	//获取相隔周数
	public static int getBettwenWeeks(String startDateStr, String endDateStr) {

		Date startDate = DateUtil.strToDate(startDateStr);
		Date endDate = DateUtil.strToDate(endDateStr);
		
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		

		int start_year = start.get(Calendar.YEAR);
		int start_week_of_year = start.get(Calendar.WEEK_OF_YEAR);


		int end_year = end.get(Calendar.YEAR);
		int end_week_of_year = end.get(Calendar.WEEK_OF_YEAR);
		
		return (end_year - start_year) * 53 + end_week_of_year - start_week_of_year;
	}
	
	//获取相隔月数
	public static int getBettwenMonths(String startDateStr, String endDateStr) {

		Date startDate = DateUtil.strToDate(startDateStr);
		Date endDate = DateUtil.strToDate(endDateStr);
		
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		

		int start_year = start.get(Calendar.YEAR);
		int start_month_of_year = start.get(Calendar.MONTH);


		int end_year = end.get(Calendar.YEAR);
		int end_month_of_year = end.get(Calendar.MONTH);
		
		return (end_year - start_year) * 12 + end_month_of_year - start_month_of_year;
	}
	
	/**
	 * 根据日期和需要增加或减少的天数，得到增加或减少的日期字符串
	 * @param destDate
	 * @param number
	 * @return
	 */
	public static String getSubtractday(String dateStr,int number,String pattern) {
        try{
        	Date destDate = strToDate(dateStr);
   		 	java.util.Calendar calendar = java.util.Calendar.getInstance();
			calendar.setTime(destDate);
			calendar.add(Calendar.DATE, number);
			if(null == pattern || "".equals(pattern)){
				pattern = "yyyy-MM-dd HH:mm:ss";
			}
			String yesterday = new SimpleDateFormat(pattern)
					.format(calendar.getTime());
			return yesterday;
		} catch (Exception ex) {
            return "";
        }
	}
	
	public static void main(String[] args) {
		
//		{
//			HttpURLConnection connection = null;
//			try {
//				String line;
//
//					//String orderurl = "http://210.22.155.10:8089/mlh/login.do?comd=login&username=test测试1&password=testpass&nickname=test测试1&sex=1&sign=";
//					String orderurl = "http://210.22.155.10:8089/wns/record.do?";
//					connection = (HttpURLConnection) new URL(orderurl).openConnection();
//					connection.setDoOutput(true);
//					connection.setRequestMethod("POST"); 
//					
//					KeyValue kv = new KeyValue();
//					kv.setKeyValue1(0);
//					String kvstr = JacksonUtil.getJsonString4JavaPOJO(kv);
//					
//					String sourceStr = "4329jh4309823h" + "10001" + kvstr + "()gamekey1";
//					String sign = HashHex.HashToMD5Hex(sourceStr);
//					System.out.println(sourceStr);
//					System.out.println(sign);
//					
//					String data = "comd=saverecord&mtId=4329jh4309823h&gameId=10001&keyValue=" + kvstr + "&recordData=()&sign=" + sign;
//					OutputStream out = connection.getOutputStream();
////					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter (out));
////					writer.append(jsondata);
//					out.write(data.getBytes());
//					out.flush();
//					
//					InputStream in = connection.getInputStream();
//					StringBuilder sb = new StringBuilder();
//					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//
//					while ((line = reader.readLine()) != null)
//						sb.append(line);
//
//					in.close();
//					connection.disconnect();
//					String result = new String(sb);
//
//			} catch (Exception e) {
////				logger.error(String.format("validator token error %s \n %s",
////						new Object[] { e.getMessage(), e.getCause() }));
//			}
//		}
		
//		System.out.println(HashHex.HashToMD5Hex("smspushYangzhi1000213750066095yzkjkey"));
//		System.out.println(HashHex.HashToMD5Hex("abcd4585111"));
		
		String coindata = "%7b%22mThirdcoin%22%3a0%2c%22mSecondcoin%22%3a24%2c%22mBasecoin%22%3a0%7d";
		
		JSONObject coinjson;
		try {
			coinjson = JSONObject.fromObject(URLDecoder.decode(coindata, "utf-8"));
			String mBasecoin = String.valueOf(coinjson.get("mBasecoin"));
			String mSecondcoin = String.valueOf(coinjson.get("mSecondcoin"));
			String mThirdcoin = String.valueOf(coinjson.get("mThirdcoin"));
			
			System.out.println(mBasecoin);
			System.out.println(mSecondcoin);
			System.out.println(mThirdcoin);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 计算一段时间后的值
	 * @param beginTime 开始时间
	 * @param addTime   增加时间  单位分
	 * @return
	 */
	public static String addTime(String beginTime, long addTime)
	{
		Date bDate = strToDate(beginTime);
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(bDate);
		calendar.add(Calendar.MINUTE, (int)addTime);
		return  DateTostr(calendar.getTime());
	}
	
	/**
	 * 计算一段时间后的值
	 * @param beginTime 开始时间
	 * @param addTime   增加时间  单位秒
	 * @return
	 */
	public static String addTime_second(String beginTime, long addTime)
	{
		Date bDate = strToDate(beginTime);
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(bDate);
		calendar.add(Calendar.SECOND, (int)addTime);
		return  DateTostr(calendar.getTime());
	}
	
	/**
	 * 计算一段时间后的值
	 * @param beginTime 开始时间
	 * @param addTime   增加时间  单位秒
	 * @return
	 */
	public static String addTime_second2(String beginTime, long addTime)
	{
		Date bDate = strToTime(beginTime);
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(bDate);
		calendar.add(Calendar.SECOND, (int)addTime);
		return TimeTostr(calendar.getTime());
	}
	
	/**
	 * 计算有多少倍的时间  从开始时间到当前
	 * @param beginTime 开始时间
	 * @param singlTime 基础时间 单位分
	 * @return
	 */
	public static int calMultiTime(String beginTime, long singlTime) {
		if(singlTime <= 0)
			return 0;
		Date bTime = strToDate(beginTime);
		Date eTime = new Date();
		
		long between = (eTime.getTime() - bTime.getTime()) / 60000;
		if(between > 0)
		{
			long mul = between / singlTime;
			return (int)mul;
		}
		return 0;
	}
	
	/**
	 * 计算有多少倍的时间  从开始时间到当前
	 * @param beginTime 开始时间
	 * @param singlTime 基础时间 单位秒
	 * @return
	 */
	public static int calMultiTime_second(String beginTime, long singlTime) {
		if(singlTime <= 0)
			return 0;
		Date bTime = strToDate(beginTime);
		Date eTime = new Date();
		
		long between = (eTime.getTime() - bTime.getTime()) / 1000;
		if(between > 0)
		{
			long mul = between / singlTime;
			return (int)mul;
		}
		return 0;
	}
	
	public static int calMultiTime_second(String beginTime, String endTime,
			int singlTime) {
		if(singlTime <= 0)
			return 0;
		Date bTime = strToDate(beginTime);
		Date eTime = strToDate(endTime);
		
		long between = (eTime.getTime() - bTime.getTime()) / 1000;
		if(between > 0)
		{
			long mul = between / singlTime;
			return (int)mul;
		}
		return 0;
	}
	
	/**
	 * 计算有多少倍的时间  从开始时间到当前
	 * 求余数
	 * @param beginTime 开始时间
	 * @param singlTime 基础时间 单位秒
	 * @return
	 */
	public static int calResidueTime_second(String beginTime, long singlTime) {
		if(singlTime <= 0)
			return 0;
		Date bTime = strToDate(beginTime);
		Date eTime = new Date();
		
		long between = (eTime.getTime() - bTime.getTime()) / 1000;
		if(between > 0)
		{
			long res = between % singlTime;
			return (int)res;
		}
		return 0;
	}
	
	public static String maxTime(String time1, String time2) {
		Date bTime1 = strToDate(time1);
		Date bTime2 = strToDate(time2);
		
		if(bTime1.before(bTime2))
		{
			return time2;
		}
		return time1;
	}
	public static String minTime(String time1, String time2) {
		Date bTime1 = strToDate(time1);
		Date bTime2 = strToDate(time2);
		
		if(bTime1.before(bTime2))
		{
			return time1;
		}
		return time2;
	}
	
//	/**
//	 * 用DES方法加密输入的字节 bytKey需为8字节长，是加密的密码
//	 */
//	private static byte[] encryptByDES(byte[] bytP, byte[] bytKey) throws Exception {
//		DESKeySpec desKS = new DESKeySpec(bytKey);
//		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
//		SecretKey sk = skf.generateSecret(desKS);
//		Cipher cip = Cipher.getInstance("DES");
//		cip.init(Cipher.ENCRYPT_MODE, sk);
//		return cip.doFinal(bytP);
//	}
//
//	/**
//	 * 用DES方法解密输入的字节 bytKey需为8字节长，是解密的密码
//	 */
//	private static byte[] decryptByDES(byte[] bytE, byte[] bytKey) throws Exception {
//		DESKeySpec desKS = new DESKeySpec(bytKey);
//		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
//		SecretKey sk = skf.generateSecret(desKS);
//		Cipher cip = Cipher.getInstance("DES");
//		cip.init(Cipher.DECRYPT_MODE, sk);
//		return cip.doFinal(bytE);
//	}
	
	// MD5加码。32位
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	// 可逆的加密算法
	public static String KL(String inStr) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 加密后解密
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}
	
	public static String getRand8Str(int randint)
	{
		Random rand = RandomUtil.getRand();
		//rand.setSeed(randint);
		String f = "" + rand.nextInt(9);
		String s = "" + rand.nextInt(9);
		int randid =  Math.abs(randint ^ 131801526);
		String randtt = String.valueOf(randid);
		return f + s + randtt;
	}
	
	public static String httptest(String validatorUrl)
	{
		HttpURLConnection connection = null;
		String line;
		String dcode = "gbk";
		try {
			connection = (HttpURLConnection) new URL(validatorUrl).openConnection();
			InputStream in = connection.getInputStream();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, dcode));
	
			//InputStreamReader input = new InputStreamReader(httpConn.getInputStream(), "utf-8");  
			
			while ((line = reader.readLine()) != null)
				sb.append(line);
	
			//String encoding = connection.getContentEncoding();
			//System.out.println("encoding:" + encoding);
			//System.out.println(connection.getContent());
			
			in.close();
			connection.disconnect();
			String result = new String(sb);
			
//			String result1 = new String(result.getBytes("iso8859-1"), dcode);
//			System.out.println(result1);
//			
//			String result2 = new String(result.getBytes("gb2312"), dcode);
//			System.out.println(result2);
			
			String result3 = new String(result.getBytes("gbk"), dcode);
			//System.out.println(result3);
			
			return result3;
//			String result4 = new String(result.getBytes("utf-8"), dcode);
//			System.out.println(result4);
//			
//			String result5 = new String(result.getBytes(), dcode);
//			System.out.println(result5);
			
		} catch (Exception e) {
//			logger.error(String.format("validator token error %s \n %s",
//					new Object[] { e.getMessage(), e.getCause() }));
		}
		return "";
	}
	
	public boolean getBinaryFile(String url) {
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			String contentType = uc.getContentType();
			int contentLength = uc.getContentLength();

			if (contentType.startsWith("text/") || (contentLength == -1)) {
				//log.error("getBinaryFile-->not a binary file");
				return false;
			}

			InputStream raw = uc.getInputStream();
			InputStream in = new BufferedInputStream(raw);
			byte[] data = new byte[contentLength];
			int bytesRead = 0;
			int offset = 0;
			while (offset < contentLength) {
				bytesRead = in.read(data, offset, data.length - offset);
				if (bytesRead == -1)
					break;
				offset += bytesRead;
			}
			in.close();

			if (offset != contentLength) {
//				log.error("getBinaryFile-->Only read " + offset
//						+ " bytes;Expected " + contentLength + " bytes.");
				return false;
			}

			String fileName = u.getFile();
			String filePath = "D://";
			fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
			FileOutputStream fout = new FileOutputStream(filePath + fileName);
			fout.write(data);
			fout.flush();
			fout.close();
		} catch (Exception e) {
			//log.error(e.getMessage());
			return false;
		}
		return true;
	}
}
