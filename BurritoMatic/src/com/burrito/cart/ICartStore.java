package com.burrito.cart;

import com.burritomatic.user.User;

public interface ICartStore {

	public ICart getCartForUser(String emaildId);

	public void addCartForUser(User user);

}
