<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
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

			/**
			  * 更新角色的权限，
			  *	
			**/
			
			function updateRolePower(powerId,roleId,checked,parentId){
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
							//showMsg("修改父类成功");	
						}
				});
			}
			//ajax 更新提示
			function showMsg(rs) {
				var rs = "<font>"+rs+" </font>";
				$("#ajaxMsg").html(rs);
				$("#ajaxMsg").css("top",($(document).scrollTop()-75));
				$("#ajaxMsg").show();
				$("#ajaxMsg").animate({"top":$(document).scrollTop()}, "slow");
				setTimeout(hideMsg, 2000);
			}
			function hideMsg() {
			  $("#ajaxMsg").animate({"top":"-75px"}, "slow",function() {$("#ajaxMsg").hide();});
			}
			/**
			* 初始化数据
			* 选中当前角色拥有的权限
			*/
			function init(){
				var roleId = ${roleId};
				$.ajax({
					url : "bacl_admin.do?comd=n65",
					type : "POST",
					data : {roleId:roleId},
					dataType : "json",
					success : function(datas){	
						for(var i=0; i < datas.length; i++){
							var powerId = datas[i]["powerId"];
							var cState = datas[i]["CState"];
							var rState = datas[i]["RState"];
							var uState = datas[i]["UState"];
							var dState = datas[i]["DState"];
							
							
							if(0 != cState){
								document.getElementById( powerId+"_C").checked   =  true;
							}else{
								document.getElementById( powerId+"_C").checked   =  false;
							}
							if(0 != rState){
								document.getElementById( powerId+"_R").checked   =  true;
							}else{
								document.getElementById( powerId+"_R").checked   =  false;
							}
							if(0 != uState){
								document.getElementById( powerId+"_U").checked   =  true;
							}else{
								document.getElementById( powerId+"_U").checked   =  false;
							}
							if(0 != dState){
								document.getElementById( powerId+"_D").checked   =  true;
							}else{
								document.getElementById( powerId+"_D").checked   =  false;
							}
			
						}
					}
				});
			}
			
			/**
			* ajax 更新数据
			* 增加或修改的权限
			*/
			function updateRolePowerStateA(powerId,roleId,permission,checked){
			
			//roleId,powerId,checked,parentId
				//当前模块checkBox
				var checkBoxList = document.getElementsByName(powerId);
				var count = 0;
				for(var i =0;i<checkBoxList.length;i++){
					if(checkBoxList[i].checked == true){
						count = 1;
					}
				}
				count = 1;
				//查询当前模块是否有选中的，有，修改或者增加;无，删除。
				if( 1 == count){
					$.ajax({
						url : "bacl_admin.do?comd=n66",
						type : "POST",
						data : {roleId:roleId,powerId:powerId,checked:checked,permission:permission},
						dataType : "html",
						success : function(rs){	
								if("success" == rs){
									showMsg("修改成功");	
								}
							}
					});
				}else{
					$.ajax({
						url : "bacl_admin.do?comd=n67",
						type : "POST",
						data : {roleId:roleId,powerId:powerId},
						dataType : "html",
						success : function(rs){	
								if("success" == rs){
									showMsg("修改成功");	
								}
							}
					});
				}
		
			}
			/**
			* ajax 更新数据
			* 增加或修改的权限
			*/
			function updateRolePowerState(powerId,roleId,permission,checked,parentId){
		//function updateRolePower(roleId,powerId,checked,parentId){
			//	updateRolePower(powerId,roleId,checked,parentId);
			
				//获取以父类命名的子类checkBox
				var childList = document.getElementsByName(parentId);
				var count = 0;
				//execute 标示当前是否需要对父类进行增加或者删除
				var executeParent = "false";				
				if(false == checked){
					for(var i =0;i<childList.length;i++){
						if(childList[i].checked == true){
							count++;
						}
					}
					if(count == 0){
						//用于当子类全部清除时，父类也同时需要删除
						executeParent = "true";
						document.getElementById(parentId+"_R").checked = false;
					}
				}else{
					for(var i =0;i<childList.length;i++){
						if(childList[i].checked == true){
							count++;
						}
					}
					if(count == 1){
					//	新增父类下第一个子类时，需要同时新增这个父类权限
						executeParent = "true";
						document.getElementById(parentId+"_R").checked = true;
					}
				}
			
				//查询当前模块是否有选中的，有，修改或者增加;
				$.ajax({
					url : "bacl_admin.do?comd=n66",
					type : "POST",
					data : {roleId:roleId,powerId:powerId,checked:checked,permission:permission,executeParent:executeParent,parentId:parentId},
					dataType : "html",
					success : function(rs){	
							if("success" == rs){
								showMsg("修改成功");	
							}
						}
				});
			}
		</script>
	</head>
	<body class="main_body" onload="init()"   >
		 <% 
		     String hint = (String)request.getAttribute("hint");
		     if(hint != null){ 
	     %>
	    	 <center><%=hint %> </center>
	     <%
	     }
	     %>
	     <div id="ajaxMsg" style="font-weight: bold; width: 160px; height: 70px; line-height: 75px; text-align: center; border: 1px solid #D3DEE5; background: url('<%=request.getContextPath()%>/image/admin/menu_bg.gif'); display: none; position: absolute; left: 30%; filter: Alpha(Opacity = 90);">
			修改成功
		</div>
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=n53" method="post" onsubmit="return checkData();" >
			<center><input id="show"  type="text" style="border:0px ;background:#339999;color:red;font-size:18px"  />  </center>
			<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
						<td align="center" > 顶级模块名</td>
						<td align="center" > 子模块名</td>
						<td align="center" >增加&nbsp;查询&nbsp;修改&nbsp;删除</td>
				</tr>
				<c:forEach items="${backPowerVOs}" var="backPowerVO" step="1" varStatus="status">
					<c:if test="${0 == backPowerVO.parentId}">
						<tr> 
							<td align="center" >
						<B> ${backPowerVO.powerName} </B>
							</td>
							<td align="center" >&nbsp;</td>
							<td align="center" >
								&nbsp;<input type="hidden" id="${backPowerVO.powerId }_C" disabled="disabled" />
								&nbsp;<input type="checkbox" id="${backPowerVO.powerId }_R"  disabled="disabled" />
								&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="${backPowerVO.powerId }_U" disabled="disabled" />
								&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="${backPowerVO.powerId }_D" disabled="disabled" />
							</td>
							</tr>
								<c:forEach items="${backPowerVOs}" var="backPowerVOchild" >
									<!-- 判断是否属于这个的子类 -->
									<c:if test="${backPowerVO.powerId == backPowerVOchild.parentId}">
									<tr>
											<td align="center" >&nbsp;</td>
											<td align="center" >${backPowerVOchild.powerName }</td>
											<td align="center" >
												&nbsp;<input type="checkbox" id="${backPowerVOchild.powerId }_C" name="${backPowerVOchild.parentId }" onclick="updateRolePowerState(${backPowerVOchild.powerId },${roleId },0,this.checked,${backPowerVOchild.parentId })"   />
												&nbsp;&nbsp;&nbsp;<input type="checkbox" id="${backPowerVOchild.powerId }_R" name="${backPowerVOchild.parentId }" onclick="updateRolePowerState(${backPowerVOchild.powerId },${roleId },1,this.checked,${backPowerVOchild.parentId })"  />
												&nbsp;&nbsp;&nbsp;<input type="checkbox" id="${backPowerVOchild.powerId }_U" name="${backPowerVOchild.parentId }" onclick="updateRolePowerState(${backPowerVOchild.powerId },${roleId },2,this.checked,${backPowerVOchild.parentId })"  />
												&nbsp;&nbsp;&nbsp;<input type="checkbox" id="${backPowerVOchild.powerId }_D" name="${backPowerVOchild.parentId }" onclick="updateRolePowerState(${backPowerVOchild.powerId },${roleId },3,this.checked,${backPowerVOchild.parentId })"  />
											</td>
									</tr>
									</c:if>
								</c:forEach>
					</c:if>
				</c:forEach>
					<tr> 
						<td colspan="3" align="center" > <input  type="button"  value="返回" onclick="javascript:history.go(-1);" />&nbsp;&nbsp;  </td>
					</tr>
			</table>
		</form>
	</body>
</html>
