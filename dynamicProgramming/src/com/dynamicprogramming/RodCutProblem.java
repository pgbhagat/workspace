package com.dynamicprogramming;

import java.util.Arrays;

public class RodCutProblem {

	public static int rodCutRecursion(int rodLength, int[] costs) {
		int maxCost = 0;
		if (rodLength > 0) {
			for (int i = 1; i < costs.length + 1; i++) {
				if (rodLength >= i) {
					int amount = costs[i - 1] + rodCutRecursion(rodLength - i, costs);
					if (amount > maxCost) {
						maxCost = amount;
					}
				}
			}
		}
		return maxCost;
	}

	public static int[][] rodCutDynamicProgramming(int rodLengh, int[] costs) {
		int[][] solution = new int[costs.length][rodLengh + 1];
		Arrays.sort(costs);
		// populate the last row...
		for (int i = 1; i < solution[0].length; i++) {
			solution[0][i] = costs[0] * i;
		}
		for (int i = 1; i < solution.length; i++) {
			for (int j = 1; j < solution[0].length; j++) {
				solution[i][j] = solution[i - 1][j];
				if (j >= (i + 1)) {
					int maxProfit = costs[i] + solution[i][j - (i + 1)];
					if (maxProfit > solution[i][j]) {
						solution[i][j] = maxProfit;
					}

				}
			}
		}
		return solution;
	}

	public static void main(String[] args) {
		int[] costs = new int[] { 2, 5, 8, 9, 10 };
		System.out.println(rodCutRecursion(4, costs));
		int[][] solution = rodCutDynamicProgramming(4, costs);
		for (int[] row : solution) {
			for (int profit : row) {
				System.out.print(profit + " ");
			}
			System.out.println("");
		}
	}

}
