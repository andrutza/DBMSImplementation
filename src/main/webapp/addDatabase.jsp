<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04-03-16
  Time: 19:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add database form:</h2>
    <form action="/DatabaseServlet" method="post">
       Database Name: <input type="text" name="databaseName" value="" />
        <input type="submit" name="saveButton" value="Save" />
    </form>

</body>
</html>
