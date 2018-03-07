<script type="text/javascript">
    function enumAddSubmit() {
        $('#addEnumForm').form('submit', {
            //url:...,
            // onSubmit: function(){
            // // do some check
            // // return false to prevent submit;
            // },
            success: function (data) {
                console.log(data)
                win_closeFun();
                $('#enum-add-layout').parent().window('close')
                // alert(data)
                // alert(data.code)
            }
        });
    }
</script>
<div id="enum-add-layout" class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="addEnumForm" method="post" action="/enum/addEnumSubmit">
        ${pid}
            <input name="pid" type="hidden" value="${pid}"/>
    <#if pid gt 0>

    <div>
        <label>上级菜单名:</label>
        ${pname}
    </div>
    </#if>

            <div>
                <label for="code">编号:</label>
                <input class="easyui-validatebox" type="text" name="code" data-options="required:true"/>
            </div>
            <div>
                <label for="name">菜单名:</label>
                <input class="easyui-validatebox" type="text" name="name" data-options="required:true"/>
            </div>
            <div>
                <label for="url">url:</label>
                <input class="easyui-validatebox" type="text" name="url" data-options="required:true"/>
            </div>
            <div>
                <label for="icons">图标:</label>
                <input class="easyui-validatebox" type="text" name="icons"/>
            </div>
            <div>
                <label for="type">菜单类型:</label>
                <label><input class="easyui-validatebox" type="radio" name="type" value="1"/>菜单</label>
                <label><input class="easyui-validatebox" type="radio" name="type" value="2"/>链接</label>
                <label><input class="easyui-validatebox" type="radio" name="type" value="3"/>按钮</label>
            </div>
            <div>
                <label for="desc">顺序:</label>
                <input class="easyui-validatebox" type="text" name="desc"/>
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
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="enumAddSubmit()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"
           onclick="$('#enum-add-layout').parent().window('close')">关闭</a>
    </div>
</div>