package prj.com.util.filter.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import prj.com.util.filter.Constants;
import prj.com.util.tag.RequestModel;


public class RequestFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) {

		try {
			HttpServletRequest requs = (HttpServletRequest) request;
			requs.setCharacterEncoding("UTF-8");
			String canshu = requs.getQueryString();
			String uri = requs.getRequestURI();
			//System.out.println("path:"+requs.getRequestURI());
			//System.out.println("canshu:"+canshu);
			if (canshu != null && !canshu.equals("") && !canshu.equals("nul")) {
				String[] ss = canshu.split("=");
				String[] qq = ss[1].split("-");
				canshu = qq[0];
			}
			HttpSession session = requs.getSession();
			String sid = request.getParameter("sid");
			String returnURL = this.getPlatformUrl(sid);

			// 这里判断session是否为空
			String roleId = (String) session.getAttribute("roleId");
			//UserLoginVO userloginvo = (UserLoginVO) session.getAttribute("userloginvo");
			Long loginTime = (Long) session.getAttribute("loginTime");

			// 这里过滤游戏是否关闭
//			int is_open = dc.getapp_is_open();
//			if (userloginvo != null) {
//				//腾讯需要关闭入口，其他平台不需要（防止维护时，跳转到腾讯链接页面）
//				if (0 == cooperateId && is_open == 1 && userloginvo.getUserpower() == 0) {// 登陆入口关闭
//					//返回腾讯社区url，做为参数带到提示页面
//					String society_url = this.getDoorClosedUrl();
//					String url_not_server = "/jsp/cooperate/qq/return_not_server.jsp?society_url=" + society_url;
//					request.getRequestDispatcher(url_not_server).forward(request, response);
//					return;
//				}
//			}
			// 防刷新
//			Long oldtime = (Long) session.getAttribute(Constants.CURRENT_TIME);
//			session.setAttribute(Constants.CURRENT_TIME, System.currentTimeMillis());
//			if (oldtime != null && (Constants.NO_FRESH_TIME != -1) && (System.currentTimeMillis() - oldtime.longValue()) < Constants.NO_FRESH_TIME) {
//				request.setAttribute(Constants.NO_FRESH_PARAM, requs.getRequestURI() + "?" + this.getParam(requs));
//				request.getRequestDispatcher(Constants.NO_FRESH_PAGE).forward(request, response);
//				return;
//			}
			// 不需要过滤的url
			List<String> urlOfNoNeedFilter = SystemConfig.UrlOfNoNeedFilter; 
			//当前url
			String url = requs.getRequestURI(); 
//			if (!urlOfNoNeedFilter.contains(url)) 
//			{
//				// 处理参数
//				Map req_param = (Map) session.getAttribute(Constants.REQUEST_PARAMS);
//				if (canshu != null && req_param != null) {
//					StringBuilder p = new StringBuilder();
//					if (req_param.containsKey(canshu)) {
//						RequestModel req = (RequestModel) req_param.get(canshu);
//						String req_url = req.getUrl();
//						String[] sa = req_url.split("\\?");
//						if (!urlOfNoNeedFilter.contains(sa[0])) {
//							if (teamId == null || teamId.equals("")	|| teamId.equals("null") || loginTime == null) {
//								// 这里判断session是否销毁
//								request.getRequestDispatcher(returnURL).forward(request, response);
//								return;
//							}
//							//封号
////							if (CloseConstant.check(userloginvo.getInfoId())) {
////								request.getRequestDispatcher("/jsp/error/close.jsp").forward(request, response);
////								return;
////							}
//							// 判断是否重复登录
////							boolean check = MemCacheUtil.check_kaiguan();
////							if (check) {
////								long recordLoginTime = MemCacheUtil.getLoginTime(userloginvo.getInfoId());
////								if (recordLoginTime <= 0L) {
////									long nowTime = new Date().getTime();
////									session.setAttribute("loginTime", nowTime);
////									MemCacheUtil.addLoginIn(userloginvo.getInfoId(), nowTime);
////								} else if (loginTime < recordLoginTime) {
//////									System.out.println("session登录时间"	+ loginTime + " : " + "缓存登录时间" + recordLoginTime);
////									request.getRequestDispatcher("/not_login.jsp").forward(request,response);
////									return;
////								}
////							}
//						}
//
//						for (Entry e : req.getParams().entrySet()) {
//							String name = (String) e.getKey();
//							String value = (String) e.getValue();
//							if (!value.startsWith("$")) {
//								p.append(name).append("=").append(value).append("&");
//							}
//						}
//						if (p.length() > 0) {
//							if (req_url.indexOf("?") > 0) {
//								req_url = req_url + "&"	+ p.toString().substring(0,(p.length() - 1));
//							} else {
//								req_url = req_url + "?" + p.toString().substring(0,(p.length() - 1));
//							}
//						}
//						if (req_url.indexOf("?") > 0) {
//							req_url = req_url + "&token=" + System.currentTimeMillis();
//						} else {
//							req_url = req_url + "?token=" + System.currentTimeMillis();
//						}
//						session.setAttribute(Constants.CURRENT_REQUEST_MODEL,req);
//						//模块pv统计
//						//ModulepvService.addValue(req_url);
//						request.getRequestDispatcher(req_url).forward(request,response);
//					} else {
//						RequestModel req = (RequestModel) session.getAttribute(Constants.CURRENT_REQUEST_MODEL);
//						if (req == null) {
//							request.getRequestDispatcher(returnURL).forward(request, response);
//							return;
//						}
//						String req_url = req.getUrl();
//						for (Entry e : req.getParams().entrySet()) {
//							String name = (String) e.getKey();
//							String value = (String) e.getValue();
//							if (!value.startsWith("$")) {
//								p.append(name).append("=").append(value).append("&");
//							}
//						}
//						if (p.length() > 0) {
//							if (req_url.indexOf("?") > 0) {
//								req_url = req_url + "&" + p.toString().substring(0,(p.length() - 1));
//							} else {
//								req_url = req_url + "?" + p.toString().substring(0,(p.length() - 1));
//							}
//						}
//						//模块PV统计
//						//ModulepvService.addValue(req_url);
//						request.getRequestDispatcher(req_url).forward(request,response);
//					}
//				} 
//				else 
//				{
//					request.getRequestDispatcher(returnURL).forward(request,response);
//				}
//			}
//			else 
//			{
//				//不需要过滤的参数
//				chain.doFilter(request, response);
//			}
			
//			// 处理参数
//			Map req_param = (Map) session.getAttribute(Constants.REQUEST_PARAMS);
//			if (canshu != null && req_param != null) {
//				StringBuilder p = new StringBuilder();
//				if (req_param.containsKey(canshu)) {
//					RequestModel req = (RequestModel) req_param.get(canshu);
//					String req_url = req.getUrl();
//					String[] sa = req_url.split("\\?");
////					if (!urlOfNoNeedFilter.contains(sa[0])) {
////						if (teamId == null || teamId.equals("")	|| teamId.equals("null") || userloginvo == null || loginTime == null) {
////							// 这里判断session是否销毁
////							request.getRequestDispatcher(returnURL).forward(request, response);
////							return;
////						}
////						//封号
////						if (CloseConstant.check(userloginvo.getInfoId())) {
////							request.getRequestDispatcher("/jsp/error/close.jsp").forward(request, response);
////							return;
////						}
////						
////					 
////						
////						//判断是否已经有其他ip登录
////						//IpLimitUtil.getIpLimit(requs, r, userloginvo.getInfoId()+"");		
////						
////						// 判断是否重复登录
////						boolean check = MemCacheUtil.check_kaiguan();
////						if (check) {
////							long recordLoginTime = MemCacheUtil.getLoginTime(userloginvo.getInfoId());
////							if (recordLoginTime <= 0L) {
////								long nowTime = new Date().getTime();
////								session.setAttribute("loginTime", nowTime);
////								MemCacheUtil.addLoginIn(userloginvo.getInfoId(), nowTime);
////							} else if (loginTime < recordLoginTime) {
//////									System.out.println("session登录时间"	+ loginTime + " : " + "缓存登录时间" + recordLoginTime);
////								request.getRequestDispatcher("/not_login.jsp").forward(request,response);
////								return;
////							}
////						}
////					}
//
//					for (Entry e : req.getParams().entrySet()) {
//						String name = (String) e.getKey();
//						String value = (String) e.getValue();
//						if (!value.startsWith("$")) {
//							p.append(name).append("=").append(value).append("&");
//						}
//					}
//					if (p.length() > 0) {
//						if (req_url.indexOf("?") > 0) {
//							req_url = req_url + "&"	+ p.toString().substring(0,(p.length() - 1));
//						} else {
//							req_url = req_url + "?" + p.toString().substring(0,(p.length() - 1));
//						}
//					}
//					if (req_url.indexOf("?") > 0) {
//						req_url = req_url + "&token=" + System.currentTimeMillis();
//					} else {
//						req_url = req_url + "?token=" + System.currentTimeMillis();
//					}
//					session.setAttribute(Constants.CURRENT_REQUEST_MODEL,req);
//					System.out.println("*****req_url="+req_url);
//
//					request.getRequestDispatcher(req_url).forward(request,response);
//				} else {
//					RequestModel req = (RequestModel) session.getAttribute(Constants.CURRENT_REQUEST_MODEL);
//					if (req == null) {
//						request.getRequestDispatcher(returnURL).forward(request, response);
//						return;
//					}
//					String req_url = req.getUrl();
//					for (Entry e : req.getParams().entrySet()) {
//						String name = (String) e.getKey();
//						String value = (String) e.getValue();
//						if (!value.startsWith("$")) {
//							p.append(name).append("=").append(value).append("&");
//						}
//					}
//					if (p.length() > 0) {
//						if (req_url.indexOf("?") > 0) {
//							req_url = req_url + "&" + p.toString().substring(0,(p.length() - 1));
//						} else {
//							req_url = req_url + "?" + p.toString().substring(0,(p.length() - 1));
//						}
//					}
//					request.getRequestDispatcher(req_url).forward(request,response);
//				}
//			} else {
//				request.getRequestDispatcher(returnURL).forward(request,response);
//			}
			chain.doFilter(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void init(FilterConfig config) throws ServletException {
	}

	// 获取参数串，暂时只支持不同名参数
	private String getParam(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paramName = (String) enu.nextElement();
			String paramValue = request.getParameter(paramName);
			sb.append(paramName).append("=").append(paramValue).append("&amp;");
		}
		sb.append("tt=tt");
		return sb.toString().substring(0, (sb.length() - 1));
	}
	
	//获得session过期后需要返回的平台url地址
	private String getPlatformUrl(String sid)
	{
		String url="";
//		int platformId=CooperatConfig.getStrKeyInteger("cooperate.id");
		int platformId = 0;
		switch (platformId) {
		//腾讯
		case 0:
			url="/platform.do?sid=" + sid;
			break;
		//3G门户
		case 1:
			url="/mhlogin.do?comd=login";
			break;
		//3G泡泡
		case 2:
			url="/pplogin.do?comd=back";
			break;
		//uc
		case 3:
			if(null != sid && !"".equals(sid)){
				try {
						url = "/uclogin.do?comd=login"+"&uzone_token="+URLEncoder.encode(sid, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
			}else{
				url = "/uclogin.do?comd=login"+"&uzone_token=";
			}
			break;
		//乐讯
		case 4:
			if(null != sid && !"".equals(sid)){
				try {
						url = "/lexun.do?comd=login"+"&lxrn="+URLEncoder.encode(sid, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
			}
			break;
		//3G泡泡2区
		case 5:
			url="/pplogin2.do?comd=back";
			break;
		//PPTV
		case 6:
			url="/pptvLogin.do?comd=back";
			break;
		default:
			url="/platform.do?sid=" + sid;
			break;
		}
		return url;
	}
	//获得入口关闭后的跳转url
	private String getDoorClosedUrl()
	{
		String url="";
//		int platformId=CooperatConfig.getStrKeyInteger("cooperate.id");
		int platformId = 0;
		switch (platformId) {
		//腾讯
		case 0:
//			url= QQConifg.getStrKey("qq.game.society.url");
			break;
		//3G门户
		case 1:
			url="/mhlogin.do?comd=login";
			break;
		//3G泡泡
		case 2:
			url="/pplogin.do?comd=back";
			break;
		//3G泡泡2区
		case 5:
			url="/pplogin2.do?comd=back";
			break;
		//PPTV
		case 6:
			url="/pptvLogin.do?comd=back";
			break;
		default:
//			url= QQConifg.getStrKey("qq.game.society.url");
			break;
		}
		return url;
	}
}
