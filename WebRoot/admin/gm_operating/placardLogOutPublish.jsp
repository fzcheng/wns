<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="my"  uri="/my" %>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function checkData(url){
				 var title = $.trim($("#title").val());
				 var titleColor = $.trim($("#titleColor").val());
				 var contentVal = $.trim($("#contentVal").val());
				 var contentColor = $.trim($("#contentColor").val());
				
				 $("#title").val(title);
				 $("#titleColor").val(titleColor);
				 $("#contentVal").val(contentVal);
				 $("#contentColor").val(contentColor);
				 
				 if(title.length == 0){
					 alert("公告标题不能为空!");
					 return;
				 }
				 if(title.length > 255){
					 alert("公告标题字数超出限制255!");
					 return;
				 }
				 if(titleColor.length > 20){
					 alert("公告标题颜色字数超出限制20!");
					 return;
				 }
				 if(contentVal.length == 0){
					 alert("公告内容不能为空!");
					 return;
				 }
				 if(contentVal.length >255){
					 alert("公告内容字数超出限制255!");
					 return;
				 }
				 if(contentColor.length >20){
					 alert("公告内容颜色字数超出限制20!");
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
	<c:if test="${message != null && message != ''}">
	<center><font color="#FF0000">${message}</font></center>
	</c:if>
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">发布退出公告</strong>
	      <c:if test="${my:hasPermission(backUser.roleId,50022) || my:hasPermission(backUser.roleId,50021)}">
	      <form id="textform" name="textform" action="#"  method="post">
						<input name="id" type="hidden" value="${plaBean.id}">
						<table width="50%" style="margin-left: 60" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>公告标题：</td>
								<td><input id="title" name="title" value="${plaBean.title}" size="40"/></td>
							</tr>
							<tr>
								<td>标题颜色：</td>
								<td>
								<select id="titleColor" name="titleColor">
									<option value="0x000000" <c:if test="${plaBean.titleColor eq '0x000000'}">selected="selected"</c:if>>黑色</option>
									<option value="0xff0000" <c:if test="${plaBean.titleColor eq '0xff0000'}">selected="selected"</c:if>>红色</option>
									<option value="0x0000ff" <c:if test="${plaBean.titleColor eq '0x0000ff'}">selected="selected"</c:if>>蓝色</option>
									<option value="0x00ff00" <c:if test="${plaBean.titleColor eq '0x00ff00'}">selected="selected"</c:if>>绿色</option>
									<option  value="0xffff00"<c:if test="${plaBean.titleColor eq '0xffff00'}">selected="selected"</c:if>>黄色</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>公告内容：</td>
								<td><textarea id="contentVal" name="content" cols="40" rows="8">${plaBean.content}</textarea></td>
							</tr>
							<tr>
								<td>内容颜色：</td>
								<td>
								<select id="contentColor" name="contentColor">
									<option value="0x000000" <c:if test="${plaBean.contentColor eq '0x000000'}">selected="selected"</c:if>>黑色</option>
									<option value="0xff0000" <c:if test="${plaBean.contentColor eq '0xff0000'}">selected="selected"</c:if>>红色</option>
									<option value="0x0000ff" <c:if test="${plaBean.contentColor eq '0x0000ff'}">selected="selected"</c:if>>蓝色</option>
									<option value="0x00ff00" <c:if test="${plaBean.contentColor eq '0x00ff00'}">selected="selected"</c:if>>绿色</option>
									<option value="0xffff00" <c:if test="${plaBean.contentColor eq '0xffff00'}">selected="selected"</c:if>>黄色</option>
								</select>
								</td>
							</tr>
							<c:if test="${my:hasPermission(backUser.roleId,50023)}">
							<tr>
								<td>立即发布</td>
								<td>
									<input name="status" value="0" type="checkbox" <c:if test="${plaBean != null}" >disabled="disabled"</c:if>/>
								</td>
							</tr>
							</c:if>
							<tr>
								<td colspan="2" align="center">
								<c:if test="${plaBean == null}">
									<input type="button" onclick="checkData('<%=request.getContextPath()%>/bacl_admin.do?comd=addPlacardLogOut')" VALUE="提交">
								</c:if>	
								<c:if test="${plaBean != null}">
									<input type="button" onclick="checkData('<%=request.getContextPath()%>/bacl_admin.do?comd=updatePlacardLogOut')" VALUE="更新">
								</c:if>	
									<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=n5_6'" VALUE="取消">
								</td>
							</tr>
						</table>
					</form><br/><br/>
		</c:if>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">公告列表</strong>
		<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" width="5%">No.</td>
						<td align="center" width="15%">公告标题</td>
						<td align="center" width="10%">标题颜色</td>
						<td align="center" width="30%">公告内容</td>
						<td align="center" width="10%">内容颜色</td> 
						<td align="center" width="10%">发布时间</td> 
						<td align="center" width="8%">状态</td>
						<td align="center" width="12%">操作</td>
					</tr>
					<c:forEach items="${plaList}" var="pla" varStatus="sta">
						<tr> 
							<td align="center">${sta.index+1}</td>
							<td align="left">${pla.title}</td>
							<td align="center">${pla.titleColorName}&nbsp;</td>
							<td align="left">${pla.content}</td> 
							<td align="center">${pla.contentColorName}&nbsp;</td>
							<td align="center"><fmt:formatDate value="${pla.publishTime}" pattern="yyyy-MM-dd HH:mm"/>&nbsp;</td>
							<td align="center">${pla.statusName}</td>
							<td align="center">
							<c:if test="${pla.status == 0}">
								<c:if test="${my:hasPermission(backUser.roleId,50024)}">
								<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=toPlaLogOutTop&id=${pla.id}'" value="置顶">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50022)}">
								<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=toEditPlaLogOut&id=${pla.id}'" value="编辑">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50026)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=removePlacardLogOut&id=${pla.id}','确认将公告下线?')" value="下线">
								</c:if>
							</c:if>
							<c:if test="${pla.status == 1}">
								<c:if test="${my:hasPermission(backUser.roleId,50022)}">
								<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=toEditPlaLogOut&id=${pla.id}'" value="编辑">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50023)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=publishPlacardLogOut&id=${pla.id}','确认发布公告?')" value="发布">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50025)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=delPlacardLogOut&id=${pla.id}','确认删除公告?')" value="删除">
								</c:if>
							</c:if>
							<c:if test="${pla.status == 2}">
								<c:if test="${my:hasPermission(backUser.roleId,50022)}">
								<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=toEditPlaLogOut&id=${pla.id}'" value="编辑">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50023)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=publishPlacardLogOut&id=${pla.id}','确认发布公告?')" value="发布">
								</c:if>
								<c:if test="${my:hasPermission(backUser.roleId,50025)}">
								<input type="button" onclick="confirmExcute('<%=request.getContextPath()%>/bacl_admin.do?comd=delPlacardLogOut&id=${pla.id}','确认删除公告?')" value="删除">
								</c:if>
							</c:if>
							</td>
						</tr>
					</c:forEach>
			</table>
	</body>
</html>
