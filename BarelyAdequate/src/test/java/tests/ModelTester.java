package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Bill;
import model.Material;
import model.Project;

/**
 * Tester for relevant methods in the model classes
 * @author Gavin Montes
 *
 */
class ModelTester {
	
	Project p = new Project();
	Bill bill = new Bill(10,9);
	ArrayList<Material> mats = new ArrayList<Material>();
	/**
	 * @author Gavin Montes
	 */
	@Test
	void testMonthlySavings() {
		assertEquals(bill.getMonthlySavings(), 1);
	}
	/**
	 * @author Gavin Montes
	 */
	@Test
	void testSetDiff() {
		p.setDiff(11);
		assertEquals(p.getDiff(), 10);
		p.setDiff(-1);
		assertEquals(p.getDiff(), 0);
	}
	/**
	 * @author Gavin Montes
	 */
	@Test
	void testGetROI() {
		p.setCurrentBill(10);
		p.setProjectedBill(9);
		mats.add(new Material("test", 11));
		p.setMaterials(mats);
		assertTrue(p.getROI().equals("11 months"));
	}
}
