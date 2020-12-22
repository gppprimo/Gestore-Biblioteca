package DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {

	protected static Connection conn;
	final protected static ConnectionFactory conn_factory=new H2ConnectionFactory();
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
		        conn = conn_factory.createConnection();
		    } catch(Exception e) {
		        e.printStackTrace();
	        }
		}
		return conn;
	}
	
	public static void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}
	
}
