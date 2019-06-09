/**
 * @author Nicole
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;
import model.Project;
import model.User;

/**
 * This class contains all the testers for the User class.
 * 
 * @author Nicole
 */
class UserTests {
	
	/** The user. */
	private User user; 

	/**
	 * Tests the User constructor to make sure a user was created properly.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @author Nicole
	 */
	@Test
	void testUserConstructor() throws ClassNotFoundException, SQLException {
		user = new User("name", "email");
		assertEquals("Error, name is not correct", "name", user.getName());
		assertEquals("Error, email is not correct", "email", user.getEmail());
		
	}
	
	/**
	 * Tests the addProject() method with a null value.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @author Nicole
	 */
	@Test
	void testAddProjectNullProjectName() throws ClassNotFoundException, SQLException {
		user = new User("name", "email");
		assertThrows(NullPointerException.class, () -> {
			user.addProject(null);
			
		});
		
	}
	
	/**
	 * Tests the addProject() method with a null value.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @author Nicole
	 */
	@Test
	void testAddProject() throws ClassNotFoundException, SQLException {
		user = new User("name", "email");
		user.addProject(new Project());
		List<Project> projects = user.getUserProjects();
		assertEquals("Error, size of projects should be 2!", 2, projects.size());
		assertEquals("Error, there are no materials in this project!", 0, projects.get(0).getMaterials().size());
		assertEquals("Error, there are no tasks in this project!", 0, projects.get(0).getProcedure().size());
		
	}

}
