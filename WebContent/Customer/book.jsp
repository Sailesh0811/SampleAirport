<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
    	int seats;
    	session = request.getSession();
    	seats=Integer.parseInt((String)session.getAttribute("seats"));
    	request.setAttribute("seats", seats);
    	out.println(seats);
    	out.println((String)session.getAttribute("cls"));
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book</title>
</head>
<body>
<jsp:include page="../header.jsp" />
<form action="ValidateBookServlet" method="post">
	<table cellspacing="10" align="left" id="search">
		<tbody>
			<tr>
				<th>Passenger Name</th>
			</tr>		
			<c:forEach var="seats" begin="1"  end="${seats}">
			<tr><td>
				<input type="text" name='<c:out value="${seats}"></c:out>'> 
			</td></tr>
			</c:forEach>
		
		<tr><td><input type="submit" name="submit" value="book"></td></tr>
		</tbody>
	</table>	
</form>	 
</body>
</html>