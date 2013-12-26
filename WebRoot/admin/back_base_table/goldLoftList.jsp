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
   function  updateGoldLoft(itemId){
	
 
		$("#roomId").val(itemId);
	     editForm.submit();
	}
	
	function delExchangeRules(url){
	alert(url);
		if(confirm('确认删除吗?')) {
			window.location.href = "bacl_admin.do?" +url;
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
		<strong style="font-size: 30">藏金阁信息</strong>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0" style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr>
				<td colspan="3">
					<form id="textform1" name="textform1"
						action="<%=request.getContextPath()%>/bacl_admin.do?comd=GoldLoftList"
						method="post">
						角色ID：
						<input id="roleId" name="roleId" type="text" value="${roleId}"
							size="10" />
						&nbsp; 开始时间：
						<input id="sTime" name="beginTime" type="text" value="${beginTime}"
							onClick="setDay(this);" size="15" readonly="readonly" />
						&nbsp; 结束时间：
						<input id="eTime" name="calcTime" type="text" value="${calcTime}"
							onClick="setDay(this);" size="15" readonly="readonly" />
						&nbsp;
						<input type="submit" value="搜索" />
					</form>
				</td>
			</tr>
		</table>
		<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr align="center">
				<td width="5%">
					ID
				</td>
				<td width="15%">
					角色ID
				</td>
				<td width="5%">
					角色名称
				</td>
				<td width="15%">
					帮会名称
				</td>
				<td width="5%">
					占领时间
				</td>
				<td>来开时间</td>
				
				<td width="10%">获得金币 </td>
			 
			</tr>
			<!-- background-color:#339999; -->
			<c:if test="${not empty goldLoftList}">
				<c:forEach var="goldLoft" items="${goldLoftList}" begin="0"
					varStatus="status">


					<c:choose>
						<c:when test="${status.index%2==0}">
							<tr align="center">
								<td width="5%">
									${goldLoft.roomId}
								</td>
						 	<td width="5%">
									${goldLoft.roleId}
								</td>
						 
						 	<td width="5%">
									${goldLoft.roleName}
								</td>
						 
						 	<td width="5%">
									${goldLoft.groupName}
								</td>
						 
						 	<td width="15%">
									<fmt:formatDate value="${goldLoft.beginTime}" pattern="yyyy-MM-dd HH:mm" />
									
								</td>
						 
						 	<td width="15%">
									<fmt:formatDate value="${goldLoft.calcTime}" pattern="yyyy-MM-dd HH:mm" />
								</td>
						 
						 	<td width="5%">
									${goldLoft.sumgold}
								</td>
								<td width="10%">
									<a href="#" onclick="updateGoldLoft('${goldLoft.roomId}')">踢出藏金阁</a>&nbsp;&nbsp;
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr align="center">
								<td width="5%">
									${goldLoft.roomId}
								</td>
						 	<td width="5%">
									${goldLoft.roleId}
								</td>
						 
						 	<td width="5%">
									${goldLoft.roleName}
								</td>
						 
						 	<td width="5%">
									${goldLoft.groupName}
								</td>
						 
						 	<td width="15%">
									<fmt:formatDate value="${goldLoft.beginTime}" pattern="yyyy-MM-dd HH:mm" />
									
								</td>
						 
						 	<td width="15%">
									<fmt:formatDate value="${goldLoft.calcTime}" pattern="yyyy-MM-dd HH:mm" />
								</td>
						 
						 	<td width="5%">
									${goldLoft.sumgold}
								</td>
								<td width="10%">
									<a href="#" onclick="updateGoldLoft('${goldLoft.roomId}')">踢出藏金阁</a>&nbsp;&nbsp;
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</table>
		<form id="editForm"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=updateGoldLoft"
			method="post">
			<input id="roomId" name="roomId" type="hidden" />
		</form>




	</body>
</html>
