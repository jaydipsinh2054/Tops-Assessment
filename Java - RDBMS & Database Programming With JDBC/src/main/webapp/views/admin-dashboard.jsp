<%@ page import="model.Admin"%>
<%
Admin admin = (Admin) session.getAttribute("user");
if (admin == null || !"ADMIN".equals(session.getAttribute("role"))) {
	response.sendRedirect("../index.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<div class="container">
			<span class="navbar-brand">SmileCare Admin: <%=admin.getUsername()%></span>
			<a href="../logout" class="btn btn-danger btn-sm">Logout</a>
		</div>
	</nav>
	<div class="container mt-4">
		<h3>Today's Appointments</h3>
		<table class="table table-hover mt-3">
			<thead class="table-primary">
				<tr>
					<th>Patient ID</th>
					<th>Service</th>
					<th>Time</th>
					<th>Base Cost</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>101</td>
					<td>Root Canal</td>
					<td>2026-03-10 14:00</td>
					<td>$450.00</td>
					<td>
						<form action="../generateBill" method="POST"
							style="display: inline;">
							<input type="hidden" name="appId" value="1"> <input
								type="hidden" name="baseCost" value="450.00"> <input
								type="number" name="medCost" placeholder="Medication Cost"
								required>
							<button class="btn btn-sm btn-success">Finalize & Bill</button>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>