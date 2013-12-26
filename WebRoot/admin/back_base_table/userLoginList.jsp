<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my" uri="/my"%>
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css"
			rel="stylesheet" type="text/css">
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/refresh.js"></script>
		
		<style type="text/css">
		.tareacss{
			background-color:#ccc;
			border:0px;
		}
		</style>
 <script type="text/javascript">
		
 $(function(){
		 $('table tr').click(function(){ 
		 $(this).css('background-color', '#ccc').siblings().css('background-color', ''); 
		 });
		  
   })
		
  function window.onbeforeunload(){
				var JscrollPos;
				if (typeof window.pageYOffset != 'undefined') { 
				JscrollPos = window.pageYOffset;
				}
				else if (typeof document.compatMode != 'undefined' &&
				document.compatMode != 'BackCompat') { 
				JscrollPos = document.documentElement.scrollTop;
				}
				else if (typeof document.body != 'undefined') { 
				JscrollPos = document.body.scrollTop;
				}
				document.cookie="scrollTop="+JscrollPos;
	}
	
   function  editUserLogin(roleId,seq,status){
	var roleId =roleId;
	var channelUserId = $.trim($("#channelUserId"+seq).text());
	var status = $.trim(status);
	$("#roleId_").val(roleId);
	$("#channelUserId_").val(channelUserId);
	$("#status_").val(status);
	editForm.submit();
	}
	
function submitBut(status){
	var tip="确认开通？" ;
	if(ischeck()){
		if('0'==status)tip ="确认关闭？" ; else tip ="确认开通？" ; 
			if(confirm(tip)) {
				$("#status").val(status);
				editAllRoleId.submit();
			}
		}
	}
		
	</script>
   </head>
	<body class="main_body">
		<c:if test="${message != null && message != ''}">
			<center>
				<font color="#FF0000">${message}</font>
			</center>
		</c:if>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<strong style="font-size: 30">兑换规则</strong>
		<c:if test="${my:hasPermission(backUser.roleId,50028)}">
			<input onclick="confirmRefresh()" type="button" value="刷新列表" />
		</c:if>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<form action="<%=request.getContextPath()%>/bacl_admin.do?comd=getByChannelUserId"
			method="post"> 
		用户ID：<input type="text" value="" name="channelUserId_" /><input   type="submit"   value="查询" />
		</form>
		<form  id="editAllRoleId" name="editAllRoleId" action="<%=request.getContextPath()%>/bacl_admin.do?comd=editAllUserLogin"
			method="post">
		<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr align="center">
				<td width="10%">
					SEQ
				</td>
				<td width="15%">
					角色ID
				</td>
				<td width="5%">
					用户ID
				</td>
				<td width="30%">
					用户名
				</td>
				<td width="5%">
					状态
				</td>
			  <td width="5%">
					编辑
				</td>
			  
			</tr>
			<!-- background-color:#339999; -->
			<c:if test="${not empty userLogionList}">
				<c:forEach var="userLogin" items="${userLogionList}" begin="0"
					varStatus="status">
							<tr align="center">
								<td width="10%">
									<input type="checkbox"  id="roleId" name="roleId" value="${userLogin.roleId}"  />
								</td>
								
								<td width="5%">
									${userLogin.roleId}
								</td>
								<td width="5%" id="channelUserId${status.count}">
									${userLogin.channelUserId}
								</td>
								
								<td width="5%">
									${userLogin.infoName}
								</td>
								<td width="5%" id="status${status.count}" >
									${userLogin.status}
								</td>
								<td width="10%">
									<c:if test="${0 eq userLogin.status}">
									<a href="#" onclick="editUserLogin('${userLogin.roleId}','${status.count}','1')">关闭</a>
									</c:if>
									<c:if test="${1 eq userLogin.status}">
									<a href="#" onclick="editUserLogin('${userLogin.roleId}','${status.count}','0')">开通</a>
									</c:if>
									<c:if test="${2 eq userLogin.status}">
									<a href="#" onclick="editUserLogin('${userLogin.roleId}','${status.count}','2')">删除</a>
									</c:if>
									 &nbsp;&nbsp;
								</td>
					 </tr>
		<input type="hidden" value="${userLogin.status}" id="status" name="status"/>
		<input type="hidden" value="${userLogin.channelUserId}"  name="channelUserId_"/>
				</c:forEach>
			</c:if>
		 
		 </table>	
		<input type="checkbox" style="margin-left: 65;"  onclick="check('roleId')">
		全选
		<input value="开通"  type="button" onclick="submitBut('1')" style="width: 55px;background-color: #ccc;"/>
		<input value="关闭"  type="button" onclick="submitBut('0')" style="width: 55px;background-color: #ccc;"/>
		
		</form>
		 <input id="allcheck" name="roleId_" type="hidden" value="0"/>
		<form id="editForm"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=editUserLogin"
			method="post">
			<input id="roleId_" name="roleId_" type="hidden" />
			<input id="channelUserId_" name="channelUserId_" type="hidden" />
			<input id="status_" name="status_" type="hidden" />
		</form>

<script type="text/javascript">



function ischeck(){ 

	if($("input[name=roleId]").attr('checked')==true){
		return true; 
	} 
	window.alert('最少选择一个操作项！'); 
	return false; 
 
} 





function check(obj){
var allcheck =$("#allcheck").val();

var code_Values =document.all(obj);
	 if(code_Values.length>0&&allcheck=='0'){
		 for(var i=0;i<code_Values.length;i++){ 
				code_Values[i].checked = true; 
			} 
			$("#allcheck").val('1');
 		}else {
 		 for(var i=0;i<code_Values.length;i++){ 
				code_Values[i].checked = false; 
			} 
			$("#allcheck").val('0');
 		} 
 }
 

</script>
	</body>
</html>
