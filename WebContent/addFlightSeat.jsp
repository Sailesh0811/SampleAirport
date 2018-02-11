<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Flight Seat</title>
<style>
	#login{
		margin-top:15%;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="login">
<form method="post" action="AddFlightSeatServlet">
<table align="center" cellspacing="20">
<tr><td>Flight id</td><td> <input type="text" name="flightId"></td></tr>
<tr><td>Economyseat start no</td><td> <input type="text" name="startENo"></td></tr>
<tr><td>Economyseat end no</td><td> <input type="text" name="endENo"></td></tr>
<tr><td>BusinessSeat start no </td><td><input type="text"  name="startBNo"></td></tr>
<tr><td>Business seat end no</td><td> <input type="text" name="endBNo"></td></tr>
<tr><td></td><td><input type="submit" name="submit" value="Add"></td>
</tr>
</table>
</form>
</body>
</html>