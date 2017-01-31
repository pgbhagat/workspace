package com.burrito.inventory;

import java.util.Map;

import com.burrito.items.IItem;
import com.burritomatic.exception.InventoryException;

public interface IInventoryStore {

	public Map<IItem, Float> getAllMenuItemsAndPrices();

	public Map<IItem, Float> getAllIngredientItemsAndPrices();

	public void restockInventory(IItem item, float pricePerItem, int totalUnits) throws InventoryException;

	public float deduceFromInventory(IItem item, int unitsToDeduce) throws InventoryException;

	public float updatePrice(IItem item, float newPricePerItem) throws InventoryException;

	public boolean isItemCountAvailable(IItem itemToCheckFor, int totalUnitsToCheck);

	public int getAvailbeItemCount(IItem item);

	public float getPrice(IItem item);

	Map<IItem, Float> getAllNonBurritoItemsAndPrices();

}
