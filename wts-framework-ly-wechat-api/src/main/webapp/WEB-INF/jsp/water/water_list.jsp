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
    <div class="nav_tit">
      <h2>水费查询</h2>
      <span class="return"></span></div>
  </div>
  <div class="bg">
    <div class="ss_con">
    	<div class="ss_tit"><input name="" type="text" placeholder="用户号：" class="inp"><input name="" type="button" class="btn" value="" onClick="sx_con()"><div class="clear"></div></div>
    </div>
  </div>
  <div class="">
  	<div class="tit"><span><a href="${pageContext.request.contextPath}/u/account/list.jhtml">更多用户号>></a></span>水费信息</div>
    <div class="bg1">您是三类计划用户，该计划用户号内包含用户号：16048685   16048687   16048689</div>
    <div class="list">本数据截止日期：2019年02月28日</div>
  </div>
  <div class="bg">
    <div class="con3" style="display:block">
      <ul>
        <li><span>月份</span>2019.2</li>
        <li><span>用水量（吨）</span>2120</li>
        <li><span>应缴水费</span>4664</li>
        <li><span>其中超计划</span>4664</li>
        <li><span>其中违约金</span>0</li>
        <li><span>代收垃圾费</span>0</li>
        <li><span>交费情况</span>已交</li>
        <li><span>所属地区</span>厦门</li>
        <li><span>抄码明细</span><a href="#">明细</a></li>
        <li><span>本期应缴金额</span>4664</li>
      </ul>
    </div>
  </div>
  <div class="bg mar_t">
    <div class="con3" style="display:block">
      <ul>
        <li><span>月份</span>2019.2</li>
        <li><span>用水量（吨）</span>2120</li>
        <li><span>应缴水费</span>4664</li>
        <li><span>其中超计划</span>4664</li>
        <li><span>其中违约金</span>0</li>
        <li><span>代收垃圾费</span>0</li>
        <li><span>交费情况</span>已交</li>
        <li><span>所属地区</span>厦门</li>
        <li><span>抄码明细</span><a href="#">明细</a></li>
        <li><span>本期应缴金额</span>4664</li>
      </ul>
    </div>
  </div>
  <div class="bg mar_t">
  	<div class="list1">
        	<ul>
            	<li><a href="#"><img src="${pageContext.request.contextPath}/images/sfcx_07.png"></a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/images/sfcx_09.png"></a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/images/sfcx_11.png"></a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/images/sfcx_16.png"></a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/images/sfcx_17.png"></a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/images/sfcx_18.png"></a></li>
            </ul>
            <div class="clear"></div>
        </div>
  </div>
</div>


</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/sj_js.js"></script>
</html>
