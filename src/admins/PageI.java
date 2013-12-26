package admins;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public abstract class PageI {
	
	protected int current_Page=1;// 当前页
	protected int all_Page = 0;// 总页数
	public static int EVERY_PAGE_COUNT = 10;// 每页显示条数10
	protected int every_Page_Num = 6;//每页显示数字数
	protected List<Object> list = new ArrayList<Object>();// 记录集
	protected int all_count = 0;//记录总数
	protected String url = null;//跳转Action
	protected String depart;
	protected String refresh;
	private String nowUrl;//当前条件
	protected Map<String, Object> condition;// 查询条件
	protected HttpSession session;
	private String GAME_PATH = "/devpm";//游戏更目录
	private Integer pageCount;
	
	
	private String pageLimit;
	
	public enum Show{
		SHOW(true,false,true);
		private boolean common;
		private boolean num;
		private boolean input;
		Show(boolean common,boolean num,boolean input){
			this.common = common;
			this.num = num;
			this.input = input;
		}
	}
	Show so = Show.values()[0];
	public boolean getCommon(){
		return so.common;
	}
	
	
	public String getPageLimit() {
		pageLimit = " limit "+(current_Page-1)*getPageCount()+","+getPageCount();;
		return pageLimit;
	}
	
	public int getLimit(){
		return (current_Page-1)*getPageCount();
	}

	/**
	 * @return
	 */
	public int getPageCount() {
		if(pageCount != null){
			return pageCount;
		}
		return PageI.EVERY_PAGE_COUNT;
	}


	public void setPageLimit(String pageLimit) {
		this.pageLimit = pageLimit;
	}


	public boolean getNum(){
		return so.num;
	}
	
	public boolean getInput(){
		return so.input;
	}
	public PageI(HttpServletRequest request){
		this.condition = getCondition(request);
		this.session = request.getSession();
		
	}

	public PageI(HttpServletRequest request,Integer pageCount){
		this.condition = getCondition(request);
		this.session = request.getSession();
		this.pageCount = pageCount;
	}
	public Map<String,Object> getCondition(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//this.url = request.getContextPath()+request.getServletPath();
		String pageurll = request.getContextPath()+request.getServletPath();
		String qqcheck = (String)request.getSession().getAttribute("qqcheck");
		if(qqcheck ==  null || qqcheck.equals("null")){
			qqcheck = "";
		}
		if(pageurll.indexOf("?") != -1){
			qqcheck = qqcheck.replaceAll("\\?", "&amp;");
			this.url = pageurll+qqcheck;
		}else{
			this.url = pageurll+qqcheck;
		}
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paramName = (String) enu.nextElement();
			String paramValue = request.getParameter(paramName);
			
			//分页时带中文参数处理
			try {
				//判断是否中文，是中文就不处理
				 char[] charArray = paramValue.toCharArray(); 
				 if (0<charArray.length && (charArray[0] >= 0x4e00)&&(charArray[0] <= 0x9fbb)){ 

				 }else{
			    	//处理中文乱码
					 paramValue = new String(paramValue.getBytes("ISO-8859-1"),"UTF-8");
				 }

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			map.put(paramName, paramValue);
		}
		if(map.containsKey("current_Page")){
			String curPage= (String) map.get("current_Page");
			if(curPage==null||curPage.equals(""))
			{
				curPage = "1";
			}
			this.current_Page = Integer.valueOf(curPage.trim());
			if(this.current_Page<=0)
				this.current_Page=1;
			map.remove("current_Page");
		}
		return map;
	}
	
	
	public PageI(int current_Page,String url){
		if(url==null||"".equals(url)){
			throw new IllegalAccessError();
		}
		this.current_Page = (current_Page<=0?1:current_Page);
		this.url = url;
	}
	
	public int getCurrent_Page() {
		return current_Page;
	}

	public void setCurrent_Page(int current_Page) {
		this.current_Page = current_Page;
	}

	public int getAll_Page() {
		return all_Page;
	}

	public void setAll_Page(int all_Page) {
		this.all_Page = all_Page;
	}

	public List<Object> getList() {
		return list;
	}

	public int getAll_count() {
		return all_count;
	}

	public void setAll_count(int all_count) {
		this.all_count = all_count;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void build(List<Object> list,int allCount){
		this.list = list;
		this.all_count = allCount;
		this.buildPageBean(current_Page, all_count, list);
		this.depart = Depart();
		this.refresh = refresh();
	}

	private void buildPageBean(int current_Page, int all_count, List<Object> list) {
		this.current_Page = (current_Page <= 0 ? 1 : current_Page);
		this.all_count = all_count<0?0:all_count;
		this.all_Page = ((all_count %getPageCount()==0)?all_count/getPageCount():(all_count/getPageCount()+1));
		this.list = list;
	}
	
	public String refresh() {
		StringBuffer sb = new StringBuffer();
		//1.0
		if(session==null||session.getAttribute("vesion")==null){
		sb.append("<anchor><go method=\"post\" href=\"" + GAME_PATH + "/" + url + "\" >");
		sb.append("<postfield name=\"current_Page\" value=\"" + current_Page + "\" />");
		if (condition != null) {
			for (Object o : condition.keySet()) {
				sb.append("<postfield name=\"" + o + "\" value=\"" + condition.get(o) + "\" />");
			}
		}
		sb.append("</go>刷新</anchor>");
		return sb.toString();
		}else{
			sb.append("<a href=\""+ url+"?");
			sb.append("current_Page=").append(current_Page).append("&amp;");
			if (condition != null) {
				for (Object o : condition.keySet()) {
					sb.append(o).append("=").append(condition.get(o)).append("&amp;");
				}
			}
			sb.append( "\" >");
			sb.append("刷新</a>\040");
			return sb.toString();
		}
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getRefresh() {
		return refresh;
	}

	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}
	
	public String getLimitSql(){
		return " limit "+(current_Page-1)*getPageCount()+","+getPageCount();
	}
	
	public String Depart() {
		StringBuffer sb = new StringBuffer();
		if (url == null || "".equals(url)) {
			return "";
		} else {
			if(url.indexOf("&amp;")!=-1)
			{
				url=url.replaceAll("&amp;", "?");
			}
			if (getList() != null && getList().size() > 0) {
				if(current_Page > all_Page){
					current_Page = all_Page;
				}
				if (getCommon()) {
					if(getNum()){
						sb.append("<br/>");
					}
					if (all_Page > 1) {
						if (all_Page > current_Page) {
							//1.0
							if(session==null||session.getAttribute("vesion")==null){
							sb.append("<anchor><go method=\"post\" href=\""+ url+ "\" >");
							sb.append("<postfield name=\"current_Page\" value=\""+ (current_Page + 1) + "\" />");
							if (condition != null) {
								for (Object o : condition.keySet()) {
									sb.append("<postfield name=\"" + o + "\" value=\"" + condition.get(o) + "\" />");
								}
							}
							sb.append("</go>下一页</anchor>\040");
							sb.append("<anchor><go method=\"post\" href=\"" + url+ "\" >");
							sb.append("<postfield name=\"current_Page\" value=\"" + all_Page + "\" />");
							if (condition != null) {
								for (Object o : condition.keySet()) {
									sb.append("<postfield name=\"" + o + "\" value=\"" + condition.get(o) + "\" />");
								}
							}
							sb.append("</go>末页</anchor>\040");
							}else{
								//2.0
								sb.append("<a href=\""+ url+"?");
								sb.append("current_Page=").append(current_Page + 1).append("&amp;");
								if (condition != null) {
									for (Object o : condition.keySet()) {
										sb.append(o).append("=").append(condition.get(o)).append("&amp;");
									}
								}
								sb.append( "\" >");
								sb.append("下一页</a>\040");
								sb.append("<a href=\""+ url+"?");
								sb.append("current_Page=").append(all_Page).append("&amp;");
								if (condition != null) {
									for (Object o : condition.keySet()) {
										sb.append(o).append("=").append(condition.get(o)).append("&amp;");
									}
								}
								sb.append( "\" >");
								sb.append("末页</a>\040");
							}
						}
						if (current_Page > 1) {
							if(session==null||session.getAttribute("vesion")==null){
							sb.append("<anchor><go method=\"post\" href=\"" + url + "\" >");
							sb.append("<postfield name=\"current_Page\" value=\"" + (current_Page - 1) + "\" />");
							if (condition != null) {
								for (Object o : condition.keySet()) {
									sb.append("<postfield name=\"" + o + "\" value=\"" + condition.get(o) + "\" />");
								}
							}
							sb.append("</go>上一页</anchor>\040");
							sb.append("<anchor><go method=\"post\" href=\"" + url + "\" >");
							sb.append("<postfield name=\"current_Page\" value=\"1\" />");
							if (condition != null) {
								for (Object o : condition.keySet()) {
									sb.append("<postfield name=\"" + o + "\" value=\"" + condition.get(o) + "\" />");
								}
							}
							sb.append("</go>首页</anchor>\040");
							}else{
								sb.append("<a href=\""+ url+"?");
								sb.append("current_Page=").append(current_Page - 1).append("&amp;");
								if (condition != null) {
									for (Object o : condition.keySet()) {
										sb.append(o).append("=").append(condition.get(o)).append("&amp;");
									}
								}
								sb.append( "\" >");
								sb.append("上一页</a>\040");
								sb.append("<a href=\""+ url+"?");
								sb.append("current_Page=").append(1).append("&amp;");
								if (condition != null) {
									for (Object o : condition.keySet()) {
										sb.append(o).append("=").append(condition.get(o)).append("&amp;");
									}
								}
								sb.append( "\" >");
								sb.append("首页</a>\040");
							}
						}
					}
				}
				if(getInput()&&all_Page>1){
					if(getNum()||getCommon()){
						sb.append("<br/>");
					}
					if(session==null||session.getAttribute("vesion")==null){
					sb.append("<input type=\"text\" name=\"current_Page\" format=\"*N\" size=\"3\" maxlength=\"5\" value=\""+ current_Page  + "\" />");
					sb.append("<anchor><go method=\"post\" href=\""+ url + "\" >");
					sb.append("<postfield name=\"current_Page\" value=\"$(current_Page)\" />");
					if (condition != null) {
						for (Object o : condition.keySet()) {
							sb.append("<postfield name=\"" + o + "\" value=\"" + condition.get(o) + "\" />");
						}
					}
					sb.append("</go>跳转</anchor>");
					sb.append("<br/>");
					}else{
						sb.append("<form method=\"post\" action=\""+ url + "\" >");
						sb.append("<input type=\"text\" name=\"current_Page\" format=\"*N\" size=\"3\" value=\""+ current_Page  + "\" />");
						if (condition != null) {
							for (Object o : condition.keySet()) {
								sb.append("<input type=\"hidden\" name=\""+o+"\" value=\""+condition.get(o)+"\" />");
							}
						}
						sb.append("<input type=\"submit\" value=\"跳转\"/>");
						sb.append("</form>");
					}
				}
				sb.append("第" + current_Page + "页/共" + all_Page + "页");
			} else {
				sb.append("无");
			}
			return sb.toString();
		}
	}
	
	public String getDepartWeb(){
		StringBuffer sb = new StringBuffer();
		if (url == null || "".equals(url)) {
			return "";
		} else {
			if (getList() != null && getList().size() > 0) {
				if (getCommon()) {
					if(getNum()){
						sb.append("<br/>");
					}
					if (all_Page > 1) {
						if (all_Page > current_Page) {
								//2.0
								sb.append("<a href=\""+ url+"?");
								sb.append("current_Page=").append(current_Page + 1).append("&amp;");
								if (condition != null) {
									for (Object o : condition.keySet()) {
										sb.append(o).append("=").append(condition.get(o)).append("&amp;");
									}
								}
								sb.append( "\" >");
								sb.append("<font size=\"3\" color=\"green\">下一页</font></a>\040");
								sb.append("<a href=\""+ url+"?");
								sb.append("current_Page=").append(all_Page).append("&amp;");
								if (condition != null) {
									for (Object o : condition.keySet()) {
										sb.append(o).append("=").append(condition.get(o)).append("&amp;");
									}
								}
								sb.append( "\" >");
								sb.append("<font size=\"3\" color=\"green\">末页</font></a>\040");
						}
						if (current_Page > 1) {
								sb.append("<a href=\""+ url+"?");
								sb.append("current_Page=").append(current_Page - 1).append("&amp;");
								if (condition != null) {
									for (Object o : condition.keySet()) {
										sb.append(o).append("=").append(condition.get(o)).append("&amp;");
									}
								}
								sb.append( "\" >");
								sb.append("<font size=\"3\" color=\"green\">上一页</font></a>\040");
								sb.append("<a href=\""+ url+"?");
								sb.append("current_Page=").append(1).append("&amp;");
								if (condition != null) {
									for (Object o : condition.keySet()) {
										sb.append(o).append("=").append(condition.get(o)).append("&amp;");
									}
								}
								sb.append( "\" >");
								sb.append("<font size=\"3\" color=\"green\">首页</font></a>\040");
						}
					}
				}
				if(getInput()&&all_Page>1){
						sb.append("<form method=\"post\" action=\""+ url
								+ "\" >");
						sb.append("<input type=\"text\" name=\"current_Page\" format=\"*N\" size=\"3\" value=\""+ current_Page  + "\" />");
						if (condition != null) {
							for (Object o : condition.keySet()) {
								sb.append("<input type=\"hidden\" name=\""+o+"\" value=\""+condition.get(o)+"\" />");
							}
						}
						sb.append("<input type=\"submit\" value=\"跳转\"/>");
						sb.append("</form>");
				}
				sb.append("<font size=\"3\" color=\"green\">第" + current_Page + "页/共" + all_Page + "页</font>");
			} else {
				sb.append("无");
			}
			return sb.toString();
		}
	}

	public String getNowUrl() {
		StringBuffer sb = new StringBuffer();
		sb.append(url).append("?");
		sb.append("current_Page="+ current_Page);
		if (condition != null) {
			for (Object o : condition.keySet()) {
				sb.append("&" + o+ "=" + condition.get(o));
			}
		}
	    return sb.toString();
	}

	
}
