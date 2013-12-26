<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
				if("" == jQuery.trim($("#oldPwd").val())){
			 		alert("请填写原来密码");
			 		$("#oldPwd").focus();
			 		return false;
		 		 }
				if($("#pwd").val().length  < 6 || $("#pwd").val().length >16 ){
			 		alert("请填写长度为6至16的密码");
			 		$("#pwd").focus();
			 		return false;
		 		 }
		 		if($("#pwd").val() != $("#rePwd").val()){
			 		alert("两次输入密码不一致");
			 		$("#pwd").focus();
			 		return false;
		 		 }
				return true;
			}
		</script>
	</head>
	<body class="main_body">
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n63" method="post" onsubmit="return checkData();" >
			<input type="hidden" name="roleName" id="roleName" />
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">

					<tr> 
						<td > 原密码 </td><td > <input id="oldPwd" name="oldPwd" type="passWord"  maxlength="16"/> </td>
					</tr>
					<tr> 
						<td > 新密码 </td><td > <input id="pwd" name="pwd" type="passWord" maxlength="16"/> </td>
					</tr>
					<tr> 
						<td > 确认密码 </td><td > <input id="rePwd" name="rePwd" type="passWord" maxlength="16"/> </td>
					</tr>
					
					<tr> 
						<td colspan="2" align="center" > <input  type="button"  value="取消" onclick="javascript:history.go(-1);" />&nbsp;&nbsp;<input  type="submit" value="修改" /> </td>
					</tr>
			</table>
		</form>
<table style="BORDER-COLLAPSE: collapse" height=40 cellPadding=1 width="80%" align=center  border=0 >
	<tr >  <font color="red" > ${hint}</font> </tr>
</table>
	</body>
</html>
