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
		<input id="comd" name="comd" value="consumerecordSumList" type="hidden" />
		<input id="id" name="id" value="" type="hidden" />
		<input id="index" name="c_index" value="${paging.pageIndex}" type="hidden" />
		<input id="roleId2" name="roleId2" value="${roleId}" type="hidden" />
		<input id="totalPage" name="totalPage" value="${paging.totalPage}" type="hidden" />
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr> 
						<td align="center" colspan="12" > 
							<strong>奖励点汇总信息</strong>
						</td>
					</tr>
					<tr> 
						<td align="center" colspan="12" > 
						玩家ID：<input id="roleId" name="roleId" value="${roleId}" type="text" size="15" />&nbsp;&nbsp;
						物品ID：<input id="goodsId" name="goodsId" value="${goodsId}" type="text" size="15" />&nbsp;&nbsp;
							&nbsp; 开始时间：
						<input id="sTime" name="beginTime" type="text" value="${beginTime}"
							onClick="setDay(this);" size="15" readonly="readonly" />
						&nbsp; 结束时间：
						<input id="eTime" name="endTime" type="text" value="${endTime}"
							onClick="setDay(this);" size="15" readonly="readonly" />
						&nbsp;
						<input type="submit" value="查询" />
						</td>
					</tr>
					<tr> 
						<td align="center">玩家ID</td>
						<td align="center">玩家昵称</td>
						<td align="center">物品ID</td>
						<td align="center">物品名称</td> 
						<td align="center">消耗奖励点</td> 
					</tr>
				<c:if test="${not empty consumerecordSumList}">
				<c:forEach var="rk" items="${consumerecordSumList}" varStatus="status" begin="0" >
						<tr> 
							<td align="center">${rk.roleId}</td>
							<td align="center"><a href="#" onclick="getInfo('${rk.roleId}')">${rk.roleName}</td>
							<td align="center">${rk.goodsId}</td>
							<td align="center">${rk.goodsName}</td>
							<td align="center">${rk.sumgoodsMoney}</td>
						</tr>
						<c:if test="${consumerecordSumList[status.count].allMoney ne rk.allMoney }">
							<tr style="background-color: #ccc">
								<td colspan="4" align="right">总消耗：</td>
								<td  align="center" style="float: right;">${rk.allMoney}</td>
							</tr>
						
						</c:if>
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
	<script type="text/javascript">
	function  getInfo(grpId){
	 	window.location.href="bacl_admin.do?comd=consumerecordByIdList&roleId="+grpId;
		
	}
	</script>
</html>
