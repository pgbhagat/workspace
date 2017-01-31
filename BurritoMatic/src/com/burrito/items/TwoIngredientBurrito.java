package com.burrito.items;

public class TwoIngredientBurrito extends MenuItem {

	public TwoIngredientBurrito() {
		super(IItemName.Two_Ingredient_Burrito.name());
	}

	@Override
	public float getPrice() {
		return 4.99f;
	}
}