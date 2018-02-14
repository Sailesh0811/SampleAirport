<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	if (session.getAttribute("customerId") != null) {

		System.out.println("Customer id" + (String) session.getAttribute("customerId"));

		//response.sendRedirect("book.jsp");			
	} else {
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
	<script>
$(document)
.ready(
		function function_name() {
			$('.btnCancel')
					.click(
							function() {
								
								
								var pnr = $(this).val();
								console.log(pnr)
								$
										.ajax({
											type : "POST",
											url : "CancelTicketServlet",
											data : {
												pnr:pnr
											},
											success : function(
													response) {
												console.log(response);
												var json = JSON.parse(response);
												console.log(json)
												if (json.status == "success") {
													console
															.log("error")
													$('#alert')
															.append(
																	'<div class="alert alert-danger" role="alert">Invalid Username or password</div>');
												} else {
													$('#alert').append(
															'<div class="alert alert-info" role="alert">Your ticket is cancelled successfully</div>');
													setTimeout(function(){
														location.reload();
													},3000)
												}
											}
										})
							})
		});
</script>
<div class="container">
	<div id="alert">
	
	</div>
	<form>
		<table class="table">
			<tr>
				
				<th>Pnr No</th>
				<th>Status</th>
				<th>Flight No</th>
				<th>Cancel</th>
			</tr>

			<c:forEach items="${transaction}" var="transaction">
				<tr>
						
					<td><c:out value="${transaction.getPnrNo()}"></c:out></td>
					<td><c:out value="${transaction.getStatus()}"></c:out></td>
					<td><c:out value="${transaction.getAvailableFlightNo()}"></c:out></td>
					<td>
										<button type="button" class="btn btn-primary btnCancel" name="scheduleFlightNo" value='<c:out value="${transaction.getPnrNo()}"></c:out>'>Cancel</button></td>
				</tr>
			</c:forEach>
		</table>
		
	</form>
</div>
</body>
</html>