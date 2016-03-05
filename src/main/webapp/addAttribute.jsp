<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add attribute form:</h2>
    <form action="/AttributeAddServlet" method="post">
        Attribute Name: <input type="text" name="attributeName" value="" />
        Attribute Type:
        <select name="attributeType">
            <option>int</option>
            <option>string</option>
            <option>double</option>
        </select>
        <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
        <input type="hidden" name="tableName" value="<%= request.getParameter("tableName")%>" />
        <input type="submit" name="saveButton" value="Save" />
        <input type="submit" name="cancelButton" value="Cancel" />
    </form>

</body>
</html>
