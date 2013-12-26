package cn.game.pay.mmarket;



/**
 * 
 * @author daikaifeng 2013-4-1
 *
 */
public class SyncOrderUserIdSchema 
{
	/**
	 * 用户标识类型
	1：用手机号标识
	 */
	private String UserIDType;
	/**
	 * 用户手机号码(不带+86)
	 */
	private String MSISDN;
	 
	public String getUserIDType() {
		return UserIDType;
	}
	public void setUserIDType(String userIDType) {
		UserIDType = userIDType;
	}
	public String getMSISDN() {
		return MSISDN;
	}
	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}
	
}
