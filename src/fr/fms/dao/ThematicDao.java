package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Thematic;

/**
 * Composant d'accès aux données de la table t_thematics dans la base de données phbooks
 * @author Delmerie JOHN ROSE
 *
 */
public class ThematicDao implements Dao<Thematic> {

	@Override
	public boolean create(Thematic obj) {
		String add = "insert into t_thematics (name) values (?);";
		try (PreparedStatement pStatement = connection.prepareStatement(add)) {
			pStatement.setString(1, obj.getName());
			if (pStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public Thematic read(int id) {
		Thematic thematic = null;
		String queryById = "select * from t_thematics where id = ?";
		try (PreparedStatement pStatement = connection.prepareStatement(queryById)) {
			pStatement.setInt(1, id);
			try (ResultSet res = pStatement.executeQuery()) {
				res.next();
				String name = res.getString(2);
				thematic = new Thematic(id, name);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return thematic;
	}

	@Override
	public boolean update(Thematic obj) {
		String update = "update into t_thematics (name) values (?);";
		try (PreparedStatement pStatement = connection.prepareStatement(update)) {
			pStatement.setString(1, obj.getName());
			if (pStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public boolean delete(Thematic obj) {
		String update = "delete from t_thematics where id = ?;";
		try (PreparedStatement pStatement = connection.prepareStatement(update)) {
			pStatement.setInt(1, obj.getId());
			if (pStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public ArrayList<Thematic> selectAll() {
		ArrayList<Thematic> thematics = new ArrayList<Thematic>(); 
		try (Statement statement = connection.createStatement()) {
			String query = "select * from t_thematics";
			try (ResultSet res = statement.executeQuery(query)) {
				while (res.next()) {
					int id = res.getInt(1);
					String name = res.getString(2);
					thematics.add(new Thematic(id, name));
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return thematics;
	}

}
