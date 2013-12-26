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
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
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
	      &nbsp;玩家等级统计
		<form id="textform" name="textform"  method="post">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="50" > 开始时间：<input name="beginTime" size="12" type="text" readonly="yes"
							onClick="JavaScript:popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">
						&nbsp;&nbsp;结束时间：<input name="endTime" size="12" type="text" readonly="yes"
								onClick="JavaScript:popUpCalendar(this,textform.endTime,'yyyy-mm-dd')">
						&nbsp;&nbsp;
						&nbsp;&nbsp; <input type="submit" value="查询" /> </td>
					</tr>
					<tr> 
						<td align="center"> 日期 </td>
						<td align="center"> 总玩家数 </td>
						<td align="center"> 1级</td>
						<td align="center"> % </td> 
						<td align="center"> 2级 </td> 
						<td align="center"> % </td> 
						<td align="center"> 3级 </td> 
						<td align="center"> % </td>
						<td align="center"> 4级 </td>
						<td align="center"> % </td>
						<td align="center"> 5级 </td>
						<td align="center"> % </td>
						<td align="center"> 6~10级 </td>
						<td align="center"> % </td>
						<td align="center"> 11~20级 </td>
						<td align="center"> % </td>
						<td align="center"> 21~50级 </td>
						<td align="center"> % </td>
						<td align="center"> 51~100级 </td>
						<td align="center"> % </td>
						<td align="center"> 101~200级 </td>
						<td align="center"> % </td>
						<td align="center"> 200级以上 </td>
						<td align="center"> % </td>
					</tr>
				<c:if test="${levalls != null}">
					<c:forEach items="${levalls}" var="levallVO">
						<tr> 
							<td align="center"> ${levallVO.countDate } </td>
							<td align="center"> ${levallVO.count } </td>
							<td align="center"> ${levallVO.count1 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count1_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count2 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count2_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count3 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count3_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count4 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count4_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count5 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count5_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count6_10 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count6_10_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count11_20 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count11_20_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count21_50 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count21_50_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count51_100 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count51_100_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count101_200 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count101_200_rate }" type="percent" pattern="#0.00%"/></td>
							<td align="center"> ${levallVO.count201 } </td>
							<td align="center"> <fmt:formatNumber value="${levallVO.count201_rate }" type="percent" pattern="#0.00%"/></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
