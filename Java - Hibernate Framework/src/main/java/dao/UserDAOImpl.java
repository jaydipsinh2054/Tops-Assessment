package dao;

import model.User;
import util.DBConnection;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean registerUser(User user) {
		try {
			if (emailExists(user.getEmail())) {
				return false;
			}

			Connection con = DBConnection.getConnection();
			String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User login(String email, String password) {
		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT * FROM users WHERE email=? AND password=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User getUserById(int id) {
		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT * FROM users WHERE id=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean emailExists(String email) {
		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT id FROM users WHERE email=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}