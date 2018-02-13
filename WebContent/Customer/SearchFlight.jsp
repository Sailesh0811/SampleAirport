<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>

</head>
<body>
	<jsp:include page="header.jsp" />
	<script>
		$(document).ready(function() {

		})
	</script>


	<c:choose>

		<c:when test="${flight ne null}">
			<div class="container">
				<div class="notification">
					<div class="alert alert-success" role="alert">
						Totally
						<c:out value="${flight.size()}"></c:out>
						flights have been found
					</div>
				</div>
			</div>
			<div class="container">
			
				<form action="BookServlet" method="post">

					<table class="table">
						<thead>
							<tr>
								
								<th>Flight No</th>
								<th>Airline name</th>
								<th>Departure</th>
								<th>Arrival</th>
								<th>Price</th>
								<c:if test="${sessionScope.customer ne null }">								
								<th>Book</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${flight}" var="flight">
								<tr>
									
									<td><c:out value="${flight.getFlightNo()}"></c:out></td>
									<td><c:out value="${flight.getAirline()}"></c:out></td>
									<td><c:out value="${flight.getDeparture()}"></c:out></td>
									<td><c:out value="${flight.getArrival()}"></c:out></td>
									<td><c:out value="${flight.getPrice()}"></c:out></td>
									<c:if test="${sessionScope.customer ne null }">
									<td>
										<button type="submit" class="btn btn-primary" name="scheduleFlightNo" value='<c:out value="${flight.getAvailableFlightNo()}"></c:out>'>Book
										</button>
										</c:if>
								</tr>
								
							</c:forEach>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</c:when>
		<c:when test="${flight eq null}">
		<div class="notification">
					<div class="alert alert-danger" role="alert">
						Sorry No flights Found
					</div>
				</div>
	</c:when>

	</c:choose>





</body>
</html>