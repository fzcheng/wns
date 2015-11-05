package cn.master;

public enum MasterErrorCode {

	Error_Exception(-100, "异常错误"), 
	Error_CMD(-101, "cmd错误"),
	Error_DecodeData(-102, "解数据异常"),
	Error_SIGNError(-103, "签名异常"),
	Error_LowVersion(-104, "客户端数据版本号低"),
	Error_NoNeedForce(-105, "无需使用强制上传"),;;


	private int code;
	private String name;
	private String msg;

	private MasterErrorCode(int code, String name, String msg) {
		this.code = code;
		this.name = name;
		this.msg = msg;
	}
	
	private MasterErrorCode(int code, String msg) {
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
