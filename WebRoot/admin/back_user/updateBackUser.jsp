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
			function checkData(){
				if("" == jQuery.trim($("#userName").val())){
			 		alert("请填写用户名");
			 		$("#userName").focus();
			 		return false;
		 		 }
				if($("#userPwd").val().length  < 6 || $("#userPwd").val().length >16 ){
			 		alert("请填写长度为6至16的密码");
			 		$("#userPwd").focus();
			 		return false;
		 		 }
		 		if($("#userPwd").val() != $("#rePwd").val()){
			 		alert("两次输入密码不一致");
			 		$("#userPwd").focus();
			 		return false;
		 		 }
				var roleName = $("#role>option:selected").get(0).text;
				$("#roleName").attr("value",roleName);
				return true;
			}
			$(document).ready(function(){
				 jQuery("#role").get(0).value = '${backUserVO.roleId}';  
				 jQuery("#userStateId").get(0).value = '${backUserVO.userState}';  
				 
			});
			
					//ajax 检查用户名
		   function checkUserName(userName){
		      if(jQuery.trim(userName) != ""){
		 	   
		      var oldName = $("#oldUserName").val();
		      if(oldName == userName){
		    	  return true;
		      }
				$.ajax({
					url : "bacl_admin.do?comd=checkUserName",
					type : "POST",
					data : {userName:userName},
					dataType : "html",
					success : function(rs){	
						if(rs == "false"){
								$("#tips").show();
								document.getElementById("tips").innerHTML = "用户名已存在";
								document.getElementById("updateButton").disabled = true;
							}else{
								document.getElementById("updateButton").disabled = false;
							}
						}
				});
		      }
		  	 }
					
	function hideDiv(){
		$("#tips").hide();
	}
		</script>
	</head>
	<body class="main_body">
	    <%  
	     String hint = (String)request.getAttribute("hint");
	     if(hint != null){  
	     %>
	     <center><%=hint %></center>
	     <%
	     }
	     %>
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=editBackUser" method="post" onsubmit="return checkData();" >
			<input type="hidden" name="userId" value="${backUserVO.userId}" />
			<input type="hidden" id="oldUserName" value="${backUserVO.userName}" />
			<input type="hidden" name="roleName" id="roleName" />
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 用户名 </td><td align="center"> <input id="userName" name="userName" type="text" value="${backUserVO.userName }" maxlength="10" onfocus="hideDiv()" onblur="checkUserName(this.value)" /> <div id= "tips" style="color:red" > </div></td>
					</tr>
					<tr> 
						<td align="center"> 密码 </td><td align="center"> <input id="userPwd" name="userPwd" type="passWord" value="${backUserVO.userPwd }" maxlength="16" /> </td>
					</tr>
					<tr> 
						<td align="center"> 确认密码 </td><td align="center"> <input id="rePwd" name="rePwd" type="passWord" value="${backUserVO.userPwd }" maxlength="16" /> </td>
					</tr>
					<tr> 
						<td align="center"> 游戏ID </td><td align="center"> <input name="gameId" type="text" value="${backUserVO.gameId}" maxlength="16" /> </td>
					</tr><!--
					<tr> 
						<td align="center">能操作的渠道</td>
						<td align="center">
							<select id="channel" name="channelId" >
								<option <c:if test="${backUserVO.gameId == -99}">selected="selected"</c:if> value="-99">&nbsp;</option>
								<option <c:if test="${backUserVO.gameId == -1}">selected="selected"</c:if> value="-1">全部</option>
								<c:forEach var="chann" items="${listChannel}">
								<option <c:if test="${backUserVO.gameId == chann.channelId}">selected="selected"</c:if> value="${chann.channelId}" >${chann.channelName}</option> 
								</c:forEach>
							 </select>  
						</td>
					</tr>
					--><c:if test="${backUserVO.roleId != 0}">
					<tr> 
						<td align="center"> 角色 </td>
						<td align="center"> 
							<select id="role" name="roleId" >
								<c:forEach items="${roleList}" var="backRoleVO">
									<option  value="${backRoleVO.roleId}" >${backRoleVO.roleName}</option> 
								</c:forEach>
							 </select> 
						</td>
					</tr>
					<tr> 
						<td align="center"> 状态 </td>
						<td align="center">
							<select id="userStateId" name="userState" >
								<option  value="0" >启用</option> 
								<option  value="1" >禁用</option>
							 </select>  
						</td>
					</tr>
					</c:if>
					<tr> 
						<td colspan="2" align="center" ><input id="updateButton" type="submit" value="修改用户信息" />&nbsp;&nbsp;<input  type="button"  value="取消" onclick="javascript:history.go(-1);" /></td>
					</tr>
			</table>
		</form>
	</body>
</html>
