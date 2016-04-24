<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="service.DatabaseService" %>
<%@ page import="model.Attribute" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sql editor</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
</head>
<body>
    <% List<Attribute> attributes = DatabaseService.getInstance().getAttributes(request.getParameter("dbName"),request.getParameter("tableName"));  %>

    <h2>Perform the select operation:</h2>
    <form action="/SelectServlet" method="post">
        SELECT: <!---->
        <c:forEach var="attribute" items='<%= attributes%>'>
            <input type="checkbox" class="selectedAttribute" name="selectedAttribute" value="${attribute.getName()}"> ${attribute.getName()}
        </c:forEach><br><br>
        FROM: <!---->
        <label>${request.getParameter("tableName")}</label><br><br>
        WHERE:

        number of where attributes <input type="text" name="wheres" id ="number" value="0" required/><br><br>
        <fieldset name="filterComponents" id="components">
        </fieldset><br><br>

        <input type="submit" name="executeButton" value="Execute" />

        <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
        <input type="hidden" name="tableName" value="<%= request.getParameter("tableName")%>" /><br><br>

    </form>




    <h2>The result of select operation:</h2>


    <script>
        $(document).ready(function() {
            $('#number').change(populateWhere);
        });
        function populateWhere() {
            var html = '';
            var no = document.getElementById("number").value;
            for(var i = 0; i < no; i++) {
                html += getWhereTemplate(i);
            }
            $('#components').html(html);
        }
        function getWhereTemplate(i) {
            var where =
                    '<select name="filter_attributes">'
                    +   '<c:forEach var="attribute" items="<%= attributes%>">'
                    +       '<option value="${attribute}">${attribute.name}</option>'
                    +   '</c:forEach>'
                    +'</select>'
                    +'<select name="filter_operation_types">'
                    +   '<option value="EQUAL">=</option>'
                    +   '<option value="LESS_THAN"><</option>'
                    +   '<option value="GREATER_THAN">></option>'
                    +   '<option value="LESS_OR_EQUAL"><=</option>'
                    +   '<option value="GREATER_OR_EQUAL">>=</option>'
                    +   '<option value="NOT_EQUAL">!=</option>'
                    +'</select>'
                    +'<input type="text" name="filter_values" value="" required/><br>';
            return where;
        }
    </script>

</body>
</html>
