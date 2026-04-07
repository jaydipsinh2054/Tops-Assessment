package controller;

import dao.AppointmentDAO;
import model.Appointment;
import model.Patient;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/bookAppointment")
public class AppointmentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Patient p = (Patient) session.getAttribute("user");

		Appointment app = new Appointment();
		app.setPatientId(p.getPatientId());
		app.setServiceType(request.getParameter("service"));
		app.setBaseCost(Double.parseDouble(request.getParameter("cost")));
		app.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));

		AppointmentDAO dao = new AppointmentDAO();
		if (dao.bookAppointment(app)) {
			response.sendRedirect("views/patient-dashboard.jsp?status=booked");
		}
	}
}