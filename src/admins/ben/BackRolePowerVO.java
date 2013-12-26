package admins.ben;

import java.io.Serializable;

import admins.dao.BackPowerDAO;
import cn.org.util.SpringUtils;
/**
 * @author 田建
 * 
 * 14:24:51 
 */
public class BackRolePowerVO implements Serializable {

	private static final long serialVersionUID = -5454883435309744583L;
	
	/** 主键 */
	private int id;
	/** 角色id */
	private int roleId;
	/** 权限id */
	private int powerId;
	/** 权限crud状态
	 * 从你上面的意思看 
	 * 0001为十进制的1：拥有C权限； 
 	 * 0010为十进制的2：拥有R权限； 
 	 * 0100为十进制的4：拥有U权限； 
 	 * 1000为十进制的8：拥有D权限； 
	 * 也就是用最后四位来表示CRUD的权限，1表示允许，0表示不允许
	 * */
	private int state;
	/** 创建时间 */
	private String createTime;
	
	
	/**  授权允许 */
	public static final int YES = 1;
	/**  授权不允许 */
	public static final int NO = 0;
	/**  授权不确定 */
	public static final int NEUTRAL = -1;
	
	public String getPowerName(){
		BackPowerDAO bpDao = (BackPowerDAO) SpringUtils.getBean("backPowerDao");
		BackPowerVO bpVo = bpDao.getBackPowerById(powerId);
		if(bpVo == null){
			return String.valueOf(powerId);
		}
		if(bpVo.getParentId() == 0){
			return bpVo.getPowerName();
		}
		return bpVo.getParentName() +"-"+bpVo.getPowerName();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getPowerId() {
		return powerId;
	}
	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	

	public void setPermission(int permission,boolean yes){
		int temp = 1;
		temp = temp << permission;
		if(yes){
			state |= temp;
		}else{
			state &= ~temp;
		}
	}
	
	public int getPermission(int permission){
		
		int temp = 1;
		temp = temp << permission;
		temp &= state;
		if(temp != 0){
			return YES;
		}
		return NO;
	}
	

}
