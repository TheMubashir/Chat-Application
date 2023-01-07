package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;

public  interface Database {

	public void databaseConnection() throws SQLException, ClassNotFoundException;
    
    public void closeConnection();
    
    public ResultSet executeQuery(String query) throws SQLException; 
    
    public int executeUpdate(String string) throws SQLException, ClassNotFoundException;
    
    public void queryCreateTables() throws SQLException;
    
    public String queryFindByEmail(String email); 
    
    public boolean queryValidateID(Integer id);
    
    public String queryInsertUser(String userName, String email, String password);
    
    public int verifyLoginCredentials(String email, String password);
    
    public List<String> queryDisplayUsers(int id) throws SQLException;
    
    public List<String[]> querySearchUsers(String name);
    
    public int queryChangeUsername(Integer id, String userName);
    
    public int queryChangeEmail(Integer id, String email);
    
    public int queryChangePassword(Integer id, String password);
    
    public int queryDeleteUser(Integer id);
    
    public int queryAddMessage(Integer senderID, Integer receiverID, String message, Timestamp Time);
    
    public List<String> queryGetMessages(Integer id);
    
    public int queryDeleteSelectedMessages(Integer senderId, Integer receiverId, List<Integer> messageId);
	
}
