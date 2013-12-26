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
	background-color: #ccc;
	border: 0px;
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
   function  editAllShop(itemId){
	
	var id =itemId;
	var seq = $.trim($("#seq"+itemId).text());
	var name = $.trim($("#name"+itemId).val());
	var desc = $.trim($("#desc"+itemId).val());
	var showtype = $.trim($("#showtype"+itemId).val());
	//var pricetype = $.trim($("#pricetype"+itemId).val()); 
	var price = $.trim($("#price"+itemId).val());
	var img = $.trim($("#img"+itemId).val());
	//var itemtype = $.trim($("#itemtype"+itemId).val());
	var itemid = $.trim($("#itemid"+itemId).val());
	var itemvalue = $.trim($("#itemvalue"+itemId).val()); 
	var offprice = $.trim($("#offprice"+itemId).val());
	//var offbtime = $.trim($("#offbtime"+itemId).val());
	//var offetime = $.trim($("#offetime"+itemId).val());
	//var btime = $.trim($("#btime"+itemId).val());
	//var etime = $.trim($("#etime"+itemId).val()); 
	var maxcount1 = $.trim($("#maxcount1"+itemId).val()); 
	var maxcount2 = $.trim($("#maxcount2"+itemId).val()); 
	var curcount = $.trim($("#curcount"+itemId).val()); 
	var status = $.trim($("#status"+itemId).val()); 
	$("#id_").val(id);
	$("#seq_").val(seq);
	$("#name_").val(name);
	$("#desc_").val(desc);
	$("#showtype_").val(showtype);
	//$("#pricetype_").val(pricetype);
	$("#price_").val(price);
	$("#img_").val(img);
	//$("#itemtype_").val(itemtype);
	$("#itemid_").val(itemid);
	$("#itemvalue_").val(itemvalue);
	$("#offprice_").val(offprice);
	//$("#offbtime_").val(offbtime);
	//$("#offetime_").val(offetime);
	//$("#btime_").val(btime);
	//$("#etime_").val(etime);
	$("#maxcount1_").val(maxcount1);
	$("#maxcount2_").val(maxcount2);
	$("#curcount_").val(curcount);
	$("#status_").val(status);
	
 
	editForm.submit();
	}
	
	function delOneShop(url){
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
		<strong style="font-size: 30">黑店</strong>
		<c:if test="${my:hasPermission(backUser.roleId,50028)}">
			<input onclick="confirmRefresh('getAllShopList')" type="button" value="刷新列表" />
		</c:if>
		 
		<form id="workForm" onsubmit="return checkData()" method="post" action="<%=request.getContextPath()%>/bacl_admin.do">
		<input id="comd" name="comd" value="getAllShopList" type="hidden" />
		<input id="index" name="c_index" value="${paging.pageIndex}" type="hidden" />
		<input id="id2" name="id2" value="${id}" type="hidden" />
		<input id="name2" name="name2" value="${name}" type="hidden" />
		<input id="showtype2" name="showtype2" value="${showtype}" type="hidden" />
		<input id="totalPage" name="totalPage" value="${paging.totalPage}" type="hidden" />
			<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr> 
						<td align="center" colspan="12" > 
						物品ID：<input id="id" name="id" value="${id}" type="text" size="15" />&nbsp;&nbsp;
						&nbsp;
						物品名称：<input id="name" name="name" value="${name}" type="text" size="15" />&nbsp;&nbsp;
						类型：<input id="showtype" name="showtype" value="${showtype}" type="text" size="15" />&nbsp;&nbsp;
						&nbsp;
						<input type="submit" value="查询" />
						</td>
					</tr>
			
			</table>
		
		</form>
		 
		<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr align="center">
				<td>
					ID
				</td>
				<td >
					序号
				</td>
				<td  >
					名称
				</td>
				<td>
					描述
				</td>
				<td >
					类别
				</td>
				<td >
					价格类别
				</td>
				<td >
					价格
				</td >
				<td>
					图片
				</td>
				<td >
					物品类型
				</td>
				<td >
					物品ID
				</td>
				<td >
					物品个数
				</td>
				<td >
					折扣价
				</td>
				<!-- 
				<td width="5%">
					offbtime
				</td>
				<td width="5%">
					offetime
				</td>
				<td width="5%">
					btime
				</td>
				<td width="5%">
					etime
				</td>
				 -->
				<td >
					假上限数
				</td>
				<td >
					真上限数
				</td>
				<td >
					上线数
				</td>
				<td >
					状态
				</td>
				<td >
					编辑
				</td>
			</tr>
			<c:if test="${not empty shopList}">
				<c:forEach var="shop" items="${shopList}" begin="0"
					varStatus="status">
					<tr align="center">
						<td>
							${shop.id}
						</td>


						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="seq${shop.id}" />${shop.seq}</textarea>
						</td>

						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="name${shop.id}" />${shop.name}</textarea>
						</td>
						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="desc${shop.id}" />${shop.desc}</textarea>
						</td>


						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="showtype${shop.id}" />${shop.showtype}</textarea>
						</td>

						<!--  1-奖励点 2-充值 -->
						<td style="word-break: break-all;">
						 
								<c:if test="${1 eq shop.pricetype}">奖励点 </c:if>
								<c:if test="${2 eq shop.pricetype}">充值 </c:if>
						</td>
						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="price${shop.id}" />${shop.price}</textarea>
						</td>
						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="img${shop.id}" />${shop.img}</textarea>
						</td>
						
						<!-- 1-回复血 2-回复气 3-升级点 4-奖励点 5-物品 -->
						<td style="word-break: break-all;">
								<c:if test="${1 eq shop.itemtype}">回复血 </c:if>
								<c:if test="${2 eq shop.itemtype}">回复气 </c:if>
								<c:if test="${3 eq shop.itemtype}">升级点 </c:if>
								<c:if test="${4 eq shop.itemtype}">奖励点 </c:if>
								<c:if test="${5 eq shop.itemtype}">物品 </c:if>
							 
						</td>
						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="itemid${shop.id}" />${shop.itemid}</textarea>
						</td>
						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="itemvalue${shop.id}" />${shop.itemvalue}</textarea>
						</td>
						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="offprice${shop.id}" />${shop.offprice}</textarea>
						</td>
					 <!-- 
							<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="offetime${shop.id}" /><fmt:formatDate value="${shop.offbtime}"
									pattern="yyyy-MM-dd HH:mm" /></textarea>
						</td>
								<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="offetime${shop.id}" /><fmt:formatDate value="${shop.offbtime}"
									pattern="yyyy-MM-dd HH:mm" /></textarea>
						</td>
								<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="btime${shop.id}" /><fmt:formatDate value="${shop.btime}"
									pattern="yyyy-MM-dd HH:mm" /></textarea>
						</td>
									<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="etime${shop.id}" /><fmt:formatDate value="${shop.etime}"
									pattern="yyyy-MM-dd HH:mm" /></textarea>
						</td> 
					  -->

						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="maxcount1${shop.id}" />${shop.maxcount1}</textarea>
						</td>
						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="maxcount2${shop.id}" />${shop.maxcount2}</textarea>
						</td>
						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="curcount${shop.id}" />${shop.curcount}</textarea>
						</td>
						<td style="word-break: break-all;">
							<textarea rows=1 cols=8 class="tareacss"
								style='overflow: scroll; overflow-y: hidden;; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);activeobj.style.height=''"
								id="status${shop.id}" />${shop.status}</textarea>
						</td>
						<td>
							<a href="#" onclick="editAllShop('${shop.id}')">编辑</a>&nbsp;&nbsp;
							<a href="#"
								onclick="delOneShop('comd=delOneShop&id_='+'${shop.id}')">删除</a><a
								href="#"></a>
						</td>
					</tr>
				</c:forEach>
					<tr>
				<td > 
				<td align="center" colspan="12">
				(${paging.pageIndex}/${paging.totalPage})
				<a href="javascript:toFirst('member')">首页</a>&nbsp;
				<a href="javascript:upPage('member')">上一页</a>&nbsp;
				<a href="javascript:downPage('member')">下一页</a>&nbsp;
				<a href="javascript:toLast('member')">末页</a>&nbsp;
				<input id="input" size="2" type="text" />&nbsp;
				<a href="javascript:goTo('member')">Go...</a>&nbsp;
				</td>
				</tr>
			</c:if>
		</table>
		<form id="editForm"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=editAllShop"
			method="post">
			<input id="id_" name="id_" type="hidden" />
			<input id="seq_" name="seq_" type="hidden" />
			<input id="name_" name="name_" type="hidden" />
			<input id="desc_" name="desc_" type="hidden" />
			<input id="showtype_" name="showtype_" type="hidden" />
			<!-- <input id="pricetype_" name="pricetype_" type="hidden" /> 
			<input id="itemtype_" name="itemtype_" type="hidden" />
			-->
			<input id="price_" name="price_" type="hidden" />
			<input id="img_" name="img_" type="hidden" />
			 
			<input id="itemid_" name="itemid_" type="hidden" />
			<input id="itemvalue_" name="itemvalue_" type="hidden" />
			<input id="offprice_" name="offprice_" type="hidden" />
			<input id="offbtime_" name="offbtime_" type="hidden" />
			<input id="offetime_" name="offetime_" type="hidden" />
			<input id="btime_" name="btime_" type="hidden" />
			<input id="etime_" name="etime_" type="hidden" />
			<input id="maxcount1_" name="maxcount1_" type="hidden" />
			<input id="maxcount2_" name="maxcount2_" type="hidden" />
			<input id="curcount_" name="curcount_" type="hidden" />
			<input id="status_" name="status_" type="hidden" />
		</form>
	</body>
	<script type="text/javascript">
	
				function getTypeList(){
				$("#index").val("1");
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
				var id = $("#id").val();
				var id2 = $("#id2").val();
				var name = $("#name").val();
				var name2 = $("#name2").val();
				var showtype2 = $("#showtype2").val();
				var showtype = $("#showtype").val();
				if(id !=id2 || name!=name2||showtype2 !=showtype){
					$("#index").val("1");	
				}
				if(typeId == 0){
					$("#type").val(0);
				}else{
					$("#type").val($("#sel2").val());
				}
				return true;
			}
			
			
			function subPage(input){
				$("#index").val(input);
			    workForm.submit();
			}
		
			function goTo(){
				var index = $.trim($("#input").val());
				var total = $("#totalPage").val();
			    var reg = new RegExp("^[0-9]*$");
			    if(index=='' || !reg.test(index) || parseInt(index) > parseInt(total)){
			        alert("请输入正确的页数!");
			        return;
			    }
			    subPage(index);
			}
			
			function downPage(){
				var index = $("#index").val();
				var total = $("#totalPage").val();
				if(parseInt(index) >= parseInt(total)){
					alert("已经是最后一页!");
					return;
				}
				subPage(parseInt(index)+1);
			}
			
			function upPage(){
				var index = $("#index").val();
				if(index == 1){
					alert("已经是第一页!");
					return;
				}
				subPage(parseInt(index)-1);
			}
			
			function toFirst(){
				subPage(1);
			}
			
			function toLast(){
				var index = $("#totalPage").val();
				subPage(index);
			}
	
	</script>
</html>
