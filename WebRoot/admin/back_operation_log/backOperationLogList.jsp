<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  		
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n1_4" >
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="5" > 模糊操作描叙查询<input name="operationDsc" type="text" value="${operationDsc }" maxlength="20" />  <input type="submit" value="查询" /> </td>
					</tr>
					<tr> 
						<td align="center"> 序号 </td>
						<td align="center"> 操作者 </td>
						<td align="center"> 操作描叙 </td>
						<td align="center"> 创建时间 </td> 
					</tr>
					<c:if test="${pageI.list != null}">
						<c:forEach items="${pageI.list}" var="backOperationLogVO">
							<tr> 
								<td align="center"> ${backOperationLogVO.logId } </td>
								<td align="center"> ${backOperationLogVO.operator } </td>
								<td align="center">${backOperationLogVO.operationDsc } </td>
								<td align="center"> ${backOperationLogVO.createTime } </td>
							</tr>
						</c:forEach>
					</c:if>
			</table>
			</form>
			<table width="80%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">
					${pageI.departWeb }
					</td>
				</tr>
			</table>
	</body>
</html>
