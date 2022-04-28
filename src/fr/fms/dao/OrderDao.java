package fr.fms.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Order;

/**
 * Composant d'accès aux données de la table t_orders dans la base de données phbooks
 * @author Delmerie JOHN ROSE
 *
 */
public class OrderDao implements Dao<Order> {

	@Override
	public boolean create(Order obj) {
		String add = "insert into t_orders (totalamount, date, customerid) values (?,?,?);";
		try (PreparedStatement pStatement = connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS)) {
			pStatement.setDouble(1, obj.getTotalAmount());
			pStatement.setDate(2, new java.sql.Date(obj.getDate().getTime()));
			pStatement.setInt(3, obj.getCustomerId());
			pStatement.executeUpdate();
			try (ResultSet generatedKeySet = pStatement.getGeneratedKeys()) {
				if (generatedKeySet.next()) {
					obj.setId(generatedKeySet.getInt(1));
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Order read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Order obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Order obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Order> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
