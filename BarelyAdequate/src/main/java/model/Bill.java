/**
 * 
 */
package model;

/**
 * This represents the bill and how it will be affected by the project. 
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
	 * Constructor to initialize fields.
	 */
	public Bill(double before, double after) {
		currentBill = before;
		projectedBill = after;
	}
	/**
	 * Returns a formatted amount of time that the project will
	 * take to return on investment.
	 */
	public String getROI() {
		StringBuilder sb = new StringBuilder();
		//TODO
		return "";
	}
	/**
	 * Returns the monthly savings effective on project completion.
	 * @return the monthly savings.
	 */
	public double getMonthlySavings() {
		return currentBill - projectedBill;
	}
	/**
	 * Returns the bill before the project is completed.
	 */
	public double getCurrentBill() {
		return currentBill;
	}
	/**
	 * Returns the projected bill after project completion.
	 */
	public double getProjectedBill() {
		return projectedBill;
	}
	public void setProjectedBill(double theBill) {
		projectedBill = theBill;
	}
	public void setCurrentBill(double theBill) {
		currentBill = theBill;
	}
}
