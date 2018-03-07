<!DOCTYPE>
<HTML>
<HEAD>
    <TITLE>欢迎登录</TITLE>
    <link rel="icon" type="image/x-icon" href="/public/favicon.ico">
    <script src="/public/easyui/jquery.min.js" type="text/javascript"></script>
    <script src="/public/easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <link href="/public/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="/public/easyui/themes/icon.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        $(function(){
            $('#enum_tree').tree({
                url: "/enum/listEnum",
                mrthod: "GET",
                loadFilter: function (res) {
                    //alert(res.body);

                    //return eval("(" + res.body + ")");t
                    return res.body;
                },
                onClick: function(node){
                    //alert(node.attributes.url);  // alert node text property when clicked
                    if(node.attributes.type == 1){
                        addEnum(node);
                    }else if(node.attributes.type == 2){
                        addLink(node);
                    }
                }
            });
        });
        function addEnum(node) {

        }
        function addLink(node) {
            var panelCenter = $("#root-layout").layout("panel","center");
            panelCenter.panel('refresh',node.attributes.url);
        }
    </script>
</HEAD>
<body id="root-layout" class="easyui-layout">
<div data-options="region:'north',title:'North Title',split:true" style="height:100px;">
    <a id="btn" href="/logout" class="easyui-linkbutton" data-options="iconCls:'icon-man'">退出登录</a>
</div>
<div data-options="region:'south'" style="height:30px;text-align: center;">
    Copyright © 2018 - 2020 杞县六个核桃代理商网站
</div>
<#--<div data-options="region:'east',title:'West',split:true" style="width:100px;"></div>-->
<div data-options="region:'west',title:'菜单栏',split:true" style="width:150px;">
    <ul id="enum_tree"></ul>
</div>
<div  data-options="region:'center',title:'欢迎页'" style="padding:5px;background:#eee;">
    <p style="font-size: 60px; color:#00f000">欢迎登录${userName!""}</p>
</div>
</body>
</HTML>