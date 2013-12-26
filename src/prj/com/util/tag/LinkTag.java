package prj.com.util.tag;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * Link标签
 * @author hhj
 * Created on 2008-8-15 下午03:20:32
 */

public class LinkTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	protected String text = null;
    protected StringBuilder postfieldContent;
    protected RequestModel requestModel;
    protected String cmd = "0";
    
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

    /**
     * The hyperlink URI.
     */
    protected Object href = null;

    public Object getHref() {
        return (this.href);
    }

    public void setHref(Object href) throws Exception{
    	this.href = ExpressionEvaluatorManager.evaluate(
    			"href", href.toString(), Object.class, this, pageContext);
    }

    /**
     * The method
     */
    protected String method = null;

    public String getMethod() {
        return (this.method);
    }

    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * The JSP bean name for query parameters.
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Render the beginning of the hyperlink.
     * Indexed property since 1.1
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
    	
    	postfieldContent = new StringBuilder();
    	StringBuffer results = new StringBuffer();
    
		
		
    	if(Constants.DEBUG){
        	results.append("<anchor>").append(name);
        	results.append("<go href=\"").append(href).append("\"");
            TaglibUtils.addAttribute(results, "method", "post");
            TaglibUtils.addAttribute(results, "accept-charset", "utf-8");
            results.append(">");
          
    	}else{
    		
        	if(!href.toString().startsWith("/")){
        		href="/"+href;
        	}
        	requestModel = new RequestModel(href.toString());
        	
        	HttpServletResponse sp = (HttpServletResponse) pageContext.getResponse();
        	
        	HttpSession session = pageContext.getSession();
        	String qudcmd="-1000001";
            if (session != null) {
            	if(session.getAttribute(Constants.CMD_NO)==null){
        			session.setAttribute(Constants.CMD_NO, new Counter());
        		}
    			cmd = ((Counter)session.getAttribute(Constants.CMD_NO)).getNext();
    			String qudcode=(String)session.getAttribute("qudcode");
    			if(qudcode==null){
    				qudcode="0001";
    			}
    			qudcmd=cmd.concat("-"+qudcode);
            }
            
            
            String qqcheck = (String)session.getAttribute("qqcheck");
    		if(qqcheck ==  null || qqcheck.equals("null")){
    			qqcheck = "";
    		}
    		qqcheck = qqcheck.replaceAll("\\?", "&amp;");
            if(method.equalsIgnoreCase("post")){//如果要提交表单
            	results.append("<anchor>").append(name);
                //response.encodeURL();
                String url = Constants.FACADE_URL+qudcmd+qqcheck;
                results.append("<go href=\"").append(sp.encodeURL(url)).append("\"");
                
            	//results.append("<go href=\"").append(Constants.FACADE_URL).append(qudcmd).append(qqcheck).append("\"");
                TaglibUtils.addAttribute(results, "method", method);
                TaglibUtils.addAttribute(results, "accept-charset", "");
                results.append(">");
            }else{
            	String url = Constants.FACADE_URL+qudcmd+qqcheck;
            	results = new StringBuffer("<a href=\"").append(sp.encodeURL(url)).append("\"").append(">").append(name);
            }
            
    	}
    	
    	TaglibUtils.write(pageContext, results.toString());
        this.text = null;
        return (EVAL_BODY_TAG);
    }

    /**
     * Save the associated label from the body content.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {

        if (bodyContent != null) {
            String value = bodyContent.getString();
            if (value.length() > 0)
                text = value;
        }
        return (SKIP_BODY);

    }

    /**
     * Render the end of the hyperlink.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {
    	StringBuffer results = new StringBuffer();
    	if(Constants.DEBUG){
    		results.append(this.postfieldContent);
        	results.append("</go>").append("</anchor>");
    	}else{
            if(method.equalsIgnoreCase("post")){//如果要提交表单
            	results.append(this.postfieldContent);
            	results.append("</go>").append("</anchor>");
            }else{
            	results.append("</a>");
            }
            HttpSession session = pageContext.getSession();
            if (session != null) {
    	        Map req_params = null;
    			if(session.getAttribute(Constants.REQUEST_PARAMS)==null){
    				req_params = new Hashtable();
    			}else{
    				req_params = (Map) session.getAttribute(Constants.REQUEST_PARAMS);
    			}
    			req_params.put(cmd, requestModel);
    			
    			session.setAttribute(Constants.REQUEST_PARAMS, req_params);
            }
    	}

        // Render the remainder to the output stream
        TaglibUtils.write(pageContext, results.toString());

        // Evaluate the remainder of this page
        return (EVAL_PAGE);

    }

    /**
     * Release any acquired resources.
     */
    public void release() {
        super.release();
        href = null;
        method = null;
        name = null;
        text = null;
    }
}
