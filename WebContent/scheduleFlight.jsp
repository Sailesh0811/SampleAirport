<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule Flight</title>
</head>
<body>
<jsp:include page="header.jsp" />
<form method="post" action ="ScheduleFlightServlet">
Flight id:<input type="text" name="flightId">
Date:<input type="text" name="date">
Departure Time <input type="text" name="dtime">
Arrival Time <input type="text" name="atime">
Economy seats <input type="text" name="eNo">
Economy Fare <input type="text" name="eFare">
Business seat <input type="text" name="bNo">
Business Fare <input type="text" name="bFare">
<input type="submit" name="submit" value="Schedule">
</form>
</body>
</html>