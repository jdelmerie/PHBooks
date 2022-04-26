package fr.fms.entities;

public class OrderItem {
	private int id;
	private int bookId;
	private int quantity;
	private double price;
	private int orderId;

	public OrderItem(int id, int bookId, int quantity, double price, int orderId) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.quantity = quantity;
		this.price = price;
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", bookId=" + bookId + ", quantity=" + quantity + ", price=" + price
				+ ", orderId=" + orderId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
