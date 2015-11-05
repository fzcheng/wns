package cn.bill.bestpay.v_api.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bill.base.BillDataService;
import cn.bill.base.vo.BillChannelVO;
import cn.bill.bestpay.v_api.service.BestpayRecordService;
import cn.bill.bestpay.v_api.vo.BestpayRecordVO;
import cn.game.util.HttpUtils;
import cn.org.util.DateUtil;
import cn.org.util.HashHex;
import cn.org.util.SpringUtils;

public class bestpaycb extends javax.servlet.http.HttpServlet {

	public final static String oricode = "iso8859-1";
	public final static String code = "utf-8";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		writeDataResponse("00", resp);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//doGet(req, resp);
		System.out.println();
		System.out.println("bestpaycb doPost");
		
		String ORDERSEQ = req.getParameter("ORDERSEQ");
		String ORDERREQTRANSEQ = req.getParameter("ORDERREQTRANSEQ");
		String UPTRANSEQ = req.getParameter("UPTRANSEQ");
		String TRANDATE = req.getParameter("TRANDATE");
		String ORDERAMOUNT = req.getParameter("ORDERAMOUNT");
		String RETNCODE = req.getParameter("RETNCODE");
		String RETNINFO = req.getParameter("RETNINFO");
		
//		BestpayRecordService bestpayrecordservice = (BestpayRecordService)SpringUtils.getBean("bestpayrecordservice");
//		BestpayRecordVO bestpayrecord = bestpayrecordservice.getRecordByTid(ORDERSEQ);
//		if(bestpayrecord == null)
//			return;
//		
//		System.out.println("bestpayrecord:"+bestpayrecord.toString());
//		bestpayrecord.setChannel_order_id(UPTRANSEQ);
//		if(RETNCODE != null && RETNCODE.equals("0000"))
//		{
//			bestpayrecord.setStatus(BestpayRecordVO.STATUS_SUCC);
//			bestpayrecord.setResultcode(RETNCODE);
//		}
//		else
//		{
//			bestpayrecord.setStatus(BestpayRecordVO.STATUS_FAIL);
//			bestpayrecord.setResultcode(RETNCODE);
//		}
//		
//		bestpayrecord.setModify_time(DateUtil.getCurrentTimeStrs());
//		bestpayrecordservice.update(bestpayrecord);
		
		System.out.println("ORDERSEQ:"+ORDERSEQ);
		System.out.println("ORDERREQTRANSEQ:"+ORDERREQTRANSEQ);
		System.out.println("UPTRANSEQ:"+UPTRANSEQ);
		System.out.println("TRANDATE:"+TRANDATE);
		System.out.println("ORDERAMOUNT:"+ORDERAMOUNT);
		System.out.println("RETNCODE:"+RETNCODE);
		System.out.println("RETNINFO:"+RETNINFO);
		
		System.out.println("UPTRANSEQ_"+UPTRANSEQ);
		
		writeDataResponse("UPTRANSEQ_"+UPTRANSEQ, resp);
		
		//通知下游
//		System.out.println("url:"+bestpayrecord.getTransfer_url());
//		if(bestpayrecord.getTransfer_url() != null && !bestpayrecord.getTransfer_url().equals(""))
//		{
//			Map<String, String> m = new HashMap<String, String>();
//			m.put("orderid", bestpayrecord.getOrderid());
//			m.put("phone", bestpayrecord.getPhone());
//			m.put("price", ""+bestpayrecord.getPrice());
//			
//			BillDataService billdataservice = (BillDataService)SpringUtils.getBean("billdataservice");
//			BillChannelVO vo = billdataservice.getBillChannelById(""+bestpayrecord.getCp_channel_id());
//			
//			if(vo != null)
//			{
//				String signData = "orderid="+bestpayrecord.getOrderid()+"&phone="+bestpayrecord.getPhone()+"&price="+bestpayrecord.getPrice()+"&key="+vo.getChannelkey();
//				String sign = HashHex.HashToMD5Hex(signData);
//				
//				m.put("sign", ""+sign);
//				if(bestpayrecord.getStatus() == BestpayRecordVO.STATUS_SUCC)
//					m.put("status", "0");
//				else
//					m.put("status", "-1");
//				
//				//发送请求
//				try {
//					String result = HttpUtils.URLPost(bestpayrecord.getTransfer_url(), m);
//					System.out.println("result:"+result);
//	
//					if(result != null && result.equals("200"))
//					{
//						bestpayrecord.setTransfer_status(1);
//						bestpayrecordservice.update(bestpayrecord);
//					}
//					else
//					{
//						bestpayrecord.setTransfer_status(2);
//						bestpayrecordservice.update(bestpayrecord);
//					}
//				} catch (IOException e) {
//					bestpayrecord.setTransfer_status(3);
//					bestpayrecordservice.update(bestpayrecord);
//				}
//			}
//		}
	}

	public void writeDataResponse(String data, HttpServletResponse response)
			throws IOException {
		OutputStream os = null;
		try {
			byte[] dataByte = data.getBytes("UTF-8");
			os = response.getOutputStream();
			response.setContentType("text/xml;charset=UTF-8");
			os.write(dataByte);
			os.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			if (os != null)
				os.close();
		}
	}
}
