package dao;

import model.Invoice;
import java.sql.*;

public class BillingDAO {
	public boolean saveInvoice(Invoice inv) {
		String query = "INSERT INTO billing (bill_id, medication_cost, total_amount, bill_date) VALUES (?, ?, ?, NOW())";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, inv.getBillId());
			ps.setDouble(2, inv.getMedicationCost());
			ps.setDouble(3, inv.getTotalAmount());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
