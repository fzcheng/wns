<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="my"  uri="/my" %>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
			<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
<body class="main_body">	
<c:if test="${hint != null && hint != ''}">
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><td><label style="color: red">${hint}</label></td></tr>
</table>
</c:if>
<br />
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td colspan="3">
	<form id="textform1" name="textform1" action="<%=request.getContextPath()%>/bacl_admin.do?comd=busniessInfo" method="post">
	编号：<input id="roleId" name="roleId" type="text" value="${roleId}" />&nbsp;&nbsp;
		 <input type="submit" value="搜索" />
	</form>
	</td></tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="9" style="color:#DC143C;">玩家生意信息</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >玩家昵称</td>
<td align="center" >生意类型</td>
<td align="center" >生意名称</td>
<td align="center" >个数</td>
<td align="center" >购买价格</td>
<td align="center" >卖出价格</td>
<td align="center" >能收获次数</td>
<td align="center" >产能</td>
</tr>
<c:forEach items="${rolebusinesslist}" var="roleBusinessVO" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${roleBusinessVO.roleName} </td>
<td align="center" >${roleBusinessVO.typeName} </td>
<td align="center" >${roleBusinessVO.business.name}</td>
<td align="center" ><input id="counts${status.count}" type="text" value='${roleBusinessVO.count}' /><input type="button" onclick="setG('${status.count}','${roleBusinessVO.count}','${roleBusinessVO.roleId}','${roleBusinessVO.businessId}')" value="修改"/></td>
<td align="center" >${roleBusinessVO.pr}</td>
<td align="center" >${roleBusinessVO.ps}</td>
<td align="center" >
<c:if test="${roleBusinessVO.type == 2}">${roleBusinessVO.canGainCount}</c:if>
<c:if test="${roleBusinessVO.type == 1}">-</c:if>
</td>
<td align="center" >
<c:if test="${roleBusinessVO.type == 2}">${roleBusinessVO.canGainGold}</c:if>
<c:if test="${roleBusinessVO.type == 1}">${roleBusinessVO.business.re}</c:if>
</td>
</tr>
</c:forEach>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="7" style="color:#DC143C;">3天内玩家生意动态</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >玩家昵称</td>
<td align="center" >互动玩家ID</td>
<td align="center" >互动玩家昵称</td>
<td align="center" >动作</td>
<td align="center" >银两数量</td>
<td align="center" >时间</td>
</tr>
<c:forEach items="${roleMessList}" var="mess" varStatus="status">
<c:if test="${roleId == mess.roleId}">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${mess.roleName}</td>
<td align="center" >${mess.broleId}</td>
<td align="center" >${mess.broleName}</td>
<td align="center" >被偷</td>
<td align="center" >${mess.detail}</td>
<td align="center" >${mess.time}</td>
</tr>
</c:if>

<c:if test="${roleId != mess.roleId}">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${mess.broleName}</td>
<td align="center" >${mess.roleId}</td>
<td align="center" >${mess.roleName}</td>
<td align="center" >偷</td>
<td align="center" >${mess.plusDetail}</td>
<td align="center" >${mess.time}</td>
</tr>
</c:if>
</c:forEach>
</table>
<form id="skbF" name="skbF" action="<%=request.getContextPath()%>/bacl_admin.do?comd=updateBusinessInfo" method="post">

	<input type="hidden"  id="roleId_" name="roleId"/>
	<input type="hidden"  id="count_" name="count"/>
	<input type="hidden"  id="businessId_" name="businessId"/>
	
</form>
</body>

<script type="text/javascript">

  function setG(q,v,r,b){
	if(confirm("确定修改?")){
		var count=$.trim($("#counts"+q).val());
		var roleId=$.trim(r);
		$("#roleId_").val(roleId);
		$("#count_").val(count);
		$("#businessId_").val(b);
		
		skbF.submit();
		}else{
		$("#counts"+q).val(v);
		}
	}
</script>
</html>