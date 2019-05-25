package database;

   import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
   import java.sql.SQLException;
   import java.sql.Statement;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

   public class databaseManager
    {
	   private static Statement statement = null;
	   private static Connection connect = null;
	   private static String userSql;
	   
	   public static void main( String args[] ) throws SQLException {
	databaseManager manager = new databaseManager();
	try {
		manager.deleteMaterial(1);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		  
		   
		   
	   }
		
	 
	   private Connection connect() throws ClassNotFoundException {
		   Class.forName("org.sqlite.JDBC");
	        String url = "jdbc:sqlite:users.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
		   
		   
		  public void insertUser(String username, String email) throws SQLException, ClassNotFoundException  {
			  String sql = "INSERT INTO users(USERNAME,EMAIL) VALUES(?,?)";
			  
		        try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, username);
		            pstmt.setString(2, email);
		            pstmt.executeUpdate();
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }	 	   
		   }
		  
		  public void deleteUser(String username) throws ClassNotFoundException {
		        String sql = "DELETE FROM users WHERE USERNAME = ?";
		 
		        try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, username);
		            pstmt.executeUpdate();
		 
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		    }
		  
		  public void insertMaterial(int ID, String name, double cost) throws SQLException, ClassNotFoundException  {
			  String sql = "INSERT INTO materials(ID,NAME,PRICE) VALUES(?,?,?)";	  
		        Connection conn = this.connect();
		        PreparedStatement pstmt = conn.prepareStatement(sql); 
		        pstmt.setInt(1, ID);
		        pstmt.setString(2, name);
		        pstmt.setDouble(3, cost);
		        pstmt.executeUpdate();  	   
		   }
		  
		  public void deleteMaterial(int ID) throws ClassNotFoundException {
		        String sql = "DELETE FROM users WHERE USERNAME = ?";
		 
		        try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setInt(1, ID);
		            pstmt.executeUpdate();
		 
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		    }
		  public LinkedList<String> getUserList() throws SQLException, ClassNotFoundException{
		        String sql = "SELECT USERNAME, FROM users";
		        LinkedList<String> list = new LinkedList<String>();
		             Connection conn = this.connect();
		             Statement stmt  = conn.createStatement();
		             ResultSet rs    = stmt.executeQuery(sql);
		            
		            // loop through the result set
		            while (rs.next()) {
		                list.add(rs.getString("USERNAME"));
		            }
		            return list;
		  }
		  
		  public Map<Integer, Entry<String, Double>> getMaterials() throws ClassNotFoundException, SQLException {
			  String sql = "SELECT ID, NAME, PRICE materials";
		        HashMap<Integer, Entry<String, Double>> map = new MyHashMap<Integer,Entry<String,Double>>();
		             Connection conn = this.connect();
		             Statement stmt  = conn.createStatement();
		             ResultSet rs    = stmt.executeQuery(sql);
		            
		            // loop through the result set
		            while (rs.next()) {
		            	Map.Entry<String, Double> entry = newEntry(rs.getString("NAME"), rs.getDouble("PRICE"));
		                map.put(rs.getInt("ID"), entry);
		            }
		            return map;
			  
		  }
		  
		  /** Don't ask.*/ 
		  private static <K,V> Map.Entry<K,V> newEntry(K key, V value) {
		       return new AbstractMap.SimpleEntry<>(key, value);
		   } 
		  
		  public static interface MyMap<K,V> extends Map<K,V> {

		     
		        public default V put(Entry<K,V> entry) {
		            return put(entry.getKey(), entry.getValue());
		        }
		    }

		 
		    public static class MyHashMap<K,V> extends HashMap<K,V> implements MyMap<K,V> {

		    }
	}
		  

   
