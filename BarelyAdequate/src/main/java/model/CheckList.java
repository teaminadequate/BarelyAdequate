/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author Gavin
 *
 */
public class CheckList<T> {
	/** A list to hold all of the items in the checklist. */
	private ArrayList<T> myItemList;
	/** A list of booleans to indicate if an item has been checked off. */
	private ArrayList<Boolean> myBoolList;
	/**Constructor. */
	public CheckList () {
		myItemList = new ArrayList<T>();
		myBoolList = new ArrayList<Boolean>();
	}
	/**
	 * Appends item to end of the checklist. 
	 * @param item the item to add.
	 */
	public void add(T item) {
		myItemList.add(item);
		myBoolList.add(false);
	}
	/**
	 * Inserts item into the checklist at the specified index. 
	 * @param item, the index to insert the item at.
	 * @param index, the item to add.
	 */
	public void add(int index, T item) {
		myItemList.add(index, item);
		myBoolList.add(index, false);
	}
	/**
	 * Removes an item from the list at the specified index.
	 * @param index the index of the item we want to remove.
	 */
	public void remove(int index) {
		myItemList.remove(index);
		myBoolList.remove(index);
	}
	/**
	 * Checks to see if an item is checked at the specified index.
	 * @param index the index of the item to query.
	 * @return the state of the checklist at the index.
	 */
	public boolean isChecked(int index) {
		return myBoolList.get(index);
	}
	/**
	 * Toggles state of an item at the specified index.
	 * @param index the index of the item we want to toggle.
	 */
	public void toggleChecked(int index) {
		myBoolList.set(index, !myBoolList.get(index));
	}
	/**
	 * Sets the state of the checklist item to the state specified.
	 * @oaram index the index of the item we want to set.
	 * @param state the state that we want to set the item to.
	 */
	public void toggledChecked(int index, boolean state) {
		myBoolList.set(index, state);
	}
	/**
	 * Gets the percentage of the checklist that is currently checked off.
	 * @return the amount of the checklist that is checked.
	 */
	public int getPercentDone() {
		int numChecked = 0;
		for(Boolean b : myBoolList) {
			if(b) {
				numChecked++;
			}
		}
		return (numChecked / myBoolList.size()) * 100;
	}
	/**
	 * Gets the element at the specified index. 
	 * @param the index of the item that we want to access.
	 */
	public T get(int index) {
		return myItemList.get(index);
	}
}
