<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="cn.game.vo.group.GroupStorageItemVO"%>
<%@page import="cn.game.vo.group.GroupMemberVO"%>
<%@page import="cn.game.vo.group.GroupVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib prefix="my"  uri="/my" %>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function window.onbeforeunload(){
				var JscrollPos;
				if (typeof window.pageYOffset != 'undefined') { 
				JscrollPos = window.pageYOffset;
				}
				else if (typeof document.compatMode != 'undefined' &&
				document.compatMode != 'BackCompat') { 
				JscrollPos = document.documentElement.scrollTop;
				}
				else if (typeof document.body != 'undefined') { 
				JscrollPos = document.body.scrollTop;
				}
				document.cookie="scrollTop="+JscrollPos;
			}
			
			function window.onload(){
				var arr;
				if(arr=document.cookie.match(/scrollTop=([^;]+)(;|$)/))
				document.documentElement.scrollTop=parseInt(arr[1]);
				document.body.scrollTop=parseInt(arr[1]);
			}
		
			function subPage(type,input){
				$("#"+type+"Index").val(input);
			    workForm.submit();
			}
		
			function goTo(type){
				var index = $.trim($("#"+type+"Input").val());
				var total = $("#"+type+"Total").val();
			    var reg = new RegExp("^[0-9]*$");
			    if(index=='' || !reg.test(index) || parseInt(index) > parseInt(total)){
			        alert("请输入正确的页数!");
			        return;
			    }
			    subPage(type,index);
			}
			
			function downPage(type){
				var index = $("#"+type+"Index").val();
				var total = $("#"+type+"Total").val();
				if(parseInt(index) >= parseInt(total)){
					alert("已经是最后一页!");
					return;
				}
				subPage(type,parseInt(index)+1);
			}
			
			function upPage(type){
				var index = $("#"+type+"Index").val();
				if(index == 1){
					alert("已经是第一页!");
					return;
				}
				subPage(type,parseInt(index)-1);
			}
			
			function toFirst(type){
				subPage(type,1);
			}
			
			function toLast(type){
				var index = $("#"+type+"Total").val();
				subPage(type,index);
			}
			
			function subDissolve(id){
				if(confirm("确认强制解除该公会？\n解除的公会将无法恢复!")){
					window.location.href="<%=request.getContextPath()%>/bacl_admin.do?comd=dissolveUnion&groupId="+id;
				}
			}
			
			function delGroupItem(id,propsId){
				if(confirm("确认从工会仓库中删除该物品？")){
					window.location.href="<%=request.getContextPath()%>/bacl_admin.do?comd=delGroupItem&id="+id+"&propsId="+propsId;
				}
			}
		</script>
	</head>
