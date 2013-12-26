package prj.com.util.tag;

/**
 * lable 标签
 */
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class LableTag extends TagSupport
{
	private static final long serialVersionUID = 1L;
	private String key;
	private static ILable lableFactory;

	static{
		try {
			lableFactory = (ILable) Class.forName(Constants.LABLE_CLASS).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public int doStartTag() throws JspException
	{
		String content = lableFactory.get(key);

		JspWriter jspWriter = this.pageContext.getOut();

		try
		{
			if (content == null) jspWriter.print("undefined");

			else jspWriter.write(content);
		}
		catch (IOException e)
		{
			throw new IllegalStateException(e);
		}
		
		return SKIP_BODY;
	}
	
    /**
     * Release any acquired resources.
     */
    public void release() {
        super.release();
        key = null;
    }
}

