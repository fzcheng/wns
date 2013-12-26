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
		<script type="text/javascript">
			//检查需要修改的数据和更改提交url
			function checkData(url,updateName){
				var temp = "";
				if("rolegold" == updateName){
					temp = document.getElementById("rolegold").value;
				}else if("hpoints" == updateName){
					temp = document.getElementById("hpoints").value;
				}else if("rolelevel" == updateName){
					temp = document.getElementById("rolelevel").value;
				}else if("maxmap" == updateName){
					temp = document.getElementById("maxMap").value;
				}else if("score" == updateName){
					temp = document.getElementById("score").value;
				}else if("maxblood" == updateName ){
					temp = document.getElementById("maxblood").value;
				}else if("maxenergy" == updateName ){
					temp = document.getElementById("maxenergy").value;
				}else if("maxstamina" == updateName ){
					temp = document.getElementById("maxstamina").value;
				}else if("curfight" == updateName ){
					temp = document.getElementById("curfight").value;
				}else if("points" == updateName ){
					temp = document.getElementById("points").value;
				}
				
				var re = /^[0-9]*$/;   //判断字符串是否为整数
			     if (!re.test(temp)) {
			        alert("请输入整数");
			        return false;
			     }
				document.textform2.action = url;
				document.textform2.submit();
			}
			function del(url){
				if(confirm('确认删除吗?')) {
					window.location.href = "bacl_admin.do?" +url;
				}
			}
			function checkById(){
			 	textform1.action="<%=request.getContextPath()%>/bacl_admin.do?comd=nRole_1";
			}
			function checkByNickName(){
			
				var  nickName= document.getElementById("roleNickName").value;
				
				if(""== nickName){
					alert("查询不能为空");
				}else{
					alert("正常查询");
				}
				
				var select = document.getElementById("selectNickName"); 
				select.options.add(new Option("测试1","1"));
			}
			
			function logoutRole(){
				
			
			}
		</script>
	</head>
<body class="main_body">	
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width="90%" align=center  border=0 >
<tr><font color="red" > ${hint}</font> </tr>
</table>

<br />
<form id="textform1" name="textform1" action="" method="post">
	<table width="40%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">
				请输入查询玩家ID
			</td>
			<td align="center">
			           请输入查询玩家昵称
			</td>
			<td align="center">
				请选择查询玩家的信息
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="text" name="roleId" size="10" />
			</td>
			<td align="center">
			    <input type="text" name="roleNickName" size="10"/>
			</td>
			<td align="center">
				<select name="type" >
					<option value="all" >全部信息</option>
					<option value="roleInfo" >玩家基本信息</option>
					<option value="roleattack" >攻击防御</option>
					<option value="rolebusiness" >生意信息</option>
					<option value="roleweapon" >武器信息</option>
					<option value="roleitem" >物品信息</option>
					<option value="roleskill" >功夫信息</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="1" >
				<input type="submit" name="Submit" onclick="checkById()" value="输入玩家ID查看玩家信息" />
			</td>
			<td align="center" colspan="1"  >
			    <input type="button" name="SubmitNickName" onclick="checkByNickName()" value="输入玩家昵称查看玩家信息">
			</td>
			<td>
				<select name="selectNickName" id="selectNickName" onChange="if(this.selectedIndex && this.selectedIndex!=0){window.open(this.value);}">
					<option value="" selected>查询玩家</option>
				</select>
			</td>
		</tr>
	</table>
</form>
<br />

