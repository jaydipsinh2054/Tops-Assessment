<%@ page import="model.Patient"%>
<%
Patient user = (Patient) session.getAttribute("user");
if (user == null || !"PATIENT".equals(session.getAttribute("role"))) {
	response.sendRedirect("../index.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Patient Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-dark bg-primary mb-4">
		<div class="container">
			<span class="navbar-brand">Welcome, <%=user.getFullName()%></span>
			<a href="../logout" class="btn btn-outline-light btn-sm">Logout</a>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="card text-center p-3 mb-3">
					<h5>Need a checkup?</h5>
					<a href="booking-form.jsp" class="btn btn-primary">Book
						Appointment</a>
				</div>
			</div>
			<div class="col-md-8">
				<h3>My Treatment History</h3>
				<table class="table table-striped border">
					<thead class="table-dark">
						<tr>
							<th>Date</th>
							<th>Service</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2026-03-10</td>
							<td>Routine Cleaning</td>
							<td><span class="badge bg-success">Completed</span></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>