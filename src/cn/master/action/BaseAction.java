package cn.master.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.master.MasterErrorCode;
import cn.master.response.MasterResponseEncoder;
import cn.org.util.HashHex;

public class BaseAction extends DispatchAction {

	public final static String oricode = "iso8859-1";
	public final static String code = "utf-8";
	
	/**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping  The ActionMapping used to select this instance
     * @param form     The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return The forward to which control should be transferred, or
     *         <code>null</code> if the response has been completed.
     * @throws Exception if an exception occurs
     */
	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        if (isCancelled(request)) {
            ActionForward af = cancelled(mapping, form, request, response);

            if (af != null) {
                return af;
            }
        }
        
        String cmd = request.getHeader("cmd");
        String user = request.getHeader("user");
        String type = request.getHeader("Content-Type");
        String useragent = request.getHeader("User-Agent");
        Enumeration enumer = request.getHeaderNames();
        String remoteAddr = request.getHeader("X-Forwarded-For");
        request.setAttribute("respcmd", cmd);
        request.setAttribute("User-Agent", useragent);
        response.setHeader("cmd", cmd);
        
        InputStream in = request.getInputStream();
		ByteArrayOutputStream baos = null;
		String para = null;
		try {
			// 循环读取直到结束
			baos = new ByteArrayOutputStream(1024);
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = in.read(buffer)) > 0) {
				baos.write(buffer, 0, read);
				System.out.println("read: " + read);
			}
 
			request.setAttribute("ByteArray", baos);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
        //验证 从header中取出sign
		String signOri = (String)request.getHeader("sign");
		String signCalc = HashHex.HashToMD5Hex(getSignByteArray(baos.toByteArray(), cmd + MasterResponseEncoder.SIGN_KEY));
		
		if(signOri == null || !signCalc.equals(signOri))
		{
			return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_SIGNError);
		}
			
		String name = cmd;
		if("".equals(name) || name == null)
        {
        	//request.setAttribute("errorCode", MasterErrorCode.Error_CMD.getCode());
        	//request.setAttribute("errorMsg", MasterErrorCode.Error_CMD.getMsg());
        	return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_CMD);
        }
		
		System.out.println("cmd:"+name);

        // Prevent recursive calls
        if ("execute".equals(name) || "perform".equals(name)) {
            String message =
                messages.getMessage("dispatch.recursive", mapping.getPath());

            log.error(message);
            throw new ServletException(message);
        }
        
        // Invoke the named method, and return the result
        return dispatchMethod(mapping, form, request, response, name);
    }

	/**
	 * 获取sign前的byte数组   把原byte数组和额外的String链接一起
	 * @param data
	 * @param str
	 * @return
	 */
	private byte[] getSignByteArray(byte[] data, String str) {

		byte[] b = ArrayUtils.addAll(data, (str).getBytes());
		return b;
	}
	
	/**
	 * 跳转到错误处理方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param errorCmd 
	 * @return
	 */
	public ActionForward dispatchMethodError(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, MasterErrorCode errorCmd)
	{
		try {
			request.setAttribute("errorCode", ""+errorCmd.getCode());
        	request.setAttribute("errorMsg", ""+errorCmd.getMsg());
        	
			return dispatchMethod(mapping, form, request, response, "error");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
     * Returns the method name, given a parameter's value.
     *
     * @param mapping   The ActionMapping used to select this instance
     * @param form      The optional ActionForm bean for this request (if
     *                  any)
     * @param request   The HTTP request we are processing
     * @param response  The HTTP response we are creating
     * @param parameter The <code>ActionMapping</code> parameter's name
     * @return The method's name.
     * @throws Exception if an error occurs.
     * @since Struts 1.2.0
     */
	@Override
    protected String getMethodName(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response,
        String parameter) throws Exception {
        // Identify the method name to be dispatched to.
        // dispatchMethod() will call unspecified() if name is null
        return request.getParameter(parameter);
    }
    
	public boolean isEmpty(String val) {
		return (val == null || val.trim().length() == 0);
	}
	
	public String getBStr(byte[] b) {
		String result = "{";
		for(int i = 0; i < b.length; i ++)
		{
			result += "" + b[i] + ", ";
		}
		
		result += "}";
		return result;
	}
	
	public int parseInt(String str)
	{
		try{
			int r = Integer.valueOf(str);
			return r;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
}
