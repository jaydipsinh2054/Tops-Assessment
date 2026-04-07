<%@ page import="model.Patient"%>
<%
Patient user = (Patient) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("../index.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Book Appointment | SmileCare</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container py-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0">Schedule Your Visit</h4>
					</div>
					<div class="card-body p-4">
						<form action="../bookAppointment" method="POST">
							<div class="mb-3">
								<label class="form-label">Select Dental Service</label> <select
									name="service" class="form-select" required>
									<option value="Routine Cleaning">Routine Cleaning
										($80)</option>
									<option value="Filling">Filling ($150)</option>
									<option value="Root Canal">Root Canal ($500)</option>
									<option value="Teeth Whitening">Teeth Whitening ($200)</option>
								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Preferred Date & Time</label> <input
									type="datetime-local" name="dateTime" class="form-control"
									required>
							</div>

							<div class="mb-4">
								<label class="form-label">Estimated Base Cost</label> <input
									type="number" name="cost" class="form-control"
									placeholder="Enter estimated amount" required>
								<div class="form-text text-info">Final billing includes
									taxes and insurance discounts.</div>
							</div>

							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary">Confirm
									Booking</button>
								<a href="patient-dashboard.jsp"
									class="btn btn-outline-secondary">Cancel</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>