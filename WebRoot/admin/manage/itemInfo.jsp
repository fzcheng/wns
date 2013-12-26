<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="my"  uri="/my" %>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
			<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
			<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
			<script language="javascript">
			function deleteItem(roleName,itemName,itemId,index){
				 var input = $("#input"+index).val();
				 var count = $("#itemCount"+index).text();
				
				 var reg = new RegExp("^[1-9][0-9]*$");
				 
				 if(input == '' || !reg.test(input) || parseInt(input) > parseInt(count)){
					 alert("请输入正确的数量!");
					 return;
				 }
				
				 if(confirm("确认删除玩家：\n"+roleName+"的"+itemName+input+"个!")){
					 $("#itemId").val(itemId);
					 $("#quantity").val(input);
					 document.subForm.submit();
				} 
			}
			
			function deleteAll(roleName,itemName,itemId,index){
				 var count = $("#itemCount"+index).text();
				 
				 var reg = new RegExp("^[0-9]*$");
				 if(!reg.test(count)){
					 alert("请输入正确的数量!");
					 return;
				 }
				
				 if(confirm("确认删除玩家：\n"+roleName+"的"+itemName+count+"个!")){
					 $("#itemId").val(itemId);
					 $("#quantity").val(count);
					 document.subForm.submit();
				} 
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
	<tr><td colspan="3">
	<form id="textform1" name="textform1" action="<%=request.getContextPath()%>/bacl_admin.do?comd=itemInfo" method="post">
	编号：<input id="roleId" name="roleId" type="text" value="${roleId}" />&nbsp;&nbsp;
		 <input type="submit" value="搜索" />
	</form>
	</td></tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" style="border-collapse:collapse;empty-cells:show;">
<tr><td align="center" colspan="7" style="color:#DC143C;">玩家物品信息</td></tr>
<tr >
<td align="center" width="5%">序号</td>
<td align="center" width="30%">玩家昵称</td>
<td align="center" width="25%">物品名称</td>
<td align="center" width="10%">类型</td>
<td align="center" width="10%">拥有个数</td>
<td align="center" width="20%" colspan="2">操作</td>
</tr>
<c:forEach items="${roleitemlist }" var="roleItemVO" varStatus="status">
<c:if test="${roleItemVO.count > 0}">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${roleItemVO.roleName} </td>
<td align="center" >${roleItemVO.itemName}</td>
<td align="center" >${roleItemVO.typeName}</td>
<td align="center" ><label id="itemCount${status.count}">${roleItemVO.count}</label></td>
<td align="left" <c:if test="${!my:hasPermission(backUser.roleId,70011)}">colspan="2"</c:if>>
<c:if test="${my:hasPermission(backUser.roleId,70011)}">
<input id="input${status.count}" type="text" size="2"/>&nbsp;
<input onclick="deleteItem('${roleItemVO.roleName}','${roleItemVO.itemName}','${roleItemVO.itemId}','${status.count}')" type="button" value="删除"/>
</c:if>

<c:if test="${my:hasPermission(backUser.roleId,70011)}">
<td align="center" >
&nbsp;<input onclick="deleteAll('${roleItemVO.roleName}','${roleItemVO.itemName}','${roleItemVO.itemId}','${status.count}')" type="button" value="全部删除"/>&nbsp;
</td>
</c:if>
</tr>
</c:if>
</c:forEach>
</table>
<form id="subForm" name="subForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=deleteItem" method="post">
<input id="roleId_sub" name="roleId" value="${roleId}" type="hidden" />
<input id="itemId" name="itemId" type="hidden" />
<input id="quantity" name="quantity" type="hidden" />
</form>

</body>
</html>