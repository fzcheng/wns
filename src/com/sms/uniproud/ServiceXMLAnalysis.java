package com.sms.uniproud;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;


public class ServiceXMLAnalysis {
  public static Namespace ns = null;
  /**
   * 用来解析XML的类
   * @param xmlDoc
   * @return Element
   */
  public static Element xmlElements(String xmlDoc) 
  {
    Element root = null;
    try 
    {
      //创建一个新的字符串
      StringReader read = new StringReader(xmlDoc);
      //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
      InputSource source = new InputSource(read);
      //创建一个新的SAXBuilder
      SAXBuilder saxBuilder = new SAXBuilder();

      //通过输入源构造一个Document
      Document doc = saxBuilder.build(source);
      //取的根元素
      root = doc.getRootElement();
//      System.out.println(root.getName()); //输出根元素的名称（测试）
      ns = root.getNamespace();
      //得到根元素所有子元素的集合
      //System.out.println(root);
    }
    catch (JDOMException e)
    {
      e.printStackTrace();
    }
    catch (IOException e) 
    {
      e.printStackTrace();
    }
    return root;
  }

  //得到
  public static int getErrorFlag(Element bodyElent) 
  {
    if (bodyElent == null) 
    {
      return -10;
    }
    String errorFlag = "";
    String returnMessage = "";
    Element headerElent = bodyElent.getChild("Header"); //得到Header节点
    // get ErrorFlag
    errorFlag = headerElent.getChildText("ErrorFlag");
    errorFlag = errorFlag == null ? "" : errorFlag.trim();
        // get ReturnMessage
    returnMessage = headerElent.getChildText("ReturnMessage");
    returnMessage = returnMessage == null ? "" : returnMessage.trim();
    System.out.println("调用是否成功:" + errorFlag);
    System.out.println("调用结果信息:" + returnMessage);
    return Integer.parseInt(errorFlag);
  }

//得到XML反馈的XML的BODY中的Element
  public static Element getBody(String xmlStr) 
  {
    if (xmlStr == null || xmlStr.equals(""))
      return null;
    Element bodyElent = null;
    try 
    {
      Element uniproudSoap = null;
      uniproudSoap = xmlElements(xmlStr); //开始解析xml字符串
      bodyElent = uniproudSoap.getChild("Body", ns); //得到body 节点
    }
    catch (Exception ex) 
    {
      ex.printStackTrace();
    }
    return bodyElent;
  }
  public static int getSendSmsToServerBack(String xmlStr) 
  {
	  if(xmlStr == null || xmlStr.equals(""))
	      return -1;
	    try 
	    {
	      Element bodyElent = getBody(xmlStr);
	      int errorFlag = getErrorFlag(bodyElent);
	      //errorFlag 标识返回成功，其他表示失败，不需要继续往下解析
	      if (errorFlag != 0)
	        return errorFlag;
	      Element et = bodyElent.getChild("SendSmsToServerResponse").getChild("SendSmsToServerResult");
	      System.out.println("作业号:" + et.getChildText("JobNo"));      
	      System.out.println("递交结果:" + et.getChildText("JobResult"));
	      System.out.println("递交总份数:" + et.getChildText("TotalNum"));
	      System.out.println("合格份数:" + et.getChildText("ValidNum"));
	    
	    }
	    catch (Exception ex) 
	    {
	      ex.printStackTrace();
	      return -2;
	    }
	    
	    return 0;
  }
  //解析调用获取短信发送结果接口，反馈的XML信息
  public static void getQueryResultForSmsTaskBack(String xmlStr) 
  {
	  if(xmlStr == null || xmlStr.equals(""))
	      return;
	    try 
	    {
	      Element bodyElent = getBody(xmlStr);
	      int errorFlag = getErrorFlag(bodyElent);
	      //errorFlag 标识返回成功，其他表示失败，不需要继续往下解析
	      if (errorFlag != 0)
	        return;
	      
	      List qrstList = bodyElent.getChild("QueryResultForSendTaskResponse").getChild("QueryResultForSendTaskResult").getChild("SendSmsResultList").getChildren("SendSmsResult");
	      if(qrstList.size()>0)
	      {
	    	  Element et = null;
	    	  for (int i = 0; i < qrstList.size(); i++) 
	    	  {
	    		  et = (Element) qrstList.get(i);
	    		  System.out.println("===================================="+i+"==================================");
	    		  //用户发送时传递的ClientTaskID
	    		  System.out.println("客户端任务ID:" + Integer.parseInt(et.getChildText("ClientTaskID")));
	    	      
	    	      System.out.println("段数:" + et.getChildText("NumberOfPages"));
	    	      System.out.println("费用:" + et.getChildText("BillingFee"));
	    	      System.out.println("发送结果:" + et.getChildText("result"));
	    	  }
	      }
	      else
	      {
	    	  System.out.println("没有需要获取的短信发送结果！");
	      }
	    }
	    catch (Exception ex) 
	    {
	      ex.printStackTrace();
	    }
	  
  }
  //解析调用短信接收接口，反馈的XML信息
  public static void getRecvSMSBack(String xmlStr) 
  {
    if(xmlStr == null || xmlStr.equals(""))
      return;
    
    try 
    {
      Element bodyElent = getBody(xmlStr);
      int errorFlag = getErrorFlag(bodyElent);
      //errorFlag 标识返回成功，其他表示失败，不需要继续往下解析
      if (errorFlag != 0)
        return;
      
      List recvsmList = bodyElent.getChild("RecvSmResultList").getChildren("RecvSmsResult");
      if(recvsmList.size()>0)
      {
    	  Element et = null;
    	  for (int i = 0; i < recvsmList.size(); i++) 
    	  {
    		  et = (Element) recvsmList.get(i);
    		  System.out.println("===================================="+i+"==================================");
    	      System.out.println("用户帐号:" + et.getChildText("UserId"));
    	      System.out.println("主叫号码:" + et.getChildText("CallingNumber"));
    	      System.out.println("接收时间:" + et.getChildText("StartTime"));
    	      System.out.println("短信内容:" + et.getChildText("RecvInfo"));
              //ClientTaskID和ServerTaskID 备用短信回复功能 ，不需要的客户可以不需要关心这两个字段  
    	      System.out.println("客户端任务ID:" + Integer.parseInt(et.getChildText("ClientTaskID")));
    	      System.out.println("服务端任务ID:" + Integer.parseInt(et.getChildText("ServerTaskID")));
    	  }
      }
      else
      {
    	  System.out.println("没有新接收到的短信！");
      }
    }
    catch (Exception ex) 
    {
      ex.printStackTrace();
    }
  }

}