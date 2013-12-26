<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function checkData(){
				if("" == jQuery.trim($("#roleName").val())){
			 		alert("请填写角色名称");
			 		$("#roleName").focus();
			 		return false;
		 		 }
				return true;
			}
		</script>
	</head>
	<body class="main_body">
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=editRoleInfo" method="post" onsubmit="return checkData();" >
			<input type="hidden" name="roleId" value="${role.roleId}" />
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 角色名称 </td><td align="center"> <input id="roleName" name="roleName" type="text" value="${role.roleName}" maxlength="20"   /> </td>
					</tr>
					<tr> 
						<td align="center"> 角色描叙 </td><td align="center"> <input id="roleDsc" name="roleDsc" type="text" value="${role.roleDsc}" maxlength="50"   /> </td>
					</tr>
					<tr> 
						<td colspan="2" align="center" >
							<input  type="submit" value="修改角色信息" />&nbsp;&nbsp;
							<input  type="button"  value="取消" onclick="javascript:history.go(-1);" /> 
						</td>
					</tr>
			</table>
		</form>
	</body>
</html>
