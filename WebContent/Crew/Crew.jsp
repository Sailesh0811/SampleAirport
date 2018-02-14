<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crew</title>
<style>
	html,body{
		height:100%;
		min-height: 100%;
    margin: 0;
    padding: 0;
	}
	#nav{
		padding-top:2%;
		margin-top:5%;
		height:100%;
	}
	#sideNav{
		height:100%;
		
	}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<c:choose>
		<c:when test="${sessionScope.crewId eq null }">
	<script>
		$(document).ready(function() {
			$('#loginModal').modal('show');
		})
	</script>
	</c:when>
	<c:otherwise>
	<script>
		$(document).ready(function() {
			
			$('#v-pills-tab a').click(function(e) {
				e.preventDefault()
				$(this).tab('show')
			})
			$('#v-pills-home-tab').click(function(){
			
				$('#v-pills-home').empty();
				$('#v-pills-home').load('ViewCrewProfileServlet');
			})
			$('#v-pills-profile-tab').click(function(){
				
				$('#v-pills-profile').empty();
				$('#v-pills-profile').load('ViewAssignedDutyServlet');
			})
			$('#v-pills-messages-tab').click(function(){
				
				//$('#v-pills-messages').empty();
				//$('#v-pills-messages').load('SlotRequestServlet');
			})
			$('#v-pills-settings-tab').click(function(){
				
				//$('#v-pills-settings').empty();
				//$('#v-pills-settings').load('LeaveRequestServlet');
			})
		})
	</script>
	
	<div class="container-fluid" id="nav">
	<div class="row">
	<div class="col-md-3" id="sideNav">
	<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist"
		aria-orientation="vertical">
		<a class="nav-link active" id="v-pills-home-tab" data-toggle="pill"
			href="#v-pills-home" role="tab" aria-controls="v-pills-home"
			aria-selected="true">Profile</a> <a class="nav-link"
			id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile"
			role="tab" aria-controls="v-pills-profile" aria-selected="false">Duty</a>
		<a class="nav-link" id="v-pills-messages-tab" data-toggle="pill"
			href="#v-pills-messages" role="tab" aria-controls="v-pills-messages"
			aria-selected="false">Slot Request</a> <a class="nav-link"
			id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings"
			role="tab" aria-controls="v-pills-settings" aria-selected="false">Leave Request</a>
	</div>
	</div>
	
	<div class="col-md-9">
	<div class="tab-content" id="v-pills-tabContent">
		<div class="tab-pane fade show active" id="v-pills-home"
			role="tabpanel" aria-labelledby="v-pills-home-tab"></div>
		<div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
			aria-labelledby="v-pills-profile-tab">hello</div>
		<div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
			aria-labelledby="v-pills-messages-tab" style="height:100%;"><iframe src="SlotRequestServlet" height="100%" width="100%" frameborder="0"></iframe></div>
		<div class="tab-pane fade" id="v-pills-settings" role="tabpanel"
			aria-labelledby="v-pills-settings-tab">are you</div>
	</div>
	</div>
	</div>
	</div>
	</c:otherwise>
	</c:choose>
</body>
</html>