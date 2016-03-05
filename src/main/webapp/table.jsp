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
        <th>Tables:</th>
      </tr>
      <c:forEach var="table" items='<%= DatabaseService.getInstance().getTables(request.getParameter("dbName"))%>'>
        <tr>
          <td><input type="text" name="name" value="${table.getName()}" size="15" readonly/></td>
          <td>
            <form action="attribute.jsp" method="post">
              <input type="submit" name="selectButton" value="Show Attributes" />
              <input type="hidden" name="tableName" value="${table.getName()}" />
              <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
            </form>
          </td>
          <td>
            <form action="foreignKeys.jsp" method="post">
              <input type="submit" name="selectButton" value="Show Foreign Keys" />
              <input type="hidden" name="tableName" value="${table.getName()}" />
              <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
            </form>
          </td>
        </tr>
      </c:forEach>

    </table>

  </body>
</html>