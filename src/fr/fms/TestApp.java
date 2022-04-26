package fr.fms;

import fr.fms.dao.BookDao;
import fr.fms.dao.ThematicDao;
import fr.fms.entities.Book;

public class TestApp {

	public static void main(String[] args) {

		BookDao bookDao = new BookDao();
//
//		System.out.println("All books");
//		bookDao.selectAll().forEach(book -> System.out.println(book));
//		System.out.println("Avant modif : " + bookDao.read(21));
//		Book book = bookDao.read(23);
//		book.setAuthor("Delmerie JOHN ROSE");
//		bookDao.update(book);
//		System.out.println("Après modif : " + bookDao.read(21));
//		
//		bookDao.delete(book);
		
		ThematicDao thematicDao = new ThematicDao();
		
		thematicDao.selectAll().forEach(th -> System.out.println(th));
		bookDao.selectAllByThematic(2
				).forEach(test -> System.out.println(test));

	}

}
