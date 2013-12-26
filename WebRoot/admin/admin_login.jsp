<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%
 	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("utf-8"); 
 %>
<html>
	<head>
		<title><bean:message key="index.jsp.welcome"/><bean:message key="index.jsp.tip"/></title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			  window.onload= function(){
			    var typeValue = document.getElementById("selectType").innerHTML;  
			    document.getElementById("language").selectedIndex = typeValue;
			   }     
		</script>
		<script language="JavaScript"> 
		
		if (window != top) {
			top.location.href = location.href; 
		}
			
		//function chekcLogin(n,p){
		//	var userName  = jQuery.trim($("#name").val());
		//	var password  = jQuery.trim($("#paw").val());
		//	
		//	if("0" != n){
		//		userName = n;
		//	}
		//	if("0" != p){
		//		password = p;
		//	}
		//	
		//	if("" == userName){
		// 		alert("请填写用户名");
		// 		$("#name").focus();
		// 		return false;
	 	//	 }
		//	if(password.length  < 6 || password.length > 16){
		// 		alert("请填写长度为6至16的密码");
		// 		$("#paw").focus();
		// 		return false;
	 	//	 }
		//	
		//	//var fenqu = jQuery.trim($("#fenqu").val());
		//	var url = "http://" + request.getContextPath() + "/bacl_admin.do?comd=n2";
		//	
		//	document.workForm.action = url;
		//	document.workForm.submit();
		//}

	</script>
	</head>
	<body>
		<!-- <form id="workForm" name="workForm" action="#" method="post"> -->
		<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/bacl_admin.do?comd=n2">
			<table width="294" height="85" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="25" colspan="2" align="center">
						<font color="#FF0000">${hint }</font>&nbsp;
					</td>
				</tr>
				<tr>
					<td width="68" height="25" align="left">
						<bean:message key="index.jsp.name" />
					</td>
					<td width="210" height="25">
						<input type="text" id="name" name="name" maxlength="10" />
					</td>
				</tr>
				<tr>
					<td height="25" align="left">
						<bean:message key="index.jsp.pass" />
					</td>
					<td height="25">
						<input type="passWord" id="paw" name="paw" maxlength="16" />
					</td>
				</tr>
				
				<tr>
					<td height="25" colspan="2" align="center">
						<!-- <input type="button" name="button" onclick="chekcLogin('0','0')"
							value="登陆" /> -->
						<input type="submit" name="Submit" value="登陆" />
					</td>
				</tr>
			</table>
		</form>
		
		<table>
		</table>
		</from>
	</body>
</html>
