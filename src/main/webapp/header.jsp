<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String database = request.getParameter("dbName");
    String table = request.getParameter("tableName");
%>
<div id="header">
<a href="database.jsp">Databases</a>
<c:if test="<%= database != null%>">
    &gt;&gt;
    <a href="table.jsp?dbName=<%=database%>"><%=database%></a>
    <c:if test="<%= table != null%>">
        &gt;&gt;
        <a href="attribute.jsp?dbName=<%=database%>&tableName=<%=table%>"><%=table%></a>
    </c:if>
</c:if>
</div>
<br><br><br>