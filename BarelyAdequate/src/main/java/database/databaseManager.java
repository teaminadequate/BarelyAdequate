/***
 * This is the class that manages all 
 * 
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import model.Bill;
import model.Project;
import model.Material;

/**
 * Manages connection to database, and the fields stored in it.
 * @author Gavin Montes
 * @author Kyle Bittner
 */
public class databaseManager {
	/**
	 * Main method to test db without the GUI.
	 * @author Kyle Bittner
	 * @param args 
	 * @throws SQLException
	 */
	public static void main(String args[]) {

		databaseManager manager = new databaseManager();
		try {
			manager.getUserProjects("nicole");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Connects to the database
	 * @author Kyle Bittner
	 * @return the connection to the database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");

		String url = "jdbc:sqlite:users.db";

		Connection connect = null;
		
		connect = DriverManager.getConnection(url);
		
		return connect;
	}
	/**
	 * Inserts a user into the database with name and email fields.
	 * @author Gavin Montes
	 * @param username the name of the user being inserted.
	 * @param email the email of the user to be stored in the db.
	 * @throws SQLException if the connection/ insert fails
	 * @throws ClassNotFoundException when the connection fails
	 */
	public void insertUser(String username, String email) throws SQLException, ClassNotFoundException {

		String sql = "INSERT INTO users(USERNAME,EMAIL) VALUES(?,?)";
		
		Connection connect = this.connect();
		
		PreparedStatement state = connect.prepareStatement(sql);

		state.setString(1, username);

		state.setString(2, email);

		state.executeUpdate();
		
	}
	/**
	 * Retrieves a list of the given user's projects
	 * @author Gavin Montes
	 * @param userName name of the user whose projects we are retrieving.
	 * @return A list of the user's projects as project objects.
	 * @throws SQLException if the connection/select operation fails
	 * @throws ClassNotFoundException if the connection fails
	 */
	public ArrayList<Project> getUserProjects(String userName) throws SQLException, ClassNotFoundException {
		String sql = "SELECT USERNAME, PROJECTNAME, PREBILL, POSTBILL, DIFFICULTY FROM projects";

		ArrayList<Project> projectList = new ArrayList<Project>();

		Connection connect = this.connect();

		Statement state = connect.createStatement();

		ResultSet set = state.executeQuery(sql);

		while (set.next()) {
			if (set.getString("USERNAME").equals(userName)) {
				Project p = new Project();
				p.setTitle(set.getString("PROJECTNAME"));
				p.setCurrentBill(set.getDouble("PREBILL"));
				p.setProjectedBill(set.getDouble("POSTBILL"));
				p.setDiff(set.getInt("DIFFICULTY"));
				p.setMaterials(getUserProjectMaterials(userName, p.getTitle()));
				p.setProcedure(getUserProjectProcedure(userName, p.getTitle()));
				projectList.add(p);
			}
		}
		return projectList;
	}
	/**
	 * Retrieves the list of procedure steps from the db based on the given user and project name.
	 * @author Gavin Montes
	 * @param userName the name of the user whose project we are accessing
	 * @param projectName the name of the project whose procedure we are accessing
	 * @return a list of the procedure steps for the given user's specified project.
	 * @throws SQLException if the connection/select fails
	 * @throws ClassNotFoundException if the connection fails
	 */
	public ArrayList<String> getUserProjectProcedure(String userName, String projectName) 
			throws SQLException, ClassNotFoundException {
		
		String sql = "SELECT USERNAME, PROJECTNAME, TASKDESCRIPTION FROM tasks";

		ArrayList<String> procedure = new ArrayList<String>();

		Connection connect = this.connect();

		Statement state = connect.createStatement();

		ResultSet set = state.executeQuery(sql);
		
		while (set.next()) {
			if (set.getString("USERNAME").equals(userName) && set.getString("PROJECTNAME").equals(projectName)) {
				String task = set.getString("TASKDESCRIPTION");
				procedure.add(task);
			}
		}
		return procedure;
	}
	/**
	 * Retrieves the list of material objects from the db based on the given user and project name.
	 * @author Gavin Montes
	 * @param userName the name of the user whose project we are accessing
	 * @param projectName the name of the project whose procedure we are accessing
	 * @return a list of the material objects for the given user's specified project.
	 * @throws SQLException if the connection/select fails
	 * @throws ClassNotFoundException if the connection fails
	 */
	public ArrayList<Material> getUserProjectMaterials(String userName, String projectName) 
			throws SQLException, ClassNotFoundException {
		String sql = "SELECT USERNAME, PROJECTNAME, MATERIALNAME, PRICE FROM materials";

		ArrayList<Material> materialsList = new ArrayList<Material>();

		Connection connect = this.connect();

		Statement state = connect.createStatement();

		ResultSet set = state.executeQuery(sql);
		
		while (set.next()) {
			
			if (set.getString("USERNAME").equals(userName) && set.getString("PROJECTNAME").equals(projectName)) {
				Material m = new Material(set.getString("MATERIALNAME"), set.getDouble("PRICE"));
				materialsList.add(m);
				
			}
			
		}
		return materialsList;
	}
	/**
	 * Inserts a project and all it's fields into the project table in the 
	 * db (does not insert materials or procedure steps).
	 * @author Gavin Montes
	 * @param userName the name of the user whose project we are inserting
	 * @param projectName the name of the project that we are inserting
	 * @param preBill the current bill field of the project we are inserting
	 * @param postBill the projected bill field of the project we are inserting
	 * @param diff the difficulty (scale of 1 to 10) of the project we are inserting
	 * @throws SQLException if the connection/select fails
	 * @throws ClassNotFoundException if the connection fails
	 */
	public void insertProject(String userName, String projectName, double preBill, double postBill, int diff)
			throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO projects(USERNAME, PROJECTNAME, PREBILL, POSTBILL, DIFFICULTY) VALUES (?,?,?,?,?)";
		
		Connection connect = this.connect();
		
		PreparedStatement state = connect.prepareStatement(sql);
		
		state.setString(1, projectName);

		state.setString(2, userName);

		state.setDouble(3, preBill);

		state.setDouble(4, postBill);

		state.setInt(5, diff);

		state.executeUpdate();
		
	}
	/**
	 * Removes a project of the given name from the db 
	 * (does not remove this project's materials or procedure steps).
	 * @author Gavin Montes
	 * @param projectName the name of the project we are deleting
	 * @throws SQLException if the connection/ deletion fails
	 * @throws ClassNotFoundException if the connection fails.
	 */
	public void deleteProject(String projectName) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM projects WHERE PROJECTNAME  = ?";
		
