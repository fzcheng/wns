<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script language="JavaScript">
		function subPage(input){
			$("#index").val(input);
		    workForm.submit();
		}
	
		function goTo(){
			var index = $.trim($("#input").val());
			var total = $("#total").val();
		    var reg = new RegExp("^[0-9]*$");
		    if(index=='' || !reg.test(index) || parseInt(index) > parseInt(total)){
		        alert("请输入正确的页数!");
		        return;
		    }
		    subPage(index);
		}
		
		function downPage(){
			var index = $("#index").val();
			var total = $("#total").val();
			if(parseInt(index) >= parseInt(total)){
				return;
			}
			subPage(parseInt(index)+1);
		}
		
		function upPage(){
			var index = $("#index").val();
			if(index == 1){
				return;
			}
			subPage(parseInt(index)-1);
		}
		
		function toFirst(){
			subPage(1);
		}
		
		function toLast(){
			var index = $("#total").val();
			subPage(index);
		}
		</script>
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
	<form id="workForm" name="workForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n7_10" method="post">
	<input id="index" name="index" value="${paging.pageIndex}" type="hidden"/>
	<input id="total" name="totalPage" value="${paging.totalPage}" type="hidden" />
	编号：<input id="roleId" name="roleId" type="text" value="${roleId}" />&nbsp;&nbsp;
		 <input type="submit" value="搜索" />
	</form>
	</td></tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="7" style="color:#DC143C;">玩家功夫代练信息</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >玩家昵称</td>
<td align="center" >类型</td>
<td align="center" >互动玩家ID</td>
<td align="center" >互动玩家昵称</td>
<td align="center" >功夫名称</td>
<td align="center" >代练次数</td>
</tr>
<c:forEach items="${resultList}" var="record" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<c:if test="${roleId != record.roleId}">
<td align="center" >${record.broleName}</td>
<td align="center" >被代练</td>
<td align="center" >${record.roleId}</td>
<td align="center" >${record.roleName}</td>
</c:if>
<c:if test="${roleId == record.roleId}">
<td align="center" >${record.roleName}</td>
<td align="center" >代练</td>
<td align="center" >${record.broleId}</td>
<td align="center" >${record.broleName}</td>
</c:if>
<td align="center" >${record.skillName}</td>
<td align="center" >${record.count}</td>
</tr>
</c:forEach>
<c:if test="${paging.count > 0}">
<tr>
<td align="left" colspan="7">
(${paging.pageIndex}/${paging.totalPage})
<a href="javascript:toFirst()">首页</a>&nbsp;
<a href="javascript:upPage()">上一页</a>&nbsp;
<a href="javascript:downPage()">下一页</a>&nbsp;
<a href="javascript:toLast()">末页</a>&nbsp;
<input id="input" size="2" type="text" />&nbsp;
<a href="javascript:goTo()">Go...</a>&nbsp;
</td>
</tr>
</c:if>
</table>

</body>
</html>