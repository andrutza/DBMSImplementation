<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>
    <h2>Add attribute:</h2>
    <form action="/AttributeAddServlet" method="post">
        Attribute Name: <input type="text" name="attributeName" value="" required/>
        Attribute Type:
        <select name="attributeType">
            <option>int</option>
            <option>string</option>
            <option>double</option>
        </select>
        Length: <input type="text" name="length" value="" />
        Is Primary Key: <INPUT TYPE="CHECKBOX" NAME="checkPrimary" />
        Can be Null: <INPUT TYPE="CHECKBOX" NAME="checkNull" VALUE="checkNull" />
        Is Unique Key: <INPUT TYPE="CHECKBOX" NAME="checkUnique" VALUE="checkUnique" />
        <input type="hidden" name="dbName" value="<%= request.getParameter("dbName")%>" />
        <input type="hidden" name="tableName" value="<%= request.getParameter("tableName")%>" />
        <input type="submit" name="saveButton" value="Save" />
    </form>

</body>
</html>
