<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.Post"%>

<%
Post post = (Post) request.getAttribute("post");
if (post == null) {
	response.sendRedirect(request.getContextPath() + "/dashboard");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Edit Post</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body class="bg-light">

	<div class="container mt-5">

		<div class="card shadow">
			<div class="card-body">

				<h3>Edit Post</h3>

				<form action="<%=request.getContextPath()%>/post" method="post">

					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="id" value="<%=post.getId()%>">

					<div class="mb-3">
						<label>Title</label> <input type="text" name="title"
							value="<%=post.getTitle()%>" class="form-control" required>
					</div>

					<div class="mb-3">
						<label>Content</label>
						<textarea name="content" rows="6" class="form-control" required><%=post.getContent()%></textarea>
					</div>

					<div class="mb-3">
						<label>Tags</label> <input type="text" name="tags"
							value="<%=post.getTags()%>" class="form-control">
					</div>

					<button class="btn btn-primary">Update</button>
					<a href="<%=request.getContextPath()%>/dashboard"
						class="btn btn-secondary">Cancel</a>

				</form>

			</div>
		</div>

	</div>

</body>
</html>