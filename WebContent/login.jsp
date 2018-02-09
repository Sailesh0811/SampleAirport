<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<form method="post" action="LoginServlet">
<label>Customer Id</label><input type="text" name="customerId">
<label>Password</label><input type="password" name="password">
<input type="submit" value="login" name="login">
<a href="signUp.jsp">Click here to sign up</a>
</form>
</body>
</html>