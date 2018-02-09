<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Flight</title>
</head>
<body>
<form method="post" action="AddFlightServlet"> 
		FlightNo<input type="text" name="flightNo">
		Airline Name <input type="text" name="name">
		origin<input type="text" name="origin">
		Destination<input type="text" name="destination">
		<input type="submit" name="submit" value="Add Flight">
		 
	</form>
</body>
</html>