<body class="main_body">	
<table style="margin-left: 50" width="50%" border="0" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="2" style="color:#DC143C;">公会基本信息</td></tr>
<tr >
<td align="center" width="150">公会编号</td>
<td align="left" id="groupId">${group.id}</td>
</tr>
<tr >
<td align="center" >公会名称</td>
<td align="left" id="groupName">${group.name}</td>
</tr>
<tr >
<td align="center" >公会等级</td>
<td align="left">&nbsp;${group.level}</td>
</tr>
<tr >
<td align="center" >会长编号</td>
<td align="left">&nbsp;${group.roleId}</td>
</tr>
<tr >
<td align="center" >会长昵称</td>
<td align="left">&nbsp;${group.roleName}</td>
</tr>
<tr >
<td align="center" >当前成员数</td>
<td align="left">&nbsp;${group.curcount}</td>
</tr>
<tr >
<td align="center" >最大成员数</td>
<td align="left">&nbsp;${group.maxcount}</td>
</tr>
<tr >
<td align="center" >公会资金</td>
<td align="left">
<form action="<%=request.getContextPath()%>/bacl_admin.do?comd=addGroupGold" method="post">
<input name="id" type="hidden" value="${group.id}" />
&nbsp;${group.gold}&nbsp;&nbsp;<input name="groupGold" size="10" />
<input type="submit" value="修改" />
</form>
</td>
</tr>
<tr >
<td align="center" >贡献值杠杆</td>
<td align="left">&nbsp;${group.offerlimit}</td>
</tr>
<tr >
<td align="center" >贡献最大值</td>
<td align="left">&nbsp;${group.maxoffer}</td>
</tr>
<tr >
<td align="center" >攻防加成</td>
<td align="left">&nbsp;<fmt:formatNumber value="${group.skb}" type="percent" pattern="#0.00%"/></td>
</tr>
<tr >
<td align="center" >活跃度</td>
<td align="left">&nbsp;<fmt:formatNumber value="${group.actdegree}" type="percent" pattern="#0.00%"/></td>
</tr>
<tr >
<td align="center" >公会公告</td>
<td align="left">&nbsp;${group.notice}</td>
</tr>
<tr >
<td align="center" >公告时间</td>
<td align="left">&nbsp;${group.noticeTime}</td>
</tr>
<tr >
<td align="center" >创建时间</td>
<td align="left">&nbsp;${group.createtime}</td>
</tr>
<tr >
<td align="center" >上次领奖时间</td>
<td align="left">&nbsp;${group.htime}</td>
</tr>
<tr >
<td align="center" >上次活跃度时间</td>
<td align="left">&nbsp;${group.actTime}</td>
</tr>
<c:if test="${my:hasPermission(backUser.roleId,70017)}">
<tr >
<td align="center" colspan="2">
<input onclick="subDissolve(${group.id})" value="强行解除公会" type="button" />
</td>
</tr>
</c:if>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">工会仓库</td></tr>
<tr>
<td align="center" colspan="6">
<form action="<%=request.getContextPath()%>/bacl_admin.do?comd=addGroupItem" method="post">
<input type="hidden" name="id" value="${group.id}" />
道具&nbsp;
<select name="propsId">
<c:forEach var="props" items="${itemList}">
<option <c:if test="${props.id == propsId}">selected="selected"</c:if> value="${props.id}">[${props.id}] ${props.name}</option>
</c:forEach>
</select>
<input type="text" name="propsNum" size="7" >
<input type="submit" value="添加" >
</form>
</td>
</tr>
<tr >
<td align="center" >物品编号</td>
<td align="center" >物品名称</td>
<td align="center" >物品总数</td>
<td align="center" >物品价格</td>
<td align="center" >操作</td>
</tr>
<%
@SuppressWarnings("unchecked")
List<GroupStorageItemVO> storageList  = (List<GroupStorageItemVO>)request.getAttribute("storageList");
if(storageList != null && storageList.size() > 0){
%>
<% for(GroupStorageItemVO storage : storageList){%>
<tr>
<td align="center" ><%=storage.getItemId()%></td>
<td align="center" ><%=storage.getItemName()%></td>
<td align="center" ><%=storage.getCount()%></td>
<td align="center" ><%=storage.getPrice()%></td>
<td align="center" ><input type="button" value="删除" onclick="delGroupItem(${group.id},<%=storage.getItemId()%>)" /></td>
</tr>
<%}%>
<%}%>
</table>
<br/>
<form id="workForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=groupDetail" method="post">
<input id="id" name="id" value="${group.id}" type="hidden" />

<input id="memberIndex" name="memberIndex" value="${memberPaging.pageIndex}" type="hidden" />
<input id="memberTotal" name="totalPage" value="${memberPaging.totalPage}" type="hidden" />

<input id="chatIndex" name="chatIndex" value="${chatPaging.pageIndex}" type="hidden" />
<input id="chatTotal" name="totalPage" value="${chatPaging.totalPage}" type="hidden" />

<input id="messageIndex" name="messageIndex" value="${messagePaging.pageIndex}" type="hidden" />
<input id="messageTotal" name="totalPage" value="${messagePaging.totalPage}" type="hidden" />

