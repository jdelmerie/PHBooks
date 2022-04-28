package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.Book;

/**
 * Composant d'accès aux données de la table t_books dans la base de données phbooks
 * @author Delmerie JOHN ROSE
 *
 */
public class BookDao implements Dao<Book> {

	@Override
	public boolean create(Book obj) {
		String add = "insert into t_books (title, author, publishYear, price, state) values (?,?,?,?,?);";
		try (PreparedStatement pStatement = connection.prepareStatement(add)) {
			pStatement.setString(1, obj.getTitle());
			pStatement.setString(2, obj.getAuthor());
			pStatement.setInt(3, obj.getPublishYear());
			pStatement.setDouble(4, obj.getPrice());
			pStatement.setBoolean(5, obj.isState());
			if (pStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Book read(int id) {
		Book book = null;
		String queryById = "select * from t_books where id = ?";
		try (PreparedStatement pStatement = connection.prepareStatement(queryById)) {
			pStatement.setInt(1, id);
			try (ResultSet res = pStatement.executeQuery()) {
				res.next();
				String title = res.getString(2);
				String author = res.getString(3);
				int date = res.getInt(4);
				int quantity = res.getInt(5);
				double price = res.getDouble(6);
				boolean state = res.getBoolean(7);
				book = new Book(res.getInt(1), title, author, date, quantity, price, state);
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return book;
	}

	@Override
	public boolean update(Book obj) {
		String update = "update t_books set title = ?, author = ?, publishYear = ?, price = ?, state = ? where id = ?";
		try (PreparedStatement pStatement = connection.prepareStatement(update)) {
			pStatement.setString(1, obj.getTitle());
			pStatement.setString(2, obj.getAuthor());
			pStatement.setInt(3, obj.getPublishYear());
			pStatement.setDouble(4, obj.getPrice());
			pStatement.setBoolean(5, obj.isState());
			pStatement.setInt(6, obj.getId());
			if (pStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public boolean delete(Book obj) {
		String delete = "delete from t_books where id = ?";
		try (PreparedStatement pStatement = connection.prepareStatement(delete)) {
			pStatement.setInt(1, obj.getId());
			if (pStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public ArrayList<Book> selectAll() {
		ArrayList<Book> books = new ArrayList<Book>();
		try (Statement statement = connection.createStatement()) {
			String query = "select * from t_books";
			try (ResultSet res = statement.executeQuery(query)) {
				while (res.next()) {
					int id = res.getInt(1);
					String title = res.getString(2);
					String author = res.getString(3);
					int date = res.getInt(4);
					int quantity = res.getInt(5);
					double price = res.getDouble(6);
					boolean state = res.getBoolean(7);
					books.add(new Book(id, title, author, date, quantity, price, state));
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return books;
	}

	/**
	 * Méthode qui permet de sélectionner tous les livres correspond à une thématique
	 * @param id
	 * @return
	 */
	public ArrayList<Book> selectAllByThematic(int id) {
		ArrayList<Book> books = new ArrayList<Book>();
		String query = "select * from t_books inner join t_booksbythematics on t_booksbythematics.bookid = t_books.id where t_booksbythematics.thematicid = ?;";
		try (PreparedStatement pStatement = connection.prepareStatement(query)) {
			pStatement.setInt(1, id);
			try (ResultSet res = pStatement.executeQuery()) {
				while (res.next()) {
					String title = res.getString(2);
					String author = res.getString(3);
					int date = res.getInt(4);
					int quantity = res.getInt(5);
					double price = res.getDouble(6);
					boolean state = res.getBoolean(7);
					books.add(new Book(res.getInt(1), title, author, date, quantity, price, state));
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return books;
	}

}
