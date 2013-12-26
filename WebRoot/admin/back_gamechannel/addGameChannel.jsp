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
		<script type="text/javascript">
			//ajax 检查用户名
		   function checkchannelId(channelId){
		      if(jQuery.trim(channelId) != ""){
				$.ajax({
					url : "bacl_admin.do?comd=checkChannelId",
					type : "POST",
					data : {channelId:channelId},
					dataType : "html",
					success : function(rs){	
							if($.trim(rs) == "false"){
								$("#tips").show();
								document.getElementById("tips").innerHTML = "渠道号已经被占用";
								document.getElementById("saveButton").disabled = true;
							}else{
								document.getElementById("saveButton").disabled = false;
							}
						}
				});

		 		 }
		  	 }
		   function hideDiv(){
				$("#tips").hide();
			}
			
			function setHidderChann(){
				var channelName = $("#channelId option:selected").text();
				$("#channelName").val(channelName);
		}
		function onlod(){
			setHidderChann();
		}
			
		</script>
	</head>
	<body class="main_body" onload="onlod()">
	    <%  
	     String hint = (String)request.getAttribute("hint"); 
	     if(hint != null){  
	     %>
	     <center><%=hint %></center>
	     <%
	     }
	     %>
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=AddGameCh" method="post" >
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					
					<tr> 
						<td align="center"> 游戏：</td><td>
							<select name="gameId" id="gameId">
								<c:forEach items="${gamevo}" var="gamevo" varStatus="status" >
									<option 
										value="${gamevo.gameId}" >${gamevo.gameName}
									</option>
								</c:forEach>
							
							</select>
						</td>
					</tr>
					<tr> 
						<td align="center"> 渠道：</td><td>
								<select name="channelId" id="channelId" onchange="setHidderChann()">
								<c:forEach items="${channelList}" var="channelList" varStatus="status" >
									<option 
										value="${channelList.channelId}" >${channelList.channelName}
									</option>
								</c:forEach>
							
							</select>
							
						</td>
					</tr>
					<tr> 
						<td align="center"> 统计类型 ：</td><td>
								<select name="stattype" id="stattype">
									<option 
										value="1" > 注册量
									</option>
										<option 
										value="2" > 启动量
									</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center">
							统计扣量比: 
						</td>
						<td><input type="text" id="statper" name="statper" /></td>
					</tr>
					<tr> 
						<td colspan="2" align="center" >
						<input id="saveButton"  type="submit" value="添加" />&nbsp;&nbsp;
						<input type="button" value="取消" onclick="javascript:history.go(-1);" />
						</td>
					</tr>
			</table>
			<input type="hidden" name="channelName" id="channelName"/>
		</form>
	</body>
</html>