<c:if test="${rolevo!=null}">
<form id="textform2" name="textform2" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n94" method="post">
<input type="hidden" name="roleId"  value="${rolevo.roleId }" />
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="9" style="color:#DC143C;">玩家基本信息</td></tr>
<tr >
<td  colspan="9" >玩家ID：${rolevo.roleId } </td><td><input type="button"  onclick="logoutRole()" value="注 销" /></td>
</tr>
<tr >
<td  colspan="9" >UID：${rolevo.uid } </td>
</tr>
<tr>
<td  colspan="9" >用户ID：${userId} </td>
</tr>
<tr>
<td  colspan="9" ><!-- <span  style="float: left">用户名称：${userName }</span> <span style="float: right;">  原始密码<input type="text" />&nbsp;&nbsp;新密码：<input type="text" /></span></td><td  >  <input <c:if test="${!my:hasPermission(backUser.roleId,70004)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_4','rolelevel')" style="background-color:#B0B0B0" />  --></td>
</tr>
<tr >
<td  colspan="9" >玩家名称：${rolevo.roleName } </td>
</tr>
<tr >
<td  colspan="9" >性别：${rolevo.rolesex } </td>
</tr>
<tr >
<td  colspan="9" >新手引导：${rolevo.status } </td>
</tr>
<tr >
<td  colspan="9" >门派：${rolevo.roleFa } </td>
</tr>
<tr >
<td  colspan="9" ><span  style="float: left">等级：${rolevo.rolelevel }</span> <input type="text" id="rolelevel" name="rolelevel" value="${rolevo.rolelevel }" style="float: right;"/></td><td  > <input <c:if test="${!my:hasPermission(backUser.roleId,70004)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_4','rolelevel')" style="background-color:#B0B0B0" /> </td>
</tr>
<tr >
<td  colspan="9" ><span  style="float: left">金币：${rolevo.rolegold }</span> <input type="text" id="rolegold" name="rolegold" value="${rolevo.rolegold }" style="float: right;"/></td><td><input <c:if test="${!my:hasPermission(backUser.roleId,70002)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_2','rolegold')" style="background-color:#B0B0B0" /> </td>
</tr>
<tr >
<td  colspan="9" >经验：${rolevo.curexp } </td>
</tr>
<tr >
<td  colspan="9" ><span  style="float: left">最大血量：${rolevo.maxblood }</span> <input type="text" id="maxblood" name="maxblood" value="${rolevo.maxblood }" style="float: right;"/></td><td> <input <c:if test="${!my:hasPermission(backUser.roleId,70004)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_7','maxblood')" style="background-color:#B0B0B0" /> </td>
</tr>
<tr >
<td  colspan="9" ><span  style="float: left">最大能量：${rolevo.maxenergy }</span> <input type="text" id="maxenergy" name="maxenergy" value="${rolevo.maxenergy }" style="float: right;"/></td><td> <input <c:if test="${!my:hasPermission(backUser.roleId,70004)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_8','maxenergy')" style="background-color:#B0B0B0" /> </td>
</tr>
<tr >
<td  colspan="9" ><span  style="float: left">最大气：${rolevo.maxstamina }</span> <input type="text" id="maxstamina" name="maxstamina" value="${rolevo.maxstamina }" style="float: right;"/></td><td> <input <c:if test="${!my:hasPermission(backUser.roleId,70004)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_9','maxstamina')" style="background-color:#B0B0B0" /> </td>
</tr>
<tr >
<td  colspan="9" >当前血量：${rolevo.curblood } </td>
</tr>
<tr >
<td  colspan="9" >当前能量：${rolevo.curenergy } </td>
</tr>
<tr >
<td  colspan="9" >当前气：${rolevo.curstamina } </td>
</tr>
<tr >
<td  colspan="9" ><span  style="float: left">战力：${rolevo.curfight }</span> <input type="text" id="curfight" name="curfight" value="${rolevo.curfight }" style="float: right;"/></td><td> <input <c:if test="${!my:hasPermission(backUser.roleId,70004)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_10','curfight')" style="background-color:#B0B0B0" /> </td>
</tr>
<tr >
<td  colspan="9" >血升级点：${rolevo.pointblood } </td>
</tr>
<tr >
<td  colspan="9" >能量升级点：${rolevo.pointenergy } </td>
</tr>
<tr >
<td  colspan="9" >气升级点：${rolevo.pointstamina } </td>
</tr>
<tr >
<td  colspan="9" >战力升级点：${rolevo.pointfight } </td>
</tr>
<tr >
<td  colspan="9" >钱包升级点：${rolevo.pointwallet } </td>
</tr>
<tr >
<td colspan="9" ><span  style="float: left">剩余升级点：${rolevo.points } </span><input type="text" id="points" name="points" value="${rolevo.points }" style="float: right;" /></td><td> <input <c:if test="${!my:hasPermission(backUser.roleId,70004)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_11','points')" style="background-color:#B0B0B0" /> </td>
</tr>
<tr >
<td  colspan="9" >当前地图：${rolevo.curmap } </td>
</tr>
<tr >
<td  colspan="9" >去过的最大地图：${rolevo.maxbmap }(${rolevo.maxbMapName})</td>
</tr>
<tr >
<td  colspan="9" ><span  style="float: left">最大地图：${rolevo.maxmap }(${rolevo.maxMapName})</span>
<select id="maxMap" name="maxmap" style="float: right;" >
	<option <c:if test="${rolevo.maxmap == 1}" >selected="selected"</c:if> value="1">小渔村</option>
	<option <c:if test="${rolevo.maxmap == 2}" >selected="selected"</c:if> value="2">扬州</option>
	<option <c:if test="${rolevo.maxmap == 3}" >selected="selected"</c:if> value="3">巫山</option>
	<option <c:if test="${rolevo.maxmap == 4}" >selected="selected"</c:if> value="4">黑木崖</option>
	<option <c:if test="${rolevo.maxmap == 5}" >selected="selected"</c:if> value="5">桃花岛</option>
	<option <c:if test="${rolevo.maxmap == 6}" >selected="selected"</c:if> value="6">襄阳</option>
	<option <c:if test="${rolevo.maxmap == 7}" >selected="selected"</c:if> value="7">京城</option>
	<option <c:if test="${rolevo.maxmap == 8}" >selected="selected"</c:if> value="8">华山</option>
