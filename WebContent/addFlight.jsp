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
	<table>
		<tr><td>FlightNo</td><td><input type="text" name="flightNo"></td></tr>
		<tr><td>Airline Name <td><input type="text" name="name"></td></tr>
		<tr><td>origin</td><td><input type="text" name="origin"></td></tr>
		<tr><td>Destination</td><td><input type="text" name="destination"></td></tr>
		<tr><td></td><t><input type="submit" name="submit" value="Add Flight"></td></tr>
	</table> 
	</form>
</body>
</html>