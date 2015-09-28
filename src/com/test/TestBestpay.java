package com.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.bill.bestpay.v_api.service.BestpayConstant;
import cn.bill.bestpay.v_api.service.BestpayService;
import cn.game.util.DateUtil;
import cn.game.util.HttpUtils;
import cn.org.util.HashHex;

public class TestBestpay {

	public static void main(String[] args) throws Exception
	{
		String testPhone = "18964396481";
		String money = "1000";
		String TID = DateUtil.getCurrentTimeStrsS();
//		BestpayService f = new BestpayService();
//		//f.getVerifyCode("", "18964084043", 1);
//		f.getVerifyCode(TID, "", testPhone, money);
		
		java.util.Scanner sc=new java.util.Scanner(System.in);
		TID = sc.next();
		System.out.println("TID:"+TID);
		
		String channel_order_id = sc.next();
		System.out.println("channel_order_id:"+channel_order_id);
		
		String RETNCODE = "0000";
		String RETNINFO = "0000";
		String TRANDATE = "20150607";
		String ORDERAMOUNT = "100";
		String signData = "UPTRANSEQ="+channel_order_id+"&TRANDATE="+TRANDATE+"&ORDERSEQ="+TID+"&RETNCODE="+RETNCODE+"&ORDERAMOUNT="+ORDERAMOUNT+"&KEY="+BestpayConstant.DATA_KEY+"&";
		String MAC = HashHex.HashToMD5Hex(signData);
		
		System.out.println("signData:"+signData);
		System.out.println("MAC:"+MAC);
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("ORDERSEQ", TID);
		m.put("ORDERREQTRANSEQ", TID);
		m.put("UPTRANSEQ", channel_order_id);
		m.put("TRANDATE", TRANDATE);
		m.put("ORDERAMOUNT", ORDERAMOUNT);
		m.put("RETNCODE", RETNCODE);
		m.put("RETNINFO", RETNINFO);
		m.put("MAC", MAC);
		
		//发送请求
		try {
			String result = HttpUtils.URLPost(BestpayConstant.BACKMERCHANTURL_TEST, m);
			System.out.println("result:"+result);
			//return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
