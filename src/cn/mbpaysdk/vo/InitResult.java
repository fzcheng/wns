package cn.mbpaysdk.vo;

public class InitResult {

	String port;
	int status;
	int confirm;
	String block;
	String[] topices;
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String[] getTopices() {
		return topices;
	}
	public void setTopices(String[] topices) {
		this.topices = topices;
	}
}
