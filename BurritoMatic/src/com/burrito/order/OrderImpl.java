package com.burrito.order;

import java.util.Date;
import java.util.Map;

import com.burrito.cart.ICart;
import com.burrito.items.IItem;
import com.burritomatic.user.User;

public class OrderImpl implements IOrder {

	@Override
	public int getOrderId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<IItem, Integer> getOrderedItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int placeOrder(ICart cart, OrderType type) {
		return 0;
	}

	@Override
	public boolean cancelOrder(int orderId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrderStatus trackOrder(int orderiId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDatetime(Date date) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getTotalBill() {
		// TODO Auto-generated method stub
		return 0;
	}

}
