package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import helpers.env.Environment;
import helpers.env.Factory;

public class Database {
	private static Connection connection;

//Instance should not be instantiated publicly
	private Database() {

	}

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				Environment env = Factory.getEnvInstance();
				Class.forName(env.getDriver());
				connection = DriverManager.getConnection(env.getDatabaseURL(), env.getDBUser(), env.getDBPass());
			}
			return connection;
		} catch(Exception e) {
			Logger.getLogger(Database.class.toString()).log(Level.SEVERE, e.toString());
			System.exit(0); // Cannot Start Application
		}
		return connection;
		
	}

}
