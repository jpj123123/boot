<script type="text/javascript">
    $(function() {
        $('#goods_datagrid_id').datagrid({
            url:'/goods/listAll',
            singleSelect:true,
            columns: [[
                {title: 'id', field: 'id', width: 50},
                {title: '商品编码', field: 'code', width: 120},
                {title: '商品名', field: 'name', width: 160},
                {title: '成本价（单位分）', field: 'cost', width: 120},
                {title: '单价（单位分）', field: 'price', width: 120},
                {title: '是否上架（ 1上架 0 下架）', field: 'islist', width: 180},
                {title: '创建时间', field: 'createTime', width: 180}
            ]],
            <#--//data:${enums},-->
            loadFilter:function(data){
                return data.body;
            },
            onBeforeExpand: function (row) {
                return true;
            }
        })
    });
    var goods_list_allFun = function(url){
        $('#user_datagrid_id').datagrid(
                "reload"
        );
    }
    function goods_addFun(url){

        $('#goods_win').window('open');
        ///enum/addEnum
        $('#goods_win').window('refresh',url);

    }
    function goods_editFun(url){
        var rol = $('#goods_datagrid_id').datagrid('getSelected');
        if(rol == null){
            $.messager.show({
                title:'提示',
                msg:'无选中数据！',
                timeout:1000,
                showType:'slide'
            });
            return false;
        }
        var id = 0;
        if(rol != null)
            id = rol.id;

        $('#goods_win').window('open');
        ///enum/addEnum
        $('#goods_win').window('refresh',url+"?id="+id);

    }

    function goods_deleteFun(url){
        var rol = $('#goods_datagrid_id').datagrid('getSelected');
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
                url: url+"?id="+rol.id,
                type:"post",
                data:{"id":rol.id},
                success:function(res){
                    if(res.body == true){
                        goods_datagrid_reloadFun();
                    }
                }
            });
        }

    }
    function goods_datagrid_reloadFun(){
        $('#goods_datagrid_id').datagrid(
            "reload"
        );
    }
</script>
<div>
    <#--<a id="btn" href="javascript:void(0)" onclick="enum_addFun('/enum/addEnum')"  class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>-->
    <#if btns??>
    <#list btns as item>

    <a id="btn" href="javascript:void(0)" onclick="${item.code}Fun('${item.url}')"  class="easyui-linkbutton" data-options="iconCls:'${item.icons}'">${item.name}</a>
    <#else>
    <#--//空时做的操作-->
    </#list>
    </#if>

</div>
<table id="goods_datagrid_id" style="width: ;:100%;height:100%">
</table>
<div id="goods_win" class="easyui-window" title="客户操作窗口" style="width:800px;height:600px"
     data-options="iconCls:'icon-save',modal:true,closed:true">

</div>
