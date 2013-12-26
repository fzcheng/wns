package cn.game.pay.mmarket;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class MMarketQueryService {

	static MMarketQueryService self;
	
	public static MMarketQueryService getInstance()
	{
		if(self == null)
			self = new MMarketQueryService();
		return self;
	}
	
	//testflag 0:测试  1:正式
	public Trusted2ServQueryResp query(String appId, String orderId, String tradeId, int OrderType) {
		// http发送请求消息，并接收返回消息例子：
		SyncXMLUtils utils = new SyncXMLUtils();
		try {
			// 组装消息体
			Trusted2ServQueryReq req = new Trusted2ServQueryReq();
			req.setMsgType("Trusted2ServQueryReq");
			req.setVersion("1.0.0");
//			req.setAppID("300005292017");
//			req.setOrderID("11131219114054751912");
//			req.setTradeID("5BDF0611F41B08FF97911C229E62B2CA");
			req.setAppID(appId);
			req.setOrderID(orderId);
			req.setTradeID(tradeId);
			req.setOrderType(OrderType);
			String xml = utils.vo2Xml(req, "Trusted2ServQueryReq");
			System.out.println("vo2xml=" + xml);

			// 发送请求。
			String url = "http://ospd.mmarket.com:8089/trust"; // 请求url地址
			String retxml = doPost(url, xml);// 发送请求，得到返回消息
			// 返回消息转为vo
			Trusted2ServQueryResp _resp = (Trusted2ServQueryResp) utils.xml2Vo(retxml,
					"Trusted2ServQueryResp", Trusted2ServQueryResp.class.getName());
			//System.out.println("MsgType=============>" + _resp.gethRet());
			
			return _resp;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
