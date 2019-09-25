<%--
  Created by IntelliJ IDEA.
  User: 大哥 你好
  Date: 2019/7/29
  Time: 21:19
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
            $("#btn").click(function(){
                // alert("hello btn");
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"record/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"小李","pasword":"8989","age":30}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        // data服务器端相应的json的数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.pasword);
                        alert(date.age);

                    }

                });
            });
        });

    </script>
</head>
<body>
    <h3>幻影<p/>
        <a href="user/testString">testString</a><p/>
        <a href="user/testVoid">testVoid</a><p/>

        <a href="user/testModelAndView">testModelAndView</a><p/>

        <a href="user/testForwardOrRedirect">testForwardOrRedirect</a><p/>   <!--使用关键字的方式进行转发和重定向-->

        <button id="btn">发送ajax的请求</button>
        <h4>
            <form action="record/chakan" method="post">
                <input name="Rid" type="text" /><p />
                <input type="submit" value="查看">
            </form>
        </h4>
    </h3>
</body>
</html>
