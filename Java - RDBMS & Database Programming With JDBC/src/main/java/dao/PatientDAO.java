package dao;

import model.Patient;
import java.sql.*;

public class PatientDAO {

	public boolean registerPatient(Patient patient) throws SQLException {
		String query = "INSERT INTO patients (full_name, email, password, insurance_provider) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, patient.getFullName());
			ps.setString(2, patient.getEmail());
			ps.setString(3, patient.getPassword());
			ps.setString(4, "General Care"); // Default
			return ps.executeUpdate() > 0;
		}
	}

	public Patient authenticate(String email, String password) {
		String query = "SELECT * FROM patients WHERE email = ? AND password = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Patient p = new Patient();
				p.setPatientId(rs.getInt("patient_id"));
				p.setFullName(rs.getString("full_name"));
				p.setEmail(rs.getString("email"));
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
