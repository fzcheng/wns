<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function checkData(){
				
				if("" == jQuery.trim($("#powerName").val())){
			 		alert("请填写权限名");
			 		$("#powerName").focus();
			 		return false;
		 		 }
				if("" == jQuery.trim($("#powerSn").val())){
			 		alert("请填写权限唯一标示");
			 		$("#powerName").focus();
			 		return false;
		 		 }
		 		 
				return true;
			}
			
			//ajax 检查模块标示
		   function checkPowerSn(powerSn){
		      if(jQuery.trim(powerSn) == ""){
			 		alert("模块标示不能为空");
			 		return false;
		 		 }
		 		 
		 		var jgpattern =/^[A-Za-z]+$/; 
				if(!jgpattern.test(powerSn)){ 
					alert("模块标示请输入字母"); 
					return false; 
				}
		 		 
		 		 
				$.ajax({
					url : "bacl_admin.do?comd=n68",
					type : "POST",
					data : {powerSn:powerSn},
					dataType : "html",
					success : function(rs){	
							if(rs == "true"){
								document.getElementById("tips").innerHTML = "模块标示名已存在";
								document.getElementById("savePower").disabled = true;
							}else{
								document.getElementById("tips").innerHTML = "";
								document.getElementById("savePower").disabled = false;
							}
						}
				});
		  	 }
		</script>
	</head>
	<body class="main_body">
	    <% 
	     String hint = (String)request.getAttribute("hint");
	     if(hint != null){ 
	     %>
	     <center><%=hint %></center>
	     <%
	     }
	     %>
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n53" method="post" onsubmit="return checkData();" >
			<input type="hidden" name="roleName" id="roleName" />
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center"> 权限名称 </td><td align="center"> <input id="powerName" name="powerName" type="text" maxlength="20" /> </td>
					</tr>
					<tr> 
						<td align="center"> 权限地址 </td><td align="center"> <input id="linkUrl" name="linkUrl" type="text" maxlength="100" /> </td>
					</tr>
					<tr> 
						<td align="center"> 父类权限 </td>
						<td align="center"> 
							<select id="parent" name="parentId" >
								<option  value="0" >顶级模块</option> 
								<c:forEach items="${backPowerVOs}" var="backPowerVO">
									<option  value="${backPowerVO.powerId }" >${backPowerVO.powerName }</option> 
								</c:forEach>
							 </select> 
						</td>
					</tr>
					<tr> 
						<td align="center"> 权限唯一标示 </td><td align="center"> <input id="powerSn" name="powerSn" type="text" maxlength="20"  onblur="checkPowerSn(this.value)" /> <div id= "tips" style="color:red" > </div></td>
					</tr>
					<tr> 
						<td align="center"> 权限描叙 </td><td align="center"> <input id="powerDsc" name="powerDsc" type="text" maxlength="50" /> </td>
					</tr>
					<tr> 
						<td colspan="2" align="center" ><input  type="button"  value="取消" onclick="javascript:history.go(-1);" />&nbsp;&nbsp; <input id="savePower" type="submit" value="增加" /> </td>
					</tr>
			</table>
		</form>
	</body>
</html>
