package admins.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CallbackMain extends javax.servlet.http.HttpServlet {

	private final static Logger logger = Logger.getLogger(CallbackMain.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 5149257451700795752L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {}

	public static byte[] utf2Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new byte[0];
		}
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("helloWord!");
		String xx = request.getParameter("Ss");
		System.out.println("helloWord!");
	}
}
