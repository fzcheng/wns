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
	    <%
	    String hint = (String)request.getAttribute("hint");
	     if(hint != null){
	     %>
	     <center><font color="#FF0000"><%=hint%></font></center>
	     <%
	     }
	     %>
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;消费统计
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=cole5">
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr> 
					<td align="center" colspan="50" > 
						道具：<select id="itemId" name="selectId" >
								<option value="" >全部</option>
								<c:if test="${items != null }">
								<c:forEach items="${items}" var="items" varStatus="status" >
								<option <c:if test="${items.id == selectId}">selected="selected"</c:if> value="${items.id }" >${items.name }</option>
								</c:forEach>
								</c:if> 
							 </select> &nbsp;&nbsp;
						开始时间：<input value="${beginTime}" name="beginTime" size="12" type="text" readonly="readonly" onClick="popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">&nbsp;&nbsp;
						结束时间：<input value="${endTime}" name="endTime" size="12" type="text" readonly="readonly" onClick="popUpCalendar(this,textform.endTime,'yyyy-mm-dd')">&nbsp;&nbsp;
						<input type="submit" value="查询" />
					</td>
					</tr>
					<tr> 
						<td align="center"> 日期	 </td>
						<td align="center"> 道具名称	 </td>
						<td align="center"> 购买数量 </td>
						<td align="center"> 购买人数	</td> 
						<td align="center"> 消费奖励点 </td>
					</tr>
				<c:if test="${dcc != null}">
					<c:forEach items="${dcc}" var="dcc">
						<tr> 
							<td align="center">${dcc.countDate}&nbsp;</td>
							<td align="center">${dcc.propsName}&nbsp;</td>
							<td align="center">${dcc.orderCount}&nbsp;</td> 
							<td align="center">${dcc.orderUserCount}&nbsp;</td>
							<td align="center">${dcc.consumereMoney}&nbsp;</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
