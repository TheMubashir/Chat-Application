package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public  interface Database {

	public void databaseConnection() throws SQLException;
    
    public void closeConnection(); // Do we need this method to close connection to the database or is it already included in the Java.connection;
    
    public ResultSet executeQuery(String query) throws SQLException; // Not sure whether we need this method or not 
    
    public int executeUpdate(String string) throws SQLException; // Not sure whether we need this method or not 
    
    public String queryUserName(String qEmail);  // Ye method waisy hi shuru mein banaya tha database ki testing ky liye
    
    public void queryCreateTables() throws SQLException;
    
    public int queryInsertUser(String userName, String email, String password);
    
    public boolean queryValidateEmail(String email); 
    
    public int queryChangeUserName(Integer id, String userName);
    
    public int queryChangeEmail(Integer id, String email);
    
    public int queryChangePassword(Integer id, String password);
    
    public int queryDeleteUser(Integer id);
    
    public String queryAddMessage(Integer senderID, Integer receiverID, String message, Timestamp Time);
    
    public String queryGetMessages(Integer id);
	
}
