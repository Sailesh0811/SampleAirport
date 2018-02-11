<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%
    if(session.getAttribute("customerId")!=null) {
		
		System.out.println("Customer id"+(String)session.getAttribute("customerId"));
		
		//response.sendRedirect("book.jsp");			
	}
	else {
		session.setAttribute("url", "cancelTicket.jsp");
		response.sendRedirect("login.jsp");
	}
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel Ticket</title>
</head>
<body>
<jsp:include page="header.jsp" />
<form method="post" action="CancelTicketServlet">
	<table align="center" cellspacing="20">
					<tr>
						<th>Select</th>
						<th>Pnr No</th>
						<th>Status</th>
						<th>Flight No</th>
					</tr>

					<c:forEach items="${transaction}" var="transaction">
						<tr>
							<td><input type="radio" name="pnr" value="<c:out value="${transaction.getPnrNo()}"></c:out>">
							<td><c:out value="${transaction.getPnrNo()}"></c:out></td>
							<td><c:out value="${transaction.getStatus()}"></c:out></td>
							<td><c:out value="${transaction.getAvailableFlightNo()}"></c:out>
						</tr>
					</c:forEach>
				</table>
	<input type="submit" name="submit" value="submit">
</form>
</body>
</html>