<script type="text/javascript">
    function goodsAddSubmit() {
        $('#addGoodsForm').form('submit', {
            success: function (data) {
                console.log(data)
                goods_datagrid_reloadFun();
                $('#goods-add-layout').parent().window('close')
                // alert(data)
                // alert(data.code)
            }
        });
    }
</script>
<div id="goods-add-layout" class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="addGoodsForm" method="post" action="/goods/addGoodsSubmit">
            <div>
                <label for="code">商品编码:</label>
                <input class="easyui-validatebox" type="text" name="code" data-options="required:true"/>
            </div>
            <div>
                <label for="name">商品名名:</label>
                <input class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
            </div>
            <div>
                <label for="cost">成本价:</label>
                <input class="easyui-validatebox" type="text" name="cost"/>分
            </div>
            <div>
                <label for="price">售价:</label>
                <input class="easyui-validatebox" type="text" name="price"/>分
            </div>
            <div>
                <label for="islist">菜单类型:</label>
                <label><input class="easyui-validatebox" type="radio" name="islist" value="0"/>下架</label>
                <label><input class="easyui-validatebox" type="radio" name="islist" value="1" checked/>上架</label>
            </div>
        </form>
    </div>
    <div region="south" border="false" style="text-align:right;padding:5px 0;">
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="goodsAddSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#goods-add-layout').parent().window('close')">关闭</a>
    </div>
</div>