<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	if (session.getAttribute("customerId") != null) {

		System.out.println("Customer id" + (String) session.getAttribute("customerId"));

		//response.sendRedirect("book.jsp");			
	} else {
		session.setAttribute("url", "viewHistory.jsp");
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View History</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script>
	function checkOption() {
		var x = $('#option').val();
		if (x == "1") {
			$('#pnr').append('<input type="text" name="pnr">')
		} else {
			$('#pnr').remove();
		}
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="sidenav">
		<ul>
			<li><a href="SearchServlet">Search and Book</a></li>
			<li><a href="viewHistory.jsp">View History</a></li>
			<li><a href="cancelTicket.jsp">Cancel Ticket</a></li>
			<li><a href="checkIn.jsp">Check In</a></li>
		</ul>
	</div>
	<div id="search">
		<form action="ViewHistoryServlet" method="post">
			<select name="choice" onChange="checkOption()" id="option">
				<option value="0">Select Option</option>
				<option value="1">Search By PNR</option>
				<option value="2">View All History</option>
			</select>
			<div id="pnr"></div>
			<input type="submit" name="submit" value="Search">
		</form>
		<c:choose>
			<c:when test="${check==1}">
				<table align="center" cellspacing="20">
					<tr>
						<th>Pnr No</th>
						<th>Status</th>
						<th>Flight No</th>
					</tr>

					<c:forEach items="${transaction}" var="transaction">
						<tr>
							<td><c:out value="${transaction.getPnrNo()}"></c:out></td>
							<td><c:out value="${transaction.getStatus()}"></c:out></td>
							<td><c:out value="${transaction.getAvailableFlightNo()}"></c:out>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
		No transactions found
	</c:otherwise>
		</c:choose>
	</div>
</body>
</html>