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
	</head>
	
	<!-- 控制宽度的自动适应 -->  
<style type="text/css">
comments {
	width: 100%; /*自动适应父布局宽度*/
	overflow: auto;
	word-break: break-all;
	/*在ie中解决断行问题(防止自动变为在一行显示，主要解决ie兼容问题，ie8中当设宽度为100%时，文本域类容超过一行时，  
11.当我们双击文本内容就会自动变为一行显示，所以只能用ie的专有断行属性“word-break或word-wrap”控制其断行)class="comments" style="height:expression((this.scrollHeight>150)?'150px':(this.scrollHeight+5)+'px');overflow:auto;" */
}
</style> 
	<script type="text/javascript">
 
	</script>
	<body class="main_body">
	     <center><font color="#FF0000">${message}</font></center>
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">工作登记</strong>
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do" method="post">
			<input name="id" value="${work.id}" type="hidden" />
			<input name="c_eTime" value="${c_eTime}" type="hidden" />
			<input name="c_bTime" value="${c_bTime}" type="hidden" />

			<c:if test="${empty work}">
			<input id="comd" name="comd" value="addWork" type="hidden" />
			</c:if>
			<c:if test="${not empty work}">
			<input id="comd" name="comd" value="editWork" type="hidden" />
			</c:if>
			<table border="0" width="60%" style="margin-left: 60" cellpadding="0" cellspacing="0">
					<tr>
					<td>标题：</td>
					<td><input <c:if test="${not empty work && backUser.userName != work.createBy}">readonly="readonly"</c:if>  name="title" size="40" type="text" value="${work.title}">(必填选项)
					</td>
				</tr>
				<tr>
					<td>反馈详情：</td>
					<td><textarea   cols="40" rows="12"   <c:if test="${not empty work && backUser.userName != work.createBy}">  readonly="readonly"  style="background-color: #339999"</c:if> name="content" >${work.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" VALUE="确定">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
