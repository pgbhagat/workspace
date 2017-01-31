package com.array;

enum ROW_DIR {
	FORWARD, BACKWARD
};

enum COL_DIR {
	UP, DOWN
};

public class Print2DArrayInSpiral {

	public static void main(String[] args) {

		//@formatter:off
		int input[][] = { 
				{ 1, 2, 3, 4, 5 }, 
				{ 18, 19, 20, 21, 6 },
				{ 17, 28, 29, 22, 7 }, 
				{ 16, 27, 30, 23, 8 },
				{ 15, 26, 25, 24, 9 }, 
				{ 14, 13, 12, 11, 10 } };
		//	@formatter:on
		printSpiral(input, 0, input.length - 1, 0, input[0].length - 1, ROW_DIR.FORWARD, null);

	}

	private static void printSpiral(int[][] input, int rowStart, int rowEnd, int colStart, int colEnd, ROW_DIR rowWay,
			COL_DIR colWay) {

		if (rowStart > rowEnd || colStart > colEnd)
			return;
		if (rowWay != null) {
			if (rowWay.equals(ROW_DIR.FORWARD)) {
				for (int j = colStart; j <= colEnd; j++) {
					System.out.print(input[rowStart][j] + " ");
				}
				System.out.println();
				printSpiral(input, rowStart + 1, rowEnd, colStart, colEnd, null, COL_DIR.UP);
			} else {
				for (int j = colEnd; j >= colStart; j--) {
					System.out.print(input[rowEnd][j] + " ");
				}
				System.out.println();
				printSpiral(input, rowStart, rowEnd - 1, colStart, colEnd, null, COL_DIR.DOWN);
			}
		} else {
			if (colWay.equals(COL_DIR.UP)) {
				for (int i = rowStart; i <= rowEnd; i++) {
					System.out.print(input[i][colEnd] + " ");
				}
				System.out.println();
				printSpiral(input, rowStart, rowEnd, colStart, colEnd - 1, ROW_DIR.BACKWARD, null);
			} else {
				for (int i = rowEnd; i >= rowStart; i--) {
					System.out.print(input[i][colStart] + " ");
				}
				System.out.println();
				printSpiral(input, rowStart, rowEnd, colStart + 1, colEnd, ROW_DIR.FORWARD, null);
			}

		}

	}

}
