/**
 * This is the Project file. This has all of the project's information, consisting of Materials,
 * Bills, Procedures, etc.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import database.databaseManager;

/**
 * 
 * @author Gavin
 */
public class Project {
	/** An enum for the difficulty. */
	enum Difficulty {
		Easy, Medium, Hard;
	}

	/** The title of the project. */
	private String myTitle;

	/** A checklist of materials. */
	private ArrayList<Material> myMaterials;
	
	/** The bill being affected by this project. */
	private Bill myBill;
	
	/** The difficulty of the project. */
	private Difficulty myDiff;
	
	/** The total cost of all the materials used in this project. */
	private double myTotal;
	
	/** A checklist of procedure steps needed to complete this project. */
	private ArrayList<String> myProcedure;
	
	/** The database being used to represent the project. */
	private databaseManager dbm;

	/** Initializes a new blank project. */
	public Project(databaseManager theDBM) {
		myTitle = "";
		myMaterials = new ArrayList<Material>();
		myBill = new Bill();
		myDiff = Difficulty.Easy;
		myTotal = 0.0;
		myProcedure = new ArrayList<String>();
		dbm = theDBM;
		
	}

	/**
	 * Gets the title of the project.
	 * 
	 * @return the title of the project
	 */
	public String getTitle() {
		return myTitle;
		
	}

	/**
	 * Sets the title of the project.
	 * 
	 * @param theTitle the title you want the project to be set to
	 */
	public void setTitle(String theTitle) {
		myTitle = theTitle;
		
	}

	/**
	 * Adds a material to the list of materials, as well as to the database.
	 * 
	 * @param theMaterial the material to be added to the list
	 */
	public void addMaterial(Material theMaterial) throws SQLException, ClassNotFoundException{
		myMaterials.add(theMaterial);
		myTotal += theMaterial.getCost();
		dbm.insertMaterial(myTitle, theMaterial.getName(), theMaterial.getCost());
		
	}

	/**
	 * Removes a material from the list of materials.
	 * 
	 * @param theMaterialName the name of the material that you want to be removed
	 */
	public void removeMaterial(String theMaterialName) throws SQLException, ClassNotFoundException{
		
		for (int i = 0; i < myMaterials.size(); i++) {
			if (theMaterialName.equals(myMaterials.get(i).getName())) {
				myMaterials.remove(i);
				myTotal -= myMaterials.get(i).getCost();
				dbm.deleteMaterial(theMaterialName);
				
			}
			
		}
		
	}

	/**
	 * The current bill of the project.
	 * 
	 * @return the current bill of the project
	 */
	public Bill getBill() {
		return myBill;
		
	}

	/**
	 * Sets the current bill for the project.
	 * 
	 * @param theBill the bill you want
	 */
	public void setCurrentBill(double theBill) {
		myBill.setCurrentBill(theBill);
		
	}

	/**
	 * Sets the projected bill of the project.
	 * 
	 * @param theBill the projected bill you want
	 */
	public void setProjectedBill(double theBill) {
		myBill.setProjectedBill(theBill);
		
	}

	/**
	 * Gets the difficulty of the project.
	 * 
	 * @return returns the difficulty of the project
	 */
	public Difficulty getDiff() {
		return myDiff;
		
	}

	/**
	 * Sets the difficulty of the project.
	 * 
	 * @param theDiff the new difficulty of the project
	 */
	public void setDiff(Difficulty theDiff) {
		myDiff = theDiff;
		
	}

	/**
	 * Gets the total cost of the bills.
	 * 
	 * @return the total cost of the bills
	 */
	public double getTotal() {
		return myTotal;
		
	}

	/**
	 * Adds a step to the procedures list.
	 * 
	 * @param theStep the step to be added to the procedure list
	 */
	public void addStep(String theStep) throws SQLException, ClassNotFoundException {
		myProcedure.add(theStep);
		dbm.insertTask(myTitle, theStep);
		
	}

	/**
	 * Adds a step to a specific part of the list.
	 * 
	 * @param theIndex the index where you want the list to be
	 * @param theStep the step to be added to the procedure list
	 */
	public void addStep(int theIndex, String theStep) throws SQLException, ClassNotFoundException {
		myProcedure.add(theIndex, theStep);
		dbm.insertTask(myTitle, theStep);
		
	}

	/**
	 * Removes a step.
	 * 
	 * @param theIndex the index of the step to be removed
	 */
	public void removeStep(int theIndex) throws SQLException, ClassNotFoundException {
		myProcedure.remove(theIndex);
		dbm.deleteTask(theIndex);
		
	}
	
}
