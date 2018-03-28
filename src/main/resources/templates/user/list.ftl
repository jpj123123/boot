<script type="text/javascript">
    $(function() {
        $('#user_datagrid_id').datagrid({
            url:'/user/listUser',
            singleSelect:true,
            queryParams:{
                "pageNumber":1,
                "pageSize":20
            },
            columns: [[
                {title: 'id', field: 'id', width: 50},
                {title: 'pid', field: 'pid', width: 50},
                {title: '用户名', field: 'name', width: 180},
                {title: '真是姓名', field: 'realName', width: 180},
                {title: '联系电话', field: 'phone', width: 100},
                {title: '住址', field: 'address', width: 180},
                {title: '是业务员', field: 'issale', width: 180}
            ]],
            <#--//data:${enums},-->
            loadFilter:function(data){
                reload_page("user_pp",data);
                return data.body.list;
            },
            onBeforeExpand: function (row) {
                return true;
            }
        })
    });

    var user_list_allFun = function(url){
        $('#user_datagrid_id').datagrid(
                "reload"
        );
    }
    function user_addFun(url){
        var rol = $('#user_datagrid_id').datagrid('getSelected');
        if(rol == null){
            $.messager.show({
                title:'提示',
                msg:'无选中数据！',
                timeout:1000,
                showType:'slide'
            });
            return false;
        }
        var pid = 0;
        if(rol != null)
            pid = rol.id;

        $('#user_win').window('open');
        ///enum/addEnum
        $('#user_win').window('refresh',url+"?pid="+pid);

    }
    function user_editFun(url){
        var rol = $('#user_datagrid_id').datagrid('getSelected');
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

        $('#user_win').window('open');
        ///enum/addEnum
        $('#user_win').window('refresh',url+"?id="+id);

    }

    function user_delFun(url){
        var rol = $('#user_datagrid_id').datagrid('getSelected');
        if(rol == null){
            $.messager.show({
                title:'提示',
                msg:'无选中数据！',
                timeout:1000,
                showType:'slide'
            });
            return false;
        }
        if(rol.pid==0){
            $.messager.show({
                title:'提示',
                msg:'无法删除根节点！',
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
                        role_datagrid_reloadFun();
                    }
                }
            });
        }

    }
    function add_user_roleFun(url){
        var rol = $('#user_datagrid_id').datagrid('getSelected');
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

        $('#user_win').window('open');
        ///enum/addEnum
        $('#user_win').window('refresh',url+"?id="+id);
    }
    function user_datagrid_reloadFun(){
        $('#user_datagrid_id').datagrid(
            "reload"
        );
    }
</script>
<div class="easyui-layout" style="width:100%;height:100%">
    <div data-options="region:'north'" style="height:50px">
        <#list btns as item>

            <a id="btn" href="javascript:void(0)" onclick="${item.code}Fun('${item.url}')"  class="easyui-linkbutton" data-options="iconCls:'${item.icons}'">${item.name}</a>
        <#else>
        <#--//空时做的操作-->
        </#list>
    </div>
    <div data-options="region:'center'">
        <table id="user_datagrid_id" style="width:100%;height:100%">

        </table>
        <div id="user_win" class="easyui-window" title="My Window" style="width:600px;height:400px"
             data-options="iconCls:'icon-save',modal:true,closed:true">

        </div>
    </div>
    <div data-options="region:'south'" style="height:35px;">
        <div id="user_pp" style="background:#efefef;border:1px solid #ccc;"></div>
        <script type="text/javascript">
            $('#user_pp').pagination({
                pageSize:20,
                pageList:[5,10,20,30,50],
                onSelectPage:function(pageNumber, pageSize){
                    $('#user_datagrid_id').datagrid('load',{
                        "pageNumber": pageNumber,
                        "pageSize": pageSize
                    });
                },
                onChangePageSize:function(pageSize){
                    var pageNumber = $('#user_pp').pagination("options").pageNumber;
                    $('#user_datagrid_id').datagrid('load',{
                        'pageNumber': pageNumber,
                        'pageSize': pageSize
                    });
                }
            });

        </script>
    </div>
</div>