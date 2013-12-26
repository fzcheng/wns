<%@page import="cn.game.vo.basic.shop.ShopVO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my"  uri="/my" %>
  		
<html>
	<head>
		<title>江湖乱世传-推广后台</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/shopActivity.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
 
	function confirmRefresh() {

		if (confirm("确认刷新商品列表!\n刷新列表设置将生效!")) {
			window.location.href = '<%=request.getContextPath()%>/bacl_admin.do?comd=refreshShopList';
		}
	}

		</script>
	</head>
	<body class="main_body" onload="getMission('<%=request.getContextPath()%>/bacl_admin.do');">
	<c:if test="${message != null && message != ''}">
		<center><font color="#FF0000">${message}</font></center>
	</c:if>
		<br/>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">新增装备</strong>
		
		<form id="editForm" name="${item.name}"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=addWeapon"
			method="post" onsubmit="return confirmAddWeapon()" >
			<table border="0px" cellpadding="0" cellspacing="0" width="50%" style="margin-left: 60">
			
				<tr>
				<td align="center">ID:</td>
				<td>
					<input type="text"  name="id" id="id" onblur="onfoureId()"/><br>
					<input type="hidden"  name="maxid" id="maxid" value="<%=request.getParameter("maxId") %>"/><br>
					<span  style="color: red">ID必须以为大于<%=request.getParameter("maxId") %>(例如:<%=Integer.valueOf(request.getParameter("maxId"))+1 %>)</span>
 				</td>
			</tr>
			<tr>
				<td align="center">名称:</td>
				<td>
					<input type="text"  name="name" id="name"/>
 				</td>
			</tr>
				<tr>
				<td align="center">序号:</td>
				<td>
					<input type="text"  name="seq" id="seq"/>
 				</td>
			</tr>
			<tr>
				<td align="center">解锁等级:</td>
				<td>
					<input type="text"  name="lvl" id="lvl"/>
 				</td>
			</tr>
				<tr>
				<td align="center">类型:</td>
				<td>
					<select id="t" name="t">
						<option value="1" >攻击</option>
						<option value="2" >防御</option>
						<option value="3" >动物</option>
					</select>
 				</td>
			</tr>
				<tr>
				<td align="center">价格:</td>
				<td>
					<input type="text"  name="pb" id="pb"/>
 				</td>
			</tr>
				<tr>
				<td align="center">保养费:</td>
				<td>
					<input type="text"  name="pm" id="pm"/>
 				</td>
			</tr>
					<tr>
				<td align="center">攻击:</td>
				<td>
					<input type="text"  name="atk" id="atk"/>
 				</td>
			</tr>
					<tr>
				<td align="center">防御:</td>
				<td>
					<input type="text"  name="def" id="def"/>
 				</td>
			</tr>
			<tr> 
				<td align="right">图片</td>
				<td style = "padding-left: 2px">
					<select onchange="selectPic(this.value)" name="img" id="img" style="margin-bottom: 12px">
						<option value="gt001.png" >图片①</option>
						<option value="gt002.png" >图片②</option>
						<option value="gt003.png" >图片③</option>
						<option value="gt004.png" >图片④</option>
					</select>
					<img id="pic" src="<%=request.getContextPath()%>/img/gt001.png" width="40px" height="40px">
				</td>
			</tr>
			<tr>
				<td align="center">场景:</td>
				<td>
					<select id="mlv" name="mlv" >
					<option  value="1">小渔村</option>
					<option  value="2">扬州</option>
					<option  value="3">巫山</option>
					<option  value="4">黑木崖</option>
					<option  value="5">桃花岛</option>
					<option  value="6">襄阳</option>
					<option  value="7">京城</option>
					<option  value="8">华山</option>
		</select>
 				</td>
		</tr>
			<tr>
				<td align="center">级别:</td>
				<td>
					<input type="text"  name="cls" id="cls"/>
 				</td>
			</tr>
				<tr>
				<td align="center">掉落任务:</td>
				<td>
					<select id="loot" name="loot"></select>
 				</td>
			</tr>
				<tr>
				<td align="center">掉落概率:</td>
				<td>
					<input type="text"  name="lc" id="lc"/><span style="color: red">%(例如：0.05;0.06)</span>
 				</td>
			</tr>
	 </table>
		 <br/>
			<input id="itemId" name="itemId" type="hidden" />
		 	<input id="shopName" name="shopName" type="hidden" />
			<span style="margin-left: 300" ><input type="submit"  value="确认" />&nbsp;&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="返回列表" /></span>
		</form> <br/>
 
	</body>
	<script type="text/javascript">
	 function selectPic(val) {
	 
	var src = "<%=request.getContextPath()%>/img/" + val;
	$("#pic").attr("src", src);
	
}
function onfoureId(){
	var id=$("#id").val();
	var maxid=$("#maxid").val();
	if(id<=maxid){
		alert("你输入的ID必须大于"+maxid);
	}
	$("#id").val('we'+id);
}
	</script>
</html>
