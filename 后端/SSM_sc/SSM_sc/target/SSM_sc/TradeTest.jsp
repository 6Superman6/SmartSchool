<%--
  Created by IntelliJ IDEA.
  User: 大哥 你好
  Date: 2019/8/10
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>

    <script src="js/jquery.min.js"></script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn1").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"trade/insertTo",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"tdes":"两9把刀","tuid":"T12301","tprice":20.8,"image":"ima"}',
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
                    url:"trade/updateTo",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"tid":4,"tdes":"梁66666把刀","tprice":20.6,"image":"106.12.189.19/ima"}',
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
                    url:"trade/deleteTo",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"tid":8}',
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
                    url:"trade/rmess",
                    contentType:"application/json;charset=UTF-8",
                    data:'{}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                        alert(data.list[0].tid);

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
                    url:"trade/rmessmy",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"tuid":"T12301"}',
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

</head>
<body>
<h3>Trade---Ajax数据<p/>

    <h4><button id="btn1">添加一条二手物品信息</button></h4>
    <h4><button id="btn2">修改一条二手物品信息</button></h4>
    <h4><button id="btn3">删除一条二手物品信息</button></h4>
    <h4><button id="btn4">查看所有二手物品信息</button></h4>
    <h4><button id="btn5">查看自己发布得二手物品信息------卖家</button></h4>



</h3>

</body>
</html>
