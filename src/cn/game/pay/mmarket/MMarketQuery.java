package cn.game.pay.mmarket;



import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MMarketQuery extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1119597036505752077L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        
        	SyncXMLUtils.query();
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

