package prj.com.util.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title: 系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: zznode
 * </p>
 * 
 * @author
 * @CreatedTime: 2007-10-31
 * @version 1.0
 */
public class EncodingFilter implements Filter {

	private FilterConfig filterCfg;

	Logger logger = Logger.getLogger("log.servcie");

	/**
	 * 
	 */
	public EncodingFilter() {
		super();
	}

	public void init(FilterConfig arg0) throws ServletException {
		this.filterCfg = arg0;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) {
		try {
			HttpServletRequest hreq = (HttpServletRequest) request;
			String nam = null;
			String encoding = filterCfg.getInitParameter("encoding");
			if (encoding == null || "".equals(encoding)) {
				String clientEn = hreq.getHeader("accept-language");
				if ("zh-tw".equals(clientEn)) {
					encoding = "Big5";
				} else if ("zh-cn".equals(clientEn)) {
					encoding = "UTF-8";
				} else {
					encoding = "ISO8859_1";
				}
			}
			//PvSerivce.num += 1;
			request.setCharacterEncoding(encoding);
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {

	}

}
