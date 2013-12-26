package cn.game.pay.mm;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * 类说明：
 * @author：tianyue
 *
 */
public class MM_PayVO {
	
	private String userId;//	用户伪码
	private String cpServiceId;//	业务代码
	private String consumeCode;//	消费代码
	private String cpParam;//	CP参数
	private String hRet;//	状态外码
	private String status;//	状态内码
	private String versionId;//	版本号
	private String transIDO;//	交易号
	
	@SuppressWarnings({ "rawtypes" })
	public MM_PayVO(Document document) {

			Element es = document.getRootElement();
			for (Iterator i = es.elementIterator(); i.hasNext();) {
				Element e = (Element) i.next();
				if(!e.isTextOnly())
				{
					for (Iterator j = e.elementIterator(); j.hasNext();) {
						Element node = (Element) j.next();
						System.out.println(node.getName() + ":" + node.getText());
					}
				}
				else
				{
					if(e.getName().equals("userId"))
					{
						userId = e.getText();
					}
					else if(e.getName().equals("cpServiceId"))
					{
						cpServiceId = e.getText();
					}
					else if(e.getName().equals("consumeCode"))
					{
						consumeCode = e.getText();
					}
					else if(e.getName().equals("cpParam"))
					{
						cpParam = e.getText();
					}
					else if(e.getName().equals("hRet"))
					{
						hRet = e.getText();
					}
					else if(e.getName().equals("status"))
					{
						status = e.getText();
					}
					else if(e.getName().equals("versionId"))
					{
						versionId = e.getText();
					}
					else if(e.getName().equals("transIDO"))
					{
						transIDO = e.getText();
					}
					
					System.out.println(e.getName() + ":" + e.getText());
				}

			}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCpServiceId() {
		return cpServiceId;
	}

	public void setCpServiceId(String cpServiceId) {
		this.cpServiceId = cpServiceId;
	}

	public String getConsumeCode() {
		return consumeCode;
	}

	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	public String getCpParam() {
		return cpParam;
	}

	public void setCpParam(String cpParam) {
		this.cpParam = cpParam;
	}

	public String getHRet() {
		return hRet;
	}

	public void setHRet(String ret) {
		hRet = ret;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getTransIDO() {
		return transIDO;
	}

	public void setTransIDO(String transIDO) {
		this.transIDO = transIDO;
	}

	public String getHexStr() {
		return userId + "_" + cpServiceId + "_" + consumeCode + "_" + cpParam
		 + "_" + hRet + "_" + status + "_" + versionId + "_" + transIDO;
	}

	public int getSectionId() {
		String str[] = cpParam.split("A");
		int sectionId = Integer.valueOf(str[0]);
		return sectionId;
	}
}
