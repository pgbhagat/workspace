package com.backtracking;

import java.util.List;

public class Sudoku {

	private static final int BOX_SIZE = 3;

	public static void main(String[] args) {
		//@formatter:off
		int[][] inputGrid = new int[][] { 
				{ 5, 3, 0, 0, 7, 0, 0, 0, 0 },
				{ 6, 0, 0, 1, 9, 5, 0, 0, 0 }, 
				{ 0, 9, 8, 0, 0, 0, 0, 6, 0 },
				{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, 
				{ 4, 0, 0, 8, 0, 3, 0, 0, 1 },
				{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, 
				{ 0, 6, 0, 0, 0, 0, 2, 8, 0 },
				{ 0, 0, 0, 4, 1, 9, 0, 0, 5 }, 
				{ 0, 0, 0, 0, 8, 0, 0, 7, 9 },
		};
		//@formatter:on

		if (solveSudoku(inputGrid)) {
			printGrid(inputGrid);
		}
	}

	private static boolean solveSudoku(int[][] inputGrid) {
		boolean isSolved = false;
		int[] cell = getEmptyCell(inputGrid);
		int row = cell[0];
		int col = cell[1];
		if (row == -1 || col == -1) {
			isSolved = true;
		} else {
			for (int num = 1; num <= inputGrid.length; num++) {
				if (isSafe(inputGrid, row, col, num)) {
					inputGrid[row][col] = num;
					if (solveSudoku(inputGrid)) {
						isSolved = true;
						break;
					}
					inputGrid[row][col] = 0;
				}
			}
		}
		return isSolved;
	}

	private static boolean isSafe(int[][] inputGrid, int row, int col, int num) {

		if (!usedInRow(inputGrid, row, num) && !usedInCol(inputGrid, col, num)
				&& !usedInBox(inputGrid, row - row % BOX_SIZE, col - col % BOX_SIZE, num)) {
			return true;
		}
		return false;
	}

	private static boolean usedInBox(int[][] inputGrid, int rowStart, int colStart, int num) {
		for (int i = rowStart; i < rowStart + BOX_SIZE; i++) {
			for (int j = colStart; j < colStart + BOX_SIZE; j++) {
				if (inputGrid[i][j] == num) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean usedInCol(int[][] inputGrid, int col, int num) {
		for (int i = 0; i < inputGrid.length; i++) {
			if (inputGrid[i][col] == num) {
				return true;
			}
		}
		return false;
	}

	private static boolean usedInRow(int[][] inputGrid, int row, int num) {
		for (int i = 0; i < inputGrid[0].length; i++) {
			if (inputGrid[row][i] == num) {
				return true;
			}
		}
		return false;
	}

	private static int[] getEmptyCell(int[][] inputGrid) {
		int[] cell = new int[] { -1, -1 };
		for (int i = 0; i < inputGrid.length; i++) {
			for (int j = 0; j < inputGrid[0].length; j++) {
				if (inputGrid[i][j] == 0) {
					cell[0] = i;
					cell[1] = j;
					return cell;
				}
			}
		}
		return cell;
	}

	private static void printGrid(int[][] outputGrid) {
		for (int[] outputRow : outputGrid) {
			for (int element : outputRow) {
				System.out.print(element + " ");
			}
			System.out.println();
		}
	}

}