</select></td><td><input <c:if test="${!my:hasPermission(backUser.roleId,70005)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_5','maxmap')" style="background-color:#B0B0B0" /></td>
</tr>
<tr >
<td  colspan="9" >当前称号：${rolevo.curTitle } </td>
</tr>
<tr >
<td  colspan="9" ><span  style="float: left">奖励点：${rolevo.hpoints }</span><!--  <input type="text" id="hpoints" name="hpoints" value="${rolevo.hpoints }" style="float: right;"/> </td><td> <input <c:if test="${!my:hasPermission(backUser.roleId,70003)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_3','hpoints')" style="background-color:#B0B0B0" />  --></td>
</tr>
<tr >
<td  colspan="9" ><span  style="float: left">积分：${rolevo.score }</span> <!-- <input type="text" id="score" name="score" value="${rolevo.score }" style="float: right;" /></td><td> <input <c:if test="${!my:hasPermission(backUser.roleId,70022)}">disabled="disabled"</c:if> type="button" value="修改" onclick="checkData('bacl_admin.do?comd=nRole_6','score')" style="background-color:#B0B0B0" /> --> </td>
</tr>
<tr>
<td align="right" >&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
</form>
</c:if>

<td > &nbsp;&nbsp;&nbsp;&nbsp;</td>
<!-- 
<c:if test="${roleattackvo != null }">
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="14" style="color:#DC143C;">玩家攻防信息</td></tr>
<tr >
<td align="center" >玩家ID</td>
<td align="center" >每小时回报</td>
<td align="center" >武器维护费</td>
<td align="center" >攻击</td>
<td align="center" >防御</td>
<td align="center" >加成1</td>
<td align="center" >加成2</td>
<td align="center" >加成3</td>
<td align="center" >加成4</td>
<td align="center" >加成5</td>
<td align="center" >挑战胜利</td>
<td align="center" >挑战失败</td>
<td align="center" >好友数</td>
<td align="center" >公会</td>
</tr>
<tr >
<td align="center" >${roleattackvo.roleId }</td>
<td align="center" >${roleattackvo.re } </td>
<td align="center" >${roleattackvo.pm }</td>
<td align="center" >${roleattackvo.atk }</td>
<td align="center" >${roleattackvo.def }</td>
<td align="center" >${roleattackvo.skb }</td>
<td align="center" >${roleattackvo.abf }</td>
<td align="center" >${roleattackvo.aes }</td>
<td align="center" >${roleattackvo.aia }</td>
<td align="center" >${roleattackvo.aid }</td>
<td align="center" >${roleattackvo.wincount }</td>
<td align="center" >${roleattackvo.failcount }</td>
<td align="center" >${roleattackvo.friendscount }</td>
<td align="center" >${roleattackvo.groupId }</td>
</tr>
</table>
</c:if>

