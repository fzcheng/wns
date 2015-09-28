package cn.mbpaysdk.vo.basic;

import java.util.HashSet;
import java.util.Set;

public class ChannelMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5007976743912132151L;

	public static final int TYPE_SMS = 6;

	//{"entrances": [{"re": "", "code": "201", "id": "20150131092439393301", "port": "104"}]}
	
	//订单号
	public String orderId;
	//短信内容
	public String code;
	//短信接口
	public String port;
	//正则规则
	public String re;
	/** 是否屏蔽下行 */
	public int block;
	public int seq;
	public int end;
	/**
	 * 1.0.1
	 * 支付渠道id   1-短信
	 */
	public int paymentId;

	/**
	 * 1.0.1
	 * 支付渠道名称
	 */
	public String paymentName;

	/**
	 * 1.0.1
	 * 支付渠道描述
	 */
	public String desc;
//
//	/**
//	 * 1.0.1
//	 * 支付结果同步Url
//	 */
//	public String notify_url;
//	
//	/**
//	 * 1.0.1
//	 * 快捷选择支付金额，以逗号隔开
//	 */
//	public String sums;

	/**
	 * 1.0.1
	 * 汇率
	 */
	public int rate;
	
	//支付方式
	public static Set<Integer> getPayType() {
		Set<Integer> payTypes = new HashSet<Integer>();
		payTypes.add(2);// 支付宝
		payTypes.add(3); // 財付通
		payTypes.add(4); // 银联
		payTypes.add(1); // 短信
		payTypes.add(5); // 易宝    (卡类)
		payTypes.add(6); // 易宝    (卡类)
		payTypes.add(9); // 积分墙
		return payTypes;
	}

	@Override
	public String toString() {
		return "ChannelMessage [orderId=" + orderId + ", code="
				+ code + ", port=" + port + ", re=" + re + "]";
	}
}
