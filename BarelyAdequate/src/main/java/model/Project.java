/**
 * This is the Project file. This has all of the project's information, consisting of Materials,
 * Bills, Procedures, etc.
 */
package model;

import java.util.ArrayList;

/**
 * 
 * @author Gavin Montes
 */
public class Project {
	/** The title of the project. */
	private String myTitle;
	/** A checklist of materials. */
	private ArrayList<Material> myMaterials;
	/** The bill being affected by this project. */
	private Bill myBill;
	/** The difficulty of the project. (On a scale of 1-10).*/
	private int myDiff;
	/** A checklist of procedure steps needed to complete this project. */
	private ArrayList<String> myProcedure;
	
	/** 
	 * Initializes a new blank project. 
	 * @author Gavin Montes
	 */
	public Project() {
		myTitle = "";
		myMaterials = new ArrayList<Material>();
		myBill = new Bill();
		myDiff = 2;
		myProcedure = new ArrayList<String>();
	}

	/**
	 * Gets the title of the project.
	 * @author Gavin Montes
	 * @return the title of the project
	 */
	public String getTitle() {
		return myTitle;
	}
	/**
	 * Gets the list of materials.
	 * @author Gavin Montes
	 * @return and ArrayList of the materials for this project.
	 */
	public ArrayList<Material> getMaterials() {
		return myMaterials;
	}
	/**
	 * Gets the procedure.
	 * @author Gavin Montes
	 * @return and ArrayList of procedure steps(Strings).
	 */
	public ArrayList<String> getProcedure() {
		return myProcedure;
	}
	/**
	 * Gets the difficulty of the project.
	 * @author Gavin Montes
	 * @return returns the difficulty of the project (Scale of 1 to 10).
	 */
	public int getDiff() {
		return myDiff;
	}
	/**
	 * Sets the title of the project.
	 * @author Gavin Montes
	 * @param theTitle the title you want the project to be set to
	 */
	public void setTitle(String theTitle) {
		myTitle = theTitle;
	}
	/**
	 * Adds a material to the list of materials, as well as to the database.
	 * @author Gavin Montes
	 * @param theMaterial the material to be added to the list
	 */
	public void addMaterial(Material theMaterial) {
		myMaterials.add(theMaterial);
	}
	/**
	 * Removes a material from the list of materials.
	 * @author Gavin Montes
	 * @param theMaterialName the name of the material that you want to be removed
	 */
	public void removeMaterial(String theMaterialName) {
		for (int i = 0; i < myMaterials.size(); i++) {
			if (theMaterialName.equals(myMaterials.get(i).getName())) {
				myMaterials.remove(i);
			}
		}
	}
	/**
	 * The current bill of the project.
	 * @author Gavin Montes
	 * @return the current bill of the project
	 */
	public Bill getBill() {
		return myBill;
		
	}
	/**
	 * Sets the current bill for the project.
	 * @author Gavin Montes
	 * @param theBill the bill you want
	 */
	public void setCurrentBill(double theBill) {
		myBill.setCurrentBill(theBill);
		
	}
	/**
	 * Sets the projected bill of the project.
	 * @author Gavin Montes
	 * @param theBill the projected bill you want
	 */
	public void setProjectedBill(double theBill) {
		myBill.setProjectedBill(theBill);
	}
	/**
	 * Sets the difficulty of the project.
	 * @author Gavin Montes
	 * @param theDiff the new difficulty of the project
	 */
	public void setDiff(int theDiff) {
		if (theDiff > 10) {
			myDiff = 10;
		} else if (theDiff < 1) {
			myDiff = 1;
		} else {
			myDiff = theDiff;
		}
	}
	/**
	 * Gets the total cost of the materials.
	 * @author Gavin Montes
	 * @return the total cost of the materials
	 */
	public double getTotal() {
		double total = 0;
		for(Material m : myMaterials) {
			total += m.getCost();
		}
		return total;
	}
	/**
	 * Adds a step to the procedures list.
	 * @author Gavin Montes
	 * @param theStep the step to be added to the procedure list
	 */
	public void addStep(String theStep) {
		myProcedure.add(theStep);
	}
	/**
	 * Adds a step to a specific part of the list.
	 * @author Gavin Montes
	 * @param theIndex the index where you want the list to be
	 * @param theStep the step to be added to the procedure list
	 */
	public void addStep(int theIndex, String theStep) {
		myProcedure.add(theIndex, theStep);
	}
	/**
	 * Removes a step.
	 * @author Gavin Montes
	 * @param theIndex the index of the step to be removed
	 */
	public void removeStep(String theStepName) {
		for (int i = 0; i < myMaterials.size(); i++) {
			if (theStepName.equals(myProcedure.get(i))) {
				myMaterials.remove(i);
			}
		}
	}
	/**
	 * Sets the materials list to the given list.
	 * @author Gavin Montes
	 * @param theMaterials the new updated list of materials
	 */
	public void setMaterials(ArrayList<Material> theMaterials) {
		myMaterials = theMaterials;
	}
	/**
	 * Sets the procedure to the given list.
	 * @author Gavin Montes
	 * @param theProcedure the new updated list steps
	 */
	public void setProcedure(ArrayList<String> theProcedure) {
		myProcedure = theProcedure;
	}
	/**
	 * Returns a formatted amount of time that the project will take to return on
	 * investment.
	 * @author Gavin Montes
	 * @return returns the projected return on investment.
	 */
	public String getROI() {
		double total = getTotal();
		int months = 0;
		while(total > 0) {
			total -= myBill.getMonthlySavings();
			months++;
		}
		return months + " months";
	}
}
