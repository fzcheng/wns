package cn.game.pay.mmarket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * post 消息格式 
 * MsgType 必须 String 60 消息类型 
 * Version 必须 String 10 该接口消息的版本号，本次所有的接口消息的版本都为“1.0.0” 
 * Send_Address 可选 Address_Info_Schema 发送方的地址
 * Dest_Address 可选 Address_Info_Schema 接收方的地址 
 * OrderID 必须 String 20 订单编号 CheckID 可选 Integer 取值来自能力开放平台订购、鉴权接口中的CheckID。 
 * TradeID 可选 String 64 外部交易ID 
 * Price 可选 Integer 10 业务资费(单位：分) 
 * ActionTime 必须 String 14 操作时间；格式为:YYYYMMDDHHMISS
 * ActionID 必须 Integer 操作代码，具体值如下：1： 定购服务；2： 暂停服务；(包月业务有效)3： 停止服务；(包月业务有效)4：激活服务 (包月业务有效) 
 * MSISDN 可选 String 15 目标用户手机号码(不带+86) 
 * FeeMSISDN 可选 String 15 计费手机号码(不带+86) 
 * AppID 必须 String 20 应用ID 
 * ProgramID 可选 String 20 应用程序包ID（暂时置空）
 * PayCode 必须 String 20 应用计费点编码 
 * TotalPrice 可选 Integer 10 订购总价(单位：分) 
 * SubsNumb 可选 Integer 4 订购关系个数（默认为1） 
 * SubsSeq 可选 Integer 4 当次同步的序号（默认为1） 
 * ChannelID 可选 String 64 渠道ID
 * 
 * @author lijianyu.xiaoao
 * 
 */
public class MMService {
	private NodeList getNodeList(String content, String exprstring) {
		NodeList node = null;
		try {
			java.io.ByteArrayInputStream bai = new java.io.ByteArrayInputStream(
					content.getBytes("UTF-8"));
			DocumentBuilderFactory domFactory = DocumentBuilderFactory
					.newInstance();
			domFactory.setNamespaceAware(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(bai);

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			/*
			 * xpath.setNamespaceContext(new NamespaceContext(){
			 * 
			 * @Override public String getNamespaceURI(String prefix) { return
			 * "http://www.monternet.com/dsmp/schemas/"; }
			 * 
			 * @Override public String getPrefix(String namespaceURI) { // TODO
			 * Auto-generated method stub return null; }
			 * 
			 * @Override public Iterator getPrefixes(String namespaceURI) { //
			 * TODO Auto-generated method stub return null; }});
			 */
			XPathExpression expr = xpath.compile(exprstring);
			Object r = expr.evaluate(doc, XPathConstants.NODESET);
			node = (NodeList) r;
			bai.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return node;
	}

	/**
	 * 解析post数据
	 * 
	 * @param in
	 */
	public void doPostData(InputStream in) {
		String content = "";
		DataInputStream dis = new DataInputStream(in);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			byte[] b = new byte[1024];
			int l = dis.read(b);
			while (l != -1) {
				bos.write(b, 0, l);
				l = dis.read(b);
			}
			content = bos.toString("UTF-8");
			dis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		NodeList nodes = getNodeList(content,
				"//*[local-name()='Version']|//*[local-name()='PayCode']");
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				String name = nodes.item(i).getNodeName();
				String value = nodes.item(i).getTextContent();
				// TODO:业务逻辑
				System.out.println(name + "=" + value);
			}
		}
	}

	public static void main(String[] args) {
		MMService service = new MMService();
		String demoContent = "<!--?xml version=\"1.0\" encoding=\"UTF-8\"?-->"
				+ "<syncapporderreq xmlns=\"http://www.monternet.com/dsmp/schemas/\">"
				+ "<transactionid>CSSP14510795</transactionid>"
				+ "<MsgType>SyncAppOrderReq</MsgType>"
				+ "<Version>1.0.0</Version>" + "<Send_Address>"
				+ "<devicetype>200</devicetype>" + "<deviceid>CSSP</deviceid>"
				+ "</Send_Address>" + "<Dest_Address>"
				+ "<devicetype>400</devicetype>" + "<deviceid>SPSYN</deviceid>"
				+ "</Dest_Address>" + "<OrderID>11130502000006310278</OrderID>"
				+ "<CheckID>0</CheckID>"
				+ "<ActionTime>20130502000006</ActionTime>"
				+ "<ActionID>1</ActionID>" + "<MSISDN></MSISDN>"
				+ "<FeeMSISDN></FeeMSISDN>" + "<AppID>300002796251</AppID>"
				+ "<PayCode>30000279625106</PayCode>"
				+ "<TradeID>1BF33C5CD213D684</TradeID>" + "<Price>3000</Price>"
				+ "<TotalPrice>3000</TotalPrice>" + "<SubsNumb>1</SubsNumb>"
				+ "<SubsSeq>1</SubsSeq>" + "<ChannelID>2200004711</ChannelID>"
				+ "</syncapporderreq>";
		System.out.println(demoContent);
		ByteArrayInputStream bis;
		try {
			bis = new ByteArrayInputStream(demoContent.getBytes("UTF-8"));
			service.doPostData(bis);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
