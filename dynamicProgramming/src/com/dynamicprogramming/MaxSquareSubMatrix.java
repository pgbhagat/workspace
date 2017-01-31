package com.dynamicprogramming;

public class MaxSquareSubMatrix {

	public static int maxSquareSubMatrix1(int[][] matrix) {
		int maxSize = 0;
		int row = 0, col = 0;
		if (matrix != null && matrix.length > 0) {
			int[][] solution = new int[matrix.length][matrix[0].length];
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					solution[i][j] = matrix[i][j];
				}
			}
			for (int i = 1; i < matrix.length; i++) {
				for (int j = 1; j < matrix[0].length; j++) {
					if (matrix[i][j] == 1 && matrix[i - 1][j] == 1 && matrix[i - 1][j - 1] == 1
							&& matrix[i][j - 1] == 1) {
						solution[i][j] = solution[i - 1][j - 1] + 1;
						if (solution[i][j] > maxSize) {
							maxSize = solution[i][j];
							row = i;
							col = j;
						}
					}
				}
			}
		}
		System.out.println("max size square ending at  [" + row + "][" + col + "]");
		System.out.println("1 max size - " + maxSize);
		return maxSize;
	}

	public static int maxSquareSubMatrix2(int[][] matrix) {
		int maxSize = 0;
		int[][] solution = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			solution[i][0] = matrix[i][0];
		}
		for (int i = 0; i < matrix[0].length; i++) {
			solution[0][i] = matrix[0][i];
		}

		for (int i = 1; i < solution.length; i++) {
			for (int j = 1; j < solution[0].length; j++) {
				if (matrix[i][j] == 1) {
					solution[i][j] = 1
							+ Math.min(solution[i - 1][j - 1], Math.min(solution[i - 1][j], solution[i - 1][j - 1]));
					if (solution[i][j] > maxSize) {
						maxSize = solution[i][j];
					}
				} else {
					solution[i][j] = 0;
				}
			}
		}
		System.out.println("2 max size - " + maxSize);
		return maxSize;

	}

	public static void main(String[] args) {
		int[][] matrix = {

				{ 0, 0, 1, 0, 1 }, { 0, 0, 1, 0, 1 }, { 0, 0, 1, 1, 1 }, { 0, 0, 1, 1, 1 }, // 3
				{ 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0 }, // 5
				{ 0, 1, 1, 1, 0 }, // 6

		};
		maxSquareSubMatrix1(matrix);
		maxSquareSubMatrix2(matrix);
	}

}
