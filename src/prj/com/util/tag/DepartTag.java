package prj.com.util.tag;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * 分页标签 
 * @author hhj
 * Created on 2008-8-18 下午02:31:51
 */
public class DepartTag extends BodyTagSupport
{
	private static final long serialVersionUID = 1L;
	protected String text = null;
	private Object href;
	private Object currentPage =1;
	private Object totalCount ;
	private Object countPerPage = 10;
    protected StringBuilder postfieldContent;
    protected RequestModel requestModel;
    protected String cmd = "0";
    protected StringBuffer results;
    
	/**
     * The requestModel.
     */
    public RequestModel getRequestModel() {
		return requestModel;
	}

	public void setRequestModel(RequestModel requestModel) {
		this.requestModel = requestModel;
	}

	/**
     * The postfield Content.
     */
	public StringBuilder getPostfieldContent() {
		return postfieldContent;
	}

	public void setPostfieldContent(StringBuilder postfieldContent) {
		this.postfieldContent = postfieldContent;
	}

	public int doStartTag() throws JspException
	{
		postfieldContent = new StringBuilder();
		if(!href.toString().startsWith("/")){
			requestModel = new RequestModel("/"+href.toString());
    	}
		return EVAL_BODY_BUFFERED;
	}

    /**
     * Save the associated label from the body content.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {
    	BodyContent bodyContent = this.getBodyContent();
		bodyContent.clearBody();
		
       	results = new StringBuffer();
		int pagenow = Integer.parseInt(currentPage.toString());
		int total = Integer.parseInt(totalCount.toString());
		int pagenum = Integer.parseInt(countPerPage.toString());
        int pagecount = getPageCount(total,pagenum);
        
        results.append("共"+total+"条  第"+pagenow+"/"+pagecount+"页 <br />\n");
       
        
        
        
        
        if(Constants.DEBUG){
    		if(pagecount>1){
    			if(pagenow!=pagecount){
    				results.append("<anchor>下一页 \n")
    				       .append("<go href=\"").append(this.href.toString())
    				       .append("?pagenow="+(pagenow+1)+"&amp;pagenum="+pagenum).append("\"  method=\"post\" accept-charset=\"utf-8\">\n")
    				       .append(postfieldContent).append("\n</go>\n</anchor>");
    			}
    			if(pagenow!=1){
    				results.append("<anchor>上一页 \n")
    				       .append("<go href=\"").append(this.href.toString())
    				       .append("?pagenow="+(pagenow-1)+"&amp;pagenum="+pagenum).append("\"  method=\"post\" accept-charset=\"utf-8\">\n")
    				       .append(postfieldContent).append("\n</go>\n</anchor>");
    			}
    		}
    	}else{
            if(pagecount>1){
            	HttpSession session = pageContext.getSession();
                if (session != null) {
                	if(session.getAttribute(Constants.CMD_NO)==null){
            			session.setAttribute(Constants.CMD_NO, new Counter());
            		}
                	
                	Map req_params = null;
        			if(session.getAttribute(Constants.REQUEST_PARAMS)==null){
        				req_params = new Hashtable();
        			}else{
        				req_params = (Map) session.getAttribute(Constants.REQUEST_PARAMS);
        			}
        			
        			 String qudcmd="-1000001";
        				if(pagenow!=pagecount){
        					cmd = ((Counter)session.getAttribute(Constants.CMD_NO)).getNext();
        					String qudcode=(String)session.getAttribute("qudcode");
        					if(qudcode==null){
        						qudcode="0001";
        					}
        					qudcmd=cmd.concat("-"+qudcode);
        				}
        					
	    			if(pagenow!=pagecount){
	    				cmd = ((Counter)session.getAttribute(Constants.CMD_NO)).getNext();
	    				RequestModel r = new RequestModel(requestModel.getUrl());
	    				for(Entry e: requestModel.getParams().entrySet()){
	    					r.addParam((String)e.getKey(), e.getValue());
	    				}
	    				r.addParam("pagenow", Integer.toString((pagenow+1)));
	    				r.addParam("pagenum", Integer.toString(pagenum));
	    				req_params.put(cmd, r);
	    				
	    				results.append("<a href=\"").append(Constants.FACADE_URL).append(qudcmd).append("\"").append(">").append("下一页").append("</a> ");
	    			}
	    			if(pagenow!=1){
	    				cmd = ((Counter)session.getAttribute(Constants.CMD_NO)).getNext();
	    				RequestModel r = new RequestModel(requestModel.getUrl());
	    				for(Entry e: requestModel.getParams().entrySet()){
	    					r.addParam((String)e.getKey(), e.getValue());
	    				}
	    				r.addParam("pagenow", Integer.toString((pagenow-1)));
	    				r.addParam("pagenum", Integer.toString(pagenum));
	    				req_params.put(cmd, r);
	    				
	    				results.append("<a href=\"").append(Constants.FACADE_URL).append(qudcmd).append("\"").append(">").append("上一页").append("</a>");
	    			}
	    			session.setAttribute(Constants.REQUEST_PARAMS, req_params);
                }
    		}
    	}

        return (SKIP_BODY);

    }
	
    /**
     * Render the end of the hyperlink.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {
        // Evaluate the remainder of this page
    	TaglibUtils.write(pageContext, results.toString());
        return (EVAL_PAGE);
    }
    

    public int getPageCount(int reCount ,int numPerPage) {
	   int pageCount=0;
	   if (numPerPage > 0 && reCount > 0) {
	      pageCount = reCount / numPerPage; //计算分页数
	      if (reCount % numPerPage > 0) { //判断最后是否不足一页
	        pageCount += 1;
	      }
	    }
	    else {
	      if (reCount == 0) {
	        pageCount = 1;
	      }
	    }
	   return pageCount;
	}
    
	/**
	 * @param href
	 *  要设置的 href。
	 */
	public void setHref(Object href) throws JspException {
		this.href = ExpressionEvaluatorManager.evaluate(
		"href", href.toString(), Object.class, this, pageContext);
	}

	public void setCurrentPage(Object currentPage) throws JspException {
		this.currentPage = ExpressionEvaluatorManager.evaluate(
		"currentPage", currentPage.toString(), Object.class, this, pageContext);
	}

	public void setTotalCount(Object totalCount) throws JspException {
		this.totalCount = ExpressionEvaluatorManager.evaluate(
		"totalCount", totalCount.toString(), Object.class, this, pageContext);
	}

	public void setCountPerPage(Object countPerPage) throws JspException {
		this.countPerPage = ExpressionEvaluatorManager.evaluate(
	    "countPerPage", countPerPage.toString(), Object.class, this, pageContext);
	}
	
    /**
     * Release any acquired resources.
     */
    public void release() {
        super.release();
        href = null;
        postfieldContent = null;
        requestModel = null;
        cmd = null;
        results = null;
        text = null;
    }
}
