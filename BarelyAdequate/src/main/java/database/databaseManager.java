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

public class databaseManager {

	public static void main(String args[]) throws SQLException {

		databaseManager manager = new databaseManager();
		try {
			manager.getUserProjects("nicole");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection connect() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");

		String url = "jdbc:sqlite:users.db";

		Connection connect = null;

		try {
			connect = DriverManager.getConnection(url);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return connect;
	}

	public void insertUser(String username, String email) throws SQLException, ClassNotFoundException {

		String sql = "INSERT INTO users(USERNAME,EMAIL) VALUES(?,?)";

		try (Connection connect = this.connect();

				PreparedStatement state = connect.prepareStatement(sql)) {

			state.setString(1, username);

			state.setString(2, email);

			state.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

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

	public ArrayList<Material> getUserProjectMaterials(String userName, String projectName) 
			throws SQLException, ClassNotFoundException {
		String sql = "SELECT USERNAME, PROJECTNAME, MATERIALNAME FROM materials";

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

	public void insertProject(String userName, String projectName, double preBill, double postBill, int diff)
			throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO projects(USERNAME, PROJECTNAME, PREBILL, POSTBILL, DIFFICULTY) VALUES (?,?,?,?,?)";

		try (Connection connect = this.connect();

				PreparedStatement state = connect.prepareStatement(sql)) {

			state.setString(1, projectName);

			state.setString(2, userName);

			state.setDouble(3, preBill);

			state.setDouble(4, postBill);

			state.setInt(5, diff);

			state.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	public void deleteProject(String projectName) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM projects WHERE PROJECTNAME  = ?";

		try (Connection connect = this.connect();

				PreparedStatement state = connect.prepareStatement(sql)) {

			state.setString(1, projectName);

			state.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	public void insertTask(String projectName, String task) throws SQLException, ClassNotFoundException {

		String sql = "INSERT INTO tasks(PROJECTNAME,TASKDESCRIPTION) VALUES(?,?)";

		try (Connection connect = this.connect();

				PreparedStatement state = connect.prepareStatement(sql)) {

			state.setString(1, projectName);

			state.setString(2, task);
			state.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	public void deleteTasks(String projectName) throws ClassNotFoundException {

		String sql = "DELETE FROM tasks WHERE PROJECTNAME  = ?";

		try (Connection connect = this.connect();

				PreparedStatement state = connect.prepareStatement(sql)) {

			state.setString(1, projectName);

			state.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	public void insertMaterial(String projectName, String materialName, double cost)
			throws SQLException, ClassNotFoundException {

		String sql = "INSERT INTO materials(PROJECTNAME,MATERIALNAME,PRICE) VALUES(?,?,?)";

		Connection conn = this.connect();

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, projectName);

		pstmt.setString(2, materialName);

		pstmt.setDouble(3, cost);

		pstmt.executeUpdate();
	}

	public void deleteMaterials(String projectName) throws ClassNotFoundException {

		String sql = "DELETE FROM materials WHERE PROJECTNAME = ?";

		try (Connection conn = this.connect();

				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, projectName);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}

	public LinkedList<String> getUserList() throws SQLException, ClassNotFoundException {

		String sql = "SELECT USERNAME, FROM users";

		LinkedList<String> list = new LinkedList<String>();

		Connection connect = this.connect();

		Statement state = connect.createStatement();

		ResultSet set = state.executeQuery(sql);

		while (set.next()) {

			list.add(set.getString("USERNAME"));
		}

		return list;
	}

	/* What does this do? */
	public void updateMaterials(int userID, String projectName, String materialName, double price)
			throws ClassNotFoundException, SQLException {

		String sql = "UPDATE materials SET NAME = ? , " + "PRICE = ? " + "WHERE ID = ?";

		Connection conn = this.connect();

		PreparedStatement state = conn.prepareStatement(sql);

		state.setInt(1, userID);

		state.setString(2, projectName);

		state.setString(3, projectName);

		state.setDouble(4, price);

		state.executeUpdate();
	}

	/* Needs to be updated for new database setup (No User ID). */
	public Map<Integer, Entry<String, Double>> getMaterials(int userID, String projectName)
			throws ClassNotFoundException, SQLException {

		String sql = "SELECT ID, NAME, PRICE materials";

		HashMap<Integer, Entry<String, Double>> map = new MyHashMap<>();

		Connection connect = this.connect();

		Statement state = connect.createStatement();

		ResultSet set = state.executeQuery(sql);

		int counter = 0;

		while (set.next()) {

			if ((set.getString("projectName") == projectName) && (set.getInt("userID") == userID)) {

				counter++;

				Map.Entry<String, Double> entry = newEntry(set.getString("materialName"), set.getDouble("price"));

				map.put(counter, entry);
			}
		}
		return map;

	}

	/** Don't ask. */
	private static <K, V> Map.Entry<K, V> newEntry(K key, V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}

	public static interface MyMap<K, V> extends Map<K, V> {

		public default V put(Entry<K, V> entry) {
			return put(entry.getKey(), entry.getValue());
		}
	}

	public static class MyHashMap<K, V> extends HashMap<K, V> implements MyMap<K, V> {

		private static final long serialVersionUID = 1L;

	}
}
