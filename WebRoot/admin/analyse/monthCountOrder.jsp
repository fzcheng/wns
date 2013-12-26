<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="my"  uri="/my" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
	<body class="main_body">
	  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月运营统计渠道排行
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=monthCountOrder">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="12" > 
						统计日期：<input value="${date}" name="date" size="12" type="text" readonly="readonly" onClick="popUpCalendar(this,textform.date,'yyyy-mm-dd')">&nbsp;&nbsp;
						排行类别：
						<select name="colName">
							<option <c:if test="${colName eq 'rechargeCount'}">selected="selected"</c:if> value="rechargeCount">充值金额</option>
						</select>
						<input type="submit" value="查询" />
						</td>
					</tr>
					<tr> 
						<td align="center"> 日期 </td>
						<td align="center"> 渠道 </td>
						<td align="center"> 排行数据 </td>
					</tr>

					<c:forEach items="${result}" var="orderVO">
						<tr> 
							<td align="center"> ${orderVO.countDate} </td>
							<td align="center"> ${orderVO.channelName} </td>
							<td align="center"> ${orderVO.orderNum} </td>
						</tr>
					</c:forEach>
			</table>
		</form>
	</body>
</html>
