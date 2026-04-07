package dao;

import model.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

	public boolean bookAppointment(Appointment app) {
		String query = "INSERT INTO appointments (patient_id, service_type, app_date, base_cost, status) VALUES (?, ?, ?, ?, 'Scheduled')";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, app.getPatientId());
			ps.setString(2, app.getServiceType());
			ps.setTimestamp(3, Timestamp.valueOf(app.getDateTime()));
			ps.setDouble(4, app.getBaseCost());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Appointment> getAppointmentsByPatient(int patientId) {
		List<Appointment> list = new ArrayList<>();
		String query = "SELECT * FROM appointments WHERE patient_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, patientId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Appointment a = new Appointment();
				a.setAppId(rs.getInt("app_id"));
				a.setServiceType(rs.getString("service_type"));
				a.setDateTime(rs.getTimestamp("app_date").toLocalDateTime());
				a.setBaseCost(rs.getDouble("base_cost"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
