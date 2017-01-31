package com.array;

public class AllPositiveNegativeOnEachSide {

	public static void main(String[] args) {

		int[] input = { 1, -2, 3, 4, -5, -2, -3, 4, 5, 6, 7, 8, 0, -1, -4, -5, 9 };
		separateAllPositiveAndNegativeEachSide(input);
		print(input);

	}

	private static void print(int[] input) {
		for (int i : input) {
			System.out.print(i + " ");
		}

	}

	private static void separateAllPositiveAndNegativeEachSide(int[] input) {
		if (input == null) {
			return;
		}
		int leftEnd = 0;
		int rightEnd = input.length - 1;
		while (leftEnd < rightEnd) {
			while (input[leftEnd] < 0 && leftEnd < input.length) {
				leftEnd++;
			}
			while (input[rightEnd] >= 0 && rightEnd >= 0) {
				rightEnd--;
			}
			// Swap
			if (leftEnd < rightEnd) {
				int tmp = input[leftEnd];
				input[leftEnd] = input[rightEnd];
				input[rightEnd] = tmp;
			}
		}

	}

}
