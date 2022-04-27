package fr.fms.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import fr.fms.dao.BookDao;
import fr.fms.dao.CustomerDao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.OrderDao;
import fr.fms.dao.OrderItemDao;
import fr.fms.dao.ThematicDao;
import fr.fms.entities.Book;
import fr.fms.entities.Customer;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
import fr.fms.entities.Thematic;

public class IBusinessBookImpl implements IBusinessBook {

	private HashMap<Integer, Book> cart;
	private BookDao bookDao = DaoFactory.getBookDao();
	private ThematicDao thematicDao = DaoFactory.getThematicDao();
	private CustomerDao customerDao = DaoFactory.getCustomerDao();
	private OrderDao orderDao = DaoFactory.getOrderDao();
	private OrderItemDao orderItemDao = DaoFactory.getOrderItemDao();

	public IBusinessBookImpl() {
		cart = new HashMap<Integer, Book>();
	}

	@Override
	public ArrayList<Book> selectAllBooks() {
		return bookDao.selectAll();
	}

	@Override
	public ArrayList<Book> selectAllBooksByThematic(int id) {
		return bookDao.selectAllByThematic(id);
	}

	@Override
	public ArrayList<Thematic> selectThematics() {
		return thematicDao.selectAll();
	}

	@Override
	public void addToCart(Book book) {
		Book tmp = cart.get(book.getId());
		if (tmp != null) {
			tmp.setQuantity(tmp.getQuantity() + 1);
		} else {
			cart.put(book.getId(), book);
		}
	}

	@Override
	public boolean removeFromCart(int id) {
		Book book = cart.get(id);
		if (book != null) {
			if (book.getQuantity() == 1) {
				cart.remove(id);
			} else {
				book.setQuantity(book.getQuantity() - 1);
			}
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Book> getCart() {
		return new ArrayList<Book>(cart.values());
	}

	@Override
	public Book getOneBook(int id) {
		return bookDao.read(id);
	}

	@Override
	public Thematic getOneThematic(int id) {
		return thematicDao.read(id);
	}
	
	@Override
	public boolean order(int custormerId) {
		if (customerDao.read(custormerId) != null) {
			double totalAmount = getTotal();
			Order order = new Order(totalAmount, new Date(), custormerId);
			if (orderDao.create(order)) {
				for (Book book : getCart()) {
					orderItemDao
							.create(new OrderItem(0, book.getId(), book.getQuantity(), book.getPrice(), order.getId()));
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean createCustomerAccount(Customer customer) {
		return customerDao.create(customer);
	}

	public Customer existCustomer(String email, String password) {
		Customer customer = null;
		for (Customer cus : customerDao.selectAll()) {
			if (cus.getEmail().equalsIgnoreCase(email) && cus.getPassword().equals(password)) {
				customer = new Customer(cus.getId(), cus.getEmail(), cus.getPassword(), cus.getFirstname(),
						cus.getLastname(), cus.getAddress(), cus.getPhone());
			}
		}
		return customer;
	}

	
	public boolean isCartEmpty() {
		return cart.isEmpty();
	}

	public void clearCart() {
		cart.clear();
	}

	public double getTotal() {
		double total = 0;
		for (Book book : cart.values()) {
			total += book.getPrice() * book.getQuantity();
		}
		return total;
	}

}
