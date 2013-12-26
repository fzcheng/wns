package admins;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author Administrator数据库分页
 *
 */
public class PageBean extends PageI {


	public PageBean(HttpServletRequest request){
		super(request);
	}
	public PageBean(HttpServletRequest request,Integer pageCount){
		super(request,pageCount);
	}
	public PageBean(int current_Page, String url) {
		super(current_Page, url);
	}

	public PageBean(String action_url,int current_Page,Map<String, Object> map) {
		this(current_Page, action_url);
		this.condition = map;
	}
	

	

}
