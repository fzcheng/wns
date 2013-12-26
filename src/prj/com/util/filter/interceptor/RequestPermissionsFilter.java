package prj.com.util.filter.interceptor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import admins.ben.BackUserVO;

/**
 * @author LuZhiYong
 * @Date 2012-7-23
 */
public class RequestPermissionsFilter implements Filter{
	
	private static Set<String> uncheckFunction = new HashSet<String>();
	
	static{
		uncheckFunction.add("n2");
		uncheckFunction.add("n1_0");
		uncheckFunction.add("logout");
		uncheckFunction.add("switchArea");
		//uncheckFunction.add("selectLanguage");
		uncheckFunction.add("checkChannelId");
	}
	
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest requs = (HttpServletRequest) request;
			String paramStr = requs.getParameter("comd");
			BackUserVO backUser = (BackUserVO) requs.getSession().getAttribute("backUser");
//			if(backUser != null && paramStr != null && !pageMatch(uncheckFunction,paramStr)){
//				IBackRolePowerService backService = (BackRolePowerService) SpringUtils.getBean("backRolePowerService");
//				
//				int roleId = backUser.getRoleId();
//				
//				if(roleId == 0){ //超级管理员
//					chain.doFilter(request, response);	
//					return;
//				}
//				
//				boolean hasPer = backService.hasPermission(roleId, paramStr);
//				String returnURL = "/bacl_admin.do?comd=toNoPermission";
//				if(!hasPer){
//					request.getRequestDispatcher(returnURL).forward(request,response);
//					return;
//				}
//			}
			chain.doFilter(request, response);	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	private boolean pageMatch(Set<String> pageSet, String pagePath) {
		for (String pageRegex : pageSet) {
			if(pageRegex.equals(pagePath)){
				return true;
			}
		}
		return false;
	}
}
