package com.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class AllCoinChangeWays {

	public static void main(String[] args) {
		int[] coins = { 1, 2, 3, 4 };
		int amount = 5;
		List<List<Integer>> ways = getAllPossibleCoinsForAmount(amount, coins);
		for (List<Integer> way : ways) {
			System.out.println(way);
		}
	}

	private static List<List<Integer>> getAllPossibleCoinsForAmount(int amount, int[] coins) {
		List<List<Integer>> totalWays = new ArrayList<List<Integer>>();
		if (amount == 0) {
			totalWays.add(new ArrayList<Integer>());
		} else {
			for (int i = 0; i < coins.length; i++) {
				if (coins[i] <= amount) {
					List<List<Integer>> tmpWays = getAllPossibleCoinsForAmount(amount - coins[i], coins);
					for (List<Integer> way : tmpWays) {
						way.add(coins[i]);
						totalWays.add(way);
					}
				}
			}
		}
		return totalWays;
	}

}
