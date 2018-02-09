<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LeaveRequest</title>
</head>
<body>

	<c:choose>
		<c:when test="${type eq 0}">
			<form method="post" action="CompensationLeaveServlet">
				<c:forEach begin="1" end="${no}" var="dates">
					<input type="text" name="some${dates}">
				</c:forEach>
				
				
				<input type="submit" value="Submit" name="submit">
			</form>
		</c:when>		
		<c:otherwise>
			<form method="post" action="LeaveRequestServlet">
				<label>Type of leave</label> <select name="leaveType">
					<option value="COMP">Compensation</option>
					<option value="NON">Non-Compensation</option>
				</select> <input type="text" name="noOfDays"> <input type="text"
					name="date"> <input type="submit" value="Leave"
					name="leave">
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>