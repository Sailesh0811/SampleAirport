<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crew | Login</title>
<script
  src="http://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
<script>
$(document).ready(function(){
	
	$('#crewId').change(
	function checkId(){
		console.log("fadfad")
		var reg = new RegExp('^\\d+$');
		var x=$('#crewId').val();
		var res=reg.test(x);
		console.log(res);
		if(!res){
			alert("enter a proper id");
			$('#crewId').focus();
		}
	});
});
</script>
</head>
<body>
<form method="post" action="CrewLoginServlet">
<input type="text" name="crewId" id="crewId" placeholder="crew id">
<input type="password" name="password" placeholder="password">
<input type="submit" name="submit" value="Login">
</form>
</body>
</html>