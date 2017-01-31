package com.dynamicprogramming;

public class MaxSnakeSequence {

	public static int getMaxSnakeSeqRecursion(int[][] arr, int row, int col) {
		if (row == arr.length - 1 && col == arr[0].length) {
			return 0;
		}
		int leftCount = 0;
		int rightCount = 0;
		if ((row + 1 <= arr.length - 1) && (Math.abs(arr[row + 1][col] - arr[row][col]) == 1))
			leftCount = getMaxSnakeSeqRecursion(arr, row + 1, col);
		if ((col + 1 <= arr[0].length - 1) && (Math.abs(arr[row][col] - arr[row][col + 1]) == 1))
			rightCount = getMaxSnakeSeqRecursion(arr, row, col + 1);
		return (leftCount > rightCount ? leftCount : rightCount ) + 1;

	}

	public static int[][] getMaxSnakePathIterative(int[][] arr) {
		int[][] result = new int[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				result[i][j] = 1;
			}
		}

		int maxLength = 0;
		int maxRow = 0;
		int maxCol = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				// down
				if (i > 0 && Math.abs(arr[i - 1][j] - arr[i][j]) == 1) {
					result[i][j] = Math.max(result[i][j], result[i - 1][j] + 1);
					if (maxLength < result[i][j]) {
						maxLength = result[i][j];
						maxRow = i;
						maxCol = j;
					}
				}

				// right
				if (j > 0 && Math.abs(arr[i][j - 1] - arr[i][j]) == 1) {
					result[i][j] = Math.max(result[i][j], result[i][j - 1] + 1);
					if (maxLength < result[i][j]) {
						maxLength = result[i][j];
						maxRow = i;
						maxCol = j;
					}
				}
			}
		}

		System.out.println("max length - " + maxLength + ", maxRow - " + maxRow + ", maxCol - " + maxCol);
		System.out.println("path - ");
		while (maxLength > 0) {
			System.out.print(arr[maxRow][maxCol] + " ");
			if (maxRow > 0 && Math.abs(result[maxRow - 1][maxCol] - result[maxRow][maxCol]) == 1) {
				maxRow--;
			} else if (maxCol > 0 && Math.abs(result[maxRow][maxCol - 1] - result[maxRow][maxCol]) == 1) {
				maxCol--;
			}
			maxLength--;
		}
		System.out.println();

		return result;
	}

	public static void main(String[] args) {
		int arr[][] = { { 1, 2, 1, 2 }, { 7, 7, 2, 5 }, { 6, 4, 3, 4 }, { 1, 2, 2, 5 } };
		// int arr[][] = { { 1, 2, 1, 2 }, { 7, 2, 8, 5 }, { 6, 4, 3, 4 }, { 1,
		// 3, 4, 5 } };
		System.out.println("max snake length - " + getMaxSnakeSeqRecursion(arr, 0, 0));
		for (int[] row : getMaxSnakePathIterative(arr)) {
			for (int el : row) {
				System.out.print(el + " ");
			}
			System.out.println();
		}

	}

}
