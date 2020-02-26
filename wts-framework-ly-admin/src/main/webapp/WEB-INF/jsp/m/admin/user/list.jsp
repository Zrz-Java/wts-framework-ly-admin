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
<body>
<div class="layui-form layui-card-header layuiadmin-card-header-auto">
  <div class="layui-form-item">

    <div class="layui-inline">
      <label class="layui-form-label">用户名</label>
      <div class="layui-input-block">
        <input type="text" name="username" placeholder="请输入" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" style="padding-bottom:9px">
      <button class="layui-btn layuiadmin-btn-useradmin" lay-submit="" onclick="search()">
        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
      </button>
    </div>
  </div>
</div>
<table class="layui-hide" id="user" lay-filter="usertab"></table>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">添加</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
 
<script type="text/javascript">
layui.use('table', function(){
  var table = layui.table;
   table.on('checkbox(usertab)', function(obj){
   
  });
	  table.render({
	    elem: '#user',
	    height: 550,
	    url: '../user/list_json.xhtml',
	    title: '用户表',
	    toolbar: '#barDemo',
	    page: true,
	    cols: [[ 
	      {type:'checkbox',fixed: 'left'},
	      {field:'zizeng',title:'序号' ,width:60,templet:'#zizeng'},
	      {field: 'username', title: '用户名', width:100},
	      {field: 'trueName', title: '真实姓名', width:100},
	      {field: 'mobile', title: '手机号码', width:120},
	      {field: 'sex', title: '性别', width:60},
	      {field: 'address', title: '地址', width:190},
	      {field: 'status', title: '状态', width:70},
	      {field: 'updateTime', title: '更新时间', width:170},
	      {field: 'crTime', title: '创建时间', width:150}
	    ]]
	  });



	 //工具栏事件
  table.on('toolbar(usertab)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
      case 'add':
       add();
      break;
      case 'edit':
      
        layer.msg('编辑');
      break;
      case 'del':
        layer.msg('删除');
      break;
      case 'getCheckData':
        var data = checkStatus.data;
        layer.alert(JSON.stringify(data));
      break;
      case 'getCheckLength':
        var data = checkStatus.data;
        layer.msg('选中了：'+ data.length + ' 个');
      break;
      case 'isAll':
        layer.msg(checkStatus.isAll ? '全选': '未全选')
      break;
    };
  });
  
  
  });
  
  
function add(){
	layer.open({
	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['1000px', '90%'],
	  content: '../user/addedit.xhtml' //iframe的url
	}); 
}

function search(){
  var table = layui.table;
  var field = $('#username').val();
  table.reload('user', {
    where: {'keys':field}
  })
}

 
</script>
<script type="text/html" id="zizeng">
    {{d.LAY_TABLE_INDEX+1}}
</script>
</body>
</html>        
        