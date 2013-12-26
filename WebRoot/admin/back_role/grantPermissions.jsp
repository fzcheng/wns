<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my"  uri="/my" %>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script language="JavaScript">
			function lTor(){
				var menuText =$("#menu option:selected").text();
				var addList = $("#addList").val();
				var oldList = $("#oldList").val();
				var delList = $("#delList").val();
				$("#funcList option:selected").each(function(){
					var rVal = $(this).val();
              		var rText = $(this).text();
              		var id = ","+rVal+",";

              		if(delList.indexOf(id) != -1){
              			delList = removeId(delList,rVal);
              			$("#delList").val(delList);
              		}
              		
              		if(addList.indexOf(id) == -1 && oldList.indexOf(id) == -1){
              			addList = addId(addList,rVal);
              			$("#addList").val(addList);
              		}
              		
              		$("#roleFuncList").append("<option value='"+rVal+"'>"+menuText+"-"+rText+"</option>");  
              		$(this).remove();
				 });
			}
			function rTol(){
				var menuText =$("#menu option:selected").text();
				var addList = $("#addList").val();
				var delList = $("#delList").val();
				var oldList = $("#oldList").val();
				$("#roleFuncList option:selected").each(function(){
					var lVal = $(this).val();
              		var lText = $(this).text();
              		var text = lText.split("-"); 
					var id = ","+lVal+",";
              		
              		if(addList.indexOf(id) != -1){
              			addList = removeId(addList,lVal);
              			$("#addList").val(addList);
              		}
              		if(oldList.indexOf(id) != -1 && delList.indexOf(id) == -1){
              			delList = addId(delList,lVal);
              			$("#delList").val(delList);
              		}
              		
              		if($.trim(text[0]) == $.trim(menuText)){
						$("#funcList").append("<option value='"+lVal+"'>"+text[1]+"</option>");
              			$(this).remove();
              		}else{
              			$(this).remove();
              		}
				 });
			}
			
			function getFuncList(){
				 var id = $("#menu").val();
				 $.ajax({   
		              type: "POST",   
		              url: "bacl_admin.do",   
		              data: "comd=getFuncList&powerId=" + id,   
		              dataType : "xml",   
		              success: function(xml) {  
		            	  clear();  
		                  $(xml).find("OPTION").each(function(){
		                	  var option = $(this);
		                	  var val = option.find("VAL").text();
		                	  var name = option.find("TEXT").text();
		                	  
		                	  var t = $("#roleFuncList option[value='"+val+"']").text();
		                	  if(t.length == 0){
		                		  var option = document.createElement("option");   
		                          option.setAttribute("value",val);   
		                          var text = document.createTextNode(name);   
		                          option.appendChild(text);   
		                          $("#funcList").get(0).appendChild(option);
		                	  }
		                  });  
		              }   
		          });  
			}
			
			function clear() {   
			      var selectobj = $("#funcList").get(0);   
			      var s = selectobj.options;   
			      var n = s.length;   
			      if (s && n > 0) {   
			          for (var i = 0; i < n; i++) {   
			              selectobj.removeChild(s[0]);   
			          }    
			      }   
			} 
			
			function subForm(){
				var addList = $("#addList").val();
				var delList = $("#delList").val();
				alert("add"+addList);
				alert("del"+delList);
				return false;
			}
			
			function removeId(str,id){
				var ids = "";
				 $.each(str.split(","+id+","),function(i,value) {   
					 ids += value; 
			     });   
				return ids;
			}
			
			function addId(str,id){
				if($.trim(str).length == 0){
					return "," + id + ",";
				}else{
					return str + id + ",";
				}
			}
			
			function sortOption(){
				var options = $("#roleFuncList option");
				var len = options.length;
				for(var i = 0; i<len; i++){
					for(var j = 0; j < len-i; j++){
						var id_i = $(options[j-1]).val();
						var id_j = $(options[j]).val();
						if(id_i > id_j){
							swapOption(options[j],options[j-1]);
						}
					}
				}
			}
			
			function swapOption(option1,option2){
			    var tempStr=option1.value;
			    option1.value=option2.value;
			    option2.value=tempStr;
			    tempStr=option1.text;
			    option1.text=option2.text;
			    option2.text=tempStr;
			    tempStr=option1.selected;
			    option1.selected=option2.selected;
			    option2.selected=tempStr;
			}
		</script>
	</head>
	<body class="main_body">
	  <c:if test="${message != null && message != ''}">
	  <center><font color="#FF0000">${message}</font></center>
	  </c:if>

<table style="margin-left: 60" width="50%" border="0" align="left" cellpadding="0" cellspacing="0">
<tr> 
	<td align="center"><strong>分配功能权限</strong></td>
</tr>
<tr> 
	<td align="left">角色编号：${backRole.roleId}</td>
</tr>
<tr> 
	<td align="left">角色名称：${backRole.roleName}</td>
</tr>
<tr> 
	<td align="left">角色描述：${backRole.roleDsc}</td>
</tr>
<tr> 
	<td align="left">&nbsp;&nbsp;
	<select id="menu" onchange="getFuncList()">
	<c:forEach var="menu" items="${menuList}">
		<option value="${menu.powerId}">${menu.powerName}</option>
	</c:forEach>
	</select>
	</td>
</tr>		
<tr> 
	<td align="left">
		<table frame="void" cellspacing="1" ><tr>
			<td width="160" align="center">
			<select id="funcList" size="20" style="width:150px" multiple>
			<c:forEach var="option" items="${functionList}">
				<option value="${option.value}">${option.text}</option>
			</c:forEach>
			</select>
			</td>
			<td width="40" align="center" valign="middle">
			<input onclick="lTor()" type="button" value=" &#62;&#62; "><br/>
			<input onclick="rTol()" type="button" value=" &#60;&#60; "><br/>
			<input onclick="sortOption()" type="button" value="排序">
			</td>
			<td width="160" align="center">
			<select id="roleFuncList" size="20" style="width:200px" multiple>
				<c:forEach var="vo" items="${roleFuncList}">
					<option value="${vo.powerId}">${vo.powerName}</option>
				</c:forEach>
			</select>
			</td>
			</tr>
		</table>
	</td>
</tr>
<tr> 
	<td align="center">
	<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do?comd=updateRolePermissions" method="post">
		<input id="oldList" name="ids" type="hidden" value="${ids}"/>
		<input id="roleId" name="roleId" type="hidden" value="${backRole.roleId}" />
		<input id="delList" name="delList" type="hidden" value="" />
		<input id="addList" name="addList" type="hidden" value="" />
		<input type="submit" value="确定" />
		<input onclick="javascript:history.go(-1)" type="button" value="返回" />
	</form>
	</td>
</tr>
</table>

</body>
</html>
