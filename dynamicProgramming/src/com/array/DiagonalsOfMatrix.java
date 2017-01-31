package com.array;

public class DiagonalsOfMatrix {

	public static void main(String[] args) {
		//@formatter:off
		int [][] matrix = {
				{1,  2,  3,  4},
				{5,  6,  7,  8},
				{9,  10, 11, 12},
				{13, 14, 15, 16}
				
		};
		//@formatter:on
		printMatrixDiagonals(matrix);
	}

	private static void printMatrixDiagonals(int[][] matrix) {
		if (matrix == null || matrix.length <= 1 || matrix[0].length <= 1)
			return;

		int totalRows = matrix.length;
		int totalColumns = matrix[0].length;

		int row = 0;
		while (row < totalRows) {
			int tmpRow = row;
			int tmpCol = 0;
			while (tmpCol < totalColumns && tmpRow < totalRows) {
				System.out.print(matrix[tmpRow++][tmpCol++] + " ");

			}
			System.out.println();
			row++;
		}

		int col = 1;
		while (col < totalColumns) {
			int tmpCol = col;
			int tmpRow = 0;
			while (tmpCol < totalColumns && tmpRow < totalRows) {
				System.out.print(matrix[tmpRow++][tmpCol++] + " ");
			}
			System.out.println();
			col++;
		}
	}

}
