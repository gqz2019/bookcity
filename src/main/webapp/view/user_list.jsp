<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 2021/10/31
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>用户信息列表页面</title>
</head>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript">
</script>
<body>
<h2>用户信息列表页面</h2>
<table border="1">
    <tr>
        <th>序号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>邮箱</th>
        <th>修改
            <a href="<%=basePath%>user/toAdd">添加</a>
        </th>
    </tr>
    <c:forEach items="${ulist}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.password}</td>
            <td>${u.email}</td>
            <td>
                <a href="<%=basePath%>user/deleteUserById?id=${u.id}">删除</a>
                <a href="<%=basePath%>user/toUpdate?id=${u.id}">修改</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5">
            <a href="<%=basePath%>user/list?pageNum=1">首页</a>
            <a href="<%=basePath%>user/list?pageNum=${pageNum == 1 ? 1 : (pageNum-1)}">上一页</a>
            <a href="<%=basePath%>user/list?pageNum=${pageNum == totalPage ? pageNum : (pageNum+1)}">下一页</a>
            <a href="<%=basePath%>user/list?pageNum=${totalPage}">尾页</a>
        </td>
    </tr>
</table>
</body>
</html>
