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
					<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n2_1"  method="post">
					
					渠道：<select id="channelId" name="selectId" >
							<option 
								<c:if test="${selectId == '-1'}">selected="selected"</c:if> 
									value="-1" >汇总数据
							</option>
							<c:forEach items="${backrolegameList}" var="channels" varStatus="status" >
								<option 
									<c:if test="${selectId == channels.id}">selected="selected"</c:if> 
									value="${channels.id}" >${channels.gameName}-${channels.channelName}
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
						<td align="center" width="10%"> 日期	 </td>
						<td align="center"> 游戏	 </td>
						<td align="center"> 渠道	 </td>
						<td align="center"> 新增启动用户  </td>
						<td align="center"> 新增注册用户	</td>
					</tr>
				
					<% 
					List<LoginStatDayDetailVO> result = (List<LoginStatDayDetailVO>)request.getAttribute("loginstatdaydetail");
					if(result != null && result.size() >0){
						int scount = 0,regcount = 0; 
						int size = result.size();
						
						for(int i = 0; i < size; i++){
							LoginStatDayDetailVO vo = result.get(i);
							
							if(vo.getStattype() == 2)
							{
								scount += vo.getShowscount();
							}
							else if(vo.getStattype() == 1)
							{
								regcount += vo.getShowregcount();
							}
					%>
						<tr> 
							<td align="center"><%=vo.getDate()%></td>
							<td align="center"><%=vo.getGameName()%></td>
							<td align="center"><%=vo.getChannelName()%></td>
							<td align="center"><%=(vo.getStattype() == 2)?vo.getShowscount():0%></td> 
							<td align="center"><%=(vo.getStattype() == 1)?vo.getShowregcount():0%></td>
						</tr>
					<%}%>
					<tr> 
						<td align="center" colspan="3"><strong>合计</strong></td>
						<td align="center"><strong><%=scount%></strong></td> 
						<td align="center"><strong><%=regcount%></strong></td>
						</tr>
			  <%}%>
			</table>
	</body>
</html>
