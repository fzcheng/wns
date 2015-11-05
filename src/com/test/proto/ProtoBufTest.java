package com.test.proto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import awo.common.model.obj.AnalyDataFormat;
import awo.common.model.obj.AnalyDataFormat.FirstTestBack;

import com.test.HttpRequester;

public class ProtoBufTest {
	
	public final static String URL = "http://192.168.1.136:8080/wns/master.do";
	
	public static void main(String[] args) throws Exception {  
        // 读取已经存在.book文件  
//        AddressBook addressBook = AddressBook.parseFrom(new FileInputStream("src/Book/TestPerson.book"));  
//        Print(addressBook);  
		
		//AnalyDataFormat.FirstTest.Builder firsttest = AnalyDataFormat.FirstTest.newBuilder().setTestInt1(1);
		
		AnalyDataFormat.FirstTest.Builder firsttest = AnalyDataFormat.FirstTest.newBuilder().
				setTestInt1(321).
				setTestStr2("this is 魔力小鸟。 1028").
				setTestStr4("eggeggfwesfs");
		System.out.print("firsttest:" + firsttest);
		
		System.out.print("f :"+firsttest.toString());
		System.out.print("f :"+firsttest.build().toString());
		//firsttest.build().writeTo(output);
		
		httpPost(URL, 1, firsttest.build().toByteArray());
		
		
		//AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder().build();
		//AddressBookProtos.AddressBook.Builder address = AddressBookProtos.AddressBook.newBuilder().setPerson(1, person);
		//httpPost(URL, 1, address.build().toByteArray());
    }  
	
	public static String httpPost(String strUrl, int pcode, byte[] p) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/octet-stream");
			
			con.setRequestProperty("cmd", "FirstTest");
			
			OutputStream bout = con.getOutputStream();
			//bout.write("1231231231321321321321321312321313129".getBytes());
			bout.write(p);
			bout.flush();
			bout.close();
			BufferedReader bin = new BufferedReader(new InputStreamReader(con
					.getInputStream()));
			String line = bin.readLine();
			bin.close();
			//System.out.println(line);
			
			String sign = con.getHeaderField("sign");
			String sign2 = con.getHeaderField("sign2");
			String user = con.getHeaderField("user");
			String cmd = con.getHeaderField("cmd");
			String servertime = con.getHeaderField("servertime");
			
//			String sign = con.getRequestProperty("sign");
//			String sign2 = con.getRequestProperty("sign2");
//			String user = con.getRequestProperty("user");
//			String cmd = con.getRequestProperty("cmd");
//			String servertime = con.getRequestProperty("servertime");
			
			FirstTestBack.Builder first = FirstTestBack.newBuilder();
			
			first.mergeFrom(line.getBytes());
			return line;
		}
		catch(Exception e)
		{
			
		}
		
		return "";
	}
	
	public static String httpPost2(String strUrl, int pcode, byte[] p) {
		HttpRequester req = new HttpRequester();
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "1");
		map.put("2", "2");
		try {
			req.sendPost(strUrl, map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strUrl;
	}
}
