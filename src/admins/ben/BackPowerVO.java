package admins.ben;

import java.io.Serializable;

import admins.dao.BackPowerDAO;
import cn.org.util.SpringUtils;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackPowerVO implements Serializable {
	
	private static final long serialVersionUID = 7775190014848049147L;
	
	/** 主键 */
	private int powerId;
	/** 模块名 */
	private String powerName;
	/** 链接地址 */
	private String linkUrl;
	/** 父级ID */
	private int parentId;
	/** 模块唯一标示 */
	private String powerSn;
	/** 模块描述 */
	private String powerDsc;
	/** 0=模块  or 1=功能 */
	private int type = 0;
	/** 创建时间 */
	private String createTime;
	/** 父类权限名 */
	private String parentName;
	
	public int getPowerId() {
		return powerId;
	}
	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}
	public String getPowerName() {
		return powerName;
	}
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getPowerSn() {
		return powerSn;
	}
	public void setPowerSn(String powerSn) {
		this.powerSn = powerSn;
	}
	public String getPowerDsc() {
		return powerDsc;
	}
	public void setPowerDsc(String powerDsc) {
		this.powerDsc = powerDsc;
	}
	public String getCreateTime() {
		if(!"".equals(createTime) && 19 == createTime.length()){
			return createTime;
		}
		return createTime.substring(0,createTime.length()-2);
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getParentName() {
		BackPowerDAO bpDao = (BackPowerDAO) SpringUtils.getBean("backPowerDao");
		BackPowerVO bpVo = bpDao.getBackPowerById(parentId);
		if(bpVo == null){
			return String.valueOf(parentId);
		}
		return bpVo.getPowerName();
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public static void main(String[] args) {
		String str = "abcd125";
		System.out.println(str.indexOf("a"));
	}
	
}
