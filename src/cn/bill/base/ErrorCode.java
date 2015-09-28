package cn.bill.base;

public enum ErrorCode {

	Error_Exception(-100, "异常错误"), 
	Error_Phone(-101, "号码错误"), 
	Error_Money(-102, "金额错误"),
	Error_ChannelID(-103, "渠道id错误"),
	Error_CreateOrder(-104, "创建订单失败"),
	Error_GetVerifyCode(-105, "获取验证码失败"),
	Error_NoOrder(-106, "订单不存在"),
	Error_SendVerifyCode(-107, "请求失败"),
	Error_OrderId(-108, "订单号错误"),
	Error_GOODS(-109, "商品有误"),;

	private int code;
	private String name;
	private String msg;

	private ErrorCode(int code, String name, String msg) {
		this.code = code;
		this.name = name;
		this.msg = msg;
	}
	
	private ErrorCode(int code, String msg) {
		this.code = code;
		this.name = msg;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
