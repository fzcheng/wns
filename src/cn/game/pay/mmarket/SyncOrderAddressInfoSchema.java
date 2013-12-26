package cn.game.pay.mmarket;



/**
 * 
 * @author daikaifeng 2013-4-1
 *
 */
public class SyncOrderAddressInfoSchema {
	
	/**
	 * 网元类型；目前定义如下：
		0:	MISC
		1:   短信网关
		2:   彩信中心
		3:   BOSS
		4:   第三方VSP
		5:   WAP网关
		100:  WAP门户
		101:  WWW门户
		102:  WTBS
		103:  AMPD
		104:  PPMS
		105:  客服系统
		106:  终端门户
		111:  PC门户服务
		200:  CSSP 
		220:  第三方门户(139MM)
		221:  mm.139.com
		222:  zcom
		400： SP
		401： mm.10086.cn
		402： mm.shequ.10086.cn
		403：shangchen.mm.shequ.10086.cn
		404：dev.10086.cn
		405：kaifa.shequ.10086.cn
		304:  实体卡平台
		305:  Dssp平台
		306： CA平台
		308： OCS
		309：TAAC平台
		500： 能力网关OASS
		501： FMM前置机
		502： Widget平台
		701：NOKIA-OVI
		702：MOTO
		801：WABP平台
	 */
	private String DeviceType;
	
	/**
	 * 网元标识;网元英文简称
	 */
	private String DeviceID;
	
	 
	public String getDeviceType() {
		return DeviceType;
	}
	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}
	public String getDeviceID() {
		return DeviceID;
	}
	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

}
