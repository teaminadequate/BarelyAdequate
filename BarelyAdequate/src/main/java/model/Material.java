/**
 * 
 */
package model;

/**
 * @author Gavin
 *
 */
public class Material {
	/** The cost of the material. */
	private double myCost;
	/** The name of the material. */
	private String myMaterialType;
	
	/**
	 * Constructor to initialize fields.
	 * @param
	 */
	public Material(String type, double cost) {
		myMaterialType = type;
		myCost = cost;
	}
	/**
	 * Getter for the material type.
	 * @return myMaterialType, the identifier (name) of this material.
	 */
	public String getName() {
		return myMaterialType;
	}
	/**
	 * Getter for the cost.
	 * @return myCost, the cost of this material.
	 */
	public double getCost() {
		return myCost;
	}
}
