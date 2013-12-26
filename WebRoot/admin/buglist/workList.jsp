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

			function delWork(id){
				if(confirm("确认删除一条记录!")){
					$("#id").val(id);
					$("#comd").val("delWork");
					workForm.submit();
				}
			}
			
			
			function workDetail(id){
				$("#id").val(id);
				$("#comd").val("toEditWork");
				workForm.submit();
			}
		</script>
	</head>
	<body class="main_body">
	<center><font color="#FF0000">${message}</font></center><br>
	<a href="<%=request.getContextPath()%>/bacl_admin.do?comd=toEditWork">工作录入</a>
		<form id="workForm" method="post" action="<%=request.getContextPath()%>/bacl_admin.do">
		<input id="comd" name="comd" value="workList" type="hidden" />
		<input id="id" name="id"  type="hidden" />
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr> 
						<td align="center" colspan="12" > 
							<strong>客服交接列表</strong>
						</td>
					</tr>
					<tr> 
						<td align="center" colspan="4" > 
						开始时间：<input id="bTime" name="c_bTime"  value="${c_bTime}" size="15" type="text" readonly="readonly" onClick="setDayHM(this);">&nbsp;&nbsp;
						结束时间：<input id="eTime" name="c_eTime"  value="${c_eTime}" size="15" type="text" readonly="readonly" onClick="setDayHM(this);">&nbsp;&nbsp;
						<input type="submit" value="查询" />
						</td>
					</tr>
					<tr> 
						<td width="20%" align="center">发布时间</td>
						<td width="20%"  align="center">发布人</td>
						<td width="40%"  align="center">描述</td>
						<td width="20%" align="center">操作 </td>
					</tr>
				<c:if test="${not empty wortList}">
				<c:forEach var="work" items="${wortList}">
						<tr> 
							<td align="center"><fmt:formatDate value='${work.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
							<td align="center">${work.createBy}</td>
							<td align="center">${work.title}</td>
							<td align="center">
								<a href="javascript:workDetail(${work.id})">查看</a>&nbsp;
								<c:if test="${backUser.userName eq work.createBy}">
								<a href="javascript:delWork(${work.id})">删除</a>
								</c:if>
							</td>	
						</tr>
				</c:forEach>
				</c:if>
			</table>
		</form>
	</body>
</html>