<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">公会成员信息</td></tr>
<tr >
<td align="center" >玩家ID</td>
<td align="center" >玩家昵称</td>
<td align="center" >玩家职位</td>
<td align="center" >玩家贡献</td>
<td align="center" >攻防加成</td>
<td align="center" >入会时间</td>
</tr>
<%
@SuppressWarnings("unchecked")
List<GroupMemberVO> memberList = (List<GroupMemberVO>)request.getAttribute("memberList");
if(memberList != null && memberList.size() > 0){
	GroupVO groVo = (GroupVO)request.getAttribute("group");
	int offLimit = groVo.getOfferlimit();
	float skd = groVo.getSkb();
	int level = groVo.getLevel();
	float actDegree = groVo.getActdegree();
	
%>
<% for(GroupMemberVO member : memberList){%>
<tr >
<td align="center" ><%=member.getRoleId()%></td>
<td align="center" ><%=member.getRoleName()%></td>
<td align="center" ><%=member.getPowerName()%></td>
<td align="center" ><input type="text" value='<%=member.getOffer()%>' id="off<%=member.getRoleId()%>" /><input type="button" value="修改"  onclick="setG('<%=member.getRoleId()%>','<%=member.getOffer()%>')"/></td>
<td align="center" ><fmt:formatNumber value="<%=member.getRoleGroupSkb(offLimit, skd, level, actDegree)%>" type="percent" pattern="#0.00%"/> </td>
<td align="center" ><%=member.getJoinTime()%></td>
</tr>
<%}%>
<tr>
<td align="left" colspan="6">
(${memberPaging.pageIndex}/${memberPaging.totalPage})
<a href="javascript:toFirst('member')">首页</a>&nbsp;
<a href="javascript:upPage('member')">上一页</a>&nbsp;
<a href="javascript:downPage('member')">下一页</a>&nbsp;
<a href="javascript:toLast('member')">末页</a>&nbsp;
<input id="memberInput" size="2" type="text" />&nbsp;
<a href="javascript:goTo('member')">Go...</a>&nbsp;
</td>
</tr>
<%}%>
</table>
 
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">公会成员聊天信息(7天)</td></tr>
<tr >
<td align="center" width="5%">序号</td>
<td align="center" width="8%">玩家ID</td>
<td align="center" width="12%">玩家昵称</td>
<td align="center" width="60%">聊天内容</td>
<td align="center" width="15%">时间</td>
</tr>
<c:forEach items="${chatList}" var="chat" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${chat.roleId} </td>
<td align="center" >${chat.roleName}</td>
<td align="left" >${chat.detail}</td>
<td align="center" >${chat.time}</td>
</tr>
</c:forEach>
<c:if test="${chatList!=null && fn:length(chatList) > 0}">
<tr>
<td align="left" colspan="6">
(${chatPaging.pageIndex}/${chatPaging.totalPage})
<a href="javascript:toFirst('chat')">首页</a>&nbsp;
<a href="javascript:upPage('chat')">上一页</a>&nbsp;
<a href="javascript:downPage('chat')">下一页</a>&nbsp;
<a href="javascript:toLast('chat')">末页</a>&nbsp;
<input id="chatInput" size="2" type="text" />&nbsp;
<a href="javascript:goTo('chat')">Go...</a>&nbsp;
</td>
</tr>
</c:if>
</table>
<br/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td align="center" colspan="6" style="color:#DC143C;">公会动态信息(7天)</td></tr>
<tr >
<td align="center" width="5%">序号</td>
<td align="center" width="8%">玩家ID</td>
<td align="center" width="12%">玩家昵称</td>
<td align="center" width="60%">内容</td>
<td align="center" width="15%">时间</td>
</tr>
<c:forEach items="${messageList}" var="message" varStatus="status">
<tr >
<td align="center" >${status.count}</td>
<td align="center" >${message.roleId} </td>
<td align="center" >${message.roleName}</td>
<td align="left" >${message.detail}</td>
<td align="center" >${message.time}</td>
</tr>
</c:forEach>
<c:if test="${messageList!=null && fn:length(messageList) > 0}">
<tr>
<td align="left" colspan="6">
(${messagePaging.pageIndex}/${messagePaging.totalPage})
<a href="javascript:toFirst('message')">首页</a>&nbsp;
<a href="javascript:upPage('message')">上一页</a>&nbsp;
<a href="javascript:downPage('message')">下一页</a>&nbsp;
<a href="javascript:toLast('message')">末页</a>&nbsp;
<input id="messageInput" size="2" type="text" />&nbsp;
<a href="javascript:goTo('message')">Go...</a>&nbsp;
</td>
</tr>
</c:if>
</table>
</form>
<input style="margin-left: 25" type="button" onclick="script:history.go(-1)" value="返回列表"/>
<form id="skbF" name="skbF" action="<%=request.getContextPath()%>/bacl_admin.do?comd=updateRoleById" method="post">

	<input type="hidden"  id="groupId_" name="groupId"/>
	<input type="hidden"  id="roleId_" name="roleId"/>
	<input type="hidden"  id="offer_" name="offer"/>
	
</form>
</body>
 
<script type="text/javascript">

  function setG(g,v){
	if(confirm("确定修改?")){
		var offer=$.trim($("#off"+g).val());
		var groupId=$.trim($("#groupId").text());
		$("#groupId_").val(groupId);
		$("#roleId_").val(g);
		$("#offer_").val(offer);
		skbF.submit();
		}else{
		
		$.trim($("#off"+g).val(v))
		 return false;
		}
	}
</script>
 
</html>