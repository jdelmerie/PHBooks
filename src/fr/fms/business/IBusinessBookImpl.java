package fr.fms.business;

import java.util.ArrayList;

import fr.fms.dao.BookDao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.ThematicDao;
import fr.fms.entities.Book;
import fr.fms.entities.Thematic;

public class IBusinessBookImpl implements IBusinessBook {
	
	private BookDao bookDao = DaoFactory.getBookDao();
	private ThematicDao thematicDao = DaoFactory.getThematicDao();
	
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

	
	
	
	public Thematic getOneThematic(int id) {
		return thematicDao.read(id);
	}
}
