<%@ page import="service.DatabaseService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>DBMS</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>
<body>
<h2>Foreign keys:</h2>
<table>
    <tr>
        <th>Name</th>
        <th>Referenced Table</th>
        <th>Referenced Attribute</th>
    </tr>
    <c:forEach var="fk" items='<%= DatabaseService.getInstance().getForeignKeys(request.getParameter("dbName"),request.getParameter("tableName"))%>'>
        <tr>
            <td><input type="text" name="name" value="${fk.getNames().get(0)}" size="15" readonly/></td>
            <td><input type="text" name="refTable" value="${fk.getRefTable()}" size="15" readonly/></td>
            <td><input type="text" name="references" value="${fk.getReferences().get(0).getName()}" size="15" readonly/></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
