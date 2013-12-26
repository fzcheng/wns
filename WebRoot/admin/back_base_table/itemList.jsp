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
   function  editItem(itemId){
	
	var id =itemId;
	var name = $.trim($("#name"+itemId).val());
	var type = $.trim($("#type"+itemId).text());
	var desc = $.trim($("#desc"+itemId).val());
	var img = $.trim($("#img"+itemId).val());
	var eff = $.trim($("#eff"+itemId).val()); 
	$("#id_").val(id);
	$("#name_").val(name);
	$("#type_").val(type);
	$("#desc_").val(desc);
	$("#img_").val(img);
	$("#eff_").val(eff);
 
	editForm.submit();
	}
	
	function delItem(url){
		if(confirm('确认删除吗?')) {
			window.location.href = "bacl_admin.do?" +url;
		}
	}	
	function add(url){
		 
			window.location.href =url;
		 
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
		<strong style="font-size: 30">道具列表</strong>
		<c:if test="${my:hasPermission(backUser.roleId,50028)}">
			<input onclick="confirmRefresh('getItemList')" type="button" value="刷新列表" />
			<input onclick="add('<%=request.getContextPath()%>/admin/back_base_table/addItem.jsp')" type="button" value="新增道具" />
		</c:if>
		<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr align="center">
				<td width="5%">
					SEQ
				</td>
				<td width="15%">
					道具名称
				</td>
				<td width="5%">
					类型
				</td>
				<td width="30%">
					描述
				</td>
				<td width="5%">
					图片
				</td>
				<td width="30%">
					属性
				</td>
				<td width="10%">
					编辑
				</td>
			</tr>
			<!-- background-color:#339999; -->
			<c:if test="${not empty itemList}">
				<c:forEach var="item" items="${itemList}" begin="0"
					varStatus="status">


					<c:choose>
						<c:when test="${status.index%2==0}">
							<tr align="center">
								<td width="5%">
									${status.count}
								</td>
								<td style="word-break: break-all;">
									<textarea class="tareacss" rows=1 cols=20
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="name${item.id}"
										id="name${item.id}" />${item.name}</textarea>
								</td>

								<td width="5%" id="type${item.id}">
									${item.type}
								</td>

								<td style="word-break: break-all;">
									<textarea rows=1 cols=40 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="desc${item.id}"
										id="desc${item.id}" />${item.desc}</textarea>
								</td>

								<td style="word-break: break-all;">
									<textarea rows=1 cols=15 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="img${item.id}"
										id="img${item.id}" />${item.img}</textarea>
								</td>
								<td style="word-break: break-all;">
									<textarea rows=1 cols=40 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="eff${item.id}"
										id="eff${item.id}" />${item.eff}</textarea>
								</td>
								<td width="10%">
									<a href="#" onclick="editItem('${item.id}')">编辑</a>&nbsp;
									<a href="#" onclick="delItem('comd=delItem&id='+'${item.id}')">删除</a>&nbsp;
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr align="center">
								<td width="5%">
									${status.count}
								</td>
								<td style="word-break: break-all;">
									<textarea rows=1 cols=20 class="tareacss" 
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="name${item.id}"
										id="name${item.id}" />${item.name}</textarea>
								</td>

								<td width="5%" id="type${item.id}">
									${item.type}
								</td>

								<td style="word-break: break-all;">
									<textarea rows=1 cols=40 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="desc${item.id}"
										id="desc${item.id}" />${item.desc}</textarea>
								</td>

								<td style="word-break: break-all;">
									<textarea rows=1 cols=15 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height='';" name="img${item.id}"
										id="img${item.id}" />${item.img}</textarea>
								</td>
								<td style="word-break: break-all;">
									<textarea rows=1 cols=40 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="eff${item.id}"
										id="eff${item.id}" />${item.eff}</textarea>
								</td>
								<td width="10%">
									<a href="#" onclick="editItem('${item.id}')">编辑</a>&nbsp;
									<a href="#" onclick="delItem('comd=delItem&id='+'${item.id}')">删除</a>&nbsp;
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</table>
		<form id="editForm" name="${item.name}"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=editItem"
			method="post">
			<input id="id_" name="id_" type="hidden" />
			<input id="name_" name="name_" type="hidden" />
			<input id="type_" name="type_" type="hidden" />
			<input id="desc_" name="desc_" type="hidden" />
			<input id="img_" name="img_" type="hidden" />
			<input id="eff_" name="eff_" type="hidden" />
		</form>




	</body>
</html>
