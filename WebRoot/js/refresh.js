  function confirmRefresh(methods) {

	if (confirm("确认刷新列表!\n刷新列表设置将生效")) {
		window.location.href = 'bacl_admin.do?comd=refreshShopList&methods='+methods;
	}
}