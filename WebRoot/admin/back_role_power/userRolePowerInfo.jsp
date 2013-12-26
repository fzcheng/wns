<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
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
				return true;
			}
			function closeWindow(){
				window.close();
			}
			/**
			  * 更新角色的权限，
			  *	
			**/
			function updateRolePower(roleId,powerId,checked,parentId){
				//获取以父类命名的子类checkBox
				var sonList = document.getElementsByName(parentId);
				var count = 0;
				//execute 标示当前是否需要对父类进行增加或者删除
				var execute = "false";				
				if(false == checked){
					for(var i =0;i<sonList.length;i++){
						if(sonList[i].checked == true){
							count++;
						}
					}
					if(count == 0){
						//用于当子类全部清除时，父类也同时需要删除
						execute = "true";
					}
				}else{
					for(var i =0;i<sonList.length;i++){
						if(sonList[i].checked == true){
							count++;
						}
					}
					if(count == 1){
					//	新增父类下第一个子类时，需要同时新增这个父类权限
						execute = "true";
					}
				}
				
				//ajax 更新数据
				$.ajax({
					url : "bacl_admin.do?comd=n60",
					type : "POST",
					data : {roleId:roleId,powerId:powerId,checked:checked,execute:execute,parentId:parentId},
					dataType : "html",
					success : function(rs){	
							showMsg("修改成功");	
						}
				});
			}
			
			//软提示信息
			function showMsg(rs) {
				$("#show").attr("value",rs);
				setTimeout(hideMsg, 1000);
			}
			
			function hideMsg() {
			  $("#show").attr("value","");
			} 

		</script>
	</head>
	<body class="main_body">
		 <% 
		     String hint = (String)request.getAttribute("hint");
		     if(hint != null){ 
	     %>
	    	 <center><%=hint %> </center>
	     <%
	     }
	     %>
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n53" method="post" onsubmit="return checkData();" >
			<center><input id="show"  type="text" style="border:0px solid #c00;background:#339999;color:red"  />  </center>
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
				<c:forEach items="${backPowerVOs}" var="backPowerVO" step="1" varStatus="status">
					<c:if test="${0 == backPowerVO.parentId}">
						<tr> 
							<td >
					<!--  <input type="checkbox" id="${roleId }_${backPowerVO.powerId }" name="${backPowerVO.powerId }_${backPowerVO.parentId }"  disabled="disabled"  />-->	
						<B> ${backPowerVO.powerName} </B>
								<c:forEach items="${backPowerVOs}" var="backPowerVOSon" >
									<!-- 判断是否属于这个的子类 -->
									<c:if test="${backPowerVO.powerId == backPowerVOSon.parentId}">
											<input type="checkbox" id="${backPowerVOSon.powerId }" name="${backPowerVOSon.parentId }" onchange="updateRolePower( ${roleId },${backPowerVOSon.powerId },this.checked,${backPowerVOSon.parentId } )" />
											${backPowerVOSon.powerName }
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:forEach items="${backRolePowerVOs}" var="backRolePowerVO" step="1" varStatus="status">
					<script type="text/javascript"> 
						document.getElementById("${backRolePowerVO.powerId}").checked =true;
					</script>
				</c:forEach>
				
					<tr> 
						<td colspan="2" align="center" > <input  type="button"  value="返回" onclick="javascript:history.go(-1);" />&nbsp;&nbsp; </td>
					</tr>
			</table>
		</form>
	</body>
</html>
