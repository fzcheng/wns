package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestWabpcb {

	public static void main(String[] args) throws Exception
	{
		TestWabpcb t = new TestWabpcb();
		for(int i = 0 ; i < 10000; i ++)
		{
			//String data = "<?xml version=\"1.0\" encoding=\"gbk\"?> <ServiceWebTransfer2APReq><APTransactionID>20150519170137068043</APTransactionID><APId>21648</APId><ServiceId>21754</ServiceId><ServiceType>31</ServiceType><ChannelId>21649</ChannelId><APContentId>703106</APContentId><APUserId>18772407</APUserId><OrderType>0</OrderType><Actiontime>2015-05-19 17:01:53</Actiontime><ServiceAction>0</ServiceAction><method /><signMethod>DSA</signMethod><sign>MCwCFE6effW1Ng5mzqOvvjncOboyAicSAhQ178h8ZeUYmAyQSA7RSTi4hBHjlQ==</sign><Msisdn>25685766562</Msisdn><Province>28</Province><Backup1>11150519170153390926</Backup1><Backup2 /></ServiceWebTransfer2APReq>";
			String data = "<?xml version=\"1.0\" encoding=\"gbk\"?> <VertifyUserState2APReq><APTransactionID>20150519173744530673</APTransactionID><APId>21648</APId><ServiceId>21748</ServiceId><ServiceType>31</ServiceType><ChannelId>21649</ChannelId><APContentId>703106</APContentId><APUserId>11528726</APUserId><OrderType>0</OrderType><Actiontime>2015-05-19 17:38:01</Actiontime><method /><signMethod>DSA</signMethod><sign>MCwCFBDzk6JemIwk0fFXlwab7Tz3EhOSAhQuaATlL/bw6BhKzQqmGzvuLbTHFQ==</sign><Msisdn>28813159981</Msisdn><Province>871</Province><Backup1 /><Backup2 /></VertifyUserState2APReq>\r\n";
			String result = t.sendCommond(data);
			//System.out.println(result);
			Thread.sleep(100);
		}
	}
	
	private String sendCommond(String data) {
		HttpURLConnection connection = null;
		try {
			String line;

				String orderurl = "http://wabp.magicbirds.cn/wabpcb/transfer";
				//String data = "gameId="+gameId+"&data="+cdata+"&key="+encryptkey;
				//String data = "gameId="+gameId+"&key="+URLEncoder.encode(encryptkey,"utf-8");
				//connection = (HttpURLConnection) new URL(orderurl + data).openConnection();
				connection = (HttpURLConnection) new URL(orderurl).openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST"); 
				OutputStream out = connection.getOutputStream();
//				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter (out));
//				writer.append(jsondata);
				out.write(data.getBytes());
				out.flush();
				
				InputStream in = connection.getInputStream();
				StringBuilder sb = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				while ((line = reader.readLine()) != null)
					sb.append(line);

				in.close();
				connection.disconnect();
				String result = new String(sb);
				System.out.println("result:"+result);
				
				return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
