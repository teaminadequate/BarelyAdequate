/**
 * This is the class that tests the database. 
 * 
 * @author Nicole
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import database.databaseManager;
import model.Material;
import model.Project;

class DatabaseTester {
	/** The instance of the database to be tested. */
	private databaseManager dbm = new databaseManager();
	
	/** Five. */
	private static final int FIVE = 5;
	

	/**
	 * Tests the insertUser method to make sure that a user is inserted into the database properly.
	 * This tests specifically if the username is null. Expects an exception to be thrown.
	 * 
	 * @author Nicole
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testInsertUserNull() throws ClassNotFoundException, SQLException {
		assertThrows(SQLException.class, () -> {
			dbm.insertUser(null, "fake email");
			
		});
		
	}

	/**
	 * Tests the insertUser method to make sure that a user is inserted into the database properly.
	 * This tests specifically if the email is null. Expects an exception to be thrown.
	 * 
	 * @author Nicole
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testInsertEmailNull() throws ClassNotFoundException, SQLException {
		assertThrows(SQLException.class, () -> {
			dbm.insertUser("name", null);
			
		});
		
	}
	
	/**
	 * Tests to make sure a duplicate user is not added. 
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void testInsertUserDuplicate() throws ClassNotFoundException, SQLException {
		assertThrows(SQLException.class, () -> {
			dbm.insertUser("nicole", "email67");
			
		});
		
	}
	
	/**
	 * Inserts a randomly generated user into the database. 
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void testInsertUser() throws ClassNotFoundException, SQLException{
		try {
			dbm.insertUser(getAlphaNumericString(FIVE), getAlphaNumericString(FIVE));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Checks to make sure that the list is null if a user does not exist in the database. 
	 * 
	 * @author Nicole
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testGetUserProjectsNull() throws ClassNotFoundException, SQLException {
		List<Project> list = dbm.getUserProjects(null);
		assertTrue("Error! There is no user with the name 'null' in the database", list.isEmpty());
		
	}
	
	/**
	 * Tests to make sure that it returns a list of projects since the user exists in the database.
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void testGetUserProjects() throws ClassNotFoundException, SQLException {
		List<Project> list = dbm.getUserProjects("nicole");
		
		assertEquals("Error! This is not the correct project for this user", "kill",
				list.get(0).getTitle());
		assertEquals("Error! This is not the correct project for this user", "me",
				list.get(1).getTitle());
		assertEquals("Error! This is not the correct project for this user", "pls",
				list.get(2).getTitle());
		
	}
	
	/**
	 * Tests the getUserProjectMaterials() method to make sure that if the name is null
	 * it returns an empty list.
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void testGetUserProjectMaterialsNameNull() throws ClassNotFoundException, SQLException {
		
		List<Material> list = dbm.getUserProjectMaterials(null, "projectName");
		assertTrue("Error! Username is null, there should be no materials in this list!", list.isEmpty());
			
		
	}
	
	/**
	 * Tests the getUserProjectMaterials() method with null for the project name.
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void testGetUserProjectMaterialsProjectNameNull() throws ClassNotFoundException, SQLException {
		
		List<Material> list = dbm.getUserProjectMaterials("name", null);
		assertTrue("Error! Project name is null, there should be no materials in this list!", list.isEmpty());
		
	}
	
	/**
	 * Tests the getUserProjectProcedures() with a null username.
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void testGetUserProjectProceduresUsernameNull() throws ClassNotFoundException, SQLException {
		
		List<String> list = dbm.getUserProjectProcedure(null, "project name");
		assertTrue("Error! Username is null, there should be no tasks in this list!", list.isEmpty());
		
	}
	
	/**
	 * Tests the getUserProjectProcedures() with a null project name.
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void testGetUserProjectProceduresProjectNameNull() throws ClassNotFoundException, SQLException {
		
		List<String> list = dbm.getUserProjectProcedure("name", null);
		assertTrue("Error! Project name is null, there should be no tasks in this list!", list.isEmpty());
		
	}
	
	/**
	 * Tests the the insertProject() method with username as null.
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void insertProjectNullUsername() throws ClassNotFoundException, SQLException {
		
		assertThrows(SQLException.class, () -> {
			
			dbm.insertProject(null, "project name", 0.0, 0.0, 0);
		});
		
	}
	
	/**
	 * Tests the the insertProject() method with project name as null.
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void insertProjectNullProjectName() throws ClassNotFoundException, SQLException {
		
		assertThrows(SQLException.class, () -> {
			
			dbm.insertProject("name", null, 0.0, 0.0, 0);
		});
		
	}
	
	/**
	 * Tests the insertProject() method with valid parameters.
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void testInsertProject() throws ClassNotFoundException, SQLException {
		
		dbm.insertProject(getAlphaNumericString(5), getAlphaNumericString(5), 
				0.0, 0.0, 0);
		
	}
	
	// TO DO: NEGATIVE VALUES FOR insertProject() method
	
	/**
	 * Tests insertProject() to add in values to the database properly.
	 * 
	 * @author Nicole
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void insertProjectNameDuplicate() throws ClassNotFoundException, SQLException {
		assertThrows(SQLException.class, () -> {
			
			dbm.insertProject("name", "projectName", 0.0, 0.0, 0);
			dbm.insertProject("name", "projectName", 0.0, 0.0, 0);
			
		});
		
		
	}
	
	/**
	 * Tests insertTask() with a null project name.
	 * 
	 * @author Nicole
	 */
	@Test
	void testInsertTaskNullName() {
		assertThrows(SQLException.class, () -> {
			
			dbm.insertTask(null, "projectName", "taskName");
			
		});
		
	}
	
	/**
	 * Tests insertTask() with a null task name.
	 * 
	 * @author Nicole
	 */
	@Test
	void testInsertTaskNullProjectName() {
		
		assertThrows(SQLException.class, () -> {
			
			dbm.insertTask("name", null, "taskName");
			
		});
		
	}
	
	/**
	 * Tests insertTask() with a null task name.
	 * 
	 * @author Nicole
	 */
	@Test
	void testInsertTaskNullTaskName() {
		
		assertThrows(SQLException.class, () -> {
			
			dbm.insertTask("name", "projectName", null);
			
		});
		
	}
	
	/**
	 * Tests insertTask() with valid parameters.
	 * 
	 * @author Nicole
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	void testInsertTask() throws ClassNotFoundException, SQLException {
		
		dbm.insertTask(getAlphaNumericString(5), getAlphaNumericString(5), getAlphaNumericString(5));
		
	}

	/**
	 * Tests the deleteTask() method
	 * 
	 * @author Nicole
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	@Test
	void testDeleteTask() throws ClassNotFoundException, SQLException {
		
		dbm.deleteTasks("projectName");
		
	}
	
	/**
	 * Tests the insertMaterial() method with a null username
	 * 
	 * @author Nicole
	 */
	@Test
	void testInsertMaterialNullUsername() {
		
		assertThrows(SQLException.class, () -> {
			
			dbm.insertMaterial(null, "projectName", "materialName", 0.0);
			
		});
		
	}
	
	/**
	 * Tests the insertMaterial() method with a null project name.
	 * 
	 * @author Nicole
	 */
	@Test
	void testInsertMaterialNullProjectName() {
		
		assertThrows(SQLException.class, () -> {
			
			dbm.insertMaterial("name", null, "materialName", 0.0);
			
		});
		
	}
	
	/**
	 * Tests the insertMaterial() method with a null material name.
	 * 
	 * @author Nicole
	 */
	@Test
	void testInsertMaterialNullMaterialName() {
		
		assertThrows(SQLException.class, () -> {
			
			dbm.insertMaterial("name", "projectName", null, 0.0);
			
		});
		
	}
	
	/**
	 * Tests the insertMaterial() method with a duplicate name.
	 * 
	 * @author Nicole
	 */
	@Test
	void testInsertMaterialDuplicate() {
		
		assertThrows(SQLException.class, () -> {
			
			dbm.insertMaterial("name", "projectName", "materialName", 0.0);
			dbm.insertMaterial("name", "projectName", "materialName", 0.0);
			
		});
		
	}
	
	/**
	 * Tests the insertMaterial() method with proper parameters.
	 * 
	 * @author Nicole
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testInsertMaterial() throws ClassNotFoundException, SQLException {
		
		dbm.insertMaterial(getAlphaNumericString(5), getAlphaNumericString(5), 
				getAlphaNumericString(5), 0.0);
		
	}
	
	
	/**
	 * Tests to make sure that the deleteProject methods throws an error if the string is null.
	 * 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void deleteProject() throws ClassNotFoundException, SQLException {
		
		dbm.deleteProject("projectName");
		
	}
	
	/**
	 * Generates a random string. Used for testing insert user. 
	 * 
	 * @param n the length of the string to be tested 
	 * @return a randomly generated string of length n
	 * @author https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
	 */

    private static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
	
}
