package cn.mbpaysdk.vo;

import javax.servlet.http.HttpServletRequest;

import cn.game.vo.BaseVO;
import cn.org.util.DateUtil;

/**
 * mbirdpaysdk中 的phone信息
 * @author a
 *
 */
public class PhoneVO extends BaseVO{

	String imsi; 
	String sign; 
	String imei; 
	int dpi; 
	int height; 
	int width; 
	String nettype; 
	String product; 
	int app_id; 
	String package_name; 
	String version_name; 
	int version_code;
	String create_time; 
	String modify_time; 
	String sdkVersion;
	String channelId;
	int mobileType;
	int province;
	int location;
	
	public PhoneVO()
	{
		
	}
	
	public PhoneVO(HttpServletRequest request) {
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
		
		this.setImei(imei);
		this.setImsi(imsi);
		this.setApp_id(Integer.valueOf(app_id.trim()));
		this.setCreate_time(DateUtil.getCurrentTime());
		this.setModify_time(DateUtil.getCurrentTime());
		this.setProduct(product);
		this.setPackage_name(package_name);
		this.setNettype(nettype);
		this.setWidth(Integer.valueOf(width.trim()));
		this.setHeight(Integer.valueOf(height.trim()));
		this.setDpi(Integer.valueOf(dpi));
		this.setVersion_code(Integer.valueOf(version_code.trim()));
		this.setVersion_name(version_name);
		this.setSdkVersion(sdkVersion);
		this.setChannelId(channelId);
		this.setMobileType(1);//运营商
		this.setProvince(0);//省
		this.setLocation(0);//地市
		this.setSign(sign);
	}

	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public int getDpi() {
		return dpi;
	}
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getNettype() {
		return nettype;
	}
	public void setNettype(String nettype) {
		this.nettype = nettype;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}
	public int getVersion_code() {
		return version_code;
	}
	public void setVersion_code(int version_code) {
		this.version_code = version_code;
	}
	public String getSdkVersion() {
		return sdkVersion;
	}
	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public int getMobileType() {
		return mobileType;
	}
	public void setMobileType(int mobileType) {
		this.mobileType = mobileType;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
}
