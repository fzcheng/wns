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
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=editChannel" method="post" >
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 渠道ID：<input id="channelId" name="channelId" type="text" maxlength="10" onblur="checkchannelId(this.value)" onfocus="hideDiv()"  value="<%=request.getParameter("channelId") %>"  readonly="readonly"/> <div id= "tips" style="color:red" > </div></td>
					</tr>
					<tr> 
						<td align="center"> 名称 ：<input id="channelName" name="channelName" type="text"   value="<%=new String(request.getParameter("channelName").getBytes("iso8859-1"),"utf-8") %>"/> </td>
					</tr>
					<tr> 
						<td colspan="2" align="center" >
						<input id="saveButton"  type="submit" value="修改" />&nbsp;&nbsp;
						<input type="button" value="取消" onclick="javascript:history.go(-1);" />
						</td>
					</tr>
			</table>
		</form>
	</body>
</html>
