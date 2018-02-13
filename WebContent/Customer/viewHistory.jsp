<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View History</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script>
	function checkOption() {
		var x = $('#option').val();
		if (x == "1") {
			$('#pnr').append('<input type="text" name="pnr">')
		} else {
			$('#pnr').remove();
		}
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div class="container">
		
		<c:choose>
			<c:when test="${check==1}">
				<table class="table">
					<thead>
					<tr>
						<th>Pnr No</th>
						<th>Status</th>
						<th>Flight No</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${transaction}" var="transaction">
						<tr>
							<td><c:out value="${transaction.getPnrNo()}"></c:out></td>
							<td><c:out value="${transaction.getStatus()}"></c:out></td>
							<td><c:out value="${transaction.getAvailableFlightNo()}"></c:out>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
		No transactions found
	</c:otherwise>
		</c:choose>
	</div>
</body>
</html>