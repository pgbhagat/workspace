package com.burrito.items;

public class ALaCarte extends MenuItem {

	ALaCarte() {
		super(IItemName.A_La_Carte.name());
	}

	@Override
	public float getPrice() {
		return 5.99f;
	}

}
