package controller;

import dao.PostDAO;
import dao.PostDAOImpl;
import model.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("views/login.jsp");
			return;
		}

		PostDAO dao = new PostDAOImpl();
		List<Post> posts = dao.getAllPosts();

		request.setAttribute("posts", posts);
		request.getRequestDispatcher("views/dashboard.jsp").forward(request, response);
	}
}