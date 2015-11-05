package cn.bill.bestpay.v_sdk.action;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.bill.bestpay.v_api.service.BestpayRecordService;
import cn.bill.bestpay.v_api.vo.BestpayOriRecordVO;
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
		
		System.out.println();
		System.out.println("bestverify doPost:" + req.getParameterMap());
		System.out.println("bestverify doPost:" + goodsCode + "-" + phoneNum + "-" + orderNo + "-" + orderAmount + "-" + attach);
		
		BestpayRecordService bestpayrecordservice = (BestpayRecordService)SpringUtils.getBean("bestpayrecordservice");
		BestpayOriRecordVO bestpayorirecord = bestpayrecordservice.getRecordOriByOrderNo(orderNo);
		if(bestpayorirecord != null)
		{
			logger.error("bestpay record is duplicate.  orderNo : " + orderNo);
			writeDataResponse("false", resp);
			return;
		}
		else
		{
			int orderAmountInt = Integer.valueOf(orderAmount);
			bestpayorirecord = bestpayrecordservice.createRecordOri(orderNo, goodsCode, phoneNum, orderAmountInt, attach);
			writeDataResponse("true", resp);
			return;
		}
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
