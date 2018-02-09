<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<form method="post" action="CancelTicketServlet">
	<input type="text" name="pnr">
	<input type="submit" name="submit" value="submit">
</form>
</body>
</html>