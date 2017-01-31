package com.burrito.cart;

import java.util.HashMap;
import java.util.Map;

import com.burritomatic.user.User;

public class CartStoreImpl implements ICartStore {

	private Map<String, ICart> usersAndCarts;
	private static CartStoreImpl instance = new CartStoreImpl();

	public static ICartStore getInstance() {
		return instance;
	}

	private CartStoreImpl() {
		usersAndCarts = new HashMap<>();
	}

	@Override
	public synchronized ICart getCartForUser(String emailId) {
		return usersAndCarts.get(emailId);
	}

	@Override
	public synchronized void addCartForUser(User user) {
		if (!usersAndCarts.containsKey(user.getEmailId())) {
			usersAndCarts.put(user.getEmailId(), new CartImpl(user));
		}
	}

}
