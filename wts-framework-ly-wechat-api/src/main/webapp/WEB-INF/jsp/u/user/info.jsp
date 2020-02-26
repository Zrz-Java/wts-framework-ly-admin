<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="../include/public_server.jsp"%>
<%request.setAttribute("TITLE","个人基本信息");%>
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
  <%@ include file= "../include/public_import.jsp" %>
</head>

<body>
  <%@ include file= "../include/public_header.jsp" %>
  <div class="bg2">
  	<span><img src="${pageContext.request.contextPath}/images/img_03.png"></span>
  </div>
  <div class="bg">
    <div class="con3" style="display:block">
      <ul>
        <li><span>用户号：</span>${wxAccount.v.ACCOUNTID}</li>
        <li><span>业主姓名：</span>${wxAccount.v.ACCOUNT}</li>
        <li><span>手机号：</span><my:starstr start="3" end="3" value="${wxAccount.v.MOBILE}"></my:starstr></li>
        <li><span>地址 : </span>${wxAccount.v.ADDRESS == ""?"无":wxAccount.v.ADDRESS}</li>
      </ul>
    </div>
    <div class=" con2">
    	<div class="btn4" onclick="wxUserHistory('${wxAccount.v.ACCOUNTID}')">绑定轨迹</div>
        <div class="btn5"  onclick="deleteAllAccount('${wxAccount.v.ACCOUNTID}')">解绑</div>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
  </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/sj_js.js"></script>
<script type="text/javascript">
  function wxUserHistory(accountId){
    window.location.href="bind-history.jhtml?accountId="+accountId;
  }

  function deleteAllAccount(accountId){



  }
</script>
</html>
