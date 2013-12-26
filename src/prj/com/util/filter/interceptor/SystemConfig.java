/**
 * 
 */
package prj.com.util.filter.interceptor;

import java.util.ArrayList;
import java.util.List;

public class SystemConfig {
	public static List<String> UrlOfNoNeedFilter = new ArrayList<String>();
	public static int attackParameter = 1;

	static {
		UrlOfNoNeedFilter.add("/");
		UrlOfNoNeedFilter.add("/login.do");
		UrlOfNoNeedFilter.add("/back.do");
		UrlOfNoNeedFilter.add("/lead.do");
		UrlOfNoNeedFilter.add("/platform.do");
		UrlOfNoNeedFilter.add("/invite.do");
		UrlOfNoNeedFilter.add("/pay.do");
		UrlOfNoNeedFilter.add("/bacl_admin.do");
		UrlOfNoNeedFilter.add("/services");
		UrlOfNoNeedFilter.add("/businessWebService");
		UrlOfNoNeedFilter.add("http://ng.3g.qq.com/assistant/basketSurvey.jsp?cpid=1006&amp;gameid=2006");
		UrlOfNoNeedFilter.add("/pplogin.do");
		UrlOfNoNeedFilter.add("/recharge.do");
		UrlOfNoNeedFilter.add("/invalidsession.do");
		UrlOfNoNeedFilter.add("/mhlogin.do");
		UrlOfNoNeedFilter.add("/appmanager.do");
		UrlOfNoNeedFilter.add("/mhrecharge.do");
		UrlOfNoNeedFilter.add("/goodsinfo.do");
		UrlOfNoNeedFilter.add("/minrentang");
		UrlOfNoNeedFilter.add("/quanminxing");
		UrlOfNoNeedFilter.add("/pptvLogin.do");
	}

}
