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
		<strong style="font-size: 30">副本设定</strong>
		<c:if test="${my:hasPermission(backUser.roleId,50028)}">
			<input onclick="confirmRefresh('editConfigList')" type="button" value="刷新列表" />
		</c:if>
		<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr align="center">
				<td width="%5" >
					回血时间
				</td>
				<td width="%10" >
					回能量时间
				</td>
				<td width="%20" >
					回气时间
				</td>
				<td width="%10" >
					收入时间
				</td>
				<td width="%5" >
					timer_factor
				</td>
				<td width="%30" >
					当前版本
				</td>
				<td width="%30" >
					下一个版本
				</td>
				<td width="%30" >
					debug版本
				</td>
				<td width="%30" >
					可挑战次数
				</td>
				<td width="%10" >
					编辑
				</td>
			</tr>
			
			<!-- background-color:#339999; -->
			<c:if test="${not empty configvo}">
		 
								<tr align="center">
								<td  width="%5" >
									${configvo.hp_reload_timer}
								</td>
									<td  width="%5" >
									${configvo.energy_reload_timer}
								</td>
									<td  width="%5" >
									${configvo.stamina_reload_timer}
								</td>
									<td  width="%5" >
									${configvo.money_reload_timer}
								</td>
									<td  width="%5" >
									${configvo.timer_factor}
								</td>
									<td  width="%5" id="version${configvo.current_version}">
									${configvo.current_version}
								</td>
										<td  width="%5" >
									${configvo.next_version}
								</td>
										<td  width="%5" >
									${configvo.debug_version}
								</td>
								<td style="word-break: break-all;" width="%10" >
									<textarea rows=1 cols=15 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" 
										id="fight_type${configvo.current_version}" />${configvo.fight_type}</textarea>
								</td>
									<td width="10%">
									<a href="#" onclick="editConfig('${configvo.current_version}')">编辑</a>&nbsp;&nbsp;
								</td>
							</tr>
					 
				 
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
