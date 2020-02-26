<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../include/public_server.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="keywords" content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link rel="shortcut icon" href="#" type="image/png">
  <title>${title}</title>
 <%@ include file="../../include/public_common_import.jsp"%>
  <script type="text/javascript">
 $(function(){
	  	$('#ultab li').removeClass("open");
	 	$('#index').addClass("open");
 })
 </script>
</head>
<body class="layui-layout-body">
<%@ include file="../include/public_index_top_manage.jsp"%>
    <!-- left side start-->
  <%@ include file="../include/public_index_left_manage.jsp"%>
            <div class="layui-fluid">
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-sm6 layui-col-md3">
                        <div class="layui-card">
                            <div class="layui-card-header">
                                访问量
                                <span class="layui-badge layui-bg-blue layuiadmin-badge">周</span>
                            </div>
                            <div class="layui-card-body layuiadmin-card-list">
                                <p class="layuiadmin-big-font">9,999,666</p>
                                <p>
                                    总计访问量
                                    <span class="layuiadmin-span-color">88万 <i class="layui-inline layui-icon layui-icon-flag"></i></span>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-sm6 layui-col-md3">
                        <div class="layui-card">
                            <div class="layui-card-header">
                                下载
                                <span class="layui-badge layui-bg-cyan layuiadmin-badge">月</span>
                            </div>
                            <div class="layui-card-body layuiadmin-card-list">
                                <p class="layuiadmin-big-font">33,555</p>
                                <p>
                                    新下载
                                    <span class="layuiadmin-span-color">10% <i class="layui-inline layui-icon layui-icon-face-smile-b"></i></span>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-sm6 layui-col-md3">
                        <div class="layui-card">
                            <div class="layui-card-header">
                                收入
                                <span class="layui-badge layui-bg-green layuiadmin-badge">年</span>
                            </div>
                            <div class="layui-card-body layuiadmin-card-list">

                                <p class="layuiadmin-big-font">999,666</p>
                                <p>
                                    总收入
                                    <span class="layuiadmin-span-color">*** <i class="layui-inline layui-icon layui-icon-dollar"></i></span>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-sm6 layui-col-md3">
                        <div class="layui-card">
                            <div class="layui-card-header">
                                活跃用户
                                <span class="layui-badge layui-bg-orange layuiadmin-badge">月</span>
                            </div>
                            <div class="layui-card-body layuiadmin-card-list">

                                <p class="layuiadmin-big-font">66,666</p>
                                <p>
                                    最近一个月
                                    <span class="layuiadmin-span-color">15% <i class="layui-inline layui-icon layui-icon-user"></i></span>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-sm12">
                        <div class="layui-card">
                            <div class="layui-card-header">
                                访问量
                                <div class="layui-btn-group layuiadmin-btn-group">
                                    <a href="javascript:;" class="layui-btn layui-btn-primary layui-btn-xs">去年</a>
                                    <a href="javascript:;" class="layui-btn layui-btn-primary layui-btn-xs">今年</a>
                                </div>
                            </div>
                            <div class="layui-card-body">
                                <div class="layui-row">
                                    <div class="layui-col-sm8">
                                        <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade" lay-filter="LAY-index-pagetwo" lay-anim="fade" style="width: 100%; height: 280px;">
                                            <div carousel-item="" id="LAY-index-pagetwo">
                                                <div class="layui-this" _echarts_instance_="1551360335389" style="-webkit-tap-highlight-color: transparent; user-select: none; background-color: rgba(0, 0, 0, 0); cursor: default;"><div style="position: relative; overflow: hidden; width: 685px; height: 332px;"><div data-zr-dom-id="bg" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 685px; height: 332px; user-select: none;"></div><canvas width="685" height="332" data-zr-dom-id="0" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 685px; height: 332px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas><canvas width="685" height="332" data-zr-dom-id="1" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 685px; height: 332px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas><canvas width="685" height="332" data-zr-dom-id="_zrender_hover_" class="zr-element" style="position: absolute; left: 0px; top: 0px; width: 685px; height: 332px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas><div class="echarts-tooltip zr-element" style="position: absolute; display: none; border-style: solid; white-space: nowrap; transition: left 0.4s, top 0.4s; background-color: rgba(50, 50, 50, 0.5); border-width: 0px; border-color: rgb(51, 51, 51); border-radius: 4px; color: rgb(255, 255, 255); font-family: 微软雅黑, Arial, Verdana, sans-serif; padding: 5px; left: 200px; top: 96px;">3月<br>访问量 : 950<br>下载量 : 800<br>平均访问量 : 850</div></div></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-sm4">
                                        <div class="layuiadmin-card-list">
                                            <p class="layuiadmin-normal-font">月访问数</p>
                                            <span>同上期增长</span>
                                            <div class="layui-progress layui-progress-big" lay-showpercent="yes">
                                                <div class="layui-progress-bar" lay-percent="30%" style="width: 30%;"><span class="layui-progress-text">30%</span></div>
                                            </div>
                                        </div>
                                        <div class="layuiadmin-card-list">
                                            <p class="layuiadmin-normal-font">月下载数</p>
                                            <span>同上期增长</span>
                                            <div class="layui-progress layui-progress-big" lay-showpercent="yes">
                                                <div class="layui-progress-bar" lay-percent="20%" style="width: 20%;"><span class="layui-progress-text">20%</span></div>
                                            </div>
                                        </div>
                                        <div class="layuiadmin-card-list">
                                            <p class="layuiadmin-normal-font">月收入</p>
                                            <span>同上期增长</span>
                                            <div class="layui-progress layui-progress-big" lay-showpercent="yes">
                                                <div class="layui-progress-bar" lay-percent="25%" style="width: 25%;"><span class="layui-progress-text">25%</span></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

    </div>
    </div>
    </div>
</body>
</html>
