package com.burrito.manager;

import java.util.Map;

import com.burrito.cart.Bill;
import com.burrito.items.IItem;
import com.burrito.items.IngredientItem;

public interface IBurritoMaticManager {

	public Map<IItem, Float> getMenuOptions();

	public Map<IItem, Float> getAllIngredientOptions();
	
	public Map<IItem, Float> getNonBurritoOptions();
	
	public void addToMyCart(IItem menu, IngredientItem... ingredients) throws Exception;

	public void updateIngrdientCount(IItem menu, IngredientItem ingredient, int count) throws Exception;

	public void changeIngredients(IItem menu, IngredientItem... ingredients) throws Exception;

	public void removeMenuFromMyCart(IItem menu);

	public Bill getBillDetails();

	public int pay(float amount) throws Exception;
}
