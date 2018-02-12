<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Slot Request</title>
</head>
<body>
	<form method="post" action="SlotRequestServlet">
		<label>Enter the date you want slot</label> <input type="text"
			name="date"> <input type="text" name="destination"> <input
			type="submit" value="search" name="slot">
	</form>
	<form method="post" action="InsertSlotServlet">
	<c:out value="${check}"></c:out>
	<c:set var="check" scope="session" value="${check}" />
	<c:if test="${check}">
	<h1>Available flights</h1>
		<c:forEach var="some" items="${some}">
		<input type="radio" name="flightNo" value="${some}"><c:out value="${some}"></c:out>
		</c:forEach>
		<input type="submit" value="Slot">
	</c:if>
	
	</form>
</body>
</html>