<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script language="JavaScript">
		var time = ${time};
		var t = setInterval(refresh,parseInt(time)*1000);
		function r_start(){
			time = $.trim($("#time").val());
			var reg = new RegExp("^[0-9]*$");
			if(time=='' || !reg.test(time)){
			    alert("请输入正确的秒数!");
			    return;
			}
		   	t = setInterval(refresh,parseInt(time)*1000);
		}
		function r_stop(){
		   	clearInterval(t);
		}
		
		function onclickBox(){
			if($("#checkId").attr("checked")==true){
				r_start();
			}else{
				r_stop();
			}
		}
		
		function refresh(){
			var chat = $.trim($("#chat").val());
			var time = $.trim($("#time").val());
			$("#chat_s").val(chat);
			$("#time_s").val(time);
			document.subForm.submit();
		}
		
		function reply(roleId,roleName){
			var chat = $.trim($("#chat").val());
			var time = $.trim($("#time").val());
			$("#chat_s").val(chat);
			$("#time_s").val(time);
			$("#roleId_s").val($.trim(roleId));
			$("#roleName_s").val($.trim(roleName));
			document.subForm.submit();
		}
		
		function checkRoleName(){
			var roleName = $.trim($("#roleName").val());
			if(roleName == null || roleName.length == 0){
				return false;
			}
			return true;
		}
		</script>
	</head>
<body class="main_body">	
<c:if test="${message != null && message != ''}">
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><td><label style="color: red">${message}</label></td></tr>
</table>
</c:if>
<br />
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td colspan="3" align="left">
	<form id="textform1" name="textform1" action="<%=request.getContextPath()%>/bacl_admin.do?comd=sendWhisper" method="post" onsubmit="return checkRoleName();">
	<input id="roleId" type="hidden" name="roleId" value="${roleId}"/>
	<input id="roleName" type="hidden" name="roleName" value="${roleName}"/>
	[${gameId}&nbsp;${gameName}]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You To [${roleName}]：<input id="chat" name="chat" type="text" value="${chat}" size="100"/>&nbsp;&nbsp;
		 <input type="submit" value="发送" />
	</form>
	</td>
	<td><input onclick="onclickBox()" id="checkId" name="check" type="checkbox" checked="checked" />自动刷新&nbsp;<input id="time" type="text" value="${time}" size="1"/>S</td>
	</tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="7" style="color:#DC143C;">玩家私聊信息</td></tr>
<tr >
<td align="center" width="5%">序号</td>
<td align="center">状态</td>
<td align="center">玩家ID</td>
<td align="center">玩家昵称</td>
<td align="center">聊天内容</td>
<td align="center">时间</td>
<td align="center">操作</td>
</tr>
<c:forEach items="${chatList}" var="chat" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >
<c:if test="${chat.type==1}">收到</c:if>
<c:if test="${chat.type==2}">发送</c:if>
</td>
<td align="center" >${chat.bRoleId}</td>
<td align="center" >${chat.bRoleName}</td>
<td align="left" style="word-break:break-all;padding-left: 3px">${chat.detail}</td>
<td align="center" >${chat.time}</td>
<td align="center" >
<c:if test="${chat.type==1}"><input onclick="reply('${chat.bRoleId}','${chat.bRoleName}')" value="回复" type="button" /></c:if>
&nbsp;
</td>
</tr>
</c:forEach>
</table>
<form id="subForm" name="subForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=whisper" method="post" >
<input id="time_s" name="time" type="hidden"/>
<input id="roleId_s" name="roleId" type="hidden" value="${roleId}"/>
<input id="roleName_s" name="roleName" type="hidden" value="${roleName}"/>
<input id="chat_s" name="chat" type="hidden" />
</form>

</body>
</html>