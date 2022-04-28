package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.OrderItem;

/**
 * Composant d'accès aux données de la table t_ordersitems dans la base de données phbooks
 * @author Delmerie JOHN ROSE
 *
 */
public class OrderItemDao implements Dao<OrderItem> {

	@Override
	public boolean create(OrderItem obj) {
		String add = "insert into t_ordersitems (bookid, quantity, price, orderid) values (?,?,?,?);";
		try (PreparedStatement pStatement = connection.prepareStatement(add)) {
			pStatement.setInt(1, obj.getBookId());
			pStatement.setInt(2, obj.getQuantity());
			pStatement.setDouble(3, obj.getPrice());
			pStatement.setInt(4, obj.getOrderId());
			if (pStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public OrderItem read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(OrderItem obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(OrderItem obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<OrderItem> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
