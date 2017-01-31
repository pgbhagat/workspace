package com.burrito.items;

public class ThreeIngredientBurrito extends MenuItem {

	public ThreeIngredientBurrito() {
		super(IItemName.Three_Ingredient_Burrito.name());
	}

	@Override
	public float getPrice() {
		return 5.99f;
	}
}