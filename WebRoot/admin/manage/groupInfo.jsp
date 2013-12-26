<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="my"  uri="/my" %>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
<body class="main_body">	
<c:if test="${message != null && message != ''}">
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><td><label style="color: red">${message}</label></td></tr>
</table>
</c:if>
<br />
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td colspan="3">
	<form id="textform1" name="textform1" action="<%=request.getContextPath()%>/bacl_admin.do?comd=groupInfo" method="post">
	公会名称：<input id="groupName" name="groupName" type="text" value="${groupName}" size="20"/>&nbsp;
	公会ID：<input id="groupId" name="groupId" type="text" value="${groupId}" size="20"/>&nbsp;
	<input type="submit" value="搜索" />
	</form>
	</td></tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="8" style="color:#DC143C;">公会信息</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >公会名称</td>
<td align="center" >公会等级</td>
<td align="center" >会长ID</td>
<td align="center" >会长昵称</td>
<td align="center" >成员数量</td>
<td align="center" >公会资金</td>
<td align="center" >创建时间</td>
</tr>
<c:forEach items="${groupList}" var="group" varStatus="status">
<tr >
<td align="center" >${group.id}</td>
<td align="center" >
<c:if test="${my:hasPermission(backUser.roleId,70016)}">
<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=groupDetail&id=${group.id}" >${group.name}</a>
</c:if>
<c:if test="${!my:hasPermission(backUser.roleId,70016)}">${group.name}</c:if>
</td>
<td align="center" >${group.level}</td>
<td align="center" >${group.roleId}</td>
<td align="center" >${group.curRoleName}</td>
<td align="center" >${group.curcount}</td>
<td align="center" >${group.gold}</td>
<td align="center" >${group.createtime}</td>
</tr>
</c:forEach>
</table>

</body>
</html>