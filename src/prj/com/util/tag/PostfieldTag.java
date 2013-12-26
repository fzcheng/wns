package prj.com.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * postfield tag
 * @author hhj
 * Created on 2008-8-16 下午09:51:20
 */
public class PostfieldTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;

	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	public PostfieldTag()
	{
		super();

	}

	private StringBuilder getTureParent(Tag parent)
	{
		if (parent instanceof LinkTag)
		{
			return ((LinkTag) parent).getPostfieldContent();
		}else if (parent instanceof DepartTag)
		{
			return ((DepartTag) parent).getPostfieldContent();
		}else if (parent instanceof GoTag)
		{
			return ((GoTag) parent).getPostfieldContent();
		}
		else
		{
			return this.getTureParent(parent.getParent());
		}
	}
	
	private RequestModel getRequestParent(Tag parent)
	{
		if (parent instanceof LinkTag)
		{
			return ((LinkTag) parent).getRequestModel();
		}else if (parent instanceof DepartTag)
		{
			return ((DepartTag) parent).getRequestModel();
		}else if (parent instanceof GoTag)
		{
			return ((GoTag) parent).getRequestModel();
		}
		else
		{
			return this.getRequestParent(parent.getParent());
		}
	}

	public int doEndTag() throws JspException
	{
		StringBuilder results = getTureParent(this.getParent());
		if(Constants.DEBUG){
			results.append("<postfield name='").append(name).append("' value='").append(value).append("'/>");
		}else{
			RequestModel request = getRequestParent(this.getParent());
			if(value.startsWith("$")){
				results.append("<postfield name='").append(name).append("' value='").append(value).append("'/>");
			}
			request.addParam(name, value);
		}
        //System.out.println("pf:"+name+"-"+value);
		return EVAL_PAGE;
	}

}
