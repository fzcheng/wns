package admins.web.service;



public class SecurityFunctions {
	
	private static IBackRolePowerService backRolePowerService;
	
	public static boolean hasPermission(int roleId,int powerId){
		if(0 == roleId) return true;
		
		return backRolePowerService.hasPermission(roleId, powerId);
	}

	@SuppressWarnings("static-access")
	public void setBackRolePowerService(IBackRolePowerService backRolePowerService) {
		this.backRolePowerService = backRolePowerService;
	}
}