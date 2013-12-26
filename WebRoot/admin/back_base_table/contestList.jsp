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
   function  editConfig(itemId){
	
	
	var id =itemId;
	var version = $.trim($("#version"+itemId).text());
	var fight_type = $.trim($("#fight_type"+itemId).val());
	$("#version_").val(version);
	$("#fight_type_").val(fight_type);
	editForm.submit();
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
		<strong style="font-size: 30">比武招亲信息</strong>
		<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr align="center">
				<td width="%5" >
					玩家ID
				</td>
				<td width="%10" >
					玩家昵称
				</td>
				<td width="%20" >
					对手玩家ID
				</td>
				<td width="%10" >
					玩家昵称
				</td>
				<td width="%5" >
					参与时间
				</td>
				 
			</tr>
			
			<!-- background-color:#339999; -->
			<c:if test="${not empty contestList}">
		 	<c:forEach var="contest" items="${contestList}">
								<tr align="center">
								<td  width="%5" >
									${contest.aroleId}
								</td>
									<td  width="%5" >
									${contest.aroleName}
								</td>
									<td  width="%5" >
									${contest.broleId}
								</td>
									<td  width="%5" >
									${contest.broleName}
								</td>
									<td  width="%5" >
									${contest.createTime}
								</td>
				 </tr>
				 </c:forEach>
			</c:if>
		</table>
		<form id="editForm"  
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=editConfigList"
			method="post">
			<input id="version_" name="version_" type="hidden" />
			<input id="fight_type_" name="fight_type_" type="hidden" />
			 
			 
		</form>




	</body>
</html>
