<%--
  Created by IntelliJ IDEA.
  User: yangqi
  Date: 2019-8-17 0017
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testtell</title>
</head>
<script src="js/jquery.min.js"></script>

<script>
    // 页面加载，绑定单击事件
    $(function(){
        $("#btn1").click(function(){
            // alert("hello btn");
            $.ajax({
                //编写json格式，设置属性和值
                url:"testtell/insertTell",//增加考试信息
                contentType:"application/json;charset=UTF-8",
                data:'{"testname":"计算机等级考试","testdate":"2019-12-12","testarea":"信息工程学院","image":"C://"}',
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
                url:"testtell/updateTell",//修改考试信息
                contentType:"application/json;charset=UTF-8",
                data:'{"testid":"3","testname":"软考","testdate":"2019-12-12","testarea":"信息工程学院","image":"C://"}',
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
                url:"testtell/deleteTell",//删除考试信息
                contentType:"application/json;charset=UTF-8",
                data:'1',
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
                url:"testtell/checkTell",//查看所有的考试通知
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

<body>
    <h3>  考试通知 </h3>
    <h3><button id="btn1">添加一条考试通知</button></h3>
    <h3><button id="btn2">修改一条考试通知</button></h3>
    <h3><button id="btn3">删除一条考试通知</button></h3>
    <h3><button id="btn4">查看所有考试通知</button></h3>
</body>
</html>
