package fr.fms.dao;

public class DaoFactory {

	public static BookDao getBookDao() {
		return new BookDao();
	}

	public static ThematicDao getThematicDao() {
		return new ThematicDao();
	}
	
	public static CustomerDao getCustomerDao() {
		return new CustomerDao();
	}
	
	public static OrderDao getOrderDao() {
		return new OrderDao();
	}
	
	public static OrderItemDao getOrderItemDao() {
		return new OrderItemDao();
	}
}
