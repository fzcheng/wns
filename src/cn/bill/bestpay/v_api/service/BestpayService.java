package cn.bill.bestpay.v_api.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import cn.bill.base.ErrorCode;
import cn.bill.base.dao.BillBlockDAO;
import cn.bill.bestpay.v_api.vo.BestpayRecordVO;
import cn.game.service.ReturnMessage;
import cn.game.util.DateUtil;
import cn.game.util.HttpUtils;
import cn.org.util.HashHex;
import cn.org.util.UUidUtil;

public class BestpayService {
	
	BillBlockDAO billblockdao;
	BestpayRecordService bestpayrecordservice;
	
	public void setBillblockdao(BillBlockDAO billblockdao) {
		this.billblockdao = billblockdao;
	}
	
	public void setBestpayrecordservice(BestpayRecordService bestpayrecordservice) {
		this.bestpayrecordservice = bestpayrecordservice;
	}
	
	
	public void init()
	{
		es.scheduleWithFixedDelay(new DealMissionRunnable(), 30,
				30, TimeUnit.SECONDS);
	}	
	
	private ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

//	static final String VERIFYCODE_URL = "https://webpaywg.bestpay.com.cn/verifyCode.do";
//	static final String BILLPAY_URL = "https://webpaywg.bestpay.com.cn/backBillPay.do";
//	static final String BACKMERCHANTURL = "http://wns.magicbirds.cn/wns/bestpaycb.do";
//	
//	final static String MERCHANTID = "023101400064000";
//	final static String DATA_KEY = "B2861E6B4F51839C57DE429898DA90A2A66FDA16F1EF5A3C";
//	final static String TRADE_KEY = "123456";
	
	/**
	 * 通过手机号码 获取验证码
	*/
	public ReturnMessage requestGetVerifyCode(HttpServletRequest request) {
		ReturnMessage rm = new ReturnMessage();
		
		String phone = (String)request.getParameter("phone");
		String channelid = (String)request.getParameter("channelid");
		String orderid = (String)request.getParameter("orderid");
		String money = (String)request.getParameter("price");//元
		String app_id = (String)request.getParameter("app_id");//应用名
		String fee_code = (String)request.getParameter("fee_code");//计费点名
		String dev_name = (String)request.getParameter("dev_name");//公司名
		String url = (String)request.getParameter("url");//成功通知url
		String extra = (String)request.getParameter("extra");//extra
		
		//需判断是不是电信号码
		if(phone == null || phone.length() != 11)
		{
			rm.setDetail("号码有误");
			rm.setObject(ErrorCode.Error_Phone);
			return rm;
		}
		//TODO 需判断是不是电信号码
		
		//app_id 是否有效 是否开通了此功能
		
		//orderid
		if(orderid == null || orderid.equals(""))
		{
			rm.setDetail("orderid有误");
			rm.setObject(ErrorCode.Error_OrderId);
			return rm;
		}
		
		int moneyI = 0;
		try{
			moneyI = Integer.valueOf(money);
		}
		catch(Exception e)
		{
			rm.setDetail("金额有误");
			rm.setObject(ErrorCode.Error_Money);
			return rm;
		}
		
		int channelidI = 0;
		try{
			channelidI = Integer.valueOf(channelid);
		}
		catch(Exception e)
		{
			rm.setDetail("渠道号有误");
			rm.setObject(ErrorCode.Error_ChannelID);
			return rm;
		}
		
		//1.生成订单
		String TID = DateUtil.getCurrentTimeStrsS();
		BestpayRecordVO record = bestpayrecordservice.createRecord(orderid, phone, moneyI, TID, channelidI, app_id, fee_code, dev_name, url, extra);
		if(record == null)
		{
			rm.setDetail("创建订单失败");
			rm.setObject(ErrorCode.Error_CreateOrder);
			return rm;
		}

		//2.获取验证码请求
		String result = getVerifyCode(TID, phone, money);
		if(result == null)
		{
			rm.setDetail("获取验证码失败");
			rm.setObject(ErrorCode.Error_GetVerifyCode);
			return rm;
		}
		
		if(!result.equals("00"))
		{
			String str[] = result.split("&");
			rm.setDetail(result);
			rm.setObject(ErrorCode.Error_GetVerifyCode);
			
			//更新状态 status resultcode
			record.setStatus(BestpayRecordVO.STATUS_ERROR);
			record.setResultcode(str[1]);
			
			bestpayrecordservice.update(record);
			return rm;
		}
		
		//更新状态 status
		record.setStatus(BestpayRecordVO.STATUS_ING);
		bestpayrecordservice.update(record);
		
		rm.setResult(true);
		rm.setObject(record);
		
		return rm;
	}
	
