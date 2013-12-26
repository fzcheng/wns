<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="cn.usercenter.analyse.LoginStatDayDetailVO"%>
<%@page import="admins.ben.BackRoleGameDetailVO"%>
<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@ taglib prefix="my" uri="/my"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
	<body class="main_body">
		<center>
			<font color="#FF0000">${message}</font>
		</center>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户总汇&nbsp;&nbsp;

		<table width="90%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center" colspan="13">
					<form id="textform" name="textform"
						action="<%=request.getContextPath()%>/bacl_admin.do?comd=n1_1"
						method="post">

						游戏：
						<select id="gameIndex" name="gameId">
							<option <c:if test="${gameId == '-1'}">selected="selected"</c:if>
								value="-1">
								未选择
							</option>
							<c:forEach items="${gameList}" var="game" varStatus="status">
								<option
									<c:if test="${gameId == game.gameId}">selected="selected"</c:if>
									value="${game.gameId}">
									${game.gameName}
								</option>
							</c:forEach>
						</select>
						&nbsp;&nbsp; 渠道：
						<select id="channelIndex" name="channelId">
							<option
								<c:if test="${channelId == '-1'}">selected="selected"</c:if>
								value="-1">
								未选择
							</option>
							<c:forEach items="${channelList}" var="channel"
								varStatus="status">
								<option
									<c:if test="${channelId == channel.channelId}">selected="selected"</c:if>
									value="${channel.channelId}">
									${channel.channelName}-${channel.channelId}
								</option>
							</c:forEach>
						</select>
						&nbsp;&nbsp; 开始时间：
						<input value="${beginTime}" name="beginTime" size="12" type="text" id="beginTime"
							readonly="readonly"
							onClick="popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">
						&nbsp;&nbsp; 结束时间：
						<input value="${endTime}" name="endTime" size="12" type="text" id="endTime"
							readonly="readonly"
							onClick=
	popUpCalendar(this, textform.endTime, 'yyyy-mm-dd');
>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" value="查询" />
					</form>
				</td>
			</tr>
			<tr>
				<td align="center" width="10%">
					日期
				</td>
				<td align="center">
					游戏
				</td>
				<td align="center">
					渠道
				</td>
				<td align="center">
					新增启动用户
				</td>
				<td align="center">
					新增启动用户(渠道)
				</td>
				<td align="center">
					新增注册用户
				</td>
				<td align="center">
					新增注册用户(渠道)
				</td>
				<td align="center">
					操作
				</td>
			</tr>

			<% 
					List<LoginStatDayDetailVO> result = (List<LoginStatDayDetailVO>)request.getAttribute("loginstatdaydetail");
					if(result != null && result.size() >0){
						int showscount = 0,showregcount = 0; 
						int snewcount = 0, effregcount = 0;
						int size = result.size();
						
						for(int i = 0; i < size; i++){
							LoginStatDayDetailVO vo = result.get(i);
							
							snewcount += vo.getSnewcount();
							showscount += vo.getShowscount();
							effregcount += vo.getEffregcount();
							showregcount += vo.getShowregcount();
					%>
			<tr>
				<td align="center"><%=vo.getDate()%></td>
				<td align="center"><%=vo.getGameName()%></td>
				<td align="center"><%=vo.getChannelName()%></td>
				<td align="center"><%=vo.getSnewcount()%></td>
				<td align="center">
					<input type="text" value="<%=vo.getShowscount()%>"
						id="sn<%=vo.getId()%>" />
				</td>
				<td align="center"><%=vo.getEffregcount()%></td>
				<td align="center">
					<input type="text" value="<%=vo.getShowregcount()%>"
						id="sh<%=vo.getId()%>" />
				</td>
				<td align="center">
					<input type="button" onclick="editDayLogin('<%=vo.getId()%>','<%=vo.getShowscount()%>','<%=vo.getShowregcount()%>')"
						value="修改" />
				</td>
			</tr>
			<%}%>
			<tr>
				<td align="center" colspan="3">
					<strong>合计</strong>
				</td>
				<td align="center">
					<strong><%=snewcount%></strong>
				</td>
				<td align="center">
					<strong><%=showscount%></strong>
				</td>
				<td align="center">
					<strong><%=effregcount%></strong>
				</td>
				<td align="center">
					<strong><%=showregcount%></strong>
				</td>
			</tr>
			<%}%>
		</table>


		<form id="editForm" name="editForm"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=editDayLogin"
			method="post">
			<input id="sh_" name="sh_" type="hidden" />
			<input id="sn_" name="sn_" type="hidden" />
			<input id="id_" name="id_" type="hidden" />
			<input id="gameId_" name="gameId_" type="hidden" />
			<input id="channelId_" name="channelId_" type="hidden" />
			<input id="endTime_" name="endTime_" type="hidden" />
			<input id="beginTime_" name="beginTime_" type="hidden" />
			
		</form>

	<script type="text/javascript"><!--
	function editDayLogin(itemId,snn,shh) {
		var id = itemId;
		var sn = $.trim($("#sn" + itemId).val());
		var sh = $.trim($("#sh" + itemId).val());
		
		 if(isNaN(sn)||isNaN(sh)){  
			 alert("请输入数字");
			 $("#sn" + itemId).val(snn);
			 $("#sh" + itemId).val(shh)
			 return false;
		 }
		//else if(sh>shh||sn>snn){
		//	alert("修改的注册人数不能大于当前的注册人数");
		//	$("#sn" + itemId).val(snn);
		//	$("#sh" + itemId).val(shh)
		//	return false;
		//}
	 
		
		var gameId=$.trim($("#gameIndex").val());
		var channelId=$.trim($("#channelIndex").val());
		var beginTime=$("#beginTime").val();
		var endTime=$("#endTime").val();
		//alert("gameId:"+gameId+" channelId"+channelId+"beginTime:"+beginTime);
		$("#sn_").val(sn);
		$("#sh_").val(sh);
		$("#id_").val(id);
		$("#gameId_").val(gameId);
		$("#channelId_").val(channelId);
		$("#endTime_").val(endTime);
		$("#beginTime_").val(beginTime);
		
		editForm.submit();
	}
</script>
	</body>
</html>
