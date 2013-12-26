<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script language="JavaScript">
		var time = ${time};
		var t = setInterval(refresh,parseInt(time)*1000);
		function r_start(){
			time = $.trim($("#time").val());
			var reg = new RegExp("^[0-9]*$");
			if(time=='' || !reg.test(time)){
			    alert("请输入正确的秒数!");
			    return;
			}
		   	t = setInterval(refresh,parseInt(time)*1000);
		}
		function r_stop(){
		   	clearInterval(t);
		}
		
		function onclickBox(){
			if($("#checkId").attr("checked")==true){
				r_start();
			}else{
				r_stop();
			}
		}
		
		function refresh(){
			var chat = $.trim($("#chat").val());
			var time = $.trim($("#time").val());
			window.location.href = "<%=request.getContextPath()%>/bacl_admin.do?comd=n7_11&chat="+chat+"&time="+time;
		}
		
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
<c:if test="${message != null && message != ''}">
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><td><label style="color: red">${message}</label></td></tr>
</table>
</c:if>
<br />
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td colspan="3" align="center">
	<form id="textform1" name="textform1" action="<%=request.getContextPath()%>/bacl_admin.do?comd=chatHelp" method="post">
	GM:<input id="chat" name="chat" type="text" value="${chat}" size="100"/>&nbsp;&nbsp;
		 <input type="submit" value="发送" />
	</form>
	</td>
<form id="workForm" name="workForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n7_11" method="post">
	<td><input onclick="onclickBox()" id="checkId" name="check" type="checkbox" value="F" checked="checked" />自动刷新&nbsp;<input id="time" name="time" type="text" value="${time}" size="1"/>S</td>
	</tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="5" style="color:#DC143C;">玩家聊天信息</td></tr>
<tr >
<td align="center" width="5%">序号</td>
<td align="center" width="10%">玩家ID</td>
<td align="center" width="15%">玩家昵称</td>
<td align="center" width="55%">聊天内容</td>
<td align="center" width="15%">时间</td>
</tr>
<c:forEach items="${resultList}" var="chat" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${chat.roleId} </td>
<td align="center" >${chat.roleName}</td>
<td align="left" style="word-break:break-all;padding-left: 3px">${chat.detail}</td>
<td align="center" >${chat.time}</td>
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
	<input id="index" name="index" value="${paging.pageIndex}" type="hidden"/>
	<input id="total" name="totalPage" value="${paging.totalPage}" type="hidden" />
</form>
</body>
</html>