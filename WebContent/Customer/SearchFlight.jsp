<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../header.jsp" />
<div id="sidenav">
	<ul>
	<li><a href="SearchServlet">Search and Book</a></li>
	<li><a href="viewHistory.jsp">View History</a></li>
	<li><a href="cancelTicket.jsp">Cancel Ticket</a></li>
	<li><a href="checkIn.jsp">Check In</a></li>
	</ul>
</div>
<c:choose>
	<c:when test="${f!=0 }">
	<div id="search">
	<form method="post" action="SearchServlet">
	<table cellspacing="10" align="center">
	<tr><td><label>From</label></td><td>
		<select name="from">
		<c:forEach var="from" items="${from}">
			<option value="${from}"><c:out value="${from}"></c:out></option>
		</c:forEach>
	</select>
	</td></tr>
	<tr><td><label>To</label></td><td>
		<select name="to">
		<c:forEach var="to" items="${to}">
			<option value="${to}"><c:out value="${to}"></c:out></option>
		</c:forEach>
	</select>
	
	</td></tr>
	<tr><td><label>Date</label></td><td><input type="date" name="date" ></td></tr>
	<tr><td><label>Seats</label></td><td>
		<select name="seats">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>
	
	</td></tr>
	<tr><td><label>Class</label></td>
	<td>
	<select name="class">
		<option value="null">Select Class</option>
		<option value="EC" >EC</option>
		<option value="BC">BC</option>		
	</select></td></tr>
	<tr><td>Filter</td><td><select name="filter">
	<option value="LOWRATE">LowRate</option>
	<option value="HIGHRATE">HighRate</option>
	<option value="AIRLINE">Airline</option>
	<option value="LDEPARTURE">Early Departure</option>
	<option value="HDEPARTURE">Late Departure</option>
</select></td></tr>
	
	<tr><td></td><td><input type="submit" name="submit"></td></tr>
	</table>
</form>
</div>
	</c:when>
	<c:when test="${f==0}">
	<div id="flightResult">
		<form action="BookServlet" method="post">
	<table cellspacing="30" align="center" >
	<tbody>
	<tr>
	<th>Select</th>
	<th>Flight No</th>
	<th>Airline name</th>
	<th>Departure</th>
	<th>Arrival</th>
	<th>Price</th></tr>
	<c:forEach  items="${flight}" var="flight">
		<tr><td><input type="radio" name="scheduleFlightNo" value='<c:out value="${flight.getAvailableFlightNo()}"></c:out>'>
		<td><c:out value="${flight.getFlightNo()}"></c:out></td>
		<td><c:out value="${flight.getAirline()}"></c:out></td>
		<td><c:out value="${flight.getDeparture()}"></c:out></td>
		<td><c:out value="${flight.getArrival()}"></c:out></td>
		<td><c:out value="${flight.getPrice()}"></c:out></td></tr>	
	</c:forEach>		
	<tr><td></td><td></td><td></td><td></td><td></td>
	<td><input type="submit" name="submit" value="Book"></td></tr>
	</tbody>
	</table>
</form>
	</div>
	</c:when>
	<c:when test="${f==1}">
		Sorry No flights found!!
	</c:when>

</c:choose>





</body>
</html>