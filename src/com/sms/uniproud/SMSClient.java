package com.sms.uniproud;



/**
 * 普通发送无收件人信息
 * GuestCompany uniproud
 * author shqv
 */

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import java.util.*;
import java.sql.*;

public class SMSClient {//64.187.11.116
//    private static EndpointReference targetEPR = new EndpointReference("http://202.100.80.24:8081/services/BnetAuthentication");
    private OMElement result = null;
    private static EndpointReference targetEPR = null;
    public SMSClient(String addr)
    {
         targetEPR = new EndpointReference("http://"+addr+"/servlet/services/SmsService");
    }
    public static OMElement getEchoOMElement(String xml)
    {
        //固定格式
        OMFactory fac = OMAbstractFactory.getOMFactory();
        //第一个参数为固定格式不可以更改
//        OMNamespace omNs = fac.createOMNamespace("bnetesb", "BnetAuthentication");//
        OMNamespace omNs = fac.createOMNamespace("http://axis2.fax.uniproud.com", "SMSToServer");//
        OMElement method = fac.createOMElement("SMSToServer", omNs);
        OMElement value = fac.createOMElement("Text", omNs);
        value.addChild(fac.createOMText(value, xml));
        method.addChild(value);
//        System.out.println("method=="+method);
        return method;
    }

    org.apache.axis2.client.async.Callback callback = new org.apache.axis2.client.async.Callback()
    {
        public void onComplete(org.apache.axis2.client.async.AsyncResult asyncResult) {
          result = asyncResult.getResponseEnvelope();
        }
        public void onError(Exception e)
        {
            e.printStackTrace();
        }
    };
    //异步调用
    public String Asynchronous(String xmlStr,String methodName){
      String fileXml = "";
      ServiceClient sender = null;
      try {
        OMElement payload = getEchoOMElement(xmlStr);
        Options options = new Options();
        options.setTo(targetEPR);
        options.setAction(methodName); //访问的方法名

        options.setTimeOutInMilliSeconds(60000); //设置超时时间
        //Blocking invocation
        sender = new ServiceClient();
        sender.setOptions(options);
        sender.sendReceiveNonBlocking(payload, callback);
        while (!callback.isComplete()) {
          Thread.sleep(1000);
        }
        OMElement _fileName = null; //文件名
//        System.out.println("打印结果" + result);
        for (Iterator _iterator = result.getChildElements(); _iterator.hasNext(); ) {
          OMElement _ele = (OMElement) _iterator.next();
          _ele = _ele.getFirstElement().getFirstElement();
          if (_ele.getLocalName().equalsIgnoreCase("return")) {
            _fileName = _ele;
            fileXml = _fileName.getText();
          }
        }
      }catch (AxisFault axisFault) {
        axisFault.printStackTrace();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
      finally {
        try {
          sender.cleanup();
        }
        catch (AxisFault axisFault) {
          //have to ignore this
        }
      }
      return fileXml;
    }
    /**
     * @param xml
     * @param methodName
     * @return
     */
    public String SendReceive(String xmlStr,String methodName){
         String fileXml = "";
         ServiceClient sender = null;
        try {
          OMElement payload = getEchoOMElement(xmlStr);
          Options options = new Options();
          options.setTo(targetEPR);
          options.setAction(methodName);//访问的方法名

          options.setTimeOutInMilliSeconds(120000); //设置超时时间
          //Blocking invocation
          sender = new ServiceClient();
          sender.setOptions(options);

        /**
         * Callback to handle the response
         * 异步调用 begin
          Callback callback = new Callback() {
              public void onComplete(AsyncResult result) {
                  System.out.println(result.getResponseEnvelope());
              }

              public void onError(Exception e) {
                  e.printStackTrace();
              }
          };
          sender.sendReceiveNonBlocking(payload, callback);
          while (!callback.isComplete()) {
            Thread.sleep(1000);
          }
          **///end
         
         result = sender.sendReceive(payload);//开始传递XML
          OMElement _fileName = null;//文件名
//          System.out.println("打印结果"+result);
//          System.out.println("同步调用:"+result);
          for (Iterator _iterator = result.getChildElements(); _iterator.hasNext();) {
                   OMElement _ele = (OMElement) _iterator.next();

            if (_ele.getLocalName().equalsIgnoreCase("return")) {
                   _fileName = _ele;
            }
            fileXml = _fileName.getText();
          }
        }catch(AxisFault axisFault){
        	axisFault.getMessage();
        }catch(Exception ex){
          ex.printStackTrace();
        }finally {
            try {
                sender.cleanup();
            } catch (AxisFault axisFault) {
                //have to ignore this
            }
        }
        return fileXml;
      }


}

