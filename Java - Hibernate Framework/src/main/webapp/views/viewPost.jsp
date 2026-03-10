<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.Post"%>

<%
Post post = (Post) request.getAttribute("post");
%>

<!DOCTYPE html>
<html>
<head>
<title>View Post</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>

	<div class="container mt-5">

		<div class="card shadow">
			<div class="card-body">

				<h2><%=post.getTitle()%></h2>

				<p class="text-muted">
					Posted on:
					<%=post.getCreatedAt()%></p>

				<hr>

				<p><%=post.getContent()%></p>

				<span class="badge bg-info"><%=post.getTags()%></span>

				<hr>

				<a href="../dashboard" class="btn btn-secondary">Back</a>

			</div>
		</div>

	</div>

</body>
</html>