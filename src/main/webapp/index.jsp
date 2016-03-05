<%@ page import="service.DatabaseService" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28-02-16
  Time: 17:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>DBMS</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script>
      function showTables(id) {
        $.ajax({
          type: "POST",
          url:"/TableServlet",
          data: "databaseNmae="+id
        })
      }
    </script>

  </head>
  <body>
    <table>
      <tr>
        <th>Databases:</th>
      </tr>
      <c:forEach var="db" items='<%=DatabaseService.getInstance().getDatabases()%>'>
        <tr>
          <td id="selectedDatabase"><input type="text" name="name" value="${db.getName()}" size="15" readonly/></td>
          <td>
            <form action="table.jsp" method="post">
              <input type="submit" name="selectButton" value="Show Tables" />
              <input type="hidden" name="dbName" value="${db.getName()}" />
            </form>
          </td>
          <td>
            <form action="/DatabaseDeleteServlet" method="post">
              <input type="submit" name="deleteButton" value="Delete Database" />
              <input type="hidden" name="databaseName" value="${db.getName()}" />
            </form>
          </td>
        </tr>
      </c:forEach>

    </table>


    <form action="addDatabase.jsp" method="get">
      <input  type="submit" name="addDatabase" value="Add Database" /> </form>



  </body>
</html>
