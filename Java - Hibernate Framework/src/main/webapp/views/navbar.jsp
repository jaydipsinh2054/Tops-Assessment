<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    
    <nav class="navbar navbar-expand-lg sticky-top shadow-sm"
        style="background: rgba(255, 255, 255, 0.95); backdrop-filter: blur(10px);">
        <div class="container">
            <a class="navbar-brand fw-bold" href="dashboard.jsp"
                style="color: #4f46e5; font-size: 1.4rem; letter-spacing: -0.5px;">
                <i class="fa-solid fa-pen-nib me-2"></i>SimpleBlog
            </a>
            <button class="navbar-toggler border-0 shadow-none" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0 fw-medium">
                    <li class="nav-item">
                        <a class="nav-link px-3" href="dashboard.jsp">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-3" href="createpost.jsp">Create Post</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-3" href="search.jsp">Search</a>
                    </li>
                    <li class="nav-item1">
                        <a class="nav-link px-3" href="profile.jsp">Welcome,<%=session.getAttribute("firstname") %></a>
                    </li>
                    <li class="nav-item ms-lg-2">
                        <a class="nav-link btn btn-light rounded-pill px-3 text-danger fw-semibold" href="index.jsp"
                            style="background-color: #fff0f0;">
                            <i class="fa-solid fa-arrow-right-from-bracket me-1"></i>Logout
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <style>
        .navbar-nav .nav-link {
            color: #475569;
            transition: color 0.2s ease;
        }

        .navbar-nav .nav-link:hover {
            color: #4f46e5;
        }

        .navbar-nav .btn.nav-link:hover {
            background-color: #fee2e2 !important;
            color: #dc2626 !important;
        }
    </style>