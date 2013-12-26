<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="my"  uri="/my" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
	<body class="main_body">
	    <%
	    String hint = (String)request.getAttribute("hint");
	     if(hint != null){
	     %>
	     <center><font color="#FF0000"><%=hint%></font></center>
	     <%
	     }
	     %>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在线统计
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath() %>/bacl_admin.do?comd=cole3">
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="10" > 输入查询日期：<input name="checkTime" size="12" type="text" readonly="yes"
							onClick="JavaScript:popUpCalendar(this,textform.checkTime,'yyyy-mm-dd 00:00:00')">
						&nbsp;&nbsp;
						&nbsp;&nbsp; <input type="submit" value="查询" /> </td>
					</tr>
					<tr> 
						<td align="center"> 序号 </td>
						<td align="center"> 日期 </td>
						<td align="center"> 人数 </td>
					</tr>
				<c:if test="${dayonlinesummarys != null}">
					<c:forEach items="${dayonlinesummarys}" var="dayOnlinesummaryVO" varStatus="status">
						<tr> 
							<td align="center"> ${status.index + 1 } </td>
							<td align="center"> ${dayOnlinesummaryVO.dtime} </td>
							<td align="center"> ${dayOnlinesummaryVO.num} </td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
