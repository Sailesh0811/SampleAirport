<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Slot assign</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div id="search">
	<form method="get" action="SlotAssignServlet">
	<input type="text" name="scheduleFlightNo">
		<input type="submit" name="submit" value="submit">
		
	</form>
		<form method="post" action="SlotAssignServlet">
			
			<c:set value="approve" var="app"></c:set>
				<c:set value="deny" var="deny"></c:set>
				<c:forEach var="slot" items="${slot}">
			<c:choose>
				
				
					<c:when test="${slot.getStatus()=='COMP'}">
						
						<table align="center" cellspacing="20">
							<tr>
								<th>Crew Id</th>
								<th>Designation</th>
								<th>Type</th>
								<th>Assign</th>
								<th>Deny</th>
							</tr>
							<tr>
								<td><c:out value="${slot.getCrewId() }"></c:out></td>
								<td><c:out value="${slot.getDesignation() }"></c:out></td>
								<td><c:out value="${slot.getStatus() }"></c:out>
								<td><input type="radio" name="approve${slot.getCrewId()}" value='<c:out value="${app}${slot.getCrewId()}"></c:out>'></td>
								<td><input type="radio" name="approve${slot.getCrewId()}" value='<c:out value="${deny}${slot.getCrewId()}"></c:out>'></td>
							</tr>
						</table>
					</c:when>
					<c:when test="${slot.getStatus()=='NON'}">
						
						<table>
							<tr>
								<th>Crew Id</th>
								<th>Designation</th>
								<th>Type</th>
								<th>Assign</th>
								<th>Deny</th>
							</tr>
							<tr>
								<td><c:out value="${slot.getCrewId() }"></c:out></td>
								<td><c:out value="${slot.getDesignation() }"></c:out></td>
								<td><c:out value="${slot.getStatus() }"></c:out>
								<td><input type="radio" name="approve${slot.getCrewId()}" value='<c:out value="${app}${slot.getCrewId()}"></c:out>'></td>
								<td><input type="radio" name="approve${slot.getCrewId()}" value='<c:out value="${deny}${slot.getCrewId()}"></c:out>'></td>
							</tr>
						</table>
					</c:when>
					<c:when test="${slot.getStatus()=='NOR'}">
						
						<table>
							<tr>
								<th>Crew Id</th>
								<th>Designation</th>
								<th>Type</th>
								<th>Assign</th>
								
							</tr>
							<tr>
								<td><c:out value="${slot.getCrewId() }"></c:out></td>
								<td><c:out value="${slot.getDesignation() }"></c:out></td>
								<td><c:out value="${slot.getStatus() }"></c:out>
								<td><input type="radio" name="approve${slot.getCrewId()}" value='<c:out value="${app}${slot.getCrewId()}"></c:out>'></td>
								<td><input type="radio" name="approve${slot.getCrewId()}" value='<c:out value="${deny}${slot.getCrewId()}"></c:out>'></td>
							</tr>
						</table>
					</c:when>
				
			</c:choose>
			</c:forEach>
			<input type="submit" name="submit" value="submit">
		</form>
	</div>
</body>
</html>