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
			function sendQuery(answer){
				window.textform.submit();
			}
			$(document).ready(function(){
				 jQuery("#type").get(0).value = '${isAnswer}'; 
				 jQuery("#sort").get(0).value = '${sort}';  
			});
			
		// Javascript 控制 CheckBox 的全选与取消全选
		function checkAll(obj) {
		    var el = document.getElementsByName("questionCheckbox");
		    var len = el.length;
		    for(var i=0; i<len; i++) {
		        if((el[i].type=="checkbox") && (el[i].name == 'questionCheckbox')) {
		            el[i].checked = obj.checked;
		        }
		    }
		}
		//批量回答
		function batchAnswer(current_Page){
			var el = document.getElementsByName("questionCheckbox");
		    var len = el.length;
		    var teamIds = "";
		    var count = 0;
		    for(var i=0; i<len; i++) {
		        if((el[i].type=="checkbox") && (el[i].name == 'questionCheckbox') &&(el[i].checked == true)) {
		           teamIds = teamIds +el[i].value+",";
		           count++;
		        }
		    }
		    if(count == 0){
		    	alert("请选择需要回答问题!");
		    	return false;
		    }
		    teamIds = teamIds.substring(0, teamIds.length-1);
		    window.textform.action = "bacl_admin.do?comd=nAnswer_6&teamIds="+teamIds+"&current_Page="+current_Page;
		    window.textform.submit();
		}
		//批量分配问题
		function batchQuestion(){
			var el = document.getElementsByName("questionCheckbox");
		    var len = el.length;
		    var teamIds = "";
		    var count = 0;
		    for(var i=0; i<len; i++) {
		        if((el[i].type=="checkbox") && (el[i].name == 'questionCheckbox') &&(el[i].checked == true)) {
		           teamIds = teamIds +el[i].value+",";
		           count++;
		        }
		    }
		    if(count == 0){
		    	alert("请选择需要回答问题!");
		    	return false;
		    }
		    teamIds = teamIds.substring(0, teamIds.length-1);
		    window.textform.action = "bacl_admin.do?comd=n139&teamIds="+teamIds;
		    window.textform.submit();
		}
		//批量删除
		function batchDel(current_Page){
			
			var el = document.getElementsByName("questionCheckbox");
		    var len = el.length;
		    var teamIds = "";
		    var count = 0;
		    for(var i=0; i<len; i++) {
		        if((el[i].type=="checkbox") && (el[i].name == 'questionCheckbox') &&(el[i].checked == true)) {
		           teamIds = teamIds +el[i].value+",";
		           count++;
		        }
		    }
		    if(count == 0){
		    	alert("请选择需要删除问题!");
		    	return false;
		    }
		    if(confirm('确认删除吗?')) {
			    teamIds = teamIds.substring(0, teamIds.length-1);
			    window.textform.action = "bacl_admin.do?comd=n110&teamIds="+teamIds+"&current_Page="+current_Page;
			    window.textform.submit();
		   }
		}
			
		</script>
</head>
<body>
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><font color="red" > ${hint}</font> </tr>
</table>
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=nAnswer_1" >
			<center>查看开始时间:${beginTime } </center>    <center> 查看结束时间:${endTime } </center>
			<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="7" > 答疑列表</td>
					</tr>
					<tr> 
						<td align="center" colspan="7" > 开始时间：<input name="beginTime" size="12" type="text" readonly="yes"
							onClick="JavaScript:popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">
						&nbsp;&nbsp;结束时间：<input name="endTime" size="12" type="text" readonly="yes"
								onClick="JavaScript:popUpCalendar(this,textform.endTime,'yyyy-mm-dd')">
						&nbsp;&nbsp;<select id="sort" name="sort" >
								<option value="desc" >降序</option>
								<option value="asc" >升序</option>
							</select> 
							&nbsp;&nbsp;问题类型<select id="qtype" name="qtype" >
								<option value="all" >全部</option>
								<option value="1" >疑难解答</option>
								<option value="0" >技术处理</option>
							</select> 	
							&nbsp;&nbsp;回答类型<select id="type" name="isAnswer" >
								<option value="all" >全部</option>
								<option value="noAnswer" >未回答</option>
								<option value="answer" >已回答</option>
							</select> 	
								
						&nbsp;&nbsp;提问内容模糊查询<input name="question" type="text"  maxlength="20" />  <input type="submit" value="查询" /> </td>
					</tr>
					<tr> 
						<td align="center"> 时间 </td>
						<td align="center"> 玩家id </td>
						<td align="center"> 玩家昵称 </td>
						<td align="left"> <input type="checkbox" onclick="checkAll(this)" />全选&nbsp;&nbsp;提问内容 </td> 
						<td align="center"> 处理类型 </td>
						<td align="center"> 问题类型 </td>
						<td align="center"> 解决方案 </td> 
					</tr>
					<c:if test="${pageI.list != null}">
						<c:forEach items="${pageI.list}" var="onLineHelpVO" varStatus="status" >
							<tr> 
								<td align="center"> ${onLineHelpVO.commitTimeStr }&nbsp; </td>
								<td align="center" >${onLineHelpVO.teamId }</td>
								<td align="center"> ${onLineHelpVO.teamName } &nbsp;</td>
								<td align="left"> <c:if test="${onLineHelpVO.answer == null}" >  <c:if test="${onLineHelpVO.state==0}"> <input type="checkbox" name="questionCheckbox" value="${onLineHelpVO.teamId}" /></c:if> </c:if>&nbsp;${onLineHelpVO.question } </td>
								<td align="center"><c:choose> <c:when test="${onLineHelpVO.answer == null}" > 未解答 </c:when> <c:otherwise>已解答</c:otherwise> </c:choose></td> 
								<td align="center"> <c:if test="${onLineHelpVO.qtype==1}">疑难解答</c:if><c:if test="${onLineHelpVO.qtype==0}">技术处理</c:if>&nbsp;</td>
								<td align="center"> <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=nAnswer_4&teamId=${onLineHelpVO.teamId }&current_Page=${pageI.current_Page }" > 客服解答 </a>
								                   <c:if test="${onLineHelpVO.answer == null}" >  <c:if test="${onLineHelpVO.state==0}"><a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n138&id=${onLineHelpVO.id }">玩家作答</a></c:if> </c:if> </td>
							</tr>
						</c:forEach>
					<tr>
						<td align="center" colspan="6" ><input type="button" onclick="batchAnswer(${pageI.current_Page })" value="批量回答" />  &nbsp;&nbsp;<input type="button" onclick="batchDel(${pageI.current_Page })" value="批量删除" /> 
												 &nbsp;&nbsp;<input type="button" onclick="batchQuestion()" value="批量分配" /> 						
						</td>
					</tr>
					</c:if>
					
			</table>
		</form>
			<table width="95%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">
					${pageI.departWeb }
					</td>
				</tr>
			</table>
  &nbsp;&nbsp; <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=nAnswer_2" >增加快速解答答案</a>
</body>
</html>
