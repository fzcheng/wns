package cn.bill.bestpay.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.bill.base.dao.BillBlockDAO;
import cn.bill.base.dao.BillPhoneDAO;
import cn.game.util.DateUtil;
import cn.game.util.HttpUtils;
import cn.org.util.HashHex;

public class BestpayService {

	BillBlockDAO migublockdao;
	BillPhoneDAO miguphonedao;
	
	public void setMigublockdao(BillBlockDAO migublockdao) {
		this.migublockdao = migublockdao;
	}
	
	public void setMiguphonedao(BillPhoneDAO miguphonedao) {
		this.miguphonedao = miguphonedao;
	}
	
	public void init()
	{
		es.scheduleWithFixedDelay(new DealMissionRunnable(), 30,
				30, TimeUnit.SECONDS);
	}	
	
	private ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

	static final String VERIFYCODE_URL = "https://webpaywg.bestpay.com.cn/verifyCode.do";
	static final String BILLPAY_URL = "https://webpaywg.bestpay.com.cn/backBillPay.do";
	static final String BACKMERCHANTURL = "";
	
	final static String MERCHANTID = "023101400064000";
	final static String DATA_KEY = "B2861E6B4F51839C57DE429898DA90A2A66FDA16F1EF5A3C";
	final static String TRADE_KEY = "123456";
	
	/**
	 * 通过手机号码 获取验证码
	 * @param orderId : 下游的订单号
	 * @param phone : 下游上传的手机号码
	 * @param money : 元
	 */
	public void getVerifyCode(String orderId, String phone, int money)
	{
		//1.生成订单
		String TID = DateUtil.getCurrentTimeStrsS();
		
		//2.生成签名
		String ORDERAMOUNT = ""+money;
		String FUNCTIONTYPE = "1";//1：帐单支付
		
		String signStr = "MERCHANTID=" + MERCHANTID + "&ORDERSEQ=" + TID + "&ORDERREQTRANSEQ=" + TID + "&TELEPHONE=" + phone + "&KEY=" + DATA_KEY;
		System.out.println(signStr);
		String MAC = HashHex.HashToMD5Hex(signStr);
		System.out.println(MAC);
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("FUNCTIONTYPE", FUNCTIONTYPE);
		m.put("MERCHANTID", MERCHANTID);
		m.put("ORDERSEQ", TID);
		m.put("ORDERREQTRANSEQ", TID);
		m.put("ORDERAMOUNT", ORDERAMOUNT);
		m.put("TELEPHONE", phone);
		m.put("MAC", MAC);

		//3.发送请求
		
		try {
			String result = HttpUtils.URLPost(VERIFYCODE_URL, m);
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendVerifyCode(String VERIFYCODE)
	{
		String signStr = "MERCHANTID=987654321&MERCHANTPWD=123456&ORDERSEQ=20060314000001&ORDERREQTRANSEQ=2006031499991&ORDERREQTIME=20060314150908&ORDERAMOUNT=10000&USERACCOUNT=abcdefg&USERIP=127.0.0.1&PHONENUM=13123456789&GOODPAYTYPE=0&GOODSCODE=123456&GOODSNUM=10&KEY=123456";
		System.out.println(signStr);
		String MAC = HashHex.HashToMD5Hex(signStr);
		System.out.println(MAC);
	
		String TID = "";
		int money = 1;
		String ORDERAMOUNT = ""+money;
		String GOODPAYTYPE = "0";
		String PHONENUM = "13818365949";
		String USERACCOUNT = PHONENUM;
		String GOODSCODE = ""+money;
		String GOODSNAME = ""+money+"元道具";
		String GOODSNUM = "1";
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("MERCHANTID", MERCHANTID);
		//m.put("SUBMERCHANTID", SUBMERCHANTID);
		m.put("MERCHANTPWD", TRADE_KEY);
		m.put("MERCHANTPHONE", "4000968662");
		m.put("ORDERSEQ", TID);
		m.put("ORDERREQTRANSEQ", TID);
		m.put("ORDERAMOUNT", ORDERAMOUNT);
		m.put("ORDERREQTIME", DateUtil.getCurrentTimeStrs());
		m.put("USERACCOUNT", USERACCOUNT);
		//m.put("USERIP", USERIP);
		m.put("PHONENUM", PHONENUM);
		m.put("VERIFYCODE", VERIFYCODE);
		m.put("GOODPAYTYPE", GOODPAYTYPE);//1:预付费2:后付费0:不限
		m.put("GOODSCODE", GOODSCODE);//商品编码
		m.put("GOODSNAME", GOODSNAME);//商品名称
		m.put("GOODSNUM", GOODSNUM);//商品数量
		//m.put("ORDERDESC", ORDERDESC);//商户对支付订单的描述
		//m.put("ATTACH", ATTACH);//附加信息
		m.put("BACKMERCHANTURL", BACKMERCHANTURL);//商户提供的用于接收交易返回的后台url，用于实际的业务处理
		m.put("MAC", MAC);

		//发送请求
		try {
			String result = HttpUtils.URLPost(BILLPAY_URL, m);
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//上传验证码
	
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
		BestpayService f = new BestpayService();
		//f.getVerifyCode("", "18964084043", 1);
		f.getVerifyCode("", "18964396481", 1);
	}
}
