package com.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class MinCoinChange {

	private List<List<Integer>> getAllPossibleWaysRecursion(int amount, int[] coins) {
		List<List<Integer>> totalWays = new ArrayList<List<Integer>>();
		if (amount == 0) {
			totalWays.add(new ArrayList<Integer>());
			return totalWays;
		}
		for (int i = 0; i < coins.length; i++) {
			if (coins[i] <= amount) {
				List<List<Integer>> ways = getAllPossibleWaysRecursion(amount - coins[i], coins);
				for (List<Integer> w : ways) {
					w.add(coins[i]);
					totalWays.add(w);
				}
			}
		}
		return totalWays;
	}

	private int[][] getMinChangeDynamicProgramming(int amount, int[] coins) {
		int[][] solution = null;
		if (amount > 0 && coins != null && coins.length > 0) {
			solution = new int[coins.length][amount + 1];
			
			for (int i = 0; i < solution.length; i++) {
				solution[i][0] = 0;
			}
			for (int i = 0; i < solution[0].length; i++) {
				solution[0][i] = i;
			}

			for (int i = 1; i < coins.length; i++) {
				for (int j = 1; j < solution[0].length; j++) {
					if (j < coins[i]) {
						solution[i][j] = solution[i - 1][j];
					} else {
						int rem = j - coins[i];
						solution[i][j] = 1 + solution[i][rem];
					}
				}
			}
		}
		return solution;
	}

	public static void main(String[] args) {
		MinCoinChange min = new MinCoinChange();
		int[] coins = { 1, 2, 3 };
		int[][] ans = min.getMinChangeDynamicProgramming(7, coins);
		for (int[] row : ans) {
			for (int e : row) {
				System.out.print(e + " ");
			}
			System.out.println();
		}

		System.out.println("with recursion..");
		List<List<Integer>> ways = min.getAllPossibleWaysRecursion(7, coins);
		for (List<Integer> way : ways) {
			System.out.println(way);
		}
	}

}
