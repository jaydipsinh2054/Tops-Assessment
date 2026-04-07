package controller;

import dao.BillingDAO;
import model.Invoice;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/generateBill")
public class BillingServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int billId = Integer.parseInt(request.getParameter("billId"));
		double baseCost = Double.parseDouble(request.getParameter("baseCost"));
		double medCost = Double.parseDouble(request.getParameter("medCost"));
		double discount = 0.10; // Assume 10% insurance for simplicity

		Invoice inv = new Invoice();
		inv.setBillId(billId);
		inv.setMedicationCost(medCost);
		inv.calculateTotal(baseCost, discount); // The OOP calculation logic

		BillingDAO dao = new BillingDAO();
		if (dao.saveInvoice(inv)) {
			request.setAttribute("invoice", inv);
			request.getRequestDispatcher("views/invoice-detail.jsp").forward(request, response);
		}
	}
}