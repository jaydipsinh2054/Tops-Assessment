package controller;

import dao.PostDAO;
import dao.PostDAOImpl;
import model.Post;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/views/login.jsp");
			return;
		}

		String action = request.getParameter("action");
		PostDAO dao = new PostDAOImpl();
		User user = (User) session.getAttribute("user");

		if ("create".equals(action)) {

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String tags = request.getParameter("tags");

			Post post = new Post(user.getId(), title, content, tags);
			dao.addPost(post);

			response.sendRedirect(request.getContextPath() + "/dashboard");
		}

		else if ("update".equals(action)) {

			int id = Integer.parseInt(request.getParameter("id"));
			String newTitle = request.getParameter("title");
			String newContent = request.getParameter("content");
			String newTags = request.getParameter("tags");

			Post updatedPost = new Post(id, user.getId(), newTitle, newContent, newTags, null);
			dao.updatePost(updatedPost);

			response.sendRedirect(request.getContextPath() + "/dashboard");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		PostDAO dao = new PostDAOImpl();

		if ("view".equals(action)) {

			int id = Integer.parseInt(request.getParameter("id"));
			Post post = dao.getPostById(id);

			request.setAttribute("post", post);
			request.getRequestDispatcher("/views/viewPost.jsp").forward(request, response);
		}

		else if ("edit".equals(action)) {

			int id = Integer.parseInt(request.getParameter("id"));
			Post post = dao.getPostById(id);

			request.setAttribute("post", post);
			request.getRequestDispatcher("/views/editPost.jsp").forward(request, response);
		}

		else if ("delete".equals(action)) {

			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("user") == null) {
				response.sendRedirect(request.getContextPath() + "/views/login.jsp");
				return;
			}

			int id = Integer.parseInt(request.getParameter("id"));
			dao.deletePost(id);

			response.sendRedirect(request.getContextPath() + "/dashboard");
		}
	}
}