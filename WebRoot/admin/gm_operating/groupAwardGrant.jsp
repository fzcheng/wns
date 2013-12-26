<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function sDiv(id){
				$("#div1").hide();
				$("#div2").hide();
				$("#div3").hide();
				$("#div4").hide();
				$("#div5").hide();
				$("#div"+id).show();
				if(id == 4){
					getWeapon(1);
				}
			}

			function queryGroup(){
				removeAll();
				var groupName = $.trim($("#groupName").val());
				var groupId = $.trim($("#groupId").val());
				$.ajax({   
		              type: "POST",   
		              url: "bacl_admin.do",   
		              data: "comd=queryGroup&groupName=" + groupName+"&groupId="+groupId,   
		              dataType : "json",   
		              success: function(data) {
		            	  var list = data.menbers;
		            	  if(list){
		            		  var str = "<table>";
		            		  var len = list.length;
		            		
		            		  for (var i = 0; i < len; i++){
		            			  str += "<tr><td><input type='checkbox' value='"+list[i]['roleId']+"' name='roleIds' checked='checked' />";
		            			  str += "["+list[i]['roleId']+"]" + list[i]['roleName'] +"</td>";
		            			  i++;
		            			  if(i < len){
			            			  str += "<td><input type='checkbox' value='"+list[i]['roleId']+"' name='roleIds' checked='checked' />";
			            			  str += "["+list[i]['roleId']+"]" + list[i]['roleName'] +"</td>";  
		            			  }else{
		            				  str += "<td>&nbsp;</td>";
		            				  str += "<td>&nbsp;</td></tr>";
		            				  break;
		            			  }
		            			  i++;
		            			  if(i < len){
		            				  str += "<td><input type='checkbox' value='"+list[i]['roleId']+"' name='roleIds' checked='checked' />";
			            			  str += "["+list[i]['roleId']+"]" + list[i]['roleName'] +"</td></tr>";  
		            			  }else{
		            				  str += "<td>&nbsp;</td></tr>";
		            				  break;
		            			  }
		            			  
		            		  }
		            		  str += "</table>";
		            		  $("#result").html(str);
		            	  }else{
		            		  $("#result").text("Not Found !!!"); 
		            	  }
		              }   
		          });
			}
			
			function getWeapon(type){
				 $.ajax({   
		              type: "POST",   
		              url: "bacl_admin.do",   
		              data: "comd=getWeapon&parent=" + type,   
		              dataType : "xml",   
		              success: function(xml) {  
		            	  clear();  
		                  $(xml).find("OPTION").each(function(){
		                	  var option = $(this);
		                	  var val = option.find("VAL").text();
		                	  var name = option.find("TEXT").text();
		                	  
		                	  var option = document.createElement("option");   
	                          option.setAttribute("value",val);   
	                          var text = document.createTextNode("["+val+"] "+name);   
	                          option.appendChild(text);   
	                          $("#panoply").get(0).appendChild(option); 
		                  });  
		              }   
		          });  

			}
			
		    function clear() {   
		      var selectobj = $("#panoply").get(0);   
		      var s = selectobj.options;   
		      var n = s.length;   
		      if (s && n > 0) {   
		          for (var i = 0; i < n; i++) {   
		              selectobj.removeChild(s[0]);   
		          }    
		      }   
		    } 
		   
		   function addAward(id){
			   var groupName = $("#groupName_js").val();
			   var quatity = $("#iValue"+id).val();
			   var objType = id;
			   var objId;
			   var objDesc;
			   
			   if(id == 1){
				   objId = "Gold";
				   objDesc = "金币奖励";
			   }else if(id == 2){
				   objId = "Point";
				   objDesc = "奖励点奖励";
			   }else if(id == 3){
				   objId = $("#props").val();
				   var text = $("#props option:selected").text();
				   objDesc = text.substr(text.indexOf("]")+1);
			   }else if(id == 4){
				   objId = $("#panoply").val();
				   var text = $("#panoply option:selected").text();
				   objDesc = text.substr(text.indexOf("]")+1);
			   }else if(id == 5){
				   objId = "Score";
				   objDesc = "积分奖励";
			   }
			   
			   var reg = new RegExp("^[0-9]*$");
			   
			   if(quatity.length ==0 || !reg.test(quatity)){
				   alert("请输入正确的数量!");
				   return;
			   }else{
				   $.ajax({   
		              type: "POST",   
		              url: "bacl_admin.do",   
		              data: "comd=addGroupAward&objId="+objId+"&objDesc="+objDesc+"&objType="+objType+"&quatity="+quatity,   
		              dataType : "json",   
		              success: function(data) {
		            	  removeAll();
		            	  $.each(data.awards,function(idx,item){ 
		            		  $("<tr align='center'><td>"+item.objId+"</td><td>"+item.objDesc+"</td><td>"+item.quatity+"</td><td><input onclick=remove('"+item.objId+"') type='button' value='Remove'></td></tr>").insertAfter($("#table1 tr:eq(0)"));
		            	  });
					  }   
				   }); 
			   }   
		   }
		   
		   function removeAll(){
			   $("#table1 tr:not(:first)").remove();
		   }
		   
		   function remove(objId){
			   $.ajax({   
		              type: "POST",   
		              url: "bacl_admin.do",   
		              data: "comd=removeGroupAward&objId="+objId,   
		              dataType : "json",   
		              success: function(data) {
		            	  removeAll();
		            	  $.each(data.awards,function(idx,item){ 
		            		  $("<tr align='center'><td>"+item.objId+"</td><td>"+item.objDesc+"</td><td>"+item.quatity+"</td><td><input onclick=remove('"+item.objId+"') type='button' value='Remove'></td></tr>").insertAfter($("#table1 tr:eq(0)"));
		            	  });
					  }   
				   }); 
		   }
		   
		   function confirmAward(){
			   var trs = $("#table1").find("tr");
			   if(trs.length <= 1){
				   alert("请先添加奖励!");
				   return false;
			   }
			   var check = $("input[type='checkbox']:checked");

			   if(check.length == 0){
				   alert("请先选择玩家!");
				   return false;
			   }else if(confirm("确认奖励玩家!")){
				   var roleIds = "";
				   for(var i = 0; i<check.length; i++){
					    if(i == check.length-1){
					    	roleIds += check[i].value;
					    }else{
					    	roleIds += check[i].value + ",";
					    }
				   }
				   alert(roleIds);
				   $("#roleId_sub").val(roleIds);
				   $("#workForm").submit();
				   return true;
			   }
			   return false;
		   }
		</script>
	</head>
	<body class="main_body" onload="sDiv(1)">
	   <c:if test="${message!=null && message!=''}">
	     <center><font color="#FF0000">${message}</font></center>
	   </c:if>
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">发放奖励</strong>
	      <table cellpadding="0" cellspacing="0" width="80%" style="margin-left: 60">
			<tr>
		 
				<td align="left" colspan="2">
					<input id="groupName_js" type="hidden"/>
					帮派ID：<input id="groupId" name="groupId" size="20"/>&nbsp;&nbsp;
					帮派名：<input id="groupName" name="groupName" size="20"/>&nbsp;&nbsp;
					<input type="button" onclick="queryGroup()" value="检索"/>
					</td>
			</tr>
			<tr align="left" style="margin-left: 2px" valign="middle">
				<td>2.检索结果：</td>
				<td colspan="2">
				<div id="result">&nbsp;</div>
				</td>
			</tr>
			<tr>
				<td>3.选择类别：</td>
				<td colspan="2">
					<input id="r1" checked="checked" onclick="sDiv(1)" name="type" type="radio" value="1">银两奖励&nbsp;&nbsp;
					<input id="r2" onclick="sDiv(2)" name="type" type="radio" value="2">奖励点奖励&nbsp;&nbsp;
					<input id="r5" onclick="sDiv(5)" name="type" type="radio" value="5">积分奖励&nbsp;&nbsp;
					<input id="r3" onclick="sDiv(3)" name="type" type="radio" value="3">道具奖励&nbsp;&nbsp;
					<input id="r4" onclick="sDiv(4)" name="type" type="radio" value="4">装备奖励&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="3" align="left">
					<div id="div1" style="display:none">
						银两:
						<input id="iValue1" name="iValue" type="text"/>
						<input onclick="addAward(1)" type="button" value="Add">
					</div>
					<div id="div2" style="display:none">
						奖励点:
						<input id="iValue2" name="iValue" type="text"/>
						<input onclick="addAward(2)" type="button" value="Add">
					</div>
					<div id="div5" style="display:none">
						积分:
						<input id="iValue5" name="iValue" type="text"/>
						<input onclick="addAward(5)" type="button" value="Add">
					</div>
					<div id="div3" style="display:none">
						道具:
						<select id="props" name="props">
						<c:forEach var="props" items="${itemList}">
							<option value="${props.id}">[${props.id}] ${props.name}</option>
						</c:forEach>
						</select>&nbsp;
						数量:
						<input id="iValue3" name="iValue" type="text" size="5"/>
						<input onclick="addAward(3)" type="button" value="Add">
					</div>
					<div id="div4" style="display:none">
						<table width="100%" style="border: none;" align="left" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center">类别:</td>
								<td>
									<input onclick="getWeapon(1)" checked="checked" type="radio" name="pType" >武器&nbsp;
									<input onclick="getWeapon(2)" type="radio" name="pType" >防具&nbsp;
									<input onclick="getWeapon(3)" type="radio" name="pType" >神兽&nbsp;
								</td>
							</tr>
							<tr>
								<td align="center">装备:</td>
								<td>
									<select id="panoply" name="panoply"></select>
								</td>
							</tr>
							<tr>
								<td align="center">数量:</td>
								<td>
								<input id="iValue4" name="iValue" type="text" size="5"/>
								<input onclick="addAward(4)" type="button" value="Add">
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table><br/>
		<table id="table1" width="50%" border="0px" cellpadding="0" cellspacing="0" style="margin-left: 60">
			<tr align="center"> 
				<td width="10%">ID</td>
				<td width="40%">奖励物品</td>
				<td width="30%">数量</td>
				<td width="20%">操作</td>
			</tr>
		</table><br/>
		<form id="workForm" name="workForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=subGroupAward" method="post">
			<input id="roleId_sub" name="roleIds" type="hidden" />
			<input type="button" onclick="confirmAward()" style="margin-left: 300" value="确认" />
		</form> 
	</body>
</html>
