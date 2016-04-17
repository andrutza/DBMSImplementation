<%@ page import="service.DatabaseService" %>
<%@ page import="model.Record" %>
<%@ page import="model.Attribute" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ForeignKey" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>DBMS</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>
<h2>Records:</h2>
<table align="center">
    <% List<Attribute> attributes = DatabaseService.getInstance().getAttributes(request.getParameter("dbName"),request.getParameter("tableName"));  %>
    <c:forEach var="attribute" items='<%= attributes %>'>
        <th>${attribute.getName()}</th>
    </c:forEach>

    <c:forEach var="rec" items='<%= DatabaseService.getInstance().getRecords(request.getParameter("dbName"),request.getParameter("tableName"))%>'>
        <tr>
            <c:forEach var="attribute" items='<%= attributes %>'>
                <td><input type="text" name="recordValue" value="${rec.getValue(attribute)}" size="15" readonly/></td>
            </c:forEach>
            <td>
                <form action="${pageContext.request.contextPath}/RecordDeleteServlet" method="post">
                    <input type="submit" name="deleteButton" value="Delete Record" />
                    <input type="hidden" name="primaryKey" value="${rec.getStoreKey()}" />
                    <input type="hidden" name="tableName" value="<%= request.getParameter("tableName")%>" />
                    <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
                </form>
            </td>
        </tr>
    </c:forEach>

</table><br><br>
<form action="addRecord.jsp" method="post">
    <input type="submit" name="addRecButton" value="Add Record" />
    <input type="hidden" name="tableName" value="<%= request.getParameter("tableName")%>" />
    <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
</form>

</body>
</html>
