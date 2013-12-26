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
				 var messName = $.trim($("#messName").val());
				 var messContent = $.trim($("#messContent").val());

				if(messName.length == 0){
					 alert("GM抬头不能为空!");
					 return;
				 }
				 if(messName.length > 255){
					 alert("GM抬头字数超出限制255!");
					 return;
				 }

				 if(messContent.length == 0){
					 alert("消息内容不能为空!");
					 return;
				 }
				 if(messContent.length >255){
					 alert("消息内容字数超出限制255!");
					 return;
				 }
				
				 if(confirm("确认发送消息!")){
					 $("#messName").val(messName);
					 $("#messContent").val(messContent);
					 document.textform.action = url;
					 document.textform.submit();
				} 
			}
		</script>
	</head>
	<body class="main_body">
	    <c:if test="${message!=null && message != ''}">
	     <center><font color="#FF0000">${message}</font></center>
		</c:if>
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">GM大喇叭</strong>
	      <c:if test="${my:hasPermission(backUser.roleId,50016)}">
	      <form id="textform" name="textform" action="#"  method="post">
						<table width="50%" style="margin-left: 60">
							<tr>
								<td>消息Title：</td>
								<td align="left">GM<input id="messName" name="messName" size="40" value="${messName}"/></td>
							</tr>
							<tr>
								<td>消息内容：</td>
								<td><textarea id="messContent" name="messContent" cols="40" rows="8">${messContent}</textarea></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="button" onclick="checkData('<%=request.getContextPath()%>/bacl_admin.do?comd=createGmMessage')" VALUE="提交">
									<input type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=n5_3'" VALUE="取消">
								</td>
							</tr>
						</table>
					</form><br/><br/>
			</c:if>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">GM大喇叭列表</strong>
		<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" width="5%">No.</td>
						<td align="center" width="15%">消息标题</td>
						<td align="center" width="60%">消息内容</td>
						<td align="center" width="20%">发送时间</td>
					</tr>
					<c:forEach items="${GmMessList}" var="mess" varStatus="sta">
						<tr> 
							<td align="center">${sta.index+1}</td>
							<td align="center">${mess.messageName}</td>
							<td align="left">${mess.messageContent}</td> 
							<td align="center"><fmt:formatDate value="${mess.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
					</c:forEach>
			</table>
	</body>
</html>
