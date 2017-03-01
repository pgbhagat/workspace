package com.hackerank;

/**
 * 
 * input array - all items prices.
 * 
 * output -
 * 
 * First line: total discounts for all items
 * 
 * Second line: space separated indices with no discounts.
 * 
 * Discount for ith item will be the amount of first item on right side which is
 * <= price [i], ZERO is no such element
 * 
 * @author Prashant.Bhagat
 *
 */
public class Discounts {

	static void finalPrice(int[] prices) {
		if (prices != null) {
			int totalItems = prices.length;
			int[] discounts = new int[totalItems];
			discounts[totalItems - 1] = 0;// last item with ZERO discount
			int minDiscount = prices[totalItems - 1];
			for (int i = totalItems - 2; i >= 0; i--) {
				minDiscount = getFirstItemWithPrice(prices, i);
				if (prices[i] >= minDiscount) {
					discounts[i] = minDiscount;
				} else {
					discounts[i] = 0;
				}
			}
			int totalDiscount = 0;
			for (int i = 0; i < prices.length; i++) {
				totalDiscount += prices[i] - discounts[i];
			}
			System.out.println(totalDiscount);
			for (int i = 0; i < discounts.length; i++) {
				if (discounts[i] == 0)
					System.out.print(i + " ");
			}
		}
	}

	private static int getFirstItemWithPrice(int[] prices, int i) {
		int currPrice = prices[i];
		for (int j = i + 1; j < prices.length; j++) {
			if (currPrice >= prices[j]) {
				return prices[j];
			}
		}
		return 0;
	}

	public static void main(String... args) {

		int prices[] = { 5, 1, 3, 4, 6, 2 };
		// int prices[] = { 1, 3, 3, 2, 5 };
		finalPrice(prices);
	}
}
