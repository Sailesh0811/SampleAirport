<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
<div class="notification">
	<div class="alert alert-info" role="alert">
						Enter <c:out value="${seats}"></c:out> passengers name to complete the booking
					</div>
</div>
</div>
<div class="container">

<form action="ValidateBookServlet" method="post">
	<table class="table">
			<thead>
			<tr>
				<th>S No</th>
				<th>Passenger Name</th>
			</tr>	
			</thead>	
			<tbody>
			<c:forEach var="seats" begin="1"  end="${seats}">
			<tr><td>
				<c:out value="${seats}"></c:out> 
			</td><td>
				<input type="text" name=passenger<c:out value="${seats}"></c:out>> 
			</td></tr>
			</c:forEach>		
		</tbody>
	</table>
	<button type="submit" class="btn btn-primary">Make Payment</button>	
</form>	

</div> 
</body>
</html>