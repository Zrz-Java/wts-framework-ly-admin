<%@page import="com.base.cluster.ClusterDataManager"%>
<%@page import="com.base.empty.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="we" uri="http://www.xmtbyh.com/we"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	request.setAttribute("title", "手机服务管理系统");
	User loginUser = ClusterDataManager.getUser(request.getSession().getId());
	request.setAttribute("loginUser", loginUser);
%>