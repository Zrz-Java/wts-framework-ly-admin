<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="trt_menu">
  <ul class="trt_twhite1">
      <li><a href="http://www.ixm.gov.cn/">首页</a></li>
        <li><a href="${ctx }/u/">我的主页<span></span></a></li>
        <li><a href="${ctx }/u/business/index.jhtml">我的办事<span></span></a></li>
        <li><a href="${ctx }/u/tradeunions/index.jhtml">我的工会<span></span></a></li>
        <!-- <li><a href="${ctx }/u/message/index.jhtml">我的消息<span></span></a></li> -->
    </ul>
  	<div class="trt_ss" style="width: 320px;">
  		<input name="" type="text" class="trt_inp1" id="j_search">
  		<img src="http://www.ixm.gov.cn/images/trt_fwt_btn1.jpg" id="j_searchBtn">
  	</div>
    <div class="trt_clear"></div>
    <div class="trt_jsk1_con1" id="j_searchResult" style="display: none">
    	<div class="trt_jsk1_con2" id="j_srItems"></div>
    	<div class="trt_jsk1_con2_b"></div>
    </div>
    <div class="trt_clear"></div>
    <script type="text/javascript" src="http://www.ixm.gov.cn/jsxm/searchcomm.js" related="1"></script>
    <script type="text/javascript" src="http://www.ixm.gov.cn/jsxm/littlesearch.js" related="1"></script>
</div>