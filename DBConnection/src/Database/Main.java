package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException{
		
		Database db = Database.getInstance();
		
		db.executeUpdate("INSERT INTO users (user_name, email, password) VALUES ('Mubashir', 'mubashir@gmail.de', 'LK8933');");
		
		ResultSet result = db.executeQuery("SELECT * FROM users;");
        
        while (result.next()) {
        	
        	String id = result.getString(1);
        	String username = result.getString(2);
        	String email = result.getString(3);
        	String password = result.getString(4);
        
        	System.out.println(id + ", " + username + " " + email + " " + password); 
        	
        }
        
        result.close();
        System.out.println(db.queryUserName("robin@gmx.de"));
        db.closeConnection();
        
	}

}
