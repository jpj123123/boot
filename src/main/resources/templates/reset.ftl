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
            $('#resetForm').form('clear');
        }
        function resetSubmit(){
            if($("input[name='userName']").val()=="" || $("input[name='newPassword']").val()==""|| $("input[name='oldPassword']").val()==""){
                $("#showMsg").html("用户名或密码为空，请输入");
                $("input[name='login']").focus();
            }else{
                $("#resetForm").submit();
            }
        }
    </script>
</HEAD>
<BODY>
<div id="login-window" class="easyui-window" title="重置密码" style="padding:5px;width:385px;height:205px" >
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
            <form  id="resetForm" method="post" action="/user/resetSubmit">
                <div style="padding:5px 0;">
                    <label for="login">登录名:</label>
                    <input type="text" value="${dto.userName!""}" name="userName" style="width:260px;"></input>
                </div>
                <div style="padding:5px 0;">
                    <label for="password">原密码:</label>
                    <input type="password" value="${dto.oldPassword!""}" name="oldPassword" style="width:260px;"></input>
                </div>
                <div style="padding:5px 0;">
                    <label for="password">新密码:</label>
                    <input type="password" value="${dto.newPassword!""}" name="newPassword" style="width:260px;"></input>
                </div>
                <div style="padding:5px 0;text-align: center;color: red;" id="showMsg">${dto.errorMsg!""}</div>
            </form>
        </div>
        <div region="south" border="false" style="text-align:right;padding:5px 0;">
            <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="resetSubmit()">重置</a>
            <a class="easyui-linkbutton" iconCls="icon-cancel" href="/login">去登陆</a>
        </div>
    </div>
</div>
</BODY>
</HTML>