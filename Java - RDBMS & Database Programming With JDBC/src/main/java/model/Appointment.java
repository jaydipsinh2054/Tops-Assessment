package model;

import java.time.LocalDateTime;

public class Appointment {
	private int appId;
	private int patientId;
	private String serviceType; // e.g., 'Root Canal', 'Cleaning'
	private LocalDateTime dateTime;
	private String status; // 'Scheduled', 'Completed', 'Cancelled'
	private double baseCost;

	public Appointment() {
	}

	// Getters and Setters
	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}
}