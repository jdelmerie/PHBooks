package fr.fms.entities;

import java.util.Date;

public class Order {
	private int id;
	private double totalAmount;
	private Date date;
	private int customerId;

	public Order(int id, double totalAmount, Date date, int customerId) {
		this.id = id;
		this.totalAmount = totalAmount;
		this.date = date;
		this.customerId = customerId;
	}

	public Order(double totalAmount, Date date, int customerId) {
		this.totalAmount = totalAmount;
		this.date = date;
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", totalAmount=" + totalAmount + ", date=" + date + ", customerId=" + customerId
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
