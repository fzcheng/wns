<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="my"  uri="/my" %>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script language="JavaScript">
			function checkData(url){
				 var actName = $.trim($("#actName").val());
				 var actContent = $.trim($("#actContent").val());
				 var beginTime = $.trim($("#beginTime").val());
				 var endTime = $.trim($("#endTime").val());
				
				 $("#actName").val(actName);
				 $("#actContent").val(actContent);
				 
				 if(actName.length == 0){
					 alert("活动名称不能为空!");
					 return;
				 }
				 if(actName.length > 255){
					 alert("活动名称字数超出限制255!");
					 return;
				 }
				 if(actContent.length == 0){
					 alert("活动内容不能为空!");
					 return;
				 }
				 if(actContent.length >255){
					 alert("活动内容字数超出限制255!");
					 return;
				 }
				 if(beginTime.length == 0){
					 alert("开始时间不能为空!");
					 return;
				 }
				 if(endTime.length == 0){
					 alert("结束不能为空!");
					 return;
				 }
				 if(strToDate(endTime).getTime() <= strToDate(beginTime).getTime()){
					 alert("结束时间不能小于等于开始时间!");
					 return;
				 }
				 document.textform.action = url;
				 document.textform.submit();
			}
			
			function confirmExcute(url,mess){
				if(confirm(mess)){
					window.location.href = url;	
				}
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
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">发布活动</strong>
		<c:if test="${my:hasPermission(backUser.roleId,50002) || my:hasPermission(backUser.roleId,50003)}">
		<form id="textform" name="textform" action="#"  method="post">
						<input name="id" type="hidden" value="${actBean.id}">
						<table width="50%" border="0" style="margin-left: 60" cellpadding="0" cellspacing="0">
							<tr>
								<td>活动名称：</td>
								<td><input id="actName" name="actName" value="${actBean.actName}" size="40"/></td>
							</tr>
							<tr>
								<td>活动内容：</td>
								<td><textarea id="actContent" name="actContent" cols="40" rows="8">${actBean.actContent}</textarea></td>
							</tr>
							<tr>
								<td>开始时间：</td>
								<td><input id="beginTime" name="beginTime"  value="<fmt:formatDate value='${actBean.beginTime}' pattern='yyyy-MM-dd HH:mm'/>" size="15" type="text" readonly="readonly" onClick="setDayHM(this);"></td>
							</tr>
							<tr>
								<td>结束时间：</td>
								<td><input id="endTime" name="endTime" value="<fmt:formatDate value='${actBean.endTime}' pattern='yyyy-MM-dd HH:mm'/>" size="15" type="text" readonly="readonly" onClick="setDayHM(this);"></td>
							</tr>
							<c:if test="${my:hasPermission(backUser.roleId,50004)}">
							<tr>
								<td>立即发布</td>
								<td>
									<input name="status" value="0" type="checkbox" <c:if test="${actBean != null}" >disabled="disabled"</c:if>/>
								</td>
							</tr>
							</c:if>
							<tr>
								<td colspan="2" align="center">
								<c:if test="${actBean == null}">
									<input type="button" onclick="checkData('<%=request.getContextPath()%>/bacl_admin.do?comd=createActivity')" VALUE="提交">
								</c:if>	
								<c:if test="${actBean != null}">
									<input type="button" onclick="checkData('<%=request.getContextPath()%>/bacl_admin.do?comd=updateActivity')" VALUE="更新">
								</c:if>	
									<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=n5_1'" VALUE="取消">
								</td>
							</tr>
						</table>
					</form><br/><br/>
		</c:if>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">活动列表</strong>
		<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" width="5%">No.</td>
						<td align="center" width="15%">活动名称</td>
						<td align="center" width="40%">活动内容</td>
						<td align="center" width="10%">开始时间</td>
						<td align="center" width="10%">结束时间</td> 
						<td align="center" width="8%">状态</td>
						<td align="center" width="12%">操作</td>
					</tr>
					<c:forEach items="${actList}" var="act" varStatus="sta">
						<tr> 
							<td align="center">${sta.index+1}</td>
							<td align="left" style="word-break:break-all;">${act.actName}</td>
							<td align="left" style="word-break:break-all;">${act.actContent}</td>
							<td align="center"><fmt:formatDate value="${act.beginTime}" pattern="yyyy-MM-dd HH:mm"/></td> 
							<td align="center"><fmt:formatDate value="${act.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td align="center">${act.statusName}</td>
							<td align="center">
							<c:if test="${act.status == 0}">
								<c:if test="${my:hasPermission(backUser.roleId,50005)}">
								<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=toActTop&id=${act.id}'" value="置顶">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50003)}">
								<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=toEditAct&id=${act.id}'" value="编辑">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50007)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=removeActivity&id=${act.id}','确认将活动下线?')" value="下线">
								</c:if>
							</c:if>
							<c:if test="${act.status == 1}">
								<c:if test="${my:hasPermission(backUser.roleId,50003)}">
								<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=toEditAct&id=${act.id}'" value="编辑">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50004)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=publishActivity&id=${act.id}','确认发布活动?')" value="发布">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50006)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=deleteActivity&id=${act.id}','确认删除活动?')" value="删除">
								</c:if>
							</c:if>
							<c:if test="${act.status == 2}">
								<c:if test="${my:hasPermission(backUser.roleId,50003)}">
								<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=toEditAct&id=${act.id}'" value="编辑">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50004)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=publishActivity&id=${act.id}','确认发布活动?')" value="发布">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50006)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=deleteActivity&id=${act.id}','确认删除活动?')" value="删除">
								</c:if>
							</c:if>
							</td>
						</tr>
					</c:forEach>
			</table>
	</body>
</html>
