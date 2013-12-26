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
	      	    <div  align="left"> 
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;每月汇总充值金额&nbsp;&nbsp;
	     </div>
	      
		<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr> 
					<td align="center" colspan="13" >
					<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=UTotalGamepayMonth"  method="post">
				
					
				分区：<select id="sectionId" name="sectionId" >
							<option 
								<c:if test="${sectionId == '-1'}">selected="selected"</c:if> 
									value="-1" >未选择
							</option>
							<c:forEach items="${sectionList}" var="sect" varStatus="status" >
								<option 
									<c:if test="${sectionId == sect.sectionId}">selected="selected"</c:if> 
									value="${sect.sectionId}" >${sect.sectionName}-${sect.sectionId}
								</option>
							</c:forEach>
						</select>&nbsp;&nbsp;
					渠道：<select id="channelId" name="channelId" >
							<option 
								<c:if test="${channelId == '-1'}">selected="selected"</c:if> 
									value="-1" >未选择
							</option>
							<c:forEach items="${channelList}" var="channel" varStatus="status" >
								<option 
									<c:if test="${channelId == channel.channelId}">selected="selected"</c:if> 
									value="${channel.channelId}" >${channel.gameName}-${channel.channelName}
								</option>
							</c:forEach>
						</select>&nbsp;&nbsp;
					开始时间：<input value="${beginTime}" name="beginTime" size="12" type="text" readonly="readonly" onClick="popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">&nbsp;&nbsp;
					结束时间：<input value="${endTime}" name="endTime" size="12" type="text" readonly="readonly" onClick="popUpCalendar(this,textform.endTime,'yyyy-mm-dd')">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="查询" />
				</form>
				</td>
					</tr>
					<tr> 
						<td align="center"> 渠道	 </td>
						<td align="center"> 分区  </td>
						<td align="center"> 充值次数 </td>
						<td align="center"> 充值总额	</td>
						<td align="center"> 时间</td>
					</tr>
					<c:forEach items="${totalGamepayMonth}" var="totalp">
					
					
					<c:if test="${totalp.channelId  eq '0'}">
						<tr style="background-color: #ccc"> 
							<td align="center"> ${totalp.channelName} </td>
							<td align="center"> ${totalp.sectionName} </td>
							<td align="center"> ${totalp.payCount} </td>
							<td align="center"> ${totalp.rechargeCount} </td>
							<td align="center"> ${totalp.countDate} </td>
						</tr>
						</c:if>
						<c:if test="${totalp.channelId  ne '0'}">
							<tr> 
								<td align="center"> ${totalp.channelName} </td>
								<td align="center"> ${totalp.sectionName} </td>
								<td align="center"> ${totalp.payCount} </td>
								<td align="center"> ${totalp.rechargeCount} </td>
								<td align="center"> ${totalp.countDate} </td>
							</tr>
						
					</c:if>
					 
				 </c:forEach>
			</table>
			<script type="text/javascript">
			
				function Hand(){
				
				 window.location.href="<%=request.getContextPath()%>/bacl_admin.do?comd=HandTotalGamepayMonth";
				}
			</script>
	</body>
</html>
