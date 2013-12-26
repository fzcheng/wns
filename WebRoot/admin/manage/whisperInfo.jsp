<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my"  uri="/my" %>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script language="javascript">
			function clearRoleId(){
				$("#roleId").val("");
			}
			function clearTime(){
				$("#time1").val("");
				$("#time2").val("");
			}
			function submitData(roleId,roleName){
				$("#roleId_s").val($.trim(roleId));
				$("#roleName_s").val($.trim(roleName));
				document.subForm.submit();
			}
		</script>
	</head>
<body class="main_body">
<c:if test="${message!=null && message !=''}">
<label style="color: red">${message}</label>
</c:if>
<br />
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td colspan="3">
	<form id="textform1" name="textform1" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n7_12" method="post">
	编号：<input id="roleId" name="roleId" type="text" value="${roleId}" onfocus="clearTime()" />&nbsp;&nbsp;
	开始时间：<input id="time1" name="beginTime" type="text" value="${beginTime}" onfocus="clearRoleId()" onClick="setDayHM(this);" />&nbsp;&nbsp;
	结束时间：<input id="time2" name="endTime" type="text" value="${endTime}" onfocus="clearRoleId()" onClick="setDayHM(this);" />&nbsp;&nbsp;
		 <input type="submit" value="搜索" />
	</form>
	</td></tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">玩家私聊信息</td></tr>
<tr >
<td align="center" width="10%">序号</td>
<td align="center" width="30%">玩家昵称</td>
<td align="center" width="20%">帐号创建时间</td>
<td align="center" width="20%">最后登录时间</td>
<td align="center" width="20%">操作</td>
</tr>
<c:forEach items="${roleList}" var="role" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${role.infoName}</td>
<td align="center" >${role.createTime}</td>
<td align="center" >${role.lastTime}</td> 
<td align="center" ><input onclick="submitData('${role.roleId}','${role.infoName}')" type="button" value="私聊" /></td> 
</tr>
</c:forEach>
</table>
<form id="subForm" name="subForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=whisper" method="post">
<input id="time_s" name="time" value="60" type="hidden"/>
<input id="roleId_s" name="roleId" type="hidden"/>
<input id="roleName_s" name="roleName" type="hidden"/>
</form>
</body>
</html>