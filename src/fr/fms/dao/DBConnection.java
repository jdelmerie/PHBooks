package fr.fms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static Connection connection = null;
	private static Properties prop;

	private DBConnection() {
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				prop = ConfigFile.getConfig("files/config.properties");
				Class.forName(prop.getProperty("db.driver.class"));

				String url = prop.getProperty("db.url");
				String login = prop.getProperty("db.login");
				String psw = prop.getProperty("db.password");
				connection = DriverManager.getConnection(url, login, psw);
			} catch (IOException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		return connection;
	}
}
