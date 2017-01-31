package com.burrito.items;

public enum IngredientItem implements IItem {

	Tortilla("Tortilla", IngredientType.Base),

	Rice("Rice", IngredientType.Base),

	Chicken("Chicken", IngredientType.Meat),

	Steak("Steak", IngredientType.Meat),

	RedSalsa("Red salsa", IngredientType.Salsa),

	GreenSalsa("Green salsa", IngredientType.Salsa),

	Queso("Queso", IngredientType.Salsa) {
		@Override
		public float getPrice() {
			return 1.5f;
		}
	},

	GratedCheese("Grated cheese", IngredientType.Toppings),

	SourCream("Sour Cream", IngredientType.Toppings),

	Gucamole("Gucamole", IngredientType.Toppings) {
		@Override
		public float getPrice() {
			return 1.25f;
		}
	};

	private String name;
	private IngredientType type;

	IngredientItem(String name, IngredientType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getPrice() {
		return 0;
	}

	public IngredientType getType() {
		return type;
	}

}
