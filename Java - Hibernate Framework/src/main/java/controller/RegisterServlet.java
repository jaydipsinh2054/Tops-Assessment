package controller;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (name == null || email == null || password == null || name.isEmpty() || email.isEmpty()
				|| password.isEmpty()) {

			request.setAttribute("error", "All fields are required.");
			request.getRequestDispatcher("views/register.jsp").forward(request, response);
			return;
		}

		User user = new User(name, email, password);
		UserDAO dao = new UserDAOImpl();

		if (dao.registerUser(user)) {
			response.sendRedirect("views/login.jsp");
		} else {
			request.setAttribute("error", "Registration failed. Email may already exist.");
			request.getRequestDispatcher("views/register.jsp").forward(request, response);
		}
	}
}