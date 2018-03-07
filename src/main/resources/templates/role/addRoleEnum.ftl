<script type="text/javascript">
    $(function(){
        $("#add_role_enum_tree").tree({
            url:"/enum/listEnumDtoAll?roleId=${pid}",
            checkbox:true,
            loadFilter: function(data){
                if (data.body){
                    return data.body;
                } else {
                    return data;
                }
            }
        });
        $("#add_role_enum_tree_old").tree({
            url:"/enum/listEnumDtoAll?roleId=${id}",
            loadFilter: function(data){
                if (data.body){
                    return data.body;
                } else {
                    return data;
                }
            }
        });
    });
    function addRoleEnumSubmit() {
        var nodes = $("#add_role_enum_tree").tree('getChecked', ['checked','indeterminate']);
        if(nodes == null){
            $.messager.show({
                title:'提示',
                msg:'无选中数据！',
                timeout:1000,
                showType:'slide'
            });
            return false;
        }
        var data = new Array();
        $.each( nodes, function(i, n){
            var obj = {"id":n.attributes.id};
            data.push(n.attributes.id);
        });
        $.ajax({
            url: '/role/addRoleEnumSubmit',
            type:"post",
            data:{"roleId":${id},"enumIds":data},
            traditional: true,
            success:function(res){
                if(res.body == true){
                    $.messager.show({
                        title:'提示',
                        msg:'添加成功！',
                        timeout:1000,
                        showType:'slide'
                    });
                    $('#add-role-enum-layout').parent().window('close')
                }
            }
        });
    }
</script>
<div id="add-role-enum-layout" class="easyui-layout" fit="true">
    <div data-options="region:'east',title:'已关联菜单',split:true" style="width:50%;">
        <ul id="add_role_enum_tree_old" class="easyui-tree"></ul>
    </div>
    <div region="center" title="可关联菜单" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <ul id="add_role_enum_tree" class="easyui-tree"></ul>
    </div>
    <div region="south" border="false" style="text-align:right;padding:5px 0;">
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="addRoleEnumSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#add-role-enum-layout').parent().window('close')">关闭</a>
    </div>
</div>