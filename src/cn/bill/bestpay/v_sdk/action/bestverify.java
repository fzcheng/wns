package cn.bill.bestpay.v_sdk.action;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.bill.bestpay.v_api.service.BestpayRecordService;
import cn.bill.bestpay.v_api.vo.BestpayRecordVO;
import cn.org.util.SpringUtils;

public class bestverify extends javax.servlet.http.HttpServlet {

	public final static String oricode = "iso8859-1";
	public final static String code = "utf-8";
	private final static Logger logger = Logger.getLogger(bestverify.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		System.out.println("bestverify doGet");
		writeDataResponse("true", resp);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String goodsCode = req.getParameter("goodsCode");
		String phoneNum = req.getParameter("phoneNum");
		String orderNo = req.getParameter("orderNo");
		String orderAmount = req.getParameter("orderAmount");
		String attach = req.getParameter("attach");
		
		System.out.println("bestverify doPost:" + req.getParameterMap());
		System.out.println("bestverify doPost:" + goodsCode + "-" + phoneNum + "-" + orderNo + "-" + orderAmount + "-" + attach);
		
		int id = 0;
		try{
			id = Integer.valueOf(attach);
		}
		catch(Exception e)
		{
			logger.error("ATTACH to id error: ATTACH:" + attach + " msg:" + e.getMessage());
			e.printStackTrace();
			return;
		}
		BestpayRecordService bestpayrecordservice = (BestpayRecordService)SpringUtils.getBean("bestpayrecordservice");
		BestpayRecordVO bestpayrecord = bestpayrecordservice.getRecordById(id);
		if(bestpayrecord == null)
		{
			logger.error("bestpay record not exist. id:" + id);
			writeDataResponse("false", resp);
			return;
		}
		
		if(!(bestpayrecord.getSMSID() == null || bestpayrecord.getSMSID().equals("")))
		{
			logger.error("bestpay record is duplicate. id:" + id + ". SMSID1:" + bestpayrecord.getSMSID() + ". SMSID2:" + orderNo);
			writeDataResponse("false", resp);
			return;
		}
		bestpayrecord.setSMSID(orderNo);
		bestpayrecord.setPhone(phoneNum);
		bestpayrecordservice.update(bestpayrecord);
		
		writeDataResponse("true", resp);
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
