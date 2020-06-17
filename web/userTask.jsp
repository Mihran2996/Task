<%@ page import="model.User" %>
<%@ page import="model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: MIHRAN
  Date: 17.06.2020
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserTask</title>
</head>
<body>
<%User user = (User) request.getAttribute("user");%>
<h1>Welcome ` to <%=user.getName()%> task page
</h1>
<%List<Task> task = (List<Task>) request.getAttribute("task");%>
<% if (task != null) {%>
<table border="1px solid black">
    <caption>tasks</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>description</th>
        <th>deadline</th>
        <th>status</th>
        <th>user_id</th>
    </tr>
    <% for (Task task1 : task) {%>
    <tr>
        <td><%=task1.getId()%>
        </td>
        <td><%=task1.getName()%>
        </td>
        <td><%=task1.getDescription()%>
        </td>
        <td><%=task1.getDeadline()%>
        </td>
        <td><%=task1.getStatus()%>
        </td>
        <td><%=task1.getUserId()%>
        </td>
    </tr>

    <%}%>
</table>


<% } %>
</body>
</html>
