package com.array;

public class PrintAllPathsArray {

	public static void main(String[] args) {
		//@formatter:off
		int input[][] = { 
				{ 1, 2, 3, 4, 5 }, 
				{ 18, 19, 20, 21, 6 },
				{ 17, 28, 29, 22, 7 }, 
				{ 16, 27, 30, 23, 8 },
				{ 15, 26, 25, 24, 9 }, 
				{ 14, 13, 12, 11, 10 } };
		//@formatter:on
		printAllPaths(input, 0, 0, "");
	}

	private static void printAllPaths(int[][] input, int row, int col, String path) {
		if (row == input.length - 1 && col == input[0].length - 1) {
			path = path + " " + input[row][col];
			System.out.println(path);
		} else {
			if (row < input.length && col < input[0].length) {
				path = path + " " + input[row][col];
				printAllPaths(input, row + 1, col, path);
				printAllPaths(input, row, col + 1, path);
				printAllPaths(input, row + 1, col + 1, path);
			}
		}
	}

}
