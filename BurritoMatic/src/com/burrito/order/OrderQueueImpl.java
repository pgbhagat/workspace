package com.burrito.order;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class OrderQueueImpl implements IOrderQueue {

	Queue<IOrder> allOrdersInProcess;

	int MAX_ORDER_IN_PROCESS = 100;

	private static IOrderQueue instance = new OrderQueueImpl();

	public static IOrderQueue getInstance() {
		return instance;
	}

	private OrderQueueImpl() {
		allOrdersInProcess = new LinkedBlockingQueue<IOrder>(MAX_ORDER_IN_PROCESS);
	}

	@Override
	public void enqueuOrder(IOrder order) {
		allOrdersInProcess.offer(order);
	}

	@Override
	public IOrder dequeueOrder() {
		return allOrdersInProcess.remove();
	}

}
