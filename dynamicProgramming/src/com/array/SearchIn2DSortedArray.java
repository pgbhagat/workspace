package com.array;

public class SearchIn2DSortedArray {

	public static void main(String[] args) {
		//@formatter:off
		int [][] input = { 
				{ 1, 2, 3, 4 }, 
				{ 6, 7, 8, 9 }, 
				{ 13, 14, 15, 16 },
				{ 17, 18, 19, 20 }, 
				{ 22, 23, 24, 26 } };
		
		//@formatter:on
		search(input, 19);

	}

	private static void search(int[][] input, int number) {
		int row = 0;
		int col = input[0].length - 1;
		while (row < input.length && col >= 0) {
			if (input[row][col] == number) {
				System.out.println(number + " found at row - " + row + ", col - " + col);
				return;
			} else if (number < input[row][col]) {
				col--;
			} else {
				row++;
			}
		}
		System.out.println(number + " not found");

	}

}
