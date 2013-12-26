<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my"  uri="/my" %>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
			<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>
<body class="main_body">	
<c:if test="${hint != null && hint != ''}">
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><td><label style="color: red">${hint}</label></td></tr>
</table>
</c:if>
<br />
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td colspan="3">
	<form id="textform1" name="textform1" action="<%=request.getContextPath()%>/bacl_admin.do?comd=skillInfo" method="post">
	玩家编号：<input id="roleId" name="roleId" type="text" value="${roleId}" />&nbsp;&nbsp;
		 <input type="submit" value="搜索" />
	</form>
	</td></tr>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">玩家功夫信息</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >玩家昵称</td>
<td align="center" >技能名称</td>
<td align="center" >等级</td>
<td align="center" >攻击加成</td>
<td align="center" >当前学习次数</td>
<td align="center" >总学习次数</td>
<td align="center" >编 辑</td>
</tr>
<c:forEach items="${roleskilllist}" var="roleSkillVO" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${roleSkillVO.roleName} </td>
<td align="center" >${roleSkillVO.skillName}</td>
<td align="center" >${roleSkillVO.level}</td>
<td align="center" >${roleSkillVO.skb}</td>
<td align="center" >${roleSkillVO.count}</td>
<td align="center" ><input id="totalcount${status.count}" type="text" value='${roleSkillVO.totalcount}'  /></td>
<td align="center"><a href="#" onclick="setG('totalcount${status.count}','${roleSkillVO.totalcount}','${roleId}','${roleSkillVO.skillId}')">修 改</td>
</tr>
</c:forEach>
</table>

<form id="skbF" name="skbF" action="<%=request.getContextPath()%>/bacl_admin.do?comd=updateskillInfo" method="post">

	<input type="hidden"  id="skillId_" name="skillId"/>
	<input type="hidden"  id="roleId_" name="roleId"/>
	<input type="hidden"  id="totalcount_" name="totalcount"/>
	
</form>
</body>
<script type="text/javascript">

  function setG(q,v,r,k){
	if(confirm("确定修改?")){
		var totalcount=$.trim($("#"+q).val());
		var skillId=$.trim(k);
		var roleId=$.trim(r);
		
		$("#skillId_").val(skillId);
		$("#roleId_").val(roleId);
		$("#totalcount_").val(totalcount);
		
		skbF.submit();
		}else{
		$("#"+q).val(v);
		}
	}
</script>
</html>