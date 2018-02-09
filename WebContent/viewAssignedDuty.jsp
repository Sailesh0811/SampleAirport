<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Assigned Duty</title>
</head>
<body>
<h1>Your slot</h1>
<table>
<th>Scheduled Flight No</th>
<c:forEach var="some" items="${some}">
<tr>
<td><c:out value="${some}"></c:out>
</td>
</tr>
</c:forEach>

</table>
<h1>Other Crew Members
</h1>
<table>

<th>Crew Id</th>
<c:forEach var="s" items="${s}">
<tr>
<td><c:out value="${s}"></c:out>
</td>
</c:forEach>

</table>
</body>
</html>