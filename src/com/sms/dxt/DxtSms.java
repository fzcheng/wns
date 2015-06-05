package com.sms.dxt;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.game.pay.mmarket.SyncXMLUtils;
import cn.game.service.DataService;
import cn.game.vo.sms.SmsChannelVO;
import cn.org.util.SpringUtils;

import com.sms.IPushSms;

public class DxtSms implements IPushSms {

	public static String url = "http://www.guanggaoziyuan.com:8802/sms.aspx";
//	public static String userid = "30942";
//	public static String account = "1225";
//	public static String pwd = "123456aa";
	
	@Override
	public int sendSms(String telnum, String content) {
	
		DataService dataservice = (DataService)SpringUtils.getBean("dataservice");
		
		SmsChannelVO smschannel = dataservice.getSmsChannelById("dxt");
		if(smschannel == null)
		{
			return -99;
		}
		
		String userid = smschannel.getUserid();
		String account = smschannel.getAccount();
		String pwd = smschannel.getPwd();
		
		HttpURLConnection connection = null;
		try {
			String line;

				//String orderurl = "http://210.22.155.10:8089/mlh/login.do?comd=login&username=test测试1&password=testpass&nickname=test测试1&sex=1&sign=";
				String orderurl = url;
				connection = (HttpURLConnection) new URL(orderurl).openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST"); 
				
				String data = "action=send&userid="+ userid
						+ "&account=" + account 
						+ "&password=" +pwd
						+ "&mobile=" + telnum
						+ "&content=" + content
						+ "&sendTime=&extno=";
				
				System.out.println("send data:" + telnum + "-" + content);
				OutputStream out = connection.getOutputStream();
//				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter (out));
//				writer.append(jsondata);
				out.write(data.getBytes("utf-8"));
				out.flush();
				
				InputStream in = connection.getInputStream();
				StringBuilder sb = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

				while ((line = reader.readLine()) != null)
					sb.append(line);

				in.close();
				connection.disconnect();
				String result = new String(sb);
				System.out.println(result);
				
				//解析xml数据
				SyncXMLUtils utils = new SyncXMLUtils();
				//DxtResultVO r = (DxtResultVO)utils.xml2Vo(new String(result.getBytes(), "utf-8"), "returnsms", DxtResultVO.class.getName());
				DxtResultVO r = (DxtResultVO)utils.xml2Vo(result, "returnsms", DxtResultVO.class.getName());
				System.out.println("message:" + r.getMessage());
				
				if(r.getReturnstatus().equals("Success"))
					return 0;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public static void main(String[] args) {
		DxtSms client = new DxtSms();

		client.sendSms("13818365949", "欢迎下载桃色恋人2：http://apk.mmarket.com/myheartmm1113.apk");
	}
}
