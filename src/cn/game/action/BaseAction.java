package cn.game.action;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.game.config.ServerKey;
import cn.game.request.ErrorResponse;
import cn.game.service.DataService;
import cn.game.util.JsonUtil;
import cn.game.vo.basic.GameVO;
import cn.org.util.SpringUtils;
import cn.org.util.StringUtil;

import com.util.encrypt.AES;
import com.util.encrypt.EncryUtil;
import com.util.encrypt.RSA;

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

        //System.out.println("baseaction execute");
        //这里处理数据得解密
        String gameId = request.getParameter("gameId");
        String data = request.getParameter("data");
        String encryptkey = request.getParameter("key");
        //String data = URLDecoder.decode(odata, "utf-8");
        //String encryptkey = URLDecoder.decode(request.getParameter("key"), "utf-8");
        System.out.println(request.getQueryString());
        if("".equals(gameId) || gameId == null)
        {
        	request.setAttribute("error", "gameId错误");
        	return dispatchMethod(mapping, form, request, response, "error");
        }
        DataService dataservice = (DataService)SpringUtils.getBean("dataservice");
        GameVO game = dataservice.getGameById(gameId);
        if(game == null)
        {
        	request.setAttribute("error", "gameId错误");
        	return dispatchMethod(mapping, form, request, response, "error");
        }
        if(game.getFlag() == 0)
		{
        	request.setAttribute("error", "游戏未授权。");
        	return dispatchMethod(mapping, form, request, response, "error");
		}
        String clientPublicKey = game.getPublicKey();
        request.setAttribute("clientPublicKey", clientPublicKey);
        if("".equals(data) || data == null)
        {
        	request.setAttribute("error", "内容错误");
        	return dispatchMethod(mapping, form, request, response, "error");
        }
        
        //System.out.println("clientPublicKey:"+clientPublicKey);
        //System.out.println("encryptkey:"+encryptkey);
		//System.out.println("gameId:"+gameId);
		//System.out.println("data:"+data);
        
        String dmerchantAesKey = RSA.decrypt(encryptkey, ServerKey.serverPrivateKey);
		String dinfo = AES.decryptFromBase64(data, dmerchantAesKey);
		TreeMap<String, Object> dmap = JsonUtil.getMap4Json(dinfo);
		String doinfo = StringUtil.getFromMap(dmap, "sign");
		String dsign = (String)dmap.get("sign");
		boolean matched = EncryUtil.checkHandleRSA(doinfo, dsign, clientPublicKey);
		if(matched == false)
        {
        	request.setAttribute("error", "签名不匹配");
        	return dispatchMethod(mapping, form, request, response, "error");
        }
		String name = (String)dmap.get("comd");
		
		if("".equals(name) || name == null)
        {
        	request.setAttribute("error", "comd错误");
        	return dispatchMethod(mapping, form, request, response, "error");
        }
		
		System.out.println("comd:"+name);
		for (Map.Entry<String, Object> entry : dmap.entrySet()) {
			request.setAttribute(entry.getKey(), entry.getValue());
			try{
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			catch(Exception e){
				
			}
		}
		
		
        // Get the parameter. This could be overridden in subclasses.
        //String parameter = getParameter(mapping, form, request, response);

        // Get the method's name. This could be overridden in subclasses.
        //String name = getMethodName(mapping, form, request, response, parameter);

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
	 * 服务器时间
	 */
	public ActionForward error(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String errorMessage = (String)request.getAttribute("error");
		
		if(errorMessage == null || errorMessage.equals(""))
			errorMessage = "参数错误";
		
		ErrorResponse errrsp = new ErrorResponse(request,response, -1, errorMessage);
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
