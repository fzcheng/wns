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
			function checkData(){
				if("" == jQuery.trim($("#answer").val())){
			 		alert("请填写快速答案!");
			 		$("#answer").focus();
			 		return false;
		 		 }
				return true;
			}
		</script>
	</head>
	<body class="main_body">
		<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
		<tr><font color="red" > ${hint}</font> </tr>
		</table>
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=nAnswer_3" method="post" onsubmit="return checkData();" >
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr><td>增加快速解答答案(每个答案用|区分开就可以了)</td> </tr>
			<tr>
				<td>
					<input type="hidden" name="answerId" value="${quickAnswerVO.answerId }" >
					<textarea rows="10" cols="80" id="answer" name="answer" >${quickAnswerVO.answer}</textarea>
					<br />
				</td>
			</tr>
			<tr>
				<td>
					<input  type="button"  value="取消" onclick="javascript:history.go(-1);" />&nbsp;&nbsp;<input type="submit"  value="确认修改" >
				</td>
			</tr>
			</table>
		</form>
	</body>
</html>
