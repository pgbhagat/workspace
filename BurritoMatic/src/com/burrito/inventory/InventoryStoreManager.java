package com.burrito.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.burrito.items.DrinkItem;
import com.burrito.items.IItem;
import com.burrito.items.IngredientItem;
import com.burrito.items.MenuItem;
import com.burritomatic.exception.InventoryException;

/**
 * 
 * Thread safe inventory management
 * 
 * 
 * @author Prashant.Bhagat
 *
 */
public class InventoryStoreManager implements IInventoryStore {

	class StoreWrapper {
		private final Map<IItem, Integer> menuStore = new HashMap<>();
		private final Map<IItem, Integer> ingredientStore = new HashMap<>();
		private final Map<IItem, Integer> nonBurritoStore = new HashMap<>();

		public Map<IItem, Integer> getStore(IItem item) {
			if (item instanceof MenuItem) {
				return menuStore;
			} else if (item instanceof IngredientItem) {
				return ingredientStore;
			} else if (item instanceof DrinkItem) {
				return nonBurritoStore;
			}
			return null;
		}

		public Map<IItem, Integer> getMenuStore() {
			return menuStore;
		}

		public Map<IItem, Integer> getIngredientStore() {
			return ingredientStore;
		}

		public Map<IItem, Integer> getNonBurritoStore() {
			return nonBurritoStore;
		}
	}

	private ItemToPrice itemToPrice = new ItemToPrice();
	private StoreWrapper storeWrapper = new StoreWrapper();

	private static InventoryStoreManager instance = null;

	public static InventoryStoreManager getInstance() {
		if (instance == null) {
			synchronized (InventoryStoreManager.class) {
				if (instance == null) {
					instance = new InventoryStoreManager();
				}
			}
		}
		return instance;
	}

	class ItemToPrice {
		Map<IItem, Float> itemToPriceStore = new HashMap<>();

		private float getPrice(IItem item) {
			return itemToPriceStore.get(item);
		}

		private float updatePrice(IItem item, float newPricePerUnit) {
			Float oldValue = itemToPriceStore.put(item, newPricePerUnit);
			return oldValue == null ? 0.0f : oldValue;
		}
	}

	@Override
	public void restockInventory(IItem item, float pricePerItem, int totalUnits) throws InventoryException {
		if (pricePerItem < 0.0f) {
			throw new InventoryException("Item price can't be less than zero..");
		}
		if (totalUnits > 0) {
			synchronized (this) {
				Map<IItem, Integer> store = storeWrapper.getStore(item);
				store.put(item, (store.get(item) != null ? store.get(item) : 0) + totalUnits);
				itemToPrice.updatePrice(item, pricePerItem);
			}
		}
	}

	@Override
	public float deduceFromInventory(IItem item, int unitsToDeduce) throws InventoryException {
		if (unitsToDeduce > 0) {
			synchronized (this) {
				int existintUnits = 0;
				if (storeWrapper.getStore(item).containsKey(item)) {
					existintUnits = storeWrapper.getStore(item).get(item);
				}
				if (unitsToDeduce <= existintUnits) {
					storeWrapper.getStore(item).put(item, existintUnits - unitsToDeduce);
				} else {
					throw new InventoryException(
							"Not enough items [ " + item.getName() + "] in store, available items - " + existintUnits);
				}
			}
		}
		return 0;
	}

	@Override
	public float updatePrice(IItem item, float newPricePerUnit) throws InventoryException {
		float oldPrice = 0.0f;
		if (newPricePerUnit < 0) {
			synchronized (this) {
				oldPrice = itemToPrice.updatePrice(item, newPricePerUnit);
			}
		} else {
			throw new InventoryException("Invalid price to be updated..");
		}
		return oldPrice;
	}

	@Override
	public synchronized boolean isItemCountAvailable(IItem itemToCheckFor, int totalUnitsToCheck) {
		float existingUnits = storeWrapper.getStore(itemToCheckFor).get(itemToCheckFor);
		if (existingUnits >= totalUnitsToCheck) {
			return true;
		}
		return false;
	}

	@Override
	public synchronized float getPrice(IItem item) {
		return itemToPrice.getPrice(item);
	}

	@Override
	public synchronized Map<IItem, Float> getAllMenuItemsAndPrices() {
		Map<IItem, Float> menuItemsAndPrices = new HashMap<IItem, Float>();
		for (Entry<IItem, Integer> entry : storeWrapper.getMenuStore().entrySet()) {
			menuItemsAndPrices.put(entry.getKey(), itemToPrice.getPrice(entry.getKey()));
		}
		return menuItemsAndPrices;
	}

	@Override
	public synchronized Map<IItem, Float> getAllIngredientItemsAndPrices() {
		Map<IItem, Float> ingredientItemsAndPrices = new HashMap<IItem, Float>();
		for (Entry<IItem, Integer> entry : storeWrapper.getIngredientStore().entrySet()) {
			ingredientItemsAndPrices.put(entry.getKey(), itemToPrice.getPrice(entry.getKey()));
		}
		return ingredientItemsAndPrices;
	}

	@Override
	public synchronized Map<IItem, Float> getAllNonBurritoItemsAndPrices() {
		Map<IItem, Float> drinkItemsAndPrices = new HashMap<IItem, Float>();
		for (Entry<IItem, Integer> entry : storeWrapper.getNonBurritoStore().entrySet()) {
			drinkItemsAndPrices.put(entry.getKey(), itemToPrice.getPrice(entry.getKey()));
		}
		return drinkItemsAndPrices;
	}

	@Override
	public synchronized int getAvailbeItemCount(IItem item) {
		int existintUnits = 0;
		if (storeWrapper.getStore(item).containsKey(item)) {
			existintUnits = storeWrapper.getStore(item).get(item);
		}
		return existintUnits;
	}

}
