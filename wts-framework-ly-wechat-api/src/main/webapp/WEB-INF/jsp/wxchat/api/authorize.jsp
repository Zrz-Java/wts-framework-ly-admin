<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String auditStatus =String.valueOf(request.getSession().getAttribute("auditStatus"));
if(auditStatus.equals("1")){
    response.sendRedirect("../../u/account/list.jhtml");
}else{
    response.sendRedirect("../../u/account/bind-logo.jhtml");
}
%>