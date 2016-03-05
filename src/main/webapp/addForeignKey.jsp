<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="service.DatabaseService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add foreign key form:</h2>
    <form action="/ForeignKeyAddServlet" method="post">
        Attribute:
        <select name="attribute">
            <c:forEach var="attribute" items='<%= DatabaseService.getInstance().getAttributes(request.getParameter("dbName"),request.getParameter("tableName"))%>'>
                <option>${attribute.name}</option>
            </c:forEach>
        </select>
        Referenced Table:
        <select name="refTable">
            <c:forEach var="table" items='<%= DatabaseService.getInstance().getTables(request.getParameter("dbName"))%>'>
                <option>${table.name}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
        <input type="hidden" name="tableName" value="<%= request.getParameter("tableName")%>" />
        <input type="submit" name="saveButton" value="Save" />
        <input type="submit" name="cancelButton" value="Cancel" />
    </form>

</body>
</html>
