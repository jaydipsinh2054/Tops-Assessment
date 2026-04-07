<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Patient Registration</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="card p-4 shadow">
					<h2 class="text-center mb-4">Join SmileCare</h2>
					<form action="register" method="POST">
						<div class="mb-3">
							<label>Full Name</label> <input type="text" name="fullName"
								class="form-control" required>
						</div>
						<div class="mb-3">
							<label>Email Address</label> <input type="email" name="email"
								class="form-control" required>
						</div>
						<div class="mb-3">
							<label>Password</label> <input type="password" name="password"
								class="form-control" required>
						</div>
						<button type="submit" class="btn btn-success w-100">Register</button>
					</form>
					<a href="index.jsp" class="btn btn-link mt-2">Back to Login</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>