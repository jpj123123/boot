<script type="text/javascript">
    function userEditSubmit() {
        $('#addUserForm').form('submit', {
            success: function (data) {
                user_datagrid_reloadFun();
                $('#user-add-layout').parent().window('close')
            }
        });
    }
</script>
<div id="user-add-layout" class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="addUserForm" method="post" action="/user/editUserSubmit">
            <input type="hidden" name="id" value="${user.id}">
            <div>
                <label for="pid">真实姓名:</label>
                ${user.realName!"暂未填写"}
            </div>
            <div>
                <label for="pid">联系方式:</label>
                ${user.phone!"暂未填写"}
            </div>
            <div>
                <label for="pid">上级Id:</label>
                <input class="easyui-validatebox" type="text" name="pid" value="${user.pid}" data-options="required:true"/>
            </div>

            <div>
                <label for="name">用户名:</label>
                <input class="easyui-validatebox" type="text" name="name" value="${user.name}" data-options="required:true"/>
            </div>
            <div>
                <label for="password">登录密码:</label>
                <input class="easyui-validatebox" type="password" name="password" value="${user.password}" data-options="required:true"/>
            </div>
            <div>
                <label for="issale">是业务员:</label>
                <input class="easyui-validatebox" type="checkbox" name="issale" <#if user.issale>checked</#if>/>
            </div>
        </form>
    </div>
    <div region="south" border="false" style="text-align:right;padding:5px 0;">
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="userEditSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#user-add-layout').parent().window('close')">关闭</a>
    </div>
</div>