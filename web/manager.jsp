<%@ page import="java.util.Arrays" %>
<%@ page import="model.UserType" %>
<%@ page import="model.TaskStatus" %>
<%@ page import="manager.UserManager" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="manager.TaskManager" %>
<%@ page import="model.Task" %><%--
  Created by IntelliJ IDEA.
  User: MIHRAN
  Date: 15.06.2020
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager</title>

</head>
<body>
<h1>Welcome Manager page</h1>
<form action="/man" method="post">
    <h3>For user add</h3>
    Please Select User Type`  <%=Arrays.toString(UserType.values())%><br>
    <input type="text" placeholder="type" name="type"><br>
    <br>
    <input type="text" placeholder="name" name="name"><br>
    <br>
    <input type="text" placeholder="surname" name="surname"><br>
    <br>
    <input type="text" placeholder="email" name="email"><br>
    <br>
    <input type="password" placeholder="password" name="password"><br>
    <br>
    <input type="submit" value="click">
</form>
<%
    UserManager userManager = new UserManager();
    List<User> allUsers = userManager.getAllUsers();
    List<User> allUsers1 = (List<User>) request.getAttribute("user");
    if (allUsers != null) {%>
<table border="1px solid black">
    <caption>Users</caption>
    <tr>
        <th>id</th>
        <th>type</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>delete</th>
    </tr>
    <% for (User user : allUsers) {%>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getType()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getSurname()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><a href="/remove?id=<%=user.getId()%>">delete</a></td>
    </tr>
    <%}%>
    <%
        allUsers = allUsers1;
    %>
</table>
<%}%>
<br>
<h3>For task add</h3>
Please Select Status`  <%=Arrays.toString(TaskStatus.values())%>
<form action="/man" method="get">
    <input type="text" placeholder="status" name="status"><br>
    <br>
    <input type="text" placeholder="name" name="name"><br>
    <br>
    <input type="text" placeholder="description" name="description"><br>
    <br>
    <input type="date" placeholder="deadline" name="deadline"><br>
    <br>
    <input type="number" placeholder="user_id" name="userId"><br>
    <br>
    <input type="submit" value="click">
</form>
<br>

<h3>For change task user_id</h3>
<%
    TaskManager taskManager = new TaskManager();
    List<Task> allTask = taskManager.getAllTask();
    List<Task> allTask1 = (List<Task>) request.getAttribute("tasks");
%>
<% if (allTask != null) {%>

<table border="1px solid black">
    <caption>All tasks</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>description</th>
        <th>deadline</th>
        <th>status</th>
        <th>user_id</th>
    </tr>
    <% for (Task task1 : allTask) {%>
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
    <%
        allTask = allTask1;
    %>
</table>
<%}%>
<br>
Please select`<br>
<form action="/change" method="post">
    <input type="number" placeholder="TaskId" name="id"><br>
    <br>
    <input type="number" placeholder="User_id" name="userId">
    <input type="submit" value="change">
</form>
<br>
<a href="index.jsp" title="login">return</a>


</body>
</html>
