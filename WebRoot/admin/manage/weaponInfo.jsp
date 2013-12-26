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
			function deleteWeapon(roleName,weaponName,weaponId,index){
				 var input = $("#input"+index).val();
				 var count = $("#weaponCount"+index).text();
				
				 var reg = new RegExp("^[0-9]*$");
				 
				 if(input == '' || !reg.test(input) || parseInt(input) > parseInt(count)){
					 alert("请输入正确的数量!");
					 return;
				 }
				
				 if(confirm("确认删除玩家：\n"+roleName+"的"+weaponName+input+"个!")){
					 $("#weaponId").val(weaponId);
					 $("#quantity").val(input);
					 document.subForm.submit();
				} 
			}
			
			function deleteAll(roleName,weaponName,weaponId,index){
				 var count = $("#weaponCount"+index).text();
				 
				 var reg = new RegExp("^[1-9][0-9]*$");
				 if(!reg.test(count)){
					 alert("请输入正确的数量!");
					 return;
				 }
				
				 if(confirm("确认删除玩家：\n"+roleName+"的"+weaponName+count+"个!")){
					 $("#weaponId").val(weaponId);
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
	<form id="textform1" name="textform1" action="<%=request.getContextPath()%>/bacl_admin.do?comd=weaponInfo" method="post">
	编号：<input id="roleId" name="roleId" type="text" value="${roleId}" />&nbsp;&nbsp;
		 <input type="submit" value="搜索" />
	</form>
	</td></tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" style="border-collapse:collapse;empty-cells:show;">
<tr><td align="center" colspan="8" style="color:#DC143C;">玩家武器信息</td></tr>
<tr >
<td align="center" width="5%">序号</td>
<td align="center" width="30%">玩家昵称</td>
<td align="center" width="20%">武器名称</td>
<td align="center" width="10%">类型</td>
<td align="center" width="10%">拥有个数</td>
<td align="center" width="10%">装备个数</td>
<td align="center" colspan="2" width="15%">操作</td>
</tr>
<c:forEach items="${roleweaponlist}" var="roleWeaponVO" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${roleWeaponVO.roleName} </td>
<td align="center" >${roleWeaponVO.weaponName}</td>
<td align="center" >${roleWeaponVO.typeName}</td>
<td align="center" ><label id="weaponCount${status.count}">${roleWeaponVO.count}</label></td>
<td align="center" >${roleWeaponVO.usecount}</td>
<td align="left" <c:if test="${!my:hasPermission(backUser.roleId,70009)}">colspan="2"</c:if>>
<c:if test="${my:hasPermission(backUser.roleId,70009)}">
<input id="input${status.count}" type="text" size="2" />&nbsp;
<input onclick="deleteWeapon('${roleWeaponVO.roleName}','${roleWeaponVO.weaponName}','${roleWeaponVO.weaponId}','${status.count}')" type="button" value="删除"/>
</c:if>

<c:if test="${my:hasPermission(backUser.roleId,70009)}">
<td align="center" >
&nbsp;<input onclick="deleteAll('${roleWeaponVO.roleName}','${roleWeaponVO.weaponName}','${roleWeaponVO.weaponId}','${status.count}')" type="button" value="全部删除"/>&nbsp;
</td>
</c:if>
</tr>
</c:forEach>
</table>
<form id="subForm" name="subForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=deleteWeapon" method="post">
<input id="roleId_sub" name="roleId" value="${roleId}" type="hidden" />
<input id="weaponId" name="weaponId" type="hidden" />
<input id="quantity" name="quantity" type="hidden" />
</form>

</body>
</html>