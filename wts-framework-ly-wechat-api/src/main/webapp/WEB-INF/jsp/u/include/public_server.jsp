<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.xiamenwater.com/swjt_wechat" prefix="my" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	request.setAttribute("title", "厦门·水务集团微信平台");
	request.setAttribute("description", "厦门·水务集团微信平台");
	request.setAttribute("keywords", "厦门·水务集团微信平台");
	
//	Member member = ClusterDataManager.getMember(request.getSession().getId());
//	if(null != member){
//		request.setAttribute("member", member);
//	}
%>