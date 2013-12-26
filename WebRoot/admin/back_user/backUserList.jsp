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
					window.location.href = "bacl_admin.do?comd=delBackUser&userId=" +id;
				}
			}
		</script>
	</head>
	<body class="main_body">
	   <c:if test="${message != null && message != ''}">
	   	<center><font color="#FF0000">${message}</font></center>
	   </c:if>
		<c:if test="${my:hasPermission(backUser.roleId,211)}"><a href="<%=request.getContextPath()%>/bacl_admin.do?comd=toAddUser" >增加管理用户</a> </c:if>
		<form id="textform" name="textform"  method="post">
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 序号  ${backUser.roleId}</td>
						<td align="center"> 用户名 </td>
						<td align="center"> 状态 </td>
						<td align="center"> 角色名称 </td>
						<td align="center"> 游戏ID </td>
						<td align="center"> 能查看的渠道 </td>
						<td align="center"> 创建时间 </td> 
						<td align="center"> 操作 </td>
					</tr>
				<c:if test="${backUserVOs != null}">
					<c:forEach items="${backUserVOs}" var="backUserVO">
					
						<tr> 
							<td align="center"> ${backUserVO.userId}</td>
							<td align="center"> ${backUserVO.userName} </td>
							<td align="center"> <c:if test="${0 == backUserVO.userState}">启用 </c:if><c:if test="${1 == backUserVO.userState }">禁用</c:if> </td>
							<td align="center"> ${backUserVO.roleNameCur} </td>
							<td align="center"> ${backUserVO.gameId}&nbsp;</td>
					
							<td align="center"> ${backUserVO.channelName}&nbsp;</td>
							<td align="center"> ${backUserVO.createTime} </td>
							<td align="center">
							<c:if test="${my:hasPermission(backUser.roleId,212) }">
								<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n46&userId=${backUserVO.userId}" >修改</a>&nbsp;
							</c:if>
							<c:if test="${0 != backUserVO.roleId}">
								<c:if test="${my:hasPermission(backUser.roleId,213)}">
									<a href="javascript:del(${backUserVO.userId})" >删除</a>
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
