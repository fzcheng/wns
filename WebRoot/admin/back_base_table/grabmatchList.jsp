<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="admins.ben.analyse.DayOpsummaryVO"%>
<%@ taglib prefix="my"  uri="/my" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/refresh.js"></script>
		<script language="JavaScript">
		
			function getTypeList(){
				$("#index").val("1");
			}
			
			function clear() {   
			      var selectobj = $("#sel2").get(0);   
			      var s = selectobj.options;   
			      var n = s.length;   
			      if (s && n > 0) {   
			          for (var i = 0; i < n; i++) {   
			              selectobj.removeChild(s[0]);   
			          }    
			      }   
		   } 
			
			function checkData(){
				var typeId = $("#sel1").val();
				var roleid = $("#roleId").val();
				var roleid2 = $("#roleId2").val();
				if(roleid !=roleid2){
					$("#index").val("1");	
				}
				if(typeId == 0){
					$("#type").val(0);
				}else{
					$("#type").val($("#sel2").val());
				}
				return true;
			}
			
			
			function subPage(input){
				$("#index").val(input);
			    workForm.submit();
			}
		
			function goTo(){
				var index = $.trim($("#input").val());
				var total = $("#totalPage").val();
			    var reg = new RegExp("^[0-9]*$");
			    if(index=='' || !reg.test(index) || parseInt(index) > parseInt(total)){
			        alert("请输入正确的页数!");
			        return;
			    }
			    subPage(index);
			}
			
			function downPage(){
				var index = $("#index").val();
				var total = $("#totalPage").val();
				if(parseInt(index) >= parseInt(total)){
					alert("已经是最后一页!");
					return;
				}
				subPage(parseInt(index)+1);
			}
			
			function upPage(){
				var index = $("#index").val();
				if(index == 1){
					alert("已经是第一页!");
					return;
				}
				subPage(parseInt(index)-1);
			}
			
			function toFirst(){
				subPage(1);
			}
			
			function toLast(){
				var index = $("#totalPage").val();
				subPage(index);
			}
			 
			function delBug(id){
				if(confirm("确认删除一条记录!")){
					$("#id").val(id);
					$("#comd").val("delBug");
					workForm.submit();
				}
			}
			
			function kfDetail(id){
				$("#id").val(id);
				$("#comd").val("kfBugDetail");
				workForm.submit();
			}
			
			function yyDetail(id){
				$("#id").val(id);
				$("#comd").val("yyBugDetail");
				workForm.submit();
			}
		</script>
	</head>
	<body class="main_body">
	<center><font color="#FF0000">${message}</font></center>
		<form id="workForm" onsubmit="return checkData()" method="post" action="<%=request.getContextPath()%>/bacl_admin.do">
		<input id="comd" name="comd" value="grabmatchList" type="hidden" />
		<input id="id" name="id" value="" type="hidden" />
		<input id="index" name="c_index" value="${paging.pageIndex}" type="hidden" />
		<input id="roleId2" name="roleId2" value="${roleId}" type="hidden" />
		<input id="totalPage" name="totalPage" value="${paging.totalPage}" type="hidden" />
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr> 
						<td align="center" colspan="12" > 
							<strong>地榜排名</strong>
						</td>
					</tr>
					<tr> 
						<td align="center" colspan="12" > 
						玩家ID：<input id="roleId" name="roleId" value="${roleId}" type="text" size="15" />&nbsp;&nbsp;
						<input type="submit" value="查询" />
						</td>
					</tr>
					<tr> 
						<td align="center">玩家ID</td>
						<td align="center">玩家昵称</td>
						<td align="center">门派</td>
						<td align="center">攻击</td> 
						<td align="center">防御</td> 
					</tr>
				<c:if test="${not empty grabmatchList}">
				<c:forEach var="gb" items="${grabmatchList}">
						<tr> 
							<td align="center">${gb.roleId}</td>
							<td align="center">${gb.roleName}</td>
							<td align="center">${gb.roleFa}</td>
							<td align="center">${gb.atk}</td>
							<td align="center">${gb.def}</td>
						</tr>
				</c:forEach>
				<tr>
				<td align="center" colspan="12">
				(${paging.pageIndex}/${paging.totalPage})
				<a href="javascript:toFirst('member')">首页</a>&nbsp;
				<a href="javascript:upPage('member')">上一页</a>&nbsp;
				<a href="javascript:downPage('member')">下一页</a>&nbsp;
				<a href="javascript:toLast('member')">末页</a>&nbsp;
				<input id="input" size="2" type="text" />&nbsp;
				<a href="javascript:goTo('member')">Go...</a>&nbsp;
				</td>
				</tr>	
				</c:if>
			</table>
		</form>
	</body>
</html>
