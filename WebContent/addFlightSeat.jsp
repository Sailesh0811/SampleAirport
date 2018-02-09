<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Flight Seat</title>
</head>
<body>
<form method="post" action="AddFlightSeatServlet">
Flight id: <input type="text" name="flightId">
Economyseat start no <input type="text" name="startENo">
Economyseat end no <input type="text" name="endENo">
BusinessSeat start no <input type="text"  name="startBNo">
Business seat end no <input type="text" name="endBNo">
<input type="submit" name="submit" value="Add">

</form>
</body>
</html>