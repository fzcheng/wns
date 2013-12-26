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
		
			function del(){
				var msg = "您真的确定要删除吗？\n\n请确认！";
   				if (confirm(msg)==true){
   				 return true;
   				}else{
  				 return false;
  				}

			}
			
		</script>

	</head>
	<body class="main_body">
		<form id="textform" name="textform"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=n135"
			method="post"">
			<center>
				辅助管理员信息列表
			</center>
			<font color="red" > ${hint }</font>
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
						<input type="submit" name="添 加" value="添 加"/>
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
					回答问题次数
				</td>
				<td>
					创建人ID
				</td>
				<td>
					创建人昵称
				</td>
				<td>
					操 作
				</td>
			</tr>

			<c:choose>
				<c:when test="${null != pagei}">
					<c:forEach items="${pagei.list}" var="helpTeamVO">
						<tr>
							<td>
								${helpTeamVO.creatTimeStr}
							</td>
							<td>
								<!--<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n82&teamId=${helpTeamVO.teamId }"> -->
								${helpTeamVO.teamId }
								<!-- </a> -->
							</td>
							<td>
								${helpTeamVO.teamName }
							</td>
							<td>
								${helpTeamVO.count }
							</td>
							<td>
								${helpTeamVO.userId }
							</td>
							<td>
								${helpTeamVO.userName }
							</td>
							<td>
								<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n137&teamId=${helpTeamVO.teamId }" > 查 看</a>
								<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n136&teamId=${helpTeamVO.teamId }"> 删 除</a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				
			</c:choose>

		</table>

		<table width="90%" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">
					${pagei.departWeb }
				</td>
			</tr>
		</table>
	</body>
</html>