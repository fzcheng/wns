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
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=addWeaponBox"
			method="post" onsubmit="return confirmAddWeapon()" >
			<table border="0px" cellpadding="0" cellspacing="0" width="50%" style="margin-left: 60">
			
				<tr>
				<td align="center">ID:</td>
				<td>
					<input type="text"  name="id" id="id"/><br>
					<span  style="color: red">ID必须以为大于<%=request.getParameter("maxId") %>(例如：<%=Integer.valueOf(request.getParameter("maxId"))+1 %>)</span>
 				</td>
			</tr>
			<tr>
				<td align="center">名称:</td>
				<td>
					<input type="text"  name="name" id="name"/>
 				</td>
			</tr>
	 		<tr>
				<td align="center">描述:</td>
				<td>
					<textarea rows="10" id="desc" name="desc" cols="30"></textarea>

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
				<td align="center">价格:</td>
				<td>
					<input type="text"  name="rp" id="rp"/>
 				</td>
			</tr>
				<tr>
				<td align="center">掉落概率:</td>
				<td>
					<textarea rows="10" id="we" name="we" cols="30"></textarea>%(例如：0.05;0.06)</span>
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
