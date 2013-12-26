<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
			function del(id){
				if(confirm('确认删除吗?')) {
					window.location.href = "bacl_admin.do?comd=n54&powerId=" +id;
				}
			}
		</script>
	</head>
	<body class="main_body">
	    <%
	    String hint = (String)request.getAttribute("hint");
	     if(hint != null){
	     %>
	     <center><font color="#FF0000"><%=hint %></font></center>
	     <%
	     }
	     %>
	     <c:if test="${my:hasPermission(backUser.roleId,10006)}"> <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n52" >增加权限</a> </c:if>
		<form id="textform" name="textform"  method="post">
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 序号 </td>
						<td align="center"> 菜单名 </td>
						<td align="center"> 菜单URL </td>
						<td align="center"> 父权权限 </td>
						<td align="center"> 权限描叙 </td>
						<td align="center"> 创建时间 </td> 
						<td align="center"> 操作 </td>
					</tr>
					<c:if test="${pageI.list != null}">
						<c:forEach items="${pageI.list}" var="backPowerVO">
							<tr> 
								<td align="center"> ${backPowerVO.powerId } </td>
								<td align="center"> <c:if test="${backPowerVO.parentId == 0}"> <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n49&pId=${backPowerVO.powerId}" > ${backPowerVO.powerName } </a> </c:if><c:if test="${backPowerVO.parentId != 0}">${backPowerVO.powerName }</c:if> </td>
								<td align="center"> ${backPowerVO.linkUrl }&nbsp; </td>
								<td align="center"> <c:if test="${backPowerVO.parentId == 0}">无 </c:if><c:if test="${backPowerVO.parentId!= 0}"> ${backPowerVO.parentName } </c:if> </td>
								<td align="center"> ${backPowerVO.powerDsc }&nbsp;</td>
								<td align="center"> ${backPowerVO.createTime } </td>
								<td align="center">  <c:if test="${my:hasPermission(backUser.roleId,backUser.userId,'power',2) }"> <a href="<%=request.getContextPath()%>/bacl_admin.do?comd=n50&powerId=${backPowerVO.powerId }" >修改</a> </c:if> &nbsp;  <c:if test="${my:hasPermission(backUser.roleId,backUser.userId,'power',3) }"> <a href="#" onclick="del(${backPowerVO.powerId })" >删除</a> </c:if> </td>
							</tr>
						</c:forEach>
					</c:if>
					<tr> 
						<td align="center" colspan="7" > <input  type="button"  value="返回" onclick="javascript:history.go(-1);" /> </td>
					</tr>
			</table>
			</form>
			<table width="80%" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">
					${pageI.departWeb }
					</td>
				</tr>
			</table>
	</body>
</html>
