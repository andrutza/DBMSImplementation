<%@ page import="service.DatabaseService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>
    <h2>Add index:</h2>
    <form action="/IndexAddServlet" method="post">
        Index Attribute:
        <select name="attribute">
            <c:forEach var="attribute" items='<%= DatabaseService.getInstance().getAttributes(request.getParameter("dbName"),request.getParameter("tableName"))%>'>
                <option>${attribute.name}</option>
            </c:forEach>
        </select>

        <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
        <input type="hidden" name="tableName" value="<%= request.getParameter("tableName")%>" />
        <input type="submit" name="saveButton" value="Save" />
    </form>

</body>
</html>
