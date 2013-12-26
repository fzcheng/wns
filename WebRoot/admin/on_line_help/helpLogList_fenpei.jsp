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
		//批量关闭问题
		function closeQuestion(){
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
		    	alert("请选择需要关闭的问题!");
		    	return false;
		    }
		    teamIds = teamIds.substring(0, teamIds.length-1);
		    window.textform.action = "bacl_admin.do?comd=n154&teamIds="+teamIds;
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
		<form id="textform" name="textform"  method="post" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n13_6" >
			<center>查看开始时间:${beginTime } </center>    <center> 查看结束时间:${endTime } </center>
			<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" colspan="6" > 答疑列表</td>
					</tr>
					<tr> 
						<td align="center" colspan="6" > 开始时间：<input name="beginTime" size="12" type="text" readonly="yes"
							onClick="JavaScript:popUpCalendar(this,textform.beginTime,'yyyy-mm-dd')">
						&nbsp;&nbsp;结束时间：<input name="endTime" size="12" type="text" readonly="yes"
								onClick="JavaScript:popUpCalendar(this,textform.endTime,'yyyy-mm-dd')">&nbsp; 
						&nbsp;   
							&nbsp;&nbsp;  <input type="submit" value="查询" /> </td>
					</tr>					
					
			</table>
			<table width="95%" border="1" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center" colspan="7">
					&nbsp;&nbsp;
				</td>
			</tr>

			<tr>
			   <td><input type="checkbox" onclick="checkAll(this)" />全选</td>
				<td align="center">
					时间
				</td>
				<td align="center">
					问题id
				</td>
				<td align="center">
					问题
				</td>
				<td align="center">
					玩家id
				</td>
				<td align="center">
					玩家昵称
				</td>
			
				<td align="center">
					解决方案
				</td>
			</tr>
			<c:if test="${pagei.list != null}">
				<c:forEach items="${pagei.list}" var="VO"
					varStatus="status">
					<tr>
					<td>
					<input type="checkbox" name="questionCheckbox" value="${VO.teamId}" />
					</td>
						<td align="center">
							${VO.commitTime }&nbsp;
						</td>
						<td align="center">
							${VO.id }
						</td>
						<td align="center">
							${VO.question }
						</td>
						<td align="center">
							${VO.teamId }
						</td>
						<td align="center">
							${VO.teamName }
						</td>
						<td align="center">
							<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n141&id=${VO.id}">关闭分配</a>
						</td>
					</tr>
					</c:forEach>
			</c:if>
					<tr>
						<td align="center" colspan="7" >&nbsp;&nbsp;<input type="button" onclick="closeQuestion()" value="批量关闭" /> 						
						</td>
					</tr>
				
			<tr>
				<td align="center" colspan="7" >
				&nbsp;&nbsp;&nbsp;
				<!--  	<input type="button" onclick="batchQuestion()" value="批量分配" />-->
				</td>
			</tr>
		</table>
	
			
		</form>
			<table width="95%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">
					${pagei.departWeb }
					</td>
				</tr>
			</table>
</body>
</html>
