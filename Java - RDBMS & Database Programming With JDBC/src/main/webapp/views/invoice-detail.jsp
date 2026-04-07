<%@ page import="model.Invoice"%>
<%
Invoice inv = (Invoice) request.getAttribute("invoice");
if (inv == null) {
	response.sendRedirect("admin-dashboard.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Invoice #<%=inv.getBillId()%></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
@media print {
	.no-print {
		display: none;
	}
}
</style>
</head>
<body class="bg-secondary py-5">
	<div class="container bg-white p-5 shadow" style="max-width: 700px;">
		<div class="text-center mb-4">
			<h2>SmileCare Dental Services</h2>
			<p class="text-muted">123 Health Ave, Urban City | +1 555-DENTIST</p>
		</div>
		<hr>
		<div class="row mb-4">
			<div class="col-6">
				<h5>
					Invoice to: Patient #<%=inv.getBillId()%></h5>
			</div>
			<div class="col-6 text-end">
				<p>Date: 10 March 2026</p>
			</div>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th>Description</th>
					<th class="text-end">Amount</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Dental Procedure Base Charge</td>
					<td class="text-end">Calculated in App</td>
				</tr>
				<tr>
					<td>Medications & Supplies</td>
					<td class="text-end">$<%=String.format("%.2f", inv.getMedicationCost())%></td>
				</tr>
				<tr>
					<td>Taxes (5%)</td>
					<td class="text-end">Included</td>
				</tr>
			</tbody>
		</table>
		<div class="text-end mt-4">
			<h3 class="text-primary">
				Total Amount: $<%=String.format("%.2f", inv.getTotalAmount())%></h3>
		</div>
		<div class="mt-5 text-center no-print">
			<button onclick="window.print()" class="btn btn-primary me-2">Print
				Invoice</button>
			<a href="admin-dashboard.jsp" class="btn btn-outline-secondary">Return
				to Dashboard</a>
		</div>
	</div>
</body>
</html>