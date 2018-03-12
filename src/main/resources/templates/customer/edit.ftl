<script type="text/javascript">
    $(function() {
        $('#user_datagrid_id').datagrid({
            url:'/user/listUser',
            singleSelect:true,
            columns: [[
                {title: 'id', field: 'id', width: 50},
                {title: 'pid', field: 'pid', width: 50},
                {title: '用户名', field: 'name', width: 100},
                {title: '真是姓名', field: 'realName', width: 100}
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
    function customerEditSubmit() {
        $('#editCustomerForm').form('submit', {
            success: function (data) {
                var data = eval('(' + data + ')');
                if(data.code==0){
                    customer_datagrid_reloadFun();
                    $('#customer-edit-layout').parent().window('close')
                    $.messager.show({
                        title:'提示',
                        msg:"修改成功",
                        timeout:3000,
                        showType:'slide'
                    });
                    return;
                }
                $.messager.show({
                    title:'提示',
                    msg:data.body,
                    timeout:3000,
                    showType:'slide'
                });
            }
        });
    }
</script>
<div id="customer-edit-layout" class="easyui-layout" fit="true">

    <div data-options="region:'east',title:'用户列表',split:true" style="width:320px;">
        <table id="user_datagrid_id"></table>
    </div>
    <div data-options="region:'center',border:false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="editCustomerForm" method="post" action="/customer/editCustomerSubmit">
            <input type="hidden" name="id" value="${customer.id}">
            <div>
                <label for="name">客户名:</label>
                <input class="easyui-validatebox" type="text" name="name" value="${customer.name}" data-options="required:true"/>
            </div>
            <div>
                <label for="phone">联系电话:</label>
                <input class="easyui-validatebox" type="text" name="phone" value="${customer.phone}" data-options="required:true"/>
            </div>
            <div>
                <label for="address">地址:</label>
                <input class="easyui-validatebox" type="text" name="address" value="${customer.address}" data-options="required:true"/>
            </div>
            <div>
                <label for="money">余额:</label>
                <input class="easyui-validatebox" type="text" name="money" value="${customer.money?c}" data-options="required:true"/>单位（分）
            </div>
            <#--<div>
                <label for="userId">所属用户id:</label>
                <input class="easyui-validatebox" type="text" name="userId" value="${customer.userId!""}"/>
            </div>-->
        </form>
    </div>
    <div data-options="region:'south'" style="height:40px;text-align:right;padding:5px;">
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="customerEditSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#customer-edit-layout').parent().window('close')">关闭</a>
    </div>

</div>