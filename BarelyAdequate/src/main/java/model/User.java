package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import database.databaseManager;

/**
 * Stores the user's information
 * @author Gavin
 *
 */
public class User {
	/** A list of the user's projects. */
	private ArrayList<Project> userProjects;
	/** The user's first name. */
	private String userName;
	/** The user's email address. */
	private String userEmail;
	/** The database being used to represent user's information and projects. */
	private databaseManager dbm;
	
	/**
	 * Constructor to initialize fields.
	 */
	public User(String theName, String theEmail) throws SQLException, ClassNotFoundException {
		userName = theName;
		userEmail = theEmail;
		dbm = new databaseManager();
		LinkedList<String> list = dbm.getUserList();
		if(!list.contains(theName)) {
			dbm.insertUser(userName, userEmail);
		}
		userProjects = new ArrayList<Project>();
		try {
			userProjects = dbm.getUserProjects(theName);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Getter for the username.
	 * @return the user's first name.
	 */
	public String getName() {
		return userName;
	}
	/**
	 * Getter for the user's email.
	 * @return the user's email address.
	 */
	public String getEmail() {
		return userEmail;
	}
	/**
	 * Adds a new project to the list of this user's projects if there isn't on already,
	 * otherwise, overwrites a project of the same name
	 * @param theProject
	 */
	public void addProject(Project theProject) throws ClassNotFoundException, SQLException {
		userProjects.add(theProject);
		dbm.insertProject(userName, theProject.getTitle(), 
				theProject.getBill().getCurrentBill(), theProject.getBill().getProjectedBill(),
				theProject.getDiff());
		
		/* Delete all materials and procedure steps for this 
		 * project if there are any.*/
		dbm.deleteMaterials(theProject.getTitle());
		dbm.deleteTasks(theProject.getTitle());
		
		/* Put the fresh materials from the new updated project in the db.*/
		for(Material m: theProject.getMaterials()) {
			dbm.insertMaterial(userName, theProject.getTitle(), m.getName(), m.getCost());
		}
		for(String step: theProject.getProcedure()) {
			dbm.insertTask(userName, theProject.getTitle(), step);
		}
	}
	public ArrayList<Project> getUserProjects() {
		return userProjects;
	}
}
