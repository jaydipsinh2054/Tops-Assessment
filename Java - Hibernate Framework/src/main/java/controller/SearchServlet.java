package controller;

import dao.PostDAO;
import dao.PostDAOImpl;
import model.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");

		PostDAO dao = new PostDAOImpl();
		List<Post> results = dao.searchByTitleOrTag(keyword);

		request.setAttribute("posts", results);
		request.getRequestDispatcher("views/dashboard.jsp").forward(request, response);
	}
}