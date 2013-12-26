<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>江湖乱世传-推广后台 </title>
		<link href="<%=request.getContextPath()%>/css/lift.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/oschina.css" type="text/css" media="screen" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript">
			    function setheight() {
			        var sidebar=document.getElementById('myDIV');
			        sidebar.style.height=this.document.body.offsetHeight+'px'+100;
			    }
		</script>
	</head>

	<body onload="setheight()" onmouseover="self.status='欢迎光临本站';return true" >
	
		<table width='100%' cellspacing='0' cellpadding='0'>
			<tr>
				<td width='100%' valign='top' class='TAGS'>
					<div class='VerticalMenu' style="overflow:auto;" id="myDIV" >
						<ul class='Level_1'>
							<c:forEach items="${backPowerVOList}" var="backPowerVO" step="1" varStatus="status">
								<c:if test="${0 == backPowerVO.parentId}">
									<li>
										<a href="javascript:toggle('menuItem_${status.count }')" class='menuItem'>${backPowerVO.powerName}</a>
										<ul class='menuItemList' id='menuItem_${status.count }'>
											<c:forEach items="${backPowerVOList}" var="backPowerVOSon" >
											<c:choose>
												<c:when test="${backPowerVO.powerSn != 'operationDataLog'}">
													<!-- 判断是否属于这个的子类 -->
													<c:if test="${backPowerVO.powerId == backPowerVOSon.parentId}">
													<li>
														<a href="<%=request.getContextPath()%>/${backPowerVOSon.linkUrl }" target="main">${backPowerVOSon.powerName }</a>
													</li>
													</c:if>
												</c:when>
												<c:otherwise>
														<!-- 判断是否属于这个的子类 -->
													<c:if test="${backPowerVO.powerId == backPowerVOSon.parentId}">
													<li>
														<a href="#"  onclick="sendUr('${backPowerVOSon.linkUrl }')" target="main">${backPowerVOSon.powerName }</a>
													</li>
													</c:if>
												</c:otherwise>
											</c:choose>
											</c:forEach>
										</ul>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</td>
			</tr>
		</table>
		
	</body>
</html>