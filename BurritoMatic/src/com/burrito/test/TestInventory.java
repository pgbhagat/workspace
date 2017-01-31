package com.burrito.test;

import org.junit.Assert;
import org.junit.Test;

import com.burrito.inventory.IInventoryStore;
import com.burrito.inventory.InventoryStoreManager;
import com.burrito.items.BurritoInBowl;
import com.burrito.items.IngredientItem;
import com.burrito.items.ItemHelper;
import com.burrito.items.ThreeIngredientBurrito;
import com.burrito.items.TwoIngredientBurrito;
import com.burritomatic.exception.InventoryException;

import junit.framework.TestCase;

public class TestInventory extends TestCase {

	@Test
	public void testRestockInventory() {

		try {
			IInventoryStore store = new InventoryStoreManager();

			System.out.println("All burrito menu:");
			ItemHelper.printItems(store.getAllMenuItemsAndPrices());
			System.out.println("All ingredient items:");
			ItemHelper.printItems(store.getAllIngredientItemsAndPrices());

			store.restockInventory(new BurritoInBowl(), 3.99f, 100);
			store.restockInventory(new TwoIngredientBurrito(), 4.99f, 200);
			store.restockInventory(new ThreeIngredientBurrito(), 5.99f, 300);

			Assert.assertEquals(store.getAvailbeItemCount(new BurritoInBowl()), 100);
			Assert.assertEquals(store.getAvailbeItemCount(new TwoIngredientBurrito()), 200);
			Assert.assertEquals(store.getAvailbeItemCount(new ThreeIngredientBurrito()), 300);

			// Base stock...
			store.restockInventory(IngredientItem.Tortilla, 0.00f, 200);
			store.restockInventory(IngredientItem.Rice, 0.00f, 200);

			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.Tortilla), 200);
			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.Rice), 200);

			// meat stock...
			store.restockInventory(IngredientItem.Chicken, 0.00f, 100);
			store.restockInventory(IngredientItem.Steak, 0.00f, 100);

			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.Chicken), 100);
			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.Steak), 100);

			// salsa
			store.restockInventory(IngredientItem.Queso, 1.5f, 200);
			store.restockInventory(IngredientItem.RedSalsa, 0.00f, 300);
			store.restockInventory(IngredientItem.GreenSalsa, 0.00f, 400);

			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.Queso), 200);
			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.RedSalsa), 300);
			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.GreenSalsa), 400);

			// toppings
			store.restockInventory(IngredientItem.GratedCheese, 0.00f, 200);
			store.restockInventory(IngredientItem.SourCream, 0.00f, 300);
			store.restockInventory(IngredientItem.Gucamole, 1.25f, 400);

			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.GratedCheese), 200);
			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.SourCream), 300);
			Assert.assertEquals(store.getAvailbeItemCount(IngredientItem.Gucamole), 400);

			System.out.println("------------------");
			System.out.println("All burrito menu:");
			ItemHelper.printItems(store.getAllMenuItemsAndPrices());
			System.out.println("------------------");
			System.out.println("All ingredient items:");
			ItemHelper.printItems(store.getAllIngredientItemsAndPrices());
		} catch (InventoryException e) {
			Assert.fail("Test failed.." + e.getMessage());
		}
	}

	@Test
	public void testDeduceInvalidFromInventory() {
		IInventoryStore store = new InventoryStoreManager();
		try {
			System.out.println("------------------");
			System.out.println("All burrito menu:");
			ItemHelper.printItems(store.getAllMenuItemsAndPrices());
			System.out.println("------------------");
			System.out.println("All ingredient items:");
			ItemHelper.printItems(store.getAllIngredientItemsAndPrices());

			store.restockInventory(new BurritoInBowl(), 3.99f, 100);
			store.restockInventory(new TwoIngredientBurrito(), 4.99f, 200);
			store.restockInventory(new ThreeIngredientBurrito(), 5.99f, 300);
		} catch (InventoryException e) {
			Assert.fail("Test failed.." + e.getMessage());
		}

		boolean success = false;
		try {
			store.deduceFromInventory(new BurritoInBowl(), 200);
		} catch (InventoryException e) {
			success = true;
		}
		if (!success) {
			Assert.fail("Invalid deduction from invertory allowed..");
		}

	}

	@Test
	public void testDeduceFromInventory() {

		IInventoryStore store = new InventoryStoreManager();
		try {
			System.out.println("------------------");
			System.out.println("All burrito menu:");
			ItemHelper.printItems(store.getAllMenuItemsAndPrices());
			System.out.println("------------------");
			System.out.println("All ingredient items:");
			ItemHelper.printItems(store.getAllIngredientItemsAndPrices());

			store.restockInventory(new BurritoInBowl(), 3.99f, 100);
			store.restockInventory(new TwoIngredientBurrito(), 4.99f, 200);
			store.restockInventory(new ThreeIngredientBurrito(), 5.99f, 300);

			store.deduceFromInventory(new BurritoInBowl(), 15);
			store.deduceFromInventory(new TwoIngredientBurrito(), 10);

			Assert.assertEquals(store.getAvailbeItemCount(new BurritoInBowl()), 85);
			Assert.assertEquals(store.getAvailbeItemCount(new TwoIngredientBurrito()), 190);
			Assert.assertEquals(store.getAvailbeItemCount(new ThreeIngredientBurrito()), 300);
		} catch (InventoryException e) {
			Assert.fail("Invalid deduction from invertory allowed..");
		}

	}
}
