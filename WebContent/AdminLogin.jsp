<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
<style type="text/css">
	#login{
		margin-top:15%;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="login">
<form method="post" action="AdminLogin">
<table align="center" cellspacing="20">
<tr><td><label>Password</label></td><td><input type="password" name="password"></td></tr>
<tr><td></td><td><input type="submit" name="submit" value="login"></td></tr>
</table>
</form>
</div>
</body>
</html>