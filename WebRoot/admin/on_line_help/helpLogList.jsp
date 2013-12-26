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
			function exportExcel(){
				var userId = jQuery("#userId").attr("value");
				var beginTime = jQuery("#beginTime").attr("value");
				var endTime = jQuery("#endTime").attr("value");

				var url ="bacl_admin.do?comd=n117&userId="+userId+"&beginTime="+beginTime+"&endTime="+endTime;
				window.location.href =url;
			}
		</script>
</head>
<body>
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><font color="red" > ${hint}</font> </tr>
</table>
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n13_2" >
			<center>查看开始时间:${beginTime } </center>    <center> 查看结束时间:${endTime } </center>
			<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="8" > 答疑日志列表</td>
					</tr>
					<tr> 
						<td align="center" colspan="8" > 开始时间：<input id="beginTime" name="beginTime" value="${beginTimeStr }" size="12" type="text" readonly="yes"
							onClick="JavaScript:popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">
					&nbsp;结束时间：<input id="endTime" name="endTime" value="${endTimeStr }" size="12" type="text" readonly="yes" 
								onClick="JavaScript:popUpCalendar(this,textform.endTime,'yyyy-mm-dd')">
						
						<!-- 
						&nbsp;用户名称<select id="userId" name="userId" >
								<option value="all" >全部</option>
								<c:if test="${backUserVOs != null }">
									<c:forEach items="${backUserVOs}" var="backUserVO" varStatus="status" >
									<option value="${backUserVO.userId }" >${backUserVO.userName }</option>
									</c:forEach>
								</c:if> 
							</select>
						 -->
						 
							&nbsp;作答类型<select id="logType" name="logType" >
								<option value="2" >全部</option>
									<option value="0" >客服</option>
									<option value="1" >辅管</option>
									
							</select> 
							&nbsp;作答人id查询：<input id="userId" name="userId" size="12" type="text"/>		
						&nbsp; <input type="submit" value="查询" /> 
						 &nbsp;&nbsp;&nbsp; <!--  <a href="#" onclick="exportExcel()" > 导出excel  </a>--></td>
					</tr>
					<tr> 
					    <td align="center"> 编号 </td>
						<td align="center"> 时间 </td>
						<td align="center"> 作答类型</td>
						<td align="center"> 作答人id </td>
						<td align="center"> 作答人昵称 </td>
						<td align="center"> 玩家id </td>
						<td align="center"> 提问内容 </td> 
						<td align="center"> 解决方案 </td> 
					</tr>
					<c:if test="${pageI.list != null}">
						<c:forEach items="${pageI.list}" var="onlinehelpLogVO" varStatus="status" >
							<tr> 
								<td align="center"> ${status.count }&nbsp; </td>
								<td align="center"> ${onlinehelpLogVO.createTimeStr }&nbsp; </td>
								<td align="center"> <c:choose> <c:when test="${onlinehelpLogVO.logType==1 }" > 辅管回答 </c:when> <c:otherwise>客服回答</c:otherwise> </c:choose></td>
								<td align="center"> ${onlinehelpLogVO.userId }</td>
								<td align="center"> ${onlinehelpLogVO.userName }</td>
								<td align="center" >${onlinehelpLogVO.teamId }</td>
								<td align="center"> ${onlinehelpLogVO.question } </td>
								<td align="center"><c:choose> <c:when test="${onlinehelpLogVO.operType == 1 }" > 回答：${onlinehelpLogVO.answer } </c:when> <c:otherwise>删除</c:otherwise> </c:choose></td> 
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
