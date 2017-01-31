package com.dynamicprogramming;

public class MinCostPath {

	public static int minCostPathRecurse(int[][] costPathMatrix, int row, int col) {
		int min = Integer.MAX_VALUE;
		if (row == 0 && col == costPathMatrix[0].length - 1) {
			min = costPathMatrix[row][col];
		} else {
			int rightwayCost = Integer.MAX_VALUE;
			int downwayCost = Integer.MAX_VALUE;
			if (col + 1 <= costPathMatrix[0].length - 1) {
				rightwayCost = costPathMatrix[row][col] + minCostPathRecurse(costPathMatrix, row, col + 1);
			}
			if (row - 1 >= 0) {
				downwayCost = costPathMatrix[row][col] + minCostPathRecurse(costPathMatrix, row - 1, col);
			}
			min = Math.min(rightwayCost, downwayCost);
		}
		return min;
	}

	public static void minCostPathIterative(int[][] costPathMatrix) {
		int[][] solution = new int[costPathMatrix.length][costPathMatrix[0].length];
		solution[solution.length - 1][0] = costPathMatrix[solution.length - 1][0];
		// fill the top row
		for (int i = 1; i < solution[0].length; i++) {
			solution[solution.length - 1][i] = costPathMatrix[solution.length - 1][i]
					+ solution[solution.length - 1][i - 1];
		}
		// fill the leftmost column i.e. ZEROth
		for (int i = solution.length - 2; i >= 0; i--) {
			solution[i][0] = costPathMatrix[i][0] + solution[i + 1][0];
		}
		// fill the rest matrix...
		for (int i = solution.length - 2; i >= 0; i--) {
			for (int j = 1; j < solution[0].length; j++) {
				solution[i][j] = Math.min(solution[i + 1][j], solution[i][j - 1]) + costPathMatrix[i][j];
			}
		}
		for (int i = solution.length - 1; i >= 0; i--) {
			for (int j = 0; j < solution[0].length; j++) {
				System.out.print(solution[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[][] costPathMatrix = { { 2, 9, 8, 2 }, { 1, 6, 7, 8 }, { 8, 6, 3, 2 }, { 1, 7, 9, 2 } };
		System.out.println(minCostPathRecurse(costPathMatrix, costPathMatrix.length - 1, 0));
		minCostPathIterative(costPathMatrix);

	}

}
