package cn.mbpaysdk.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.bill.base.ErrorCode;
import cn.game.service.ReturnMessage;
import cn.mbpaysdk.vo.GetCodeResult;
import cn.mbpaysdk.vo.InitResult;
import cn.mbpaysdk.vo.PayrecordVO;
import cn.mbpaysdk.vo.PhoneVO;
import cn.mbpaysdk.vo.basic.ChannelMessage;
import cn.mbpaysdk.vo.basic.MentranceVO;

import com.ibm.mqtt.MqttException;

public class MbpaySdkService {

	PhoneService phoneservice;
	PayrecordService payrecordservice;
	
	DataService dataservice;
	
	public void setPhoneservice(PhoneService phoneservice) {
		this.phoneservice = phoneservice;
	}
	
	public void setPayrecordservice(PayrecordService payrecordservice) {
		this.payrecordservice = payrecordservice;
	}
	
	public void setDataservice(DataService dataservice) {
		this.dataservice = dataservice;
	}
	
	public void init()
	{
		try {
			//mqttBroker.connect();
			MqttBroker.getInstance().connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * sdk 初始化
	 * @param request
	 * @return
	 */
	public ReturnMessage requestInit(HttpServletRequest request) {
		ReturnMessage rm = new ReturnMessage();
		String imei = (String)request.getAttribute("imei");
		String imsi = (String)request.getAttribute("imsi");
		
		String app_id = (String)request.getAttribute("app_id");
		String sign = (String)request.getAttribute("sign");
		String channelId = (String)request.getAttribute("channel");
		String product = (String)request.getAttribute("product");
		String sdkVersion = (String)request.getAttribute("sdk");
		String package_name = (String)request.getAttribute("package");
		String nettype = (String)request.getAttribute("nettype");
		String height = (String)request.getAttribute("height");
		String width = (String)request.getAttribute("width");
		String dpi = (String)request.getAttribute("dpi");
		String version_code = (String)request.getAttribute("vcode");
		String version_name = (String)request.getAttribute("vname");
		
		if((imei == null) && (imsi == null))
		{
			rm.setDetail("手机唯一标示有误");
			rm.setObject(ErrorCode.Error_CreateOrder);
			return rm;
		}
		
		//正则表达 联通电信 (01|03)(\d{2})(\w{1})(\w{1})(\d{3})(\d{6})(\w{1})
		//sdk版本是否有效
		//应用版本是否有效
		//保存或更新phone数据
		PhoneVO phone = new PhoneVO(request);
		phoneservice.createOrUpdate(phone);
		
		rm.setResult(true);
		InitResult iresult = new InitResult();
		String[] topices = {"all"};
		//topices.append("client/" + imsi);// 单独用户
		iresult.setTopices(topices);
		rm.setObject(iresult);
		return rm;
	}
	
	/**
	 * sdk 请求支付代码
	 * @param request
	 * @return
	 */
	public ReturnMessage requestCode(HttpServletRequest request) {
		ReturnMessage rm = new ReturnMessage();
		String imsi = (String)request.getAttribute("imsi");
		String fee = (String)request.getAttribute("fee");
		String app_id = (String)request.getAttribute("app_id");
		if(imsi == null)
		{
			rm.setDetail("imsi有误");
			rm.setObject(ErrorCode.Error_CreateOrder);
			return rm;
		}
		
		//根据imsi判断是哪个运营商的  判断是哪个省的
		int mobileType = 0;
		int province = 0;
		if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007")) {
			// 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
			mobileType = 1;
		} else if (imsi.startsWith("46001") || imsi.startsWith("46006")) {// 中国联通
			mobileType = 2;
		} else if (imsi.startsWith("46003") || imsi.startsWith("46005") || imsi.startsWith("46011")) {// 中国电信
			mobileType = 3;
		} else {
			// 集团号码
			mobileType = 0;
		}
		
		//判断是否有权限使用此接口
		//找到合适的代码 注释：有可能有多种方案(各种类型)，有短信这种方案中需要多条记录
		//entrances
		List<MentranceVO> mentranceList = dataservice.getEntranceByCategoryAndFee(mobileType, Integer.valueOf(fee));
		
		//如果不存在短信的方案，需要考虑组合的方式
		
		//生成订单
		//PayrecordVO payrecord = new PayrecordVO(request);
		//payrecordservice.createOrUpdate(payrecord);
		
		//返回代码数据 和 订单数据
		rm.setResult(true);
		ChannelMessage[] channelMessages = new ChannelMessage[2];
		channelMessages[0] = new ChannelMessage();
		channelMessages[1] = new ChannelMessage();
		
		GetCodeResult getcoderesult = new GetCodeResult();
		getcoderesult.setChannelMessages(channelMessages);
		rm.setObject(getcoderesult);
		return rm;
	}
	
	/**
	 * sdk 上传支付结果（短信发送结果）
	 * @param request
	 * @return
	 */
	public ReturnMessage uploadPayresult(HttpServletRequest request) {
		ReturnMessage rm = new ReturnMessage();
		String sign = (String)request.getAttribute("sign");
		String order_id = (String)request.getAttribute("order_id");
		String status = (String)request.getAttribute("status");
		
		PayrecordVO payrecord = payrecordservice.getByOrderId(order_id);
		if(payrecord == null)
		{
			rm.setDetail("订单不存在");
			rm.setObject(ErrorCode.Error_CreateOrder);
			return rm;
		}
		
		
		
		return rm;
	}
}
