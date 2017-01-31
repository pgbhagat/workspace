package com.burrito.order;

import java.util.HashMap;
import java.util.Map;

class OrderStore {
	Map<String, IOrder> orderStore;

	// private static OrderStore instance = new OrderStore();

	private OrderStore() {
		orderStore = new HashMap<String, IOrder>();
	}

}
