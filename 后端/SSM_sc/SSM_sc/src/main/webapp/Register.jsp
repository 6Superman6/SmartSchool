<%--
  Created by IntelliJ IDEA.
  User: 林朝阳
  Date: 2019/8/15
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="js/jquery.min.js"></script>
    <script>
        //页面加载，绑定单击事件
        $(function () {
            $("#btn1").click(function () {
                //alert("hello btn")
                //发送ajax请求
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"user/register",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"uid":"201702505248","upsd":"nichaoge","uname":"liuchao","uemail":"465445097@qq.com","utel":"15621153978","udept":"信息工程学院","ugrade":"0"}',
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
        //页面加载，绑定单击事件
        $(function () {
            $("#btn2").click(function () {
                //alert("hello btn")
                //发送ajax请求
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"user/login",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"uid":"201702505248","upsd":"nichaoge","autoLogin":"true"}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                        alert(data.data.uid);
                    }
                });
            });
        });
    </script>
    <script>
        //页面加载，绑定单击事件
        $(function () {
            $("#btn3").click(function () {
                //alert("hello btn")
                //发送ajax请求
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"user/logout",
                    contentType:"application/json;charset=UTF-8",
                    data:'{}',
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
        //页面加载，绑定单击事件
        $(function () {
            $("#btn4").click(function () {
                //alert("hello btn")
                //发送ajax请求
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"user/login/auto",
                    contentType:"application/json;charset=UTF-8",
                    data:'{}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data.status);
                        alert(data.msg);
                        alert(data.data);
                    }
                });
            });
        });
    </script>
    <script>
        //页面加载，绑定单击事件
        $(function () {
            $("#btn5").click(function () {
                //alert("hello btn")
                //发送ajax请求
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"user/update",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"uid":"201702505248","upsd":"nichaoge255","uname":"liuchao321","uemail":"465445097@qq.com","utel":"15621153978","udept":"信息工程学院","uimage":"adsf1dasf11"}',
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
</head>
<body>
<button id="btn1">注册</button><br>
<button id="btn2">登录</button><br>
<button id="btn3">退出</button><br>
<button id="btn4">自动登录</button><br>
<button id="btn5">修改</button><br>
</body>
</html>
