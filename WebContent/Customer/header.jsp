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
					function function_name() {
						$('#btnLogin')
								.click(
										function() {
											console.log("hi")
											var customerId = $('#customerId')
													.val();
											var password = $('#password').val();
											$
													.ajax({
														type : "POST",
														url : "LoginServlet",
														data : {
															customerId : customerId,
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
						$('#btnSignUp')
								.click(
										function() {
											console.log("hi")
											var name = $('#name').val();
											var password = $('#password').val();
											var contactNumber = $(
													'#contactNumber').val();
											var gender = $('#gender').val();
											$
													.ajax({
														type : "POST",
														url : "SignUpServlet",
														data : {
															name : name,
															password : password,
															contactNumber : contactNumber,
															gender : gender
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
																console
																		.log("success")
																$(
																		'#notification')
																		.append(
																				'<div class="alert alert-success" role="alert">Successfully signed up!</div>');
																setTimeout(
																		function() {
																			$(
																					'#notification')
																					.empty();
																		}, 5000)
															}
														}
													})
										})
					})
</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg" id="nav"> <a
		class="navbar-brand" href="index.jsp">Airport</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto justify-content-end">
			<li class="nav-item active"><a class="nav-link" href="index.jsp">Home
					<span class="sr-only">(current)</span>
			</a></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> UseFul Links </a> <c:choose>
					<c:when test="${sessionScope.customer eq null }">
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="Admin/Admin.jsp">Crew Member</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#">Administrator</a>
						</div>
					</c:when>
					<c:when test="${sessionScope.customer ne null }">
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="ViewHistoryServlet">View History</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item disabled" href="CheckIn">Check In</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="CancelTicketServlet">Cancel Ticket</a>
						</div>
					</c:when>

				</c:choose></li>

		</ul>
		<c:choose>
			<c:when test="${sessionScope.customer eq null }">
				<form class="form-inline" id="login">
					<button class="btn btn-outline-success my-2 my-sm-0" type="button"
						data-toggle="modal" data-target="#loginModal">Login</button>
				</form>
				<form class="form-inline" id="signUp">
					<button class="btn btn btn-outline-secondary my-2 my-sm-0"
						type="button" data-toggle="modal" data-target="#signUpModal">Sign
						Up</button>
				</form>
			</c:when>
			<c:when test="${sessionScope.customer ne null}">
				<form class="form-inline">
					<p style="padding-left: 2%;">
						Welcome
						<c:out value="${sessionScope.customer.getName()}"></c:out>
					</p>

				</form>
				<button class="btn btn-outline-success my-2 my-sm-0" type="button"
					onclick="window.location='LogOutServlet'">LogOut</button>
			</c:when>
		</c:choose>
	</div>

	</nav>
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
								<input type="text" class="form-control" placeholder="User id"
									id="customerId" name="customerId">
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
	<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog"
		aria-labelledby="signUpModal" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Sign Up</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="alert"></div>
					<form>
						<div class="container-fluid">

							<div class="row">
								<div class="col">
									<input type="text" class="form-control" placeholder="Name"
										id="name" name="name">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<input type="password" class="form-control"
										placeholder="password" id="pass" name="password">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<input type="text" class="form-control"
										placeholder="Mobile Number" id="contactNumber"
										name="contactNumber">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<input type="password" class="form-control"
										placeholder="gender" id="gender" name="gender">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<button type="button" class="btn btn-primary float-xl-right"
									id="btnSignUp" data-dismiss="modal">Sign Up</button>
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