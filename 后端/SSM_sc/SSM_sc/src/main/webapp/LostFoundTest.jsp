<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/13
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>lost and found </title>

    <script src="js/jquery.min.js"></script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn1").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"lf/testInsertTo",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"des":"在教师捡到手机","time":"2019-09-01","uid":"201701","lflag":"失主","lstatic":"我不说","image":"106.12.189.19/wew"}',
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
                    url:"lf/testUpdateTo",//查看所有的失物信息
                    contentType:"application/json;charset=UTF-8",
                    data:'{"id":"4","des":"在教师捡到手机","time":"2019-09-01","uid":"201701","lflag":"失主","lstatic":"已解决","image":"106.12.189.19/wew"}',
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
    <!--
         //data:'{"id":"3","lstatic":"你说呢"}',
         data:'{"id":"3","uid":"21341"}', //人员账号不存在
                    //data:'{"id":"4"",des":"在教师捡到手机","time":"2019-09-01","uid":"201701","lflag":"失主","lstatic":"已解决","image":"106.12.189.19/wew"}',
                    // 数据一样
    -->
    

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn5").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"lf/testDeleteTo",//查看所有的失物信息
                    contentType:"application/json;charset=UTF-8",
                    data:'3',
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
            $("#btn6").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"lf/testAllLost",//查看所有的失物信息
                    contentType:"application/json;charset=UTF-8",
                    data:'失主',
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
                    url:"lf/testAllFound",//查看所有的失物信息
                    contentType:"application/json;charset=UTF-8",
                    data:'得主',
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
                    url:"lf/testAllThing",//查看所有的失物信息
                    contentType:"application/json;charset=UTF-8",
                    data:'{"uid":"201702505201"}',
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
    <h3>   失物招领功能实现 </h3>
    <h3><button id="btn1">添加一条信息----无论是谁 判断uid外键</button></h3>
    <h3><button id="btn3">修改一条失物招领信息----根据id即可</button></h3>
    <h3><button id="btn5">删除一条失物招领信息----根据id</button></h3>
    <h3><button id="btn6">查看所有失物招领信息----所有用户 type为失主</button></h3>
    <h3><button id="btn7">查看所有失物提供信息----所有用户 type为得主</button></h3>
    <h3><button id="btn8">用户查看自己的失物招领信息 不论失主还是得主</button></h3>

</body>
</html>
