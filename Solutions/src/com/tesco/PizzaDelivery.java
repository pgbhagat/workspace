package com.tesco;

import java.util.Arrays;

// Read only region start
public class PizzaDelivery {

	public static void main(String... args) {
		PizzaDelivery pizza = new PizzaDelivery();
		// int maxOrders = pizza.PizzaFactory(5, new int[] { 3, 6, 1, 4, 7 },
		// new int[] { 5, 8, 4, 7, 10 });
		int maxOrders = pizza.PizzaFactory(6, new int[] { 5, 1, 3, 0, 5, 8 }, new int[] { 9, 2, 4, 6, 7, 9 });
		System.out.println(maxOrders);
	}

	class Order implements Comparable<Order> {
		int startTime;
		int finishTime;

		@Override
		public int compareTo(Order o) {
			return this.finishTime - o.finishTime;
		}
	}

	public int PizzaFactory(int input1, int[] input2, int[] input3) {
		int ret = 0;
		if (input2 == null || input2.length == 0 || input3 == null || input3.length == 0) {
			throw new UnsupportedOperationException("input2 or input3 can not be empty or null.");
		}
		if (input2.length != input3.length) {
			throw new UnsupportedOperationException("input2 and input3 should match in size");
		}
		if (1 <= input1 && input1 <= 500) {
			if (1 <= input2.length && input2.length <= 500) {
				Order[] orders = getAllOrdersByFinishTime(input1, input2, input3);
				Arrays.sort(orders);// sort by finish time...
				ret = findMaxOrder(orders);
			} else {
				throw new UnsupportedOperationException("input2 and input3 size should be >=1 and <= 500");
			}
		} else {
			throw new UnsupportedOperationException("input1 should be >=1 and <=500");
		}
		return ret;
	}

	private int findMaxOrder(Order[] orders) {
		int maxOrders = 1;
		int i = 0;
		for (int j = 1; j < orders.length; j++) {
			if (orders[j].startTime >= orders[i].finishTime) {
				maxOrders++;
				i = j;
			}
		}
		return maxOrders;
	}

	private PizzaDelivery.Order[] getAllOrdersByFinishTime(int totalOrders, int[] startTime, int[] finishTime) {
		PizzaDelivery.Order[] orders = new PizzaDelivery.Order[totalOrders];
		for (int i = 0; i < totalOrders; i++) {
			orders[i] = new Order();
			orders[i].startTime = startTime[i];
			orders[i].finishTime = finishTime[i];
		}
		return orders;
	}
}