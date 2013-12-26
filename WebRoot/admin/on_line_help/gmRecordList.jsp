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
			$(document).ready(function(){
				 jQuery("#userId").get(0).value = '${userId}'; 
			});
		</script>
</head>
<body>
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><font color="red" > ${hint}</font> </tr>
</table>
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n13_3" >
			<center>查看开始时间:${beginTime } </center>    <center> 查看结束时间:${endTime } </center>
			<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="3" > 答疑日志列表</td>
					</tr>
					<tr> 
						<td align="center" colspan="3" > 开始时间：<input name="beginTime" size="12" type="text" readonly="yes"
							onClick="JavaScript:popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">
						&nbsp;&nbsp;结束时间：<input name="endTime" size="12" type="text" readonly="yes"
								onClick="JavaScript:popUpCalendar(this,textform.endTime,'yyyy-mm-dd')">
						&nbsp;&nbsp;用户名称<select id="userId" name="userId" >
								<option value="all" >全部</option>
								<c:if test="${backUserVOs != null }">
									<c:forEach items="${backUserVOs}" var="backUserVO" varStatus="status" >
									<option value="${backUserVO.userId }" >${backUserVO.userName }</option>
									</c:forEach>
								</c:if> 
							</select> 		
						&nbsp;&nbsp; <input type="submit" value="查询" /> </td>
					</tr>
					<tr> 
						<td align="center"> 时间 </td>
						<td align="center"> 操作人 </td>
						<td align="center"> 操作 </td> 
					</tr>
					<c:if test="${pageI.list != null}">
						<c:forEach items="${pageI.list}" var="gmRecordVO" varStatus="status" >
							<tr> 
								<td align="center"> ${gmRecordVO.createTimeStr }&nbsp; </td>
								<td align="center"> ${gmRecordVO.userName }</td>
								<td align="center"><c:choose> <c:when test="${gmRecordVO.operType == 1 }" > 回答:</c:when> <c:otherwise>删除:</c:otherwise> </c:choose> ${gmRecordVO.count }次 </td> 
							</tr>
						</c:forEach>
					</c:if>
			</table>
		</form>
			<table width="95%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">
					${pageI.departWeb }
					</td>
				</tr>
			</table>
</body>
</html>
