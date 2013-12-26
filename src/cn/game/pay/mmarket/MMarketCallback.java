package cn.game.pay.mmarket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.game.inter.service.IDataService;
import cn.game.service.DataService;
import cn.game.service.ReturnMessage;
import cn.game.service.record.PayRecordService;
import cn.game.vo.basic.GameVO;
import cn.game.vo.record.PayrecordVO;
import cn.org.util.HashHex;
import cn.org.util.SpringUtils;

public class MMarketCallback extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1119597036505752077L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("mmarket paycallback doGet");
		
		InputStream in = req.getInputStream();
		ByteArrayOutputStream baos = null;
		String xmlStr = null;
		try {
			// 循环读取直到结束
			baos = new ByteArrayOutputStream(1024);
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = in.read(buffer)) > 0) {
				baos.write(buffer, 0, read);
			}
			xmlStr = new String(baos.toByteArray(), "utf-8");
			System.out.println("read xmlStr: " + xmlStr);

			// xml2vo
			SyncXMLUtils utils = new SyncXMLUtils();
			SyncAppOrderReq orderReq = (SyncAppOrderReq) utils.xml2Vo(xmlStr, "SyncAppOrderReq",
							SyncAppOrderReq.class.getName());
			System.out.println("SyncAppOrderReq.getAppID=============>" + orderReq.getAppID());
			// 验证 大写MD5(OrderID#ChannelID#PayCode#Appkey)
			IDataService dataservice = (DataService)SpringUtils.getBean("dataservice");
			GameVO game = dataservice.getGameByMMAppId(orderReq.getAppID());
			String sign = HashHex.HashToMD5Hex(orderReq.getOrderID()+"#"+orderReq.getChannelID()
					+"#"+orderReq.getPayCode()+"#"+game.getMmappkey());
			int hret = 0;
			if(!sign.equals(orderReq.getMD5Sign()))
			{
				//unmatched
				System.out.println("sign unmatched");
				hret = 9015;
			}
			else
			{
				//签名匹配
				System.out.println("sign matched");
				
				PayCodeVO paycode = dataservice.getPaycodeById(orderReq.getPayCode());
				
				PayrecordVO payrecord = new PayrecordVO(orderReq, paycode);
				PayRecordService payrecordservice = (PayRecordService)SpringUtils.getBean("payrecordservice");
				ReturnMessage rm = payrecordservice.savepayrecord(payrecord);
				
				if(rm.isResult())
				{
					hret = 0;
				}
			}
			//String respXml = "<?xml version='1.0' encoding='UTF-8'?><AppOrderQueryResp><MsgType>AppOrderQueryResp</MsgType><Version>1.0.0</Version><hRet>0</hRet><OrderInfo><OrderID></OrderID><StartTime></StartTime><ExpiredTime></ExpiredTime><ValidTimes>0</ValidTimes><UserID></UserID><SPServiceID></SPServiceID></OrderInfo></AppOrderQueryResp>";
			String respXml = "<?xml version='1.0' encoding='UTF-8'?><AppOrderQueryResp><MsgType>AppOrderQueryResp</MsgType><Version>1.0.0</Version><hRet>"+ hret +"</hRet></AppOrderQueryResp>";
			// 返回消息转为vo
			SyncAppOrderResp _resp = (SyncAppOrderResp) utils.xml2Vo(respXml, "AppOrderQueryResp", SyncAppOrderResp.class.getName());
			System.out.println("hret=============>" + _resp.gethRet());
			writeDataResponse(respXml, resp);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (baos != null)
				baos.close();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
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