		Connection connect = this.connect();

		PreparedStatement state = connect.prepareStatement(sql);

		state.setString(1, projectName);

		state.executeUpdate();
		
	}
	/**
	 * Inserts a task into the task table of the db
	 * under the given user's given project.
	 * @param userName the name of the user whose project we are inserting the material into
	 * @param projectName the name of the project whose material we are inserting.
	 * @param task the description of the task we are inserting
	 * @throws SQLException if the connection/insertion fails
	 * @throws ClassNotFoundException if the connection fails
	 */
	public void insertTask(String userName, String projectName, String task) throws SQLException, ClassNotFoundException {

		String sql = "INSERT INTO tasks(USERNAME,PROJECTNAME,TASKDESCRIPTION) VALUES(?,?,?)";
		
		Connection connect = this.connect();

		PreparedStatement state = connect.prepareStatement(sql);

		state.setString(1, userName);
		
		state.setString(2, projectName);

		state.setString(3, task);
		
		state.executeUpdate();
	}
	/**
	 * Deletes all tasks from the given user's given project in the db.
	 * @param userName the name of the user whose project the materials belong to.
	 * @param projectName the name of the project these materials belong to.
	 * @throws ClassNotFoundException if the connection/deletion fails
	 * @throws SQLException if the connection fails
	 */
	public void deleteTasks(String userName, String projectName) throws ClassNotFoundException, SQLException {

		String sql = "DELETE FROM tasks WHERE (USERNAME,PROJECTNAME)  = (?,?)";
		
		Connection connect = this.connect();

		PreparedStatement state = connect.prepareStatement(sql);

	    state.setString(1, projectName);

	    state.executeUpdate();
	}
	/**
	 * Inserts a material into the db under the given user's given project.
	 * @param username the name of the user whose project we are inserting a material into
	 * @param projectName the name of the project we are inserting materials into
	 * @param materialName the name of the material we are inserting
	 * @param cost the price of the material we are inserting
	 * @throws SQLException if the connection/ insertion fails
	 * @throws ClassNotFoundException if the connection fails
	 */
	public void insertMaterial(String username, String projectName, String materialName, double cost)
			throws SQLException, ClassNotFoundException {

		String sql = "INSERT INTO materials(USERNAME,PROJECTNAME,MATERIALNAME,PRICE) VALUES(?,?,?,?)";

		Connection conn = this.connect();

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, username);
		
		pstmt.setString(2, projectName);

		pstmt.setString(3, materialName);

		pstmt.setDouble(4, cost);

		pstmt.executeUpdate();
		
	}
	/** 
	 * Deletes all the materials for a given user's given project.
	 * @param userName the name of the user whose project we are clearing the materials for.
	 * @param projectName the name of the project we are clearing materials for
	 * @throws ClassNotFoundException if the connection/deletion fails
	 * @throws SQLException if the connection fails
	 */
	public void deleteMaterials(String userName, String projectName) throws ClassNotFoundException, SQLException {

		String sql = "DELETE FROM materials WHERE (USERNAME,PROJECTNAME) = (?,?)";
		
		Connection conn = this.connect();

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, projectName);

		pstmt.executeUpdate();
	}
	/**
	 * Retrieves a list of users registered to the db.
	 * @return a list of registered users
	 * @throws SQLException if the connection fails
	 * @throws ClassNotFoundException if the connection/selec fails.
	 */
	public LinkedList<String> getUserList() throws SQLException, ClassNotFoundException {

		String sql = "SELECT USERNAME FROM users";

		LinkedList<String> list = new LinkedList<String>();

		Connection connect = this.connect();

		Statement state = connect.createStatement();

		ResultSet set = state.executeQuery(sql);

		while (set.next()) {

			list.add(set.getString("USERNAME"));
		}
		return list;
	}

