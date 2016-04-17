<%@ page import="service.DatabaseService" %>
<%@ page import="model.Attribute" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ForeignKey" %>
<%@ page import="model.Table" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>
    <h2>Add record:</h2>
    <form action="/RecordAddServlet" method="post">
        <% List<Attribute> attributes = DatabaseService.getInstance().getAttributes(request.getParameter("dbName"),request.getParameter("tableName"));%>
        <% List<ForeignKey> foreignKeys = DatabaseService.getInstance().getForeignKeys(request.getParameter("dbName"),request.getParameter("tableName"));%>
        <% Table table = DatabaseService.getInstance().findTableByName(request.getParameter("dbName"),request.getParameter("tableName")); %>
        <c:forEach var="attribute" items='<%= attributes%>'>
            <b>${attribute.getName()}</b>:
            <c:set var="attrName" value="${attribute.getName()}"/>
            <c:set var="table" value="<%= table %>"/>
            <c:set var="foreignKey" value='<%= table.getForeignKey((String)pageContext.getAttribute("attrName")) %>'/>
            <c:choose>
            <c:when test="${foreignKey!=null}">
                <select name="${foreignKey.getNames().get(0)}">
                    <c:forEach var="value" items='<%= DatabaseService.getInstance().getRecordsOfAttribute(request.getParameter("dbName"),table.getForeignKey((String)pageContext.getAttribute("attrName")).getRefTable(), (String)pageContext.getAttribute("attrName"))%>'>
                        <option>${value}</option>
                    </c:forEach>
                </select><br><br>
            </c:when>
            <c:otherwise>
                <input type="text" name="${attribute.getName()}" value="" required/><br><br>
            </c:otherwise>
            </c:choose>
        </c:forEach><br><br>

		<input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
        <input type="hidden" name="tableName" value="<%= request.getParameter("tableName")%>" />
        <input type="submit" name="saveButton" value="Save" />
    </form>

</body>
</html>
