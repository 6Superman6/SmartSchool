<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 大哥 你好
  Date: 2019/8/20
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>SpringMVC形式文件上传</h3>
<h4>
    <form action="user/fileupload2" method="post" enctype="multipart/form-data">
        文件上传：<input type="file" name="upload" /><br/>
        <input type="submit" value="上传" />
    </form><p />
    <form action="testtell/insertTell" method="post" enctype="multipart/form-data">
        <h1>考试通告添加</h1>
        <h2>考试名称：<input type="text" name="testname" /></h2>
        <h2>考试时间：<input type="text" name="testdate" /></h2>
        <h2>考试地点：<input type="text" name="testarea" /></h2>
        <h2>学校：<input type="text" name="udept" /></h2>
        <h2>相关图片：<input type="file" name="image" /></h2>
        <input type="submit" value="上传" />
    </form><p />

    <form action="testtell/updateTell" method="post" enctype="multipart/form-data">
        <h1>考试修改</h1>
        <h2>考试编号：<input type="text" name="testid" /></h2>
        <h2>考试名称：<input type="text" name="testname" /></h2>
        <h2>考试时间：<input type="text" name="testdate" /></h2>
        <h2>考试地点：<input type="text" name="testarea" /></h2>
        <h2>学校：<input type="text" name="udept" /></h2>
        <h2>相关图片：<input type="file" name="image" /></h2>
        <input type="submit" value="上传" />
    </form><p />

    <form action="lf/testInsertTo" method="post" enctype="multipart/form-data">
        <h1>考试通告修改</h1>
        <h2>描述：<input type="text" name="des" /></h2>
        <h2>时间：<input type="text" name="time" /></h2>
        <h2>报备人员：<input type="text" name="uid" /></h2>
        <h2>失主：<input type="text" name="lflag" /></h2>
        <h2>状态(已维修/待维修)：<input type="text" name="lstatic" /></h2>
        <h2>学校：<input type="text" name="udept" /></h2>
        <h2>相关图片：<input type="file" name="image" /></h2>
        <input type="submit" value="上传" />
    </form><p />
    <form action="lf/testUpdateTo" method="post" enctype="multipart/form-data">
        <h1>拾物招领修改</h1>
        <h2>描述：<input type="text" name="id" /></h2>
        <h2>描述：<input type="text" name="des" /></h2>
        <h2>时间：<input type="text" name="time" /></h2>
        <h2>状态(已维修/待维修)：<input type="text" name="lstatic" /></h2>
        <h2>学校：<input type="text" name="udept" /></h2>
        <h2>相关图片：<input type="file" name="image" /></h2>
        <input type="submit" value="上传" />
    </form><p />
    <form action="user/update" method="post" enctype="multipart/form-data">
        <h1>用户修改个人信息</h1>
        <h2>账号：<input type="text" name="uid" /></h2>
        <h2>密码：<input type="text" name="upsd" /></h2>
        <h2>用户名：<input type="text" name="uname" /></h2>
        <h2>邮箱：<input type="text" name="uemail" /></h2>
        <h2>手机号：<input type="text" name="utel" /></h2>
        <h2>学院：<input type="text" name="udept" /></h2>
        <h2>头像：<input type="file" name="uimage" /></h2>
        <input type="submit" value="上传" />
    </form><p />

    <form action="record/insertTo" method="post" enctype="multipart/form-data">
        <h1>添加报备</h1>
        <h2>报备人员：<input type="text" name="ruid" /></h2>
        <h2>报备时间：<input type="text" name="rdate" /></h2>
        <h2>地点：<input type="text" name="radr" /></h2>
        <h2>类型：<input type="text" name="rtype" /></h2>
        <h2>状态：<input type="text" name="wstatic" /></h2>
        <h2>描述：<input type="text" name="rdes" /></h2>
        <h2>学校：<input type="text" name="udept" /></h2>
        <h2>相关图片：<input type="file" name="image" /></h2>
        <input type="submit" value="上传" />
    </form><p />
    <form action="record/updateTo" method="post" enctype="multipart/form-data">
        <h1>报备修改</h1>
        <h2>编号：<input type="text" name="rid" /></h2>
        <h2>地点：<input type="text" name="radr" /></h2>
        <h2>类型：<input type="text" name="rtype" /></h2>
        <h2>描述：<input type="text" name="rdes" /></h2
        <h2>学校：<input type="text" name="udept" /></h2>
        <h2>相关图片：<input type="file" name="image" /></h2>
        <input type="submit" value="上传" />
    </form><p />

    <form action="trade/insertTo" method="post" enctype="multipart/form-data">
        <h1>添加二手</h1>
        <h2>描述：<input type="text" name="tdes" /></h2>
        <h2>卖家编号：<input type="text" name="tuid" /></h2>
        <h2>价格：<input type="text" name="tprice" /></h2>
        <h2>学校：<input type="text" name="udept" /></h2>
        <h2>相关图片：<input type="file" name="image" /></h2>
        <input type="submit" value="上传" />
    </form><p />

    <form action="trade/updateTo" method="post" enctype="multipart/form-data">
        <h1>修改二手</h1>
        <h2>编号：<input type="text" name="tid" /></h2>
        <h2>描述：<input type="text" name="tdes" /></h2>
        <h2>价格：<input type="text" name="tprice" /></h2>
        <h2>学校：<input type="text" name="udept" /></h2>
        <h2>相关图片：<input type="file" name="image" /></h2>
        <input type="submit" value="上传" />
    </form><p />

</h4>
</body>
</html>
