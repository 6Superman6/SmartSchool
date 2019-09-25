<%--
  Created by IntelliJ IDEA.
  User: yangqi
  Date: 2019-8-16 0016
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>


<script src="js/jquery.min.js"></script>

<script>
    // 页面加载，绑定单击事件
    $(function(){
        $("#btn1").click(function(){
            // alert("hello btn");
            $.ajax({
                //编写json格式，设置属性和值
                url:"test/insertTest",//增加考试信息
                contentType:"application/json;charset=UTF-8",
                data:'{"tname":"计算机等级考试","turl":"http://ncre.cqksy.cn/NCRE_EMS/StudentLogin.aspx","tcollege":"信息工程学院"}',
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
                url:"test/updateTest",//修改考试信息
                contentType:"application/json;charset=UTF-8",
                data:'{"tid":"6","tname":"软考","turl":"http://ncre.cqksy.cn/NCRE_EMS/StudentLogin.aspx","tcollege":"信息工程学院"}',
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
                url:"test/deleteTest",//删除考试信息
                contentType:"application/json;charset=UTF-8",
                data:'2',
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
                url:"test/checktest",//查看所有的考试信息
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
        $("#btn5").click(function(){
            // alert("hello btn");
            $.ajax({
                //编写json格式，设置属性和值
                url:"test/checkcollege",//查看此学院下的考试信息
                contentType:"application/json;charset=UTF-8",
                data:'{"tcollege":"信息工程学院"}',
                dataType:"json",
                type:"post",
                success:function (data) {
                    // data服务器端相应的json的数据，进行解析
                    alert(data.status);
                    alert(data.msg);
                    alert(data.list);
                    alert(data.list[0].turl);
                }

            });
        });
    });

</script>


</head>
<body>
    <h3>  考试报名入口 </h3>
    <h3><button id="btn1">添加一条考试信息  </button></h3>
    <h3><button id="btn2">修改一条考试信息</button></h3>
    <h3><button id="btn3">删除一条考试信息</button></h3>
    <h3><button id="btn4">查看所有考试信息</button></h3>
    <h3><button id="btn5">查看学院考试信息</button></h3>

</body>
</html>
