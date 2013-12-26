<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="cn.usercenter.analyse.LoginStatDayDetailVO"%>
<%@page import="admins.ben.BackRoleGameDetailVO"%>
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
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
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户总汇&nbsp;&nbsp;
	      
		<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr> 
					<td align="center" colspan="13" >
					<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n1_1"  method="post">
				
					
					分区选择：<select id="sectionId" name="sectionId" >
							<option 
								 未选择
							</option>
						 
						</select>&nbsp;&nbsp;
					渠道：<select id="channelId" name="channelId" >
							<option 
								<c:if test="${channelId == '-1'}">selected="selected"</c:if> 
									value="-1" >未选择
							</option>
							<c:forEach items="${channelList}" var="channel" varStatus="status" >
								<option 
									<c:if test="${channelId == channel.channelId}">selected="selected"</c:if> 
									value="${channel.channelId}" >${channel.channelName}-${channel.channelId}
								</option>
							</c:forEach>
						</select>&nbsp;&nbsp;
					开始时间：<input value="${beginTime}" name="beginTime" size="12" type="text" readonly="readonly" onClick="popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">&nbsp;&nbsp;
					结束时间：<input value="${endTime}" name="endTime" size="12" type="text" readonly="readonly" onClick="popUpCalendar(this,textform.endTime,'yyyy-mm-dd')">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="查询" />
				</form>
				</td>
					</tr>
	<!-- 								 
	int platId;
	int channelId;
	int sectionId;
	int userId;
	int money;
	int moneytype;
	int rechargeTime;
	int paycount;//充值次数
	int paySumMoney;
	String channelName;
	 -->
					<tr> 
						<td align="center"> 渠道	 </td>
						<td align="center"> 分区  </td>
						<td align="center"> 充值次数 </td>
						<td align="center"> 充值总额	</td>
						<td align="center"> 时间</td>
					</tr>
					<c:forEach items="${totalGamepayrecord}" var="totalp">
						<tr> 
							<td align="center"> ${totalp.channelName} </td>
							<td align="center"> ${totalp.sectionId} </td>
							<td align="center"> ${totalp.paycount} </td>
							<td align="center"> ${totalp.paySumMoney} </td>
						</tr>
					</c:forEach>
				 
			</table>
	</body>
</html>
