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
			function quickAnswerChange(){
				var answer = jQuery.trim($("#quickAnswer").val());
				if("请选择" == answer){
					$("#answer").val("");
				}else{
					$("#answer").val(answer);
				}
			}
		</script>
	</head>
	<body class="main_body">
		<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
		<tr><font color="red" > ${hint}</font> </tr>
		</table>
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=nAnswer_5" method="post" onsubmit="return checkData();" >
			<input type="hidden" name="current_Page" value="${current_Page }" >
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr><td>客服解答页面&nbsp;&nbsp; <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=nAnswer_4&teamId=${onlineHelpVO.teamId }&current_Page=${pageI.current_Page }&show=true" >查看最近15条解答记录</a> </td> </tr>
			<tr><td>问题：${onlineHelpVO.question }</td> </tr>
			<tr><td>选择快速回答
					 <select id="quickAnswer" onchange="quickAnswerChange()" >
					 	<option value="请选择">请选择</option>
					 	<c:if test="${quickAnswers != null}">
						<c:forEach items="${quickAnswers}" var="quickAnswer">
							<option value="${quickAnswer }">${quickAnswer }</option>
						</c:forEach>
						</c:if>
			 		 </select> 
			</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="teamId" value="${onlineHelpVO.teamId }" />
					<textarea rows="10" cols="80" id="answer" name="answer" >${onlineHelpVO.answer}</textarea>
					<br />
				</td>
			</tr>
			<tr>
				<td>
				<c:choose> 
					<c:when  test="${show != null && show == 'true'}">
						<input  type="button"  value="取消"  onclick="javascript:history.go(-2);"  />
					</c:when>
					<c:otherwise>
						<input  type="button"  value="取消"  onclick="javascript:history.go(-1);"  />
					</c:otherwise>
				</c:choose>	
					&nbsp;&nbsp;<input type="submit"  value="确认修改" >
				</td>
			</tr>
		</table>
		<c:if test="${show != null && show == 'true'}">
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr> <td colspan="5" > 该玩家客服答疑记录 </td></tr>
				<c:choose>
					<c:when  test="${onlinehelpLogs != null}">
						<tr>
							<td>操作人名称</td>
							<td>玩家id</td>
							<td>玩家问题</td>
							<td>解决方案</td>
							<td>创建时间</td>
						</tr>
						<c:forEach items="${onlinehelpLogs}" var="onlinehelpLogVO" varStatus="status" >
						<tr>
							<td>${onlinehelpLogVO.userName } &nbsp;</td>
							<td>${onlinehelpLogVO.teamId } &nbsp;</td>
							<td>${onlinehelpLogVO.question } &nbsp;</td>
							<td align="center"><c:choose> <c:when test="${onlinehelpLogVO.operType == 1 }" > 回答：${onlinehelpLogVO.answer } </c:when> <c:otherwise>删除</c:otherwise> </c:choose></td> 
							<td>${onlinehelpLogVO.createTimeStr } &nbsp;</td>
						</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr> <td> 无答疑记录 </td></tr>
					</c:otherwise>
				</c:choose>
			</table>
		</c:if>
		</form>
	</body>
</html>
