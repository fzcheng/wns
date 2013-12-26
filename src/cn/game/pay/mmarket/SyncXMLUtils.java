package cn.game.pay.mmarket;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.thoughtworks.xstream.XStream;

public class SyncXMLUtils {
	/** 标准的XML 头部信息 */
	public final static String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	/**
	 * 生成请求消息体格式
	 * 
	 * @param obj
	 *            封装响应对象, rootName xml根节点名
	 * @throws Exception
	 */
	public String vo2Xml(Object obj, String rootName) throws Exception {
		String voXML = "";
		try {
			// XStream stream = new XStream();
			XStream stream = XStreamFactory.getInstance().createXStream(
					rootName + "Response");
			stream.alias(rootName, obj.getClass());
			String xml = stream.toXML(obj);
			xml = xml.replaceAll("&gt;", ">").replaceAll("&lt;", "<")
					.replaceAll("&amp;", "&");
			StringBuffer sb = new StringBuffer();
			sb.append(XML_HEAD);
			sb.append(xml);
			voXML = sb.toString();
			// logger.debug("生成请求消息体格式 : " + voXML);
		} catch (Exception e) {
			// logger.error("生成请求消息体格式失败！",e);
		}
		return voXML;
	}

	/**
	 * 根据返回的响应信息转换沉VO
	 * 
	 * @param xml
	 * @param rootName
	 *            xml根节点名
	 * @param className
	 *            vo类
	 * @throws ClassNotFoundException
	 *             , Exception
	 * @throws Exception
	 */
	public Object xml2Vo(String xml, String rootName, String className)
			throws ClassNotFoundException, Exception {
		XStream stream = XStreamFactory.getInstance().createXStream(
				rootName + "Request");
		stream.alias(rootName, Class.forName(className));
		return stream.fromXML(xml);
	}

	public static void main(String ard[]) {
		//callback();
		query();
	}

	public static void callback() {
		// http发送请求消息，并接收返回消息例子：
		SyncXMLUtils utils = new SyncXMLUtils();
		try {
			// 组装消息体
			SyncAppOrderReq req = new SyncAppOrderReq();
			req.setTransactionID("CSSP13000551");
			req.setMsgType("SyncAppOrderResp");
			req.setVersion("1.0.0");
			req.setMSISDN("13426222299");
			req.setAppID("300007874415");
			req.setPayCode("30000787441501");
			Address_Info_Schema schema = new Address_Info_Schema();
			schema.setDeviceID("OASS");
			schema.setDeviceType("500");
			req.setSend_Address(schema);
			Address_Info_Schema schema2 = new Address_Info_Schema();
			schema2.setDeviceID("css");
			schema2.setDeviceType("200");
			req.setDest_Address(schema2);
			String xml = utils.vo2Xml(req, "SyncAppOrderReq");
			System.out.println("vo2xml=" + xml);

			// 发送请求。
			String url = "http://127.0.0.1:8080/wns/mmarketcallback.do"; // 请求url地址

			String retxml = doPost(url, xml);// 发送请求，得到返回消息
			// 返回消息转为vo
			SyncAppOrderResp _resp = (SyncAppOrderResp) utils.xml2Vo(retxml,
					"SyncAppOrderResp", SyncAppOrderResp.class.getName());
			System.out.println("hret=============>" + _resp.gethRet());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void query() {
		// http发送请求消息，并接收返回消息例子：
		SyncXMLUtils utils = new SyncXMLUtils();
		try {
			// 组装消息体
			Trusted2ServQueryReq req = new Trusted2ServQueryReq();
			req.setMsgType("Trusted2ServQueryReq");
			req.setVersion("1.0.0");
			req.setAppID("300005292017");
			req.setOrderID("11131219114054751912");
			req.setTradeID("5BDF0611F41B08FF97911C229E62B2CA");
			req.setOrderType(0);
//			Address_Info_Schema schema = new Address_Info_Schema();
//			schema.setDeviceID("OASS");
//			schema.setDeviceType("500");
//			req.setSend_Address(schema);
//			Address_Info_Schema schema2 = new Address_Info_Schema();
//			schema2.setDeviceID("css");
//			schema2.setDeviceType("200");
//			req.setDest_Address(schema2);
			String xml = utils.vo2Xml(req, "Trusted2ServQueryReq");
			System.out.println("vo2xml=" + xml);

			// 发送请求。
			String url = "http://ospd.mmarket.com:8089/trust"; // 请求url地址
			String retxml = doPost(url, xml);// 发送请求，得到返回消息
			// 返回消息转为vo
			Trusted2ServQueryResp _resp = (Trusted2ServQueryResp) utils.xml2Vo(retxml,
					"Trusted2ServQueryResp", Trusted2ServQueryResp.class.getName());
			//System.out.println("MsgType=============>" + _resp.gethRet());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String doPost(String url, String inxml) {

		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestBody(inxml);

		HttpClient httpClient = new HttpClient();
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		System.out.println("接口调用的URL是：" + url);
		System.out.println("接口调用的输入报文：" + inxml);

		try {
			httpClient.executeMethod(postMethod);
			int responseCode = postMethod.getStatusCode();
			System.out.println("postMethod.getStatusCode()=" + responseCode);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("输出报文："
						+ postMethod.getResponseBodyAsString());
				String outstr = new String(postMethod.getResponseBody(),
						"UTF-8");// 这里设置编码的格式
				if ("".equals(outstr)) {
					outstr = null;
				}
				return outstr;
			} else {
				System.out
						.println("接口调用返回的状态码不正确.postMethod.getStatusCode() faile ");
			}

		} catch (Exception e) {
			System.out.println("接口调用响应失败" + e);

		} finally {
			postMethod.releaseConnection();
		}
		return null;
	}
}
