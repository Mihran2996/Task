<%@ page import="model.User" %>
<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="model.TaskStatus" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: MIHRAN
  Date: 15.06.2020
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%User user = (User) request.getAttribute("user");%>
<h1>Welcome home page ` <%=user.getName()%>
</h1>
<%List<Task> task = (List<Task>) request.getAttribute("task");%>
<% if (task != null) {%>
<table border="1px solid black">
    <caption>My tasks</caption>
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
<h2>You can change status</h2>
Please Select Status`  <%=Arrays.toString(TaskStatus.values())%>
<form action="/home" method="get">
    <input type="number" placeholder="taskId" name="id"><br>
    <br>
    <input type="text" placeholder="status" name="status"><br>
    <br>
    <input type="submit" value="change">
</form>
<br>
<a href="index.jsp" title="login">return</a>
</table>
</body>
</html>
