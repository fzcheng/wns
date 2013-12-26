package com.sms.uniproud;

/**
 * 生成测试用的客户端XML字符串
 * GuestCompany uniproud
 * author shqv
 */
import java.io.*;
public class ToServerXML {
//
//
    public static String getSendSmsXML(String uid,String pwd)
    {
      String xml =
          "<?xml version=\"1.0\" encoding=\"gb2312\" ?>" +
          "<SmsInfo>" +
          "<Login>" +
          "<UserID>"+uid+"</UserID>" +//用户名
          "<Password>"+pwd+"</Password>" +//密码
          "</Login>" +
          "<SendTaskList>" +//
          "<TotalNum>3</TotalNum> " + //传真个数.最大值1000.如果个数太多,请分开传输.
          "<SendTask>" +//
          "<ClientTaskID>1</ClientTaskID>" +//客户端TaskID,标志唯一一条传真记录.用于查询发送结果
          "<SmsNumber>13818365949</SmsNumber>" +//传真号码.
          "</SendTask>" +
//          "<SendTask>" +//
//          "<ClientTaskID>2</ClientTaskID>" +//客户端TaskID,标志唯一一条传真记录.用于查询发送结果
//          "<SmsNumber>15936168606</SmsNumber>" +//传真号码.
//          "</SendTask>" +
          "</SendTaskList>" +
          "<SmsOptions>"+
          "<ExpectTime>2013-11-04 09:59:21</ExpectTime>"+//期望发送时间
	    "<Priority>0</Priority>"+
          "<Content>航空公司航班调整</Content>"+
          "</SmsOptions>"+ 
          "</SmsInfo>"; 

      return xml;
    }
    
    public static String getSendSmsXML(String user, String pass, String telnum, String content) {
    	String xml =
            "<?xml version=\"1.0\" encoding=\"gb2312\" ?>" +
            "<SmsInfo>" +
            "<Login>" +
            "<UserID>"+user+"</UserID>" +//用户名
            "<Password>"+pass+"</Password>" +//密码
            "</Login>" +
            "<SendTaskList>" +//
            "<TotalNum>1</TotalNum> " + //传真个数.最大值1000.如果个数太多,请分开传输.
            "<SendTask>" +//
            "<ClientTaskID>1</ClientTaskID>" +//客户端TaskID,标志唯一一条传真记录.用于查询发送结果
            "<SmsNumber>"+telnum+"</SmsNumber>" +//传真号码.
            "</SendTask>" +
//            "<SendTask>" +//
//            "<ClientTaskID>2</ClientTaskID>" +//客户端TaskID,标志唯一一条传真记录.用于查询发送结果
//            "<SmsNumber>15936168606</SmsNumber>" +//传真号码.
//            "</SendTask>" +
            "</SendTaskList>" +
            "<SmsOptions>"+
            "<ExpectTime>2013-11-04 09:59:21</ExpectTime>"+//期望发送时间
            "<Priority>0</Priority>"+
            "<Content>"+content+"</Content>"+
            "</SmsOptions>"+ 
            "</SmsInfo>"; 

        return xml;
	}
    
    public static String getQueryResultForSmsTaskXML(String uid,String pwd)
    {//
    	StringBuffer xmlsb = new StringBuffer();
    	xmlsb.append("<?xml version=\"1.0\" encoding=\"gb2312\" ?>");
    	xmlsb.append("<SmsInfo>");
    	xmlsb.append("<QueryResultForSmsTask>");
    	xmlsb.append("<Login>");
    	xmlsb.append("<UserID>").append(uid).append("</UserID>");
    	xmlsb.append("<Password>").append(pwd).append("</Password>");
    	xmlsb.append("</Login>");
    	xmlsb.append("</QueryResultForSmsTask>");
    	xmlsb.append("</SmsInfo>");
    	return xmlsb.toString();
    }
    
    
    public static String getRecvSmsResultXML(String uid,String pwd)
    {
    	StringBuffer xmlsb = new StringBuffer();
    	xmlsb.append("<?xml version=\"1.0\" encoding=\"gb2312\" ?>");
    	xmlsb.append("<SmsInfo>");
    	xmlsb.append("<QueryRecvSmsTask>");
    	xmlsb.append("<Login>");
    	xmlsb.append("<UserID>").append(uid).append("</UserID>");
    	xmlsb.append("<Password>").append(pwd).append("</Password>");
    	xmlsb.append("</Login>");
    	//查询未获取的短息 条件可以我为空
    	xmlsb.append("<StartTimeFilter>").append("2001-01-01 01:01:00").append("</StartTimeFilter>");
    	xmlsb.append("<EndTimeFilter>").append("2011-03-02 15:01:00").append("</EndTimeFilter>");
    	xmlsb.append("</QueryRecvSmsTask>");
    	xmlsb.append("</SmsInfo>");
    	return xmlsb.toString();
    }
}