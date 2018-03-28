<script type="text/javascript">
    $(function() {
        $('#outlib_datagrid_id').datagrid({
            url:"/outlib/listAll",
            singleSelect:true,
            queryParams:{
                "datestart":"${date}",
                "dateend":"${date}",
                "pageNumber":1,
                "pageSize":20
            },
            columns: [[
                {title: 'id', field: 'id', width: 50},
                {title: '出入库（ true出库 false 入库）', field: 'isout', width: 180},
                {title: '责任人', field: 'userId', width: 160},
                {title: '用户名', field: 'userName', width: 120},
                {title: '产品名', field: 'goodsName', width: 120},
                {title: '数量', field: 'goodsCount', width: 120},
                {title: '创建时间', field: 'createTime', width: 180},
                {title: '备注', field: 'remark', width: 200}

            ]],
            <#--//data:${enums},-->
            loadFilter:function(data){
                console.log(data)
                //更新分页
                reload_page("outlib_pp",data)
                return data.body.list;
            },
            onBeforeExpand: function (row) {
                return true;
            }
        })
    });
    var outlib_list_allFun = function(url){
        var pageNumber = $('#outlib_pp').pagination("options").pageNumber;
        var pageSize = $('#outlib_pp').pagination("options").pageSize;
        outlib_reload_fun(pageNumber,pageSize);

    }

    function outlib_addFun(url){

        $('#outlib_win').window('open');
        ///enum/addEnum
        $('#outlib_win').window('refresh',url);

    }
    function outlib_editFun(url){
        var rol = $('#outlib_datagrid_id').datagrid('getSelected');
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

        $('#outlib_win').window('open');
        ///enum/addEnum
        $('#outlib_win').window('refresh',url+"?id="+id);

    }

    function outlib_deleteFun(url){
        var rol = $('#outlib_datagrid_id').datagrid('getSelected');
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
                        outlib_datagrid_reloadFun();
                    }
                }
            });
        }
    }
    function outlib_datagrid_reloadFun(){
        $('#outlib_datagrid_id').datagrid(
            "reload"
        );
    }
    var outlib_reload_fun = function(pageNumber, pageSize){
        var startTime = dateFormat.getTime($("#datestart").val(),dateFormat.date_format)
        var endTime = dateFormat.getTime($("#dateend").val(),dateFormat.date_format)
        if(startTime>endTime){
            $.messager.show({
                title:'提示',
                msg:'起始日期不能大于结束日期！',
                timeout:1000,
                showType:'slide'
            });
        }
        $('#outlib_datagrid_id').datagrid('load',{
            'pageNumber': pageNumber,
            'pageSize': pageSize,
            "datestart":$("#datestart").val(),
            "dateend":$("#dateend").val()
        });
    }
</script>
<div class="easyui-layout" style="width:100%;height:100%">
    <div data-options="region:'north'" style="height:50px">
        <#if btns??>
        <#list btns as item>
            <#if item.code == 'outlib_list_all'>
            <label>起始日期：<input id="datestart" type="text" value="${date}"   required="required"></label>
            <label>结束日期：<input id="dateend" type="text" value="${date}"  required="required"></label>
            <br/>
            </#if>
        <a id="btn" href="javascript:void(0)" onclick="${item.code}Fun('${item.url}')"  class="easyui-linkbutton" data-options="iconCls:'${item.icons}'">${item.name}</a>
        <#else>
        <#--//空时做的操作-->
        </#list>
        </#if>
        <script type="text/javascript">
            //初始化控件
            laydate.render({
                elem: '#datestart' //指定元素
            });
            laydate.render({
                elem: '#dateend' //指定元素
            });
        </script>
    </div>
    <div data-options="region:'center'">
        <table id="outlib_datagrid_id" style="width: ;:100%;height:100%"></table>
    </div>
    <div data-options="region:'south'" style="height:35px;">
        <div id="outlib_pp" style="background:#efefef;border:1px solid #ccc;"></div>
        <script type="text/javascript">
            $('#outlib_pp').pagination({
                pageSize:20,
                pageList:[5,10,20,30,50],
                onSelectPage:function(pageNumber, pageSize){
                    outlib_reload_fun(pageNumber, pageSize);
                },
                onChangePageSize:function(pageSize){
                    var pageNumber = $('#outlib_pp').pagination("options").pageNumber;
                    outlib_reload_fun(pageNumber, pageSize);
                }
            });
        </script>
        <div id="outlib_win" class="easyui-window" title="客户操作窗口" style="width:800px;height:600px"
             data-options="iconCls:'icon-save',modal:true,closed:true">
        </div>
    </div>
</div>
