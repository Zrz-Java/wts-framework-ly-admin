<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../include/public_server.jsp"%>
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
  <%@ include file="../../../include/public_common_import.jsp"%>

<body style="background-color: #ffffff">
<form class="layui-form layui-form-pane1" id="myform" lay-filter="first" style="margin:10px 50px 10px 10px">
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-block">
      <input type="hidden" id="userId" name="userId" value="${user.userId}">
      <input type="text" id="username" name="username" lay-verify="required" required placeholder="请输入用户名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-block">
      <input type="text" id="name" name="name" lay-verify="required" required placeholder="请输入姓名" autocomplete="off" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item" pane>
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input type="radio" name="sex" value="男" title="男" lay-filter="erweima" >
      <input type="radio" name="sex" value="女" title="女" lay-filter="erweima" checked>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">身份证</label>
    <div class="layui-input-block">
      <input type="text" id="idcard" name="idcard" lay-verify="required" required placeholder="请输入身份证" autocomplete="off" class="layui-input">
    </div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-block">
      <input type="password" id="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">手机</label>
    <div class="layui-input-block">
      <input type="text" id="mobile" name="mobile" lay-verify="number" lay-verType="tips" placeholder="请输入手机号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">邮箱</label>
    <div class="layui-input-block">
      <input type="text" id="email" name="email" lay-verify="email"  lay-verType="alert" autocomplete="off" placeholder="请输入邮箱" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">联系地址</label>
    <div class="layui-input-block">
      <input type="text" id="address" name="address" lay-verify="required"  lay-verType="alert" autocomplete="off" placeholder="请输入联系地址" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="submitUser">提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
<script type="text/javascript">
layui.use('form', function(){
	  var form = layui.form;
	 form.on('radio(erweima)', function(data){
	 
	  });
	   form.render();
	   
//监听提交
  form.on('submit(submitUser)', function(data){
    var option = {
       　　	 url : '../user/addedit_dowith.xhtml',
      　　	 type : 'post',
       　　 	 dataType : 'json',
      　　 	 success : function(datas) {
          　　   	 if(datas.result){
          		layer.msg(datas.msg,{time:10000,btn: ['关闭'],yes: function(index, layero){
	           		location.reload();
	     		}});
         } else{
				 layer.alert(datas.msg);
         }
        },
        error: function(data) {
           layer.msg('数据请求异常！',{time:10000,btn: ['关闭']});
        }
     };
   $("#myform").ajaxSubmit(option);
    return false;
  });
  });
</script>
</body>
</html>        
        