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
		<input id="comd" name="comd" value="bugList" type="hidden" />
		<input id="id" name="id" value="" type="hidden" />
		<input id="index" name="c_index" value="${paging.pageIndex}" type="hidden" />
		<input id="totalPage" name="totalPage" value="${paging.totalPage}" type="hidden" />
			<input id="type" type="hidden" name="c_type" value="${type}" >
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" >
				<tr> 
						<td align="center" colspan="12" > 
							<strong>客服问题反馈信息列表</strong>
						</td>
					</tr>
					<tr> 
						<td align="center" colspan="12" > 
						开始时间：<input id="bTime" name="c_bTime"  value="${bTime}" size="15" type="text" readonly="readonly" onClick="setDayHM(this);">&nbsp;&nbsp;
						结束时间：<input id="eTime" name="c_eTime"  value="${eTime}" size="15" type="text" readonly="readonly" onClick="setDayHM(this);">&nbsp;&nbsp;
						玩家ID：<input name="c_roleId" value="${roleId}" type="text" size="15" />&nbsp;&nbsp;
						问题类别：
						<select onchange="getTypeList()" id="c_status" name="c_status">
						 <option <c:if test="${0 eq status}">selected="selected"</c:if> value="0">全部</option>
						 <option <c:if test="${1 eq status}">selected="selected"</c:if> value="1">未处理 </option>
						 <option <c:if test="${2 eq status}">selected="selected"</c:if> value="2">处理中</option>
						 <option <c:if test="${4 eq status}">selected="selected"</c:if> value="4">已处理</option>
						 <option value="3">信息不全</option>
						</select>
						
						<input type="submit" value="查询" />
						</td>
					</tr>
					<tr> 
						<td align="center">登记时间</td>
						<td align="center">玩家ID</td>
						<td align="center">玩家昵称</td>
						<td align="center">问题登记分类</td>
						<td align="center">优先级</td>
						<td align="center">提交者昵称</td> 
						<td align="center">处理者昵称</td> 
						<td align="center">处理状态</td> 
						<td align="center">操作 </td>
					</tr>
				<c:if test="${not empty bugList}">
				<c:forEach var="bug" items="${bugList}">
						<tr> 
							<td align="center"><fmt:formatDate value='${bug.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
							<td align="center">${bug.roleId}</td>
							<td align="center">${bug.roleName}</td>
							<td align="center">
							<c:if test="${bug.type == 0}">其他→${bug.other}</c:if>
							<c:if test="${bug.type != 0}">${bug.ptypeName}→${bug.typeName}</c:if>
							</td>
							<td align="center">${bug.priority}级</td> 
							<td align="center">${bug.createBy}</td>
							<td align="center">${bug.answerBy}&nbsp;</td>
							<td align="center">${bug.statusName}</td>
							<td align="center">
							<c:if test="${my:hasPermission(backUser.roleId,130006)}">
								<a href="javascript:kfDetail(${bug.id})">客服查看</a>
							</c:if>
							<c:if test="${my:hasPermission(backUser.roleId,130007)}">
								<a href="javascript:yyDetail(${bug.id})">运营查看</a>
							</c:if>
							<c:if test="${backUser.userName eq bug.createBy}"><a href="javascript:delBug(${bug.id})">删除</a></c:if>
							</td>
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
