<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
  		
<html>
	<head>
		<title>江湖行后台管理系统</title>
	</head>
	<body class="main_body">
	    <%
	    String message = (String)request.getAttribute("message");
	     if(message != null){
	     %>
	     <center><font color="#FF0000">发送成功!</font></center>
	     <%
	     }
	     %>
	</body>
</html>
