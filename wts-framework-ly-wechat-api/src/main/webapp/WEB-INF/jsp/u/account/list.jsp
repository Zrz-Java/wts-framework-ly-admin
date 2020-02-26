<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="../include/public_server.jsp"%>
<%request.setAttribute("TITLE","用户号管理");%>
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
  <style type="text/css">
    .form_tit1_r {float: right;margin-top: 6px;}
    .form_tit1_r [type="checkbox"] { display: none; }
    .form_tit1_r label { display: inline-block; width:48px; height: 24px; line-height: 24px; border-radius: 12px; background-color: #b6c8dd; overflow: hidden; cursor: pointer; position: relative;}
    .form_tit1_r label:after { content: ''; position: absolute; right: 1px; top: 1px; border: 1px solid #e4e4e4; background-color: #ffffff; width: 20px; height: 20px; border-radius: 50%; text-align: center; transition: left .1s ease-out; }
    .form_tit1_r label:before{ content:'否'; position:absolute; left:0px; width:24px; height:24px; text-align:center; top:0; line-height:24px; color:#ffffff}
    .form_tit1_r [type="checkbox"]:checked + label { background-color: #1073e6; transition: background-color .1s ease-in; position:relative }
    .form_tit1_r [type="checkbox"]:checked + label:after { left:1px; transition: left .1s ease-in; border: 1px solid #1073e6; background-color: #fff; }
    .form_tit1_r [type="checkbox"]:checked + label:before { content:'是'; position:absolute; left:20px; width:24px; height:24px; text-align:center; top:0; line-height:24px; color:#ffffff}
  </style>
</head>


<body>
  <%@ include file= "../include/public_header.jsp" %>
  <div class="btn3" onclick="bindUser()"><span><img src="${pageContext.request.contextPath}/images/tj_03.png"></span>添加绑定</div>
  <div class="bg mar_t">
    <c:forEach var="wxAccount" items="${datas}">
    <div class="con3" style="display:block" id="account_list">
      <ul>
        <li><span>用户号：</span>${wxAccount.v.ACCOUNT}</li>
        <li><span>业主姓名：</span>${wxAccount.v.ACCOUNTID}</li>
        <li><span>手机号：</span>${wxAccount.v.MOBILE}</li>
        <li><span>地址 : </span>${wxAccount.v.ADDRESS == ''?"无":wxAccount.v.ADDRESS }</li>
        <li><span>默认用户号 : </span><div class="form_tit1_r"><input type="checkbox" name="cbx${wxAccount.v.USERBINDID}" id="cbx${wxAccount.v.USERBINDID}" <c:if test="${wxAccount.v.ISMAIN == 1}"> checked </c:if> ><label for="cbx${wxAccount.v.USERBINDID}"></label></div><div class="clear"></div></li>
      </ul>
    </div>
    <div class=" con2">
    	<div class="btn4">查看账单</div>
    	<div class="btn6" onclick="wxUserHistory('${wxAccount.v.ACCOUNTID}')">绑定轨迹</div>
      <div class="btn5" onclick="deleteAccount('${wxAccount.v.ACCOUNTID}')">删除</div>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
    </c:forEach>
  </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/sj_js.js"></script>
<script type="text/javascript">
  function bindUser(){
    window.location.href="bind.jhtml";

  }
  function wxUserHistory(accountId){
    window.location.href="../user/bind-history.jhtml?accountId="+accountId;
  }

  function deleteAccount(accountId){



  }

</script>

</html>
