package DataBaseConnection;

import java.sql.Connection;

public interface ConnectionFactory {

	public Connection createConnection() throws Exception;
	
}
