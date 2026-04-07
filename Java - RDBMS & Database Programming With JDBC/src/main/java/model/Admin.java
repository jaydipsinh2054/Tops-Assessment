package model;

public class Admin {
	private int adminId;
	private String username;
	private String password;
	private String fullName;
	private String role; // e.g., 'Receptionist', 'Dentist'

	// Constructors
	public Admin() {
	}

	public Admin(int adminId, String username, String role) {
		this.adminId = adminId;
		this.username = username;
		this.role = role;
	}

	// Getters and Setters
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}