<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my"  uri="/my" %>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>推广后台管理系统</title>
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
	   	<center><font color="#FF0000">na${message}</font></center>
	   </c:if>
	   
		<c:if test="${my:hasPermission(backUser.roleId,10002)}"><a href="<%=request.getContextPath()%>/bacl_admin.do?comd=toAddUser" >增加管理用户</a> </c:if>
		
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=PayUserList"  method="post">
				
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;玩家名称：<input type="text" name="name" id="name" value="${name}"/>
						</select>&nbsp;&nbsp;
					所属渠道：<select id="channelId" name="channelId" >
							<option 
								<c:if test="${channelId == '-1'}">selected="selected"</c:if> 
									value="-1" >未选择
							</option>
							<c:forEach items="${channelList}" var="channel" varStatus="status" >
								<option 
									<c:if test="${channelId == channel.channelId}">selected="selected"</c:if> 
									value="${channel.channelId}" >${channel.channelName}
								</option>
							</c:forEach>
						</select>&nbsp;&nbsp;
						<input type="submit" value="查询" />
				</form>
		 
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 用户ID </td>
						<td align="center"> 用户名 </td>
						<td align="center"> 昵称 </td>
						<td align="center"> 游戏 </td>
						<td align="center"> 渠道 </td>
						<td align="center"> 创建时间 </td> 
						<td align="center"> 操作 </td>
					</tr>
				<c:if test="${uservo != null}">
					<c:forEach items="${uservo}" var="uservo">
						<tr> 
							<td align="center"> ${uservo.userId}</td>
							<td align="center"> ${uservo.name} </td>
							<td align="center"> ${uservo.nickname} </td>
							<td align="center"> ${uservo.gameId} </td>
							<td align="center"> ${uservo.channelId} </td>
							<td align="center">
							<c:if test="${my:hasPermission(backUser.roleId,224)}">
								<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=toEditUser&userId=${uservo.userId}&name=${uservo.name}&channelId=${uservo.channelId}&gameId=${uservo.gameId}" >修改</a>&nbsp;
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
	</body>
</html>
