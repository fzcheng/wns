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
	<c:if test="${my:hasPermission(backUser.roleId,214) }"> <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=ToAddGameCh">新增游戏</a>&nbsp; </c:if>
		<form id="textform" name="textform" method="post">
			<table width="80%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td align="center">
						游戏ID
					</td>
					<td align="center">
						游戏名称
					</td>
					<td align="center">
						渠道ID
					</td>
					<td align="center">
						渠道名称
					</td>
					<td align="center">
						统计类别
					</td>
					<td align="center">
						 统计扣量比
					</td>
				 	<td align="center">
						编 辑
					</td>
				</tr>
				<c:if test="${gameChannel != null}">
					<c:forEach items="${gameChannel}" var="gh">

						<tr>
							<td align="center">
								${gh.gameId}
							</td>
								<td align="center">
								${gh.gameName}
							</td>
							<td align="center">
								${gh.channelId}
							</td>
						 	<td align="center">
								${gh.channelName}
							</td>
							<td align="center">
								${gh.stattype }
							</td>
							<td align="center">
								${gh.statper }
							</td>
							
							<td align="center">
								 <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=ToEditGameCh&gameId=${gh.gameId}&channelId=${gh.channelId}&stattype=${gh.stattype }&statper=${gh.statper }&id=${gh.id }">修改 </a>&nbsp;&nbsp;
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
