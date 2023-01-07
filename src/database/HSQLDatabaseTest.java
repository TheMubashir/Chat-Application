package database;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import java.sql.Timestamp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

	
 @TestMethodOrder(OrderAnnotation.class)
 class HSQLDatabaseTest {
	
	 
	 @Test
	 @Order(2)
	 void testFail() {
		 
		fail("Is going to fail anyway");
			
	 }
	 
	 
	 
	 @Test
	 @Order(3)
	 void databaseSingletonCheck() throws ClassNotFoundException {
			
		 try {
				
				Database connection1 = HSQLDatabase.getInstance();
				Database connection2 = HSQLDatabase.getInstance();
				assertTrue(connection1 == connection2);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				fail();
				
			}
		}
	 
	 
	 @Test
	 @Order(1)
	 void testCreateTableUsersContent() {
			
		 try {
				
				try {
					
					Database db;
					
					db = HSQLDatabase.getInstance();
					
					db.executeQuery("INSERT INTO users (user_name, email, password) VALUES ('Ali', 'ali@gmail.de', 'AR8933');");
					db.executeQuery("INSERT INTO users (user_name, email, password) VALUES ('Bilal', 'bilal@gmail.de', 'BA8933');");
					
				} catch (ClassNotFoundException e) {
			
					e.printStackTrace();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				fail();
				
			}
		}
	 
	 
	 
	 @Test
	 @Order(2)
	 void testCreateTableChatsContent() {
		
		 try {
			 
			 Database db = HSQLDatabase.getInstance();
				
			 int senderId = 1;
			 int receiverId = 2;
			 String message = "Hello";
			 Timestamp time = new Timestamp(System.currentTimeMillis());
			 assertTrue(db.queryAddMessage(senderId, receiverId, message, time));
			 		 
		 } catch (SQLException e) {
			 
				e.printStackTrace();
				fail();
				
		 }
	    
	 }
	 
	 
	
	@Test
	@Order(3)
	void testQueryInsertUser() {
		
		Database db = HSQLDatabase.getInstance();
		
	    String userName = "John";
	    String email = "john@example.com";
	    String password = "123456";
	    assertTrue(db.queryInsertUser(userName, email, password));
	    
	 }
	
	
	
	@Test
	@Order(4)
	void testQueryChangeUsername() {
		
		Database db = HSQLDatabase.getInstance();
		
	    int id = 1;
	    String userName = "Jane";
	    assertTrue(db.queryChangeUsername(id, userName));
	    
	 }
	
	
	
	@Test
	@Order(5)
	void testQueryChangeEmail() {
		
		Database db = HSQLDatabase.getInstance();
		
	    int id = 1;
	    String email = "jane@example.com";
	    assertTrue(db.queryChangeEmail(id, email));
	  
	 }
	 
	
	
	@Test
	@Order(6)
  	void testQueryChangePassword() {
  	  
		Database db = HSQLDatabase.getInstance();
		
	    int id = 1;
	    String password = "654321";
	    assertTrue(db.queryChangePassword(id, password));
      
	 }
	 
	
	 
	@Test
	@Order(4)
	void testQueryDeleteUser() {
		
		Database db = HSQLDatabase.getInstance();
		
	    int id = 1;
	    assertTrue(db.queryDeleteUser(id));
	    
	 }
	
	
	
	@Test
	@Order(5)
	void checkTableContents() throws ClassNotFoundException {
		
		try {
			
			ResultSet result = HSQLDatabase.getInstance().executeQuery("SELECT * FROM USERS;");
			
			assertTrue(result.next());
			assertEquals(result.getString("text"), "Hello world!");
			assertTrue(result.next());
			assertEquals(result.getString("text"), "abcdef");
			assertTrue(result.next());
			assertEquals(result.getString("text"), "");
			assertTrue(result.next());
			assertEquals(result.getString("text"), null);
			assertFalse(result.next());
			
			result = HSQLDatabase.getInstance().executeQuery("SELECT * FROM CHATS;");
			assertTrue(result.next());
			assertEquals(result.getDouble("number"), 3.14);
			assertFalse(result.next());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			fail();
			
		}		
	}	

}
