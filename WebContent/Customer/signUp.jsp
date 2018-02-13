<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signup</title>
<style type="text/css">
	#signup{
		margin-top: 15%;
	}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="signup">
<form action="SignUpServlet" method="post">
<table cellspacing="20" align="center">
<tr><td>Name</td><td><input type="text" name="name"  ></td></tr>
<tr><td>Gender</td><td><input type="text" name="gender"  ></td></tr>
<tr><td>Mobile Number</td><td><input type="text" name="contactNumber" ></td></tr>
<tr><td>Password</td><td><input type="password" name="password" ></td></tr>
<tr><td></td><td><input type="submit" name="submit" value="Sign Up"></td></tr>
</table>
</form>
</div>
</body>
</html>