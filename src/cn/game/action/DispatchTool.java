package cn.game.action;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchTool {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void changeMethod(String forMethodName, Class callclass, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 得到getparameter这个方法
			Method requestMethod = request.getClass().getMethod("getParameter", new Class[] { String.class });
			// 执行这个方法得到方法名称 参数formethodName 可以是 “method”等
			String methodName = (String) requestMethod.invoke(request, new Object[] { forMethodName });
			// 通过方法名称从servlet中得到这个方法
			Method servletMethod = callclass.getMethod(methodName, new Class[] { HttpServletRequest.class,
							HttpServletResponse.class });
			// 动态构造一个userinfoservlet对象
			Object obj = callclass.newInstance();
			// 调用servlet中的方法
			servletMethod.invoke(obj, new Object[] { request, response });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
