<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my"  uri="/my" %>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
<body class="main_body">	
<c:if test="${hint != null && hint != ''}">
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><td><label style="color: red">${hint}</label></td></tr>
</table>
</c:if>
<br />
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td colspan="3">
	<form id="textform1" name="textform1" action="<%=request.getContextPath()%>/bacl_admin.do?comd=bonusmissionInfo" method="post">
	编号：<input id="roleId" name="roleId" type="text" value="${roleId}" size="10"/>&nbsp;
	开始时间：<input id="sTime" name="sTime" type="text" value="${sTime}" onClick="setDay(this);" size="15" readonly="readonly" />&nbsp;
	结束时间：<input id="eTime" name="eTime" type="text" value="${eTime}" onClick="setDay(this);" size="15" readonly="readonly" />&nbsp;
	<input type="submit" value="搜索" />
	</form>
	</td></tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">玩家日常任务信息</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >玩家ID</td>
<td align="center" >玩家昵称</td>
<td align="center" >任务名称</td>
<td align="center" >完成时间</td>
</tr>
<c:forEach items="${bonList}" var="bon" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${bon.roleId} </td>
<td align="center" >${bon.roleName}</td>
<td align="center" >${bon.bonusmissionName}</td>
<td align="center" >${bon.finishTime}</td>
</tr>
</c:forEach>
</table>

</body>
</html>