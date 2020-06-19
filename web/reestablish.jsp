<%--
  Created by IntelliJ IDEA.
  User: MIHRAN
  Date: 19.06.2020
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reestablish</title>
</head>
<body>
<%
    int code=0;
    if (session.getAttribute("codemsg") != null) {
        code = (int) session.getAttribute("codemsg");
        session.removeAttribute("codemsg");
    }
%>
<span style="color: green"><%=code%></span>

<form action="/reestablish" method="post">
    <h3>Please input Password`</h3>
    <br>
    <input type="number" name="code" placeholder="Code">
    <br>
    <input type="text" name="pass1" placeholder="Password">
    <br>
    <input type="password" name="pass2" placeholder="Password">
    <br>
    <input type="submit" value="send">

    <br>
    <%
        String msg = "";
        if (session.getAttribute("message") != null) {
            msg = (String) session.getAttribute("message");
            session.removeAttribute("message");
        }
    %>
    <span><%=msg%>
    </span>

</form>
</body>
</html>
