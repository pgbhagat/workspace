package com.burrito.cart;

import java.util.List;
import java.util.Map;

import com.burrito.items.IItem;
import com.burrito.order.OrderType;

public interface ICart {

	public void addItem(IItem item);

	public void removeItem(IItem item);

	public List<IItem> getAllSelectedItems();

	public Bill getBill();

	public int pay(float amount, OrderType type);

}
