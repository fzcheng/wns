<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function checkData(){
			
				if("" == jQuery.trim($("#teamId").val())){
			 		alert("请填写玩家ID");
			 		$("#teamId").focus();
			 		return false;
		 		 }
				return true;
			}
		</script>
	</head>
	<body class="main_body">
		<form id="textform" name="textform"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=n77"
			method="post" onsubmit="return checkData();">
			<center>
				添加辅助管理员
			</center>
			<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						输入要添加的玩家ID
						<input id="teamId" name="teamId" size="12" type="text" />
						<input type="submit" name="" Submit name="查询" method="post" />
					</td>
				</tr>
			</table>
		</form>
		<br />
		<table width="90%" border="1" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					创建时间
				</td>
				<td>
					玩家ID
				</td>
				<td>
					玩家昵称
				</td>
				<td>
					创建人ID
				</td>
				<td>
					创建人昵称
				</td>
			</tr>

			<c:choose>
				<c:when test="${null != fixturesList}">
					<c:forEach items="${fixturesList}" var="fixtures">
						<tr>
							<td>
								${fixtures.matchBeginTimeStr}
							</td>
							<td>
								<a href="/bacl_admin.do?comd=n82&fixturesId=${fixtures.id }">${fixtures.homeTeamName}
									VS ${fixtures.visitorsTeamName }</a>
							</td>
							<td>
								<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n80&fixturesId=${fixtures.id }">修改赛程</a>
							</td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">
							 没有比赛
						</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</table>

		<table style="BORDER-COLLAPSE: collapse" height=40 cellPadding=1
			width="90%" align=center border=0>
			<tr>
				<font color="red"> ${hint}</font>
			</tr>
		</table>
	</body>
</html>