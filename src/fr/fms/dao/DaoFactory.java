package fr.fms.dao;

public class DaoFactory {

	public static BookDao getBookDao() {
		return new BookDao();
	}

	public static ThematicDao getThematicDao() {
		return new ThematicDao();
	}
}
