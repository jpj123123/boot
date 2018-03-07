<script type="text/javascript">
    $(function() {
        $('#role_datagrid_id').datagrid({
            url:'/role/listAll',
            singleSelect:true,
            columns: [[
                {title: 'id', field: 'id', width: 50},
                {title: 'pid', field: 'pid', width: 50},
                {title: '角色名', field: 'name', width: 180}
            ]],
            loadFilter:function(data){
                console.log(data);
                return data.body;
            },
            onBeforeExpand: function (row) {
                return true;
            }
        })
    });
    function addUserRoleSubmit() {
        var rol = $('#role_datagrid_id').datagrid('getSelected');
        if(rol == null){
            $.messager.show({
                title:'提示',
                msg:'无选中数据！',
                timeout:1000,
                showType:'slide'
            });
            return false;
        }
        if(confirm('确定添加角色吗？')){
            $.ajax({
                url: "/user/addUserRoleSubmit",
                type:"post",
                data:{"userId":${user.id},"roleId":rol.id},
                success:function(res){
                    if(res.body == true){
                        $('#add-user-role-layout').parent().window('close');
                        user_datagrid_reloadFun();
                    }
                }
            });
        }
    }
</script>
<div id="add-user-role-layout" class="easyui-layout" fit="true">
    <div region="north" border="false" style="padding:5px;">
        <div>
            <label>用户id:</label>
        ${user.id}
        </div>
        <div>
            <label>用户名:</label>
        ${user.name}
        </div>
        <div>
            <label>已关联角色名:</label>
        ${rolename!"暂无关联"}
        </div>
    </div>
    <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <table id="role_datagrid_id" style="width:100%;height:100%">
        </table>
    </div>
    <div region="south" border="false" style="text-align:right;padding:5px 0;">
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="addUserRoleSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#add-user-role-layout').parent().window('close')">关闭</a>
    </div>
</div>