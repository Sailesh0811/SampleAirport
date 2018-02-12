<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leave Approve</title>
</head>
<body>
<jsp:include page="../header.jsp" />
<div id="search">
<c:choose>
	<c:when test="${check==0}">
		No leave Requests found
	</c:when>
	<c:when test="${check==1 }">
	<form method="post" action="LeaveApproveServlet">
		<table align="center" cellspacing="20">
			<tr><th>Crew Id</th><th>Date</th><th>No of days</th><th>Status</th><th>Approve</th><th>Deny</th></tr>
			<c:forEach var="leave" items="${leave}">
			<c:set value="approve" var="app"></c:set>
			<c:set value="deny" var="deny"></c:set>
				<tr>
				<td><c:out value="${leave.getCrewId()}"></c:out></td>
				<td><c:out value="${leave.getDate()}"></c:out></td>
				<td><c:out value="${leave.getNoOfDays()}"></c:out></td>
				<td><c:out value="${leave.getStatus()}"></c:out></td>
				<td><input type="radio" name="approve${leave.getId()}" value='<c:out value="${app}${leave.getId()}"></c:out>'></td>
				<td><input type="radio" name="approve${leave.getId()}" value='<c:out value="${deny}${leave.getId()}"></c:out>'></td>
				</tr>				
			</c:forEach>
			<tr><td></td><td></td><td></td><td></td><td></td><td><input type="submit" value="submit" name="Grant"></td></tr>
		</table>
		</form>
	</c:when>
	
</c:choose>
</div>
</body>
</html>