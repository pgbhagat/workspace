package com.array;

public class FixIndexes {

	public static void main(String[] args) {
		int[] input = { -1, 1, 2, 4, 5, 8, -1, -1, 10, -1, -1 };
		print(input);
		System.out.println();
		fixIndexes(input);
		print(input);
	}

	private static void fixIndexes(int[] input) {
		for (int i = 0; i < input.length; i++) {
			if (input[i] != -1 && input[i] != i) {
				int value = input[i];
				while (input[value] != -1 && input[value] != value) {
					int tmp = input[value];
					input[value] = value;
					value = tmp;
				}
				input[value] = value;
				if (input[i] != i) {
					input[i] = -1;
				}
			}
		}
	}

	private static void print(int[] input) {
		for (int ele : input) {
			System.out.print(ele + " ");
		}
	}

}
