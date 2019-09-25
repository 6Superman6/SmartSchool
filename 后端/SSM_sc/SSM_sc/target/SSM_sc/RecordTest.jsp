<%--
  Created by IntelliJ IDEA.
  User: 大哥 你好
  Date: 2019/8/8
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="js/jquery.min.js"></script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn1").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"record/insertTo",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"ruid":"2017101","rdate":"2019-09-01","rdes":"三宿","radr":"3D315","rtype":"电++器","wstatic":"待维修","image":"106.12.189.19"}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                    }

                });
            });
        });

    </script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn2").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"record/updateTo",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"rid":52,"rdes":"三宿","radr":"3D315","rtype":"电器","image":"106.12.189.19"}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                    }

                });
            });
        });

    </script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn3").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"record/deleteTo",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"rid":53}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                    }

                });
            });
        });

    </script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn4").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"record/delByW",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"rid":53,"wid":"T12301","wstatic":"已维修","wdate":"2019-06-08"}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                    }

                });
            });
        });

    </script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn5").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"record/rmess",
                    contentType:"application/json;charset=UTF-8",
                    data:'{}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                        alert(data.list);
                    }

                });
            });
        });

    </script>

    <script>
    // 页面加载，绑定单击事件
    $(function(){
        $("#btn6").click(function(){
            // alert("hello btn");
            $.ajax({
                //编写json格式，设置属性和值
                url:"record/rmessmy",
                contentType:"application/json;charset=UTF-8",
                data:'{"ruid":"201702"}',
                dataType:"json",
                type:"post",
                success:function (data) {
                    // data服务器端相应的json的数据，进行解析
                    alert(data.status);
                    alert(data.msg);
                    alert(data.list);
                }

            });
        });
    });

</script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn7").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"record/wymessmy",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"wid":"T12301"}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                        alert(data.list);
                    }

                });
            });
        });

    </script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn8").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"record/wwmessmy",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"wid":"T12301"}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                        alert(data.list[0].rid);
                    }

                });
            });
        });

    </script>


</head>
<body>
<h3>Record---Ajax数据<p/>

    <h4><button id="btn1">添加一条故障信息</button></h4>
    <h4><button id="btn2">更新一条故障信息</button></h4>
    <h4><button id="btn3">删除一条故障信息</button></h4>
    <h4><button id="btn4">维修人员修改一条故障信息</button></h4>
    <h4><button id="btn5">维修人员-产看故障信息</button></h4>
    <h4><button id="btn6">报备人员查看自己发布的报备信息</button></h4>
    <h4><button id="btn7">维修人员查看已维修的报备信息</button></h4>
    <h4><button id="btn8">维修人员查看待维修的报备信息</button></h4>

</h3>
</body>
</html>
