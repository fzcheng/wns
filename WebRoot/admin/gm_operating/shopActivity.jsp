<%@page import="cn.game.vo.basic.shop.ShopVO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my"  uri="/my" %>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/shopActivity.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/refresh.js"></script>
		<script type="text/javascript">
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
			
	function window.onload(){
				getProps();
				var arr;
				if(arr=document.cookie.match(/scrollTop=([^;]+)(;|$)/))
				document.documentElement.scrollTop=parseInt(arr[1]);
				document.body.scrollTop=parseInt(arr[1]);
				
	}
		</script>
	</head>
	<body class="main_body">
	<c:if test="${message != null && message != ''}">
		<center><font color="#FF0000">${message}</font></center>
	</c:if>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">物品打包</strong>
		<table border="0px" cellpadding="0" cellspacing="0" width="50%" style="margin-left: 60">
			<tr>
				<td align="center">类别:</td>
				<td>
					<input onclick="getProps()" checked="checked" type="radio" name="pType" >道具&nbsp;
					<input onclick="getWeapon(1)" type="radio" name="pType" >武器&nbsp;
					<input onclick="getWeapon(2)" type="radio" name="pType" >防具&nbsp;
					<input onclick="getWeapon(3)" type="radio" name="pType" >神兽&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center">名称:</td>
				<td>
					<select id="panoply" name="panoply"></select>
				</td>
			</tr>
			<tr>
				<td align="center">数量:</td>
				<td>
				<input id="iValue" name="iValue" type="text" size="5"/>
				<input onclick="addItem()" type="button" value="Add">
				</td>
			</tr>
		</table>
		<br/>
		<form onsubmit="return confirmAddItem()" action="<%=request.getContextPath()%>/bacl_admin.do?comd=subPackage" method="post">
		<table width="50%" border="0px" cellpadding="0" cellspacing="0" style="margin-left: 60">
			<tr> 
				<td align="right" width="20%">物品包名称</td>
				<td style = "padding-left: 2px">
					<input id="pkgName" type="text" name="pkgName" />
				</td>
			</tr>
			<tr> 
				<td align="right" width="20%">物品包描述</td>
				<td style = "padding-left: 2px">
					<input type="text" name="pkgDesc" size="70"/>
				</td>
			</tr>
			<tr> 
				<td align="right">物品包图片</td>
				<td style = "padding-left: 2px">
					<select onchange="selectPic(this.value)" name="pkgPic" style="margin-bottom: 12px">
						<option value="gt001.png" >图片①</option>
						<option value="gt002.png" >图片②</option>
						<option value="gt003.png" >图片③</option>
						<option value="gt004.png" >图片④</option>
					</select>
					<img id="pic" src="<%=request.getContextPath()%>/img/gt001.png" width="40px" height="40px">
				</td>
			</tr>
			<tr> 
				<td align="right">价格</td>
				<td style = "padding-left: 2px">
					<input id="pkgValue" type="text" name="pkgValue" />
				</td>
			</tr>
			<tr> 
				<td align="right">开始时间</td>
				<td style = "padding-left: 2px">
					<input type="text" name="btime" readonly="readonly" onClick="setDayHM(this);"/>
				</td>
			</tr>
			<tr> 
				<td align="right">结束时间</td>
				<td style = "padding-left: 2px">
					<input type="text" name="etime" readonly="readonly" onClick="setDayHM(this);"/>
				</td>
			</tr>
			<tr> 
				<td align="right">假上限</td>
				<td style = "padding-left: 2px">
					<input type="text" name="maxcount1" />
				</td>
			</tr>
			<tr> 
				<td align="right">真上限</td>
				<td style = "padding-left: 2px">
					<input type="text" name="maxcount1" />
				</td>
			</tr>
			</table>
			<table id="table1" width="50%" border="0px" cellpadding="0" cellspacing="0" style="margin-left: 60">
			<tr align="center"> 
				<td width="10%">ID</td>
				<td width="40%">物品名称</td>
				<td width="30%">数量</td>
				<td width="20%">操作</td>
			</tr>
		</table><br/>
			<input id="roleId_sub" name="roleId" type="hidden" />
			<input type="submit" style="margin-left: 300" value="确认" />
		</form> <br/>
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">商品列表</strong>
		 <c:if test="${my:hasPermission(backUser.roleId,50028)}">
		 <input onclick="confirmRefresh()" type="button" value="刷新商品列表"/>
		 </c:if>
		 <c:if test="${message != null && message != ''}"><label style="color: red">${message}</label></c:if>
		 <table  width="98%" border="0px" cellpadding="0" cellspacing="0" style="margin-left: 10;empty-cells:show; border-collapse:collapse;" >
			<tr align="center"> 
				<td>SEQ</td>
				<td>商品名称</td>
				<td>价格</td>
				<td>折扣价</td>
				<td>折扣开始时间</td>
				<td>折扣结束时间</td>
				<td>限购真上限</td>
				<td>限购假上限</td>
				<td>开始时间</td>
				<td>结束时间</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			<%
			List<ShopVO> result = (List<ShopVO>)request.getAttribute("shopList");
			if(result != null && result.size() > 0){
				int size = result.size();
				for(int i=0;i < size;i++){
					ShopVO shop = result.get(i);
					int id = shop.getId();
			%>
			<tr align="center"> 
				<td><%=shop.getSeq()%></td>
				<td><%=shop.getName()%></td>
				<td>
				<c:if test="<%=shop.getIsOldShop()%>">
				<input id="price_<%=id%>" type="hidden" value="<%=shop.getPrice()%>" /> 
				<fmt:formatNumber value="<%=shop.getPrice()%>" type="number" />
				</c:if>
				<c:if test="<%=!shop.getIsOldShop()%>">
				<input id="price_<%=id%>" type="text" size="4" value="<%=(int)shop.getPrice()%>" />
				</c:if>
				</td>
				<td>
				<%if(shop.getOffprice() !=-1){%><input id="offprice_<%=id%>" type="text" size="4" value="<%=shop.getOffprice()%>"><%}%>
				<%if(shop.getOffprice() ==-1){%><input id="offprice_<%=id%>" type="text" size="4" ><%}%>
				</td>
				<td><input id="offbtime_<%=id%>" type="text" size="15" value="<fmt:formatDate value="<%=shop.getOffbtime()%>" pattern="yyyy-MM-dd HH:mm" />" readonly="readonly" onClick="setDayHM(this);"></td>
				<td><input id="offetime_<%=id%>" type="text" size="15" value="<fmt:formatDate value="<%=shop.getOffetime()%>" pattern="yyyy-MM-dd HH:mm" />" readonly="readonly" onClick="setDayHM(this);"></td>
				<td>
				<%if(shop.getMaxcount2() !=-1){%><input id="maxcount2_<%=id%>" type="text" size="4" value="<%=shop.getMaxcount2()%>" /><%}%>
				<%if(shop.getMaxcount2() ==-1){%><input id="maxcount2_<%=id%>" type="text" size="4" /><%}%>
				</td>
				<td>
				<%if(shop.getMaxcount1() !=-1){%><input id="maxcount1_<%=id%>" type="text" size="4" value="<%=shop.getMaxcount1()%>" /><%}%>
				<%if(shop.getMaxcount1() ==-1){%><input id="maxcount1_<%=id%>" type="text" size="4" /><%}%>
				</td>
				<td><input id="btime_<%=id%>" type="text" size="15" value="<fmt:formatDate value="<%=shop.getBtime()%>" pattern="yyyy-MM-dd HH:mm" />" readonly="readonly" onClick="setDayHM(this);"/></td>
				<td><input id="etime_<%=id%>" type="text" size="15" value="<fmt:formatDate value="<%=shop.getEtime()%>" pattern="yyyy-MM-dd HH:mm" />" readonly="readonly" onClick="setDayHM(this);"/></td>
				<td>
				<%if(shop.getStatus() == 0){
					out.write("正常");
				}else if(shop.getStatus() == 1){
					out.write("上架");
				}else if(shop.getStatus() == 2){
					out.write("下架");
				}
				%>
				</td>
				<td width="160">
				<%if(i != size-1){
					int id0 = shop.getId();
					int id1 = result.get(i+1).getId();
				%>
					<input type="button" value=" ↓ " onclick="swap(<%=id0%>,<%=id1%>)"/>
				<%}else{%>
					<input type="button" disabled="disabled" value=" ↓ " />
				<%}%>
				
				<%if(i != 0){
					int id0 = shop.getId();
					int id1 = result.get(i-1).getId();
				%>
					<input type="button" value=" ↑ " onclick="swap(<%=id0%>,<%=id1%>)"/>
				<%}else{%>
					<input type="button" disabled="disabled" value=" ↑ "/>
				<%}%>
				<input type="button" onclick="editShop(<%=shop.getId()%>)" value="编辑" />
				
				<%if(shop.getStatus() == 0 || shop.getStatus() == 2){%><input onclick="window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=upShop&id=<%=id%>'" type="button" value="上架" /><%}%>
				<%if(shop.getStatus() == 1){%><input onclick="window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=downShop&id=<%=id%>'" type="button" value="下架" /><%}%>
				<%if(shop.getStatus() == 2){%><input onclick="window.location.href='<%=request.getContextPath()%>/bacl_admin.do?comd=delShop&id=<%=id%>'" type="button" value="删除" /><%}%>
				</td>
			</tr>
			<%}}%>
		</table>
		
			<c:if test="${message != null && message != ''}">
		<center><font color="#FF0000">${message}</font></center>
	</c:if>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">物品上传</strong>
		<form onsubmit="return confirmAddItem1()" action="<%=request.getContextPath()%>/bacl_admin.do?comd=addShop" method="post">
		<table border="0px" cellpadding="0" cellspacing="0" width="50%" style="margin-left: 60">
			<tr>
				<td align="center">类别:</td>
				<td>
					<input onclick="getProps()" checked="checked" type="radio" name="pType" >道具&nbsp;
					<input onclick="getWeapon1(1)" type="radio" name="pType" >武器&nbsp;
					<input onclick="getWeapon1(2)" type="radio" name="pType" >防具&nbsp;
					<input onclick="getWeapon1(3)" type="radio" name="pType" >神兽&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center">名称:</td>
				<td>
					<select id="panoply1" name="panoply1" onchange="changeSelect()"></select>
				</td>
			</tr>
			<tr>
				<td align="center">数量:</td>
				<td>
				<input id="iValue1" name="iValue1" type="text" size="5"/>
				</td>
			</tr>
				<tr>
				<td align="center">价格:</td>
				<td>
				<input id="shopprice" name="shopprice" type="text" size="5"/>
				</td>
			</tr>
			<tr> 
				<td align="right">物品图片</td>
				<td style = "padding-left: 2px">
					<select onchange="selectPic(this.value)" name="shopPic" id="shopPic" style="margin-bottom: 12px">
						<option value="gt001.png" >图片①</option>
						<option value="gt002.png" >图片②</option>
						<option value="gt003.png" >图片③</option>
						<option value="gt004.png" >图片④</option>
					</select>
					<img id="pic" src="<%=request.getContextPath()%>/img/gt001.png" width="40px" height="40px">
				</td>
			</tr>
				<tr> 
				<td align="right" width="20%">物品描述</td>
				<td style = "padding-left: 2px">
					<input type="text" name="shopDesc" size="70"/>
				</td>
			</tr>
			<tr> 
				<td align="right">开始时间</td>
				<td style = "padding-left: 2px">
					<input type="text" name="btime" readonly="readonly" onClick="setDayHM(this);"/>
				</td>
			</tr>
			<tr> 
				<td align="right">结束时间</td>
				<td style = "padding-left: 2px">
					<input type="text" name="etime" readonly="readonly" onClick="setDayHM(this);"/>
				</td>
			</tr>
			<tr> 
				<td align="right">假上限</td>
				<td style = "padding-left: 2px">
					<input type="text" name="maxcount1" />
				</td>
			</tr>
			<tr> 
				<td align="right">真上限</td>
				<td style = "padding-left: 2px">
					<input type="text" name="maxcount1" />
				</td>
			</tr>
			</table>
		 <br/>
			<input id="itemId" name="itemId" type="hidden" />
		 	<input id="shopName" name="shopName" type="hidden" />
			<input type="submit" style="margin-left: 300" value="确认" />
		</form> <br/>
		
		 <form id="editForm" action="<%=request.getContextPath()%>/bacl_admin.do?comd=editShop" method="post">
		 	<input id="shopId" name="shopId" type="hidden" />
		 	<input id="price" name="price" type="hidden" />
		 	<input id="offprice" name="offprice" type="hidden" />
		 	<input id="offbtime" name="offbtime" type="hidden" />
		 	<input id="offetime" name="offetime" type="hidden" />
		 	<input id="maxcount1" name="maxcount1" type="hidden" />
		 	<input id="maxcount2" name="maxcount2" type="hidden" />
		 	<input id="btime" name="btime" type="hidden" />
		 	<input id="etime" name="etime" type="hidden" />
		 </form>
	</body>
</html>
