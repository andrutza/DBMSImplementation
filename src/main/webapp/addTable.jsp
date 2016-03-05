<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add table form:</h2>
    <form action="/TableAddServlet" method="post">
       Table Name: <input type="text" name="tableName" value="" />
        <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
        <input type="submit" name="saveButton" value="Save" />
        <input type="submit" name="cancelButton" value="Cancel" />
    </form>

</body>
</html>
