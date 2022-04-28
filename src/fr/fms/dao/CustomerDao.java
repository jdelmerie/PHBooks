package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.Customer;

/**
 * Composant d'accès aux données de la table t_customers dans la base de données phbooks
 * @author Delmerie JOHN ROSE
 *
 */
public class CustomerDao implements Dao<Customer> {

	@Override
	public boolean create(Customer obj) {
		String add = "insert into t_customers (email, password, firstname, lastname, address, phone) values (?,?,?,?,?,?);";
		try (PreparedStatement pStatement = connection.prepareStatement(add)) {
			pStatement.setString(1, obj.getEmail());
			pStatement.setString(2, obj.getPassword());
			pStatement.setString(3, obj.getFirstname());
			pStatement.setString(4, obj.getLastname());
			pStatement.setString(5, obj.getAddress());
			pStatement.setString(6, obj.getPhone());
			if (pStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public Customer read(int id) {
		Customer customer = null;

		String queryById = "select * from t_customers where id = ?";
		try (PreparedStatement pStatement = connection.prepareStatement(queryById)) {
			pStatement.setInt(1, id);
			try (ResultSet res = pStatement.executeQuery()) {
				res.next();
				String email = res.getString(2);
				String password = res.getString(3);
				String firstname = res.getString(4);
				String lastname = res.getString(5);
				String address = res.getString(6);
				String phone = res.getString(7);
				customer = new Customer(id, email, password, firstname, lastname, address, phone);
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return customer;
	}

	@Override
	public boolean update(Customer obj) {
		String update = "update t_customers set email = ?, password = ?,firstname = ?, lastname = ?, address = ?, phone = ?";
		try (PreparedStatement pStatement = connection.prepareStatement(update)) {
			pStatement.setString(1, obj.getEmail());
			pStatement.setString(2, obj.getPassword());
			pStatement.setString(3, obj.getFirstname());
			pStatement.setString(4, obj.getLastname());
			pStatement.setString(5, obj.getAddress());
			pStatement.setString(6, obj.getPhone());
			if (pStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public boolean delete(Customer obj) {
		String delete = "delete from t_customers where id = ?";
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
	public ArrayList<Customer> selectAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try (Statement statement = connection.createStatement()) {
			String query = "select * from t_customers";
			try (ResultSet res = statement.executeQuery(query)) {
				while (res.next()) {
					int id = res.getInt(1);
					String email = res.getString(2);
					String password = res.getString(3);
					String firstname = res.getString(4);
					String lastname = res.getString(5);
					String address = res.getString(6);
					String phone = res.getString(7);
					customers.add(new Customer(id, email, password, firstname, lastname, address, phone));
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return customers;
	}

}
