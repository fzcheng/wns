package prj.com.util.filter.interceptor;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestDispatcherFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) {
		try {
			HttpServletRequest requs = (HttpServletRequest) request;
			String sid = request.getParameter("sid");
			//这里判断session是否为空
			String returnURL = "/platform.do?sid="+sid;
			String old_url = requs.getServletPath();
			//System.out.println("************************ "+old_url);
			if("/platformloginaction.do".equals(old_url)){
				request.getRequestDispatcher(returnURL).forward(request,response);
				return;
			}
			chain.doFilter(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void init(FilterConfig config) throws ServletException {
	}
	
}
