/**
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * @author Gavin
 *
 */
public class Project {
	/** An enum for the difficulty. */
	enum Difficulty {
		Easy, Medium, Hard;
	}
	
	/** The title of the project. */
	private String myTitle;
	/** A checklist of materials. */
	private CheckList<Material> myMaterials;
	/** The bill being affected by this project. */
	private Bill myBill;
	/** The difficulty of the project. */
	private Difficulty myDiff;
	/** The total cost of all the materials used in this project. */
	private double myTotal;
	/** A checklist of procedure steps needed to complete this project. */
	private CheckList<String> myProcedure;
	
	/** Initializes a new blank project. */
	public Project() {
		myTitle = "";
		myMaterials = new CheckList<Material>();
		myBill = new Bill();
		myDiff = Difficulty.Easy;
		myTotal = 0.0;
		myProcedure = new CheckList<String>();
	}
	/**
	 * Constructor to load a project from a .csv file.
	 * @param theCSV the .csv file that we are loading a project from.
	 */
	public Project(File theCSV) throws FileNotFoundException {
		//TODO
	}
	/** 
	 * Convert this project to a .csv file to be saved to the system.
	 * @return the File object that is created.
	 */
	public File toCSV() {
		//TODO
		return new File("");
	}
	public String getTitle() {
		return myTitle;
	}
	public void setTitle(String theTitle) {
		myTitle = theTitle;
	}
	public void addMaterial(Material theMaterial) {
		myMaterials.add(theMaterial);
		myTotal += theMaterial.getCost();
	}
	public void removeMaterial(int index) {
		myTotal -= myMaterials.get(index).getCost();
		myMaterials.remove(index);
	}
	public Bill getBill() {
		return myBill;
	}
	public void setCurrentBill(double theBill) {
		myBill.setCurrentBill(theBill);
	}
	public void setProjectedBill(double theBill) {
		myBill.setProjectedBill(theBill);
	}
	public Difficulty getDiff() {
		return myDiff;
	}
	public void setDiff(Difficulty theDiff) {
		myDiff = theDiff;
	}
	public double getTotal() {
		return myTotal;
	}
	public void addStep(String theStep) {
		myProcedure.add(theStep);
	}
	public void addStep(int theIndex, String theStep) {
		myProcedure.add(theIndex, theStep);
	}
	public void removeStep(int theIndex) {
		myProcedure.remove(theIndex);
	}
	public int getPercentDone() {
		return ( myProcedure.getPercentDone() + myMaterials.getPercentDone() ) / 2;
	}
	
}
