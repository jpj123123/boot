<script type="text/javascript">
    $(function() {
        $('#enum_treegrid_id').treegrid({
            idField: 'id',
            treeField: 'name',
            url:'/enum/listEnumAll?pid=${pid}',
            columns: [[
                {title: 'id', field: 'id', width: 0},
                {title: '菜单名', field: 'name', width: 180},
                {field: 'code', title: '菜单编码', width: 160, align: 'right'},
                {field: 'type', title: '菜单类型1菜单2链接3按钮', width: 180},
                {field: 'url', title: 'url', width: 180},
                {field: 'icons', title: 'icons', width: 100},
                {field: 'desc', title: '排序', width: 80}

            ]],
            <#--//data:${enums},-->
            loadFilter:function(data,parentId){
                console.log(data);
                return data.body;
            },
            onBeforeExpand: function (row) {
                return true;
            }
        })
    });
    function enum_list_allFun(url){
        $('#enum_treegrid_id').treegrid(
                "reload"
        );
    }
    function enum_addFun(url){
        var rol = $('#enum_treegrid_id').treegrid('getSelected');
        if(rol != null && rol.type == 3){
            $.messager.alert('提示','按钮不允许再添加子节点了！','warning');
            return false;

        }
        var pid = 0;
        if(rol != null)
            pid = rol.id;

        $('#add_win').window('open');
        ///enum/addEnum
        $('#add_win').window('refresh',url+"?pid="+pid);

    }
    function enum_editFun(url){
        var rol = $('#enum_treegrid_id').treegrid('getSelected');
        if(rol == null){
            $.messager.show({
                title:'提示',
                msg:'无选中数据！',
                timeout:1000,
                showType:'slide'
            });
            return false;
        }
        var id = rol.id;

        $('#add_win').window('open');
        ///enum/addEnum
        $('#add_win').window('refresh',url+"?id="+id);

    }
    function enum_delFun(url){
        win_closeFun();
        var rol = $('#enum_treegrid_id').treegrid('getSelected');
        if(rol == null){
            $.messager.show({
                title:'提示',
                msg:'无选中数据！',
                timeout:1000,
                showType:'slide'
            });
            return false;
        }

        if(confirm('确定删除节点吗？')){
            $.ajax({
                url: url,
                type:"post",
                data:{"id":rol.id},
                success:function(res){

                    if(res.body == true){
                        $.messager.show({
                            title:'提示',
                            msg:'删除成功！',
                            timeout:1000,
                            showType:'slide'
                        });
                        win_closeFun()
                    }
                }
            });
        }

    }
    function win_closeFun(){
        $('#enum_treegrid_id').treegrid(
            "reload"
        );
    }
</script>
<div>
    <#--<a id="btn" href="javascript:void(0)" onclick="enum_addFun('/enum/addEnum')"  class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>-->
    <#list btns as item>

    <a id="btn" href="javascript:void(0)" onclick="${item.code}Fun('${item.url}')"  class="easyui-linkbutton" data-options="iconCls:'${item.icons}'">${item.name}</a>
    <#else>
    <#--//空时做的操作-->
    </#list>

</div>
<table id="enum_treegrid_id" style="width:100%;height:100%">
    <#--<thead>
    <tr>
        <th data-options="field:'name',width:180">菜单名</th>
        <th data-options="field:'code',width:60,align:'right'">菜单编码</th>
        <th data-options="field:'begin',width:80">菜单类型1菜单2链接3按钮</th>
        <th data-options="field:'操作',width:80">End Date</th>
    </tr>
    </thead>-->
</table>
<div id="add_win" class="easyui-window" title="My Window" style="width:600px;height:400px"
     data-options="iconCls:'icon-save',modal:true,closed:true">

</div>
