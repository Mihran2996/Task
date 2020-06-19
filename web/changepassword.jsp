<%--
  Created by IntelliJ IDEA.
  User: MIHRAN
  Date: 19.06.2020
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ReestablishPassword</title>
</head>
<body>
<form action="/reestablish" method="get">
    <h3>Please input Email`</h3>
    <br>
    <input type="text" name="email" placeholder="Email">
    <br>
    <input type="submit" value="send">

    <br>
</form>
<%
    String str = "";
    if (session.getAttribute("msg") != null) {
        str = (String) session.getAttribute("msg");
        session.removeAttribute("msg");
    }
%>
<span style="color: red"><%=str%>
    </span>
</body>
</html>
