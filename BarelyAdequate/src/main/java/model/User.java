package model;

import java.util.ArrayList;

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
	
	/**
	 * Constructor to initialize fields.
	 */
	public User(String theName, String theEmail) {
		userName = theName;
		userEmail = theEmail;
		userProjects = new ArrayList<Project>();
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
	 * Adds a new project to the list of this user's projects.
	 * @param theProject
	 */
	public void addProject(Project theProject) {
		userProjects.add(theProject);
	}
	/**
	 * Returns a project of the specified name if it exists in the user's list,
	 * null otherwise.
	 * @param theName the name of the project we are looking for.
	 */
	public Project getProject(String theName) {
		for(Project p : userProjects) {
			if(p.getTitle().equals(theName)) {
				return p;
			}
		}
		return null;
	}
}
