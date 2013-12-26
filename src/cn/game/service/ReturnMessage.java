package cn.game.service;



public class ReturnMessage {
	private int errorCode = -2;
	private boolean result;
	private int result1;
	private int result2;
	private int result3;
	private int result4;
	private int result5;
	
	private boolean comfirm;
	private String detail;
	private Object object;
	private Object object1;
	private Object object2;
	private Object object3;
	private Object object4;
	private Object object5;
	private Object object6;
	private Object object7;
	
	//专门用于主动消息
	private int havepush;
	private Object message;
	
	public Object getObject1() {
		return object1;
	}

	public void setObject1(Object object1) {
		this.object1 = object1;
	}

	public Object getObject2() {
		return object2;
	}

	public void setObject2(Object object2) {
		this.object2 = object2;
	}
	
	public Object getObject3() {
		return object3;
	}

	public void setObject3(Object object3) {
		this.object3 = object3;
	}
	
	public Object getObject4() {
		return object4;
	}
	
	public void setObject4(Object object4) {
		this.object4 = object4;
	}
	
	public Object getObject5() {
		return object5;
	}
	
	public void setObject5(Object object5) {
		this.object5 = object5;
	}
	
	public Object getObject6() {
		return object6;
	}
	
	public void setObject6(Object object6) {
		this.object6 = object6;
	}
	
	public Object getObject7() {
		return object7;
	}
	
	public void setObject7(Object object7) {
		this.object7 = object7;
	}
	
	/**
	 * 专门用于主动消息
	 */
	public Object getPushMessage() {
		return message;
	}
	/**
	 * 专门用于主动消息
	 */
	public void setPushMessage(Object object) {
		havepush = 1;
		this.message = object;
	}
	/**
	 * 专门用于主动消息
	 */
	public void setHavepush(int havepush) {
		this.havepush = havepush;
	}	
	/**
	 * 专门用于主动消息
	 */
	public int getHavepush() {
		return this.havepush;
	}
	
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public ReturnMessage() {
		this.result = false;
		this.result1 = 0;
		this.result2 = 0;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	public void setResult1(int result) {
		this.result1 = result;
	}

	public int getResult1() {
		return this.result1;
	}

	public void setResult2(int result) {
		this.result2 = result;
	}
	
	public int getResult2() {
		return this.result2;
	}
	
	public void setResult3(int result) {
		this.result3 = result;
	}	
	
	public int getResult3() {
		return this.result3;
	}
	
	public boolean isConfirm() {
		return comfirm;
	}

	public void setConfirm(boolean comfirm) {
		setResult(true);
		this.comfirm = comfirm;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		if (detail != null && !"".equals(detail.trim())) {
			this.detail = ((this.detail==null?"":this.detail)+detail.trim());
		}
	}
	
	
	public void setDetail2(String detail) {
		if (detail != null && !"".equals(detail.trim())) {
			this.detail = (detail.trim()+"");
		}
	}
	
	public void setDetailNoTran(String detail) {
		if (detail != null && !"".equals(detail.trim())) {
			this.detail = (this.detail==null?"":this.detail)+detail.trim();
		}
	}

	public int getResult4() {
		return result4;
	}

	public void setResult4(int result4) {
		this.result4 = result4;
	}

	public int getResult5() {
		return result5;
	}

	public void setResult5(int result5) {
		this.result5 = result5;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
