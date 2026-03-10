<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Dashboard - SimpleBlog</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>

	<nav class="navbar navbar-dark bg-dark px-4">
		<span class="navbar-brand">SimpleBlog</span>
		<div>
			<span class="text-white me-3"> Welcome
				${sessionScope.user.name} </span> <a
				href="${pageContext.request.contextPath}/logout"
				class="btn btn-danger btn-sm">Logout</a>
		</div>
	</nav>

	<div class="container mt-4">

		<div class="d-flex justify-content-between mb-3">
			<a href="${pageContext.request.contextPath}/views/createPost.jsp"
				class="btn btn-primary">+ Create Post</a>

			<form action="${pageContext.request.contextPath}/search" method="get"
				class="d-flex">
				<input type="text" name="keyword" class="form-control me-2"
					placeholder="Search..." required>
				<button class="btn btn-outline-success">Search</button>
			</form>
		</div>

		<c:if test="${empty posts}">
			<div class="alert alert-info">No posts available.</div>
		</c:if>

		<c:forEach var="post" items="${posts}">
			<div class="card mb-3 shadow-sm">
				<div class="card-body">

					<h4>
						<a
							href="${pageContext.request.contextPath}/post?action=view&id=${post.id}"
							class="text-decoration-none"> ${post.title} </a>
					</h4>

					<p>${post.content}</p>

					<span class="badge bg-secondary">${post.tags}</span>

					<div class="mt-3">
						<c:if test="${post.userId == sessionScope.user.id}">
							<a
								href="${pageContext.request.contextPath}/post?action=edit&id=${post.id}"
								class="btn btn-warning btn-sm">Edit</a>
							<a
								href="${pageContext.request.contextPath}/post?action=delete&id=${post.id}"
								onclick="return confirm('Delete this post?')"
								class="btn btn-danger btn-sm"> Delete </a>
						</c:if>
					</div>

				</div>
			</div>
		</c:forEach>

	</div>

</body>
</html>