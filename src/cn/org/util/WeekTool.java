package cn.org.util;
 
import java.text.DateFormat;  
import java.text.ParsePosition;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Date;  
import java.util.GregorianCalendar;  
  
public class WeekTool {  
    //用来全局控制 上一周，本周，下一周的周数变化  
    private  int weeks = 0;  
    private int MaxDate;//一月最大天数  
    private int MaxYear;//一年最大天数  
      
      
    /** 
      * @param args 
      */  
    public static void main(String[] args) {  
    	WeekTool tt = new WeekTool();  
         System.out.println("获取当天日期:"+tt.getNowTime("yyyy-MM-dd"));  
         System.out.println("获取本周一日期:"+tt.getMondayOFWeek());  
         System.out.println("获取本周日的日期~:"+tt.getCurrentWeekday());  
         System.out.println("获取上周一日期:"+tt.getPreviousWeekday());  
         System.out.println("获取上周日日期:"+tt.getPreviousWeekSunday());  
        
     }  
      
      
    /** 
         * 得到二个日期间的间隔天数 
         */  
    public static String getTwoDay(String sj1, String sj2) {  
         SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
        long day = 0;  
        try {  
          java.util.Date date = myFormatter.parse(sj1);  
          java.util.Date mydate = myFormatter.parse(sj2);  
          day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);  
         } catch (Exception e) {  
         return "";  
         }  
        return day + "";  
     }  
  
  
    /** 
         * 根据一个日期，返回是星期几的字符串 
         * 
         * @param sdate 
         * @return 
         */  
    public static String getWeek(String sdate) {  
        // 再转换为时间  
         Date date = WeekTool.strToDate(sdate);  
         Calendar c = Calendar.getInstance();  
         c.setTime(date);  
        // int hour=c.get(Calendar.DAY_OF_WEEK);  
        // hour中存的就是星期几了，其范围 1~7  
        // 1=星期日 7=星期六，其他类推  
        return new SimpleDateFormat("EEEE").format(c.getTime());  
     }  
  
    /** 
         * 将短时间格式字符串转换为时间 yyyy-MM-dd 
         * 
         * @param strDate 
         * @return 
         */  
    public static Date strToDate(String strDate) {  
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
         ParsePosition pos = new ParsePosition(0);  
         Date strtodate = formatter.parse(strDate, pos);  
        return strtodate;  
     }  
  
    /** 
         * 两个时间之间的天数 
         * 
         * @param date1 
         * @param date2 
         * @return 
         */  
    public static long getDays(String date1, String date2) {  
        if (date1 == null || date1.equals(""))  
         return 0;  
        if (date2 == null || date2.equals(""))  
         return 0;  
        // 转换为标准时间  
         SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
         java.util.Date date = null;  
         java.util.Date mydate = null;  
        try {  
          date = myFormatter.parse(date1);  
          mydate = myFormatter.parse(date2);  
         } catch (Exception e) {  
         }  
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);  
        return day;  
     }  
  
  
  
      
    // 计算当月最后一天,返回字符串  
    public String getDefaultDay(){    
        String str = "";  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");      
  
        Calendar lastDate = Calendar.getInstance();  
        lastDate.set(Calendar.DATE,1);//设为当前月的1号  
        lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号  
        lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天  
         
        str=sdf.format(lastDate.getTime());  
       return str;    
     }  
      
    // 上月第一天  
    public String getPreviousMonthFirst(){    
        String str = "";  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");      
  
        Calendar lastDate = Calendar.getInstance();  
        lastDate.set(Calendar.DATE,1);//设为当前月的1号  
        lastDate.add(Calendar.MONTH,-1);//减一个月，变为下月的1号  
       //lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天  
         
        str=sdf.format(lastDate.getTime());  
       return str;    
     }  
      
    //获取当月第一天  
    public String getFirstDayOfMonth(){    
        String str = "";  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");      
  
        Calendar lastDate = Calendar.getInstance();  
        lastDate.set(Calendar.DATE,1);//设为当前月的1号  
        str=sdf.format(lastDate.getTime());  
       return str;    
     }  
      
    // 获得本周星期日的日期    
    public String getCurrentWeekday() {  
         weeks = 0;  
        int mondayPlus = this.getMondayPlus();  
         GregorianCalendar currentDate = new GregorianCalendar();  
         currentDate.add(GregorianCalendar.DATE, mondayPlus+6);  
         Date monday = currentDate.getTime();  
          
         DateFormat df = DateFormat.getDateInstance();  
         String preMonday = df.format(monday);  
        return preMonday;  
     }  
      
      
    //获取当天时间   
    public String getNowTime(String dateformat){  
         Date    now    =   new    Date();     
         SimpleDateFormat    dateFormat    =   new    SimpleDateFormat(dateformat);//可以方便地修改日期格式     
         String   hehe   = dateFormat.format(now);     
        return hehe;  
     }  
      
    // 获得当前日期与本周日相差的天数  
    private int getMondayPlus() {  
         Calendar cd = Calendar.getInstance();  
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......  
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-1;         //因为按中国礼拜一作为第一天所以这里减1  
        if (dayOfWeek == 1) {  
            return 0;  
         } else {  
            return 1 - dayOfWeek;  
         }  
     }  
      
    //获得本周一的日期  
    public String getMondayOFWeek(){  
          weeks = 0;  
         int mondayPlus = this.getMondayPlus();  
          GregorianCalendar currentDate = new GregorianCalendar();  
          currentDate.add(GregorianCalendar.DATE, mondayPlus);  
          Date monday = currentDate.getTime();  
           
          DateFormat df = DateFormat.getDateInstance();  
          String preMonday = df.format(monday);  
         return preMonday;  
     }  
      
  //获得相应周的周六的日期  
    public String getSaturday() {  
        int mondayPlus = this.getMondayPlus();  
         GregorianCalendar currentDate = new GregorianCalendar();  
         currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);  
         Date monday = currentDate.getTime();  
         DateFormat df = DateFormat.getDateInstance();  
         String preMonday = df.format(monday);  
        return preMonday;  
     }  
      
// 获得上周星期日的日期  
    public   String getPreviousWeekSunday() {  
         weeks=0;  
         weeks--;  
        int mondayPlus = this.getMondayPlus();  
         GregorianCalendar currentDate = new GregorianCalendar();  
         currentDate.add(GregorianCalendar.DATE, mondayPlus+weeks);  
         Date monday = currentDate.getTime();  
         DateFormat df = DateFormat.getDateInstance();  
         String preMonday = df.format(monday);  
        return preMonday;  
     }  
  
// 获得上周星期一的日期  
    public String getPreviousWeekday() {  
         weeks--;  
        int mondayPlus = this.getMondayPlus();  
         GregorianCalendar currentDate = new GregorianCalendar();  
         currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);  
         Date monday = currentDate.getTime();  
         DateFormat df = DateFormat.getDateInstance();  
         String preMonday = df.format(monday);  
        return preMonday;  
     } 

}