	/**
	 * @param request
	 * @return
	 */
	public ReturnMessage requestSendVerifyCode(HttpServletRequest request) {
		ReturnMessage rm = new ReturnMessage();
		
		String orderid = (String)request.getParameter("orderid");
		String verifycode = (String)request.getParameter("verifycode");
		
		BestpayRecordVO record = bestpayrecordservice.getRecordByOrderid(orderid);
		if(record == null)
		{
			rm.setDetail("订单不存在");
			rm.setObject(ErrorCode.Error_NoOrder);
			return rm;
		}
		
		String result = sendVerifyCode(verifycode, record);
		if(result == null)
		{
			rm.setDetail("请求失败");
			rm.setObject(ErrorCode.Error_SendVerifyCode);
			return rm;
		}
		
		if(!result.equals("00"))
		{
			String str[] = result.split("&");
			
			rm.setDetail(result);
			rm.setObject(ErrorCode.Error_SendVerifyCode);
			
			//更新状态 status resultcode
			record.setStatus(BestpayRecordVO.STATUS_ERROR);
			record.setResultcode(str[1]);
			
			bestpayrecordservice.update(record);
			
			return rm;
		}
		
		//无需更新状态 status 电信先调用了回调接口再返回的操作结果
		//record.setStatus(BestpayRecordVO.STATUS_SENDCODE);
		//bestpayrecordservice.update(record);
		
		rm.setResult(true);
		rm.setObject(record);
				
		return rm;
	}
	
