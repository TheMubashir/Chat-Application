package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class HSQLDatabase implements Database{

	Connection connection;
	public static HSQLDatabase instance = null;
	
    
	public static HSQLDatabase getInstance() throws SQLException 
	{ 
		// Singleton pattern implementation
		// Only one instance will be created and will be reused for each object
		
		
		HSQLDatabase result = instance;
        
    	if (result == null) {
    		
    		synchronized (Database.class ) {
    			
    			result = instance;
    			if (result == null) 
                {
                    
                     result = new HSQLDatabase();
                }	
    		}	
    	}
    	  
        return result;
	
	}


	
	public void databaseConnection() throws SQLException 
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
	public void closeConnection() 
	{
		// Closes the connection to the database
		
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
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



	@Override
	public void queryCreateTables() throws SQLException {
		
		try {
			
			db.executeQuery("CREATE TABLE IF NOT EXISTS USERS (id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL, User_Name VARCHAR(25) NOT NULL, Email VARCHAR(50) NOT NULL, Password VARCHAR(15) NOT NULL, Profile_Picture VARCHAR(255));");
			db.executeQuery("CREATE TABLE IF NOT EXISTS CHATS (Sender_ID INT NOT NULL, Message VARCHAR(255) NOT NULL, Receiver_ID INT NOT NULL, Time TIMESTAMP NOT NULL, FOREIGN KEY (Sender_ID) references users(ID));");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}



	@Override
	public int queryInsertUser(String userName, String email, String password) {
		
		if(queryValidateEmail(email) == true) {
			
			return 0;
			
		}
		
		else {
			
	
			try {
				
				PreparedStatement ps;
				ps = db.prepareStatement("INSERT INTO USERS(USER_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?)");
				ps.setString(1, userName);
				ps.setString(2, email);
				ps.setString(3, password);
				
				ps.executeUpdate();
				db.close();
				return 1;		
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}
	
	
	
	@Override
	public boolean queryValidateEmail(String email) {
		
		boolean status = false;
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			connection.close();;
		} catch (SQLException e){
			
			e.printStackTrace();
			
		}
		
		return status;
		
	}



	@Override
	public int queryChangeUserName(Integer id, String userName) {
		
		PreparedStatement ps = connection.prepareStatement("UPDATE USERS SET USER_NAME = ? WHERE ID = ?");
		ps.setString(1, userName);
		ps.setInt(2, id);
		ps.executeUpdate();
		connection.close();
		
		return 1;
		
	}
	
	
	
	@Override
	public int queryChangeEmail(Integer id, String email) {
		
		PreparedStatement ps = connection.prepareStatement("UPDATE USERS SET email = ? WHERE ID = ?");
		ps.setString(1, email);
		ps.setInt(2, id);
		ps.executeUpdate();
		connection.close();
		
		return 1;
	}
	
	
	
	@Override
	public int queryChangePassword(Integer id, String password) {
		
		PreparedStatement ps = connection.prepareStatement("UPDATE USERS SET PASSWORD = ? WHERE ID = ?");
		ps.setString(1, password);
		ps.setInt(2, id);
		ps.executeUpdate();
		connection.close();
		
		return 1;
		
	}
	
	
	
	@Override
	public int queryDeleteUser(Integer id) {
		
		try {
			Database connection = HSQLDatabase.getInstance();
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
			
		PreparedStatement ps = connection.prepareStatement("DELETE FROM USERS WHERE ID = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
		connection.close();
		return 1;
		
	}

	

	@Override
	public String queryAddMessage(Integer senderID, Integer receiverID, String message, Timestamp time) {
		
		PreparedStatement ps = connection.prepareStatement("INSERT INTO CHATS(SENDER_ID, MESSAGE, RECEIVER_ID, TIME) VALUES (?, ?, ?)");
		ps.setInt(1, senderID);
		ps.setInt(2, receiverID);
		ps.setString(3, message);
		ps.setTime(4,time);
		ps.executeUpdate();
		connection.close();
		
		return 1;
	}
	
	
	
	@Override
	public String queryGetMessages(Integer id) {
		
		
		
		return null;
	}
	
		
}
