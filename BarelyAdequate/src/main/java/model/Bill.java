/**
 * This class represents the bill for a single project. Every project has a bill associated with it, and will
 * change throughout the length of the application run, and this class keeps track of those changes.
 */
package model;

/**
 * This represents the bill and how it will be affected by the project.
 * 
 * @author Gavin
 *
 */
public class Bill {
	/** The (heat, water, power etc.) bill currently. */
	private double currentBill;
	
	/** The (heat, water, power, etc.) bill after the project cuts cost. */
	private double projectedBill;

	/**
	 * Constructor for a blank bill.
	 */
	public Bill() {
		currentBill = 0.0;
		projectedBill = 0.0;
	}

	/**
	 * Constructor to initialize fields. To change the bills we have to know what the previous bill
	 * was, and what we want the bill to become.
	 * 
	 * @param before the previous bill
	 * @param after the bill after
	 */
	public Bill(double before, double after) {
		currentBill = before;
		projectedBill = after;
	}

	/**
	 * Returns a formatted amount of time that the project will take to return on
	 * investment.
	 * 
	 * @return returns the projected return on investment.
	 */
	public String getROI() {
		StringBuilder sb = new StringBuilder();
		// TODO
		return "";
	}

	/**
	 * Returns the monthly savings effective on project completion.
	 * 
	 * @return the monthly savings.
	 */
	public double getMonthlySavings() {
		return currentBill - projectedBill;
	}

	/**
	 * Returns the bill before the project is completed.
	 * 
	 * @return the current bill
	 */
	public double getCurrentBill() {
		return currentBill;
	}

	/**
	 * Returns the projected bill after project completion.
	 * 
	 * @return the projected bill
	 */
	public double getProjectedBill() {
		return projectedBill;
	}

	/**
	 * Calculates the projected bill based off of the current bill.
	 * 
	 * @param theBill the current bill for the project
	 */
	public void setProjectedBill(double theBill) {
		projectedBill = theBill;
	}

	/**
	 * Sets the current bill
	 * 
	 * @param theBill the previous bill
	 */
	public void setCurrentBill(double theBill) {
		currentBill = theBill;
	}
}
