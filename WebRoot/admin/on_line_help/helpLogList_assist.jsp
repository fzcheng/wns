<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" uri="/my"%>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
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
		<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1
			width="90%" align=center border=0>
			<tr>
				<font color="red"> ${hint}</font>
			</tr>
		</table>
		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center" colspan="7">
					答疑日志列表
				</td>
			</tr>
			<tr>
				<td align="center">
					创建时间
				</td>
				<td align="center">
					${onlineHelpteam.creatTimeStr }
				</td>
			</tr>
			<tr>
				<td align="center">
					创建人
				</td>
				<td align="center">
					${onlineHelpteam.userName }
				</td>
			</tr>
			<tr>
				<td align="center">
					创建ID
				</td>
				<td align="center">
					${onlineHelpteam.userId }
				</td>
			</tr>
			<tr>
				<td align="center">
					玩家昵称
				</td>
				<td align="center">
					${onlineHelpteam.teamName }
				</td>
			</tr>
			<tr>
				<td align="center">
					玩家id
				</td>
				<td align="center">
					${onlineHelpteam.teamId }
				</td>
			</tr>
			<tr>
				<td align="center">
					回答问题次数
				</td>
				<td align="center">
					${onlineHelpteam.count}
				</td>
			</tr>


		</table>



		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center" colspan="7">
					答疑日志列表
				</td>
			</tr>

			<tr>
				<td align="center">
					时间
				</td>
				<td align="center">
					作答类型
				</td>
				<td align="center">
					操作id
				</td>
				<td align="center">
					操作人
				</td>
				<td align="center">
					玩家id
				</td>
				<td align="center">
					提问内容
				</td>
				<td align="center">
					解决方案
				</td>
			</tr>
			<c:if test="${pagei.list != null}">
				<c:forEach items="${pagei.list}" var="onlinehelpLogVO"
					varStatus="status">
					<tr>
						<td align="center">
							${onlinehelpLogVO.createTimeStr }&nbsp;
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${onlinehelpLogVO.logType==1 }"> 玩家回答 </c:when>
								<c:otherwise>客服回答</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							${onlinehelpLogVO.userId }
						</td>
						<td align="center">
							${onlinehelpLogVO.userName }
						</td>
						<td align="center">
							${onlinehelpLogVO.teamId }
						</td>
						<td align="center">
							${onlinehelpLogVO.question }
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${onlinehelpLogVO.operType == 1 }"> 回答：${onlinehelpLogVO.answer } </c:when>
								<c:otherwise>删除</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:if>

		</table>
		<table width="95%" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">
					${pagei.departWeb }
					
				</td>
			</tr>
		</table>
	</body>
</html>
