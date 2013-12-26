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
			border: 0px;
		}
		</style>
		<!-- disabled:false;-->
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
	
	
 	
   function  editWeapon(itemId){
	var id =itemId;
	var name = $.trim($("#name"+itemId).val());
	var seq = $.trim($("#seq"+itemId).text());
	var lvl = $.trim($("#lvl"+itemId).val());
	var t = $.trim($("#t"+itemId).val());
	var pb = $.trim($("#pb"+itemId).val());
	var pm = $.trim($("#pm"+itemId).val()); 
	var atk = $.trim($("#atk"+itemId).val());
	var def = $.trim($("#def"+itemId).val());
	var img = $.trim($("#img"+itemId).val());
	var mlv = $.trim($("#mlv"+itemId).val());
	var cls = $.trim($("#cls"+itemId).val()); 
	var loot = $.trim($("#loot"+itemId).val());
	var lc = $.trim($("#lc"+itemId).val());
	$("#id_").val(id);
	$("#name_").val(name);
	$("#seq_").val(seq);
	$("#lvl_").val(lvl);
	$("#t_").val(t);
	$("#pb_").val(pb);
	$("#pm_").val(pm);
	$("#atk_").val(atk);
	$("#def_").val(def);
	$("#img_").val(img);
	$("#mlv_").val(mlv);
	$("#cls_").val(cls);
	$("#loot_").val(loot);
	$("#lc_").val(lc);
 
	editForm.submit();
	}
	
	function delWeapon(url){
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
		<strong style="font-size: 30">装备列表</strong>
		<c:if test="${my:hasPermission(backUser.roleId,50028)}">
			<input onclick="confirmRefresh('getWeaponList')" type="button" value="刷新列表" />
			<input onclick="add('<%=request.getContextPath()%>/admin/back_base_table/addWeapon.jsp?maxId=${maxId}')" type="button" value="新增装备" />
		</c:if>
		<table width="98%" border="0px" cellpadding="0" cellspacing="0" style="margin-left: 60"
			>
			<tr align="center">
				<td >
					ID
				</td>
				<td  >
					装备名称
				</td>
				<td  >
					SEQ
				</td>
				<td  >
					解锁等级
				</td>
				<td  >
					类型
				</td>
				<td  >
					价格
				</td>
				<td  >
					保养费
				</td>
					<td  >
					攻击
				</td>
					<td  >
					防御
				</td>
			
				<td  >
					图片
				</td>
				<td  >
					场景
				</td>
				<td  >
					级别
				</td>
				<td  >
					掉落任务
				</td>
				<td  >
					掉落概率
				</td>
				<td >
					编辑
				</td>
			</tr>
			<c:if test="${not empty weaponList}">
				<c:forEach var="weapon" items="${weaponList}" begin="0"
					varStatus="status">
							<tr align="center" >
								<td width="5%" id="id${weapon.id}">
									${weapon.id}
								</td>
								<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="name${weapon.id}"
										id="name${weapon.id}" />${weapon.name}</textarea>
								</td>
								
								<td width="5%" id="seq${weapon.id}">
									${weapon.seq}
								</td>
									<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="lvl${weapon.id}"
										id="lvl${weapon.id}" />${weapon.lvl}</textarea>
								</td>
									<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="t${weapon.id}"
										id="t${weapon.id}" />${weapon.t}</textarea>
								</td>
									<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="pb${weapon.id}"
										id="pb${weapon.id}" />${weapon.pb}</textarea>
								</td>	
										<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="pm${weapon.id}"
										id="pm${weapon.id}" />${weapon.pm}</textarea>
								</td>
											<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="atk${weapon.id}"
										id="atk${weapon.id}" />${weapon.atk}</textarea>
								</td>
											<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="def${weapon.id}"
										id="def${weapon.id}" />${weapon.def}</textarea>
								</td>
							
								<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="img${weapon.id}"
										id="img${weapon.id}" />${weapon.img}</textarea>
								</td>
								<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="mlv${weapon.id}"
										id="mlv${weapon.id}" />${weapon.mlv}</textarea>
								</td>
									<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="cls${weapon.id}"
										id="cls${weapon.id}" />${weapon.cls}</textarea>
								</td>
										<td style="word-break: break-all;">
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="loot${weapon.id}"
										id="loot${weapon.id}" />${weapon.loot}</textarea>
								</td>
								
								<td>
									<textarea rows=1 cols=8 class="tareacss"
										style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
										onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
										onblur="clearInterval(this.clock);activeobj.style.height=''" name="lc${weapon.id}"
										id="lc${weapon.id}" />${weapon.lc}</textarea>
								</td>
								<td >
									<a href="#" onclick="editWeapon('${weapon.id}')">编辑</a>&nbsp;&nbsp;
									<a href="#" onclick="delWeapon('comd=delWeapon&id='+'${weapon.id}')">删除</a><a
										href="#"></a>
								</td>
							</tr>
				</c:forEach>
			</c:if>
		</table>
		<form id="editForm" name="${item.name}"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=editWeapon"
			method="post">
			<input id="id_" name="id_" type="hidden" />
			<input id="name_" name="name_" type="hidden" />
			<input id="seq_" name="seq_" type="hidden" />
			<input id="lvl_" name="lvl_" type="hidden" />
			<input id="t_" name="t_" type="hidden" />
			<input id="pb_" name="pb_" type="hidden" />
				<input id="pm_" name="pm_" type="hidden" />
			<input id="atk_" name="atk_" type="hidden" />
			<input id="def_" name="def_" type="hidden" />
			<input id="img_" name="img_" type="hidden" />
			<input id="mlv_" name="mlv_" type="hidden" />
			<input id="cls_" name="cls_" type="hidden" />
				<input id="loot_" name="loot_" type="hidden" />
			<input id="lc_" name="lc_" type="hidden" />
		</form>




	</body>
</html>
