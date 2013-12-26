<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/lift.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function exit(){
				if(confirm('确认退出吗?')) {
					window.location.href = "<%=request.getContextPath()%>/bacl_admin.do?comd=logout";
				}
			}
		</script>
	</head>
	<body>
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;江湖乱世传-推广后台</h2>
		<div style="float:right;position: relative" > 
				<div style="float:left;">用户名:${backUser.userName}【${backUser.roleName}】&nbsp;&nbsp;  
					<c:if test="${1!= backUser.userId }"> 
						<a href="<%=request.getContextPath()%>/admin/back_user/updatePassword.jsp" target="main" >修改密码</a> 
					</c:if> &nbsp;&nbsp;
				</div>
				
				&nbsp;&nbsp;
				<a href="javascript:exit()"  >退出</a>&nbsp;&nbsp; 
		</div>
	</body>
</html>