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
	<body class="main_body">
	<c:if test="${message != null && message != ''}">
		<center><font color="#FF0000">${message}</font></center>
	</c:if>
		<br/>
		 
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">新增道具</strong>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">物品上传2</strong>
		<form  action="<%=request.getContextPath()%>/bacl_admin.do?comd=addItems" method="post">
		<table border="0px" cellpadding="0" cellspacing="0" width="50%" style="margin-left: 60">
			<tr>
				<td align="center">名称:</td>
				<td>
					<input type="text"  name="name" id="name"/>
 				</td>
			</tr>
			<tr>
				<td align="center">类别:</td>
				<!-- 1=材料  2=物品  3=w组合包 -->
				<td>
					<input  checked="checked" type="radio" name="type" value="1" >材料&nbsp;
					<input   type="radio" name="type" value="2">物品&nbsp;
					<input   type="radio" name="type" value="3" >组合包&nbsp;
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
				<td align="right" width="20%">道具描述</td>
				<td style = "padding-left: 2px">
					<textarea rows="10" id="desc" name="desc" cols="30"></textarea>
				</td>
			</tr>
				<tr> 
				<td align="right" width="20%">属性</td>
				<td style = "padding-left: 2px">
					<textarea rows="10" id="eff" name="eff" cols="30"></textarea>
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
	</script>
</html>
