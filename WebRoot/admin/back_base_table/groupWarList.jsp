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
   function  editExchangeRules(itemId){
	
	var id =itemId;
	var type = $.trim($("#type"+itemId).text());
	var dsc = $.trim($("#dsc"+itemId).val());
	var meterials = $.trim($("#meterials"+itemId).val());
	var aim = $.trim($("#aim"+itemId).val()); 
	$("#id_").val(id);
	$("#type_").val(type);
	$("#dsc_").val(dsc);
	$("#meterials_").val(meterials);
	$("#aim_").val(aim);
 
	editForm.submit();
	}
	
	function delExchangeRules(url){
	alert(url);
		if(confirm('确认删除吗?')) {
			window.location.href = "bacl_admin.do?" +url;
		}
	}	
	
	function  getInfo(grpId){
	 window.location.href="bacl_admin.do?comd=groupWarMatchreById&groupId="+grpId;
		
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
		<strong style="font-size: 30">帮战信息</strong>
		<c:if test="${my:hasPermission(backUser.roleId,50028)}">
			<input onclick="confirmRefresh()" type="button" value="刷新列表" />
		</c:if>
		
		<table width="98%" border="0px" cellpadding="0" cellspacing="0"
			style="margin-left: 10; empty-cells: show; border-collapse: collapse;">
			<tr align="center">
				<td width="5%">
					帮战名称
				</td>
				<td width="5%">
					帮会ID
				</td>
				<td width="15%">
					对战帮会
				</td>
				<td width="5%">
					对战帮会ID
				</td>
				<td width="5%">
					总积分
				</td>
				<td width="5%">
					本届积分
				</td>
				<td width="5%">
					上一届积分
				</td>
					<td width="5%">
					总紫金宝盒数
				</td>
						<td width="5%">
					总白金宝盒数
				</td>
						<td width="5%">
					挑战结果
				</td>
			 
			</tr>
			<!-- background-color:#339999; -->
			<c:if test="${not empty groupWarList}">
				<c:forEach var="groupWar" items="${groupWarList}" begin="0"
					varStatus="status">
							<tr align="center">
								<td >
									<input type="hidden" value="${groupWar.groupId}" name="chk" />
									<span name="iouid" ><a  href ="#" onclick="getInfo('${groupWar.groupId}')">${groupWar.groupName}</a></span>
								</td>
									<td >
									${groupWar.groupId}
								</td>
									<td >
									${groupWar.bgroupName}
								</td>
									<td >
									${groupWar.bgroupId}
								</td>
									<td >
									${groupWar.totalscore}
								</td>
									<td >
									${groupWar.score}
								</td>
									<td >
									${groupWar.prescore}
								</td>
								
									<td >
									${groupWar.sumCountZ}
								</td>
									<td >
									${groupWar.sumCountB}
								</td>
								<!-- 战况 0-未报名 1-报名状态 2-轮空 3-配对 4-胜利 5-失败 -->
						 	<td >
								<c:if test="${4 eq groupWar.isStatus}">胜利 </c:if>
								<c:if test="${5 eq groupWar.isStatus}">失败 </c:if>
								</td>
							</tr>
					 
				</c:forEach>
			</c:if>
		</table>
		<form id="editForm"
			action="<%=request.getContextPath()%>/bacl_admin.do?comd=editExchangeRules"
			method="post">
			<input id="id_" name="id_" type="hidden" />
			<input id="type_" name="type_" type="hidden" />
			<input id="dsc_" name="dsc_" type="hidden" />
			<input id="meterials_" name="meterials_" type="hidden" />
			<input id="aim_" name="aim_" type="hidden" />
		</form>
	</body>
	<!-- 
<script type="text/javascript">
	
function regShowDetail() {
 
	$("span[name=iouid]").unbind('click').bind('click', function(){
		showDetail($(this).parent().parent().find('input[name=chk]').val());
	});
}

function showDetail(userId) {
	// 创建一个对话框组件
	var dialog = $.weeboxs.open('admin/back_base_table/showDetail.html', {
		title: '正在加载对战信息……',
		contentType: 'ajax',
		width: 600,
		height: 350,
		animate: true,
		clickClose: false,
		type: 'wee',
		//弹窗类型，目前有dialog,error,warning,success,wee,prompt,
		//给弹窗设置其它的样式，用此可以改变弹窗的样式boxclass: null,
		
		showOk: false,
		cancelBtnName: '关 闭',
		onopen: innerShowDetail
	});
 
 }
	
	function innerShowDetail() {
	 
		// 获得JSON格式的数据
		$.getJSON('bacl_admin.do?comd=groupWarMatchreById',{groupId : '38'}, function(jsons) {
			// 根据key设置value
			 var continents = jsons.jobs;  
			  
 
			//设置对话框标题和内容
			$('#detailDiv').removeAttr('class');
			dialog.setTitle('查看帮会对战信息');
			dialog.setContent($('#detailDiv').html());
		});
	}
	
			<SCRIPT src="/js/weetips.js" type=text/javascript></SCRIPT>
	 <SCRIPT src="/js/weebox.js" type=text/javascript></SCRIPT>
	<SCRIPT src="/js/bgiframe.js" type=text/javascript></SCRIPT>
	 <link rel="stylesheet" type="text/css" href="/css/wee.css">
		
</script>
 -->
</html>
