package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HSQLDatabase extends Database{

	Connection connection;

	
	protected HSQLDatabase() throws SQLException 
	{
		
		try {
            
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
          
       } catch (Exception e) {
           
    	   System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
           e.printStackTrace();
           return;
       }
		

		connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\Users\\Mubashir Ahmed\\Documents\\Database\\Database; hsqldb.lock_file=false; shutdown = true", "myDB", "PK313");
		
        connection.setAutoCommit(true);
	}
	
	@Override
	public ResultSet executeQuery(String query) throws SQLException 
	{
		
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		return result;
		
	}
	
	@Override
	public int executeUpdate(String string) throws SQLException
	{
		
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate(string);
		System.out.println(result);
		connection.commit();
		return result;
		
	}
	
	@Override
	public void closeConnection()  // Closes the connection with database
	{
		
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	
	@Override
	public String queryUserName(String qEmail){
		
		ResultSet result;
		try {
			
			String query = "SELECT user_name FROM users WHERE email=\'" + qEmail + "\'";
			System.out.println(query);
			result = executeQuery(query);
			result.next();
			
			String temp = result.getString(1);
			result.close();
			return temp;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
			
		}
			
	}
	
	
}
