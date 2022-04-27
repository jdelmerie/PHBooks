package fr.fms;

import fr.fms.business.IBusinessBookImpl;
import fr.fms.dao.BookDao;
import fr.fms.dao.CustomerDao;
import fr.fms.dao.ThematicDao;
import fr.fms.entities.Book;
import fr.fms.entities.Customer;

public class TestApp {

	public static void main(String[] args) {

		BookDao bookDao = new BookDao();

		System.out.println("All books");
		bookDao.selectAll().forEach(book -> System.out.println(book));
		System.out.println("Avant modif : " + bookDao.read(21));
		Book book = bookDao.read(23);
		book.setAuthor("Delmerie JOHN ROSE");
		bookDao.update(book);
		System.out.println("Après modif : " + bookDao.read(21));
		
		bookDao.delete(book);
		
		ThematicDao thematicDao = new ThematicDao();
		
		thematicDao.selectAll().forEach(th -> System.out.println(th));
		bookDao.selectAllByThematic(2
				).forEach(test -> System.out.println(test));
		
		IBusinessBookImpl bookJob = new IBusinessBookImpl();
		bookJob.selectAllBooks().forEach(b -> System.out.println(b));
	
		CustomerDao customerDao = new CustomerDao();
		customerDao.selectAll().forEach(cus -> System.out.println(cus));
		customerDao.create(new Customer("delmerie@jr.com", "del123", "delmerie", "john rose", "5 rue de Gaillac"));
		Customer delCustomer = customerDao.read(2);
		System.out.println("avant maj : " + delCustomer);
		delCustomer.setPhone("0123456789");
		customerDao.update(delCustomer);
		System.out.println("apres maj : " + customerDao.read(2));
		customerDao.delete(customerDao.read(7));
		System.out.println("après supp");
		customerDao.selectAll().forEach(cus -> System.out.println(cus));
	}

}
