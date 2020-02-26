<%@page import="com.trt.framework.util.CMyString"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>403</title>
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<style type="text/css">
    html{width: 100%; height: 100%; background:#fff url(${pageContext.request.contextPath}/images/body_404.jpg) no-repeat center bottom; background-size: cover;}
    *{margin:0; padding:0; }
    img {max-width: 100%;height: auto;  width: auto\9; /* ie8 */ vertical-align:top;}
    a{-webkit-tap-highlight-color: rgba(0,0,0,0);}
    body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td{margin:0;padding:0;}
    html {-ms-touch-action: none;}
    a:hover{ color:#004386;}
    h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:normal; }i{ font-style:normal;}
    li{list-style:none}img{border:0;}a{color:#000;text-decoration:none;outline:none;}/em{ font-style:normal;}
    body{ background:#fff; font-size:16px; line-height: 30px;  font-family:Microsoft Yahei,Hiragino Sans GB,WenQuanYi Micro Hei,Heiti SC,STHeiti,SimSun,sans-serif;}
    .clearfix:after {content:".";display:block;visibility:hidden;height:0;clear:both; }/* 清除浮动*/* html .clearfix{ zoom: 1; }     
    .mod{ position: absolute; width: 100%; text-align: center; line-height: 200px;}
	.im{ position: relative;font-size: 200px;color: #56c3f1;font-weight: bold;margin-top: 200px;width: 100%;}
    .mod p{ font-size: 24px; line-height: 40px; margin:20px 0; color: #2c2e2f;}
    .mod a{ display: block; height: 62px; width: 335px; margin:0 auto; background: #56c3f1; border-radius: 5px; font-size: 36px; color: #fff; text-align: center; line-height: 62px;  }
    .mod a:hover{ opacity: 0.8; filter:alpha(opacity=80); }
    @media screen and (max-width:1024px){
        .mod{line-height: 200px;}
        .mod .im { font-size: 200px; }
        .mod p{ font-size: 18px; line-height: 32px; }
        .mod a{ width: 260px; }
    }
    @media screen and (max-width:640px){
        .mod{line-height: 100px;}
        .mod .im { font-size: 60px; }
        .mod a{ line-height: 50px; height: 50px; font-size: 28px; }
    }
</style>
</head>
<body>
    <div class="mod">
        <div class="im">ERROR</div>
        <p>${msg }</p>
    </div>
</body>
</html>