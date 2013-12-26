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
	    <%
	     String hint = (String)request.getAttribute("hint");
	     if(hint != null){
	    %>
	     <center><font color="#FF0000"><%=hint%></font></center>
	     <%
	     }
	     %>
	     &nbsp;当日登录用户等级统计
		<form id="textform" name="textform"  method="post" action="">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="18" >
							开始时间：<input id="beginTime" name="beginTime" type="text" value="${beginTime}" onClick="setDay(this);" size="15" readonly="readonly" />&nbsp;
							结束时间：<input id="endTime" name="endTime" type="text" value="${endTime}" onClick="setDay(this);" size="15" readonly="readonly" />&nbsp;
						&nbsp;&nbsp;
						&nbsp;&nbsp; <input type="submit" value="查询" /> </td>
					</tr>
					<tr> 
						<td align="center">日期</td>
						<td align="center">登录人数 </td>
						<td align="center"> 1级</td>
						<td align="center"> 2级</td>
						<td align="center"> 3级</td>
						<td align="center"> 4级</td>
						<td align="center"> 5级</td>
						<td align="center"> 6~10级</td>
						<td align="center"> 11~20级 </td> 
						<td align="center"> 21~30级 </td> 
						<td align="center"> 31~40级 </td>
						<td align="center"> 41~50级 </td>
						<td align="center"> 51~100级 </td>
						<td align="center"> 101~200级 </td>
						<td align="center"> 200级以上 </td>
					</tr>
				<c:if test="${dayloginlevels != null}">
					<c:forEach items="${dayloginlevels}" var="dayloginlevelVO">
						<tr> 
							<td align="center"> ${dayloginlevelVO.countDate } </td>
							<td align="center"> ${dayloginlevelVO.count}</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l1Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count1})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l2Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count2})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l3Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count3})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l4Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count4})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l5Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count5})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l6to10Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count6_10})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l11to20Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count11_20})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l21to30Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count21_30})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l31to40Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count31_40})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l41to50Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count41_50})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l51to100Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count51_100})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l101to200Rate }" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count101_200})
							</td>
							<td align="center">
								<fmt:formatNumber value="${dayloginlevelVO.l201Rate}" type="percent" pattern="#0.00%" />(${dayloginlevelVO.count201})
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
