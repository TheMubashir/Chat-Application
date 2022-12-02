package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {

	private static Database instance = null;
    
    public static Database getInstance() throws SQLException 
    {
        
        if (instance == null) 
        {
            
            instance = new HSQLDatabase();
        }
        
        return instance;
        
    }
    
    public abstract ResultSet executeQuery(String query) throws SQLException;

	public abstract int executeUpdate(String string) throws SQLException;

	public abstract void closeConnection();
	
	public abstract String queryUserName(String qEmail);
    
}
