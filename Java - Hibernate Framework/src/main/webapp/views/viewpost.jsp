
<%@page import="com.model.BlogsModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
BlogsModel blog = (BlogsModel ) request.getAttribute("blog");

%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><%=blog.getTitle()%> - SimpleBlog</title>

<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<style>

:root{
--primary-color:#4f46e5;
--secondary-color:#6366f1;
--bg-color:#f8fafc;
--text-color:#334155;
}

body{
font-family:'Inter',sans-serif;
background-color:var(--bg-color);
color:var(--text-color);
display:flex;
flex-direction:column;
min-height:100vh;
}

main{
flex:1;
}

.post-container{
background:#ffffff;
border-radius:16px;
box-shadow:0 4px 20px rgba(0,0,0,0.05);
padding:3rem 4rem;
margin-bottom:2rem;
border:1px solid #f1f5f9;
}

.post-title{
font-size:2.5rem;
font-weight:800;
color:#0f172a;
letter-spacing:-0.5px;
line-height:1.2;
margin-bottom:1.5rem;
}

.author-meta{
display:flex;
align-items:center;
gap:1rem;
padding-bottom:2rem;
border-bottom:1px solid #f1f5f9;
margin-bottom:2rem;
}

.author-avatar-lg{
width:48px;
height:48px;
border-radius:50%;
background-color:#e0e7ff;
color:var(--primary-color);
display:flex;
align-items:center;
justify-content:center;
font-weight:bold;
font-size:1.2rem;
}

.author-name{
font-weight:600;
color:#1e293b;
font-size:1.1rem;
margin-bottom:0.2rem;
}

.post-date{
color:#64748b;
font-size:0.9rem;
}

.tag-badge{
background-color:rgba(79,70,229,0.1);
color:var(--primary-color);
font-weight:600;
font-size:0.8rem;
padding:0.4em 1em;
border-radius:50rem;
text-decoration:none;
display:inline-block;
margin-bottom:1.5rem;
}

.post-content{
font-size:1.1rem;
line-height:1.8;
color:#334155;
}

.post-content p{
margin-bottom:1.5rem;
}

.interaction-bar{
display:flex;
align-items:center;
padding-top:2rem;
border-top:1px solid #f1f5f9;
margin-top:3rem;
}

.like-btn{
background:none;
border:1px solid #e2e8f0;
border-radius:50rem;
padding:0.5rem 1.2rem;
color:#64748b;
font-weight:600;
display:flex;
align-items:center;
gap:0.5rem;
}

.comments-section{
background:#ffffff;
border-radius:16px;
box-shadow:0 4px 20px rgba(0,0,0,0.05);
padding:2.5rem 3rem;
border:1px solid #f1f5f9;
margin-top:2rem;
}

</style>

</head>

<body>

<%@ include file="navbar.jsp" %>

<main class="py-5">

<div class="container">

<div class="row justify-content-center">

<div class="col-lg-8">

<!-- Back Button -->
<div class="mb-3">
    <button onclick="history.back()" class="btn btn-outline-secondary">
        <i class="fa-solid fa-arrow-left"></i> Back
    </button>
</div>

<!-- Post Content -->

<article class="post-container">

<a href="search.jsp?tag=<%=blog.getTags()%>" class="tag-badge">
<%=blog.getTags()%>
</a>

<h1 class="post-title">
<%=blog.getTitle()%>
</h1>

<div class="author-meta">



<div>
<div class="author-name"><%=blog.getName()%></div>

<div class="post-date">
<%=blog.getCreatedat()%>
</div>

</div>

</div>

<div class="post-content">

<p>
<%=blog.getContext()%>
</p>

</div>

<div class="interaction-bar">

<button class="like-btn">
<i class="fa-solid fa-heart"></i> Like
</button>

</div>

</article>


<!-- Comments Section -->

<div class="comments-section">

<h4 class="fw-bold mb-4">Comments</h4>

<form action="AddCommentServlet" method="post" class="mb-5">

<input type="hidden" name="postId" value="<%=blog.getId()%>">

<textarea class="form-control mb-3" name="comment" rows="3"
placeholder="Add a comment..." required></textarea>

<div class="text-end">

<button type="submit" class="btn btn-primary px-4">
Post Comment
</button>

</div>

</form>

</div>

</div>

</div>

</div>

</main>

<%@ include file="footer.jsp" %>

</body>

</html>