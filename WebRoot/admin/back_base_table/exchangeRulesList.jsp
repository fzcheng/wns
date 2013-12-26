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
   function  editExchangeRules(itemId){
	
	var id =itemId;
	var type = $.trim($("#type"+itemId).text());
	var dsc = $.trim($("#dsc"+itemId).val());
	var meterials = $.trim($("#meterials"+itemId).val());
	var aim = $.trim($("#aim"+itemId).val()); 
	$("#id_").val(id);
	$("#type_").val(type);
	$("#dsc_").val(dsc);
	$("#meterials_").val(meterials);
	$("#aim_").val(aim);
 
	editForm.submit();
	}
	
	function delExchangeRules(url){
	alert(url);
		if(confirm('确认删除吗?')) {
			window.location.href = "bacl_admin.do?" +url;
		}
	}	
		function add(url){
			window.location.href = url;
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
			<input onclick="confirmRefresh('getExchangeRulesList')" type="button" value="刷新列表" />
			<input onclick="add('<%=request.getContextPath()%>/admin/back_base_table/addExchangerules.jsp')" type="button" value="新增兑换规则" />
		</c:if>
		
		<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr align="center">
				<td width="10%">
					ID
				</td>
				<td width="15%">
					兑换类型
				</td>
				<td width="5%">
					兑换物品
				</td>
				<td width="25%">
					被兑换物
				</td>
				<td width="10%">
					属性描述
				</td>
			 
			</tr>
			<!-- background-color:#339999; -->
			<c:if test="${not empty exchangeRulesList}">
				<c:forEach var="exchangeRules" items="${exchangeRulesList}" begin="0"
					varStatus="status">


					<c:choose>
						<c:when test="${status.index%2==0}">
							<tr align="center">
								<td width="5%">
									${exchangeRules.id}
								</td>
								<td style="word-break: break-all;">
									<textarea class="tareacss" rows=1 cols=21
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="type${exchangeRules.id}"
										id="type${exchangeRules.id}" />${exchangeRules.type}</textarea>
								</td>
								
									<td style="word-break: break-all;">
									<textarea class="tareacss" rows=1 cols=20
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="meterials${exchangeRules.id}"
										id="meterials${exchangeRules.id}" />${exchangeRules.meterials}</textarea>
								</td>
										<td style="word-break: break-all;">
									<textarea class="tareacss" rows=1 cols=40
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="aim${exchangeRules.id}"
										id="aim${exchangeRules.id}" />${exchangeRules.aim}</textarea>
								</td>

						 				<td style="word-break: break-all;">
									<textarea class="tareacss" rows=1 cols=40
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="dsc${exchangeRules.id}"
										id="dsc${exchangeRules.id}" />${exchangeRules.dsc}</textarea>
								</td>
 
								<td width="10%">
									<a href="#" onclick="editExchangeRules('${exchangeRules.id}')">编辑</a>&nbsp;&nbsp;
									<a href="#" onclick="delExchangeRules('comd=delExchangeRules&id='+'${exchangeRules.id}')">删除</a><a
										href="#"></a>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
						<tr align="center">
								<td width="5%">
									${exchangeRules.id}
								</td>
								<td style="word-break: break-all;">
									<textarea class="tareacss" rows=1 cols=21
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="type${exchangeRules.id}"
										id="type${exchangeRules.id}" />${exchangeRules.type}</textarea>
								</td>
								
									<td style="word-break: break-all;">
									<textarea class="tareacss" rows=1 cols=20
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="meterials${exchangeRules.id}"
										id="meterials${exchangeRules.id}" />${exchangeRules.meterials}</textarea>
								</td>
										<td style="word-break: break-all;">
									<textarea class="tareacss" rows=1 cols=40
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="aim${exchangeRules.id}"
										id="aim${exchangeRules.id}" />${exchangeRules.aim}</textarea>
								</td>

						 				<td style="word-break: break-all;">
									<textarea class="tareacss" rows=1 cols=40
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="dsc${exchangeRules.id}"
										id="dsc${exchangeRules.id}" />${exchangeRules.dsc}</textarea>
								</td>
 
								<td width="10%">
									<a href="#" onclick="editExchangeRules('${exchangeRules.id}')">编辑</a>&nbsp;&nbsp;
									<a href="#" onclick="delExchangeRules('comd=delExchangeRules&id='+'${exchangeRules.id}')">删除</a><a
										href="#"></a>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</table>
		<form id="editForm"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=editExchangeRules"
			method="post">
			<input id="id_" name="id_" type="hidden" />
			<input id="type_" name="type_" type="hidden" />
			<input id="dsc_" name="dsc_" type="hidden" />
			<input id="meterials_" name="meterials_" type="hidden" />
			<input id="aim_" name="aim_" type="hidden" />
		</form>




	</body>
</html>
