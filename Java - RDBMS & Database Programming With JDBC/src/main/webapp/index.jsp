<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SmileCare Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div
		class="container d-flex justify-content-center align-items-center vh-100">
		<div class="card shadow-lg" style="width: 400px;">
			<div class="card-header bg-primary text-white text-center">
				<h3>SmileCare Dental</h3>
			</div>
			<div class="card-body">
				<%
				if (request.getAttribute("error") != null) {
				%>
				<div class="alert alert-danger p-2"><%=request.getAttribute("error")%></div>
				<%
				}
				%>
				<form action="login" method="POST">
					<div class="mb-3">
						<label class="form-label">Email / Username</label> <input
							type="text" name="username" class="form-control" required>
					</div>
					<div class="mb-3">
						<label class="form-label">Password</label> <input type="password"
							name="password" class="form-control" required>
					</div>
					<div class="mb-3">
						<label class="form-label">Login as:</label> <select name="role"
							class="form-select">
							<option value="PATIENT">Patient</option>
							<option value="ADMIN">Clinic Staff</option>
						</select>
					</div>
					<button type="submit" class="btn btn-primary w-100">Login</button>
				</form>
				<div class="mt-3 text-center">
					New Patient? <a href="register.jsp">Register Here</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>