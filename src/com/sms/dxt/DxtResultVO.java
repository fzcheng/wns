package com.sms.dxt;

public class DxtResultVO {

	String returnstatus;
	String message;
	int remainpoint;
	int taskID;
	int successCounts;
	public String getReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getRemainpoint() {
		return remainpoint;
	}
	public void setRemainpoint(int remainpoint) {
		this.remainpoint = remainpoint;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public int getSuccessCounts() {
		return successCounts;
	}
	public void setSuccessCounts(int successCounts) {
		this.successCounts = successCounts;
	}
}
