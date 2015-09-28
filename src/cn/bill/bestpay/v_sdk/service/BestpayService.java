package cn.bill.bestpay.v_sdk.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import cn.bill.base.BillDataService;
import cn.bill.base.ErrorCode;
import cn.bill.base.dao.BillBlockDAO;
import cn.bill.base.vo.BillGoodsVO;
import cn.bill.bestpay.v_api.service.BestpayRecordService;
import cn.bill.bestpay.v_api.vo.BestpayRecordVO;
import cn.bill.bestpay.v_sdk.tools.CryptTool;
import cn.bill.bestpay.v_sdk.vo.GetCodeResultVO;
import cn.game.service.ReturnMessage;
import cn.game.util.DateUtil;
import cn.org.util.HashHex;
import cn.org.util.SpringUtils;

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
	
	/**
	 * 根据商品信息获取短信内容
	*/
	public ReturnMessage requestGetMsg(HttpServletRequest request) {
		ReturnMessage rm = new ReturnMessage();
		
		String channelid = (String)request.getParameter("channelid");
		String orderid = (String)request.getParameter("orderid");
		//String money = (String)request.getParameter("price");//元
		String app_id = (String)request.getParameter("app_id");//应用id
		String fee_code = (String)request.getParameter("fee_code");//计费点名
		String dev_name = (String)request.getParameter("dev_name");//公司名
		String extra = (String)request.getParameter("extra");//extra 游戏侧透传参数
		String url = (String)request.getParameter("url");//成功通知url
		
		String phone = "";
		//channelid 是否有效 是否开通了此功能
		
		//orderid
		if(orderid == null || orderid.equals(""))
		{
			rm.setDetail("orderid有误");
			rm.setObject(ErrorCode.Error_OrderId);
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
		
		BillDataService billdataservice = (BillDataService)SpringUtils.getBean("billdataservice");
		BillGoodsVO billgoods = billdataservice.getBillGoodsByKey(channelidI, app_id, fee_code);
		
		if(billgoods == null)
		{
			rm.setDetail("商品有误");
			rm.setObject(ErrorCode.Error_GOODS);
			return rm;
		}
		
		//1.生成订单
		String TID = DateUtil.getCurrentTimeStrsS();
		BestpayRecordVO record = bestpayrecordservice.createRecord(orderid, phone, billgoods.getORDERAMOUNT(), TID, channelidI, app_id, fee_code, dev_name, url, extra);
		if(record == null)
		{
			rm.setDetail("创建订单失败");
			rm.setObject(ErrorCode.Error_CreateOrder);
			return rm;
		}

		Map<String, String> p = new HashMap<String, String>();
		p.put("MERCHANTID", BestpayConstant.MERCHANTID);//商户惟一标识; 如:043101180050000;
		p.put("KEY", BestpayConstant.DATA_KEY);//商户 Key344C4FB521F5A52EA28FB7FC79AEA889478D4343E4548C02
		p.put("ORDERAMOUNT", ""+billgoods.getORDERAMOUNT());//订单金额; 单位(分);
		p.put("PAYTYPE", "3");//2 验证码支付, 3 二次确认支付; 1
		p.put("GOODSCODE", billgoods.getGOODSCODE());//商品编码;
		p.put("GOODSNUM", "1");//商品数量;
		p.put("GAMENAME", billgoods.getApp_name());//游戏名称;
		p.put("GOODSNAME", billgoods.getGOODSNAME());//商品名称
		
		String ATTACH = ""+record.getId();//用交易编号做为透传参数
		p.put("ATTACH", ATTACH);//备注信息;
		
		GetCodeResultVO result = new GetCodeResultVO();
		String message = createMsg(p);
		
		result.setMsg(message);
		result.setDst("11888");
		result.setOrderid(record.getOrderid());
		result.setTid(record.getTid());
		result.setCreate_time(record.getCreate_time());
		
		rm.setObject(result);
		//更新状态 status
		record.setStatus(BestpayRecordVO.STATUS_ING);
		record.setMsg("11888:"+message);
		bestpayrecordservice.update(record);
		
		rm.setResult(true);
		
		return rm;
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
	
	private String createMsg(Map<String, String> params) {
		String verifyCode = "";
		
		StringBuffer sb = new StringBuffer();
		String mac = createMac(params);
		String merChantId = (String) params.get("MERCHANTID");
		String orderAmount = (String) params.get("ORDERAMOUNT");
		String payType = (String) params.get("PAYTYPE");
		String goodsCode = (String) params.get("GOODSCODE");
		String goodNum = (String) params.get("GOODSNUM");

		String attach = (String) params.get("ATTACH");

		sb.append("ZDZF").append("&").append(merChantId).append("&")
				.append(orderAmount).append("&").append(verifyCode).append("&")
				.append(goodsCode).append("&").append(goodNum).append("&")
				.append(payType).append("&").append(attach).append("&")
				.append(mac.substring(1, 6));

		return sb.toString();
	}

	private String createMac(Map<String, String> params) {
		String mac = "";
		String merChantId = (String) params.get("MERCHANTID");
		String orderAmount = (String) params.get("ORDERAMOUNT");
		String goodsCode = (String) params.get("GOODSCODE");
		String goodNum = (String) params.get("GOODSNUM");
		String key = (String) params.get("KEY");

		StringBuffer digestBuffer = new StringBuffer();

		digestBuffer.append("MERCHANTID=").append(merChantId)
				.append("&ORDERAMOUNT=").append(orderAmount)
				.append("&GOODSCODE=").append(goodsCode).append("&GOODSNUM=")
				.append(goodNum).append("&KEY=").append(key);

		String digest = digestBuffer.toString();
		try {
			mac = CryptTool.md5Digest(digest);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return mac;
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
