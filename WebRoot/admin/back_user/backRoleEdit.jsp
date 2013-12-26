<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my"  uri="/my" %>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function del(id){
				if(confirm('确认删除吗?')) {
					window.location.href = "bacl_admin.do?comd=delBackUser&userId=" +id;
				}
			}
		</script>
	</head>
	<body class="main_body">
	   <c:if test="${message != null && message != ''}">
	   	<center><font color="#FF0000">${message}</font></center>
	   </c:if>
		<c:if test="${my:hasPermission(backUser.roleId,211)}"><div align="center"><a href="<%=request.getContextPath()%>/bacl_admin.do?comd=EditRole&roleId=${backRoleVO.roleId}" > ${backRoleVO.roleName}角色权限设置</a></div> </c:if>
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=EditRole&roleId=${backRoleVO.roleId}">
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center">渠道 </td>
					</tr>
					
				<c:if test="${channelList != null}">
					<tr>
					<c:forEach items="${channelList}" var="channelList" begin="1" varStatus="status">
						<c:forEach items="${backRoleGameDetailVO}" var="v">
							<c:if test="${v.channelId eq channelList.channelId}">
								<td  align="center"> <input type="checkbox" checked="checked" name="channame" value="${channelList.channelId}"/> ${channelList.channelName}</td>
								 <c:if test="${(status.index)%10==0}"> </tr><tr></c:if>
							</c:if>
						</c:forEach>
						<c:if test="${v.channelId ne channelList.channelId}">
								<td align="center"> <input name="channame" type="checkbox"  value="${channelList.channelId}"/> ${channelList.channelName}</td>
								<c:if test="${(status.index)%10==0}"> </tr><tr></c:if>
							</c:if>
					</c:forEach>
					</tr>
				</c:if>
			</table>
			<div>
				游戏：
				<select style="left: " name="gameId">
				
					<c:forEach items="${gamevo}" var="gv">
						<option value="${gv.gameId}">
							${gv.gameName}
						</option>
					</c:forEach>
					 
				</select>
			</div>
		<div align="center"><input type="submit" value="确定" /><input type="reset" value="取消" onclick="javascript:history.go(-1);"/></div>
		</form>
	</body>
</html>