	/**
	 * 通过手机号码 获取验证码
	 * @param orderId : 下游的订单号
	 * @param phone : 下游上传的手机号码
	 * @param money : 元
	 */
	public String getVerifyCode(String TID, String phone, String money)
	{
		System.out.println("TID:"+TID);
		//1.生成签名
		String ORDERAMOUNT = ""+money;
		String FUNCTIONTYPE = "1";//1：帐单支付
		
		String signStr = "MERCHANTID=" + BestpayConstant.MERCHANTID + "&ORDERSEQ=" + TID + "&ORDERREQTRANSEQ=" + TID + "&TELEPHONE=" + phone + "&KEY=" + BestpayConstant.DATA_KEY;
		System.out.println(signStr);
		String MAC = HashHex.HashToMD5Hex(signStr);
		System.out.println("MAC:"+MAC);
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("FUNCTIONTYPE", FUNCTIONTYPE);
		m.put("MERCHANTID", BestpayConstant.MERCHANTID);
		m.put("ORDERSEQ", TID);
		m.put("ORDERREQTRANSEQ", TID);
		m.put("ORDERAMOUNT", ORDERAMOUNT);
		m.put("TELEPHONE", phone);
		m.put("MAC", MAC);

		//2.发送请求
		try {
			String result = HttpUtils.URLPost(BestpayConstant.VERIFYCODE_URL, m);
			System.out.println("result:"+result);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String sendVerifyCode(String VERIFYCODE, BestpayRecordVO record)
	{
		String TID = record.getTid();
		int money = record.getPrice();
		String ORDERAMOUNT = ""+money;
		String GOODPAYTYPE = "0";
		String PHONENUM = record.getPhone();
		String USERACCOUNT = PHONENUM;
		String GOODSCODE = ""+money;
		String GOODSNAME = ""+Float.valueOf(((float)money)/100)+"元道具";
		String GOODSNUM = "1";
		String ORDERREQTIME = DateUtil.getCurrentTimeStrs();
		String USERIP = "127.0.0.1";
		
		String signStr = "MERCHANTID="+BestpayConstant.MERCHANTID+"&MERCHANTPWD="+BestpayConstant.TRADE_KEY+"&ORDERSEQ="+TID+"&ORDERREQTRANSEQ="+TID+"&ORDERREQTIME="+ORDERREQTIME+"&ORDERAMOUNT="+ORDERAMOUNT+"&USERACCOUNT="
		+USERACCOUNT+"&USERIP="+USERIP+"&PHONENUM="+PHONENUM+"&GOODPAYTYPE="+GOODPAYTYPE+"&GOODSCODE="+GOODSCODE+"&GOODSNUM="+GOODSNUM+"&KEY="+BestpayConstant.DATA_KEY;
		System.out.println(signStr);
		String MAC = HashHex.HashToMD5Hex(signStr);
		System.out.println("MAC:"+MAC);
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("MERCHANTID", BestpayConstant.MERCHANTID);
		//m.put("SUBMERCHANTID", SUBMERCHANTID);
		m.put("MERCHANTPWD", BestpayConstant.TRADE_KEY);
		m.put("MERCHANTPHONE", "4000968662");
		m.put("ORDERSEQ", TID);
		m.put("ORDERREQTRANSEQ", TID);
		m.put("ORDERAMOUNT", ORDERAMOUNT);
		m.put("ORDERREQTIME", ORDERREQTIME);
		m.put("USERACCOUNT", USERACCOUNT);
		m.put("USERIP", USERIP);
		m.put("PHONENUM", PHONENUM);
		m.put("VERIFYCODE", VERIFYCODE);
		m.put("GOODPAYTYPE", GOODPAYTYPE);//1:预付费2:后付费0:不限
		m.put("GOODSCODE", GOODSCODE);//商品编码
		m.put("GOODSNAME", GOODSNAME);//商品名称
		m.put("GOODSNUM", GOODSNUM);//商品数量
		//m.put("ORDERDESC", ORDERDESC);//商户对支付订单的描述
		//m.put("ATTACH", ATTACH);//附加信息
		m.put("BACKMERCHANTURL", BestpayConstant.BACKMERCHANTURL);//商户提供的用于接收交易返回的后台url，用于实际的业务处理
		m.put("MAC", MAC);

		//发送请求
		try {
			String result = HttpUtils.URLPost(BestpayConstant.BILLPAY_URL, m);
			System.out.println("result:"+result);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 定时加载数据线程
	 */
	class DealMissionRunnable implements Runnable {

		@Override
		public void run() {
			try {
				//dealTransferMission();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
//		System.out.println(UUidUtil.getUUID());
//		
//		String testPhone = "18964396481";
//		String money = "1000";
//		String TID = DateUtil.getCurrentTimeStrsS();
//		BestpayService f = new BestpayService();
//		//f.getVerifyCode("", "18964084043", 1);
//		f.getVerifyCode(TID, testPhone, money);
//		
//		java.util.Scanner sc=new java.util.Scanner(System.in);
//		String VERIFYCODE = sc.next();
//		System.out.println("VERIFYCODE:"+VERIFYCODE);
//		
//		TID = sc.next();
//		System.out.println("TID:"+TID);
//		
//		BestpayRecordVO record = new BestpayRecordVO();
//		record.setTid(TID);
//		record.setPhone(testPhone);
//		record.setPrice(Integer.valueOf(money));
//		f.sendVerifyCode(VERIFYCODE, record);

		String signStr = "MERCHANTID=023101400064000&ORDERSEQ=20150625181943219222&ORDERREQTRANSEQ=20150625181943219222&TELEPHONE=18964396481&KEY=B2861E6B4F51839C57DE429898DA90A2A66FDA16F1EF5A3C";
		String MAC = HashHex.HashToMD5Hex(signStr);
		System.out.println("MAC:"+MAC);
		
		System.out.println(DateUtil.getCurrentTimeStrs());
	}
}
