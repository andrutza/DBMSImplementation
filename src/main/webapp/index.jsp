<%@ page import="service.DatabaseService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>DBMS</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
  </head>
  <body>
    <table>
      <tr>
        <th>Databases:</th>
      </tr>
      <c:forEach var="db" items='<%=DatabaseService.getInstance().getDatabases()%>'>
        <tr>
          <td id="selectedDatabase">
            <label>${db.name}</label>
            <%--<input type="text" name="name" value="" size="15" readonly/>--%>
          </td>
          <td>
            <form action="table.jsp" method="post">
              <input type="submit" name="selectButton" value="Show Tables" />
              <input type="hidden" name="dbName" value="${db.name}" />
            </form>
          </td>
          <td>
            <form action="addTable.jsp" method="post">
              <input type="submit" name="addTableButton" value="Add Table" />
              <input type="hidden" name="dbName" value="${db.name}" />
            </form>
          </td>
          <td>
            <form action="${pageContext.request.contextPath}/DatabaseDeleteServlet" method="post">
              <input type="submit" name="deleteButton" value="Delete Database" />
              <input type="hidden" name="databaseName" value="${db.name}" />
            </form>
          </td>
        </tr>
      </c:forEach>
    </table>

    <form action="addDatabase.jsp" method="get">
      <input  type="submit" name="addDatabase" value="Add Database" />
    </form>

  </body>
</html>
