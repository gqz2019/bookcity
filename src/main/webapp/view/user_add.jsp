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
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>用户添加页面</title>
</head>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
  function add(){
      var username = $("#username").val();
      var password = $("#password").val();
      var email = $("#email").val();
      $.ajax({
          url:"<%=basePath%>user/add",
          type:"post",
          data:{"username":username,"password":password,"email":email},
          success:function (obj){
              if(obj.success){
                  alert("添加成功");
                  location.href = "<%=basePath%>user/list";
              }else{
                  alert("添加失败");
              }
          },
          dataType:"json"
      })
  }
</script>
<body>
    <form>
      用户名：<input type="text" id="username" name="username"><br>
      密码：<input type="text" id="password" name="password"><br>
      邮箱：<input type="text" id="email" name="email"><br>
          <button type="button" onclick="add()">添加</button>
    </form>
</body>
</html>