//	/* What does this do? */
//	public void updateMaterials(int userID, String projectName, String materialName, double price)
//			throws ClassNotFoundException, SQLException {
//
//		String sql = "UPDATE materials SET NAME = ? , " + "PRICE = ? " + "WHERE ID = ?";
//
//		Connection conn = this.connect();
//
//		PreparedStatement state = conn.prepareStatement(sql);
//
//		state.setInt(1, userID);
//
//		state.setString(2, projectName);
//
//		state.setString(3, projectName);
//
//		state.setDouble(4, price);
//
//		state.executeUpdate();
//		
//	}
//
//	/* Needs to be updated for new database setup (No User ID). */
//	public Map<Integer, Entry<String, Double>> getMaterials(int userID, String projectName)
//			throws ClassNotFoundException, SQLException {
//
//		String sql = "SELECT ID, NAME, PRICE materials";
//
//		HashMap<Integer, Entry<String, Double>> map = new MyHashMap<>();
//
//		Connection connect = this.connect();
//
//		Statement state = connect.createStatement();
//
//		ResultSet set = state.executeQuery(sql);
//
//		int counter = 0;
//
//		while (set.next()) {
//
//			if ((set.getString("projectName") == projectName) && (set.getInt("userID") == userID)) {
//
//				counter++;
//
//				Map.Entry<String, Double> entry = newEntry(set.getString("materialName"), set.getDouble("price"));
//
//				map.put(counter, entry);
//			}
//			
//		}
//		return map;
//
//	}
//
//	/** Don't ask. */
//	private static <K, V> Map.Entry<K, V> newEntry(K key, V value) {
//		return new AbstractMap.SimpleEntry<>(key, value);
//	}
//
//	public static interface MyMap<K, V> extends Map<K, V> {
//
//		public default V put(Entry<K, V> entry) {
//			return put(entry.getKey(), entry.getValue());
//		}
//	}
//
//	public static class MyHashMap<K, V> extends HashMap<K, V> implements MyMap<K, V> {
//
//		private static final long serialVersionUID = 1L;
//
//	}
}
