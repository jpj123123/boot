<script type="text/javascript">
    function goodsEditSubmit() {
        $('#editGoodsForm').form('submit', {
            success: function (data) {
                var json = eval("(" + data + ")")
                if (json.code == 0) {
                    goods_datagrid_reloadFun();
                    $('#goods-edit-layout').parent().window('close')
                    $.messager.show({
                        title:'提示',
                        msg:"修改成功",
                        timeout:3000,
                        showType:'slide'
                    });
                    return;
                }else{
                    $.messager.show({
                        title:'提示',
                        msg:data.body,
                        timeout:3000,
                        showType:'slide'
                    });
                }
            }
        });
    }
</script>
<div id="goods-edit-layout" class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="editGoodsForm" method="post" action="/goods/editGoodsSubmit">
            <input type="hidden" name="id" value="${goods.id}">
            <input type="hidden"  name="code" value="${goods.code}">
            <div>
                <label >商品编码:</label>${goods.code}
            </div>
            <div>
                <label for="name">商品名名:</label>
                <input class="easyui-validatebox" type="text" name="name"  value="${goods.name}" data-options="required:true"/>
            </div>
            <div>
                <label for="cost">成本价:</label>
                <input class="easyui-validatebox" type="text" name="cost"  value="${goods.cost?c}"/>分
            </div>
            <div>
                <label for="price">售价:</label>
                <input class="easyui-validatebox" type="text" name="price"  value="${goods.price?c}"/>分
            </div>
            <div>
                <label>商品库存:</label>${goods.count?c}

            </div>
            <div>
                <label for="islist">菜单类型:</label>
                <label><input class="easyui-validatebox" type="radio" name="islist" value="0" <#if !goods.islist>checked</#if>/>下架</label>
                <label><input class="easyui-validatebox" type="radio" name="islist" value="1" <#if goods.islist>checked</#if>/>上架</label>
            </div>
        </form>
    </div>
    <div region="south" border="false" style="text-align:right;padding:5px 0;">
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="goodsEditSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#goods-edit-layout').parent().window('close')">关闭</a>
    </div>
</div>