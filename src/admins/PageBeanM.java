package admins;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Administrator 从内存分页
 *
 */
public class PageBeanM extends PageI {
    public PageBeanM(HttpServletRequest request,List list){
    	super(request);
    	super.build(list==null?new ArrayList<Object>(0):list, list==null?0:list.size());
    }
	
	public PageBeanM(int current_Page, String url, List list) {
		super(current_Page, url);
		super.build(list, list.size());
		super.depart = Depart();
		super.refresh = refresh();
	}

	
	

	public List getList() {
		if(current_Page>all_Page){
			current_Page=all_Page;
		}
		if(current_Page<=0){
			current_Page = 1;
		}
		int fromSize = (current_Page - 1) * EVERY_PAGE_COUNT;
		int toSize = (current_Page
				* EVERY_PAGE_COUNT > all_count ? all_count : current_Page
				* EVERY_PAGE_COUNT);
		return list.size()>=toSize?list.subList(fromSize, toSize):list.subList(fromSize, list.size());
	}

}
