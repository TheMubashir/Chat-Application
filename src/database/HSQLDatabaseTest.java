/*package database;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HSQLDatabaseTest {

	Database db = HSQLDatabase.getInstance();
	
	@org.junit.jupiter.api.Test
	
	
	
	@HSQLDatabaseTest
	 public void testQueryInsertUser() {
		
	    String userName = "John";
	    String email = "john@example.com";
	    String password = "123456";
	    assertTrue(db.queryInsertUser(userName, email, password));
	    
	 }
	
	
	@HSQLDatabaseTest
	 public void testQueryChangeUsername() {
	    int id = 1;
	    String userName = "Jane";
	    assertTrue(db.queryChangeUsername(id, userName));
	    
	 }
	
	
	
	@HSQLDatabaseTest
	 public void testQueryChangeEmail() {
		
	    int id = 1;
	    String email = "jane@example.com";
	    assertTrue(db.queryChangeEmail(id, email));
	  
	 }
	 
	 
	@HSQLDatabaseTest
  	 public void testQueryChangePassword() {
  	  
	    int id = 1;
	    String password = "654321";
	    assertTrue(db.queryChangePassword(id, password));
      
	 }
	 
	 
	@HSQLDatabaseTest
	 public void testQueryDeleteUser() {
		
	    int id = 1;
	    assertTrue(db.queryDeleteUser(id));
	    
	 }
	
	
	@HSQLDatabaseTest
	  public void testQueryAddMessage() {
	    int senderId = 1;
	    int receiverId = 2;
	    String message = "Hello";
	    Timestamp time = new Timestamp(System.currentTimeMillis());
	    assertTrue(db.queryAddMessage(senderId, receiverId, message, time));
	    
	 }

}
*/