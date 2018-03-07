<!DOCTYPE>
<HTML>
<HEAD>
    <TITLE>欢迎登录</TITLE>
    <link rel="icon" type="image/x-icon" href="/public/favicon.ico">
    <script src="/public/easyui/jquery.min.js" type="text/javascript"></script>
    <script src="/public/easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <link href="/public/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="/public/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        $(document).ready(function(){
            $('#login-window').window({
                //可伸缩箭头
                collapsible:false,
                // 最小化
                minimizable:false,
                // 最大化
                maximizable:false,
                //调整窗口大小
                resizable:false,
                //拖拽操作
                draggable:true,
                //关闭按钮
                closable:false
            });
        });
        document.onkeydown = function(e){
            var event = e || window.event;
            var code = event.keyCode || event.which || event.charCode;
            if (code == 13) {
                login();
            }
        }
        $(function(){
            $("input[name='login']").focus();
        });
        function cleardata(){
            $('#loginForm').form('clear');
        }
        function login(){
            if($("input[name='login']").val()=="" || $("input[name='password']").val()==""){
                $("#showMsg").html("用户名或密码为空，请输入");
                $("input[name='login']").focus();
            }else{
                $("#loginForm").submit();
            }
        }
    </script>
</HEAD>
<BODY>
<div id="login-window" class="easyui-window" title="登录" style="padding:5px;width:350px;height:188px" >
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
            <form  id="loginForm" method="post" action="/user/login">
                <div style="padding:5px 0;">
                    <label for="login">帐号:</label>
                    <input type="text" value="${userName!""}" name="userName" style="width:260px;"></input>
                </div>
                <div style="padding:5px 0;">
                    <label for="password">密码:</label>
                    <input type="password" value="${password!""}" name="password" style="width:260px;"></input>
                </div>
                <div style="padding:5px 0;text-align: center;color: red;" id="showMsg">${errorMsg!""}</div>
            </form>
        </div>
        <div region="south" border="false" style="text-align:right;padding:5px 0;">
            <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="login()">登录</a>
            <#--<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="cleardata()">重置</a>-->
            <a class="easyui-linkbutton" iconCls="icon-cancel" href="/reset">重置密码</a>
        </div>
    </div>
</div>
</BODY>
</HTML>