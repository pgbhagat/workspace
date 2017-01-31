package com.burrito.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.burrito.cart.Bill;
import com.burrito.inventory.IInventoryStore;
import com.burrito.inventory.InventoryStoreManager;
import com.burrito.items.BurritoInBowl;
import com.burrito.items.IItem;
import com.burrito.items.IngredientItem;
import com.burrito.items.ItemHelper;
import com.burrito.items.ThreeIngredientBurrito;
import com.burrito.items.TwoIngredientBurrito;
import com.burrito.manager.IBurritoMaticManager;
import com.burrito.manager.IUserPortal;
import com.burrito.manager.UserPortalImpl;
import com.burritomatic.exception.InventoryException;
import com.burritomatic.exception.UserException;
import com.burritomatic.user.IUserManager;
import com.burritomatic.user.UserManagerImpl;

import junit.framework.TestCase;

public class TestBurritoMaticManager extends TestCase {

	@Test
	public void testOrderingOutOfStock() {
		// TODO
	}

	@Test
	public void testOrderingWithNonBurritoItems() {
		// TODO
	}

	@Test
	public void testEndToEndOrdering() {
		IUserPortal portal = new UserPortalImpl();
		IUserManager manager = UserManagerImpl.getInstance();
		IInventoryStore store = InventoryStoreManager.getInstance();
		try {
			manager.addUser("Pranay", "K", "Pranay.K@gmail.com", "woodPassword", "98405558986", "Marketyard, Pune");
			IBurritoMaticManager burritoMaticManager = portal.login("Pranay.K@gmail.com", "woodPassword");

			Assert.assertNotNull(burritoMaticManager);

			store.restockInventory(new BurritoInBowl(), 3.99f, 100);
			store.restockInventory(new TwoIngredientBurrito(), 4.99f, 200);
			store.restockInventory(new ThreeIngredientBurrito(), 5.99f, 300);

			// Base stock...
			store.restockInventory(IngredientItem.Tortilla, 0.00f, 200);
			store.restockInventory(IngredientItem.Rice, 0.00f, 200);

			// meat stock...
			store.restockInventory(IngredientItem.Chicken, 0.00f, 100);
			store.restockInventory(IngredientItem.Steak, 0.00f, 100);

			// salsa
			store.restockInventory(IngredientItem.Queso, 1.5f, 200);
			store.restockInventory(IngredientItem.RedSalsa, 0.00f, 300);
			store.restockInventory(IngredientItem.GreenSalsa, 0.00f, 400);

			// toppings
			store.restockInventory(IngredientItem.GratedCheese, 0.00f, 200);
			store.restockInventory(IngredientItem.SourCream, 0.00f, 300);
			store.restockInventory(IngredientItem.Gucamole, 1.25f, 400);

			Map<IItem, Float> allIngredientMenu = burritoMaticManager.getAllIngredientOptions();
			Map<IItem, Float> allBurritoMenu = burritoMaticManager.getMenuOptions();
			Map<IItem, Float> allNonBurrito = burritoMaticManager.getNonBurritoOptions();

			Assert.assertNotNull(allIngredientMenu);
			Assert.assertNotNull(allBurritoMenu);

			System.out.println("Available burrit menu: ");
			ItemHelper.printItems(allBurritoMenu);
			System.out.println("Available Ingredient menu: ");
			ItemHelper.printItems(allIngredientMenu);
			System.out.println("Available Non burrito menu: ");
			ItemHelper.printItems(allNonBurrito);

			IngredientItem[] ingredients = { IngredientItem.Chicken, IngredientItem.RedSalsa };
			try {
				burritoMaticManager.addToMyCart(new TwoIngredientBurrito(), ingredients);
				Bill bill = burritoMaticManager.getBillDetails();
				int orderId = burritoMaticManager.pay(bill.getBillAmount());
				Assert.assertEquals(bill.getBillAmount(), 4.99f, 0.00f);
				System.out.println(
						"Order places. Order id - " + orderId + ", Total amount paid - " + bill.getBillAmount());
			} catch (Exception e) {
				Assert.fail("Test failed with error - " + e.getMessage());
			}

			IngredientItem[] newIngredients = { IngredientItem.Tortilla, IngredientItem.Queso };
			try {
				burritoMaticManager.removeMenuFromMyCart(new TwoIngredientBurrito());
				burritoMaticManager.addToMyCart(new TwoIngredientBurrito(), newIngredients);
				Bill bill = burritoMaticManager.getBillDetails();
				int orderId = burritoMaticManager.pay(bill.getBillAmount());
				Assert.assertEquals(bill.getBillAmount(), 6.49f, 0.00f);
				System.out.println(
						"Order places. Order id - " + orderId + ", Total amount paid - " + bill.getBillAmount());
			} catch (Exception e) {
				Assert.fail("Test failed with error - " + e.getMessage());
			}

		} catch (Exception e) {
			Assert.fail("Test failed with error - " + e.getMessage());
		}
	}
}
