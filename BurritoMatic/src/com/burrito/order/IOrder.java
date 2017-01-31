package com.burrito.order;

import java.util.Date;
import java.util.Map;

import com.burrito.cart.ICart;
import com.burrito.items.IItem;
import com.burritomatic.user.User;

//Furute order related...
public interface IOrder {

	void setDatetime(Date date);

	float getTotalBill();

	int getOrderId();

	Map<IItem, Integer> getOrderedItems();

	OrderStatus trackOrder(int orderiId);

	User getUser();

	void setUser(User user);

	boolean cancelOrder(int orderId);

	int placeOrder(ICart cart, OrderType type);

}
