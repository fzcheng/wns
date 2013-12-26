<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my"  uri="/my" %>

<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
	<body class="main_body">
		<%
			String hint = (String) request.getAttribute("hint");
			if (hint != null) {
		%>
		<center>
			<font color="#FF0000"><%=hint%></font>
		</center>
		<%
			}
		%>
	<c:if test="${my:hasPermission(backUser.roleId,214) }"> <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=toAddChannel">新增渠道</a>&nbsp; </c:if>
		<form id="textform" name="textform" method="post">
			<table width="80%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td align="center">
						渠道ID
					</td>
					<td align="center">
						渠道名称
					</td>
				 	<td align="center">
						编 辑
					</td>
				</tr>
				<c:if test="${channelVOs != null}">
					<c:forEach items="${channelVOs}" var="channelVO">

						<tr>
							<td align="center">
								${channelVO.channelId}
							</td>
							<td align="center">
								${channelVO.channelName }
							</td>
							<td align="center">
								<a  href="<%=request.getContextPath()%>/admin/back_channel/editChannel.jsp?channelId=${channelVO.channelId}&channelName=${channelVO.channelName}">修改 </a>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=delChannel&channelId=${channelVO.channelId}">删除 </a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
