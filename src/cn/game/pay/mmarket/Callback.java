package cn.game.pay.mmarket;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Callback extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1119597036505752077L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        InputStream in = req.getInputStream();
        ByteArrayOutputStream baos = null;
        String xmlStr = null;
        try{

            baos = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int read = 0;
            while((read = in.read(buffer)) > 0) {
                baos.write(buffer, 0, read);
            }
            xmlStr = new String(baos.toByteArray(),"utf-8");
            System.out.println("read xmlStr: "+xmlStr);
            
            //xml2vo
            SyncXMLUtils utils=new SyncXMLUtils();
            AppOrderQueryReq appOrderQueryReq = (AppOrderQueryReq)utils.xml2Vo(xmlStr, "AppOrderQueryReq",  AppOrderQueryReq.class.getName());
            System.out.println("appOrderQueryReq.getAppID=============>"+appOrderQueryReq.getAppID());
            String respXml="<?xml version='1.0' encoding='UTF-8'?><AppOrderQueryResp><MsgType>AppOrderQueryResp</MsgType><Version>1.0.0</Version><hRet>106</hRet><OrderInfo><OrderID></OrderID><StartTime></StartTime><ExpiredTime></ExpiredTime><ValidTimes>0</ValidTimes><UserID></UserID><SPServiceID></SPServiceID></OrderInfo></AppOrderQueryResp>";
            //返回消息转为vo
            AppOrderQueryResp _resp=(AppOrderQueryResp)utils.xml2Vo(respXml, "AppOrderQueryResp",  AppOrderQueryResp.class.getName());
            System.out.println("MsgType=============>"+_resp.gethRet());
            writeDataResponse(respXml,resp);
        }catch(Exception e){
            System.out.println(e);
        }finally { if (baos != null)  baos.close(); }
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	    doGet(req, resp);
	}
	
	 public void writeDataResponse(String data,HttpServletResponse response) throws IOException {
	       OutputStream os = null;
	       try {
	           byte[] dataByte = data.getBytes("UTF-8");
	           os = response.getOutputStream();
	           response.setContentType("text/xml;charset=UTF-8");
	           os.write(dataByte);
	           os.flush();
	       }
	       catch (IOException e) {
	           throw e;
	       }
	       finally {
	           if (os != null)  os.close(); 
	       }
	   }

}

