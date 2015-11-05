package cn.bill.bestpay.v_sdk.action;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.bill.bestpay.v_api.service.BestpayRecordService;
import cn.bill.bestpay.v_api.vo.BestpayOriRecordVO;
import cn.bill.bestpay.v_api.vo.BestpayRecordVO;
import cn.org.util.DateUtil;
import cn.org.util.SpringUtils;

public class bestpaycb extends javax.servlet.http.HttpServlet {

	public final static String oricode = "iso8859-1";
	public final static String code = "utf-8";
	private final static Logger logger = Logger.getLogger(bestpaycb.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		logger.warn("bestpaycb doGet");
		writeDataResponse("00", resp);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//doGet(req, resp);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.warn("bestpaycb doPost:" + req.getParameterMap());
		
		String ORDERSEQ = req.getParameter("ORDERSEQ");
		String ORDERREQTRANSEQ = req.getParameter("ORDERREQTRANSEQ");
		String UPTRANSEQ = req.getParameter("UPTRANSEQ");
		String TRANDATE = req.getParameter("TRANDATE");
		String ORDERAMOUNT = req.getParameter("ORDERAMOUNT");
		String RETNCODE = req.getParameter("RETNCODE");
		String RETNINFO = req.getParameter("RETNINFO");
		
		logger.warn("ORDERSEQ:"+ORDERSEQ);
		logger.warn("ORDERREQTRANSEQ:"+ORDERREQTRANSEQ);
		logger.warn("UPTRANSEQ:"+UPTRANSEQ);
		logger.warn("TRANDATE:"+TRANDATE);
		logger.warn("ORDERAMOUNT:"+ORDERAMOUNT);
		logger.warn("RETNCODE:"+RETNCODE);
		logger.warn("RETNINFO:"+RETNINFO);
		
		BestpayRecordService bestpayrecordservice = (BestpayRecordService)SpringUtils.getBean("bestpayrecordservice");

		BestpayOriRecordVO bestpayorirecord = bestpayrecordservice.getRecordOriByOrderNo(ORDERSEQ);
		if(bestpayorirecord == null)
		{
			//writeDataResponse("UPTRANSEQ_"+UPTRANSEQ, resp);
			//return;
		}
		else
		{
			//logger.warn("bestpayrecord:"+bestpayorirecord.toString());
			bestpayorirecord.setORDERSEQ(ORDERSEQ);
			bestpayorirecord.setORDERREQTRANSEQ(ORDERREQTRANSEQ);
			bestpayorirecord.setUPTRANSEQ(UPTRANSEQ);
			bestpayorirecord.setTRANDATE(Integer.valueOf(TRANDATE));
			bestpayorirecord.setORDERAMOUNT2(ORDERAMOUNT);
			bestpayorirecord.setRETNCODE(RETNCODE);
			bestpayorirecord.setRETNINFO(RETNINFO);
			
			bestpayrecordservice.updateOri(bestpayorirecord);
		}
		
		writeDataResponse("UPTRANSEQ_"+UPTRANSEQ, resp);
		
		//通知下游
//		logger.warn("url:"+bestpayrecord.getTransfer_url());
//		if(bestpayrecord.getTransfer_url() != null && !bestpayrecord.getTransfer_url().equals(""))
//		{
//			Map<String, String> m = new HashMap<String, String>();
//			m.put("orderid", bestpayrecord.getOrderid());
//			m.put("phone", bestpayrecord.getPhone());
//			m.put("price", ""+bestpayrecord.getPrice());
//			m.put("extra", ""+bestpayrecord.getExtra());
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
