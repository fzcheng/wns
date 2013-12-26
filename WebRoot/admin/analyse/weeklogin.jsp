<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="my"  uri="/my" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
	</head>
	<body class="main_body">
	    <%
	    String hint = (String)request.getAttribute("hint");
	     if(hint != null){
	     %>
	     <center><font color="#FF0000"><%=hint%></font></center>
	     <%
	     }
	     %>
	     
		<form id="textform" name="textform"  method="post">
			<div style="width: 90%;text-align: right">总注册人数:${sumRegCount}人&nbsp;&nbsp; 在线人数:${onlineCount}人</div>	
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 序号 </td>
						<td align="center"> 日期 </td>
						<td align="center"> 注册人数 </td>
						<td align="center"> 登陆人数 </td> 
						<td align="center"> 次2周留存 </td> 
						<td align="center"> 次3周留存 </td> 
						<td align="center"> 次4周留存 </td>
						<td align="center"> 操作 </td>
					</tr>
				<c:if test="${weeklogins != null}">
					<c:forEach items="${weeklogins}" var="weekLoginVO">
						<tr> 
							<td align="center"> ${weekLoginVO.id } </td>
							<td align="center"> ${weekLoginVO.date } </td>
							<td align="center"> ${weekLoginVO.regcount } </td>
							<td align="center"> ${weekLoginVO.logincount } </td>
							<td align="center"> ${weekLoginVO.lcount2 } </td>
							<td align="center"> ${weekLoginVO.lcount3 } </td>
							<td align="center"> ${weekLoginVO.lcount4 } </td>
							<td align="center"> 修改<!-- <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n57&roleId=${backRoleVO.roleId }">修改</a> --></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
