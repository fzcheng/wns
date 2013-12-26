<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my"  uri="/my" %>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function del(id){
				if(confirm('确认删除吗?')) {
					window.location.href = "bacl_admin.do?comd=DelRole&roleId=" +id;
				}
			}
		</script>
	</head>
	<body class="main_body">
	   <c:if test="${message != null && message != ''}">
	   	<center><font color="#FF0000">${message}</font></center>
	   </c:if>
		<c:if test="${my:hasPermission(backUser.roleId,211)}"><a href="<%=request.getContextPath()%>/bacl_admin.do?comd=ToAddRole" >增加角色</a> </c:if>
		<form id="textform" name="textform"  method="post">
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 角色ID</td>
						<td align="center"> 角色名称 </td>
						<td align="center"> 能查看的渠道 </td>
						<td align="center"> 创建时间 </td> 
						<td align="center"> 操作 </td>
					</tr>
					
				<c:if test="${backRoleVO != null}">
					<c:forEach items="${backRoleVO}" var="backRoleVO">
					
						<tr> 
							<td align="center"> ${backRoleVO.roleId}</td>
							<td align="center"> ${backRoleVO.roleName} </td>
							<td align="center"> ${backRoleVO.channelName} </td>
							<td align="center"> ${backRoleVO.createTime} </td>
						<td>
							<c:if test="${my:hasPermission(backUser.roleId,212) }">
								<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=ToEditRole&roleId=${backRoleVO.roleId}" >分配渠道</a>&nbsp;
							</c:if>
							<c:if test="${my:hasPermission(backUser.roleId,212) }">
								<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=grantPermissions&roleId=${backRoleVO.roleId}" >分配功能权限</a>&nbsp;
							</c:if>
							<c:if test="${my:hasPermission(backUser.roleId,212) }">
								<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=grantMenu&roleId=${backRoleVO.roleId}" >分配菜单权限</a>&nbsp;
							</c:if>
							<c:if test="${0 != backUserVO.roleId}">
								<c:if test="${my:hasPermission(backUser.roleId,213)}">
									<a href="javascript:del(${backRoleVO.roleId})" >删除</a>
								</c:if>
							</c:if>&nbsp;
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
