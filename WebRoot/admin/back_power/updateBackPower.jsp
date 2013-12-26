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
				if("" == jQuery.trim($("#powerName").val())){
			 		alert("请填写权限名");
			 		$("#powerName").focus();
			 		return false;
		 		 }
				return true;
			}
			$(document).ready(function(){
				 jQuery("#parent").get(0).value = '${backPowerVO.parentId}';  
			});
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
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n51" method="post" onsubmit="return checkData();" >
			<input type="hidden" name="powerId" value="${backPowerVO.powerId }" />
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 权限名称 </td><td align="center"> <input id="powerName" name="powerName" type="text" value="${backPowerVO.powerName }" maxlength="20" /> </td>
					</tr>
					<tr> 
						<td align="center"> 权限地址 </td><td align="center"> <input id="linkUrl" name="linkUrl" type="text" value="${backPowerVO.linkUrl }" maxlength="100" /> </td>
					</tr>
					<tr> 
						<td align="center"> 父类权限 </td>
						<td align="center"> 
							<select id="parent" name="parentId" >
								<option  value="0" >顶级模块</option> 
								<c:forEach items="${backPowerVOs}" var="backPower">
									<option  value="${backPower.powerId }" >${backPower.powerName }</option> 
								</c:forEach>
							 </select> 
						</td>
					</tr>
					<tr> 
						<td align="center"> 权限描叙 </td><td align="center"> <input id="powerDsc" name="powerDsc" type="text" value="${backPowerVO.powerDsc }" maxlength="50"  /> </td>
					</tr>
					<tr> 
						<td colspan="2" align="center" ><input  type="button"  value="取消" onclick="javascript:history.go(-1);" />&nbsp;&nbsp; <input  type="submit" value="修改权限信息" /> </td>
					</tr>
			</table>
		</form>
	</body>
</html>
