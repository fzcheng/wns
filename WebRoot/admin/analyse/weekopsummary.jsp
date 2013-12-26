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
	     <center><font color="#FF0000">${message}</font></center>

	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周运营汇总&nbsp;&nbsp;
	    <input type="button" value="刷新上周数据" onclick="window.location='<%=request.getContextPath()%>/bacl_admin.do?comd=countWeek&date=1'">
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=cole6">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="11" >
						<c:if test="${channelId == -1}">
							渠道：<select id="channelId" name="selectId" >
									<option <c:if test="${selectId == '-1'}">selected="selected"</c:if> value="-1" >汇总数据</option>
									<c:if test="${channels != null }">
									<c:forEach items="${channels}" var="channels" varStatus="status" >
									<option <c:if test="${selectId == channels.channelId}">selected="selected"</c:if> value="${channels.channelId }" >${channels.channelName }</option>
									</c:forEach>
									</c:if> 
								 </select>
						</c:if>
							开始时间：<input value="${beginTime}" name="beginTime" size="12" type="text" readonly="readonly" onClick="popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">&nbsp;&nbsp;
							结束时间：<input value="${endTime}" name="endTime" size="12" type="text" readonly="readonly" onClick="popUpCalendar(this,textform.endTime,'yyyy-mm-dd')">&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="submit" value="查询" />
						</td>
					</tr>
					<tr> 
						
						<td align="center"> 日期 </td>
						<td align="center"> 渠道 </td>
						<td align="center"> 登录用户数 </td>
						<td align="center"> 本周充值 </td>
						<td align="center"> 充值人数 </td>
						<td align="center"> 本周消费 </td>
						<td align="center"> 消费人数 </td>
						<td align="center"> 周充值渗透率 </td>
						<td align="center"> 周消费渗透率 </td>
						<td align="center"> 周充值ARPU值 </td>
						<td align="center"> 周消费ARPU值 </td>
						
					</tr>
				<c:if test="${weekopsummarys != null}">
					<c:forEach items="${weekopsummarys}" var="weekOpsummaryVO">
						<tr> 
							<td align="center"> ${weekOpsummaryVO.countDate} </td>
							<td align="center"> ${weekOpsummaryVO.channelName} </td>
							<td align="center"> ${weekOpsummaryVO.userCount} </td>
							<td align="center"> ${weekOpsummaryVO.rechargeCount}元</td>
							<td align="center"> ${weekOpsummaryVO.rechargeUserCount} </td> 
							<td align="center"> ${weekOpsummaryVO.payCount} </td>
							<td align="center"> ${weekOpsummaryVO.payUserCount} </td>
							<td align="center"> <fmt:formatNumber value="${weekOpsummaryVO.rechargeStl }" type="percent" pattern="#0.00%" /></td>
							<td align="center"> <fmt:formatNumber value="${weekOpsummaryVO.payStl }" type="percent" pattern="#0.00%" /></td>
							<td align="center"> <fmt:formatNumber value="${weekOpsummaryVO.rechargeArpu }" type="number" maxFractionDigits="3" /></td>
							<td align="center"> <fmt:formatNumber value="${weekOpsummaryVO.payArpu }" type="number" maxFractionDigits="3" /></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
