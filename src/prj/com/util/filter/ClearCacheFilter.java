package prj.com.util.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 清除缓存
 */
public class ClearCacheFilter implements Filter {
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) {
        try {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setHeader("Cache-Control", "no-cache");
            res.setHeader("Cache-Control", "max-age=0");
            filterChain.doFilter(request, response);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    public void destroy() {
    }
}
