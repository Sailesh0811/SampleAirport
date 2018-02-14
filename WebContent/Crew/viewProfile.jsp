<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
    
    %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Profile</title>
</head>
<body>
<table class="table">
<thead>
<tr>
<th>Name</th>
<th>Id</th>
<th>Contact number</th>
<th>Designation</th>
<th>Leave Count</th>
</tr>
</thead>
<tbody>
<tr>
<td name="name"> ${name}
</td>
<td name="id">${id}</td>
<td name="contactno">${contactno}</td>
<td name="designation">${designation}</td>
<td name="leavedays">${leavedays}</td>
</tr>
</tbody>
</table>
</body>
</html>