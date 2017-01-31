package com.burrito.items;

public enum DrinkItem implements IItem {

	SOFT_DRINK("SOFT_DRINK") {
		@Override
		public float getPrice() {
			return 1.25f;
		}
	},

	BRIOWNIE("BRIOWNIE") {
		@Override
		public float getPrice() {
			return 1.5f;
		}
	},

	CHIPS("CHIPS") {
		@Override
		public float getPrice() {
			return 2.0f;
		}
	};

	private String name;

	DrinkItem(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public abstract float getPrice();

}
