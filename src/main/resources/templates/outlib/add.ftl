<script type="text/javascript">
    function outlibAddSubmit() {
        $('#addOutlibForm').form('submit', {
            success: function (data) {
                var json = eval("(" + data + ")")
                if (json.code == 0) {
                    outlib_datagrid_reloadFun();
                    $('#outlib-add-layout').parent().window('close')
                    $.messager.show({
                        title:'提示',
                        msg:"添加成功",
                        timeout:3000,
                        showType:'slide'
                    });
                    return;
                }else{
                    $.messager.show({
                        title:'提示',
                        msg:json.body,
                        timeout:3000,
                        showType:'slide'
                    });
                }
            }
        });
    }
</script>
<div id="outlib-add-layout" class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="addOutlibForm" method="post" action="/outlib/addOutLibSubmit">
            <div>
                <label for="isOut">出入库:</label>
                <label><input class="easyui-validatebox" type="radio" name="isOut" value="0"/>入库</label>
                <label><input class="easyui-validatebox" type="radio" name="isOut" value="1" checked/>出库</label>
            </div>
            <div>
                <label for="userId">选择业务员:</label>
                <select name="userId" >
                    <option value="${defaultId}">${defaultName}</option>
                    <#if sales??>
                        <#list sales as item>
                            <option value="${item.id}">${item.name}</option>
                        </#list>
                    </#if>
                </select>
            </div>
            <div>
                <label for="goodsId">选择商品:</label>
                <select name="goodsId" >
                    <#if goods??>
                        <#list goods as item>
                            <option value="${item.id}">${item.name}</option>
                        </#list>
                    </#if>
                </select>
            </div>
            <div>
                <label for="price">数量:</label>
                <input class="easyui-validatebox" type="text" name="goodsCount"/>
            </div>
            <div>
                <label for="price">备注:</label>
                <input class="easyui-validatebox" type="text" name="remark"/>
            </div>
        </form>
    </div>
    <div region="south" border="false" style="text-align:right;padding:5px 0;">
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="outlibAddSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#outlib-add-layout').parent().window('close')">关闭</a>
    </div>
</div>