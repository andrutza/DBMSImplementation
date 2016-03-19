<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="service.DatabaseService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>
    <h2>Add foreign key:</h2>
    <form action="/ForeignKeyAddServlet" method="post">
        Attribute:
        <select name="attribute">
            <c:forEach var="attribute" items='<%= DatabaseService.getInstance().getAttributes(request.getParameter("dbName"),request.getParameter("tableName"))%>'>
                <option>${attribute.name}</option>
            </c:forEach>
        </select>
        Referenced Table:
        <select name="refTable" id="refTable"
                onchange="loadAttributes('<%= request.getParameter("dbName") %>', this.options[this.selectedIndex].text)">
            <c:forEach var="table" items='<%= DatabaseService.getInstance().getTables(request.getParameter("dbName"))%>'>
                <option>${table.name}</option>
            </c:forEach>
        </select>
        <select name="refAttribute" id="refAttribute" readonly>
        </select>
        <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
        <input type="hidden" name="tableName" value="<%= request.getParameter("tableName")%>" />
        <input type="submit" name="saveButton" value="Save" />
    </form>
    <script>
        $(document).ready(function(){
            function loadAttributes(dbName, selectedText){
                $.ajax({
                    type: "POST",
                    url:"/AttributesServlet",
                    data: {"dbName":dbName, "tableName":selectedText},
                    success: function(data, textStatus, jqXHR)
                    {
                        $("#refAttribute").html(data);
                    }
                })
            }
            var select = document.getElementById("refTable");
            loadAttributes('<%= request.getParameter("dbName") %>', select.options[select.selectedIndex].text)
        });
    </script>
</body>
</html>
