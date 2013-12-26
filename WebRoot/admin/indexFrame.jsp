<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<frameset id="" rows="100,*" cols="*" frameborder="no" border="0" framespacing="1">
  <frame src="<%=request.getContextPath()%>/admin/topFrame.jsp" name="top" scrolling="No" noresize="noresize" id="top" title="top" />
  <frameset cols="235,*" frameborder="no" border="0" framespacing="1">
    <frame src="<%=request.getContextPath() %>/bacl_admin.do?comd=n1_0" name="left" scrolling="No" noresize="noresize" id="left" title="left"/>
    <frame src="<%=request.getContextPath()%>/admin/mainFrame.jsp" name="main" id="main" title="main" />
  </frameset>
</frameset>



