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
   function  editWeaponBox(itemId){
	
	
	var id =itemId;
	var name = $.trim($("#name"+itemId).val());
	var desc = $.trim($("#desc"+itemId).text());
	var mlv = $.trim($("#mlv"+itemId).val());
	var rp = $.trim($("#rp"+itemId).val());
	var we = $.trim($("#we"+itemId).val()); 
	$("#id_").val(id);
	$("#name_").val(name);
	$("#mlv_").val(mlv);
	$("#desc_").val(desc);
	$("#rp_").val(rp);
	$("#we_").val(we);
 
	editForm.submit();
	}
	
	function delWeaponBox(url){
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
		<strong style="font-size: 30">武器宝箱列表</strong>
		<c:if test="${my:hasPermission(backUser.roleId,50028)}">
			<input onclick="confirmRefresh('getWeaponBoxList')" type="button" value="刷新列表" />
			<input onclick="add('<%=request.getContextPath()%>/admin/back_base_table/addWeaponBox.jsp?maxId=${maxId}')" type="button" value="新增装备" />
		</c:if>
		<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr align="center">
				<td width="%5" >
					ID
				</td>
				<td width="%10" >
					名称
				</td>
				<td width="%20" >
					描述
				</td>
				<td width="%10" >
					场景
				</td>
				<td width="%5" >
					价格
				</td>
				<td width="%30" >
					武器及概率
				</td>
				<td width="%10" >
					编辑
				</td>
			</tr>
			
			<!-- background-color:#339999; -->
			<c:if test="${not empty weaponBoxList}">
				<c:forEach var="weaponBox" items="${weaponBoxList}" begin="0"
					varStatus="status">


					<c:choose>
						<c:when test="${status.index%2==0}">
									<tr align="center">
								<td  width="%5" >
									${weaponBox.id}
								</td>
								<td style="word-break: break-all;" width="%10" >
									<textarea rows=1 cols=15 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="name${weaponBox.id}"
										id="name${weaponBox.id}" />${weaponBox.name}</textarea>
								</td>
									<td style="word-break: break-all;">
									<textarea rows=1 cols=32 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="desc${weaponBox.id}"
										id="desc${weaponBox.id}" />${weaponBox.desc}</textarea>
								</td>
									<td style="word-break: break-all;" width="%5">
									<textarea rows=1 cols=15 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="mlv${weaponBox.id}"
										id="mlv${weaponBox.id}" />${weaponBox.mlv}</textarea>
								</td>
										<td style="word-break: break-all;" width="%5">
									<textarea rows=1 cols=15 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="rp${weaponBox.id}"
										id="rp${weaponBox.id}" />${weaponBox.rp}</textarea>
								</td>
									<td style="word-break: break-all;">
									<textarea rows=1 cols=51 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="we${weaponBox.id}"
										id="we${weaponBox.id}" />${weaponBox.we}</textarea>
								</td>
								<td width="%10">
									<a href="#" onclick="editWeaponBox('${weaponBox.id}')">编辑</a>&nbsp;&nbsp;
									<a href="#" onclick="delWeaponBox('comd=delWeaponBox&id='+'${weaponBox.id}')">删除</a><a
										href="#"></a>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
									<tr align="center">
								<td  width="%5" >
									${weaponBox.id}
								</td>
								<td style="word-break: break-all;" width="%10" >
									<textarea rows=1 cols=15 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="name${weaponBox.id}"
										id="name${weaponBox.id}" />${weaponBox.name}</textarea>
								</td>
									<td style="word-break: break-all;">
									<textarea rows=1 cols=32 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="desc${weaponBox.id}"
										id="desc${weaponBox.id}" />${weaponBox.desc}</textarea>
								</td>
									<td style="word-break: break-all;" width="%5">
									<textarea rows=1 cols=15 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="mlv${weaponBox.id}"
										id="mlv${weaponBox.id}" />${weaponBox.mlv}</textarea>
								</td>
										<td style="word-break: break-all;" width="%5">
									<textarea rows=1 cols=15 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="rp${weaponBox.id}"
										id="rp${weaponBox.id}" />${weaponBox.rp}</textarea>
								</td>
									<td style="word-break: break-all;">
									<textarea rows=1 cols=51 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="we${weaponBox.id}"
										id="we${weaponBox.id}" />${weaponBox.we}</textarea>
								</td>
								<td width="%10">
									<a href="#" onclick="editWeaponBox('${weaponBox.id}')">编辑</a>&nbsp;&nbsp;
									<a href="#" onclick="delWeaponBox('comd=delWeaponBox&id='+'${weaponBox.id}')">删除</a><a
										href="#"></a>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</table>
		<form id="editForm" name="${weaponBox.name}"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=editWeaponBox"
			method="post">
			<input id="id_" name="id_" type="hidden" />
			<input id="name_" name="name_" type="hidden" />
			<input id="desc_" name="desc_" type="hidden" />
			<input id="mlv_" name="mlv_" type="hidden" />
			<input id="rp_" name="rp_" type="hidden" />
			<input id="we_" name="we_" type="hidden" />
		</form>




	</body>
</html>
