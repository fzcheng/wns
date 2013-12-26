<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script language="JavaScript">
			function checkData(){
				 var roleId = $.trim($("#roleId").val());
				 var roleName = $.trim($("#roleName_cx").val());
				 var roleId1 = $.trim($("#roleId1").val());
				 var roleName1 = $.trim($("#roleName1_cx").val());
				 
				 if(roleName.length > 0 && roleName.length < 2){
					 alert("昵称必须超过2个字符!");
					 return;
				 }
				 $("#roleId_sf").val(roleId);
				 $("#roleName_sf").val(roleName);
				 $("#roleId1_sf").val(roleId1);
				 $("#roleName1_sf").val(roleName1);
				 document.searchForm.submit();
			}
			
			function excuteBan(id,type,roleId){
				var cutOffTime = $.trim($("#cutTime_"+id).val());
				var reason = $.trim($("#reason_"+id).val());

				if(cutOffTime.length == 0){
					 alert("截止时间不能为空!");
					 return;
				 }
				var myDate = new Date();
				if(strToDate(cutOffTime).getTime() <= myDate.getTime()){
					alert("截止时间不能小于等于当前时间!");
					 return;
				}

				if(cutOffTime.length == 0){
					 alert("截止时间不能为空!");
					 return;
				 }
				 if(reason.length > 255){
					 alert("理由字数超出限制255!");
					 return;
				 }
				 if(reason.length == 0){
					 alert("必须填写理由!");
					 return;
				 }
				 
				 $("#roleId_s").val(roleId);
				 $("#type_s").val(type);
				 $("#reason_s").val(reason);
				 $("#cutTime_s").val(cutOffTime);
				
				 document.workForm.submit();
			}

		</script>
	</head>
	<body class="main_body">
	    <%
	    String message = (String)request.getAttribute("message");
	     if(message != null){
	     %>
	     <center><font color="#FF0000"><%=message%></font></center>
	     <%
	     }
	     %>
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">禁言/封号</strong>
		<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
				<td colspan="7">
				玩家编号：<input id="roleId" name="roleId" value="${roleId}" size="10"/>&nbsp;&nbsp;
				玩家昵称：<input id="roleName_cx" name="roleName" value="${roleName}" size="20"/>&nbsp;&nbsp;
				<input type="button" onclick="checkData()" VALUE="查询">
				</td>
				</tr>
				<tr> 
					<td align="center" width="5%">No.</td>
					<td align="center" width="10%">玩家编号</td>
					<td align="center" width="25%">玩家昵称</td>
					<td align="center" width="30%">原因</td>
					<td align="center" width="12%">截止时间</td>
					<td align="center" width="10%">状态</td>
					<td align="center" width="18%">操作</td>
				</tr>
				<c:forEach items="${resultList}" var="las" varStatus="sta">
					<tr> 
						<td align="center">${sta.index+1}&nbsp;</td>
						<td align="center">${las.roleId}&nbsp;</td>
						<td align="left" style="word-break:break-all;">${las.roleName}&nbsp;</td>
						<td align="left" style="word-break:break-all">
						<input id="reason_${sta.index+1}" value="${las.reason}" name="reason" style="width: 98%;" >
						</td>
						<td align="center">
						<input id="cutTime_${sta.index+1}" name="cutOfftime" style="width: 98%;" value="<fmt:formatDate value="${las.cutOffTime}" pattern="yyyy-MM-dd HH:mm"/>" readonly="readonly" onClick="setDayHM(this);">
						</td>
						<td align="center">${las.statusName}&nbsp;</td>
						<td align="center">
						<c:if test="${las.type == 1 && !las.isAutoRemove}">
							<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=updateLas&id=${las.id}&type=1'" value="解禁">
							<input type="button" onclick="excuteBan(${sta.index+1},0,${las.roleId})" value="封号">
						</c:if>
						<c:if test="${las.type == 0 && !las.isAutoRemove}">
							<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=updateLas&id=${las.id}&type=0'" value="解封">
						</c:if>
						<c:if test="${las.type == -1 || las.isAutoRemove}">
							<input type="button" onclick="excuteBan(${sta.index+1},1,${las.roleId})" value="禁言">
							<input type="button" onclick="excuteBan(${sta.index+1},0,${las.roleId})" value="封号">
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</table><br/><br/>
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">禁言/封号记录</strong>
		<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
				<td colspan="7">&nbsp;
				玩家编号：<input id="roleId1" name="roleId1" value="${roleId1}" size="10"/>&nbsp;&nbsp;
				玩家昵称：<input id="roleName1_cx" name="roleName1" value="${roleName1}" size="20"/>&nbsp;&nbsp;
				<input type="button" onclick="checkData()" VALUE="查询">
				</td>
				</tr>
				<tr> 
					<td align="center" width="5%">No.</td>
					<td align="center" width="10%">玩家编号</td>
					<td align="center" width="25%">玩家昵称</td>
					<td align="center" width="30%">原因</td>
					<td align="center" width="12%">截止时间</td>
					<td align="center" width="10%">状态</td>
					<td align="center" width="18%">操作</td>
				</tr>
				<c:forEach items="${resultList1}" var="las" varStatus="sta">
					<tr> 
						<td align="center">${sta.index+1}&nbsp;</td>
						<td align="center">${las.roleId}&nbsp;</td>
						<td align="left" style="word-break:break-all;">${las.roleName}&nbsp;</td>
						<td align="left" style="word-break:break-all">${las.reason}</td>
						<td align="center"><fmt:formatDate value="${las.cutOffTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td align="center">${las.statusName}&nbsp;</td>
						<td align="center">
						<c:if test="${las.type == 1 && las.isValid == 1 && !las.isAutoRemove}">
							<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=updateLas&id=${las.id}&type=1'" value="解禁">
						</c:if>
						<c:if test="${las.type == 0 && las.isValid == 1 && !las.isAutoRemove}">
							<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=updateLas&id=${las.id}&type=0'" value="解封">
						</c:if>
						<c:if test="${las.isAutoRemove || las.isValid == 0}">&nbsp;</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			<form id="workForm" name="workForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=createLas" method="post">
				<input id="roleId_s"  name="roleId" type="hidden" value="">
				<input id="type_s"  name="type" type="hidden" value="">
				<input id="cutTime_s"  name="cutOffTime" type="hidden" value="">
				<input id="reason_s"  name="reason" type="hidden" value="">
			</form>
			<form id="searchForm" name="searchForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n5_4" method="post">
				<input id="roleId_sf" name="roleId" type="hidden" />
				<input id="roleName_sf" name="roleName" type="hidden" />
				<input id="roleId1_sf" name="roleId1" type="hidden" />
				<input id="roleName1_sf" name="roleName1" type="hidden" />
			</form>
	</body>
</html>
