<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/public_include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Proxy</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    	<c:url value="/resources/text.txt" var="url"/>
		<br>
		JSTL URL: ${url} - <ixm:starstr end="1" value="${userName}" start="1"></ixm:starstr>
		<br>
		Message: ${message}
  </body>
</html>
