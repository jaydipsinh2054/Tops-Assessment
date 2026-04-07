package model;

public class Patient {
	private int patientId;
	private String fullName;
	private String email;
	private String password;
	private String phone;
	private String insuranceProvider;
	private double insuranceDiscount; // e.g., 0.15 for 15%

	// Constructors
	public Patient() {
	}

	// Getters and Setters
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public double getInsuranceDiscount() {
		return insuranceDiscount;
	}

	public void setInsuranceDiscount(double discount) {
		this.insuranceDiscount = discount;
	}
}
