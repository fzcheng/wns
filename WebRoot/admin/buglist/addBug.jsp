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
		
	<style type="text/css">
		.tareacss{
			background-color: #339999;
			border:0px;
		}
</style>
		<script language="JavaScript">
		
			function getTypeList(){
				 var id = $("#sel1").val();
				 if(id != -1 && id != 0){
					 $.ajax({   
			              type: "POST",   
			              url: "bacl_admin.do",   
			              data: "comd=getBugType&parent=" + id,   
			              dataType : "xml",   
			              success: function(xml) {  
			            	  clear();  
			                  $(xml).find("OPTION").each(function(){
			                	  var option = $(this);
			                	  var val = option.find("VAL").text();
			                	  var name = option.find("TEXT").text();
			                	  
		                		  var option = document.createElement("option");   
		                          option.setAttribute("value",val);  
		                          var text = document.createTextNode(name);   
		                          option.appendChild(text);   
		                          $("#sel2").get(0).appendChild(option);
			                  });  
			              }   
			          }); 
				 }else{
					 clear(); 
					 var option = document.createElement("option");   
                     option.setAttribute("value","-1");   
                     var text = document.createTextNode("--请选择--");   
                     option.appendChild(text);   
                     $("#sel2").get(0).appendChild(option);
				 }
				  
			}
			
			function clear() {   
			      var selectobj = $("#sel2").get(0);   
			      var s = selectobj.options;   
			      var n = s.length;   
			      if (s && n > 0) {   
			          for (var i = 0; i < n; i++) {   
			              selectobj.removeChild(s[0]);   
			          }    
			      }   
		   } 
			
			function checkData(){
				var typeId = $("#sel1").val();
				
				if(typeId == -1){
					alert("请选择分类");
					return false;
				}else if(typeId == 0){
					var other = $.trim($("#other").val());
					if(other.length == 0){
						alert("请请输入问题分类");
						return false;
					}else{
						$("#type").val(0);
					}
				}else{
					$("#type").val($("#sel2").val());
				}
				
				var roleId = $.trim($("#roleId").val());
				var reg = new RegExp("^[1-9][0-9]*$");
				if(!reg.test(roleId)){
					alert("请输入正确的用户ID");
					return false;
				}
				
				var roleName = $.trim($("#roleName").val());
				if(roleName == null || roleName.length == 0){
					alert("请输入正确的用户昵称");
					return false;
				}
			}
			
			function back(){
				$("#comd").val("bugList");
				textform.submit();
			}
			 
		function writeSelect(obj){
		//obj.options[obj.selectedIndex].text=obj.options[obj.selectedIndex].text + String.fromCharCode(event.keyCode);
		//event.returnValue=false;
	} 
			
			
		function to_bugList(){
		
			window.location.href="bacl_admin.do?comd=bugList";
		}	 
		</script>
	</head>
	<body class="main_body">
	     <center><font color="#FF0000">${message}</font></center>
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">问题登记</strong>
		<form id="textform" name="textform" action="<%=request.getContextPath()%>/bacl_admin.do" onsubmit="return checkData()" method="post">
			<input name="id" value="${bug.id}" type="hidden">
			
			<input name="c_index" value="${c_index}" type="hidden" />
			<input name="c_eTime" value="${c_eTime}" type="hidden" />
			<input name="c_bTime" value="${c_bTime}" type="hidden" />
			<input name="c_type" value="${c_type}" type="hidden" />
			<input name="c_status" value="${c_status}" type="hidden" />
			<input name="c_roleId" value="${c_roleId}" type="hidden" />
			
			<c:if test="${not empty bug}">
				<input id="comd" name="comd" value="editBugSub" type="hidden">
			</c:if>
			<c:if test="${empty bug}">
				<input id="comd" name="comd" value="addBugSub" type="hidden">
			</c:if>
			<input id="type" name="type" value="${bug.type}" type="hidden">
			<table border="0" width="60%" style="margin-left: 60" cellpadding="0" cellspacing="0">
				<tr>
					<td>问题登记类型</td>
					<td>
					<select <c:if test="${not empty bug && backUser.userName != bug.createBy}">disabled="disabled"</c:if> onchange="getTypeList()" id="sel1" >
						<option value="-1">---请选择---</option>
						<c:forEach var="type" items="${parentType}">
							<option <c:if test="${type.value == bug.parentType}">selected="selected"</c:if> value="${type.value}">${type.text}</option>
						</c:forEach>
						<!-- <option value="0" <c:if test="${0 == bug.type}">selected="selected"</c:if>>其他</option> -->
					</select>
					<select <c:if test="${not empty bug && backUser.userName != bug.createBy}">disabled="disabled"</c:if> id="sel2" style="width: 120" onkeypress="writeSelect(this)">
						<c:if test="${typeList == null}"><option value="-1">---请选择---</option></c:if>
						<c:forEach var="type" items="${typeList}">
							<option <c:if test="${type.value == bug.type}">selected="selected"</c:if> value="${type.value}">${type.text}</option>
						</c:forEach>
					</select>&nbsp;其他描述:<input <c:if test="${not empty bug && backUser.userName != bug.createBy}">disabled="disabled"</c:if> type="text" id="other" name="other" size="40" value="${bug.other}">
					</td>
				</tr>
				<tr>
					<td>用户ID：</td>
					<td><input <c:if test="${not empty bug &&backUser.userName != bug.createBy}">disabled="disabled"</c:if> id="roleId" name="roleId" size="20" type="text" value="${bug.roleId}">(必填选项)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;优先级
					<select <c:if test="${not empty bug &&backUser.userName != bug.createBy}">disabled="disabled"</c:if> name="priority">
						<option <c:if test="${bug.priority eq 'A'}">selected="selected"</c:if> value="A">A级</option>
						<option <c:if test="${bug.priority eq 'B'}">selected="selected"</c:if> value="B">B级</option>
						<option <c:if test="${bug.priority eq 'C'}">selected="selected"</c:if> value="C">C级</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>用户昵称：</td>
					<td><input <c:if test="${not empty bug &&backUser.userName != bug.createBy}">disabled="disabled"</c:if> id="roleName" name="roleName"  size="20" type="text" value="${bug.roleName}">(必填选项)</td>
				</tr>
				<tr>
					<td>联系方式：</td>
					<td><input <c:if test="${not empty bug &&backUser.userName != bug.createBy}">disabled="disabled"</c:if> name="lxfs"  size="20" type="text" value="${bug.contact}"></td>
				</tr>
				<tr>
					<td>联系人：</td>
					<td><input <c:if test="${not empty bug &&backUser.userName != bug.createBy}">disabled="disabled"</c:if> name="lxr"  size="20" type="text" value="${bug.contacter}"></td>
				</tr>
				<tr>
					<td>反馈详情：</td>
					<td><textarea     <c:if test="${not empty bug &&backUser.userName != bug.createBy}">disabled="disabled" style="overflow-x:hidden; overflow-y:hidden; border:0;" class="tareacss" </c:if> name="question" cols="40" rows="12">${bug.question}</textarea></td>
				</tr>
				<c:if test="${not empty bug}">
				<tr>
					<td>问题处理详情：</td>
					<td>${bug.answer}&nbsp;</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="2" align="center">
						<c:if test="${not empty bug && backUser.userName == bug.createBy}">
						<input type="submit" VALUE="修改">
						</c:if>
						<c:if test="${empty bug}">
						<input type="submit" VALUE="确定">
						</c:if>
						<c:if test="${not empty c_index}">
						<input type="button" onclick="javascript:back();" VALUE="返回">
						</c:if>
						 
					</td>
				</tr>
			</table>
			<input style="margin-left: 60" onclick="to_bugList()"  type="button" value="查看信息列表"/>
		</form>
	</body>
</html>
