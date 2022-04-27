package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.dao.BookDao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.ThematicDao;
import fr.fms.entities.Book;
import fr.fms.entities.Thematic;

public class IBusinessBookImpl implements IBusinessBook {

	private HashMap<Integer, Book> cart;
	private BookDao bookDao = DaoFactory.getBookDao();
	private ThematicDao thematicDao = DaoFactory.getThematicDao();

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

	public Thematic getOneThematic(int id) {
		return thematicDao.read(id);
	}

	public boolean isCartEmpty() {
		return cart.isEmpty();
	}

}
