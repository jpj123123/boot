<script type="text/javascript">
    function submit() {
        $('#editEnumForm').form('submit', {
            //url:...,
            // onSubmit: function(){
            // // do some check
            // // return false to prevent submit;
            // },
            success: function (data) {
                console.log(data)
                win_closeFun();
                $('#enum-edit-layout').parent().window('close')
                // alert(data)
                // alert(data.code)
            }
        });
    }
</script>
<div id="enum-edit-layout" class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="editEnumForm" method="post" action="/enum/editEnumSubmit">
            <input type="hidden" name="id" value="${tenum.id}">
            <input type="hidden" name="code" value="${tenum.code}">
            <div>
                <label for="pid">上级id:</label>
                <input class="easyui-validatebox" type="text" name="pid" data-options="required:true" value="${tenum.pid}"/>
            </div>
            <div>
                <label for="code">编号:</label>
                ${tenum.code}
            </div>
            <div>
                <label for="name">菜单名:</label>
                <input class="easyui-validatebox" type="text" name="name" data-options="required:true" value="${tenum.name}"/>
            </div>
            <div>
                <label for="url">url:</label>
                <input class="easyui-validatebox" type="text" name="url" data-options="required:true" value="${tenum.url}"/>
            </div>
            <div>
                <label for="icons">图标:</label>
                <input class="easyui-validatebox" type="text" name="icons" value="${tenum.icons!''}"/>
            </div>
            <div>
                <label for="type">菜单类型:</label>
                <label><input class="easyui-validatebox" type="radio" name="type" value="1" <#if tenum.type == 1>checked</#if>/>菜单</label>
                <label><input class="easyui-validatebox" type="radio" name="type" value="2" <#if tenum.type == 2>checked</#if>/>链接</label>
                <label><input class="easyui-validatebox" type="radio" name="type" value="3" <#if tenum.type == 3>checked</#if>/>按钮</label>
            </div>
            <div>
                <label for="desc">顺序:</label>
                <input class="easyui-validatebox" type="text" name="desc" value="${tenum.desc!''}"/>
            </div>
        <#--<div>
            <label for="email">Email:</label>
            <input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'"/>
        </div>-->

        </form>
        <div id="icons_win" class="easyui-window" title="My Window" style="width:600px;height:400px"
             data-options="iconCls:'icon-save',modal:true,closed:true,href:'/public/icons.html'">
        </div>
    </div>
    <div region="south" border="false" style="text-align:right;padding:5px 0;">
        <a class="easyui-linkbutton" iconCls="icon-help" href="javascript:void(0)" onclick="$('#icons_win').window('open')"">查看图标</a>
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="submit()">提交</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#enum-edit-layout').parent().window('close')">关闭</a>
    </div>
</div>