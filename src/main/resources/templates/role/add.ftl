<script type="text/javascript">
    function roleAddSubmit() {
        $('#addEnumForm').form('submit', {
            success: function (data) {
                role_datagrid_reloadFun();
                $('#role-add-layout').parent().window('close')
            }
        });
    }
</script>
<div id="role-add-layout" class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="addEnumForm" method="post" action="/role/addRoleSubmit">
            <input name="pid" type="hidden" value="${pid}"/>
    <#if pid gt 0>

    <div>
        <label>上级角色名:</label>
        ${roleName}
    </div>
    </#if>

            <div>
                <label for="name">菜单名:</label>
                <input class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
            </div>
        </form>
    </div>
    <div region="south" border="false" style="text-align:right;padding:5px 0;">
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="roleAddSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#role-add-layout').parent().window('close')">关闭</a>
    </div>
</div>