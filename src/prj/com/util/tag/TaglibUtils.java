package prj.com.util.tag;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * 标签工具包
 * @author hhj
 * Created on 2008-8-15 下午03:01:59
 */
public class TaglibUtils {

	private static Method encode = null;
	
    static {

        try {
        	Class[] args = new Class[]{String.class, String.class};
            encode = URLEncoder.class.getMethod("encode", args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    /**
     * Constructor for TaglibUtils.
     */
    private TaglibUtils() {
        super();
    }

    public static StringBuffer addAttribute(StringBuffer buff, String attributeName, String attribute) {
        if (attribute != null && attribute.length() > 0) {
            buff.append(" ").append(attributeName).append("=\"");
            buff.append(attribute);
            buff.append("\"");
        }
        return buff;
    }
    
    public static String filter(String value) {

        if (value == null || value.length() == 0) {
            return value;
        }

        StringBuffer result = null;
        String filtered = null;
        for (int i = 0; i < value.length(); i++) {
            filtered = null;
            switch (value.charAt(i)) {
                case '<':
                    filtered = "&lt;";
                    break;
                case '>':
                    filtered = "&gt;";
                    break;
                case '&':
                    filtered = "&amp;";
                    break;
                case '"':
                    filtered = "&quot;";
                    break;
                case '\'':
                    filtered = "&#39;";
                    break;
            }

            if (result == null) {
                if (filtered != null) {
                    result = new StringBuffer(value.length() + 50);
                    if (i > 0) {
                        result.append(value.substring(0, i));
                    }
                    result.append(filtered);
                }
            } else {
                if (filtered == null) {
                    result.append(value.charAt(i));
                } else {
                    result.append(filtered);
                }
            }
        }

        return result == null ? value : result.toString();
    }



    
    /**
	 * <p>URLencodes a string assuming the character encoding is UTF-8.</p>
	 *
	 * @param url
	 * @return String The encoded url in UTF-8
	 */
	public static String encodeURL(String url) {
		return encodeURL(url, "UTF-8");
	}

    
    /**
     * Use the new URLEncoder.encode() method from Java 1.4 if available, else
     * use the old deprecated version.  This method uses reflection to find the
     * appropriate method; if the reflection operations throw exceptions, this
     * will return the url encoded with the old URLEncoder.encode() method.
     * @param enc The character encoding the urlencode is performed on.
     * @return String The encoded url.
     */
    public static String encodeURL(String url, String enc) {
        try {

			if(enc==null || enc.length()==0){
				enc = "UTF-8";
			}

            // encode url with new 1.4 method and UTF-8 encoding
            if (encode != null) {
                return (String) encode.invoke(null, new Object[]{url,  enc});
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
        	e.printStackTrace();
        }

        return URLEncoder.encode(url);
    }

    /**
     * Write the specified text as the response to the writer associated with
     * this page.  <strong>WARNING</strong> - If you are writing body content
     * from the <code>doAfterBody()</code> method of a custom tag class that
     * implements <code>BodyTag</code>, you should be calling
     * <code>writePrevious()</code> instead.
     *
     * @param pageContext The PageContext object for this page
     * @param text The text to be written
     *
     * @exception JspException if an input/output error occurs (already saved)
     */
    public static void write(PageContext pageContext, String text)
            throws JspException {

        JspWriter writer = pageContext.getOut();

        try {
            writer.print(text);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Write the specified text as the response to the writer associated with
     * the body content for the tag within which we are currently nested.
     *
     * @param pageContext The PageContext object for this page
     * @param text The text to be written
     *
     * @exception JspException if an input/output error occurs (already saved)
     */
    public static void writePrevious(PageContext pageContext, String text)
            throws JspException {

        JspWriter writer = pageContext.getOut();
        if (writer instanceof BodyContent) {
            writer = ((BodyContent) writer).getEnclosingWriter();
        }

        try {
            writer.print(text);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
