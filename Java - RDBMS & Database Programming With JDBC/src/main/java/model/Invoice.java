package model;

public class Invoice {
	private int billId;
	private int appId;
	private double medicationCost;
	private double taxRate = 0.05; // Fixed 5% tax
	private double totalAmount;

	public Invoice() {
	}

	/**
	 * Business Logic for Billing (Module 6 Requirement) Calculates: (Base + Meds) *
	 * (1 + Tax) - (Insurance Discount)
	 */
	public void calculateTotal(double baseCost, double insuranceDiscountPercent) {
		double subtotal = baseCost + medicationCost;
		double taxAmount = subtotal * taxRate;
		double discountAmount = subtotal * insuranceDiscountPercent;

		this.totalAmount = (subtotal + taxAmount) - discountAmount;
	}

	// Getters and Setters
	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public double getMedicationCost() {
		return medicationCost;
	}

	public void setMedicationCost(double cost) {
		this.medicationCost = cost;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double total) {
		this.totalAmount = total;
	}
}
