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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class databaseManager {

	public static void main(String args[]) throws SQLException {

		databaseManager manager = new databaseManager();
		try {
			manager.insertUser("nicole", "email67");

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

	
	public void insertProject(String projectName, int userID, double preBill, double postBill)
			throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO project(PROJECTNAME, USERID, PREBILL, POSTBILL) VALUES (?,?,?,?)";

		try (Connection connect = this.connect();

				PreparedStatement state = connect.prepareStatement(sql)) {

			state.setString(1, projectName);

			state.setInt(2, userID);

			state.setDouble(3, preBill);

			state.setDouble(4, postBill);

			state.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}
	
	
	public void insertTask(String projectName, int taskID, String task) throws SQLException, ClassNotFoundException {

		String sql = "INSERT INTO tasks(PROJECTNAME,TASKID,TASKDESCRIPTION) VALUES(?,?)";

		try (Connection connect = this.connect();

				PreparedStatement state = connect.prepareStatement(sql)) {

			state.setString(1, projectName);

			state.setInt(2, taskID);

			state.setString(3, task);
			state.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	
	public void deleteTask(int taskID) throws ClassNotFoundException {

		String sql = "DELETE FROM tasks WHERE TASKID  = ?";

		try (Connection connect = this.connect();

				PreparedStatement state = connect.prepareStatement(sql)) {

			state.setInt(2, taskID);

			state.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	public void insertMaterial(int itemID, String projectName, String materialName, double cost)
			throws SQLException, ClassNotFoundException {

		String sql = "INSERT INTO materials(ITEMID,PROJECTNAME,MATERIALNAME,PRICE) VALUES(?,?,?,?)";

		Connection conn = this.connect();

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, itemID);

		pstmt.setString(2, projectName);

		pstmt.setString(3, materialName);

		pstmt.setDouble(4, cost);

		pstmt.executeUpdate();
	}

	public void deleteMaterial(String materialName) throws ClassNotFoundException {

		String sql = "DELETE FROM materials WHERE MATERIALNAME = ?";

		try (Connection conn = this.connect();

				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, materialName);

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
