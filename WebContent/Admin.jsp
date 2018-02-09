<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="sidenav">
<ul>
<li><a href="addCrewMember.jsp">Add Crew Member</a></li>
<li><a href="addFlight.jsp">Add Flight</a></li>
<li><a href="addFlightSeat.jsp">Add Seats</a></li>
<li><a href="scheduleFlight.jsp">Schedule Flight</a></li>
<li><a href="SlotAssignServlet">Slot assign</a></li>
<li><a href="LeaveApproveServlet">Leave Grant</a></li>
</ul>
</div>
</body>
</html>