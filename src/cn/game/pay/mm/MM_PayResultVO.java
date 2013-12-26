package cn.game.pay.mm;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MM_PayResultVO {
	String transIDO;//版本号
	String hRet;	//返回码 0-通知成功；	其它-其他错误
	String message;	//消息 CP响应的消息，比如“Successful”或是合作方自定义的失败原因。
	public String getTransIDO() {
		return transIDO;
	}
	public void setTransIDO(String transIDO) {
		this.transIDO = transIDO;
	}
	public String getHRet() {
		return hRet;
	}
	public void setHRet(String ret) {
		hRet = ret;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getXmlData() {
		Document document = DocumentHelper.createDocument();
		Element response = document.addElement("response");
		
		Element transIDO = response.addElement("transIDO");
		transIDO.setText(this.transIDO);
		
		Element hRet = response.addElement("hRet");
		hRet.setText(this.hRet);

		Element message = response.addElement("message");
		message.setText(this.message);
		
		return document.asXML();
	}
}
