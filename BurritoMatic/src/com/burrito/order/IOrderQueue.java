package com.burrito.order;

public interface IOrderQueue {

	void enqueuOrder(IOrder order);

	IOrder dequeueOrder();
}
