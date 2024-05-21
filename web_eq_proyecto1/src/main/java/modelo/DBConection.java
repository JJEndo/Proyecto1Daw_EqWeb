package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConection {
	
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/eq";
	public static Connection instance = null;
	
	private DBConection() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		
		if(instance == null) {
			//opcional
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "");
			props.put("charset", "UTF-8");

			
			instance = DriverManager.getConnection(JDBC_URL, props);	
		}
		return instance;
		
	}
		

}
