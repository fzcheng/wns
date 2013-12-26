package cn.org.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;



public class DateFormatTool {

	private static DateFormatTool instance = null; 
 
	 public static synchronized DateFormatTool getDateFormatTool() { 
		  if(instance==null){
			  
			  instance=new DateFormatTool();
		  }
	        return instance; 
	    } 
	
	 /**
     * 某一个月第一天和最后一天
     * @param date
     * @return
     */
    public static Map<String, String> getFirstday_Lastday_Month(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();
        
        //上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        day_first = str.toString();

        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
        day_last = endStr.toString();

        Map<String, String> map = new HashMap<String, String>();
        map.put("first", day_first);
        map.put("last", day_last);
        return map;
    }
    
    
    /**
     * 当月第一天
     * @return
     */
   private static String getFirstDay() {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       Calendar calendar = Calendar.getInstance();
       Date theDate = calendar.getTime();
       
       GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
       gcLast.setTime(theDate);
       gcLast.set(Calendar.DAY_OF_MONTH, 1);
       String day_first = df.format(gcLast.getTime());
       StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
       return str.toString();

   }

    /**
     * 当月最后一天
     * @return
     */
    private static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();

    }
	public static String getBeforeDay(String str,int num,Date date){
		 
		 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
		//制定日期格式
		Calendar sc=Calendar.getInstance();
		sc.setTime(date);
		if("-".equals(str))
		sc.add(Calendar.DATE,(num-(2*num)));
		else
		sc.add(Calendar.DATE,num);	
		//将当前日期加一个月
		return df.format(sc.getTime()); 
	}
	
	
 public static boolean   compareDate(String date1,String date2){
	 boolean b=false;
	 java.util.Calendar c1=java.util.Calendar.getInstance();
	 java.util.Calendar c2=java.util.Calendar.getInstance();

	 
	 return b;
 }
 java.util.Calendar c1=java.util.Calendar.getInstance();

	public static Date  StringToDate(String strdate){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dates = null;
		try {
			dates = sdf.parse(strdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dates;
		
	}
   

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String day=getBeforeDay("-",1,new Date());
		Map map=getFirstday_Lastday_Month(new Date());
		System.out.println("前1天的日期："+day);
		System.out.println("第一天："+map.get("first"));
		System.out.println("最后一天："+map.get("last"));
	}
}
