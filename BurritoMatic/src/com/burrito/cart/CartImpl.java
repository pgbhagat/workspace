package com.burrito.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.burrito.items.IItem;
import com.burrito.items.IngredientItem;
import com.burrito.items.MenuItem;
import com.burrito.order.IOrder;
import com.burrito.order.OrderImpl;
import com.burrito.order.OrderType;
import com.burritomatic.user.User;

/**
 * Thread safe cart implementaion
 * 
 * @author Prashant.Bhagat
 *
 */
public class CartImpl implements ICart {

	User user = null;
	List<IItem> allItems = Collections.synchronizedList(new ArrayList<>());

	CartImpl(User user) {
		this.user = user;
	}

	@Override
	public void addItem(IItem item) {
		allItems.add(item);
	}

	@Override
	public void removeItem(IItem item) {
		allItems.remove(item);
	}

	@Override
	public synchronized Bill getBill() {
		float baseAmount = 0.0f;
		for (IItem item : allItems) {
			if (item instanceof MenuItem) {
				MenuItem burrito = (MenuItem) item;
				for (Entry<IngredientItem, Integer> entry : burrito.getAllIngredientsAndCount().entrySet()) {
					baseAmount += (entry.getKey().getPrice() * entry.getValue());
				}
			}
			baseAmount += item.getPrice();
		}
		return new Bill(baseAmount);
	}

	@Override
	public synchronized int pay(float amount, OrderType type) {
		IOrder newOrder = new OrderImpl();
		return newOrder.placeOrder(this, type);

	}

	@Override
	public List<IItem> getAllSelectedItems() {
		return Collections.unmodifiableList(allItems);
	}

}
