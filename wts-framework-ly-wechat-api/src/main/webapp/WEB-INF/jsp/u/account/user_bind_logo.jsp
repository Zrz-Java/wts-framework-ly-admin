<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="../include/public_server.jsp"%>
<%request.setAttribute("TITLE","用户号绑定");%>
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
<div class="logo"><img src="../../images/19xmsw_wx_03.png"></div>
  <div class="bg  mar_t">
    <form action="" id="myform" method="post">
    <div class=" list2">
      <input type="hidden" name="userId" id="userId" class="inp1" value="${userId}">
      <input type="hidden" name="openId" id="openId" class="inp1" value="${openId}">
      <ul>
        <li><span>用户号*：</span>
          <div><input type="text" name="accountId" id="accountId" class="inp1"></div>
        </li>
        <li><span>手机号*：</span>
          <div><input type="text" name="mobile" id="mobile" class="inp1"></div>
        </li>
        <li><span>手机验证码*：</span>
          <div><input type="text" name="vcode" id="vcode" class="inp2">
            <input type="button" value="发送短信验证码" class="but" id="yzm"></div>
        </li>
        <li><span>验证码*：</span>
          <div><input type="text" name="mcode" id="mcode" class="inp2">
            <img id="mvcode" src="${ctx }/valid/code.jhtml?t=<%=System.currentTimeMillis() %>" onclick="(function(__this){$(__this).attr('src','${ctx }/valid/code.jhtml?t=' + (new Date().getTime()));})(this)"></div></li>
      </ul>
    </div>
    <div class=" btn2" onclick="submitBind()"><a href="#">用户号绑定</a></div>
    </form>
    <div class="txt"><c:if test="${wxUser.v.WXUSERBINDNUM != 0}">已绑定${wxUser.v.WXUSERBINDNUM}个</c:if><c:if test="${wxUser.v.WXUSERBINDNUM == 0}">您未绑定用户号</c:if>，还可以绑定${100-wxUser.v.WXUSERBINDNUM}个。</div>
  </div>
  <div class="mar_t bg1">备注： 短信验证码由绑定用户自行与被绑定的户主电话沟通获取。</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/sj_js.js"></script>
<script type="text/javascript">
  $("#yzm").click(function() {
    if($("#yzm").attr("disabled") == 'disabled'){
      return false;
    }
    var mobile = $('#mobile').val();
    var accountId = $('#accountId').val();
    if(accountId == '') {
      layer.open({
        title: [
          '错误',
          'background-color: #FF4351; color:#fff;'
        ]
        , content: '输入的用户号为空！'
      });
      return false;
    }

      var mobile = $('#mobile').val();
      var ret = /^1[3|4|5|6|7|8|9]\d{9}$/;
      if(!ret.test(mobile)){
          layer.open({
              title: [
                  '错误',
                  'background-color: #FF4351; color:#fff;'
              ]
              ,content: '你的手机号不符合要求'
          });
          return false;
      }
    if(mobile == ''){
      layer.open({
        title: [
          '错误',
          'background-color: #FF4351; color:#fff;'
        ]
        ,content: '输入的手机号码为空！'
      });

      return false;
    }



    $("#yzm").attr("disabled", true);
    $.ajax({
      dataType : 'json',
      url:'../sendMsg.xhtml',
      type : 'post',
      data : {
        'mobile' : mobile,
      },
      error : function(XMLHttpRequest, textStatus, errorThrown) {
        layer.open({
          title: [
            '错误',
            'background-color: #FF4351; color:#fff;'
          ]
          ,content: '网络异常！'
        });
      },
      success : function(data, textStatus, jqXHR) {
        if (data.result == true) {
          var timeCount = 60;
          var clearIntervalItem = setInterval(function() {
            timeCount--;
            $("#yzm").val(timeCount + "秒后重新发送");
            if (timeCount <= 0) {
              $("#yzm").attr("disabled", false);
              $("#yzm").val("发送验证码");
              clearInterval(clearIntervalItem);
            }
          }, 1000);
        }else{
          layer.open({
            title: [
              '错误',
              'background-color: #FF4351; color:#fff;'
            ]
            ,content: data.msg
          });
        }
      }
    });

    return false
  });




  function submitBind(){
    var mobile = $('#mobile').val();
    var ret = /^1[3|4|5|6|7|8|9]\d{9}$/;
    if(!ret.test(mobile)){
        layer.open({
            title: [
                '错误',
                'background-color: #FF4351; color:#fff;'
            ]
            ,content: '你的手机号不符合要求'
        });
      return false;
    }

    $.ajax({
      dataType : 'json',
      url:'../account/bind-dowith.jhtml',
      type : 'post',
      data : $('#myform').serialize(),
      error : function(XMLHttpRequest, textStatus, errorThrown) {
        layer.open({
          title: [
            '错误',
            'background-color: #FF4351; color:#fff;'
          ]
          ,content: '网络异常！'
        });
      },
      success : function(data, textStatus, jqXHR) {
        if (data.result == true) {
          layer.open({
            content: '绑定成功！'
            ,btn: ['确定']
            ,yes: function(index){
              window.location.href = 'list.jhtml'
            }
          });
        }else{
            layer.open({
              title: [
                '错误',
                'background-color: #FF4351; color:#fff;'
              ]
              ,content: data.msg
            });

        }
      }
    });
    return false;
  }



</script>

</html>
