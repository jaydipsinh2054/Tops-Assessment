package controller;

import dao.*;
import model.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String emailOrUser = request.getParameter("username");
        String pass = request.getParameter("password");
        String role = request.getParameter("role"); // 'ADMIN' or 'PATIENT'
        
        HttpSession session = request.getSession();

        if ("ADMIN".equals(role)) {
            AdminDAO adminDAO = new AdminDAO();
            Admin admin = adminDAO.authenticate(emailOrUser, pass);
            if (admin != null) {
                session.setAttribute("user", admin);
                session.setAttribute("role", "ADMIN");
                response.sendRedirect("views/admin-dashboard.jsp");
            } else {
                loginError(request, response);
            }
        } else {
            PatientDAO patientDAO = new PatientDAO();
            Patient patient = patientDAO.authenticate(emailOrUser, pass);
            if (patient != null) {
                session.setAttribute("user", patient);
                session.setAttribute("role", "PATIENT");
                response.sendRedirect("views/patient-dashboard.jsp");
            } else {
                loginError(request, response);
            }
        }
    }

    private void loginError(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("error", "Invalid Credentials. Please try again.");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}