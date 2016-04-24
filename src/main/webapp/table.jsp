<%@ page import="service.DatabaseService" %>
<%@ page import="model.Attribute" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>DBMS</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
  </head>
  <body>
  <jsp:include page="header.jsp"/>
  <% String errorMsg = (String)request.getAttribute("errorMsg"); %>
  <c:if test="${errorMsg!=null}">
    <h2 style="color:red">${errorMsg}</h2>
  </c:if>
    <table id="tables">
      <tr>
        <th>Tables:</th>
      </tr>
      <c:forEach var="table" items='<%= DatabaseService.getInstance().getTables(request.getParameter("dbName"))%>'>
        <tr>
          <td><input type="text" name="name" value="${table.getName()}" size="15" readonly/></td>
          <td>
            <form action="${pageContext.request.contextPath}/TableDeleteServlet" method="post">
              <input type="submit" name="deleteTableButton" value="Delete Table" />
              <input type="hidden" name="tableName" value="${table.name}" />
              <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
            </form>
          </td>
          <td>
            <form action="attribute.jsp" method="post">
              <input type="submit" name="selectButton" value="Show Attributes" />
              <input type="hidden" name="tableName" value="${table.getName()}" />
              <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
            </form>
          </td>
          <td>
            <form action="addAttribute.jsp" method="post">
              <input type="submit" name="addAttributeButton" value="Add Attribute" />
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
          <td>
            <form action="addForeignKey.jsp" method="post">
              <input type="submit" name="addForeignKeyButton" value="Add Foreign Key" />
              <input type="hidden" name="tableName" value="${table.getName()}" />
              <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
            </form>
          </td>
          <td>
            <form action="index.jsp" method="post">
              <input type="submit" name="selectButton" value="Show Indexes" />
              <input type="hidden" name="tableName" value="${table.getName()}" />
              <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
            </form>
          </td>
          <td>
            <form action="addIndex.jsp" method="post">
              <input type="submit" name="addIndexButton" value="Add Index" />
              <input type="hidden" name="tableName" value="${table.getName()}" />
              <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
            </form>
          </td>
          <td>
            <form action="records.jsp" method="post">
              <input type="submit" name="selectButton" value="Show Records" />
              <input type="hidden" name="tableName" value="${table.getName()}" />
              <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
            </form>
          </td>
          <td>
            <form action="query.jsp" method="get">
              <input  type="submit" name="query" value="Sql editor" id="queryButton"/>
              <input type="hidden" name="tableName" value="${table.getName()}" />
              <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
            </form>
          </td>
        </tr>
      </c:forEach>
    </table><br><br>

  </body>
</html>
