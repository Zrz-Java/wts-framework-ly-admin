<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="../include/public_server.jsp"%>
<%request.setAttribute("TITLE","用户绑定轨迹");%>
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
  <div class="bg mar_t">
    <div class="tit">用户绑定信息</div>
    <div class="con3" style="display:block">
      <ul>
        <li><span>用户号：</span>${userInfo.ACCOUNT}</li>
        <li><span>业主姓名：</span>${userInfo.WXNAME}</li>
        <li><span>手机号：</span>${userInfo.MOBILE}</li>
        <li><span>第一次被绑定：</span>${openIdHashMap.firstTime}</li>
        <li><span>第一次被本微信绑定：</span>${openIdHashMap.oneBindTime}</li>
        <li><span>第一次被本微信解绑：</span>${openIdHashMap.oneUnBindTime}</li>
      </ul>
    </div>
  </div>
  <div class="bg mar_t">
    <div class="tit">用户绑定轨迹</div>
    <div class="con3" style="display:block">
      <%--<c:choose>--%>
      <%--<c:when test="${wxHistories.size != 0 }">--%>
      <c:forEach items="${wxHistories}" var="history" varStatus="status">
        <ul>
          <li><span>操作类型：</span>${history.operateTypeEnum}</li>
          <li><span>操作方式：</span>${history.operateMethodEnum}</li>
          <li><span>操作时间：</span>${history.time}</li>
          <li><span>微信昵称: </span>${history.wxName}</li>
          <li><span>操作渠道：</span>${history.platformEnum}</li>
        </ul>
      </c:forEach>
      <div class="clear"></div>
    </div>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
      <%--<h1>暂无查询结果</h1>--%>
      <%--&lt;%&ndash;<%@ include file="../../include/public_common_otherwise.jsp"%>&ndash;%&gt;--%>
    <%--</c:otherwise>--%>
    <%--</c:choose>--%>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/sj_js.js"></script>
</html>
