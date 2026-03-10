<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Create Post</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body class="bg-light">

	<div class="container mt-5">

		<div class="card shadow">
			<div class="card-body">

				<h3>Create Blog Post</h3>

				<form action="../post" method="post">

					<input type="hidden" name="action" value="create">

					<div class="mb-3">
						<label>Title</label> <input type="text" name="title"
							class="form-control" required>
					</div>

					<div class="mb-3">
						<label>Content</label>
						<textarea name="content" rows="6" class="form-control" required></textarea>
					</div>

					<div class="mb-3">
						<label>Tags</label> <input type="text" name="tags"
							class="form-control">
					</div>

					<button class="btn btn-success">Publish</button>
					<a href="../dashboard" class="btn btn-secondary">Cancel</a>

				</form>

			</div>
		</div>

	</div>

</body>
</html>