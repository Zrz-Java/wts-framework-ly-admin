<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="../u/include/public_server.jsp"%>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="format-detection" content="telephone=no;email=no">
    <meta name="applicable-device" content="pc,mobile">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>${title }</title>
    <meta content="${description }" name="description">
    <meta content="${keywords }" name="keywords">
    <%@ include file= "../u/include/public_import.jsp" %>
</head>

<body>
<div class="page_con">
	<div class="bg">
        <div class="nav_tit"><h2>抄码明细</h2><span class="return"></span></div>
    </div>
    <div class="bg mar_t">
    	<div class="tit">2019年2月水表抄码明细</div>
        <div class="con3" style="display:block">
            	<ul>
                	<li><span>用户号</span>12345678</li>
                    <li><span>用户号</span>12345678</li>
                    <li><span>上次抄码</span>0</li>
                    <li><span>本次抄码</span>0</li>
                </ul>
            </div>
    </div>
</div></body>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/sj_js.js"></script>
</html>
