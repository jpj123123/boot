<script type="text/javascript">
    function userAddSubmit() {
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
        <form id="addUserForm" method="post" action="/user/addUserSubmit">
            <input name="pid" type="hidden" value="${pid}"/>
            <#if pid gt 0>

            <div>
                <label>上级角色名:</label>
                ${pname}
            </div>
            </#if>

            <div>
                <label for="name">用户名:</label>
                <input class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
            </div>
            <div>
                <label for="password">登录密码:</label>
                <input class="easyui-validatebox" type="password" name="password" data-options="required:true"/>
            </div>
        </form>
    </div>
    <div region="south" border="false" style="text-align:right;padding:5px 0;">
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="userAddSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#user-add-layout').parent().window('close')">关闭</a>
    </div>
</div>