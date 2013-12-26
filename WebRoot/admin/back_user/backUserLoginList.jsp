<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
	<body class="main_body">
	    <%
	    String hint = (String)request.getAttribute("hint");
	     if(hint != null){
	     %>
	     <center><font color="#FF0000"><%=hint %></font></center>
	     <%
	     }
	     %>
	     
		<form id="textform" name="textform"  method="post">
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 序号 </td>
						<td align="center"> 用户名 </td>
						<td align="center"> 状态 </td>
						<td align="center"> 角色名称 </td>
						<td align="center"> 登入次数 </td>
						<td align="center"> 最后登入时间 </td>
						<td align="center"> 创建时间 </td> 
					</tr>
				<c:if test="${backUserVOs != null}">
					<c:forEach items="${backUserVOs}" var="backUserVO">
					
						<tr> 
							<td align="center"> ${backUserVO.userId }</td>
							<td align="center"> ${backUserVO.userName } </td>
							<td align="center"> <c:if test="${0 == backUserVO.userState }">启用 </c:if><c:if test="${1 == backUserVO.userState }">禁用</c:if> </td>
							<td align="center"> ${backUserVO.roleName}&nbsp;</td>
							<td align="center"> ${backUserVO.loginCount } </td>
							<td align="center"> ${backUserVO.lastLoginTimeStr } &nbsp; </td>
							<td align="center"> ${backUserVO.createTime } </td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
