<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add database form:</h2>
    <form action="/DatabaseAddServlet" method="post">
       Database Name: <input type="text" name="databaseName" value="" />
        <input type="submit" name="saveButton" value="Save" />
        <input type="submit" name="cancelButton" value="Cancel" />
    </form>

</body>
</html>
