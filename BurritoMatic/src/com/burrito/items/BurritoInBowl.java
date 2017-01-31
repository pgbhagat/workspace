package com.burrito.items;

public class BurritoInBowl extends MenuItem {

	public BurritoInBowl() {
		super(IItemName.Burrito_In_A_Bowl.name());
	}

	@Override
	public float getPrice() {
		return 3.99f;
	}
}