<br />
<td > &nbsp;&nbsp;&nbsp;&nbsp;</td>
<c:if test="${roleskilllist != null }">
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">玩家功夫信息</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >玩家ID</td>
<td align="center" >技能ID</td>
<td align="center" >等级</td>
<td align="center" >当前学习次数</td>
<td align="center" >总学习次数</td>
</tr>
<c:forEach items="${roleskilllist }" var="roleSkillVO" varStatus="status">
<tr >
<td align="center" >${status.count }</td>
<td align="center" >${roleSkillVO.roleId } </td>
<td align="center" >${roleSkillVO.skillId }</td>
<td align="center" >${roleSkillVO.level }</td>
<td align="center" >${roleSkillVO.count }</td>
<td align="center" >${roleSkillVO.totalcount }</td>
</tr>
</c:forEach>
</table>
</c:if>

<br />
<td > &nbsp;&nbsp;&nbsp;&nbsp;</td>
<c:if test="${rolebusinesslist != null }">
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="8" style="color:#DC143C;">玩家生意信息</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >玩家ID</td>
<td align="center" >生意ID</td>
<td align="center" >个数</td>
<td align="center" >购买价格</td>
<td align="center" >卖出价格</td>
<td align="center" >能收获次数</td>
<td align="center" >能收获银两</td>
</tr>
<c:forEach items="${rolebusinesslist }" var="roleBusinessVO" varStatus="status">
<tr >
<td align="center" >${status.count }</td>
<td align="center" >${roleBusinessVO.roleId } </td>
<td align="center" >${roleBusinessVO.businessId }</td>
<td align="center" >${roleBusinessVO.count }</td>
<td align="center" >${roleBusinessVO.pr }</td>
<td align="center" >${roleBusinessVO.ps }</td>
<td align="center" >${roleBusinessVO.canGainCount }</td>
<td align="center" >${roleBusinessVO.canGainGold }</td>
</tr>
</c:forEach>
</table>
</c:if>

<br />
<td > &nbsp;&nbsp;&nbsp;&nbsp;</td>
<c:if test="${roleweaponlist != null }">
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">玩家武器信息</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >玩家ID</td>
<td align="center" >武器ID</td>
<td align="center" >类型</td>
<td align="center" >拥有个数</td>
<td align="center" >装备个数</td>
</tr>
<c:forEach items="${roleweaponlist }" var="roleWeaponVO" varStatus="status">
<tr >
<td align="center" >${status.count }</td>
<td align="center" >${roleWeaponVO.roleId } </td>
<td align="center" >${roleWeaponVO.weaponId }</td>
<td align="center" >${roleWeaponVO.type }</td>
<td align="center" >${roleWeaponVO.count }</td>
<td align="center" >${roleWeaponVO.usecount }</td>
</tr>
</c:forEach>
</table>
</c:if>

<br />
<td > &nbsp;&nbsp;&nbsp;&nbsp;</td>
<c:if test="${roleitemlist != null }">
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">玩家物品信息</td></tr>
<tr >
<td align="center" >序号</td>
<td align="center" >玩家ID</td>
<td align="center" >物品ID</td>
<td align="center" >类型</td>
<td align="center" >拥有个数</td>
</tr>
<c:forEach items="${roleitemlist }" var="roleItemVO" varStatus="status">
<tr >
<td align="center" >${status.count }</td>
<td align="center" >${roleItemVO.roleId } </td>
<td align="center" >${roleItemVO.itemId }</td>
<td align="center" >${roleItemVO.type }</td>
<td align="center" >${roleItemVO.count }</td>
</tr>
</c:forEach>
</table>
</c:if>
 -->

</body>
</html>