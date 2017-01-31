package com.dynamicprogramming;

public class AllCombinationOfChars {

	public static void main(String[] args) {
		char[] input = new char[] { '1', '0', '2' };
		int length = 5;
		printAllPermutationsOfLength(input, 0, new char[length]);
	}

	private static void printAllPermutationsOfLength(char[] input, int index, char[] sol) {
		if (index == sol.length) {
			for (char c : sol) {
				System.out.print(" " + c);
			}
			System.out.println();
		} else if (index < sol.length) {
			for (int i = 0; i < input.length; i++) {
				sol[index] = input[i];
				printAllPermutationsOfLength(input, index + 1, sol);
			}
		}

	}

}
