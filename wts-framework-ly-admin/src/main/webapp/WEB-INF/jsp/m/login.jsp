<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/public_server.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">
    <title>手机服务管理系统</title>
    <%@ include file="../include/public_common_import.jsp"%>
    <script type="text/javascript">
    function loginInfo(){
    	var userName = $('#userName').val();
    	var passWord = $('#passWord').val();
    	var vCode = $('#vCode').val();
    	if(userName == ''){
    		 layer.msg('请输入用户名或者手机号！',{time:10000,btn: ['关闭']});
    	}
    	if(passWord == ''){
    			layer.msg('请输入密码！',{time:10000,btn: ['关闭']});
    	}
    	if(vCode == ''){
    		layer.msg('请输入验证码！',{time:10000,btn: ['关闭']});
    	}
   		passWord = encodeURI(encodeURI(passWord));
       $.ajax({
	       　　	 url : '${ctx}/m/login_dowith.xhtml',
	      　　	 type : 'post',
	       　　 	 dataType : 'json',
	       	 data:{userName:userName,passWord:passWord,vCode:vCode},
	      　　 	 success : function(datas) {
	          　　   	 if(datas.result){
	          　　    			window.location.href='${ctx}/m/admin/index/index.xhtml';
	         } else{
	            	$("#validateCode").attr("src", "${ctx }/admin_validate-code.xhtml?t=" +(new Date().getTime()));
					 layer.alert(datas.msg,{time:10000,btn: ['关闭']});
	         }
	        },
	        error: function(data) {
	           layer.msg('数据请求异常！',{time:10000,btn: ['关闭']});
	        }
        })
     }
    </script>
</head>
<body class="login-body">
<div class="container">
    <form class="form-signin" method="post">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">登录</h1>
            <img src="../images/logo.png" alt="logo"/>
        </div>
        <div class="login-wrap">
            <input type="text" id="userName" name="userName" class="form-control" placeholder="用户名/手机号" autofocus>
            <input type="password" id="passWord" name="passWord" class="form-control" placeholder="密码">
            <span><input type="text" name="vCode" id="vCode" class="form-control" placeholder="验证码"  maxlength="4" style="width:100px"/>
			<img title="点击更换验证码" src="${ctx }/admin_validate-code.xhtml" width="90" height="35" id="validateCode" style="cursor: pointer;margin-top:-95px;margin-left:105px" onclick="(function(){
       						$('#validateCode').attr('src', '<%=request.getContextPath() %>/admin_validate-code.xhtml?t=' + (new Date().getTime()));
       					})()"></span>
       		<button class="btn btn-lg btn-login btn-block" type="button" style="margin-top:-25px;" onclick="loginInfo()">登录</button>

        </div>
        <!-- modal -->
    </form>
</div>
</body>
</html>
