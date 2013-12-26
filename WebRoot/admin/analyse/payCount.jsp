<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="my"  uri="/my" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
	<body class="main_body">
	<c:if test="${hint!=null&&hint!=''}">
		<center><font color="#FF0000">${hint}</font></center>
	</c:if>
	     &nbsp;&nbsp;&nbsp;玩家充值记录统计
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=payCount">
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="18" >
							玩家编号：<input name="roleId" type="text" value="${roleId}">&nbsp;
							充值渠道：<select name="chargeCId">
										<option value="">全部</option>
										<option <c:if test="${chargeCId == 0}">selected="selected"</c:if> value="0">91充值</option>
										<option <c:if test="${chargeCId == 1}">selected="selected"</c:if> value="1">易宝</option>
									</select>
							充值状态：<select name="session">
										<option value="">全部</option>
										<option <c:if test="${session == 1}">selected="selected"</c:if> value="1">下单</option>
										<option <c:if test="${session == 2}">selected="selected"</c:if> value="2">成功</option>
										<option <c:if test="${session == 3}">selected="selected"</c:if> value="3">失败</option>
									</select>
							开始时间：<input id="beginTime" name="beginTime" type="text" value="${beginTime}" onClick="setDay(this);" size="15" readonly="readonly" />&nbsp;
							结束时间：<input id="endTime" name="endTime" type="text" value="${endTime}" onClick="setDay(this);" size="15" readonly="readonly" />&nbsp;
						&nbsp;&nbsp;
						&nbsp;&nbsp; <input type="submit" value="查询" /> </td>
					</tr>
					<tr> 
						<td align="center" width="10%">序号</td>
						<td align="center" width="15%">玩家ID</td>
						<td align="center" width="25%">玩家昵称</td>
						<td align="center" width="15%">充值次数</td>
						<td align="center" width="15%">充值金额</td>
						<td align="center" width="20%">充值时间</td>
					</tr>
					<c:forEach items="${resultList}" var="payCount" varStatus="sta">
						<tr> 
							<td align="center">${sta.count}</td>
							<td align="center">${payCount.roleId}</td>
							<td align="center">${payCount.roleName}</td>
							<td align="center">${payCount.payCount}</td>
							<td align="center">${payCount.paySum}</td>
							<td align="center"><fmt:formatDate value='${payCount.time}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
						</tr>
					</c:forEach>
			</table>
		</form>
	</body>
</html>
