<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>
    <h2>Add table:</h2>
    <form action="/TableAddServlet" method="post">
       Table Name: <input type="text" name="tableName" value="" required/>
        <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
        <input type="submit" name="saveButton" value="Save" />
    </form>

</body>
</html>
