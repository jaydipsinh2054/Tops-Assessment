package controller;

import dao.PatientDAO;
import model.Patient;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Patient p = new Patient();
		p.setFullName(request.getParameter("fullName"));
		p.setEmail(request.getParameter("email"));
		p.setPassword(request.getParameter("password"));

		PatientDAO dao = new PatientDAO();
		try {
			if (dao.registerPatient(p)) {
				response.sendRedirect("index.jsp?msg=success");
			}
		} catch (SQLException e) {
			request.setAttribute("error", "Email already exists!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
}