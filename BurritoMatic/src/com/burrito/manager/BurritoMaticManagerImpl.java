package com.burrito.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.burrito.cart.Bill;
import com.burrito.cart.CartStoreImpl;
import com.burrito.cart.ICart;
import com.burrito.inventory.IInventoryStore;
import com.burrito.inventory.InventoryStoreManager;
import com.burrito.items.IItem;
import com.burrito.items.IngredientItem;
import com.burrito.items.MenuItem;
import com.burrito.order.OrderType;
import com.burritomatic.exception.InventoryException;
import com.burritomatic.user.User;

class BurritoMaticManagerImpl implements IBurritoMaticManager {

	private User user;
	private ICart myCart;

	BurritoMaticManagerImpl(User user) {
		this.user = user;
		ICart cart = CartStoreImpl.getInstance().getCartForUser(user.getEmailId());
		if (cart == null) {
			CartStoreImpl.getInstance().addCartForUser(user);
			cart = CartStoreImpl.getInstance().getCartForUser(user.getEmailId());
		}
		myCart = cart;
	}

	@Override
	public Map<IItem, Float> getMenuOptions() {
		return InventoryStoreManager.getInstance().getAllMenuItemsAndPrices();
	}

	@Override
	public Map<IItem, Float> getAllIngredientOptions() {
		return InventoryStoreManager.getInstance().getAllIngredientItemsAndPrices();
	}

	@Override
	public void addToMyCart(IItem menu, IngredientItem... ingredients) throws Exception {
		if (!(menu instanceof MenuItem)) {
			throw new Exception("main menu can be of type only...berritto in bowl");
		}
		MenuItem menuItem = (MenuItem) menu;
		if (ingredients != null) {
			for (IngredientItem ingredient : ingredients) {
				menuItem.addIngredientsToMenuItem(ingredient, 1);
			}
		}
		ICart myCart = CartStoreImpl.getInstance().getCartForUser(user.getEmailId());
		myCart.addItem(menuItem);

	}

	@Override
	public void changeIngredients(IItem menu, IngredientItem... ingredients) throws Exception {
		if (!(menu instanceof MenuItem)) {
			throw new Exception("main menu can be of type only...berritto in bowl");
		}
		MenuItem menuItem = (MenuItem) menu;
		if (ingredients != null) {
			for (Entry<IngredientItem, Integer> ingredient : menuItem.getAllIngredientsAndCount().entrySet()) {
				menuItem.removeIngredientFromMenuItem(ingredient.getKey());
			}
			for (IngredientItem ingredient : ingredients) {
				menuItem.addIngredientsToMenuItem(ingredient, 1);
			}
		}
		myCart.removeItem(menu);
		myCart.addItem(menuItem);
	}

	@Override
	public void updateIngrdientCount(IItem menu, IngredientItem ingredient, int count) throws Exception {
		if (!(menu instanceof MenuItem)) {
			throw new Exception("main menu can be of type only...berritto in bowl");
		}
		MenuItem menuItem = (MenuItem) menu;
		if (ingredient != null) {
			menuItem.removeIngredientFromMenuItem(ingredient);
			menuItem.addIngredientsToMenuItem(ingredient, count);
		}
		myCart.removeItem(menu);
		myCart.addItem(menuItem);
	}

	@Override
	public void removeMenuFromMyCart(IItem menu) {
		if (!(menu instanceof MenuItem)) {
			// Warn,..
			return;
		}
		myCart.removeItem(menu);
	}

	@Override
	public Bill getBillDetails() {
		return myCart.getBill();
	}

	@Override
	public int pay(float amount) throws Exception{
		List<IItem> selectedItems = myCart.getAllSelectedItems();
		List<IItem> deducedItems = new ArrayList<IItem>();
		boolean success = true;
		for (IItem item : selectedItems) {
			try {
				InventoryStoreManager.getInstance().deduceFromInventory(item, 1);
				deducedItems.add(item);
			} catch (InventoryException e) {
				success = false;
				System.out.println("Item out of stock, Item - " + item.getName() + " currently is not available.");
				for (IItem deducedItem : deducedItems) {
					try {
						InventoryStoreManager.getInstance().restockInventory(deducedItem, deducedItem.getPrice(), 1);
					} catch (InventoryException e1) {
						System.out.println(
								"Error while restocking the deduced items during the payment.." + e.getMessage());
						throw e1;
					}
				}
				throw e;
			}
		}
		if (success) {
			int orderId = myCart.pay(amount, OrderType.DINE_IN);
			return orderId;
		} else {
			throw new Exception("Error during ordering, please try again");
		}
	}

	@Override
	public Map<IItem, Float> getNonBurritoOptions() {
		return InventoryStoreManager.getInstance().getAllNonBurritoItemsAndPrices();
	}

}
