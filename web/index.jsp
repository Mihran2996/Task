<%@ page import="java.util.Arrays" %>
<%@ page import="model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Welcome login page</h1>
<form action="/login" method="post">
    <input type="text" name="email" placeholder="Email"><br>
    <br>
    <input type="password" name="password" placeholder="Password"><br>
    <br>
    <input type="submit" value="click">
</form>
<%if (request.getAttribute("message") != null) {%>
<p style="color: red"><%=request.getAttribute("message")%>
</p>
<%}%>
</body>
</html>
