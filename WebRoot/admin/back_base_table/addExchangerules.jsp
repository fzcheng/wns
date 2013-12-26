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
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">新增兑换规则</strong>
		
		<form id="editForm" name="${item.name}"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=addExchangeRules"
			method="post" >
			<table border="0px" cellpadding="0" cellspacing="0" width="50%" style="margin-left: 60">
			
			<tr>
				<td align="center">类型::</td>
				<td>
					<select id="type" name="type" >
						<option  value="0">兑换稀有物品</option>
						<option  value="1">兑换积分</option>
					</select>
 				</td>
		</tr>
	 		<tr>
				<td align="center">兑换物品:</td>
				<td>
					<textarea rows="2" id="meterials" name="meterials" cols="15"></textarea> <span>(请参照原有数据)</span>
 				</td>
			</tr>
			
			<tr>
				<td align="center">被兑换物:</td>
				<td>
					<textarea rows="2" id="aim" name="aim" cols="15"></textarea><span>(请参照原有数据)</span>
 				</td>
			</tr>
				<tr>
				<td align="center">属性描述:</td>
				<td>
					<textarea rows="2" id="dsc" name="dsc" cols="15"></textarea><span>(对兑换物品描述)</span>
 				</td>
			</tr>
	 </table>
		 <br/>
			<span style="margin-left: 300" ><input type="submit"  value="确认" />&nbsp;&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="返回列表" /></span>
		</form> <br/>
	</body>
	
<script type="text/javascript">
	function confirmAddWeapon(){
		var reg = new RegExp("^[1-9][0-9]*$");
		var rp=$("#rp").val();
		var id=$("#id").val();
		if (rp.length == 0 || !reg.test(rp)) {
			alert("请输入正确的数量!");
			return false;
		}
	}
</script>
</html>
