<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
			function showDiv(id){
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

			function queryUser(){
				removeAll();
				var roleId = $("#roleId").val();
				$.ajax({   
		              type: "POST",   
		              url: "bacl_admin.do",   
		              data: "comd=queryUser&roleId=" + roleId,   
		              dataType : "text",   
		              success: function(text) {   
		                  $("#result").text(text+" [id:"+roleId+"]"); 
		                  $("#roleId_js").val(roleId);
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
			   var roleId = $("#roleId_js").val();
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
			   if(roleId.length == 0){
				   alert("请先检索玩家!");
				   return;
			   }else if(quatity.length ==0 || !reg.test(quatity)){
				   alert("请输入正确的数量!");
				   return;
			   }else{
				   $.ajax({   
		              type: "POST",   
		              url: "bacl_admin.do",   
		              data: "comd=addAward&objId="+objId+"&objDesc="+objDesc+"&objType="+objType+"&quatity="+quatity+"&roleId="+roleId,   
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
		              data: "comd=removeAward&objId="+objId,   
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
			   var user = $("#result").text();
			   if(confirm("确认奖励玩家:"+user+"!")){
				   $("#roleId_sub").val($("#roleId_js").val());
				   return true;
			   }
			   return false;
		   }
		</script>
	</head>
	<body class="main_body" onload="showDiv(1)">
	    <%
	    String message = (String)request.getAttribute("message");
	     if(message != null){
	     %>
	     <center><font color="#FF0000"><%=message%></font></center>
	     <%
	     }
	     %>
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">发放奖励</strong>
	      <table border="0px" cellpadding="0" cellspacing="0" width="60%" style="margin-left: 60">
			<tr>
				<td>1.检索玩家：</td>
				<td align="left" colspan="2">
					<input id="roleId_js" type="hidden"/>
					<input id="roleId" name="roleId" size="20"/>&nbsp;&nbsp;
					<input type="button" onclick="queryUser()" value="检索"/>
					<label style="color: red;font-size: 12px">[注:玩家编号]</label>
					</td>
			</tr>
			<tr align="left" style="margin-left: 2px" valign="middle">
				<td>2.检索结果：</td>
				<td colspan="2">
				<label id="result"></label>&nbsp;
				</td>
			</tr>
			<tr>
				<td>3.选择类别：</td>
				<td colspan="2">
					<input id="r1" checked="checked" onclick="showDiv(1)" name="type" type="radio" value="1">银两奖励&nbsp;&nbsp;
					<input id="r2" onclick="showDiv(2)" name="type" type="radio" value="2">奖励点奖励&nbsp;&nbsp;
					<input id="r5" onclick="showDiv(5)" name="type" type="radio" value="5">积分奖励&nbsp;&nbsp;
					<input id="r3" onclick="showDiv(3)" name="type" type="radio" value="3">道具奖励&nbsp;&nbsp;
					<input id="r4" onclick="showDiv(4)" name="type" type="radio" value="4">装备奖励&nbsp;&nbsp;
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
		<form onsubmit="return confirmAward()" action="<%=request.getContextPath()%>/bacl_admin.do?comd=subAward" method="post">
			<input id="roleId_sub" name="roleId" type="hidden" />
			<input type="submit" style="margin-left: 300" value="确认" />
		</form> 
	</body>
</html>
