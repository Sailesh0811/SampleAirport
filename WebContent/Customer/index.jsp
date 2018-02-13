<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Airport System</title>
<style type="text/css">
#back {
	padding-left: 0;
	padding-right: 0;
	z-index: -9999;
}

#search {
	position: relative;
	margin-top: 2%;
	background: transparent;
	padding-right: 0;
	padding-left: 0;
	color: #ffffff;
}

#card {
	background: #01579B;
	border: none;
	padding-right: 0;
	padding-left: 0;
}

#offers {
	margin-top: 3%;
}

#why {
	background-color: #424242;
	padding-left: 0;
	padding-right: 0;
	margin-top: 2%;
}

#whyCard {
	background-color: #e2e2e2;
	border:none;
}
#footer{
	background:#000000;
	margin-top: 2%;
	color:#ffffff;
	text-align: center;
	
}
</style>

</head>

<body>
	<jsp:include page="header.jsp" />

	<div class="container-fluid" id="search">
		<div class="card" id="card">
			<div class="card-body">
				<h5 class="card-title">Search</h5>
				<form method="POST" action="SearchServlet">
					<div class="form-row">
						<div class="form-group col-md-2">
							<label for="from">From</label> <select class="form-control"
								id="from" name="from">
								<c:forEach var="from" items="${from}">
									<option value="${from}"><c:out value="${from}"></c:out></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-md-2">
							<label for="to">To</label> <select class="form-control" id="to"
								name="to">
								<c:forEach var="to" items="${to}">
									<option value="${to}"><c:out value="${to}"></c:out></option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group col-md-2">
							<label for="date">Date</label> <input type="date" name="date"
								class="form-control" id="date">
						</div>
						<div class="form-group col-md-2">

							<label for="seats">Seats</label> <select class="form-control"
								id="seats" name="seats">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<label for="class">Class</label> <select class="form-control"
								id="class" name="class">
								<option value="EC">EC</option>
								<option value="BC">BC</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<label for="filter">Filter</label> <select class="form-control"
								id="filter" name="filter">
								<option value="LOWRATE">LowRate</option>
								<option value="HIGHRATE">HighRate</option>
								<option value="AIRLINE">Airline</option>
								<option value="LDEPARTURE">Early Departure</option>
								<option value="HDEPARTURE">Late Departure</option>
							</select>
						</div>
					</div>
					<button type="submit"
						class="btn btn-primary float-xl-right float-l-right ">Search</button>
				</form>
			</div>
		</div>

	</div>
	<div class="container" id="offers">
		<div id="notification">
			<c:if test="${scopeSession.pnr >0}">
				<div class="alert alert-info" role="alert">
						Your last booked pnr no<c:out value="${scopeSession.pnr}"></c:out> 
					</div>
			</c:if>
		</div>
		<div class="card-group">
			<div class="card">
				<img class="card-img-top" src="../assets/images/card3.jpg"
					alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Fly and stay with us!</h5>
					<p class="card-text">Book your flight and also book your stay
						with us to stay comfortable</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">Ends in 2 days</small>
				</div>
			</div>
			<div class="card">
				<img class="card-img-top" src="../assets/images/card2.jpg"
					alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Get 1 for 1</h5>
					<p class="card-text">Book for one night and stay for two nights</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">3 days to end</small>
				</div>
			</div>
			<div class="card">
				<img class="card-img-top" src="../assets/images/card1.jpg"
					alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Sign up no to get 50% reward</h5>
					<p class="card-text">Sign up to our system get 50% offer on
						your next two bookings</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">Last updated 3 mins ago</small>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid" id="why">
		<div class="card" id="whyCard">

			<div class="card-body">
				<div class="card-title">Why our system?</div>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
			tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
			quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
			consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
			cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
			proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
			<div class="card-title">Why our system?</div>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
			tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
			quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
			consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
			cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
			proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
			</div>
	
	</div>
	<nav class="navbar " id="footer">
  
    <p class="font-italic float-xl-right">Made with <span><img src="assets/images/heart"></span> by Coda</p>
  
</nav>
</body>
</html>