package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Testing {

	public static void main(String[] args) throws SQLException{
		
		Database db = HSQLDatabase.getInstance();
		
		db.executeUpdate("INSERT INTO users (user_name, email, password) VALUES ('Hamza', 'hamza@gmail.de', 'LK8933');");
		
		ResultSet result = db.executeQuery("SELECT * FROM users;");
        
        while (result.next()) {
        	
        	String id = result.getString(1);
        	String username = result.getString(2);
        	String email = result.getString(3);
        	String password = result.getString(4);
        
        	System.out.println(id + ", " + username + " " + email + " " + password); 
        	
        }
        
        result.close();
        //System.out.println(db.queryUserName("robin@gmx.de"));
        db.closeConnection();
        
	}
	
	
	
	

}
