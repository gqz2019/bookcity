<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 2021/10/31
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>用户修改页面</title>
</head>
<script type="text/javascript" src="<%=basePath%>static/js/jquery.js"></script>
<script type="text/javascript">
    function update() {
        // var form = $("form").serialize();
        var id = $("input[id=id]").val()
        var username = $("input[id=username]").val()
        var password = $("input[id=password]").val()
        var email = $("input[id=email]").val()

        var param = JSON.stringify(
            {
                "id": id,
                "username": username,
                "password": password,
                "email": email
            }
        );
        $.ajax({
            url: "<%=basePath%>user/update",
            type: "post",
            contentType: "application/json;charset=utf-8",
            data: param,
            success: (resp)=>{
                console.log(resp);
                var json = $.parseJSON(resp);
                console.log(json.flag);

                if (json.flag) {
                    alert("修改成功")
                    location.href = "<%=basePath%>user/list"
                } else {
                    alert("修改失败")
                }
            }
        })
    }

</script>
<body>
<form>
    <input type="hidden" id="id" name="id" value="${u.id}">
    用户名：<input type="text" id="username" value="${u.username}" name="username"><br>
    密码：<input type="text" id="password" value="${u.password}" name="password"><br>
    邮箱：<input type="text" id="email" value="${u.email}" name="email"><br>
    <button type="button" onclick="update()">修改</button>
</form>
</body>
</html>
