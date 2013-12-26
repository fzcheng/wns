package cn.game.pay.sina;

import javax.servlet.http.HttpServletRequest;

public class SinaWEIPayVO {
	String order_id;
	String amount;
	String order_uid;
	String source;
	String actual_amount;
	String signature;
	String pt;
	String status;//失败的情况固定为"F"
	
	private static String code = "utf-8";
	public SinaWEIPayVO(HttpServletRequest request)
	{
		try {
//			order_id = URLDecoder.decode(request.getParameter("order_id"), code);
//			amount = URLDecoder.decode(request.getParameter("amount"), code);
//			order_uid = URLDecoder.decode(request.getParameter("order_uid"), code);
//			source = URLDecoder.decode(request.getParameter("source"), code);
//			actual_amount = URLDecoder.decode(request.getParameter("actual_amount"), code);
//			signature = URLDecoder.decode(request.getParameter("signature"), code);
//			pt = URLDecoder.decode(request.getParameter("pt"), code);
//			status = URLDecoder.decode(request.getParameter("status"), code);
			
			order_id = request.getParameter("order_id");
			amount = request.getParameter("amount");
			order_uid = request.getParameter("order_uid");
			source = request.getParameter("source");
			actual_amount = request.getParameter("actual_amount");
			signature = request.getParameter("signature");
			pt = request.getParameter("pt");
			status = request.getParameter("status");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrder_uid() {
		return order_uid;
	}
	public void setOrder_uid(String order_uid) {
		this.order_uid = order_uid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getActual_amount() {
		return actual_amount;
	}
	public void setActual_amount(String actual_amount) {
		this.actual_amount = actual_amount;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getPt() {
		return pt;
	}
	public void setPt(String pt) {
		this.pt = pt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static String getCode() {
		return code;
	}
	public static void setCode(String code) {
		SinaWEIPayVO.code = code;
	}
	public String getGameId() {
		String s[] = pt.split("-");
		return s[0];
	}
	public int getGameMoney() {
		String s[] = pt.split("-");
		return Integer.valueOf(s[1]);
	}
}
