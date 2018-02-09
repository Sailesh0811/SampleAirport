<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Crew Member</title>
</head>
<body>
	<form method="post" action="AddCrewMemberServlet"> 
		Name<input type="text" name="name">
		Gender<select name="gender">
			<option value="male">Male</option>
			<option value="Female">Female</option>
		</select>
		Contact <input type="text" name="contactno">
		DOB<input type="text" name="dob">
		Designation<select name="designation">
			<option value="P">Pilot</option>
			<option value="A">Air Hostess</option>
			<option value="GS">Ground Staff</option>
		</select>
		Password<input type="password" name="password">
		Language<input typr="text" name="languages" placeholder="seperated by ,">
		<input type="submit" name="submit" value="Add Crew">
		 
	</form>
</body>
</html>