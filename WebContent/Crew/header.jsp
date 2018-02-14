<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Open+Sans"
	rel="stylesheet">
<!-- <link href="style.css" type="text/css" rel="stylesheet"> -->
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<style type="text/css">
#nav {
	color: #000000;
}

a, a:hover {
	color: #006064;
}

#signUp {
	padding-left: 2%;
}

#login {
	padding-left: 2%;
}
</style>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						
						$('#btnLogin').click(
										function() {
											console.log("hi")
											var crewId = $('#crewId').val();
											var password = $('#password').val();
											$
													.ajax({
														type : "POST",
														url : "CrewLoginServlet",
														data : {
															crewId:crewId,
															password : password
														},
														success : function(
																response) {
															console
																	.log(response);
															var json = JSON
																	.parse(response);
															console.log(json)
															if (json.status == "failed") {
																console
																		.log("error")
																$('#alert')
																		.append(
																				'<div class="alert alert-danger" role="alert">Invalid Username or password</div>');
															} else {
																location
																		.reload();
															}
														}
													})
										})

					})
</script>
</head>
<body>
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="loginModal" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Login</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="alert"></div>
					<form>
						<div class="form-row">

							<div class="col">
								<input type="text" class="form-control" placeholder="Crew id"
									id="crewId" name="crewId">
							</div>
							<div class="col">
								<input type="password" class="form-control"
									placeholder="password" id="password" name="password">
							</div>
						</div>
						<div class="row">
							<div class="col">
								<button type="button" class="btn btn-primary float-xl-right"
									id="btnLogin">Login</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>

				</div>
			</div>
		</div>
	</div>
</body>
</html>