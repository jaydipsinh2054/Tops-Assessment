<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
Integer uid = (Integer) session.getAttribute("uid");

if(uid == null)
{
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profile - SimpleBlog</title>

<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<style>

:root{
--primary-color:#4f46e5;
--bg-color:#f8fafc;
}

body{
font-family:'Inter',sans-serif;
background:var(--bg-color);
}

.profile-header{
background:white;
border-radius:20px;
padding:3rem;
margin-bottom:3rem;
box-shadow:0 4px 20px rgba(0,0,0,0.05);
text-align:center;
position:relative;
overflow:hidden;
}

.profile-banner-color{
position:absolute;
top:0;
left:0;
right:0;
height:120px;
background:linear-gradient(135deg,var(--primary-color),#818cf8);
}

.profile-content{
position:relative;
margin-top:50px;
}

.profile-avatar{
width:120px;
height:120px;
border-radius:50%;
background:white;
color:var(--primary-color);
display:flex;
align-items:center;
justify-content:center;
font-size:3rem;
font-weight:800;
margin:auto;
border:5px solid white;
margin-bottom:1rem;
}

.profile-name{
font-size:2rem;
font-weight:800;
}

.profile-email{
color:#64748b;
}

.blog-card{
background:white;
border-radius:16px;
box-shadow:0 4px 6px rgba(0,0,0,0.05);
transition:0.3s;
height:100%;
}

.blog-card:hover{
transform:translateY(-5px);
box-shadow:0 10px 20px rgba(0,0,0,0.08);
}

.blog-card-body{
padding:1.5rem;
}

.blog-card-title{
font-size:1.25rem;
font-weight:700;
}

.blog-card-text{
color:#64748b;
font-size:0.95rem;
}

.action-buttons{
display:flex;
gap:10px;
margin-top:15px;
}

.btn-edit{
background:#f1f5f9;
padding:6px 10px;
border-radius:6px;
text-decoration:none;
color:black;
}

.btn-delete{
background:#fee2e2;
padding:6px 10px;
border-radius:6px;
text-decoration:none;
color:red;
}

</style>
</head>

<body>

<%@ include file="navbar.jsp" %>

<main class="py-5">
<div class="container">
<div class="row justify-content-center">
<div class="col-lg-10">

<!-- PROFILE HEADER -->

<div class="profile-header">
<div class="profile-banner-color"></div>

<div class="profile-content">

<%
Connection cn = new DBUtil().getConnectionData();

String userQry="select * from users where uid=?";
PreparedStatement userSt=cn.prepareStatement(userQry);
userSt.setInt(1,uid);

ResultSet userRs=userSt.executeQuery();

if(userRs.next())
{
%>

<div class="profile-avatar">
<%= userRs.getString("name").substring(0,1).toUpperCase() %>
</div>

<h1 class="profile-name"><%=userRs.getString("name")%></h1>

<div class="profile-email"><%=userRs.getString("email")%></div>

<%
}
%>

</div>
</div>


<!-- POSTS HEADER -->

<div class="d-flex justify-content-between align-items-center mb-4">

<h3 class="fw-bold">My Blog Posts</h3>

<a href="createpost.jsp" class="btn btn-primary">
<i class="fa-solid fa-plus"></i> New Post
</a>

</div>


<!-- BLOG POSTS -->

<div class="row g-4">

<%

String blogQry="select * from blogs where uid=? order by id desc";

PreparedStatement blogSt=cn.prepareStatement(blogQry);

blogSt.setInt(1,uid);

ResultSet blogRs=blogSt.executeQuery();

while(blogRs.next())
{
%>

<div class="col-md-6">

<div class="blog-card">

<div class="blog-card-body">

<div class="d-flex justify-content-between mb-2">

<span class="badge bg-light text-primary">
Published
</span>

<span class="text-muted small">
<%=blogRs.getString("createdat")%>
</span>

</div>

<h4 class="blog-card-title">
<%=blogRs.getString("title")%>
</h4>


<div class="action-buttons">

<a href="editpost.jsp?id=<%=blogRs.getInt("id")%>" class="btn-edit">
<i class="fa-solid fa-pen-to-square"></i> Edit
</a>


<form action="DeletePostController" method="post" style="display:inline;">
    
    <input type="hidden" name="id" value="<%=blogRs.getInt("id")%>">
    <input type="hidden" name="action" value="deletepost">

    <button type="submit" class="btn-delete"
    onclick="return confirm('Are you sure you want to delete this post?')">
    <i class="fa-solid fa-trash"></i> Delete
    </button>

</form>

</div>

</div>
</div>

</div>

<%
}

cn.close();
%>

</div>

</div>
</div>
</div>
</main>

<%@ include file="footer.jsp" %>

</body>
</html>