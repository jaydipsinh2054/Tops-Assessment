<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Search - SimpleBlog</title>

<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<style>

:root {
    --primary-color:#4f46e5;
    --secondary-color:#6366f1;
    --bg-color:#f8fafc;
    --text-color:#1e293b;
}

body{
    font-family:'Inter',sans-serif;
    background-color:var(--bg-color);
    color:var(--text-color);
}

.search-container{
    background:#ffffff;
    border-radius:20px;
    box-shadow:0 4px 20px rgba(0,0,0,0.05);
    padding:3rem;
    margin-bottom:3rem;
}

.search-input-group{
    position:relative;
}

.search-input{
    width:100%;
    padding:1.2rem 1.5rem 1.2rem 3.5rem;
    font-size:1.1rem;
    border-radius:50rem;
    border:2px solid #e2e8f0;
}

.search-input:focus{
    outline:none;
    border-color:var(--primary-color);
}

.search-icon{
    position:absolute;
    left:1.5rem;
    top:50%;
    transform:translateY(-50%);
    color:#94a3b8;
}

.search-btn{
    position:absolute;
    right:5px;
    top:50%;
    transform:translateY(-50%);
    background:var(--primary-color);
    color:white;
    border:none;
    border-radius:50rem;
    padding:8px 20px;
}

.filter-group{
    display:flex;
    gap:1rem;
    margin-top:1.5rem;
    justify-content:center;
}

.filter-radio{
    display:none;
}

.filter-label{
    padding:6px 18px;
    border-radius:50rem;
    background:#f1f5f9;
    cursor:pointer;
}

.filter-radio:checked + .filter-label{
    background:rgba(79,70,229,0.1);
    color:var(--primary-color);
}

.blog-card{
    background:#fff;
    border-radius:16px;
    box-shadow:0 4px 6px rgba(0,0,0,0.05);
}

.blog-card-body{
    padding:1.5rem;
}

.tag-badge{
    background:rgba(79,70,229,0.1);
    color:var(--primary-color);
    font-size:12px;
    padding:4px 10px;
    border-radius:20px;
    display:inline-block;
    margin-bottom:10px;
}

.blog-card-title{
    font-size:18px;
    font-weight:600;
    text-decoration:none;
    color:#0f172a;
}

.blog-card-text{
    font-size:14px;
    color:#64748b;
}

</style>

</head>

<body>

<%@ include file="navbar.jsp" %>

<main class="py-5">

<div class="container">

<!-- SEARCH BOX -->
<div class="row justify-content-center">

<div class="col-lg-8">

<div class="search-container text-center">

<h2 class="fw-bold mb-4">Find exactly what you're looking for</h2>

<div class="search-input-group">

<i class="fa-solid fa-magnifying-glass search-icon"></i>

<input type="text"
id="searchInput"
class="search-input"
placeholder="Search posts...">

<button type="button"
onclick="searchPost()"
class="search-btn">
Search
</button>

</div>


<div class="filter-group">

<div>
<input type="radio"
id="filterTitle"
name="filterByType"
value="title"
class="filter-radio"
checked>

<label for="filterTitle" class="filter-label">
<i class="fa-solid fa-heading"></i> By Title
</label>
</div>

<div>
<input type="radio"
id="filterTag"
name="filterByType"
value="tag"
class="filter-radio">

<label for="filterTag" class="filter-label">
<i class="fa-solid fa-tags"></i> By Tag
</label>
</div>

</div>

</div>

</div>

</div>


<!-- RESULTS TITLE -->
<div class="mb-4">

<h4 class="fw-bold">
Search Results
<span id="resultCount" class="text-muted fs-6"></span>
</h4>

</div>


<!-- RESULT CONTAINER -->
<div id="resultContainer" class="row g-4">

<!-- AJAX DATA WILL LOAD HERE -->

</div>

</div>

</main>

<%@ include file="footer.jsp" %>

<script>

function searchPost()
{
    var query=document.getElementById("searchInput").value;

    var filter=document.querySelector('input[name="filterByType"]:checked').value;

    var xhr=new XMLHttpRequest();

    xhr.open("GET","SearchController?query="+query+"&filterByType="+filter,true);

    xhr.onload=function()
    {
        if(xhr.status==200)
        {
            document.getElementById("resultContainer").innerHTML=xhr.responseText;
        }
    };

    xhr.send();
}

</script>

</body>
</html>