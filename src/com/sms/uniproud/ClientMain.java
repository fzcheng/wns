package com.sms.uniproud;

import com.sms.IPushSms;
import com.sms.uniproud.SMSClient;

public class ClientMain implements IPushSms{

	final static String IP = "222.73.36.203";
	final static String USER = "38051884";
	final static String PASS = "630368";
	
	public static ClientMain self;
	
	public static ClientMain getInstance() {
		if(self == null)
			self = new ClientMain();
		return self;
	}
	
	public int sendSms(String telnum, String content)
	{
		System.out.println(System.getProperty("http.nonProxyHosts"));

		System.setProperty("http.nonProxyHosts", "169.254/16");
		
		SMSClient client = new SMSClient(IP);
		// 发送传真接口
		String sendSmsToBack = client.SendReceive(ToServerXML.getSendSmsXML(
				USER, PASS, telnum, content), "urn:SendSmsToServer"); //
		return ServiceXMLAnalysis.getSendSmsToServerBack(sendSmsToBack);
	}
	
	public static void main(String[] args) {
		SMSClient client = new SMSClient(IP);

		System.out.println(System.getProperty("http.nonProxyHosts"));

		System.setProperty("http.nonProxyHosts", "169.254/16");
		// 发送传真接口
		String sendSmsToBack = client.SendReceive(ToServerXML.getSendSmsXML(
				USER, PASS), "urn:SendSmsToServer"); //
		ServiceXMLAnalysis.getSendSmsToServerBack(sendSmsToBack);

		// 查询发送清单接口
		// String queryForSmsBack =
		// client.SendReceive(ToServerXML.getQueryResultForSmsTaskXML("帐号",
		// "密码"),"urn:QueryResultForSmsTask"); //
		// ServiceXMLAnalysis.getQueryResultForSmsTaskBack(queryForSmsBack);

		// 短信接收接口
		// String recvSmsBack =
		// client.SendReceive(ToServerXML.getRecvSmsResultXML("帐号",
		// "密码"),"urn:QueryRecvSmsTask"); //
		// ServiceXMLAnalysis.getRecvSMSBack(recvSmsBack);
	}

}