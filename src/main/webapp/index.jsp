<%@ page import="service.DatabaseService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>DBMS</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>
<h2>Indexes:</h2>
<table align="center">
    <tr>
        <th>Name</th>
        <th>Index Attribute</th>
    </tr>
    <c:forEach var="indx" items='<%= DatabaseService.getInstance().getIndexes(request.getParameter("dbName"),request.getParameter("tableName"))%>'>
        <tr>
            <td><input type="text" name="name" value="${indx.getName()}" size="15" readonly/></td>
            <td><input type="text" name="atrbs" value="${indx.getIndexAttributes().get(0)}" size="15" readonly/></td>
        </tr>							
    </c:forEach>

</table>

</body>
</html>
