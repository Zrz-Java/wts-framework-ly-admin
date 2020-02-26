<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="public_server.jsp"%>



    <!-- 侧边菜单 -->
    <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="">
                    <span>{{ layui.setter.name || 'layuiAdmin' }}</span>
                </div>
            <ul class="layui-nav layui-nav-tree" id="yultab">
                <li class="active"><a href="index.xhtml"><i class="fa fa-home"></i> <span>首页</span></a></li>
                <we:actions var="action" parentId="0">
                    <li <c:if test="${action.isleaf == 1}" >   id="t${action.acId}" class="layui-nav-item" onclick="navActive(${action.acId})"</c:if>><a <c:if test="${action.isleaf == 1}">href="javaScript:void(0)"</c:if><c:if test="${action.isleaf != 1}">href="javaScript:void(0);" onclick="freshen('${action.contentlistpage}')"</c:if>><i class="${action.iconCls}"></i> <span>${action.acName }</span></a>
                        <c:if test="${action.isleaf == 1}">
                            <ul class="sub-menu-list">
                                <we:actions var="subaction" parentId="${action.acId}">
                                    <li><a href="javaScript:void(0);" onclick="freshen('${subaction.contentlistpage}')">${subaction.acName}</a></li>
                                </we:actions>
                            </ul>
                        </c:if>
                    </li>
                </we:actions>
            </ul>
        </div>
    </div>






<script type="text/javascript">
function navActive(acId){
    $("#yultab li").removeClass('active');
    $("#t"+acId).addClass('active');
	}

function freshen(url){
    $.ajax({
        url : url,
        type : 'post',
        dataType : 'html',
        success : function(html) {
            $('.wrapper').html(html);
            },
        error:  function(data) {
        layer.msg('数据请求异常！',{time:10000,btn: ['关闭']});
             }
    })
}
</script>