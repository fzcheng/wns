package cn.bill.base;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.bill.base.response.BillErrorResponse;
import cn.bill.base.vo.BillChannelVO;
import cn.org.util.HashHex;
import cn.org.util.SpringUtils;
import cn.org.util.StringUtil;

public class BaseBillAction extends DispatchAction {

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

        //这里处理数据得解密
        String channelid = request.getParameter("channelid");

        System.out.println(request.getQueryString());
        if("".equals(channelid) ||channelid == null)
        {
        	request.setAttribute("error", "channelid错误");
        	return dispatchMethod(mapping, form, request, response, "error");
        }
        BillDataService migudataservice = (BillDataService)SpringUtils.getBean("billdataservice");
        BillChannelVO channel = migudataservice.getBillChannelById(channelid);
        if(channel == null)
        {
        	request.setAttribute("error", "channelid错误");
        	return dispatchMethod(mapping, form, request, response, "error");
        }
        if(channel.getStatus() == 0)
		{
        	request.setAttribute("error", "渠道未授权。");
        	return dispatchMethod(mapping, form, request, response, "error");
		}
        
        
        String sign = request.getParameter("sign");
        
        //验证
		StringBuffer signData = new StringBuffer();
		Map m = request.getParameterMap();
		Iterator<String> iter = m.keySet().iterator();
		
		TreeMap<String, Object> valueMap = new TreeMap<String, Object>();
		while(iter.hasNext())
		{
			String key = iter.next();
			String value = request.getParameter(key);
			valueMap.put(key, value);
		}
		
		Iterator<String> iter2 = valueMap.keySet().iterator();
		while(iter2.hasNext())
		{
			String key = iter2.next();
			
			if(key.equals("sign"))
			{
				continue;
			}
			
			String value = request.getParameter(key);
			
			System.out.println(""+key+"|"+value);
			signData.append(value);
		}
		
		signData.append("appkey");
		//sha1
		String signature = HashHex.HashToSHA1(signData.toString());
		if(signature.toLowerCase().equals(sign.toLowerCase()))
		{
			System.out.println("sign match");
			//签名匹配
		}
		else
		{
			System.out.println("sign unmatch:" + signature + "|" + sign);
			//签名不匹配
			request.setAttribute("error", "签名不匹配");
        	return dispatchMethod(mapping, form, request, response, "error");
		}
        
		String name = (String)request.getParameter("comd");
		
		if("".equals(name) || name == null)
        {
        	request.setAttribute("error", "comd错误");
        	return dispatchMethod(mapping, form, request, response, "error");
        }
		
		System.out.println("comd:"+name);
//		for (Map.Entry<String, Object> entry : m.entrySet()) {
//			request.setAttribute(entry.getKey(), entry.getValue());
//			try{
//				System.out.println(entry.getKey() + " : " + entry.getValue());
//			}
//			catch(Exception e){
//				
//			}
//		}

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
	
	public int getRoleId(HttpServletRequest request) {
		String roleIdStr = (String)request.getSession().getAttribute("roleId");
		return StringUtil.parseInt(roleIdStr);
	}
	
	
	/**
	 * 错误
	 */
	public ActionForward error(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String errorMessage = (String)request.getAttribute("error");
		
		if(errorMessage == null || errorMessage.equals(""))
			errorMessage = "参数错误";
		
		BillErrorResponse errrsp = new BillErrorResponse(request,response, -1, errorMessage);
		errrsp.write();

		return null;
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
