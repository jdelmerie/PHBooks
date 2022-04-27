package fr.fms.entities;

public class Book {
	private int id;
	private String title;
	private String author;
	private int publishYear;
	private int quantity = 1;
	private double price;
	private boolean state; // true = new - false = old

	public Book(int id, String title, String author, int publishYear, int quantity, double price, boolean state) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishYear = publishYear;
		setQuantity(quantity);
		this.price = price;
		this.state = state;
	}
	
	public Book(String title, String author, int publishYear, int quantity, double price, boolean state) {
		this.title = title;
		this.author = author;
		this.publishYear = publishYear;
		setQuantity(quantity);
		this.price = price;
		this.state = state;
	}
	
	public Book(String title, String author, int publishYear, double price, boolean state) {
		this.title = title;
		this.author = author;
		this.publishYear = publishYear;
		this.price = price;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publishYear=" + publishYear
				+ ", quantity=" + quantity + ", price=" + price + ", state=" + state + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
