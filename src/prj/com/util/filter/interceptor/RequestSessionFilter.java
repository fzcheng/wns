package prj.com.util.filter.interceptor;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestSessionFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) {
		try {
			HttpServletRequest requs = (HttpServletRequest) request;
			String url = requs.getRequestURI()+"?"+requs.getQueryString();
			HttpSession session = requs.getSession();
			//这里判断session是否为空
			String admin_name = (String)session.getAttribute("admin_name");
			String admin_paw = (String) session.getAttribute("admin_paw");
			String comd=(String) requs.getParameter("comd");
			//这里判断session是否为空
			String returnURL = "/bacl_admin.do?comd=n1";
			if(!url.equals("/bacl_admin.do?comd=n1") && !url.equals("/bacl_admin.do?comd=n2")){
			/*	if("selectLanguage".equals(comd)){
					request.getRequestDispatcher("/bacl_admin.do?comd=selectLanguage").forward(request,response);
					return;
				}*/
				if(admin_name == null || admin_name.equals("") || admin_name.equals("null") || admin_paw == null || admin_paw.equals("") || admin_paw.equals("null")){//这里判断session是否销毁
					request.getRequestDispatcher(returnURL).forward(request,response);
					return;
				}
			}
			request.setAttribute("comd", "n1");
			chain.doFilter(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void init(FilterConfig config) throws ServletException {
	}
	
}
