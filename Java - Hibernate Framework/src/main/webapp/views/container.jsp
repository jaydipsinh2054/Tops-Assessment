<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="com.model.BlogsModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

            <main class="py-5">
                <div class="container">
                    <div class="row g-4">
                        <!-- Main Feed -->
                        <div class="col-lg-8">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h2 class="fw-bold m-0" style="color: #0f172a; letter-spacing: -0.5px;">Latest Posts
                                </h2>

                            </div>

                            <div class="row g-4">
                                <!-- Blog Card 1 -->
                          <%
Connection cn = new DBUtil().getConnectionData();
String qry = "SELECT blogs.*, users.name FROM blogs JOIN users ON blogs.uid = users.uid";
PreparedStatement st = cn.prepareStatement(qry);
ResultSet rs = st.executeQuery();

while(rs.next())
{
String context = rs.getString(4);

String shortContext = context.length() > 120 
        ? context.substring(0,120) + "..." 
        : context;
%>

<div class="col-md-6">
<div class="blog-card">
<div class="blog-card-body">

<a href="search.jsp?tag=<%=rs.getString(5)%>" class="tag-badge">
<%=rs.getString(5)%>
</a>

<!-- Title -->
<form action="ViewPostController" method="post">

<input type="hidden" name="action" value="view">
<input type="hidden" name="id" value="<%=rs.getInt(1)%>">

<button type="submit" 
style="border:none;background:none;padding:0;text-align:left"
class="blog-card-title d-block">

<%=rs.getString(3)%>

</button>

</form>

<!-- Short Content -->
<p class="blog-card-text">
<%=shortContext%>
</p>

<div class="blog-meta">

<div class="author-info">

<div>
<div><%=rs.getString("name")%></div>

<div class="text-muted" style="font-size: 0.75rem;">
<%=rs.getString(6)%>
</div>

</div>

</div>

<!-- Read Button -->
<form action="ViewPostController" method="post">

<input type="hidden" name="action" value="view">
<input type="hidden" name="id" value="<%=rs.getInt(1)%>">

<button type="submit" 
class="read-more-btn"
style="border:none;background:none">

Read <i class="fa-solid fa-arrow-right"></i>

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

                            <!-- Pagination -->
                            <nav class="mt-5">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                        <a class="page-link border-0 rounded-circle me-2"
                                            style="width: 40px; height: 40px; display: flex; align-items: center; justify-content: center;"
                                            href="#" tabindex="-1"><i class="fa-solid fa-chevron-left"></i></a>
                                    </li>
                                    <li class="page-item active"><a class="page-link border-0 rounded-circle me-2"
                                            style="width: 40px; height: 40px; display: flex; align-items: center; justify-content: center; background-color: var(--primary-color);"
                                            href="#">1</a></li>
                                    <li class="page-item"><a
                                            class="page-link border-0 rounded-circle me-2 text-dark bg-white shadow-sm"
                                            style="width: 40px; height: 40px; display: flex; align-items: center; justify-content: center;"
                                            href="#">2</a></li>
                                    <li class="page-item"><a
                                            class="page-link border-0 rounded-circle me-2 text-dark bg-white shadow-sm"
                                            style="width: 40px; height: 40px; display: flex; align-items: center; justify-content: center;"
                                            href="#">3</a></li>
                                    <li class="page-item">
                                        <a class="page-link border-0 rounded-circle text-dark bg-white shadow-sm"
                                            style="width: 40px; height: 40px; display: flex; align-items: center; justify-content: center;"
                                            href="#"><i class="fa-solid fa-chevron-right"></i></a>
                                    </li>
                                </ul>
                            </nav>
                        </div>

                        <!-- Right Sidebar -->
                        <div class="col-lg-4">



                            <!-- Recent Posts Widget -->
                            <div class="sidebar-widget">
<h4 class="widget-title">Recent Posts</h4>

<div class="recent-posts">

<%
Connection cn2 = new DBUtil().getConnectionData();

String recentQry = "SELECT id,title,createdat FROM blogs ORDER BY createdat DESC LIMIT 3";

PreparedStatement pst = cn2.prepareStatement(recentQry);

ResultSet rrs = pst.executeQuery();

while(rrs.next())
{
%>

<div class="recent-post-item">
<div>

<form action="ViewPostController" method="post">

<input type="hidden" name="action" value="view">
<input type="hidden" name="id" value="<%=rrs.getInt("id")%>">

<button type="submit"
style="border:none;background:none;padding:0;text-align:left"
class="recent-title">

<%=rrs.getString("title")%>

</button>

</form>

<span class="recent-date">
<%=rrs.getTimestamp("createdat")%>
</span>

</div>
</div>

<%
}
cn2.close();
%>

</div>
</div>
                           
                            <!-- Tags Widget -->
<div class="sidebar-widget"> 
<h4 class="widget-title">Popular Tags</h4>

<div class="tag-cloud">

<%
Connection cn3 = new DBUtil().getConnectionData();

String tagQry = "SELECT tags, COUNT(tags) as total FROM blogs GROUP BY tags ORDER BY total DESC LIMIT 7";

PreparedStatement pstTag = cn3.prepareStatement(tagQry);

ResultSet tagRs = pstTag.executeQuery();

while(tagRs.next())
{
String tag = tagRs.getString("tags");
String encodedTag = java.net.URLEncoder.encode(tag,"UTF-8");
%>

<a href="search.jsp?tags=<%=encodedTag%>">
<%=tag%>
</a>

<%
}
cn3.close();
%>

</div>
</div>

                        </div>
                    </div>
                </div>
            </main>


</body>
</html>