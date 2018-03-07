<script type="text/javascript">
    $(function() {
        $('#customer_datagrid_id').datagrid({
            url:'/customer/listAll',
            singleSelect:true,
            columns: [[
                {title: 'id', field: 'id', width: 50},
                {title: '客户名', field: 'name', width: 50},
                {title: '手机号', field: 'phone', width: 180},
                {title: '地址', field: 'address', width: 180},
                {title: '账户余额', field: 'money', width: 100},
                {title: '所属人id', field: 'userId', width: 180},
                {title: '所属人账号', field: 'userName', width: 180},
                {title: '所属人姓名', field: 'userRealName', width: 180}
            ]],
            <#--//data:${enums},-->
            loadFilter:function(data){
                console.log(data);
                return data.body;
            },
            onBeforeExpand: function (row) {
                return true;
            }
        })
    });
    var customer_list_allFun = function(url){
        $('#user_datagrid_id').datagrid(
                "reload"
        );
    }
    function customer_addFun(url){

        $('#customer_win').window('open');
        ///enum/addEnum
        $('#customer_win').window('refresh',url);

    }
    function customer_editFun(url){
        var rol = $('#customer_datagrid_id').datagrid('getSelected');
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

        $('#customer_win').window('open');
        ///enum/addEnum
        $('#customer_win').window('refresh',url+"?id="+id);

    }

    function customer_deleteFun(url){
        var rol = $('#customer_datagrid_id').datagrid('getSelected');
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
                        customer_datagrid_reloadFun();
                    }
                }
            });
        }

    }
    function customer_datagrid_reloadFun(){
        $('#customer_datagrid_id').datagrid(
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
<table id="customer_datagrid_id" style="width:100%;height:100%">
</table>
<div id="customer_win" class="easyui-window" title="客户操作窗口" style="width:800px;height:600px"
     data-options="iconCls:'icon-save',modal:true,closed:true">

</div>
