<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking success</title>
</head>
<body>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <jsp:include page="header.jsp" />
 	<div class="container">
	<div class="alert alert-success" role="alert">
						Your last booked pnr no <c:out value="${sessionScope.pnr}"></c:out> 
						You will be redirected to home page in 2 minutes
						
					</div>
					</div>
					<script type="text/javascript">
						$(document).ready(function(){
							setTimeout(function(){
								window.location="IndexServlet"
							},10000)
						});
					</script>
</body>
</html>