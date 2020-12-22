package DataBaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;

public class H2ConnectionFactory implements ConnectionFactory{

	@Override
	public Connection createConnection() throws Exception {
		// TODO Auto-generated method stub
		
		Class.forName("org.h2.Driver");
		return DriverManager.getConnection("jdbc:h2:~/DB", "sa", "");
		
	}

}
