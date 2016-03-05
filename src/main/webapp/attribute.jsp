<%@ page import="service.DatabaseService" %>
<%@ page import="model.Attribute" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>DBMS</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
  </head>
  <body>
    <% List<Attribute> attributes = DatabaseService.getInstance().getAttributes(request.getParameter("dbName"),request.getParameter("tableName"));%>
    <h2>Attributes:</h2>
    <table>
      <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Length</th>
        <th>Is not null</th>
        <th>Is primary key</th>
        <th>Is unique key</th>
      </tr>
      <c:forEach var="attribute" items='<%= attributes%>'>
        <tr>
          <td><input type="text" name="name" value="${attribute.getName()}" size="15" readonly/></td>
          <td><input type="text" name="type" value="${attribute.getType()}" size="15" readonly/></td>
          <td><input type="text" name="length" value="${attribute.getLength()}" size="15" readonly/></td>
          <td><input type="text" name="name" value="${attribute.isNotNull()}" size="15" readonly/></td>
          <td><input type="text" name="name" value="${attribute.isPrimaryKey()}" size="15" readonly/></td>
          <td><input type="text" name="name" value="${attribute.isUniqueKey()}" size="15" readonly/></td>
        </tr>
      </c:forEach>

    </table>

  </body>
</html>
