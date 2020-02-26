<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  errorPage="../../error/error.jsp"%>
<%@ include file="../../../include/public_server.jsp"%>
<%
  request.setAttribute("EASYUI_DATAGRID_TABLE", "car_list");
%>
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
<body>
<div class="easyui-panel" data-options="fit:true,border:false" style="padding: 10px;padding-bottom: 0px;">
  <div>
    <my:hasRight index="5" objId="30301" objType="30301">
      <div style="float: left;">
        <button class="layui-btn"  onclick="${EASYUI_DATAGRID_TABLE}_addeditRow('')">新增</button>
      </div>
    </my:hasRight>
    <div style="float: right;">
      <form id="${EASYUI_DATAGRID_TABLE }_table_form" action="">
        <table style="min-width: 870px;">
          <tr>
            <td style="padding-right: 3px;">开始录用时间</td>
            <td><input class="layui-input" id="${EASYUI_DATAGRID_TABLE }_startTime" name="STARTADOPTTIME" style="width: 110px;"></td>
            <td style="padding: 0px 3px;">结束录用时间</td>
            <td><input class="layui-input" id="${EASYUI_DATAGRID_TABLE }_endTime" name="ENDADOPTTIME" style="width: 110px;">
            </td>
            <td style="padding: 0px 3px;">关键字</td>
            <td><input class="layui-input" id="${EASYUI_DATAGRID_TABLE}_searchValue" name="SEARCHVALUE" placeholder="标题/单位" style="width: 220px;"></td>
            <td style="padding-left: 3px;">
              <div class="layui-btn-group">
									<span class="layui-btn" onclick="(function(){
                                            var table_form = $('#${EASYUI_DATAGRID_TABLE }_table_form');
                                            layui.table.reload('${EASYUI_DATAGRID_TABLE }_table', {
                                            where: $(table_form).serializeJSON()
                                            });
                                            })()"> <i class="glyphicon glyphicon-search"></i>查询 </span> <span class="layui-btn" onclick="(function(){
                      var table_form = $('#${EASYUI_DATAGRID_TABLE }_table_form');
                      $(table_form)[0].reset();
                      layui.table.reload('${EASYUI_DATAGRID_TABLE }_table', {
                      where: $(table_form).serializeJSON()
                      });
                      })()"> <i class="glyphicon glyphicon-refresh"></i>清除 </span>
              </div></td>
          </tr>
        </table>
      </form>
    </div>
    <div style="clear: both;"></div>
  </div>
  <table class="layui-table" lay-data="{url:'../articlescore/list_json.xhtml?articleId=${articleId }', id:'${EASYUI_DATAGRID_TABLE }_table',height: 'full-60',request: {limitName: 'rows'},response: {dataName:'rows'}}" lay-filter="${EASYUI_DATAGRID_TABLE }_table">
    <thead>
    <tr>
      <th lay-data="{type:'numbers',fixed:'left'}">序号</th>
      <my:hasRight index="5" objId="30301" objType="30301">
        <th lay-data="{width:80 ,fixed:'left',align:'center', toolbar: '#rowBt'}">操作</th>
      </my:hasRight>
      <th lay-data="{field:'title', width:220, fixed:'left',templet: '#contactNameTpl'}">稿件标题</th>
      <th lay-data="{field:'kxName',width:160}">所选刊型</th>
      <th lay-data="{field:'score',width:80}">分值</th>
      <th lay-data="{field:'groupName', width:240}">所属单位</th>
      <th lay-data="{field:'adoptTime',width:170}">录用时间</th>
      <th lay-data="{field:'crTime',width:170}">创建时间</th>
    </tr>
    </thead>
  </table>
</div>
<script type="text/javascript">
  layui.table.init('${EASYUI_DATAGRID_TABLE }_table', {
    page : {
      first : '首页',
      last : '尾页',
      prev : '上一页',
      next : '下一页',
      limit : 20,
      layout : [ 'count', 'prev', 'page', 'next', 'skip' ],
    }

  });
</script>
</body>
<script type="text/html" id="contactNameTpl">
  <div class="layui-table-link" onclick="${EASYUI_DATAGRID_TABLE}_addeditRow('{{d.scoreId}}');" style="cursor: pointer;">{{d.title}}</div>
</script>
<script type="text/html" id="rowBt">
  {{#  if(d.kxParentId != 435){ }}
  <div class="layui-btn layui-btn-danger layui-btn-xs" onclick="${EASYUI_DATAGRID_TABLE}_deleteRow('{{d.scoreId}}');">删除</div>
  {{#  } }}
</script>
<script type="text/javascript">
  layui.form.render();
  layui.laydate.render({
    elem : '#${EASYUI_DATAGRID_TABLE }_startTime'
  });
  layui.laydate.render({
    elem : '#${EASYUI_DATAGRID_TABLE }_endTime'
  });

  function ${EASYUI_DATAGRID_TABLE}_addeditRow(id){
    var url = "../articlescore/addedit.xhtml?articleId=${articleId}&id="+id;
    layer.open({
      type: 2,
      title: '新增/修改分数',
      shade: 0.8,
      area: ['800px', '550px'],
      content: url
    });
  }
  function ${EASYUI_DATAGRID_TABLE}_deleteRow(id){
    layer.confirm("您确定要删除该分数吗？", function(index) {
      $.ajax({
        url : "../articlescore/delete.xhtml",
        dataType : "json",
        data : {
          "scoreId" : id
        },
        success : function(_data) {
          if (_data.result) {
            parent.layer.msg("删除分数记录成功！");
            layui.table.reload('${EASYUI_DATAGRID_TABLE }_table');
          } else {
            parent.layer.msg(_data.msg);
          }
          layer.close(index);
        },
        error : function() {
          parent.layer.msg("请求服务异常，请稍后再试!");
          layer.close(index);
        }
      });
    });
  }
</script>
</html>
        