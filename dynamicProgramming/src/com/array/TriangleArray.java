package com.array;

public class TriangleArray {

	public static void main(String[] args) {

		int[] input = { 1, 2, 3, 4, 5, 6 };
		triangle(input);
	}

	private static void triangle(int[] input) {
		if (input.length > 1) {
			int[] level = new int[input.length - 1];
			for (int i = 0; i < level.length; i++) {
				level[i] = input[i] + input[i + 1];
			}
			triangle(level);
		}
		for (int ele : input) {
			System.out.format("%3d ", ele);
		}
		System.out.println();
	}

}
