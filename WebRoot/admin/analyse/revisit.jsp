<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="my" uri="/my"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
	<body class="main_body">
		<%
			String hint = (String) request.getAttribute("hint");
			if (hint != null) {
		%>
		<center>
			<font color="#FF0000"><%=hint%></font>
		</center>
		<%
			}
		%>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回访统计
		<form id="textform" name="textform" method="post">
			<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0">

				<tr>
					<td align="center" width="20%">
 					渠道：<select id="channelId" name="selectId">
								<option
									<c:if test="${selectId == '-1'}">selected="selected"</c:if>
									value="-1">
									汇总数据
								</option>
								<c:forEach items="${channels}" var="channels" varStatus="status">
									<option
										<c:if test="${selectId == channels.channelId}">selected="selected"</c:if>
										value="${channels.channelId}">
										${channels.channelName}
									</option>
								</c:forEach>
							</select>&nbsp;&nbsp;
					</td>
					<td align="center" colspan="11">
						开始时间：
						<input value="${beginTime}" name="beginTime" size="12" type="text"
							readonly="readonly"
							onClick="
	JavaScript: popUpCalendar(this, textform.beginTime, 'yyyy-mm-dd');"
>
						&nbsp;&nbsp; 结束时间：
						<input value="${endTime}" name="endTime" size="12" type="text"
							readonly="readonly"
							onClick=
	"JavaScript: popUpCalendar(this, textform.endTime, 'yyyy-mm-dd');"
>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" value="查询" />
					</td>
				</tr>
				<tr>
					<td align="center">
						日期
					</td>
					<td align="center">
						新增用户(当日注册)
					</td>
					<td align="center">
						次日2回访
					</td>
					<td align="center">
						次日2回访率
					</td>
					<td align="center">
						次3日回访
					</td>
					<td align="center">
						次4日回访
					</td>
					<td align="center">
						次5日回访
					</td>
					<td align="center">
						次6日回访
					</td>
					<td align="center">
						次7日回访
					</td>
					<td align="center">
						次7日回访率
					</td>
				</tr>
				<c:if test="${revisits != null}">
					<c:forEach items="${revisits}" var="revisitVO">
						<tr>
							<td align="center">
								${revisitVO.date }
							</td>
							<td align="center">
								${revisitVO.regcount}
							</td>
							<td align="center">
								${revisitVO.lcount2}
							</td>
							<td align="center">
								<fmt:formatNumber value="${revisitVO.lcount2_rate }"
									type="percent" />
							</td>
							<td align="center">
								${revisitVO.lcount3}
							</td>
							<td align="center">
								${revisitVO.lcount4}
							</td>
							<td align="center">
								${revisitVO.lcount5}
							</td>
							<td align="center">
								${revisitVO.lcount6}
							</td>
							<td align="center">
								${revisitVO.lcount7}
							</td>
							<td align="center">
								<fmt:formatNumber value="${revisitVO.lcount7_rate }"
									type="percent" />
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
