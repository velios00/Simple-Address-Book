package ConnessioneDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessionePG {

	// ATTRIBUTI
	private static ConnessionePG instance;
	private Connection connection = null;
	private String nome = "postgres";
	private String password = "progettoDB";
	private String url = "jdbc:postgresql://localhost:5432/RubricaDef";
	private String driver = "org.postgresql.Driver";

	// COSTRUTTORE
	public ConnessionePG() throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, nome, password);

		} catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
		}

	}

	public Connection getConnection() {
			return connection;
	}
	
	public static ConnessionePG getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConnessionePG();
		} else if (instance.getConnection().isClosed()) {
			instance = new ConnessionePG();
		}
		return instance;
	}
}