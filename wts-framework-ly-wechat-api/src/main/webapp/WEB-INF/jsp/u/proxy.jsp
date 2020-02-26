<%@page import="com.trt.framework.util.CMyString"%>
<%@page import="com.trt.framework.util.CMyAesEncode"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String j = request.getParameter("j");
	String returnUrl =request.getContextPath() + "/u/";
	if(!CMyString.isEmpty(j)){
		returnUrl = CMyAesEncode.decrypt(j, CMyAesEncode.PASSWORD);
	}
%>
<script type="text/javascript">
	window.location.href = "<%=returnUrl%>";
</script>