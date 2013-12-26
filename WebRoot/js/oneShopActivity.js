
 function getWeapon(type) {
	$.ajax( {
		type : "POST",
		url : "bacl_admin.do",
		data : "comd=getWeapon&parent=" + type,
		dataType : "xml",
		success : function(xml) {
			clear();
			$(xml).find("OPTION").each(function() {
				var option = $(this);
				var val = option.find("VAL").text();
				var name = option.find("TEXT").text();

				var option = document.createElement("option");
				option.setAttribute("value", val);
				var text = document.createTextNode("[" + val + "] " + name);
				option.appendChild(text);
				$("#panoply").get(0).appendChild(option);
			});
		}
	});

} 


 function selectPic(val) {
	var src = "<%=request.getContextPath()%>/img/" + val;
	$("#pic").attr("src", src);
}

 function addItem() {
	var quatity = $("#iValue").val();

	var itemId = $("#panoply").val();
	var text = $("#panoply option:selected").text();
	var itemName = text.substr(text.indexOf("]") + 1);

	var reg = new RegExp("^[1-9][0-9]*$");
	if (quatity.length == 0 || !reg.test(quatity)) {
		alert("请输入正确的数量!");
		return;
	} else {
		$
				.ajax( {
					type : "POST",
					url : "bacl_admin.do",
					data : "comd=addItem&itemId=" + itemId + "&itemName="
							+ itemName + "&quatity=" + quatity,
					dataType : "json",
					success : function(data) {
						removeAll();
						$
								.each(
										data.items,
										function(idx, item) {
											$(
													"<tr align='center'><td>"
															+ item.itemId
															+ "</td><td>"
															+ item.itemName
															+ "</td><td>"
															+ item.quatity
															+ "</td><td><input onclick=remove('"
															+ item.itemId
															+ "') type='button' value='Remove'></td></tr>")
													.insertAfter(
															$("#table1 tr:eq(0)"));
										});
					}
				});
	}
}

function clear() {
	var selectobj = $("#panoply").get(0);
	var s = selectobj.options;
	var n = s.length;
	if (s && n > 0) {
		for ( var i = 0; i < n; i++) {
			selectobj.removeChild(s[0]);
		}
	}
}

function removeAll() {
	$("#table1 tr:not(:first)").remove();
}

function remove(itemId) {
	$.ajax( {
		type : "POST",
		url : "bacl_admin.do",
		data : "comd=removeItem&itemId=" + itemId,
		dataType : "json",
		success : function(data) {
			removeAll();
			$.each(data.items, function(idx, item) {
				$(
						"<tr align='center'><td>" + item.itemId + "</td><td>"
								+ item.itemName + "</td><td>" + item.quatity
								+ "</td><td><input onclick=remove('"
								+ item.itemId
								+ "') type='button' value='Remove'></td></tr>")
						.insertAfter($("#table1 tr:eq(0)"));
			});
		}
	});
}

function confirmAddItem() {
	var trs = $("#table1").find("tr");
	if (trs.length <= 1) {
		alert("请先添加物品!");
		return false;
	}

	if (confirm("完成打包!")) {
		var pkgName = $.trim($("#pkgName").val());
		var pkgValue = $.trim($("pkgValue").val());
		if (pkgName == '' || itemName.length == 0) {
			alert("物品包名称不能为空");
			return false;
		}
		var reg = new RegExp("^[1-9][0-9]*$");
		if (pkgValue.length == 0 || !reg.test(pkgValue)) {
			alert("物品价格输入不正确!");
			return false;
		}
		$("#itemName").val(itemName);
		$("#pkgValue").val(pkgValue);
		return true;
	}
	return false;
}

function swap(id0, id1) {
	var url = "<%=request.getContextPath()%>/bacl_admin.do?comd=orderShopList";
	url += "&id0=" + id0;
	url += "&id1=" + id1;
	window.location.href = url;
}

function editShop(id) {
	var price = $.trim($("#price_" + id).val());
	var offprice = $.trim($("#offprice_" + id).val());
	var offbtime = $.trim($("#offbtime_" + id).val());
	var offetime = $.trim($("#offetime_" + id).val());
	var maxcount1 = $.trim($("#maxcount1_" + id).val());
	var maxcount2 = $.trim($("#maxcount2_" + id).val());
	var btime = $.trim($("#btime_" + id).val());
	var etime = $.trim($("#etime_" + id).val());

	$("#shopId").val(id);
	$("#price").val(price);
	$("#offprice").val(offprice);
	$("#offbtime").val(offbtime);
	$("#offetime").val(offetime);
	$("#maxcount1").val(maxcount1);
	$("#maxcount2").val(maxcount2);
	$("#btime").val(btime);
	$("#etime").val(etime);

	editForm.submit();
}

function confirmRefresh() {

	if (confirm("确认刷新商品列表!\n刷新列表设置将生效!")) {
		window.location.href = '<%=request.getContextPath()%>/bacl_admin.do?comd=refreshShopList';
	}
}